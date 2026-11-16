package com.joao.sistemafarmacia.dao;

import com.joao.sistemafarmacia.model.Pedido;
import com.joao.sistemafarmacia.model.Receita;
import com.joao.sistemafarmacia.util.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReceitaDAO {

    private final Connection conn;

    public ReceitaDAO() {
        this.conn = Conexao.getInstance();
    }

    public Receita salvar(Receita receita) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO receita (codigo, nome_medico, data_emissao, data_entrada, pedido_id) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, receita.getCodigo());
            stmt.setString(2, receita.getNomeMedico());
            stmt.setDate(3, receita.getDataEmissao() != null ? Date.valueOf(receita.getDataEmissao()) : null);
            stmt.setDate(4, receita.getDataEntrada() != null ? Date.valueOf(receita.getDataEntrada()) : null);
            stmt.setLong(5, receita.getPedido().getId());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                receita.setId(rs.getLong(1));
            }
            rs.close();
            stmt.close();
            return receita;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível salvar a receita", e);
        }
    }

    public Receita editar(Receita receita) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE receita SET codigo=?, nome_medico=?, data_emissao=?, data_entrada=?, pedido_id=? WHERE receita_id=?");
            stmt.setString(1, receita.getCodigo());
            stmt.setString(2, receita.getNomeMedico());
            stmt.setDate(3, receita.getDataEmissao() != null ? Date.valueOf(receita.getDataEmissao()) : null);
            stmt.setDate(4, receita.getDataEntrada() != null ? Date.valueOf(receita.getDataEntrada()) : null);
            stmt.setLong(5, receita.getPedido().getId());
            stmt.setLong(6, receita.getId());
            stmt.executeUpdate();
            stmt.close();
            return receita;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível editar a receita", e);
        }
    }

    public void deletar(Long id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM receita WHERE receita_id=?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível deletar a receita", e);
        }
    }

    public List<Receita> buscarTodos() {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM receita");
            ResultSet rs = stmt.executeQuery();
            return mapearLista(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível buscar as receitas", e);
        }
    }

    public List<Receita> buscarPorNomeMedico(String nomeMedico) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM receita WHERE nome_medico ILIKE ?");
            stmt.setString(1, "%" + nomeMedico + "%");
            ResultSet rs = stmt.executeQuery();
            return mapearLista(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível buscar receitas pelo nome do médico", e);
        }
    }

    public List<Receita> buscarPorPedido(Long pedidoId) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM receita WHERE pedido_id=?");
            stmt.setLong(1, pedidoId);
            ResultSet rs = stmt.executeQuery();
            return mapearLista(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível buscar as receitas do pedido", e);
        }
    }

    private List<Receita> mapearLista(ResultSet rs) throws SQLException {
        List<Receita> receitas = new ArrayList<>();
        while (rs.next()) {
            receitas.add(mapear(rs));
        }
        return receitas;
    }

    private Receita mapear(ResultSet rs) throws SQLException {
        Receita receita = new Receita();
        receita.setId(rs.getLong("receita_id"));
        receita.setCodigo(rs.getString("codigo"));
        receita.setNomeMedico(rs.getString("nome_medico"));

        Date dataEmissao = rs.getDate("data_emissao");
        receita.setDataEmissao(dataEmissao != null ? dataEmissao.toLocalDate() : null);

        Date dataEntrada = rs.getDate("data_entrada");
        receita.setDataEntrada(dataEntrada != null ? dataEntrada.toLocalDate() : null);

        Pedido pedido = new Pedido();
        pedido.setId(rs.getLong("pedido_id"));
        receita.setPedido(pedido);

        return receita;
    }
}

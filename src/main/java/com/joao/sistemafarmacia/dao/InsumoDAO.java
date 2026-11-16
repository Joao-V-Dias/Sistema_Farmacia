package com.joao.sistemafarmacia.dao;

import com.joao.sistemafarmacia.model.Insumo;
import com.joao.sistemafarmacia.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InsumoDAO {

    private final Connection conn;

    public InsumoDAO() {
        this.conn = Conexao.getInstance();
    }

    public Insumo salvar(Insumo insumo) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO insumo (nome, valor, quantidade, minimo) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, insumo.getNome());
            stmt.setDouble(2, insumo.getValor());
            stmt.setDouble(3, insumo.getQuantidade());
            stmt.setDouble(4, insumo.getMinimo());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                insumo.setId(rs.getLong(1));
            }
            rs.close();
            stmt.close();
            return insumo;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível salvar o insumo", e);
        }
    }

    public Insumo editar(Insumo insumo) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE insumo SET nome=?, valor=?, quantidade=?, minimo=? WHERE insumo_id=?");
            stmt.setString(1, insumo.getNome());
            stmt.setDouble(2, insumo.getValor());
            stmt.setDouble(3, insumo.getQuantidade());
            stmt.setDouble(4, insumo.getMinimo());
            stmt.setLong(5, insumo.getId());
            stmt.executeUpdate();
            stmt.close();
            return insumo;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível editar o insumo", e);
        }
    }

    public void darBaixa(Long id, double quantidade) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE insumo SET quantidade = quantidade - ? WHERE insumo_id=?");
            stmt.setDouble(1, quantidade);
            stmt.setLong(2, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível dar baixa no estoque do insumo", e);
        }
    }

    public void deletar(Long id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM insumo WHERE insumo_id=?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível deletar o insumo", e);
        }
    }

    public List<Insumo> buscarTodos() {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM insumo");
            ResultSet rs = stmt.executeQuery();
            return mapearLista(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível buscar os insumos", e);
        }
    }

    public List<Insumo> buscarPorNome(String nome) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM insumo WHERE nome ILIKE ?");
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            return mapearLista(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível buscar insumos pelo nome", e);
        }
    }

    public List<Insumo> buscarEstoqueMinimo() {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM insumo WHERE quantidade <= minimo");
            ResultSet rs = stmt.executeQuery();
            return mapearLista(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível buscar os insumos em estoque mínimo", e);
        }
    }

    private List<Insumo> mapearLista(ResultSet rs) throws SQLException {
        List<Insumo> insumos = new ArrayList<>();
        while (rs.next()) {
            insumos.add(mapear(rs));
        }
        return insumos;
    }

    private Insumo mapear(ResultSet rs) throws SQLException {
        Insumo insumo = new Insumo();
        insumo.setId(rs.getLong("insumo_id"));
        insumo.setNome(rs.getString("nome"));
        insumo.setValor(rs.getDouble("valor"));
        insumo.setQuantidade(rs.getDouble("quantidade"));
        insumo.setMinimo(rs.getDouble("minimo"));
        return insumo;
    }
}

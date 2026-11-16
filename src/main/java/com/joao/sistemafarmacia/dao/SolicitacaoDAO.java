package com.joao.sistemafarmacia.dao;

import com.joao.sistemafarmacia.model.Insumo;
import com.joao.sistemafarmacia.model.Solicitacao;
import com.joao.sistemafarmacia.util.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SolicitacaoDAO {

    private final Connection conn;

    public SolicitacaoDAO() {
        this.conn = Conexao.getInstance();
    }

    public Solicitacao salvar(Solicitacao solicitacao) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO solicitacao (insumo_id, quantidade, valor, data_solicitacao, status) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, solicitacao.getInsumo().getId());
            stmt.setDouble(2, solicitacao.getQuantidade());
            stmt.setDouble(3, solicitacao.getValor());
            stmt.setDate(4, solicitacao.getDataSolicitacao() != null ? Date.valueOf(solicitacao.getDataSolicitacao()) : null);
            stmt.setString(5, solicitacao.getStatus());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                solicitacao.setId(rs.getLong(1));
            }
            rs.close();
            stmt.close();
            return solicitacao;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível salvar a solicitação", e);
        }
    }

    public Solicitacao editar(Solicitacao solicitacao) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE solicitacao SET quantidade=?, valor=?, status=? WHERE solicitacao_id=?");
            stmt.setDouble(1, solicitacao.getQuantidade());
            stmt.setDouble(2, solicitacao.getValor());
            stmt.setString(3, solicitacao.getStatus());
            stmt.setLong(4, solicitacao.getId());
            stmt.executeUpdate();
            stmt.close();
            return solicitacao;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível editar a solicitação", e);
        }
    }

    public List<Solicitacao> buscarTodos() {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT s.*, i.nome, i.valor AS valor_insumo, i.quantidade AS quantidade_insumo, i.minimo FROM solicitacao s "
                    + "INNER JOIN insumo i ON i.insumo_id = s.insumo_id ORDER BY s.solicitacao_id DESC");
            ResultSet rs = stmt.executeQuery();
            return mapearLista(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível buscar as solicitações", e);
        }
    }

    private List<Solicitacao> mapearLista(ResultSet rs) throws SQLException {
        List<Solicitacao> solicitacoes = new ArrayList<>();
        while (rs.next()) {
            solicitacoes.add(mapear(rs));
        }
        return solicitacoes;
    }

    private Solicitacao mapear(ResultSet rs) throws SQLException {
        Insumo insumo = new Insumo();
        insumo.setId(rs.getLong("insumo_id"));
        insumo.setNome(rs.getString("nome"));
        insumo.setValor(rs.getDouble("valor_insumo"));
        insumo.setQuantidade(rs.getDouble("quantidade_insumo"));
        insumo.setMinimo(rs.getDouble("minimo"));

        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setId(rs.getLong("solicitacao_id"));
        solicitacao.setInsumo(insumo);
        solicitacao.setQuantidade(rs.getDouble("quantidade"));
        solicitacao.setValor(rs.getDouble("valor"));
        solicitacao.setStatus(rs.getString("status"));

        Date dataSolicitacao = rs.getDate("data_solicitacao");
        solicitacao.setDataSolicitacao(dataSolicitacao != null ? dataSolicitacao.toLocalDate() : null);

        return solicitacao;
    }
}

package com.joao.sistemafarmacia.dao;

import com.joao.sistemafarmacia.model.Insumo;
import com.joao.sistemafarmacia.model.Solicitacao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SolicitacaoDAO extends DAO<Solicitacao> {

    public SolicitacaoDAO(String CREATE, String READ, String UPDATE, String DELETE) {
        super(CREATE, READ, UPDATE, DELETE);
    }

    @Override
    protected void preencherPrepareStatement(PreparedStatement stmt, Solicitacao entidade) throws SQLException {
        stmt.setLong(1, entidade.getInsumo().getId());
        stmt.setDouble(2, entidade.getQuantidade());
        stmt.setDouble(3, entidade.getValor());
        stmt.setDate(4, entidade.getDataSolicitacao() != null ? Date.valueOf(entidade.getDataSolicitacao()) : null);
        stmt.setString(5, entidade.getStatus());
    }

    @Override
    protected Solicitacao mapear(ResultSet rs) throws SQLException {
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

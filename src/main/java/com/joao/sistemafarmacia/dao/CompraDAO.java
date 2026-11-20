package com.joao.sistemafarmacia.dao;

import com.joao.sistemafarmacia.model.Compra;
import com.joao.sistemafarmacia.model.Insumo;
import com.joao.sistemafarmacia.model.Solicitacao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompraDAO extends DAO<Compra> {

    private static final String CREATE = "INSERT INTO compra (solicitacao_id, insumo_id, cnpj_fornecedor, numero_nota_fiscal, quantidade, valor, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String READ = "SELECT c.*, i.nome, i.valor AS valor_insumo, i.quantidade AS quantidade_insumo, i.minimo FROM compra c "
            + "INNER JOIN insumo i ON i.insumo_id = c.insumo_id ORDER BY c.compra_id DESC";
    private static final String UPDATE = "UPDATE compra SET solicitacao_id=?, insumo_id=?, cnpj_fornecedor=?, numero_nota_fiscal=?, quantidade=?, valor=?, status=? WHERE compra_id=?";
    private static final String DELETE = "DELETE FROM compra WHERE compra_id=?";

    public CompraDAO() {
        super(CREATE, READ, UPDATE, DELETE);
    }

    @Override
    protected void preencherPrepareStatement(PreparedStatement stmt, Compra entidade) throws SQLException {
        stmt.setLong(1, entidade.getSolicitacao().getId());
        stmt.setLong(2, entidade.getInsumo().getId());
        stmt.setString(3, entidade.getCnpjFornecedor());
        stmt.setString(4, entidade.getNumeroNotaFiscal());
        stmt.setDouble(5, entidade.getQuantidade());
        stmt.setDouble(6, entidade.getValor());
        stmt.setString(7, entidade.getStatus());
    }

    @Override
    protected Compra mapear(ResultSet rs) throws SQLException {
        Insumo insumo = new Insumo();
        insumo.setId(rs.getLong("insumo_id"));
        insumo.setNome(rs.getString("nome"));
        insumo.setValor(rs.getDouble("valor_insumo"));
        insumo.setQuantidade(rs.getDouble("quantidade_insumo"));
        insumo.setMinimo(rs.getDouble("minimo"));

        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setId(rs.getLong("solicitacao_id"));

        Compra compra = new Compra();
        compra.setId(rs.getLong("compra_id"));
        compra.setSolicitacao(solicitacao);
        compra.setInsumo(insumo);
        compra.setCnpjFornecedor(rs.getString("cnpj_fornecedor"));
        compra.setNumeroNotaFiscal(rs.getString("numero_nota_fiscal"));
        compra.setQuantidade(rs.getDouble("quantidade"));
        compra.setValor(rs.getDouble("valor"));
        compra.setStatus(rs.getString("status"));
        return compra;
    }
}

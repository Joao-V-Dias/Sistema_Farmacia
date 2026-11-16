package com.joao.sistemafarmacia.dao;

import com.joao.sistemafarmacia.model.Compra;
import com.joao.sistemafarmacia.model.Insumo;
import com.joao.sistemafarmacia.model.Solicitacao;
import com.joao.sistemafarmacia.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO {

    private final Connection conn;

    public CompraDAO() {
        this.conn = Conexao.getInstance();
    }

    public Compra salvar(Compra compra) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO compra (solicitacao_id, insumo_id, cnpj_fornecedor, numero_nota_fiscal, quantidade, valor, status) VALUES (?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, compra.getSolicitacao().getId());
            stmt.setLong(2, compra.getInsumo().getId());
            stmt.setString(3, compra.getCnpjFornecedor());
            stmt.setString(4, compra.getNumeroNotaFiscal());
            stmt.setDouble(5, compra.getQuantidade());
            stmt.setDouble(6, compra.getValor());
            stmt.setString(7, compra.getStatus());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                compra.setId(rs.getLong(1));
            }
            rs.close();
            stmt.close();
            return compra;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível salvar a compra", e);
        }
    }

    public Compra editar(Compra compra) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE compra SET cnpj_fornecedor=?, numero_nota_fiscal=?, quantidade=?, valor=?, status=? WHERE compra_id=?");
            stmt.setString(1, compra.getCnpjFornecedor());
            stmt.setString(2, compra.getNumeroNotaFiscal());
            stmt.setDouble(3, compra.getQuantidade());
            stmt.setDouble(4, compra.getValor());
            stmt.setString(5, compra.getStatus());
            stmt.setLong(6, compra.getId());
            stmt.executeUpdate();
            stmt.close();
            return compra;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível editar a compra", e);
        }
    }

    public List<Compra> buscarTodos() {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT c.*, i.nome, i.valor AS valor_insumo, i.quantidade AS quantidade_insumo, i.minimo FROM compra c "
                    + "INNER JOIN insumo i ON i.insumo_id = c.insumo_id ORDER BY c.compra_id DESC");
            ResultSet rs = stmt.executeQuery();
            return mapearLista(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível buscar as compras", e);
        }
    }

    private List<Compra> mapearLista(ResultSet rs) throws SQLException {
        List<Compra> compras = new ArrayList<>();
        while (rs.next()) {
            compras.add(mapear(rs));
        }
        return compras;
    }

    private Compra mapear(ResultSet rs) throws SQLException {
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

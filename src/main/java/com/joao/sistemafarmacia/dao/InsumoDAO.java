package com.joao.sistemafarmacia.dao;

import com.joao.sistemafarmacia.model.Insumo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class InsumoDAO extends DAO<Insumo> {

    public InsumoDAO(String CREATE, String READ, String UPDATE, String DELETE) {
        super(CREATE, READ, UPDATE, DELETE);
    }

    @Override
    protected void preencherPrepareStatement(PreparedStatement stmt, Insumo entidade) throws SQLException {
        stmt.setString(1, entidade.getNome());
        stmt.setDouble(2, entidade.getValor());
        stmt.setDouble(3, entidade.getQuantidade());
        stmt.setDouble(4, entidade.getMinimo());
    }

    @Override
    protected Insumo mapear(ResultSet rs) throws SQLException {
        Insumo insumo = new Insumo();
        insumo.setId(rs.getLong("insumo_id"));
        insumo.setNome(rs.getString("nome"));
        insumo.setValor(rs.getDouble("valor"));
        insumo.setQuantidade(rs.getDouble("quantidade"));
        insumo.setMinimo(rs.getDouble("minimo"));
        return insumo;
    }

    public void darBaixa(Long id, double quantidade) {
        try {
            PreparedStatement stmt = CONN.prepareStatement("UPDATE insumo SET quantidade = quantidade - ? WHERE insumo_id=?");
            stmt.setDouble(1, quantidade);
            stmt.setLong(2, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("NAO FOI POSSIVEL DAR BAIXA NO ESTOQUE DO INSUMO", e);
        }
    }

    public List<Insumo> buscarPorNome(String nome) {
        try {
            PreparedStatement stmt = CONN.prepareStatement("SELECT * FROM insumo WHERE nome ILIKE ?");
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            return mapearLista(rs);
        } catch (SQLException e) {
            throw new RuntimeException("NAO FOI POSSIVEL BUSCAR INSUMOS PELO NOME", e);
        }
    }

    public List<Insumo> buscarEstoqueMinimo() {
        try {
            PreparedStatement stmt = CONN.prepareStatement("SELECT * FROM insumo WHERE quantidade <= minimo");
            ResultSet rs = stmt.executeQuery();
            return mapearLista(rs);
        } catch (SQLException e) {
            throw new RuntimeException("NAO FOI POSSIVEL BUSCAR OS INSUMOS EM ESTOQUE MINIMO", e);
        }
    }

}

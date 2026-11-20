package com.joao.sistemafarmacia.dao;

import com.joao.sistemafarmacia.model.Insumo;
import com.joao.sistemafarmacia.model.ItensReceita;
import com.joao.sistemafarmacia.model.Receita;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ItensReceitaDAO extends DAO<ItensReceita> {

    public ItensReceitaDAO(String CREATE, String READ, String UPDATE, String DELETE) {
        super(CREATE, READ, UPDATE, DELETE);
    }

    @Override
    protected void preencherPrepareStatement(PreparedStatement stmt, ItensReceita entidade) throws SQLException {
        stmt.setLong(1, entidade.getReceita().getId());
        stmt.setLong(2, entidade.getInsumo().getId());
        stmt.setDouble(3, entidade.getQuantidade());
    }

    @Override
    protected ItensReceita mapear(ResultSet rs) throws SQLException {
        Insumo insumo = new Insumo();
        insumo.setId(rs.getLong("insumo_id"));
        insumo.setNome(rs.getString("nome"));
        insumo.setValor(rs.getDouble("valor"));
        insumo.setQuantidade(rs.getDouble("quantidade"));
        insumo.setMinimo(rs.getDouble("minimo"));

        Receita receita = new Receita();
        receita.setId(rs.getLong("receita_id"));

        ItensReceita item = new ItensReceita();
        item.setId(rs.getLong("itens_receita_id"));
        item.setReceita(receita);
        item.setInsumo(insumo);
        item.setQuantidade(rs.getDouble("quantidade_item"));
        return item;
    }

    public void deletarPorReceita(Long receitaId) {
        try {
            PreparedStatement stmt = CONN.prepareStatement("DELETE FROM itens_receita WHERE receita_id=?");
            stmt.setLong(1, receitaId);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("NAO FOI POSSIVEL DELETAR OS ITENS DA RECEITA", e);
        }
    }

    public List<ItensReceita> buscarPorReceita(Long receitaId) {
        try {
            PreparedStatement stmt = CONN.prepareStatement(
                    "SELECT ir.itens_receita_id, ir.receita_id, ir.quantidade AS quantidade_item, i.* FROM itens_receita ir "
                    + "INNER JOIN insumo i ON i.insumo_id = ir.insumo_id WHERE ir.receita_id=?");
            stmt.setLong(1, receitaId);
            ResultSet rs = stmt.executeQuery();
            return mapearLista(rs);
        } catch (SQLException e) {
            throw new RuntimeException("NAO FOI POSSIVEL BUSCAR OS ITENS DA RECEITA", e);
        }
    }
}

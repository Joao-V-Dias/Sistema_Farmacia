package com.joao.sistemafarmacia.dao;

import com.joao.sistemafarmacia.model.Insumo;
import com.joao.sistemafarmacia.model.ItensReceita;
import com.joao.sistemafarmacia.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItensReceitaDAO {

    private final Connection conn;

    public ItensReceitaDAO() {
        this.conn = Conexao.getInstance();
    }

    public ItensReceita salvar(ItensReceita item, Long receitaId) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO itens_receita (receita_id, insumo_id, quantidade) VALUES (?, ?, ?)");
            stmt.setLong(1, receitaId);
            stmt.setLong(2, item.getInsumo().getId());
            stmt.setDouble(3, item.getQuantidade());
            stmt.executeUpdate();
            stmt.close();
            return item;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível salvar o item da receita", e);
        }
    }

    public void deletarPorReceita(Long receitaId) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM itens_receita WHERE receita_id=?");
            stmt.setLong(1, receitaId);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível deletar os itens da receita", e);
        }
    }

    public List<ItensReceita> buscarPorReceita(Long receitaId) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT ir.quantidade AS quantidade_item, i.* FROM itens_receita ir "
                    + "INNER JOIN insumo i ON i.insumo_id = ir.insumo_id WHERE ir.receita_id=?");
            stmt.setLong(1, receitaId);
            ResultSet rs = stmt.executeQuery();
            return mapearLista(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível buscar os itens da receita", e);
        }
    }

    private List<ItensReceita> mapearLista(ResultSet rs) throws SQLException {
        List<ItensReceita> itens = new ArrayList<>();
        while (rs.next()) {
            itens.add(mapear(rs));
        }
        return itens;
    }

    private ItensReceita mapear(ResultSet rs) throws SQLException {
        Insumo insumo = new Insumo();
        insumo.setId(rs.getLong("insumo_id"));
        insumo.setNome(rs.getString("nome"));
        insumo.setValor(rs.getDouble("valor"));
        insumo.setQuantidade(rs.getDouble("quantidade"));
        insumo.setMinimo(rs.getDouble("minimo"));

        ItensReceita item = new ItensReceita();
        item.setInsumo(insumo);
        item.setQuantidade(rs.getDouble("quantidade_item"));
        return item;
    }
}

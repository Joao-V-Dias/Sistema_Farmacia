package com.joao.sistemafarmacia.dao;

import com.joao.sistemafarmacia.model.Pedido;
import com.joao.sistemafarmacia.model.Receita;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReceitaDAO extends DAO<Receita> {

    public ReceitaDAO(String CREATE, String READ, String UPDATE, String DELETE) {
        super(CREATE, READ, UPDATE, DELETE);
    }

    @Override
    protected void preencherPrepareStatement(PreparedStatement stmt, Receita entidade) throws SQLException {
        stmt.setString(1, entidade.getCodigo());
        stmt.setString(2, entidade.getNomeMedico());
        stmt.setDate(3, entidade.getDataEmissao() != null ? Date.valueOf(entidade.getDataEmissao()) : null);
        stmt.setDate(4, entidade.getDataEntrada() != null ? Date.valueOf(entidade.getDataEntrada()) : null);
        stmt.setLong(5, entidade.getPedido().getId());
    }

    @Override
    protected Receita mapear(ResultSet rs) throws SQLException {
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

    public List<Receita> buscarPorNomeMedico(String nomeMedico) {
        try {
            PreparedStatement stmt = CONN.prepareStatement("SELECT * FROM receita WHERE nome_medico ILIKE ?");
            stmt.setString(1, "%" + nomeMedico + "%");
            ResultSet rs = stmt.executeQuery();
            return mapearLista(rs);
        } catch (SQLException e) {
            throw new RuntimeException("NAO FOI POSSIVEL BUSCAR RECEITAS PELO NOME DO MEDICO", e);
        }
    }

    public List<Receita> buscarPorPedido(Long pedidoId) {
        try {
            PreparedStatement stmt = CONN.prepareStatement("SELECT * FROM receita WHERE pedido_id=?");
            stmt.setLong(1, pedidoId);
            ResultSet rs = stmt.executeQuery();
            return mapearLista(rs);
        } catch (SQLException e) {
            throw new RuntimeException("NAO FOI POSSIVEL BUSCAR AS RECEITAS DO PEDIDO", e);
        }
    }
}

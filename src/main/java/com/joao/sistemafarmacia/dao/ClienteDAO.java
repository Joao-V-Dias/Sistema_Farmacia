package com.joao.sistemafarmacia.dao;

import com.joao.sistemafarmacia.model.Cliente;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClienteDAO extends DAO<Cliente> {

    public ClienteDAO(String CREATE, String READ, String UPDATE, String DELETE) {
        super(CREATE, READ, UPDATE, DELETE);
    }

    @Override
    protected void preencherPrepareStatement(PreparedStatement stmt, Cliente entidade) throws SQLException {
        stmt.setString(1, entidade.getNome());
        stmt.setString(2, entidade.getTelefone());
        stmt.setString(3, entidade.getRua());
        stmt.setString(4, entidade.getNumero());
        stmt.setString(5, entidade.getCpf());
        stmt.setDate(6, entidade.getDataNascimento() != null ? Date.valueOf(entidade.getDataNascimento()) : null);
    }

    @Override
    protected Cliente mapear(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getLong("cliente_id"));
        cliente.setNome(rs.getString("nome"));
        cliente.setCpf(rs.getString("cpf"));
        cliente.setTelefone(rs.getString("telefone"));
        cliente.setRua(rs.getString("rua"));
        cliente.setNumero(rs.getString("numero"));

        Date dataNascimento = rs.getDate("data_nascimento");
        cliente.setDataNascimento(dataNascimento != null ? dataNascimento.toLocalDate() : null);

        return cliente;
    }

    public List<Cliente> buscarPorCpf(String cpf) {
        try {
            PreparedStatement stmt = CONN.prepareStatement("SELECT * FROM cliente WHERE cpf ILIKE ?");
            stmt.setString(1, "%" + cpf + "%");
            ResultSet rs = stmt.executeQuery();
            return mapearLista(rs);
        } catch (SQLException e) {
            throw new RuntimeException("NAO FOI POSSIVEL BUSCAR CLIENTES PELO CPF", e);
        }
    }
}

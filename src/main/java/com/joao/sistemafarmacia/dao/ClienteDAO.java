package com.joao.sistemafarmacia.dao;

import com.joao.sistemafarmacia.model.Cliente;
import com.joao.sistemafarmacia.util.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private final Connection conn;

    public ClienteDAO() {
        this.conn = Conexao.getInstance();
    }

    public Cliente salvar(Cliente cliente) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO cliente (nome, telefone, rua, numero, cpf, data_nascimento) VALUES (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getRua());
            stmt.setString(4, cliente.getNumero());
            stmt.setString(5, cliente.getCpf());
            stmt.setDate(6, cliente.getDataNascimento() != null ? Date.valueOf(cliente.getDataNascimento()) : null);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                cliente.setId(rs.getLong(1));
            }
            rs.close();
            stmt.close();
            return cliente;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível salvar o cliente", e);
        }
    }

    public Cliente editar(Cliente cliente) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE cliente SET nome=?, cpf=?, telefone=?, rua=?, numero=?, data_nascimento=? WHERE cliente_id=?");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getRua());
            stmt.setString(5, cliente.getNumero());
            stmt.setDate(6, cliente.getDataNascimento() != null ? Date.valueOf(cliente.getDataNascimento()) : null);
            stmt.setLong(7, cliente.getId());
            stmt.executeUpdate();
            stmt.close();
            return cliente;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível editar o cliente", e);
        }
    }

    public void deletar(Long id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM cliente WHERE cliente_id=?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível deletar o cliente", e);
        }
    }

    public List<Cliente> buscarTodos() {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cliente");
            ResultSet rs = stmt.executeQuery();
            return mapearLista(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível buscar os clientes", e);
        }
    }

    public List<Cliente> buscarPorCpf(String cpf) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cliente WHERE cpf ILIKE ?");
            stmt.setString(1, "%" + cpf + "%");
            ResultSet rs = stmt.executeQuery();
            return mapearLista(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível buscar clientes pelo cpf", e);
        }
    }

    private List<Cliente> mapearLista(ResultSet rs) throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        while (rs.next()) {
            clientes.add(mapear(rs));
        }
        return clientes;
    }

    private Cliente mapear(ResultSet rs) throws SQLException {
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
}

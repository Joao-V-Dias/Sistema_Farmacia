package com.joao.sistemafarmacia.dao;

import com.joao.sistemafarmacia.model.Cliente;
import com.joao.sistemafarmacia.model.Pedido;
import com.joao.sistemafarmacia.util.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    private final Connection conn;

    public PedidoDAO() {
        this.conn = Conexao.getInstance();
    }

    public Pedido salvar(Pedido pedido) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO pedido (data_pedido, cliente_id, valor, status) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setDate(1, pedido.getDataPedido() != null ? Date.valueOf(pedido.getDataPedido()) : null);
            stmt.setLong(2, pedido.getCliente().getId());
            stmt.setDouble(3, pedido.getValor());
            stmt.setString(4, pedido.getStatus());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                pedido.setId(rs.getLong(1));
            }
            rs.close();
            stmt.close();
            return pedido;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível salvar o pedido", e);
        }
    }

    public Pedido editar(Pedido pedido) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE pedido SET data_pedido=?, cliente_id=?, valor=?, status=? WHERE pedido_id=?");
            stmt.setDate(1, pedido.getDataPedido() != null ? Date.valueOf(pedido.getDataPedido()) : null);
            stmt.setLong(2, pedido.getCliente().getId());
            stmt.setDouble(3, pedido.getValor());
            stmt.setString(4, pedido.getStatus());
            stmt.setLong(5, pedido.getId());
            stmt.executeUpdate();
            stmt.close();
            return pedido;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível editar o pedido", e);
        }
    }

    public void deletar(Long id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM pedido WHERE pedido_id=?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível deletar o pedido", e);
        }
    }

    public List<Pedido> buscarTodos() {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT p.*, c.nome, c.cpf, c.telefone, c.rua, c.numero, c.data_nascimento FROM pedido p "
                    + "INNER JOIN cliente c ON c.cliente_id = p.cliente_id");
            ResultSet rs = stmt.executeQuery();
            return mapearLista(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível buscar os pedidos", e);
        }
    }

    public List<Pedido> buscarPorStatus(String status) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT p.*, c.nome, c.cpf, c.telefone, c.rua, c.numero, c.data_nascimento FROM pedido p "
                    + "INNER JOIN cliente c ON c.cliente_id = p.cliente_id WHERE p.status ILIKE ?");
            stmt.setString(1, "%" + status + "%");
            ResultSet rs = stmt.executeQuery();
            return mapearLista(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível buscar pedidos pelo status", e);
        }
    }

    public List<Pedido> buscarPorCpf(String cpf) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT p.*, c.nome, c.cpf, c.telefone, c.rua, c.numero, c.data_nascimento FROM pedido p "
                    + "INNER JOIN cliente c ON c.cliente_id = p.cliente_id WHERE c.cpf ILIKE ?");
            stmt.setString(1, "%" + cpf + "%");
            ResultSet rs = stmt.executeQuery();
            return mapearLista(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível buscar pedidos pelo cpf", e);
        }
    }

    private List<Pedido> mapearLista(ResultSet rs) throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        while (rs.next()) {
            pedidos.add(mapear(rs));
        }
        return pedidos;
    }

    private Pedido mapear(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getLong("cliente_id"));
        cliente.setNome(rs.getString("nome"));
        cliente.setCpf(rs.getString("cpf"));
        cliente.setTelefone(rs.getString("telefone"));
        cliente.setRua(rs.getString("rua"));
        cliente.setNumero(rs.getString("numero"));

        Date dataNascimento = rs.getDate("data_nascimento");
        cliente.setDataNascimento(dataNascimento != null ? dataNascimento.toLocalDate() : null);

        Pedido pedido = new Pedido();
        pedido.setId(rs.getLong("pedido_id"));
        pedido.setCliente(cliente);
        pedido.setValor(rs.getDouble("valor"));
        pedido.setStatus(rs.getString("status"));

        Date dataPedido = rs.getDate("data_pedido");
        pedido.setDataPedido(dataPedido != null ? dataPedido.toLocalDate() : null);

        return pedido;
    }
}

package com.joao.sistemafarmacia.dao;

import com.joao.sistemafarmacia.model.Cliente;
import com.joao.sistemafarmacia.model.Pedido;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PedidoDAO extends DAO<Pedido> {

    private static final String CREATE = "INSERT INTO pedido (data_pedido, cliente_id, valor, status) VALUES (?, ?, ?, ?)";
    private static final String READ = "SELECT p.*, c.nome, c.cpf, c.telefone, c.rua, c.numero, c.data_nascimento FROM pedido p "
            + "INNER JOIN cliente c ON c.cliente_id = p.cliente_id";
    private static final String UPDATE = "UPDATE pedido SET data_pedido=?, cliente_id=?, valor=?, status=? WHERE pedido_id=?";
    private static final String DELETE = "DELETE FROM pedido WHERE pedido_id=?";

    public PedidoDAO() {
        super(CREATE, READ, UPDATE, DELETE);
    }

    @Override
    protected void preencherPrepareStatement(PreparedStatement stmt, Pedido entidade) throws SQLException {
        stmt.setDate(1, entidade.getDataPedido() != null ? Date.valueOf(entidade.getDataPedido()) : null);
        stmt.setLong(2, entidade.getCliente().getId());
        stmt.setDouble(3, entidade.getValor());
        stmt.setString(4, entidade.getStatus());
    }

    @Override
    protected Pedido mapear(ResultSet rs) throws SQLException {
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

    public List<Pedido> buscarPorStatus(String status) {
        try {
            PreparedStatement stmt = CONN.prepareStatement(
                    "SELECT p.*, c.nome, c.cpf, c.telefone, c.rua, c.numero, c.data_nascimento FROM pedido p "
                    + "INNER JOIN cliente c ON c.cliente_id = p.cliente_id WHERE p.status ILIKE ?");
            stmt.setString(1, "%" + status + "%");
            ResultSet rs = stmt.executeQuery();
            return mapearLista(rs);
        } catch (SQLException e) {
            throw new RuntimeException("NAO FOI POSSIVEL BUSCAR PEDIDOS PELO STATUS", e);
        }
    }

    public List<Pedido> buscarPorCpf(String cpf) {
        try {
            PreparedStatement stmt = CONN.prepareStatement(
                    "SELECT p.*, c.nome, c.cpf, c.telefone, c.rua, c.numero, c.data_nascimento FROM pedido p "
                    + "INNER JOIN cliente c ON c.cliente_id = p.cliente_id WHERE c.cpf ILIKE ?");
            stmt.setString(1, "%" + cpf + "%");
            ResultSet rs = stmt.executeQuery();
            return mapearLista(rs);
        } catch (SQLException e) {
            throw new RuntimeException("NAO FOI POSSIVEL BUSCAR PEDIDOS PELO CPF", e);
        }
    }
}

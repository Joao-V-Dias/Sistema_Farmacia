package com.joao.sistemafarmacia.service;

import com.joao.sistemafarmacia.dao.ClienteDAO;
import com.joao.sistemafarmacia.model.Cliente;
import java.util.List;

public class ClienteService {

    private final ClienteDAO clienteDAO;

    public ClienteService() {
        this.clienteDAO = new ClienteDAO();
    }

    public Cliente salvar(Cliente cliente) {
        return clienteDAO.salvar(cliente);
    }

    public Cliente editar(Cliente cliente) {
        return clienteDAO.editar(cliente);
    }

    public void deletar(Long id) {
        clienteDAO.deletar(id);
    }

    public List<Cliente> buscarClientes() {
        return clienteDAO.buscarTodos();
    }

    public List<Cliente> buscarClientes(String cpf) {
        return clienteDAO.buscarPorCpf(cpf);
    }
}

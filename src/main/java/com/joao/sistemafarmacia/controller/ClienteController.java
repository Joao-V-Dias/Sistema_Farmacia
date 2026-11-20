package com.joao.sistemafarmacia.controller;

import com.joao.sistemafarmacia.dao.ClienteDAO;
import com.joao.sistemafarmacia.dao.DAOFactory;
import com.joao.sistemafarmacia.model.Cliente;
import java.util.List;

public class ClienteController {

    private final ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = (ClienteDAO) DAOFactory.criar("cliente");
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

package com.joao.sistemafarmacia.controller;

import com.joao.sistemafarmacia.dao.ClienteDAO;
import com.joao.sistemafarmacia.model.Cliente;
import com.joao.sistemafarmacia.view.observer.Observer;
import java.util.ArrayList;
import java.util.List;

public class ClienteController implements Controller, Subject {

    private final ClienteDAO clienteDAO;
    private final List<Observer> observadores;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
        this.observadores = new ArrayList<>();
    }

    public Cliente salvar(Cliente cliente) {
        cliente = clienteDAO.salvar(cliente);
        notificarObservadores();
        return cliente;
    }

    public Cliente editar(Cliente cliente) {
        cliente = clienteDAO.editar(cliente);
        notificarObservadores();
        return cliente;
    }

    public void deletar(Long id) {
        clienteDAO.deletar(id);
        notificarObservadores();
    }

    public List<Cliente> buscarClientes() {
        return clienteDAO.buscarTodos();
    }

    public List<Cliente> buscarClientes(String cpf) {
        return clienteDAO.buscarPorCpf(cpf);
    }

    @Override
    public void adicionarObservador(Observer observer) {
        observadores.add(observer);
    }

    @Override
    public void notificarObservadores() {
        for (Observer observer : observadores) {
            observer.atualizar();
        }
    }
}

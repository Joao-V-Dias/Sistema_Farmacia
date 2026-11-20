package com.joao.sistemafarmacia.controller;

import com.joao.sistemafarmacia.dao.PedidoDAO;
import com.joao.sistemafarmacia.model.Pedido;
import com.joao.sistemafarmacia.model.Receita;
import com.joao.sistemafarmacia.view.observer.Observer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PedidoController implements Controller, Subject {

    private final PedidoDAO pedidoDAO;
    private final ReceitaController receitaController;
    private final List<Observer> observadores;

    public PedidoController() {
        this.pedidoDAO = new PedidoDAO();
        this.receitaController = (ReceitaController) ControllerFactory.criar("receita");
        this.observadores = new ArrayList<>();
    }

    public Pedido salvar(Pedido pedido) {
        if (pedido.getReceitas().isEmpty()) {
            throw new IllegalArgumentException("Adicione pelo menos uma receita no pedido");
        }
        for (Receita receita : pedido.getReceitas()) {
            if (receita.getInsumos().isEmpty()) {
                throw new IllegalArgumentException("Adicione pelo menos um insumo na receita " + receita.getCodigo());
            }
        }
        pedido.setDataPedido(LocalDate.now());
        pedido.setValor(calcularTotal(pedido.getReceitas()));
        pedido.setStatus("PENDENTE");

        pedido = pedidoDAO.salvar(pedido);
        for (Receita receita : pedido.getReceitas()) {
            receita.setPedido(pedido);
            receitaController.salvar(receita);
        }
        notificarObservadores();
        return pedido;
    }

    public Pedido editar(Pedido pedido) {
        pedido = pedidoDAO.editar(pedido);
        notificarObservadores();
        return pedido;
    }

    public void deletar(Long id) {
        pedidoDAO.deletar(id);
        notificarObservadores();
    }

    public List<Pedido> buscarPedidos() {
        return pedidoDAO.buscarTodos();
    }

    public List<Pedido> buscarPedidos(String status) {
        return pedidoDAO.buscarPorStatus(status);
    }

    public List<Pedido> buscarPedidosPorCpf(String cpf) {
        return pedidoDAO.buscarPorCpf(cpf);
    }

    public double calcularTotal(List<Receita> receitas) {
        double total = 0;
        for (Receita receita : receitas) {
            total += receitaController.calcularValor(receita);
        }
        return total;
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

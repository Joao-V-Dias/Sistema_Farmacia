package com.joao.sistemafarmacia.controller;

import com.joao.sistemafarmacia.dao.PedidoDAO;
import com.joao.sistemafarmacia.model.Pedido;
import com.joao.sistemafarmacia.model.Receita;
import java.time.LocalDate;
import java.util.List;

public class PedidoController {

    private final PedidoDAO pedidoDAO;
    private final ReceitaController receitaController;

    public PedidoController() {
        this.pedidoDAO = new PedidoDAO();
        this.receitaController = new ReceitaController();
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
        return pedido;
    }

    public Pedido editar(Pedido pedido) {
        return pedidoDAO.editar(pedido);
    }

    public void deletar(Long id) {
        pedidoDAO.deletar(id);
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
}

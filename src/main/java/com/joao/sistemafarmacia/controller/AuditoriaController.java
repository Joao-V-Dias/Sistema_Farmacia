package com.joao.sistemafarmacia.controller;

import com.joao.sistemafarmacia.model.Compra;
import com.joao.sistemafarmacia.model.Pedido;
import com.joao.sistemafarmacia.model.Receita;
import java.util.List;

public class AuditoriaController {

    private final CompraController compraController;
    private final ManipulacaoController manipulacaoController;

    public AuditoriaController() {
        this.compraController = new CompraController();
        this.manipulacaoController = new ManipulacaoController();
    }

    public List<Compra> buscarCompras() {
        return compraController.buscarCompras();
    }

    public Compra confirmarRecebimento(Compra compra) {
        return compraController.confirmarRecebimento(compra);
    }

    public Compra marcarDivergente(Compra compra) {
        return compraController.marcarDivergente(compra);
    }

    public List<Pedido> buscarPedidosManipulados() {
        return manipulacaoController.buscarPedidosManipulados();
    }

    public List<Receita> buscarReceitas(Long pedidoId) {
        return manipulacaoController.buscarReceitas(pedidoId);
    }

    public Pedido liberarEntrega(Pedido pedido) {
        return manipulacaoController.liberarEntrega(pedido);
    }

    public Pedido reprovarManipulacao(Pedido pedido) {
        return manipulacaoController.reprovar(pedido);
    }
}

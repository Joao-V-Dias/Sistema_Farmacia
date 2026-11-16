package com.joao.sistemafarmacia.service;

import com.joao.sistemafarmacia.model.Compra;
import com.joao.sistemafarmacia.model.Pedido;
import com.joao.sistemafarmacia.model.Receita;
import java.util.List;

public class AuditoriaService {

    private final CompraService compraService;
    private final ManipulacaoService manipulacaoService;

    public AuditoriaService() {
        this.compraService = new CompraService();
        this.manipulacaoService = new ManipulacaoService();
    }

    public List<Compra> buscarCompras() {
        return compraService.buscarCompras();
    }

    public Compra confirmarRecebimento(Compra compra) {
        return compraService.confirmarRecebimento(compra);
    }

    public Compra marcarDivergente(Compra compra) {
        return compraService.marcarDivergente(compra);
    }

    public List<Pedido> buscarPedidosManipulados() {
        return manipulacaoService.buscarPedidosManipulados();
    }

    public List<Receita> buscarReceitas(Long pedidoId) {
        return manipulacaoService.buscarReceitas(pedidoId);
    }

    public Pedido liberarEntrega(Pedido pedido) {
        return manipulacaoService.liberarEntrega(pedido);
    }

    public Pedido reprovarManipulacao(Pedido pedido) {
        return manipulacaoService.reprovar(pedido);
    }
}

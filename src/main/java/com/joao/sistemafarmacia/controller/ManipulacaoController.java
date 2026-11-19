package com.joao.sistemafarmacia.controller;

import com.joao.sistemafarmacia.model.ItensReceita;
import com.joao.sistemafarmacia.model.Pedido;
import com.joao.sistemafarmacia.model.Receita;
import java.util.List;

public class ManipulacaoController {

    private final PedidoController pedidoController;
    private final ReceitaController receitaController;
    private final InsumoController insumoController;

    public ManipulacaoController() {
        this.pedidoController = new PedidoController();
        this.receitaController = new ReceitaController();
        this.insumoController = new InsumoController();
    }

    public List<Pedido> buscarPedidos() {
        return pedidoController.buscarPedidos();
    }

    public List<Pedido> buscarPedidosManipulados() {
        return pedidoController.buscarPedidos("MANIPULADO");
    }

    public List<Receita> buscarReceitas(Long pedidoId) {
        return receitaController.buscarReceitas(pedidoId);
    }

    public Pedido manipular(Pedido pedido) {
        if (!pedido.getStatus().equals("PENDENTE")) {
            throw new IllegalArgumentException("Este pedido já foi manipulado");
        }
        List<Receita> receitas = receitaController.buscarReceitas(pedido.getId());
        validarEstoque(receitas);

        for (Receita receita : receitas) {
            for (ItensReceita item : receita.getInsumos()) {
                insumoController.darBaixa(item.getInsumo().getId(), item.getQuantidade());
            }
        }
        pedido.setStatus("MANIPULADO");
        return pedidoController.editar(pedido);
    }

    public Pedido liberarEntrega(Pedido pedido) {
        if (!pedido.getStatus().equals("MANIPULADO")) {
            throw new IllegalArgumentException("Este pedido já foi auditado");
        }
        pedido.setStatus("LIBERADO PARA ENTREGA");
        return pedidoController.editar(pedido);
    }

    public Pedido reprovar(Pedido pedido) {
        if (!pedido.getStatus().equals("MANIPULADO")) {
            throw new IllegalArgumentException("Este pedido já foi auditado");
        }
        pedido.setStatus("REPROVADO");
        return pedidoController.editar(pedido);
    }

    private void validarEstoque(List<Receita> receitas) {
        for (Receita receita : receitas) {
            for (ItensReceita item : receita.getInsumos()) {
                if (calcularQuantidadeInsumo(receitas, item.getInsumo().getId()) > item.getInsumo().getQuantidade()) {
                    throw new IllegalArgumentException("Estoque insuficiente de " + item.getInsumo().getNome());
                }
            }
        }
    }

    private double calcularQuantidadeInsumo(List<Receita> receitas, Long insumoId) {
        double quantidade = 0;
        for (Receita receita : receitas) {
            for (ItensReceita item : receita.getInsumos()) {
                if (item.getInsumo().getId().equals(insumoId)) {
                    quantidade += item.getQuantidade();
                }
            }
        }
        return quantidade;
    }
}

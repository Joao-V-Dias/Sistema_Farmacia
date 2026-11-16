package com.joao.sistemafarmacia.service;

import com.joao.sistemafarmacia.model.ItensReceita;
import com.joao.sistemafarmacia.model.Pedido;
import com.joao.sistemafarmacia.model.Receita;
import java.util.List;

public class ManipulacaoService {

    private final PedidoService pedidoService;
    private final ReceitaService receitaService;
    private final InsumoService insumoService;

    public ManipulacaoService() {
        this.pedidoService = new PedidoService();
        this.receitaService = new ReceitaService();
        this.insumoService = new InsumoService();
    }

    public List<Pedido> buscarPedidos() {
        return pedidoService.buscarPedidos();
    }

    public List<Pedido> buscarPedidosManipulados() {
        return pedidoService.buscarPedidos("MANIPULADO");
    }

    public List<Receita> buscarReceitas(Long pedidoId) {
        return receitaService.buscarReceitas(pedidoId);
    }

    public Pedido manipular(Pedido pedido) {
        if (!pedido.getStatus().equals("PENDENTE")) {
            throw new IllegalArgumentException("Este pedido já foi manipulado");
        }
        List<Receita> receitas = receitaService.buscarReceitas(pedido.getId());
        validarEstoque(receitas);

        for (Receita receita : receitas) {
            for (ItensReceita item : receita.getInsumos()) {
                insumoService.darBaixa(item.getInsumo().getId(), item.getQuantidade());
            }
        }
        pedido.setStatus("MANIPULADO");
        return pedidoService.editar(pedido);
    }

    public Pedido liberarEntrega(Pedido pedido) {
        if (!pedido.getStatus().equals("MANIPULADO")) {
            throw new IllegalArgumentException("Este pedido já foi auditado");
        }
        pedido.setStatus("LIBERADO PARA ENTREGA");
        return pedidoService.editar(pedido);
    }

    public Pedido reprovar(Pedido pedido) {
        if (!pedido.getStatus().equals("MANIPULADO")) {
            throw new IllegalArgumentException("Este pedido já foi auditado");
        }
        pedido.setStatus("REPROVADO");
        return pedidoService.editar(pedido);
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

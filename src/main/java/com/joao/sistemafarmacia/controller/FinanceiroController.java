package com.joao.sistemafarmacia.controller;

import com.joao.sistemafarmacia.dao.FinanceiroDAO;
import com.joao.sistemafarmacia.model.Compra;
import com.joao.sistemafarmacia.model.Solicitacao;
import java.util.List;

public class FinanceiroController {

    private final FinanceiroDAO financeiroDAO;
    private final SolicitacaoController solicitacaoController;
    private final CompraController compraController;

    public FinanceiroController() {
        this.financeiroDAO = new FinanceiroDAO();
        this.solicitacaoController = new SolicitacaoController();
        this.compraController = new CompraController();
    }

    public double buscarValorEstoque() {
        return financeiroDAO.buscarValorEstoque();
    }

    public List<Solicitacao> buscarSolicitacoes() {
        return solicitacaoController.buscarSolicitacoes();
    }

    public double calcularTotalSolicitacoes(List<Solicitacao> solicitacoes) {
        double total = 0;
        for (Solicitacao solicitacao : solicitacoes) {
            total += solicitacao.getValor();
        }
        return total;
    }

    public List<Compra> buscarCompras() {
        return compraController.buscarCompras();
    }

    public double calcularTotalCompras(List<Compra> compras) {
        double total = 0;
        for (Compra compra : compras) {
            total += compra.getValor();
        }
        return total;
    }
}

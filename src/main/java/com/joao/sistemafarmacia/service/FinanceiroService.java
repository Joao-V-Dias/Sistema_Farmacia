package com.joao.sistemafarmacia.service;

import com.joao.sistemafarmacia.dao.FinanceiroDAO;
import com.joao.sistemafarmacia.model.Compra;
import com.joao.sistemafarmacia.model.Solicitacao;
import java.util.List;

public class FinanceiroService {

    private final FinanceiroDAO financeiroDAO;
    private final SolicitacaoService solicitacaoService;
    private final CompraService compraService;

    public FinanceiroService() {
        this.financeiroDAO = new FinanceiroDAO();
        this.solicitacaoService = new SolicitacaoService();
        this.compraService = new CompraService();
    }

    public double buscarValorEstoque() {
        return financeiroDAO.buscarValorEstoque();
    }

    public List<Solicitacao> buscarSolicitacoes() {
        return solicitacaoService.buscarSolicitacoes();
    }

    public double calcularTotalSolicitacoes(List<Solicitacao> solicitacoes) {
        double total = 0;
        for (Solicitacao solicitacao : solicitacoes) {
            total += solicitacao.getValor();
        }
        return total;
    }

    public List<Compra> buscarCompras() {
        return compraService.buscarCompras();
    }

    public double calcularTotalCompras(List<Compra> compras) {
        double total = 0;
        for (Compra compra : compras) {
            total += compra.getValor();
        }
        return total;
    }
}

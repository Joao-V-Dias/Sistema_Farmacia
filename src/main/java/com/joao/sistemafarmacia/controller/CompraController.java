package com.joao.sistemafarmacia.controller;

import com.joao.sistemafarmacia.dao.CompraDAO;
import com.joao.sistemafarmacia.model.Compra;
import com.joao.sistemafarmacia.model.Insumo;
import java.util.List;

public class CompraController {

    private final CompraDAO compraDAO;
    private final SolicitacaoController solicitacaoController;
    private final InsumoController insumoController;

    public CompraController() {
        this.compraDAO = new CompraDAO();
        this.solicitacaoController = new SolicitacaoController();
        this.insumoController = new InsumoController();
    }

    public Compra salvar(Compra compra) {
        if (!compra.getSolicitacao().getStatus().equals("PENDENTE")) {
            throw new IllegalArgumentException("Esta solicitação já foi comprada");
        }
        if (compra.getQuantidade() <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero");
        }
        compra.setInsumo(compra.getSolicitacao().getInsumo());
        compra.setStatus("AGUARDANDO RECEBIMENTO");
        compra = compraDAO.salvar(compra);

        compra.getSolicitacao().setStatus("COMPRADA");
        solicitacaoController.editar(compra.getSolicitacao());
        return compra;
    }

    public List<Compra> buscarCompras() {
        return compraDAO.buscarTodos();
    }

    public Compra confirmarRecebimento(Compra compra) {
        if (!compra.getStatus().equals("AGUARDANDO RECEBIMENTO")) {
            throw new IllegalArgumentException("Esta compra já foi auditada");
        }
        Insumo insumo = compra.getInsumo();
        insumo.setQuantidade(insumo.getQuantidade() + compra.getQuantidade());
        insumoController.editar(insumo);

        compra.setStatus("RECEBIDA");
        return compraDAO.editar(compra);
    }

    public Compra marcarDivergente(Compra compra) {
        if (!compra.getStatus().equals("AGUARDANDO RECEBIMENTO")) {
            throw new IllegalArgumentException("Esta compra já foi auditada");
        }
        compra.setStatus("DIVERGENTE");
        return compraDAO.editar(compra);
    }
}

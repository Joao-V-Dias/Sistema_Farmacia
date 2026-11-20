package com.joao.sistemafarmacia.controller;

import com.joao.sistemafarmacia.dao.CompraDAO;
import com.joao.sistemafarmacia.model.Compra;
import com.joao.sistemafarmacia.model.Insumo;
import com.joao.sistemafarmacia.view.observer.Observer;
import java.util.ArrayList;
import java.util.List;

public class CompraController implements Controller, Subject {

    private final CompraDAO compraDAO;
    private final SolicitacaoController solicitacaoController;
    private final InsumoController insumoController;
    private final List<Observer> observadores;

    public CompraController() {
        this.compraDAO = new CompraDAO();
        this.solicitacaoController = (SolicitacaoController) ControllerFactory.criar("solicitacao");
        this.insumoController = (InsumoController) ControllerFactory.criar("insumo");
        this.observadores = new ArrayList<>();
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
        notificarObservadores();
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
        compra = compraDAO.editar(compra);
        notificarObservadores();
        return compra;
    }

    public Compra marcarDivergente(Compra compra) {
        if (!compra.getStatus().equals("AGUARDANDO RECEBIMENTO")) {
            throw new IllegalArgumentException("Esta compra já foi auditada");
        }
        compra.setStatus("DIVERGENTE");
        compra = compraDAO.editar(compra);
        notificarObservadores();
        return compra;
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

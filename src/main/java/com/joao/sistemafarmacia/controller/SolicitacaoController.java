package com.joao.sistemafarmacia.controller;

import com.joao.sistemafarmacia.dao.SolicitacaoDAO;
import com.joao.sistemafarmacia.model.Solicitacao;
import com.joao.sistemafarmacia.view.observer.Observer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SolicitacaoController implements Controller, Subject {

    private final SolicitacaoDAO solicitacaoDAO;
    private final List<Observer> observadores;

    public SolicitacaoController() {
        this.solicitacaoDAO = new SolicitacaoDAO();
        this.observadores = new ArrayList<>();
    }

    public Solicitacao salvar(Solicitacao solicitacao) {
        if (solicitacao.getQuantidade() <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero");
        }
        solicitacao.setValor(solicitacao.getInsumo().getValor() * solicitacao.getQuantidade());
        solicitacao.setDataSolicitacao(LocalDate.now());
        solicitacao.setStatus("PENDENTE");
        solicitacao = solicitacaoDAO.salvar(solicitacao);
        notificarObservadores();
        return solicitacao;
    }

    public Solicitacao editar(Solicitacao solicitacao) {
        solicitacao = solicitacaoDAO.editar(solicitacao);
        notificarObservadores();
        return solicitacao;
    }

    public List<Solicitacao> buscarSolicitacoes() {
        return solicitacaoDAO.buscarTodos();
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

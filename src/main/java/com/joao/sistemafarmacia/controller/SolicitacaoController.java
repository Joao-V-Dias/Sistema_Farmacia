package com.joao.sistemafarmacia.controller;

import com.joao.sistemafarmacia.dao.SolicitacaoDAO;
import com.joao.sistemafarmacia.dao.DAOFactory;
import com.joao.sistemafarmacia.model.Solicitacao;
import java.time.LocalDate;
import java.util.List;

public class SolicitacaoController {

    private final SolicitacaoDAO solicitacaoDAO;

    public SolicitacaoController() {
        this.solicitacaoDAO = (SolicitacaoDAO) DAOFactory.criar("solicitacao");
    }

    public Solicitacao salvar(Solicitacao solicitacao) {
        if (solicitacao.getQuantidade() <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero");
        }
        solicitacao.setValor(solicitacao.getInsumo().getValor() * solicitacao.getQuantidade());
        solicitacao.setDataSolicitacao(LocalDate.now());
        solicitacao.setStatus("PENDENTE");
        return solicitacaoDAO.salvar(solicitacao);
    }

    public Solicitacao editar(Solicitacao solicitacao) {
        return solicitacaoDAO.editar(solicitacao);
    }

    public List<Solicitacao> buscarSolicitacoes() {
        return solicitacaoDAO.buscarTodos();
    }
}

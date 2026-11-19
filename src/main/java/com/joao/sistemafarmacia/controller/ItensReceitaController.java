package com.joao.sistemafarmacia.controller;

import com.joao.sistemafarmacia.dao.ItensReceitaDAO;
import com.joao.sistemafarmacia.model.ItensReceita;
import java.util.List;

public class ItensReceitaController {

    private final ItensReceitaDAO itensReceitaDAO;

    public ItensReceitaController() {
        this.itensReceitaDAO = new ItensReceitaDAO();
    }

    public ItensReceita salvar(ItensReceita item) {
        return itensReceitaDAO.salvar(item);
    }

    public void deletarPorReceita(Long receitaId) {
        itensReceitaDAO.deletarPorReceita(receitaId);
    }

    public List<ItensReceita> buscarItens(Long receitaId) {
        return itensReceitaDAO.buscarPorReceita(receitaId);
    }
}

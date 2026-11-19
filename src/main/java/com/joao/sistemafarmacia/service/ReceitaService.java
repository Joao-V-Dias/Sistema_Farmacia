package com.joao.sistemafarmacia.service;

import com.joao.sistemafarmacia.dao.ReceitaDAO;
import com.joao.sistemafarmacia.model.ItensReceita;
import com.joao.sistemafarmacia.model.Receita;
import java.util.List;

public class ReceitaService {

    private final ReceitaDAO receitaDAO;
    private final ItensReceitaService itensReceitaService;

    public ReceitaService() {
        this.receitaDAO = new ReceitaDAO();
        this.itensReceitaService = new ItensReceitaService();
    }

    public Receita salvar(Receita receita) {
        receita = receitaDAO.salvar(receita);
        for (ItensReceita item : receita.getInsumos()) {
            item.setReceita(receita);
            itensReceitaService.salvar(item);
        }
        return receita;
    }

    public Receita editar(Receita receita) {
        receita = receitaDAO.editar(receita);
        itensReceitaService.deletarPorReceita(receita.getId());
        for (ItensReceita item : receita.getInsumos()) {
            item.setReceita(receita);
            itensReceitaService.salvar(item);
        }
        return receita;
    }

    public void deletar(Long id) {
        itensReceitaService.deletarPorReceita(id);
        receitaDAO.deletar(id);
    }

    public List<Receita> buscarReceitas() {
        return carregarItens(receitaDAO.buscarTodos());
    }

    public List<Receita> buscarReceitas(String nomeMedico) {
        return carregarItens(receitaDAO.buscarPorNomeMedico(nomeMedico));
    }

    public List<Receita> buscarReceitas(Long pedidoId) {
        return carregarItens(receitaDAO.buscarPorPedido(pedidoId));
    }

    public double calcularValor(Receita receita) {
        double valor = 0;
        for (ItensReceita item : receita.getInsumos()) {
            valor += item.getInsumo().getValor() * item.getQuantidade();
        }
        return valor;
    }

    private List<Receita> carregarItens(List<Receita> receitas) {
        for (Receita receita : receitas) {
            receita.setInsumos(itensReceitaService.buscarItens(receita.getId()));
        }
        return receitas;
    }
}

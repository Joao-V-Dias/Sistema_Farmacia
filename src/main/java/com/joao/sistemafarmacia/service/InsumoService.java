package com.joao.sistemafarmacia.service;

import com.joao.sistemafarmacia.dao.InsumoDAO;
import com.joao.sistemafarmacia.model.Insumo;
import java.util.List;

public class InsumoService {

    private final InsumoDAO insumoDAO;

    public InsumoService() {
        this.insumoDAO = new InsumoDAO();
    }

    public Insumo salvar(Insumo insumo) {
        return insumoDAO.salvar(insumo);
    }

    public Insumo editar(Insumo insumo) {
        return insumoDAO.editar(insumo);
    }

    public void darBaixa(Long id, double quantidade) {
        insumoDAO.darBaixa(id, quantidade);
    }

    public void deletar(Long id) {
        insumoDAO.deletar(id);
    }

    public List<Insumo> buscarInsumos() {
        return insumoDAO.buscarTodos();
    }

    public List<Insumo> buscarInsumos(String nome) {
        return insumoDAO.buscarPorNome(nome);
    }

    public List<Insumo> buscarInsumosEstoqueMinimo() {
        return insumoDAO.buscarEstoqueMinimo();
    }
}

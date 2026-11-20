package com.joao.sistemafarmacia.controller;

import com.joao.sistemafarmacia.dao.InsumoDAO;
import com.joao.sistemafarmacia.model.Insumo;
import com.joao.sistemafarmacia.view.observer.Observer;
import java.util.ArrayList;
import java.util.List;

public class InsumoController implements Controller, Subject {

    private final InsumoDAO insumoDAO;
    private final List<Observer> observadores;

    public InsumoController() {
        this.insumoDAO = new InsumoDAO();
        this.observadores = new ArrayList<>();
    }

    public Insumo salvar(Insumo insumo) {
        insumo = insumoDAO.salvar(insumo);
        notificarObservadores();
        return insumo;
    }

    public Insumo editar(Insumo insumo) {
        insumo = insumoDAO.editar(insumo);
        notificarObservadores();
        return insumo;
    }

    public void darBaixa(Long id, double quantidade) {
        insumoDAO.darBaixa(id, quantidade);
        notificarObservadores();
    }

    public void deletar(Long id) {
        insumoDAO.deletar(id);
        notificarObservadores();
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

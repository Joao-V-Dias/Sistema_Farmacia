package com.joao.sistemafarmacia.controller;

import com.joao.sistemafarmacia.view.observer.Observer;

public interface Subject {
    void adicionarObservador(Observer observer);
    void notificarObservadores();
}

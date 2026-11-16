package com.joao.sistemafarmacia.model;

import java.time.LocalDate;
import java.util.List;

public class Cliente extends Pessoa {
    private String rua;
    private String numero;
    private List<Pedido> pedidos;

    public Cliente() {
    }

    public Cliente(String nome, String cpf, String telefone, LocalDate dataNascimento, String rua, String numero) {
        super(nome, cpf, telefone, dataNascimento);
        setRua(rua);
        setNumero(numero);
    }

    public Cliente(Long id, String nome, String cpf, String telefone, LocalDate dataNascimento, String rua, String numero) {
        super(id, nome, cpf, telefone, dataNascimento);
        setRua(rua);
        setNumero(numero);
    }

    public void setRua(String rua) {
        this.rua = rua.toUpperCase();
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
}

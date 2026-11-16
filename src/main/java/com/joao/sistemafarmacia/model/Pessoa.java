package com.joao.sistemafarmacia.model;

import java.time.LocalDate;

public abstract class Pessoa {
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;

    public Pessoa() {
    }

    public Pessoa(String nome, String cpf, String telefone, LocalDate dataNascimento) {
        setNome(nome);
        setCpf(cpf);
        setTelefone(telefone);
        setDataNascimento(dataNascimento);
    }

    public Pessoa(Long id, String nome, String cpf, String telefone, LocalDate dataNascimento) {
        this.id = id;
        setNome(nome);
        setCpf(cpf);
        setTelefone(telefone);
        setDataNascimento(dataNascimento);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    public void setCpf(String cpf) {
        if(cpf.length() > 11){
            throw new IllegalArgumentException("CPF inválido");
        }
        this.cpf = cpf;
    }

    public void setTelefone(String telefone) {
        if(telefone.length() > 11){
            throw new IllegalArgumentException("Telefone inválido");
        }
        this.telefone = telefone;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento != null && dataNascimento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de nascimento não pode ser no futuro");
        }
        this.dataNascimento = dataNascimento;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
}

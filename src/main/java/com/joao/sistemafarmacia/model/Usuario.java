package com.joao.sistemafarmacia.model;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public final class Usuario extends Pessoa {

    private String email;
    private String senha;
    private String cargo;

    public Usuario(Long id, String nome, String cpf, String telefone, LocalDate dataNascimento, String email, String cargo) throws AddressException {
        super(id, nome, cpf, telefone, dataNascimento);
        setEmail(email);
        setCargo(cargo);
    }

    public Usuario() {
    }

    public Usuario(String email, String senha, String nome, String cpf, String telefone, LocalDate dataNascimento, String cargo) throws AddressException, NoSuchAlgorithmException {
        super(nome, cpf, telefone, dataNascimento);
        setEmail(email);
        setSenha(senha);
        setCargo(cargo);
    }

    public void setEmail(String email) throws AddressException {
        if (email == null || email.isBlank()) {
            throw new AddressException("O email não pode ser nulo ou vazio");
        }
        InternetAddress addr = new InternetAddress(email);
        addr.validate();
        this.email = email;
    }

    public void setSenha(String senha) {
        if(senha.length() < 8){
            throw new IllegalArgumentException("A senha deve ter no mínimo 8 caracteres");
        }
        this.senha = senha;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo.toUpperCase();
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getCargo() {
        return cargo;
    }
}

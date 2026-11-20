package com.joao.sistemafarmacia.controller;

import com.joao.sistemafarmacia.dao.UsuarioDAO;
import com.joao.sistemafarmacia.model.Usuario;
import com.joao.sistemafarmacia.util.Hash;
import com.joao.sistemafarmacia.view.observer.Observer;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.mail.internet.AddressException;

public class UsuarioController implements Controller, Subject {

    private final UsuarioDAO usuarioDAO;
    private final List<Observer> observadores;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
        this.observadores = new ArrayList<>();
    }

    public Usuario salvar(Usuario usuario) throws NoSuchAlgorithmException {
        usuario.setSenha(Hash.gerarHash(usuario.getSenha()));
        usuario = usuarioDAO.salvar(usuario);
        notificarObservadores();
        return usuario;
    }

    public Usuario editar(Usuario usuario) throws NoSuchAlgorithmException {
        usuario.setSenha(Hash.gerarHash(usuario.getSenha()));
        usuario = usuarioDAO.editar(usuario);
        notificarObservadores();
        return usuario;
    }

    public void deletar(Long id) {
        usuarioDAO.deletar(id);
        notificarObservadores();
    }

    public List<Usuario> buscarUsuarios() {
        return usuarioDAO.buscarTodos();
    }

    public List<Usuario> buscarUsuarios(String nome) {
        return usuarioDAO.buscarPorNome(nome);
    }

    public Boolean fazerLogin(Usuario usuario) throws NoSuchAlgorithmException {
        Usuario usuarioEncontrado = usuarioDAO.buscarPorEmail(usuario.getEmail());
        return usuarioEncontrado != null
                && Hash.validarSenha(usuario.getSenha(), usuarioEncontrado.getSenha());
    }

    public Usuario criaPrimeiroUsuario() throws AddressException, NoSuchAlgorithmException {
        return new Usuario("adm@gmail.com", "123Mudar", "Joao", "12345678910", "34999999999", null, "ADMINISTRADOR");
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

package com.joao.sistemafarmacia.controller;

import com.joao.sistemafarmacia.dao.UsuarioDAO;
import com.joao.sistemafarmacia.model.Usuario;
import com.joao.sistemafarmacia.util.Hash;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.mail.internet.AddressException;

public class UsuarioController {

    private final UsuarioDAO usuarioDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public Usuario salvar(Usuario usuario) throws NoSuchAlgorithmException {
        usuario.setSenha(Hash.gerarHash(usuario.getSenha()));
        return usuarioDAO.salvar(usuario);
    }

    public Usuario editar(Usuario usuario) throws NoSuchAlgorithmException {
        usuario.setSenha(Hash.gerarHash(usuario.getSenha()));
        return usuarioDAO.editar(usuario);
    }

    public void deletar(Long id) {
        usuarioDAO.deletar(id);
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
}

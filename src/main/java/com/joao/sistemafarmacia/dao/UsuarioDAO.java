package com.joao.sistemafarmacia.dao;

import com.joao.sistemafarmacia.model.Usuario;
import com.joao.sistemafarmacia.util.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.mail.internet.AddressException;

public class UsuarioDAO {

    private final Connection conn;

    public UsuarioDAO() {
        this.conn = Conexao.getInstance();
    }

    public Usuario salvar(Usuario usuario) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO usuario (email, nome, senha, telefone, cpf, data_nascimento, cargo) VALUES (?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTelefone());
            stmt.setString(5, usuario.getCpf());
            stmt.setDate(6, usuario.getDataNascimento() != null ? Date.valueOf(usuario.getDataNascimento()) : null);
            stmt.setString(7, usuario.getCargo());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                usuario.setId(rs.getLong(1));
            }
            rs.close();
            stmt.close();
            return usuario;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível salvar o usuário", e);
        }
    }

    public Usuario editar(Usuario usuario) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE usuario SET nome=?, senha=?, cpf=?, telefone=?, data_nascimento=?, cargo=? WHERE usuario_id=?");
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getCpf());
            stmt.setString(4, usuario.getTelefone());
            stmt.setDate(5, usuario.getDataNascimento() != null ? Date.valueOf(usuario.getDataNascimento()) : null);
            stmt.setString(6, usuario.getCargo());
            stmt.setLong(7, usuario.getId());
            stmt.executeUpdate();
            stmt.close();
            return usuario;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível editar o usuário", e);
        }
    }

    public void deletar(Long id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM usuario WHERE usuario_id=?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível deletar o usuário", e);
        }
    }

    public List<Usuario> buscarTodos() {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT usuario_id, nome, email, senha, cpf, telefone, data_nascimento, cargo FROM usuario");
            ResultSet rs = stmt.executeQuery();
            return mapearLista(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível buscar os usuários", e);
        }
    }

    public List<Usuario> buscarPorNome(String nome) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuario WHERE nome ILIKE ?");
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            return mapearLista(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível buscar usuários pelo nome", e);
        }
    }

    public Usuario buscarPorEmail(String email) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuario WHERE email=?");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? mapear(rs) : null;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível buscar o usuário pelo email", e);
        }
    }

    private List<Usuario> mapearLista(ResultSet rs) throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        while (rs.next()) {
            usuarios.add(mapear(rs));
        }
        return usuarios;
    }

    private Usuario mapear(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getLong("usuario_id"));
        usuario.setNome(rs.getString("nome"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setCpf(rs.getString("cpf"));
        usuario.setTelefone(rs.getString("telefone"));
        usuario.setCargo(rs.getString("cargo"));

        Date dataNascimento = rs.getDate("data_nascimento");
        usuario.setDataNascimento(dataNascimento != null ? dataNascimento.toLocalDate() : null);

        try {
            usuario.setEmail(rs.getString("email"));
        } catch (AddressException e) {
            throw new RuntimeException("Usuário com email inválido no banco de dados", e);
        }
        return usuario;
    }
}

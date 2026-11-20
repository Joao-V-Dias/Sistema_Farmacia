package com.joao.sistemafarmacia.dao;

import com.joao.sistemafarmacia.model.Usuario;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.mail.internet.AddressException;

public class UsuarioDAO extends DAO<Usuario> {

    public UsuarioDAO(String CREATE, String READ, String UPDATE, String DELETE) {
        super(CREATE, READ, UPDATE, DELETE);
    }

    @Override
    protected void preencherPrepareStatement(PreparedStatement stmt, Usuario entidade) throws SQLException {
        stmt.setString(1, entidade.getEmail());
        stmt.setString(2, entidade.getNome());
        stmt.setString(3, entidade.getSenha());
        stmt.setString(4, entidade.getTelefone());
        stmt.setString(5, entidade.getCpf());
        stmt.setDate(6, entidade.getDataNascimento() != null ? Date.valueOf(entidade.getDataNascimento()) : null);
        stmt.setString(7, entidade.getCargo());
    }

    @Override
    protected Usuario mapear(ResultSet rs) throws SQLException {
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
            throw new RuntimeException("USUARIO COM EMAIL INVALIDO NO BANCO DE DADOS", e);
        }
        return usuario;
    }

    public Usuario buscarPorEmail(String email) {
        try {
            PreparedStatement stmt = CONN.prepareStatement("SELECT * FROM usuario WHERE email=?");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? mapear(rs) : null;
        } catch (SQLException e) {
            throw new RuntimeException("NAO FOI POSSIVEL BUSCAR O USUARIO PELO EMAIL", e);
        }
    }

    public List<Usuario> buscarPorNome(String nome) {
        try {
            PreparedStatement stmt = CONN.prepareStatement("SELECT * FROM usuario WHERE nome ILIKE ?");
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            return mapearLista(rs);
        } catch (SQLException e) {
            throw new RuntimeException("NAO FOI POSSIVEL BUSCAR USUARIOS PELO NOME", e);
        }
    }

}

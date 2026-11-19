/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joao.sistemafarmacia.dao;

import com.joao.sistemafarmacia.model.Entidade;
import com.joao.sistemafarmacia.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joao
 */
public abstract class DAO<T extends Entidade> {

    protected final Connection CONN;
    
    private final String CREATE;
    private final String UPDATE;
    private final String DELETE;
    private final String READ;

    public DAO(String CREATE, String READ, String UPDATE, String DELETE) {
        this.CONN = Conexao.getInstance();
        this.CREATE = CREATE;
        this.READ = READ;
        this.UPDATE = UPDATE;
        this.DELETE = DELETE;
    }
    
    public T salvar(T entidade) {
        try {
            PreparedStatement stmt = CONN.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preencherPrepareStatement(stmt, entidade);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                entidade.setId(rs.getLong(1));
            }
            rs.close();
            stmt.close();
            return entidade;
        } catch (SQLException e) {
            throw new RuntimeException("NAO FOI POSSIVEL SALVAR OS DADOS", e);
        }
    }
    
    public T editar(T entidade) {
        try {
            PreparedStatement stmt = CONN.prepareStatement(UPDATE);
            preencherPrepareStatement(stmt, entidade);
            stmt.setLong(stmt.getParameterMetaData().getParameterCount(), entidade.getId());
            stmt.executeUpdate();
            stmt.close();
            return entidade;
        } catch (SQLException e) {
            throw new RuntimeException("NAO FOI POSSIVEL EDITAR OS DADOS", e);
        }
    }
    
    public void deletar(Long id) {
        try {
            PreparedStatement stmt = CONN.prepareStatement(DELETE);
            stmt.setLong(1, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException("NAO FOI POSSIVEL DELETAR OS DADOS", e);
        }
    }
    
    public List<T> buscarTodos() {
        try {
            PreparedStatement stmt = CONN.prepareStatement(READ);
            ResultSet rs = stmt.executeQuery();
            return mapearLista(rs);
        } catch (SQLException e) {
            throw new RuntimeException("NAO FOI POSSIVEL BUSCAR OS DADOS", e);
        }
    }
    
    protected List<T> mapearLista(ResultSet rs) throws SQLException {
        List<T> entidade = new ArrayList<>();
        while (rs.next()) {
            entidade.add(mapear(rs));
        }
        return entidade;
    }
    
    protected abstract void preencherPrepareStatement(PreparedStatement stmt, T entidade) throws SQLException;
    protected abstract T mapear(ResultSet rs) throws SQLException;
}

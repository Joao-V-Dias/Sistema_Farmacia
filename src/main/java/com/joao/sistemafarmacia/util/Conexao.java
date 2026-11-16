package com.joao.sistemafarmacia.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/db_farmacia";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "postgres";

    private static Connection conn;

    private Conexao() {
    }

    public static Connection getInstance() {
        if (conn == null) {
            conectar();
        }
        return conn;
    }

    private static void conectar() {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Não foi possível conectar ao banco de dados", e);
        }
    }
}

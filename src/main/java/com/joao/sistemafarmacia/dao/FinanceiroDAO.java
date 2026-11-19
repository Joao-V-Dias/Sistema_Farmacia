package com.joao.sistemafarmacia.dao;

import com.joao.sistemafarmacia.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FinanceiroDAO {

    private final Connection conn;

    public FinanceiroDAO() {
        this.conn = Conexao.getInstance();
    }

    public double buscarValorEstoque() {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT valor_total FROM valor_estoque WHERE valor_estoque_id=1");
            ResultSet rs = stmt.executeQuery();

            double valorTotal = 0;
            if (rs.next()) {
                valorTotal = rs.getDouble("valor_total");
            }
            rs.close();
            stmt.close();
            return valorTotal;
        } catch (SQLException e) {
            throw new RuntimeException("NAO FOI POSSIVEL BUSCAR O VALOR DO ESTOQUE", e);
        }
    }
}

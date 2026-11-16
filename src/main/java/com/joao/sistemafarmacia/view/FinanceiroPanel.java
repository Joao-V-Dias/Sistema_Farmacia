package com.joao.sistemafarmacia.view;

import com.joao.sistemafarmacia.model.Compra;
import com.joao.sistemafarmacia.model.Solicitacao;
import com.joao.sistemafarmacia.service.FinanceiroService;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FinanceiroPanel extends javax.swing.JPanel {

    private final FinanceiroService financeiroService;
    private List<Solicitacao> solicitacoes;

    public FinanceiroPanel() {
        initComponents();
        this.financeiroService = new FinanceiroService();
        this.solicitacoes = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtValorEstoque = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabSolicitacao = new javax.swing.JTable();
        btnRegistrarCompra = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtTotalSolicitacoes = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabCompra = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtTotalCompras = new javax.swing.JTextField();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("FINANCEIRO");

        jLabel2.setText("Valor Total do Estoque:");

        txtValorEstoque.setEnabled(false);

        jLabel3.setText("Solicitações de Insumos:");

        tabSolicitacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Insumo", "Quantidade", "Valor", "Data", "Status"
            }
        ));
        jScrollPane1.setViewportView(tabSolicitacao);

        btnRegistrarCompra.setText("Registrar Compra");
        btnRegistrarCompra.addActionListener(this::btnRegistrarCompraActionPerformed);

        jLabel4.setText("Total das Solicitações:");

        txtTotalSolicitacoes.setEnabled(false);

        jLabel5.setText("Compras:");

        tabCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Insumo", "CNPJ Fornecedor", "Nota Fiscal", "Quantidade", "Valor", "Status"
            }
        ));
        jScrollPane2.setViewportView(tabCompra);

        jLabel6.setText("Total das Compras:");

        txtTotalCompras.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1029, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValorEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRegistrarCompra)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalSolicitacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1029, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtValorEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrarCompra)
                    .addComponent(jLabel4)
                    .addComponent(txtTotalSolicitacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTotalCompras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarCompraActionPerformed
        registrarCompra();
    }//GEN-LAST:event_btnRegistrarCompraActionPerformed

    public void carregarTodos() {
        try {
            txtValorEstoque.setText(String.valueOf(financeiroService.buscarValorEstoque()));

            solicitacoes = financeiroService.buscarSolicitacoes();
            preencherTabelaSolicitacoes(solicitacoes);
            txtTotalSolicitacoes.setText(String.valueOf(financeiroService.calcularTotalSolicitacoes(solicitacoes)));

            List<Compra> compras = financeiroService.buscarCompras();
            preencherTabelaCompras(compras);
            txtTotalCompras.setText(String.valueOf(financeiroService.calcularTotalCompras(compras)));
        } catch (RuntimeException ex) {
            mensagem(ex.getMessage());
        }
    }

    private void registrarCompra() {
        Solicitacao solicitacao = buscarSolicitacaoSelecionada();
        if (solicitacao == null) {
            return;
        }
        new CompraView(solicitacao).setVisible(true);
    }

    private Solicitacao buscarSolicitacaoSelecionada() {
        int linha = tabSolicitacao.getSelectedRow();
        if (linha < 0) {
            mensagem("Selecione uma solicitação na tabela");
            return null;
        }
        return solicitacoes.get(tabSolicitacao.convertRowIndexToModel(linha));
    }

    private void preencherTabelaSolicitacoes(List<Solicitacao> solicitacoes) {
        DefaultTableModel tabelaSolicitacoes = (DefaultTableModel) tabSolicitacao.getModel();
        tabelaSolicitacoes.setNumRows(0);

        solicitacoes.forEach(solicitacao -> tabelaSolicitacoes.addRow(new Object[]{
            solicitacao.getId(), solicitacao.getInsumo().getNome(), solicitacao.getQuantidade(),
            solicitacao.getValor(), solicitacao.getDataSolicitacao(), solicitacao.getStatus()
        }));
    }

    private void preencherTabelaCompras(List<Compra> compras) {
        DefaultTableModel tabelaCompras = (DefaultTableModel) tabCompra.getModel();
        tabelaCompras.setNumRows(0);

        compras.forEach(compra -> tabelaCompras.addRow(new Object[]{
            compra.getId(), compra.getInsumo().getNome(), compra.getCnpjFornecedor(), compra.getNumeroNotaFiscal(),
            compra.getQuantidade(), compra.getValor(), compra.getStatus()
        }));
    }

    private void mensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrarCompra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabCompra;
    private javax.swing.JTable tabSolicitacao;
    private javax.swing.JTextField txtTotalCompras;
    private javax.swing.JTextField txtTotalSolicitacoes;
    private javax.swing.JTextField txtValorEstoque;
    // End of variables declaration//GEN-END:variables
}

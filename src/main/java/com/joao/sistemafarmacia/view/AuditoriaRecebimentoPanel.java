package com.joao.sistemafarmacia.view;

import com.joao.sistemafarmacia.model.Compra;
import com.joao.sistemafarmacia.controller.AuditoriaController;
import com.joao.sistemafarmacia.controller.ControllerFactory;
import com.joao.sistemafarmacia.controller.Subject;
import com.joao.sistemafarmacia.view.observer.Observer;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AuditoriaRecebimentoPanel extends javax.swing.JPanel implements Observer {

    private final AuditoriaController auditoriaController;
    private List<Compra> compras;

    public AuditoriaRecebimentoPanel() {
        initComponents();
        this.auditoriaController = (AuditoriaController) ControllerFactory.criar("auditoria");
        this.compras = new ArrayList<>();
        ((Subject) ControllerFactory.criar("compra")).adicionarObservador(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabCompra = new javax.swing.JTable();
        btnDivergente = new javax.swing.JButton();
        btnRecebido = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("AUDITORIA DE RECEBIMENTO");

        tabCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Insumo", "CNPJ Fornecedor", "Nota Fiscal", "Quantidade", "Valor", "Status"
            }
        ));
        jScrollPane1.setViewportView(tabCompra);

        btnDivergente.setText("Insumo Divergente");
        btnDivergente.addActionListener(this::btnDivergenteActionPerformed);

        btnRecebido.setText("Recebido OK");
        btnRecebido.addActionListener(this::btnRecebidoActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1029, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnDivergente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRecebido)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDivergente)
                    .addComponent(btnRecebido))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDivergenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDivergenteActionPerformed
        marcarDivergente();
    }//GEN-LAST:event_btnDivergenteActionPerformed

    private void btnRecebidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecebidoActionPerformed
        confirmarRecebimento();
    }//GEN-LAST:event_btnRecebidoActionPerformed

    @Override
    public void atualizar() {
        carregarTodos();
    }

    public void carregarTodos() {
        try {
            compras = auditoriaController.buscarCompras();
            preencherTabela(compras);
        } catch (RuntimeException ex) {
            mensagem(ex.getMessage());
        }
    }

    private void confirmarRecebimento() {
        Compra compra = buscarCompraSelecionada();
        if (compra == null) {
            return;
        }
        try {
            auditoriaController.confirmarRecebimento(compra);
            mensagem("Compra " + compra.getId() + " recebida, estoque atualizado");
        } catch (RuntimeException ex) {
            mensagem(ex.getMessage());
        }
    }

    private void marcarDivergente() {
        Compra compra = buscarCompraSelecionada();
        if (compra == null) {
            return;
        }
        try {
            auditoriaController.marcarDivergente(compra);
            mensagem("Compra " + compra.getId() + " marcada como divergente");
        } catch (RuntimeException ex) {
            mensagem(ex.getMessage());
        }
    }

    private Compra buscarCompraSelecionada() {
        int linha = tabCompra.getSelectedRow();
        if (linha < 0) {
            mensagem("Selecione uma compra na tabela");
            return null;
        }
        return compras.get(tabCompra.convertRowIndexToModel(linha));
    }

    private void preencherTabela(List<Compra> compras) {
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
    private javax.swing.JButton btnDivergente;
    private javax.swing.JButton btnRecebido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabCompra;
    // End of variables declaration//GEN-END:variables
}

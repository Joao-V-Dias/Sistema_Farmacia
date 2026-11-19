package com.joao.sistemafarmacia.view;

import com.joao.sistemafarmacia.model.ItensReceita;
import com.joao.sistemafarmacia.model.Pedido;
import com.joao.sistemafarmacia.model.Receita;
import com.joao.sistemafarmacia.controller.AuditoriaController;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AuditoriaManipulacaoPanel extends javax.swing.JPanel {

    private final AuditoriaController auditoriaController;
    private List<Pedido> pedidos;

    public AuditoriaManipulacaoPanel() {
        initComponents();
        this.auditoriaController = new AuditoriaController();
        this.pedidos = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabPedido = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabReceita = new javax.swing.JTable();
        btnReprovar = new javax.swing.JButton();
        btnLiberar = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("AUDITORIA DE MANIPULAÇÃO");

        jLabel2.setText("Pedidos:");

        tabPedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Cliente", "CPF", "Data Pedido", "Valor", "Status"
            }
        ));
        tabPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabPedidoMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabPedido);

        jLabel3.setText("Receitas do Pedido Selecionado:");

        tabReceita.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Receita", "Médico", "Insumo", "Quantidade", "Estoque Atual"
            }
        ));
        jScrollPane2.setViewportView(tabReceita);

        btnReprovar.setText("Reprovar");
        btnReprovar.addActionListener(this::btnReprovarActionPerformed);

        btnLiberar.setText("Liberar para Entrega");
        btnLiberar.addActionListener(this::btnLiberarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1029, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1029, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnReprovar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLiberar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReprovar)
                    .addComponent(btnLiberar))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tabPedidoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabPedidoMousePressed
        int linha = tabPedido.rowAtPoint(evt.getPoint());
        if (linha >= 0) {
            carregarReceitas(pedidos.get(tabPedido.convertRowIndexToModel(linha)));
        }
    }//GEN-LAST:event_tabPedidoMousePressed

    private void btnReprovarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReprovarActionPerformed
        reprovar();
    }//GEN-LAST:event_btnReprovarActionPerformed

    private void btnLiberarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLiberarActionPerformed
        liberarEntrega();
    }//GEN-LAST:event_btnLiberarActionPerformed

    public void carregarTodos() {
        try {
            pedidos = auditoriaController.buscarPedidosManipulados();
            preencherTabelaPedidos(pedidos);
            preencherTabelaReceitas(new ArrayList<>());
        } catch (RuntimeException ex) {
            mensagem(ex.getMessage());
        }
    }

    private void carregarReceitas(Pedido pedido) {
        try {
            preencherTabelaReceitas(auditoriaController.buscarReceitas(pedido.getId()));
        } catch (RuntimeException ex) {
            mensagem(ex.getMessage());
        }
    }

    private void liberarEntrega() {
        Pedido pedido = buscarPedidoSelecionado();
        if (pedido == null) {
            return;
        }
        try {
            auditoriaController.liberarEntrega(pedido);
            mensagem("Pedido " + pedido.getId() + " liberado para entrega");
            carregarTodos();
        } catch (RuntimeException ex) {
            mensagem(ex.getMessage());
        }
    }

    private void reprovar() {
        Pedido pedido = buscarPedidoSelecionado();
        if (pedido == null) {
            return;
        }
        try {
            auditoriaController.reprovarManipulacao(pedido);
            mensagem("Pedido " + pedido.getId() + " reprovado");
            carregarTodos();
        } catch (RuntimeException ex) {
            mensagem(ex.getMessage());
        }
    }

    private Pedido buscarPedidoSelecionado() {
        int linha = tabPedido.getSelectedRow();
        if (linha < 0) {
            mensagem("Selecione um pedido na tabela");
            return null;
        }
        return pedidos.get(tabPedido.convertRowIndexToModel(linha));
    }

    private void preencherTabelaPedidos(List<Pedido> pedidos) {
        DefaultTableModel tabelaPedidos = (DefaultTableModel) tabPedido.getModel();
        tabelaPedidos.setNumRows(0);

        pedidos.forEach(pedido -> tabelaPedidos.addRow(new Object[]{
            pedido.getId(), pedido.getCliente().getNome(), pedido.getCliente().getCpf(),
            pedido.getDataPedido(), pedido.getValor(), pedido.getStatus()
        }));
    }

    private void preencherTabelaReceitas(List<Receita> receitas) {
        DefaultTableModel tabelaReceitas = (DefaultTableModel) tabReceita.getModel();
        tabelaReceitas.setNumRows(0);

        for (Receita receita : receitas) {
            for (ItensReceita item : receita.getInsumos()) {
                tabelaReceitas.addRow(new Object[]{
                    receita.getCodigo(), receita.getNomeMedico(), item.getInsumo().getNome(),
                    item.getQuantidade(), item.getInsumo().getQuantidade()
                });
            }
        }
    }

    private void mensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLiberar;
    private javax.swing.JButton btnReprovar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabPedido;
    private javax.swing.JTable tabReceita;
    // End of variables declaration//GEN-END:variables
}

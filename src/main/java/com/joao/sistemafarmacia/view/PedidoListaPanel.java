package com.joao.sistemafarmacia.view;

import com.joao.sistemafarmacia.model.Pedido;
import com.joao.sistemafarmacia.service.PedidoService;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PedidoListaPanel extends javax.swing.JPanel {

    private final PedidoService pedidoService;
    private List<Pedido> pedidos;

    public PedidoListaPanel() {
        initComponents();
        this.pedidoService = new PedidoService();
        this.pedidos = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtCpf = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabPedido = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("BUSCAR PEDIDO");

        txtCpf.addCaretListener(this::txtCpfCaretUpdate);

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
                        .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtCpfCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCpfCaretUpdate
        buscarPedidos();
    }//GEN-LAST:event_txtCpfCaretUpdate

    private void tabPedidoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabPedidoMousePressed
        if (evt.getClickCount() == 2) {
            abrirPedido(tabPedido.rowAtPoint(evt.getPoint()));
        }
    }//GEN-LAST:event_tabPedidoMousePressed

    public void carregarTodos() {
        try {
            pedidos = pedidoService.buscarPedidos();
            preencherTabela(pedidos);
        } catch (RuntimeException ex) {
            mensagem(ex.getMessage());
        }
    }

    private void buscarPedidos() {
        try {
            pedidos = pedidoService.buscarPedidosPorCpf(txtCpf.getText());
            preencherTabela(pedidos);
        } catch (RuntimeException ex) {
            mensagem(ex.getMessage());
        }
    }

    private void abrirPedido(int linha) {
        if (linha >= 0) {
            new PedidoReceitasView(pedidos.get(tabPedido.convertRowIndexToModel(linha)).getId()).setVisible(true);
        }
    }

    private void preencherTabela(List<Pedido> pedidos) {
        DefaultTableModel tabelaPedidos = (DefaultTableModel) tabPedido.getModel();
        tabelaPedidos.setNumRows(0);

        pedidos.forEach(pedido -> tabelaPedidos.addRow(new Object[]{
            pedido.getId(), pedido.getCliente().getNome(), pedido.getCliente().getCpf(),
            pedido.getDataPedido(), pedido.getValor(), pedido.getStatus()
        }));
    }

    private void mensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabPedido;
    private javax.swing.JTextField txtCpf;
    // End of variables declaration//GEN-END:variables
}

package com.joao.sistemafarmacia.view;

import com.joao.sistemafarmacia.model.Cliente;
import com.joao.sistemafarmacia.controller.ClienteController;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ClienteListaPanel extends javax.swing.JPanel {

    private final ClienteController clienteController;
    private List<Cliente> clientes;

    public ClienteListaPanel() {
        initComponents();
        this.clienteController = new ClienteController();
        this.clientes = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtBuscarCliente = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabCliente = new javax.swing.JTable();

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("BUSCAR CLIENTE");

        txtBuscarCliente.addCaretListener(this::txtBuscarClienteCaretUpdate);

        tabCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "CPF", "Telefone", "Data Nascimento", "Rua", "Numero"
            }
        ));
        tabCliente.setShowGrid(true);
        tabCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabClienteMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabCliente);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1029, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarClienteCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtBuscarClienteCaretUpdate
        buscarClientes();
    }//GEN-LAST:event_txtBuscarClienteCaretUpdate

    private void tabClienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabClienteMousePressed
        if (evt.getClickCount() == 2) {
            abrirCliente(tabCliente.rowAtPoint(evt.getPoint()));
        }
    }//GEN-LAST:event_tabClienteMousePressed

    public void carregarTodos() {
        try {
            clientes = clienteController.buscarClientes();
            preencherTabela(clientes);
        } catch (RuntimeException ex) {
            mensagem(ex.getMessage());
        }
    }

    private void buscarClientes() {
        try {
            clientes = clienteController.buscarClientes(txtBuscarCliente.getText());
            preencherTabela(clientes);
        } catch (RuntimeException ex) {
            mensagem(ex.getMessage());
        }
    }

    private void abrirCliente(int linha) {
        if (linha >= 0) {
            new ClienteView(clientes.get(tabCliente.convertRowIndexToModel(linha))).setVisible(true);
        }
    }

    private void preencherTabela(List<Cliente> clientes) {
        DefaultTableModel tabelaClientes = (DefaultTableModel) tabCliente.getModel();
        tabelaClientes.setNumRows(0);

        clientes.forEach(cliente -> tabelaClientes.addRow(new Object[]{
            cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getTelefone(),
            cliente.getDataNascimento(), cliente.getRua(), cliente.getNumero()
        }));
    }

    private void mensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabCliente;
    private javax.swing.JTextField txtBuscarCliente;
    // End of variables declaration//GEN-END:variables
}

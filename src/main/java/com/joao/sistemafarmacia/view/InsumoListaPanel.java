package com.joao.sistemafarmacia.view;

import com.joao.sistemafarmacia.model.Insumo;
import com.joao.sistemafarmacia.controller.InsumoController;
import com.joao.sistemafarmacia.controller.ControllerFactory;
import com.joao.sistemafarmacia.view.observer.Observer;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class InsumoListaPanel extends javax.swing.JPanel implements Observer {

    private final InsumoController insumoController;
    private List<Insumo> insumos;

    public InsumoListaPanel() {
        initComponents();
        this.insumoController = (InsumoController) ControllerFactory.criar("insumo");
        this.insumos = new ArrayList<>();
        insumoController.adicionarObservador(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtInsumo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabInsumo = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("BUSCAR INSUMO");

        txtInsumo.addCaretListener(this::txtInsumoCaretUpdate);

        tabInsumo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "Valor", "Quantidade", "Minimo"
            }
        ));
        tabInsumo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabInsumoMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabInsumo);

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
                        .addComponent(txtInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtInsumoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtInsumoCaretUpdate
        buscarInsumos();
    }//GEN-LAST:event_txtInsumoCaretUpdate

    private void tabInsumoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabInsumoMousePressed
        if (evt.getClickCount() == 2) {
            abrirInsumo(tabInsumo.rowAtPoint(evt.getPoint()));
        }
    }//GEN-LAST:event_tabInsumoMousePressed

    @Override
    public void atualizar() {
        carregarTodos();
    }

    public void carregarTodos() {
        try {
            insumos = insumoController.buscarInsumos();
            preencherTabela(insumos);
        } catch (RuntimeException ex) {
            mensagem(ex.getMessage());
        }
    }

    private void buscarInsumos() {
        try {
            insumos = insumoController.buscarInsumos(txtInsumo.getText());
            preencherTabela(insumos);
        } catch (RuntimeException ex) {
            mensagem(ex.getMessage());
        }
    }

    private void abrirInsumo(int linha) {
        if (linha >= 0) {
            new InsumoView(insumos.get(tabInsumo.convertRowIndexToModel(linha))).setVisible(true);
        }
    }

    private void preencherTabela(List<Insumo> insumos) {
        DefaultTableModel tabelaInsumos = (DefaultTableModel) tabInsumo.getModel();
        tabelaInsumos.setNumRows(0);

        insumos.forEach(insumo -> tabelaInsumos.addRow(new Object[]{
            insumo.getId(), insumo.getNome(), insumo.getValor(), insumo.getQuantidade(), insumo.getMinimo()
        }));
    }

    private void mensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabInsumo;
    private javax.swing.JTextField txtInsumo;
    // End of variables declaration//GEN-END:variables
}

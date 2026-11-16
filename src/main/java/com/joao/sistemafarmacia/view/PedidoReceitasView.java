package com.joao.sistemafarmacia.view;

import com.joao.sistemafarmacia.model.ItensReceita;
import com.joao.sistemafarmacia.model.Receita;
import com.joao.sistemafarmacia.service.ReceitaService;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PedidoReceitasView extends javax.swing.JFrame {

    private final ReceitaService receitaService;
    private List<Receita> receitas;

    public PedidoReceitasView(Long pedidoId) {
        initComponents();
        this.receitaService = new ReceitaService();
        this.receitas = new ArrayList<>();
        txtTitulo.setText("RECEITAS DO PEDIDO " + pedidoId);
        carregarReceitas(pedidoId);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtTitulo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabReceita = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabInsumo = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Receitas do Pedido");
        setAlwaysOnTop(true);
        setResizable(false);

        txtTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTitulo.setText("RECEITAS DO PEDIDO");

        jLabel2.setText("Receitas:");

        tabReceita.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Código", "Médico", "Data Emissão", "Data Entrada"
            }
        ));
        tabReceita.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabReceitaMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabReceita);

        jLabel3.setText("Insumos da Receita:");

        tabInsumo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Insumo", "Valor", "Quantidade", "Subtotal"
            }
        ));
        jScrollPane2.setViewportView(tabInsumo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tabReceitaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabReceitaMousePressed
        selecionarReceita(tabReceita.rowAtPoint(evt.getPoint()));
    }//GEN-LAST:event_tabReceitaMousePressed

    private void carregarReceitas(Long pedidoId) {
        try {
            receitas = receitaService.buscarReceitas(pedidoId);
            preencherTabelaReceitas(receitas);
        } catch (RuntimeException ex) {
            mensagem(ex.getMessage());
        }
    }

    private void selecionarReceita(int linha) {
        if (linha >= 0) {
            preencherTabelaInsumos(receitas.get(tabReceita.convertRowIndexToModel(linha)).getInsumos());
        }
    }

    private void preencherTabelaReceitas(List<Receita> receitas) {
        DefaultTableModel tabelaReceitas = (DefaultTableModel) tabReceita.getModel();
        tabelaReceitas.setNumRows(0);

        receitas.forEach(receita -> tabelaReceitas.addRow(new Object[]{
            receita.getId(), receita.getCodigo(), receita.getNomeMedico(),
            receita.getDataEmissao(), receita.getDataEntrada()
        }));
    }

    private void preencherTabelaInsumos(List<ItensReceita> itens) {
        DefaultTableModel tabelaInsumos = (DefaultTableModel) tabInsumo.getModel();
        tabelaInsumos.setNumRows(0);

        itens.forEach(item -> tabelaInsumos.addRow(new Object[]{
            item.getInsumo().getId(), item.getInsumo().getNome(), item.getInsumo().getValor(),
            item.getQuantidade(), item.getInsumo().getValor() * item.getQuantidade()
        }));
    }

    private void mensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabInsumo;
    private javax.swing.JTable tabReceita;
    private javax.swing.JLabel txtTitulo;
    // End of variables declaration//GEN-END:variables
}

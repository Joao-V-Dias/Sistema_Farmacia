package com.joao.sistemafarmacia.view;

import com.joao.sistemafarmacia.model.Insumo;
import com.joao.sistemafarmacia.model.Solicitacao;
import com.joao.sistemafarmacia.service.InsumoService;
import com.joao.sistemafarmacia.service.SolicitacaoService;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EstoqueMinimoPanel extends javax.swing.JPanel {

    private final InsumoService insumoService;
    private final SolicitacaoService solicitacaoService;
    private List<Insumo> insumos;

    public EstoqueMinimoPanel() {
        initComponents();
        this.insumoService = new InsumoService();
        this.solicitacaoService = new SolicitacaoService();
        this.insumos = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabInsumo = new javax.swing.JTable();
        btnSolicitar = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("ESTOQUE MÍNIMO");

        tabInsumo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome", "Valor", "Quantidade", "Minimo"
            }
        ));
        jScrollPane1.setViewportView(tabInsumo);

        btnSolicitar.setText("Criar Solicitação");
        btnSolicitar.addActionListener(this::btnSolicitarActionPerformed);

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
                        .addComponent(btnSolicitar)))
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
                .addComponent(btnSolicitar)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSolicitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolicitarActionPerformed
        criarSolicitacao();
    }//GEN-LAST:event_btnSolicitarActionPerformed

    public void carregarTodos() {
        try {
            insumos = insumoService.buscarInsumosEstoqueMinimo();
            preencherTabela(insumos);
        } catch (RuntimeException ex) {
            mensagem(ex.getMessage());
        }
    }

    private void criarSolicitacao() {
        Insumo insumo = buscarInsumoSelecionado();
        if (insumo == null) {
            return;
        }
        String quantidade = JOptionPane.showInputDialog(this, "Quantidade de " + insumo.getNome() + " a solicitar:");
        if (quantidade == null) {
            return;
        }
        try {
            Solicitacao solicitacao = solicitacaoService.salvar(mapearSolicitacao(insumo, quantidade));
            mensagem("Solicitação " + solicitacao.getId() + " criada com sucesso");
        } catch (NumberFormatException ex) {
            mensagem("Quantidade inválida");
        } catch (RuntimeException ex) {
            mensagem(ex.getMessage());
        }
    }

    private Insumo buscarInsumoSelecionado() {
        int linha = tabInsumo.getSelectedRow();
        if (linha < 0) {
            mensagem("Selecione um insumo na tabela");
            return null;
        }
        return insumos.get(tabInsumo.convertRowIndexToModel(linha));
    }

    private Solicitacao mapearSolicitacao(Insumo insumo, String quantidade) {
        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setInsumo(insumo);
        solicitacao.setQuantidade(Double.parseDouble(quantidade));
        return solicitacao;
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
    private javax.swing.JButton btnSolicitar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabInsumo;
    // End of variables declaration//GEN-END:variables
}

package com.joao.sistemafarmacia.view;

import com.joao.sistemafarmacia.model.Compra;
import com.joao.sistemafarmacia.model.Solicitacao;
import com.joao.sistemafarmacia.controller.CompraController;
import com.joao.sistemafarmacia.controller.ControllerFactory;
import javax.swing.JOptionPane;

public class CompraView extends javax.swing.JFrame {

    private final CompraController compraController;
    private final Solicitacao solicitacao;

    public CompraView(Solicitacao solicitacao) {
        initComponents();
        this.compraController = (CompraController) ControllerFactory.criar("compra");
        this.solicitacao = solicitacao;
        mapearCampos(solicitacao);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSolicitacao = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtInsumo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCnpj = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNotaFiscal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dados da Compra");
        setAlwaysOnTop(true);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DADOS DA COMPRA");

        jLabel2.setText("Solicitação:");

        txtSolicitacao.setEnabled(false);

        jLabel3.setText("Insumo:");

        txtInsumo.setEnabled(false);

        jLabel4.setText("CNPJ do Fornecedor:");

        jLabel5.setText("Número da Nota Fiscal:");

        jLabel6.setText("Quantidade Comprada:");

        jLabel7.setText("Valor da Compra:");

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(this::btnSalvarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(txtSolicitacao)
                    .addComponent(jLabel3)
                    .addComponent(txtInsumo)
                    .addComponent(jLabel4)
                    .addComponent(txtCnpj)
                    .addComponent(jLabel5)
                    .addComponent(txtNotaFiscal)
                    .addComponent(jLabel6)
                    .addComponent(txtQuantidade)
                    .addComponent(jLabel7)
                    .addComponent(txtValor)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSalvar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSolicitacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalvar)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        salvar();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void salvar() {
        try {
            Compra compra = compraController.salvar(mapearCompra());
            mensagem("Compra " + compra.getId() + " registrada com sucesso");
            this.dispose();
        } catch (RuntimeException ex) {
            mensagem(ex.getMessage());
        }
    }

    private Compra mapearCompra() {
        Compra compra = new Compra();
        compra.setSolicitacao(solicitacao);
        compra.setCnpjFornecedor(txtCnpj.getText());
        compra.setNumeroNotaFiscal(txtNotaFiscal.getText());
        compra.setQuantidade(Double.parseDouble(txtQuantidade.getText()));
        compra.setValor(Double.parseDouble(txtValor.getText()));
        return compra;
    }

    private void mapearCampos(Solicitacao solicitacao) {
        txtSolicitacao.setText(String.valueOf(solicitacao.getId()));
        txtInsumo.setText(solicitacao.getInsumo().getNome());
        txtQuantidade.setText(String.valueOf(solicitacao.getQuantidade()));
        txtValor.setText(String.valueOf(solicitacao.getValor()));
    }

    private void mensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtCnpj;
    private javax.swing.JTextField txtInsumo;
    private javax.swing.JTextField txtNotaFiscal;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JTextField txtSolicitacao;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}

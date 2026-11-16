package com.joao.sistemafarmacia.view;

import java.awt.CardLayout;

public class Index extends javax.swing.JFrame {

    public Index() {
        initComponents();
        setExtendedState(Index.MAXIMIZED_BOTH);
        mostrarTela("listarPedido");
        pedidoListaPanel1.carregarTodos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        usuarioListaPanel1 = new com.joao.sistemafarmacia.view.UsuarioListaPanel();
        clienteListaPanel1 = new com.joao.sistemafarmacia.view.ClienteListaPanel();
        insumoListaPanel1 = new com.joao.sistemafarmacia.view.InsumoListaPanel();
        pedidoListaPanel1 = new com.joao.sistemafarmacia.view.PedidoListaPanel();
        estoqueMinimoPanel1 = new com.joao.sistemafarmacia.view.EstoqueMinimoPanel();
        financeiroPanel1 = new com.joao.sistemafarmacia.view.FinanceiroPanel();
        auditoriaRecebimentoPanel1 = new com.joao.sistemafarmacia.view.AuditoriaRecebimentoPanel();
        manipulacaoPanel1 = new com.joao.sistemafarmacia.view.ManipulacaoPanel();
        auditoriaManipulacaoPanel1 = new com.joao.sistemafarmacia.view.AuditoriaManipulacaoPanel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        btnListarCliente = new javax.swing.JMenuItem();
        AdicionarCliente = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        btnBuscarUsuario = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        btnBuscarInsumo = new javax.swing.JMenuItem();
        btnCadastrarInsumo = new javax.swing.JMenuItem();
        btnEstoqueMinimo = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        btnFinanceiro = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        btnManipulacao = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        btnAuditoriaRecebimento = new javax.swing.JMenuItem();
        btnAuditoriaManipulacao = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema Farmacia");

        jPanel1.setLayout(new java.awt.CardLayout());
        jPanel1.add(usuarioListaPanel1, "listarUsuario");
        jPanel1.add(clienteListaPanel1, "listarCliente");
        jPanel1.add(insumoListaPanel1, "listarInsumo");
        jPanel1.add(pedidoListaPanel1, "listarPedido");
        jPanel1.add(estoqueMinimoPanel1, "estoqueMinimo");
        jPanel1.add(financeiroPanel1, "financeiro");
        jPanel1.add(auditoriaRecebimentoPanel1, "auditoriaRecebimento");
        jPanel1.add(manipulacaoPanel1, "manipulacao");
        jPanel1.add(auditoriaManipulacaoPanel1, "auditoriaManipulacao");

        jMenu5.setText("Pedidos");

        jMenuItem1.setText("Cadastrar");
        jMenuItem1.addActionListener(this::jMenuItem1ActionPerformed);
        jMenu5.add(jMenuItem1);

        jMenuItem2.setText("Consultar");
        jMenuItem2.addActionListener(this::jMenuItem2ActionPerformed);
        jMenu5.add(jMenuItem2);

        jMenuBar2.add(jMenu5);

        jMenu6.setText("Clientes");

        btnListarCliente.setText("Buscar");
        btnListarCliente.setToolTipText("");
        btnListarCliente.addActionListener(this::btnListarClienteActionPerformed);
        jMenu6.add(btnListarCliente);

        AdicionarCliente.setText("Adicionar");
        AdicionarCliente.addActionListener(this::AdicionarClienteActionPerformed);
        jMenu6.add(AdicionarCliente);

        jMenuBar2.add(jMenu6);

        jMenu7.setText("Usuarios");

        btnBuscarUsuario.setText("Buscar");
        btnBuscarUsuario.addActionListener(this::btnBuscarUsuarioActionPerformed);
        jMenu7.add(btnBuscarUsuario);

        jMenuItem4.setText("Criar");
        jMenuItem4.addActionListener(this::jMenuItem4ActionPerformed);
        jMenu7.add(jMenuItem4);

        jMenuBar2.add(jMenu7);

        jMenu8.setText("Insumos");

        btnBuscarInsumo.setText("Buscar");
        btnBuscarInsumo.addActionListener(this::btnBuscarInsumoActionPerformed);
        jMenu8.add(btnBuscarInsumo);

        btnCadastrarInsumo.setText("Cadastrar");
        btnCadastrarInsumo.addActionListener(this::btnCadastrarInsumoActionPerformed);
        jMenu8.add(btnCadastrarInsumo);

        btnEstoqueMinimo.setText("Estoque Mínimo");
        btnEstoqueMinimo.addActionListener(this::btnEstoqueMinimoActionPerformed);
        jMenu8.add(btnEstoqueMinimo);

        jMenuBar2.add(jMenu8);

        jMenu1.setText("Financeiro");

        btnFinanceiro.setText("Visualizar");
        btnFinanceiro.addActionListener(this::btnFinanceiroActionPerformed);
        jMenu1.add(btnFinanceiro);

        jMenuBar2.add(jMenu1);

        jMenu2.setText("Manipulacao");

        btnManipulacao.setText("Manipular");
        btnManipulacao.addActionListener(this::btnManipulacaoActionPerformed);
        jMenu2.add(btnManipulacao);

        jMenuBar2.add(jMenu2);

        jMenu3.setText("Auditoria");

        btnAuditoriaRecebimento.setText("Recebimento");
        btnAuditoriaRecebimento.addActionListener(this::btnAuditoriaRecebimentoActionPerformed);
        jMenu3.add(btnAuditoriaRecebimento);

        btnAuditoriaManipulacao.setText("Manipulação");
        btnAuditoriaManipulacao.addActionListener(this::btnAuditoriaManipulacaoActionPerformed);
        jMenu3.add(btnAuditoriaManipulacao);

        jMenuBar2.add(jMenu3);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnListarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarClienteActionPerformed
        mostrarTela("listarCliente");
        clienteListaPanel1.carregarTodos();
    }//GEN-LAST:event_btnListarClienteActionPerformed

    private void btnBuscarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarUsuarioActionPerformed
        mostrarTela("listarUsuario");
        usuarioListaPanel1.carregarTodos();
    }//GEN-LAST:event_btnBuscarUsuarioActionPerformed

    private void AdicionarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdicionarClienteActionPerformed
        new ClienteView().setVisible(true);
    }//GEN-LAST:event_AdicionarClienteActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        new UsuarioView().setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void btnCadastrarInsumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarInsumoActionPerformed
        new InsumoView().setVisible(true);
    }//GEN-LAST:event_btnCadastrarInsumoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new PedidoView().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        mostrarTela("listarPedido");
        pedidoListaPanel1.carregarTodos();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btnBuscarInsumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarInsumoActionPerformed
        mostrarTela("listarInsumo");
        insumoListaPanel1.carregarTodos();
    }//GEN-LAST:event_btnBuscarInsumoActionPerformed

    private void btnEstoqueMinimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstoqueMinimoActionPerformed
        mostrarTela("estoqueMinimo");
        estoqueMinimoPanel1.carregarTodos();
    }//GEN-LAST:event_btnEstoqueMinimoActionPerformed

    private void btnFinanceiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinanceiroActionPerformed
        mostrarTela("financeiro");
        financeiroPanel1.carregarTodos();
    }//GEN-LAST:event_btnFinanceiroActionPerformed

    private void btnAuditoriaRecebimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAuditoriaRecebimentoActionPerformed
        mostrarTela("auditoriaRecebimento");
        auditoriaRecebimentoPanel1.carregarTodos();
    }//GEN-LAST:event_btnAuditoriaRecebimentoActionPerformed

    private void btnManipulacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManipulacaoActionPerformed
        mostrarTela("manipulacao");
        manipulacaoPanel1.carregarTodos();
    }//GEN-LAST:event_btnManipulacaoActionPerformed

    private void btnAuditoriaManipulacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAuditoriaManipulacaoActionPerformed
        mostrarTela("auditoriaManipulacao");
        auditoriaManipulacaoPanel1.carregarTodos();
    }//GEN-LAST:event_btnAuditoriaManipulacaoActionPerformed

    private void mostrarTela(String tela) {
        CardLayout cl = (CardLayout) jPanel1.getLayout();
        cl.show(jPanel1, tela);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AdicionarCliente;
    private com.joao.sistemafarmacia.view.AuditoriaManipulacaoPanel auditoriaManipulacaoPanel1;
    private com.joao.sistemafarmacia.view.AuditoriaRecebimentoPanel auditoriaRecebimentoPanel1;
    private javax.swing.JMenuItem btnAuditoriaManipulacao;
    private javax.swing.JMenuItem btnAuditoriaRecebimento;
    private javax.swing.JMenuItem btnBuscarInsumo;
    private javax.swing.JMenuItem btnBuscarUsuario;
    private javax.swing.JMenuItem btnCadastrarInsumo;
    private javax.swing.JMenuItem btnEstoqueMinimo;
    private javax.swing.JMenuItem btnFinanceiro;
    private javax.swing.JMenuItem btnListarCliente;
    private javax.swing.JMenuItem btnManipulacao;
    private com.joao.sistemafarmacia.view.ClienteListaPanel clienteListaPanel1;
    private com.joao.sistemafarmacia.view.EstoqueMinimoPanel estoqueMinimoPanel1;
    private com.joao.sistemafarmacia.view.FinanceiroPanel financeiroPanel1;
    private com.joao.sistemafarmacia.view.InsumoListaPanel insumoListaPanel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private com.joao.sistemafarmacia.view.ManipulacaoPanel manipulacaoPanel1;
    private com.joao.sistemafarmacia.view.PedidoListaPanel pedidoListaPanel1;
    private com.joao.sistemafarmacia.view.UsuarioListaPanel usuarioListaPanel1;
    // End of variables declaration//GEN-END:variables
}

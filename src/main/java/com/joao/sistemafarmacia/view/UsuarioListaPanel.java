package com.joao.sistemafarmacia.view;

import com.joao.sistemafarmacia.model.Usuario;
import com.joao.sistemafarmacia.controller.UsuarioController;
import com.joao.sistemafarmacia.controller.ControllerFactory;
import com.joao.sistemafarmacia.view.observer.Observer;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class UsuarioListaPanel extends ListaPanel<Usuario> implements Observer {

    private final UsuarioController usuarioController;

    public UsuarioListaPanel() {
        initComponents();
        this.usuarioController = (UsuarioController) ControllerFactory.criar("usuario");
        usuarioController.adicionarObservador(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabUsuario = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("BUSCAR USUARIO");

        txtUsuario.addCaretListener(this::txtUsuarioCaretUpdate);

        tabUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "CPF", "Telefone", "Data Nascimento", "Email", "Cargo"
            }
        ));
        tabUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabUsuarioMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabUsuario);

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
                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtUsuarioCaretUpdate
        buscarUsuarios();
    }//GEN-LAST:event_txtUsuarioCaretUpdate

    private void tabUsuarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabUsuarioMousePressed
        if (evt.getClickCount() == 2) {
            abrirUsuario(tabUsuario.rowAtPoint(evt.getPoint()));
        }
    }//GEN-LAST:event_tabUsuarioMousePressed

    @Override
    public void atualizar() {
        carregarTabela();
    }

    @Override
    protected List<Usuario> buscarItens() {
        return usuarioController.buscarUsuarios();
    }

    private void buscarUsuarios() {
        try {
            itens = usuarioController.buscarUsuarios(txtUsuario.getText());
            preencherTabela(itens);
        } catch (RuntimeException ex) {
            mensagem(ex.getMessage());
        }
    }

    private void abrirUsuario(int linha) {
        if (linha >= 0) {
            new UsuarioView(buscarItem(tabUsuario, linha)).setVisible(true);
        }
    }

    @Override
    protected void preencherTabela(List<Usuario> usuarios) {
        DefaultTableModel tabelaUsuarios = (DefaultTableModel) tabUsuario.getModel();
        tabelaUsuarios.setNumRows(0);

        usuarios.forEach(usuario -> tabelaUsuarios.addRow(new Object[]{
            usuario.getId(), usuario.getNome(), usuario.getCpf(), usuario.getTelefone(),
            usuario.getDataNascimento(), usuario.getEmail(), usuario.getCargo()
        }));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabUsuario;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}

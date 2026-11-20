package com.joao.sistemafarmacia.view;

import com.joao.sistemafarmacia.model.Cliente;
import com.joao.sistemafarmacia.model.Insumo;
import com.joao.sistemafarmacia.model.ItensReceita;
import com.joao.sistemafarmacia.model.Pedido;
import com.joao.sistemafarmacia.model.Receita;
import com.joao.sistemafarmacia.controller.ClienteController;
import com.joao.sistemafarmacia.controller.InsumoController;
import com.joao.sistemafarmacia.controller.PedidoController;
import com.joao.sistemafarmacia.controller.ReceitaController;
import com.joao.sistemafarmacia.controller.ControllerFactory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PedidoView extends javax.swing.JFrame {

    private final PedidoController pedidoController;
    private final ReceitaController receitaController;
    private final ClienteController clienteController;
    private final InsumoController insumoController;
    private List<Cliente> clientes;
    private List<Insumo> insumos;
    private List<Receita> receitas;
    private Receita receitaSelecionada;

    public PedidoView() {
        initComponents();
        this.pedidoController = (PedidoController) ControllerFactory.criar("pedido");
        this.receitaController = (ReceitaController) ControllerFactory.criar("receita");
        this.clienteController = (ClienteController) ControllerFactory.criar("cliente");
        this.insumoController = (InsumoController) ControllerFactory.criar("insumo");
        this.clientes = new ArrayList<>();
        this.receitas = new ArrayList<>();
        carregarInsumos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCpf = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbCliente = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNomeMedico = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDataEmissao = new javax.swing.JTextField();
        btnAdicionarReceita = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabReceitas = new javax.swing.JTable();
        btnRemoverReceita = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtInsumo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbInsumo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtQuantidade = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabItens = new javax.swing.JTable();
        btnRemover = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtValorTotal = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastrar Pedido");
        setAlwaysOnTop(true);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CADASTRAR PEDIDO");

        jLabel2.setText("CPF do Cliente:");

        txtCpf.addCaretListener(this::txtCpfCaretUpdate);

        jLabel3.setText("Cliente:");

        jLabel10.setText("Código da Receita:");

        jLabel4.setText("Nome do Médico:");

        jLabel5.setText("Data de Emissão da Receita (dd/mm/aaaa):");

        btnAdicionarReceita.setText("Adicionar Receita");
        btnAdicionarReceita.addActionListener(this::btnAdicionarReceitaActionPerformed);

        jLabel11.setText("Receitas do Pedido:");

        tabReceitas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Médico", "Data Emissão", "Valor"
            }
        ));
        tabReceitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabReceitasMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tabReceitas);

        btnRemoverReceita.setText("Remover Receita");
        btnRemoverReceita.addActionListener(this::btnRemoverReceitaActionPerformed);

        jLabel9.setText("Nome do Insumo:");

        txtInsumo.addCaretListener(this::txtInsumoCaretUpdate);

        jLabel6.setText("Insumo:");

        jLabel7.setText("Quantidade:");

        btnAdicionar.setText("Adicionar Insumo");
        btnAdicionar.addActionListener(this::btnAdicionarActionPerformed);

        jLabel12.setText("Insumos da Receita Selecionada:");

        tabItens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Insumo", "Valor", "Quantidade", "Subtotal"
            }
        ));
        jScrollPane1.setViewportView(tabItens);

        btnRemover.setText("Remover Insumo");
        btnRemover.addActionListener(this::btnRemoverActionPerformed);

        jLabel8.setText("Valor Total:");

        txtValorTotal.setEnabled(false);

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(this::btnSalvarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(txtCpf)
                    .addComponent(jLabel3)
                    .addComponent(cbCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtNomeMedico)
                    .addComponent(jLabel5)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdicionarReceita))
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                    .addComponent(btnRemoverReceita)
                    .addComponent(jLabel9)
                    .addComponent(txtInsumo)
                    .addComponent(jLabel6)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbInsumo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdicionar))
                    .addComponent(jLabel12)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRemover)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSalvar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNomeMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdicionarReceita))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemoverReceita)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbInsumo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdicionar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRemover)
                    .addComponent(jLabel8)
                    .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvar))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCpfCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCpfCaretUpdate
        buscarClientes();
    }//GEN-LAST:event_txtCpfCaretUpdate

    private void btnAdicionarReceitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarReceitaActionPerformed
        adicionarReceita();
    }//GEN-LAST:event_btnAdicionarReceitaActionPerformed

    private void tabReceitasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabReceitasMousePressed
        selecionarReceita(tabReceitas.rowAtPoint(evt.getPoint()));
    }//GEN-LAST:event_tabReceitasMousePressed

    private void btnRemoverReceitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverReceitaActionPerformed
        removerReceita();
    }//GEN-LAST:event_btnRemoverReceitaActionPerformed

    private void txtInsumoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtInsumoCaretUpdate
        carregarInsumos();
    }//GEN-LAST:event_txtInsumoCaretUpdate

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        adicionarItem();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        removerItem();
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        salvar();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void salvar() {
        try {
            Pedido pedido = pedidoController.salvar(mapearPedido());
            mensagem("Pedido " + pedido.getId() + " salvo com sucesso");
            this.dispose();
        } catch (RuntimeException ex) {
            mensagem(ex.getMessage());
        }
    }

    private Pedido mapearPedido() {
        validarCliente();
        Pedido pedido = new Pedido();
        pedido.setCliente(clientes.get(cbCliente.getSelectedIndex()));
        pedido.setReceitas(receitas);
        return pedido;
    }

    private void validarCliente() {
        if (cbCliente.getSelectedIndex() < 0) {
            throw new IllegalArgumentException("Busque e selecione um cliente");
        }
    }

    private void buscarClientes() {
        try {
            clientes = clienteController.buscarClientes(txtCpf.getText());
            cbCliente.removeAllItems();
            clientes.forEach(cliente -> cbCliente.addItem(cliente.getCpf() + " - " + cliente.getNome()));
        } catch (RuntimeException ex) {
            mensagem(ex.getMessage());
        }
    }

    private void adicionarReceita() {
        try {
            Receita receita = mapearReceita();
            receitas.add(receita);
            receitaSelecionada = receita;
            limparCamposReceita();
            atualizarTabelas();
        } catch (IllegalArgumentException ex) {
            mensagem(ex.getMessage());
        }
    }

    private Receita mapearReceita() {
        validarCodigoReceita();

        Receita receita = new Receita();
        receita.setCodigo(txtCodigo.getText());
        receita.setNomeMedico(txtNomeMedico.getText());
        receita.setDataEmissao(lerDataEmissao());
        receita.setDataEntrada(LocalDate.now());
        receita.setInsumos(new ArrayList<>());
        return receita;
    }

    private void validarCodigoReceita() {
        if (txtCodigo.getText().isBlank()) {
            throw new IllegalArgumentException("Informe o código da receita");
        }
        for (Receita receita : receitas) {
            if (receita.getCodigo().equals(txtCodigo.getText())) {
                throw new IllegalArgumentException("Esta receita já foi adicionada");
            }
        }
    }

    private LocalDate lerDataEmissao() {
        try {
            return LocalDate.parse(txtDataEmissao.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Data de emissão inválida. Use o formato dd/mm/aaaa");
        }
    }

    private void limparCamposReceita() {
        txtCodigo.setText("");
        txtNomeMedico.setText("");
        txtDataEmissao.setText("");
    }

    private void selecionarReceita(int linha) {
        if (linha >= 0) {
            receitaSelecionada = receitas.get(tabReceitas.convertRowIndexToModel(linha));
            preencherTabelaItens(receitaSelecionada);
        }
    }

    private void removerReceita() {
        int linha = tabReceitas.getSelectedRow();
        if (linha < 0) {
            mensagem("Selecione uma receita na tabela");
            return;
        }
        Receita receita = receitas.remove(tabReceitas.convertRowIndexToModel(linha));
        if (receita == receitaSelecionada) {
            receitaSelecionada = null;
        }
        atualizarTabelas();
    }

    private void carregarInsumos() {
        try {
            insumos = insumoController.buscarInsumos(txtInsumo.getText());
            cbInsumo.removeAllItems();
            insumos.forEach(insumo -> cbInsumo.addItem(insumo.getNome()));
        } catch (RuntimeException ex) {
            insumos = new ArrayList<>();
            mensagem(ex.getMessage());
        }
    }

    private void adicionarItem() {
        try {
            validarReceitaSelecionada();
            ItensReceita item = mapearItem();
            receitaSelecionada.getInsumos().add(item);
            txtQuantidade.setText("");
            atualizarTabelas();
        } catch (NumberFormatException ex) {
            mensagem("Quantidade inválida");
        } catch (IllegalArgumentException ex) {
            mensagem(ex.getMessage());
        }
    }

    private void validarReceitaSelecionada() {
        if (receitaSelecionada == null) {
            throw new IllegalArgumentException("Adicione ou selecione uma receita");
        }
    }

    private ItensReceita mapearItem() {
        Insumo insumo = buscarInsumoSelecionado();

        double quantidade = Double.parseDouble(txtQuantidade.getText());
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero");
        }

        ItensReceita item = new ItensReceita();
        item.setInsumo(insumo);
        item.setQuantidade(quantidade);
        return item;
    }

    private Insumo buscarInsumoSelecionado() {
        if (cbInsumo.getSelectedIndex() < 0) {
            throw new IllegalArgumentException("Selecione um insumo");
        }
        Insumo insumo = insumos.get(cbInsumo.getSelectedIndex());
        for (ItensReceita item : receitaSelecionada.getInsumos()) {
            if (item.getInsumo().getId().equals(insumo.getId())) {
                throw new IllegalArgumentException("Este insumo já foi adicionado");
            }
        }
        return insumo;
    }

    private void removerItem() {
        int linha = tabItens.getSelectedRow();
        if (receitaSelecionada == null || linha < 0) {
            mensagem("Selecione um insumo na tabela");
            return;
        }
        receitaSelecionada.getInsumos().remove(tabItens.convertRowIndexToModel(linha));
        atualizarTabelas();
    }

    private void atualizarTabelas() {
        preencherTabelaReceitas(receitas);
        preencherTabelaItens(receitaSelecionada);
        txtValorTotal.setText(String.valueOf(pedidoController.calcularTotal(receitas)));
    }

    private void preencherTabelaReceitas(List<Receita> receitas) {
        DefaultTableModel tabelaReceitas = (DefaultTableModel) tabReceitas.getModel();
        tabelaReceitas.setNumRows(0);

        receitas.forEach(receita -> tabelaReceitas.addRow(new Object[]{
            receita.getCodigo(), receita.getNomeMedico(), receita.getDataEmissao(), receitaController.calcularValor(receita)
        }));

        if (receitaSelecionada != null) {
            int indice = receitas.indexOf(receitaSelecionada);
            tabReceitas.setRowSelectionInterval(indice, indice);
        }
    }

    private void preencherTabelaItens(Receita receita) {
        DefaultTableModel tabelaItens = (DefaultTableModel) tabItens.getModel();
        tabelaItens.setNumRows(0);

        if (receita == null) {
            return;
        }

        receita.getInsumos().forEach(item -> tabelaItens.addRow(new Object[]{
            item.getInsumo().getId(), item.getInsumo().getNome(), item.getInsumo().getValor(),
            item.getQuantidade(), item.getInsumo().getValor() * item.getQuantidade()
        }));
    }

    private void mensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAdicionarReceita;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnRemoverReceita;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbCliente;
    private javax.swing.JComboBox<String> cbInsumo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabItens;
    private javax.swing.JTable tabReceitas;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCpf;
    private javax.swing.JTextField txtDataEmissao;
    private javax.swing.JTextField txtInsumo;
    private javax.swing.JTextField txtNomeMedico;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JTextField txtValorTotal;
    // End of variables declaration//GEN-END:variables
}

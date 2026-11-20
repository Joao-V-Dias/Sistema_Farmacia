package com.joao.sistemafarmacia.view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public abstract class ListaPanel<T> extends javax.swing.JPanel {

    protected List<T> itens;

    public ListaPanel() {
        this.itens = new ArrayList<>();
    }

    public void carregarTabela() {
        try {
            itens = buscarItens();
            preencherTabela(itens);
            aposCarregar();
        } catch (RuntimeException ex) {
            mensagem(ex.getMessage());
        }
    }

    protected void aposCarregar() {
    }

    protected T buscarItem(JTable tabela, int linha) {
        return itens.get(tabela.convertRowIndexToModel(linha));
    }

    protected T buscarSelecionado(JTable tabela, String aviso) {
        int linha = tabela.getSelectedRow();
        if (linha < 0) {
            mensagem(aviso);
            return null;
        }
        return buscarItem(tabela, linha);
    }

    protected void mensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }

    protected abstract List<T> buscarItens();

    protected abstract void preencherTabela(List<T> itens);
}

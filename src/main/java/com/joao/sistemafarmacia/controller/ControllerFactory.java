package com.joao.sistemafarmacia.controller;

public class ControllerFactory {

    private static AuditoriaController auditoriaController;
    private static ClienteController clienteController;
    private static CompraController compraController;
    private static FinanceiroController financeiroController;
    private static InsumoController insumoController;
    private static ItensReceitaController itensReceitaController;
    private static ManipulacaoController manipulacaoController;
    private static PedidoController pedidoController;
    private static ReceitaController receitaController;
    private static SolicitacaoController solicitacaoController;
    private static UsuarioController usuarioController;

    public static Controller criar(String tipo) {
        switch (tipo) {
            case "auditoria":
                if (auditoriaController == null) {
                    auditoriaController = new AuditoriaController();
                }
                return auditoriaController;
            case "cliente":
                if (clienteController == null) {
                    clienteController = new ClienteController();
                }
                return clienteController;
            case "compra":
                if (compraController == null) {
                    compraController = new CompraController();
                }
                return compraController;
            case "financeiro":
                if (financeiroController == null) {
                    financeiroController = new FinanceiroController();
                }
                return financeiroController;
            case "insumo":
                if (insumoController == null) {
                    insumoController = new InsumoController();
                }
                return insumoController;
            case "itensReceita":
                if (itensReceitaController == null) {
                    itensReceitaController = new ItensReceitaController();
                }
                return itensReceitaController;
            case "manipulacao":
                if (manipulacaoController == null) {
                    manipulacaoController = new ManipulacaoController();
                }
                return manipulacaoController;
            case "pedido":
                if (pedidoController == null) {
                    pedidoController = new PedidoController();
                }
                return pedidoController;
            case "receita":
                if (receitaController == null) {
                    receitaController = new ReceitaController();
                }
                return receitaController;
            case "solicitacao":
                if (solicitacaoController == null) {
                    solicitacaoController = new SolicitacaoController();
                }
                return solicitacaoController;
            case "usuario":
                if (usuarioController == null) {
                    usuarioController = new UsuarioController();
                }
                return usuarioController;
            default:
                throw new IllegalArgumentException("TIPO DE CONTROLLER INVALIDO: " + tipo);
        }
    }
}

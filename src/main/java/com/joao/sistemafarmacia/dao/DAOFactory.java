package com.joao.sistemafarmacia.dao;

public class DAOFactory {

    public static DAO criar(String tipo) {
        switch (tipo) {
            case "cliente":
                return new ClienteDAO(
                        "INSERT INTO cliente (nome, telefone, rua, numero, cpf, data_nascimento) VALUES (?, ?, ?, ?, ?, ?)",
                        "SELECT * FROM cliente",
                        "UPDATE cliente SET nome=?, telefone=?, rua=?, numero=?, cpf=?, data_nascimento=? WHERE cliente_id=?",
                        "DELETE FROM cliente WHERE cliente_id=?");
            case "compra":
                return new CompraDAO(
                        "INSERT INTO compra (solicitacao_id, insumo_id, cnpj_fornecedor, numero_nota_fiscal, quantidade, valor, status) VALUES (?, ?, ?, ?, ?, ?, ?)",
                        "SELECT c.*, i.nome, i.valor AS valor_insumo, i.quantidade AS quantidade_insumo, i.minimo FROM compra c INNER JOIN insumo i ON i.insumo_id = c.insumo_id ORDER BY c.compra_id DESC",
                        "UPDATE compra SET solicitacao_id=?, insumo_id=?, cnpj_fornecedor=?, numero_nota_fiscal=?, quantidade=?, valor=?, status=? WHERE compra_id=?",
                        "DELETE FROM compra WHERE compra_id=?");
            case "insumo":
                return new InsumoDAO(
                        "INSERT INTO insumo (nome, valor, quantidade, minimo) VALUES (?, ?, ?, ?)",
                        "SELECT * FROM insumo",
                        "UPDATE insumo SET nome=?, valor=?, quantidade=?, minimo=? WHERE insumo_id=?",
                        "DELETE FROM insumo WHERE insumo_id=?");
            case "itensReceita":
                return new ItensReceitaDAO(
                        "INSERT INTO itens_receita (receita_id, insumo_id, quantidade) VALUES (?, ?, ?)",
                        "SELECT ir.itens_receita_id, ir.receita_id, ir.quantidade AS quantidade_item, i.* FROM itens_receita ir INNER JOIN insumo i ON i.insumo_id = ir.insumo_id",
                        "UPDATE itens_receita SET receita_id=?, insumo_id=?, quantidade=? WHERE itens_receita_id=?",
                        "DELETE FROM itens_receita WHERE itens_receita_id=?");
            case "pedido":
                return new PedidoDAO(
                        "INSERT INTO pedido (data_pedido, cliente_id, valor, status) VALUES (?, ?, ?, ?)",
                        "SELECT p.*, c.nome, c.cpf, c.telefone, c.rua, c.numero, c.data_nascimento FROM pedido p INNER JOIN cliente c ON c.cliente_id = p.cliente_id",
                        "UPDATE pedido SET data_pedido=?, cliente_id=?, valor=?, status=? WHERE pedido_id=?",
                        "DELETE FROM pedido WHERE pedido_id=?");
            case "receita":
                return new ReceitaDAO(
                        "INSERT INTO receita (codigo, nome_medico, data_emissao, data_entrada, pedido_id) VALUES (?, ?, ?, ?, ?)",
                        "SELECT * FROM receita",
                        "UPDATE receita SET codigo=?, nome_medico=?, data_emissao=?, data_entrada=?, pedido_id=? WHERE receita_id=?",
                        "DELETE FROM receita WHERE receita_id=?");
            case "solicitacao":
                return new SolicitacaoDAO(
                        "INSERT INTO solicitacao (insumo_id, quantidade, valor, data_solicitacao, status) VALUES (?, ?, ?, ?, ?)",
                        "SELECT s.*, i.nome, i.valor AS valor_insumo, i.quantidade AS quantidade_insumo, i.minimo FROM solicitacao s INNER JOIN insumo i ON i.insumo_id = s.insumo_id ORDER BY s.solicitacao_id DESC",
                        "UPDATE solicitacao SET insumo_id=?, quantidade=?, valor=?, data_solicitacao=?, status=? WHERE solicitacao_id=?",
                        "DELETE FROM solicitacao WHERE solicitacao_id=?");
            case "usuario":
                return new UsuarioDAO(
                        "INSERT INTO usuario (email, nome, senha, telefone, cpf, data_nascimento, cargo) VALUES (?, ?, ?, ?, ?, ?, ?)",
                        "SELECT usuario_id, nome, email, senha, cpf, telefone, data_nascimento, cargo FROM usuario",
                        "UPDATE usuario SET email=?, nome=?, senha=?, telefone=?, cpf=?, data_nascimento=?, cargo=? WHERE usuario_id=?",
                        "DELETE FROM usuario WHERE usuario_id=?");
            default:
                throw new IllegalArgumentException("TIPO DE DAO INVALIDO: " + tipo);
        }
    }
}

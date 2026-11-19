SET client_encoding = 'UTF8';

CREATE TABLE usuario (
    usuario_id      SERIAL PRIMARY KEY,
    nome            VARCHAR(150) NOT NULL,
    email           VARCHAR(150) NOT NULL,
    senha           VARCHAR(255) NOT NULL,
    cpf             VARCHAR(14) NOT NULL,
    telefone        VARCHAR(14) NOT NULL,
    cargo           VARCHAR(50) NOT NULL,
    data_nascimento DATE
);

CREATE TABLE cliente (
    cliente_id      SERIAL PRIMARY KEY,
    nome            VARCHAR(150) NOT NULL,
    cpf             VARCHAR(11) NOT NULL,
    telefone        VARCHAR(11) NOT NULL,
    rua             VARCHAR(100) NOT NULL,
    numero          VARCHAR(10) NOT NULL,
    data_nascimento DATE
);

CREATE TABLE insumo (
    insumo_id  BIGSERIAL PRIMARY KEY,
    nome       VARCHAR(100) NOT NULL,
    valor      NUMERIC(10, 2) NOT NULL DEFAULT 0 CHECK (valor >= 0),
    quantidade NUMERIC(10, 3) NOT NULL DEFAULT 0 CHECK (quantidade >= 0),
    minimo     NUMERIC(10, 3) NOT NULL DEFAULT 0 CHECK (minimo >= 0)
);

CREATE TABLE pedido (
    pedido_id   BIGSERIAL PRIMARY KEY,
    data_pedido DATE NOT NULL DEFAULT CURRENT_DATE,
    cliente_id  BIGINT NOT NULL REFERENCES cliente (cliente_id),
    valor       NUMERIC(10, 2) NOT NULL DEFAULT 0 CHECK (valor >= 0),
    observacao  VARCHAR(255),
    status      VARCHAR(30) NOT NULL
);

CREATE TABLE receita (
    receita_id   BIGSERIAL PRIMARY KEY,
    codigo       VARCHAR(50) NOT NULL,
    nome_medico  VARCHAR(100) NOT NULL,
    data_emissao DATE NOT NULL,
    data_entrada DATE NOT NULL DEFAULT CURRENT_DATE,
    pedido_id    BIGINT NOT NULL REFERENCES pedido (pedido_id) ON DELETE CASCADE,
    CHECK (data_emissao <= data_entrada)
);

CREATE TABLE itens_receita (
    itens_receita_id BIGSERIAL PRIMARY KEY,
    receita_id       BIGINT NOT NULL REFERENCES receita (receita_id) ON DELETE CASCADE,
    insumo_id        BIGINT NOT NULL REFERENCES insumo (insumo_id),
    quantidade       NUMERIC(10, 3) NOT NULL CHECK (quantidade > 0),
    UNIQUE (receita_id, insumo_id)
);

CREATE TABLE valor_estoque (
    valor_estoque_id INTEGER PRIMARY KEY,
    valor_total      NUMERIC(12, 2) NOT NULL DEFAULT 0,
    data_atualizacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE solicitacao (
    solicitacao_id   BIGSERIAL PRIMARY KEY,
    insumo_id        BIGINT NOT NULL REFERENCES insumo (insumo_id),
    quantidade       NUMERIC(10, 3) NOT NULL CHECK (quantidade > 0),
    valor            NUMERIC(10, 2) NOT NULL DEFAULT 0 CHECK (valor >= 0),
    data_solicitacao DATE NOT NULL DEFAULT CURRENT_DATE,
    status           VARCHAR(30) NOT NULL
);

CREATE TABLE compra (
    compra_id          BIGSERIAL PRIMARY KEY,
    solicitacao_id     BIGINT NOT NULL REFERENCES solicitacao (solicitacao_id),
    insumo_id          BIGINT NOT NULL REFERENCES insumo (insumo_id),
    cnpj_fornecedor    VARCHAR(18) NOT NULL,
    numero_nota_fiscal VARCHAR(50) NOT NULL,
    quantidade         NUMERIC(10, 3) NOT NULL CHECK (quantidade > 0),
    valor              NUMERIC(10, 2) NOT NULL DEFAULT 0 CHECK (valor >= 0),
    status             VARCHAR(30) NOT NULL
);

CREATE OR REPLACE FUNCTION atualizar_valor_estoque() RETURNS TRIGGER AS $$
BEGIN
    UPDATE valor_estoque
       SET valor_total = (SELECT COALESCE(SUM(valor * quantidade), 0) FROM insumo),
           data_atualizacao = CURRENT_TIMESTAMP
     WHERE valor_estoque_id = 1;
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_atualizar_valor_estoque
AFTER INSERT OR UPDATE OR DELETE ON insumo
FOR EACH STATEMENT
EXECUTE FUNCTION atualizar_valor_estoque();

INSERT INTO valor_estoque (valor_estoque_id, valor_total) VALUES (1, 0);
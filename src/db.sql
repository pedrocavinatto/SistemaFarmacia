CREATE DATABASE IF NOT EXISTS farmacia;

USE farmacia;

CREATE TABLE IF NOT EXISTS marcas (
    id INT(11) NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    cnpj VARCHAR(255) NOT NULL,
    telefone VARCHAR(30) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS metodos_pagamento (
    id INT(11) NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

INSERT INTO metodos_pagamento (nome) VALUES 
('Boleto'),
('Cartão de Débito'),
('Cartão de Crédito'),
('Dinheiro');

CREATE TABLE IF NOT EXISTS remedios (
    id INT(11) NOT NULL AUTO_INCREMENT,
    codigo_barras VARCHAR(255) NOT NULL UNIQUE,
    nome VARCHAR(255) NOT NULL,
    id_marca INT(11) NOT NULL,
    data_producao DATE NOT NULL,
    data_validade DATE NOT NULL,
    valor_custo DECIMAL(10, 2) NOT NULL,
    valor_venda DECIMAL(10, 2) NOT NULL,
    quantidade INT(11) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_marca) REFERENCES marcas(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS vendas (
    id INT(11) NOT NULL AUTO_INCREMENT,
    id_metodo_pagamento INT(11) NOT NULL,
    data_venda DATE NOT NULL,
    valor_total DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id_metodo_pagamento) REFERENCES metodos_pagamento(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS venda_remedios (
    id_venda INT(11) NOT NULL,
    id_remedio INT(11) NOT NULL,
    valor_venda DECIMAL(10, 2) NOT NULL,
    quantindade INT(11) NOT NULL,
    PRIMARY KEY (id_venda, id_remedio),
    FOREIGN KEY (id_venda) REFERENCES vendas(id),
    FOREIGN KEY (id_remedio) REFERENCES remedios(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;
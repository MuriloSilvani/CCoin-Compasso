/* Usuarios */

CREATE TABLE cargos(
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  descricao VARCHAR(50) NOT NULL
);

CREATE TABLE unidades(
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  uf VARCHAR(2) NOT NULL,
  cidade VARCHAR(50) NOT NULL,
  endereco VARCHAR(150) NOT NULL
);

CREATE TABLE niveis_acesso(
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  descricao VARCHAR(50) NOT NULL
);

CREATE TABLE usuarios(
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  login VARCHAR(20) UNIQUE NOT NULL,
  email VARCHAR(150) UNIQUE NOT NULL,
  senha VARCHAR(50) NOT NULL,
  nome VARCHAR(150) NOT NULL,
  id_nivel_acesso INTEGER NOT NULL,
  id_cargo INTEGER NOT NULL,
  id_unidade INTEGER NOT NULL,
  credito INTEGER DEFAULT 0,
  responsavel_unidade BOOLEAN DEFAULT FALSE,
  FOREIGN KEY (id_nivel_acesso) REFERENCES niveis_acesso(id),
  FOREIGN KEY (id_cargo) REFERENCES cargos(id),
  FOREIGN KEY (id_unidade) REFERENCES unidades(id)
);





/* Itens */

CREATE TABLE itens(
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  descricao VARCHAR(100) NOT NULL
);

CREATE TABLE tipos_itens(
 id INTEGER PRIMARY KEY AUTO_INCREMENT,
 descricao VARCHAR(50) NOT NULL
) COMMENT = 'Essa tabela é para diferenciar os tamanhos das camisas, os tipos das agendas';

CREATE TABLE estoque(
  id_item INTEGER NOT NULL,
  id_tipo_item INTEGER NOT NULL,
  qtde_reservado INTEGER DEFAULT 0,
  qtde_disponivel INTEGER NOT NULL,
  FOREIGN KEY (id_item) REFERENCES itens(id),
  FOREIGN KEY (id_tipo_item) REFERENCES tipos_itens(id)
) COMMENT = 'Tabala excluisava para itens fisicos, que precisam de estoque';







/*   Resgate e transferencia */
/* Select para ver os comentarios no from alterar para tabela desejada 
use resgates_transferencias;
show full columns from status_requerimentos*/

CREATE TABLE trasferencias(
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  usuario_origem INTEGER NOT NULL COMMENT 'Foreign Key para o banco de usuarios referenciando o ID',
  usuario_destino INTEGER NOT NULL COMMENT 'Foreign Key para o banco de usuarios referenciando o ID',
  valor INTEGER NOT NULL
);

CREATE TABLE resgates(
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  id_usuario INTEGER NOT NULL COMMENT 'Foreign Key para o banco de usuarios referenciando o ID'
);

CREATE TABLE status_requerimentos(
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  id_resgate INTEGER,
  id_transferencia INTEGER,
  id_status INTEGER NOT NULL COMMENT 'Valores padrão 0: em analise, 1: aprovado, 2: a caminho, 3: entregue, 4: cancelado, 5: Reprovado',
  data_status TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
  FOREIGN KEY (id_resgate) REFERENCES resgates(id),
  FOREIGN KEY (id_transferencia) REFERENCES trasferencias(id)
  
) COMMENT = 'Essa tabela irá receber ou uma foreign key para resgate ou uma para transferencia, não podendo receber as duas. Essa tabeal será para historico';

CREATE TABLE itens_resgates(
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  id_resgate INTEGER NOT NULL,
  id_item INTEGER NOT NULL COMMENT 'Foreign Key para o banco de itens referenciando a tabela de itens o ID',
  id_tipo_item INTEGER COMMENT 'Foreign Key para o banco de itens referenciando a tabela tipo_itens e o campo ID caso necessario',
  qtde INTEGER NOT NULL,
  data_agendamento TIMESTAMP COMMENT 'Data do agendamento do home office ou folga' ,
  FOREIGN KEY (id_resgate) REFERENCES resgates(id)
);
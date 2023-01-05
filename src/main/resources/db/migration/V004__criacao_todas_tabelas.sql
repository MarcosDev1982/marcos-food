create table forma_de_pagamento (
    id bigint not null auto_increment,
     descricao varchar(255),
     primary key (id)) engine=InnoDB;

create table foto_produto (
    id bigint not null auto_increment,
    content_type varchar(255),
    descricao varchar(255),
    tamanho bigint,
     primary key (id)) engine=InnoDB;

create table grupo (
    id bigint not null auto_increment,
    nome varchar(255),
    primary key (id)) engine=InnoDB;


create table hibernate_sequence (
    next_val bigint) engine=InnoDB;

insert into hibernate_sequence values ( 1 );

create table item_pedido (
    id bigint not null, observacao varchar(255),
        preco_total decimal(19,2), preco_unitario decimal(19,2),
        pedido_id bigint, produto_id bigint,
        primary key (id)) engine=InnoDB;

create table pedido (
    id bigint not null auto_increment,
    data_cancelamento datetime(6),
    data_confirmacao datetime(6),
    data_criacao datetime(6), data_entrega datetime(6),
    endereco_bairro varchar(255),
    endereco_cep varchar(255),
    endereco_complemento varchar(255),
    endereco_lougradouro varchar(255),
    endereco_numero varchar(255),
    status_pedido integer,
    taxa_frete decimal(19,2),
    valor_total decimal(19,2),
    cliente_id bigint,
    endereco_cidade_id bigint,
    forma_de_pagamento_id bigint,
    restaurante_id bigint,
    primary key (id)) engine=InnoDB;

create table permissao (
    id bigint not null auto_increment,
    descricao varchar(255),
     nome varchar(255),
      primary key (id)) engine=InnoDB;

create table produto (
    id bigint not null auto_increment,
    ativo bit, descricao varchar(255),
    nome varchar(255),
    preco decimal(19,2),
    restaurante_id bigint,
    primary key (id)) engine=InnoDB;

create table restaurante (
    id bigint not null auto_increment,
    data_atualizacao datetime not null,
    data_cadastro datetime not null,
    endereco_bairro varchar(255),
    endereco_cep varchar(255),
    endereco_complemento varchar(255),
    endereco_lougradouro varchar(255),
    endereco_numero varchar(255),
    nome varchar(255) not null,
    taxa_frete decimal(19,2) not null,
    cozinha_id bigint not null,
    endereco_cidade_id bigint,
    primary key (id)) engine=InnoDB;

create table restaurante_forma_pagamento (
    restaurante_id bigint not null,
    forma_pagamento_id bigint not null) engine=InnoDB;

create table restaurante_grupo_permissoes (
    grupo_id bigint not null,
    permisao_id bigint not null) engine=InnoDB;

create table restaurante_grupos (
    restaurante_id bigint not null,
    grupos_id bigint not null) engine=InnoDB;

create table restaurante_produtos (
    restaurante_id bigint not null,
    produtos_id bigint not null) engine=InnoDB;

create table restaurante_responsaveis (
    restaurante_id bigint not null,
     responsaveis_id bigint not null) engine=InnoDB;

create table user_grupos (
    user_id bigint not null,
    grupos_id bigint not null) engine=InnoDB;

create table usuario (
    id bigint not null auto_increment,
    data_cadastro datetime(6),
    email varchar(255) not null,
    nome varchar(255) not null,
    senha varchar(255) not null, primary key (id)) engine=InnoDB;

alter table restaurante_produtos add constraint fk_restuarante_produto unique (produtos_id);
alter table restaurante_responsaveis add constraint fk_restaurante_responsavies unique (responsaveis_id);
alter table cidade add constraint fk_cidade_estado_id foreign key (estado_id) references estado (id);
alter table item_pedido add constraint fk_item_pedido foreign key (pedido_id) references pedido (id);
alter table item_pedido add constraint fk_item_pedido_produto foreign key (produto_id) references produto (id);
alter table pedido add constraint fk_pedido_usuario foreign key (cliente_id) references usuario (id);
alter table pedido add constraint fk_pedido_cidade foreign key (endereco_cidade_id) references cidade (id);
alter table pedido add constraint fk_pedido_forma_pagamento foreign key (forma_de_pagamento_id) references forma_de_pagamento (id);
alter table pedido add constraint fk_pedido_restaurante foreign key (restaurante_id) references restaurante (id);
alter table produto add constraint fk_produto_restaurante foreign key (restaurante_id) references restaurante (id);
alter table restaurante add constraint fk_restaurante_cozinha foreign key (cozinha_id) references cozinha (id);
alter table restaurante add constraint fk_restaurante_cidade foreign key (endereco_cidade_id) references cidade (id);
alter table restaurante_forma_pagamento add constraint fk_restuarante_forma_pagamento foreign key (forma_pagamento_id) references forma_de_pagamento (id);
alter table restaurante_forma_pagamento add constraint fk_restuarante_forma_pagamento_restaurante  foreign key (restaurante_id) references restaurante (id);
alter table restaurante_grupo_permissoes add constraint fk_restuarante_grupo_permissao foreign key (permisao_id) references permissao (id);
alter table restaurante_grupo_permissoes add constraint fk_restuarante_permissoes_grupo foreign key (grupo_id) references grupo (id);
alter table restaurante_grupos add constraint fk_restaurante_grupos foreign key (grupos_id) references grupo (id);
alter table restaurante_grupos add constraint restaurante_grupos_restuarante foreign key (restaurante_id) references restaurante (id);
alter table restaurante_produtos add constraint fk_restaurante_produtos_produto foreign key (produtos_id) references produto (id);
alter table restaurante_produtos add constraint fk_restaurante_produtos_restuarante  foreign key (restaurante_id) references restaurante (id);
alter table restaurante_responsaveis add constraint fk_restaurante_responsaveis_usuario foreign key (responsaveis_id) references usuario (id);
alter table restaurante_responsaveis add constraint restaurante_responsaveis_restuarante foreign key (restaurante_id) references restaurante (id);
alter table user_grupos add constraint fk_user_grupos_grupos foreign key (grupos_id) references grupo (id);
alter table user_grupos add constraint user_grupos_user foreign key (user_id) references usuario (id);
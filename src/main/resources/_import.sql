insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');
insert into cozinha (id, nome) values (1, 'Argentina');
insert into cozinha (id, nome) values (2, 'Brasileira');


insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'Campinas',2);
insert into cidade (id, nome, estado_id) values (4, 'São Paulo',2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza',3);

insert into restaurante (nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_bairro, endereco_cep,endereco_complemento, endereco_numero, endereco_lougradouro, data_cadastro, data_atualizacao)  values ('Reibugger', 15.00, 1, 1, 'Centro', '38400-900', 'casa', '1000', 'Rua Jão Pinheiro', utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('MorroDefome', 25.00, 1, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Comida barata',35.00, 2, utc_timestamp, utc_timestamp);



insert into forma_de_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_de_pagamento (id, descricao) values (2, 'Cartao de débito');
insert into forma_de_pagamento (id, descricao) values (3, 'Dinherio');

insert into permissao(id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinha');
insert into permissao(id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite consultar cozinha');


insert into restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) values (1,1),(1,2),(1,3), (2,3), (3,2),(3,3);







App Web de cadastro de Pacientes e Enfermeiro 
Com as  4 opções do CRUD ( inserir , remover , atualizar e pesquisar ) 
Para salvar os dados como CPF e  Senha foi utilizado criptografia para ficar de acordo com a LGPD 
Senhas foi usado criptografia de "uma via" que  após criptografar não conseguimos voltar para a senha original , para login é comparado o HASH da senha
CPF como é um dado que vai precisar dele sem ser criptografado , foi usado uma criptografia que é possivel descriptografar .

## INSERT INICIAL para cadastrar usuario com permissao nivel medico 
insert into enfermeiro (cpf,nome,senha)values ('MTIzLjQ1Ni43ODktOTk=','medico','$2a$10$pScqXfx.2Bt/wKeNdLA9G.c14vmMY27rBAi1S/rsI9EJ7LidMrZrG');


insert into permissoes (id_permissao,permissao_nome)values (1,'PERMISSAO_MEDICO');
insert into permissoes (id_permissao,permissao_nome)values (2,'PERMISSAO_USER');


insert into permissoes_usuarios(cpf,id_permissao) values ('MTIzLjQ1Ni43ODktOTk=',1);

usuario: medico
senha: 123456
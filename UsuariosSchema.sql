create table usuarios(
id serial primary key,
nome varchar(100),
cpf varchar(11)Not null unique,
email varchar(100),
senha varchar(100) NOT null
);

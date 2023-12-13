create table flora(
    id bigint not null auto_increment,
    nome varchar(100) not null,
    nomeCientifico varchar(100) not null unique,
    descricao varchar(1000) not null ,
    tipo varchar(100) not null,
    imagem varchar(100) not null,


    primary key(id)

);

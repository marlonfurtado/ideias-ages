create table user
(
  id int not null auto_increment
    primary key,
  cpf varchar(11) not null,
  email varchar(100) not null,
  name varchar(100) null,
  password longtext not null,
  phone varchar(20) null,
  role_id int not null,
  active smallint(6) not null,
  constraint user_cpf_uindex
  unique (cpf)
);
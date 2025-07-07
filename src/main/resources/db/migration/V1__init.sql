create table cliente (
  id_cliente bigserial not null,
  nome       varchar(256),
  documento  bigint not null,
  constraint cliente_pkey primary key (id_cliente),
  constraint cliente_documento_uk unique (documento)
);

create table pedido (
  id_pedido bigserial not null,
  code                bigint not null,
  id_cliente          bigint not null,
  status       varchar(256) ,
  data          timestamp with time zone,
  constraint pedido_pkey primary key (id_pedido)


);

create table item (
  id_item          bigserial not null,
  quantidade        int not null,
  nome              varchar(128) not null,
  id_cliente              bigint not null,
  id_pedido     bigint not null,
  preco        float4 not null,

  constraint item_pkey primary key (id_item),
  constraint item_cliente_id_cliente_fk foreign key (id_cliente) references cliente
);

alter table productos
add column ubicacion varchar(60);

alter table productos
add column stock_minimo numeric(19,2) NOT NULL default 0;

alter table productos
add column stock_actual numeric(19,2) NOT NULL default 0;

alter table productos
add constraint unique_codigo_propio unique(codigo_propio);

alter table productos
add column codigo_fabricante varchar(60);
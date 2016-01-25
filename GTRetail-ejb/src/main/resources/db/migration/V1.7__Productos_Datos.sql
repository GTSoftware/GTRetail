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

insert into parametros values ('venta.pos.id_lista','1','El ID de la lista a utilizar en una venta tipo POS',0);
insert into parametros values ('venta.pos.redondear.cant_decimales','2','La cantidad de decimales a redondear en una venta tipo POS',0);
insert into parametros values ('venta.pos.redondeo.id_producto','1','El ID de producto a utilizar para el redondeo',0);
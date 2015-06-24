INSERT INTO parametros (nombre_parametro, valor_parametro, descripcion_parametro) VALUES
('producto.descuento-recargo.codigo',	'0',	'El ID del producto que representa un descuento o un recargo');

alter table ventas_lineas
add column descripcion varchar(200) not null default 'NO DESC';

update ventas_lineas set descripcion = (select pr.descripcion from productos pr where pr.id_producto = ventas_lineas.id_producto);

create table afip_auth_services(
nombre_servicio varchar(30)  primary key,
token varchar(1024),
sign varchar(255),
fecha_expiracion timestamp,
version integer not null default 0);

comment on table afip_auth_services is 'Parámetros de autorización para cada servicio web de AFIP';

--Parámetros para la configuración
insert into parametros values ('afip.wsaa.endpoint','https://wsaahomo.afip.gov.ar/ws/services/LoginCms','La URL para acceder al servicio de autorización para servicios web de AFIP',0);

insert into parametros values ('afip.wsaa.cert.path','/home/rodrigo/certs/afip/alias.p12','La ruta al certificado en formato pkcs12',0);
insert into parametros values ('afip.wsaa.cert.password','soloio','La clave para descifrar el certificado',0);
insert into parametros values ('afip.wsaa.dn','cn=wsaahomo,o=afip,c=ar,serialNumber=CUIT 33693450239','El DN para acceder al servicio',0);
/*
 * Copyright 2017 GT Software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * Author:  Rodrigo
 * Created: Dec 02, 2017
 */

drop table if exists proveedores_ordenes_compra_lineas;
drop table if exists proveedores_ordenes_compra;

create table proveedores_ordenes_compra_estados (
    id_estado_orden_compra integer primary key,
    nombre_estado_orden_compra varchar(50) not null,
    version integer NOT NULL DEFAULT 0
);


INSERT INTO proveedores_ordenes_compra_estados (id_estado_orden_compra, nombre_estado_orden_compra, version)
VALUES (1, 'DISEÑO', 0);
INSERT INTO proveedores_ordenes_compra_estados (id_estado_orden_compra, nombre_estado_orden_compra, version)
VALUES (2, 'PENDIENTE DE RECEPCIÓN', 0);
INSERT INTO proveedores_ordenes_compra_estados (id_estado_orden_compra, nombre_estado_orden_compra, version)
VALUES (3, 'RECEPCIÓN PARCIAL', 0);
INSERT INTO proveedores_ordenes_compra_estados (id_estado_orden_compra, nombre_estado_orden_compra, version)
VALUES (4, 'RECEPCIÓN TOTAL', 0);
INSERT INTO proveedores_ordenes_compra_estados (id_estado_orden_compra, nombre_estado_orden_compra, version)
VALUES (5, 'ANULADA', 0);

comment on table proveedores_ordenes_compra_estados is 'Los posibles estados de las órdenes de compra';

create table proveedores_ordenes_compra (
    id_orden_compra serial primary key,
    id_proveedor integer not null, --El proveedor al cual se le realiza el pedido
    fecha_alta timestamp without time zone NOT NULL,
    id_usuario integer NOT NULL, --El usuario que carga la OC
    id_transporte integer, --El proveedor que sería la empresa de transportes que se encarga de traer la mercadería.
    id_estado_orden_compra integer not null,
    observaciones text,
    fecha_estimada_recepcion date,
    version integer not null default 0,
    monto_iva_total numeric(19,2) not null,
    total_orden_compra numeric(19,2) not null,

    CONSTRAINT proveedores_ordenes_compra_id_proveedor_fk FOREIGN KEY (id_proveedor)
      REFERENCES personas (id_persona) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT proveedores_ordenes_compra_id_transporte_fk FOREIGN KEY (id_transporte)
      REFERENCES personas (id_persona) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT proveedores_ordenes_compra_id_estado_orden_compra_fk FOREIGN KEY (id_estado_orden_compra)
      REFERENCES proveedores_ordenes_compra_estados (id_estado_orden_compra) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT proveedores_ordenes_compra_id_usuario_fk FOREIGN KEY (id_usuario)
      REFERENCES usuarios (id_usuario) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


create table proveedores_ordenes_compra_porcentajes (
    id_orden_compra_porcentaje serial primary key,
    id_orden_compra integer not null,
    id_tipo_porcentaje integer not null,
    valor numeric(19,4) not null,
    version integer not null default 0,

     CONSTRAINT proveedores_ordenes_compra_porcentajes_id_orden_compra_fk FOREIGN KEY (id_orden_compra)
      REFERENCES proveedores_ordenes_compra (id_orden_compra) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
     CONSTRAINT proveedores_ordenes_compra_porcentajes_id_tipo_porcentaje_fk FOREIGN KEY (id_tipo_porcentaje)
      REFERENCES productos_tipos_porcentajes(id_tipo_porcentaje) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
comment on table proveedores_ordenes_compra_porcentajes is 'Los porcentajes que afectan a la orden de compra en general';


create table proveedores_ordenes_compra_lineas (
    id_orden_compra_linea serial primary key,
    id_orden_compra integer not null,
    id_producto integer not null,
    cantidad_pedida numeric(19,2) not null,
    id_tipo_unidad integer not null,
    cantidad_recibida numeric(19,2) not null,
    precio_compra_unitario numeric(19,4) not null,
    subtotal_compra numeric(19,4) not null,
    version integer not null default 0,
    CONSTRAINT proveedores_ordenes_compra_lineas_id_orden_compra_fk FOREIGN KEY (id_orden_compra)
      REFERENCES proveedores_ordenes_compra (id_orden_compra) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT proveedores_ordenes_compra_lineas_id_producto_fk FOREIGN KEY (id_producto)
      REFERENCES productos (id_producto) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT proveedores_ordenes_compra_lineas_id_tipo_unidad_fk FOREIGN KEY (id_tipo_unidad)
      REFERENCES productos_tipos_unidades(id_tipo_unidad) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION

);

create table proveedores_ordenes_compra_lineas_porcentajes(
    id_orden_compra_linea_porcentaje serial primary key,
    id_orden_compra_linea integer not null,
    id_tipo_porcentaje integer not null,
    valor numeric(19,4) not null,
    version integer not null default 0,

     CONSTRAINT proveedores_ordenes_compra_lineas_porcentajes_id_orden_compra_linea_fk FOREIGN KEY (id_orden_compra_linea)
      REFERENCES proveedores_ordenes_compra_lineas (id_orden_compra_linea) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
     CONSTRAINT proveedores_ordenes_compra_lineas_porcentajes_id_tipo_porcentaje_fk FOREIGN KEY (id_tipo_porcentaje)
      REFERENCES productos_tipos_porcentajes(id_tipo_porcentaje) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

comment on table proveedores_ordenes_compra_lineas_porcentajes is 'Los porcentajes que afectan al precio unitario de cada línea de la orden de compra';




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
 * Author:  fede
 * Created: Apr 22, 2017
 */

CREATE TABLE productos_x_depositos
(
  id_producto_x_deposito integer NOT NULL DEFAULT nextval('producto_x_deposito_id_producto_x_deposito_seq'::regclass),
  id_producto integer NOT NULL,
  id_deposito integer,
  stock numeric(19,2),
  CONSTRAINT pk_producto_x_deposito PRIMARY KEY (id_producto_x_deposito),
  CONSTRAINT fk_deposito FOREIGN KEY (id_deposito)
      REFERENCES depositos (id_deposito) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_productos FOREIGN KEY (id_producto)
      REFERENCES productos (id_producto) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "unique" UNIQUE (id_producto, id_deposito)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE productos_x_depositos
  OWNER TO retail;


CREATE TABLE remito_movimientos_tipos
(
  id_tipo_movimiento integer NOT NULL DEFAULT nextval('stock_movimientos_tipos_id_tipo_movimiento_seq'::regclass),
  nombre_tipo character varying(60) NOT NULL,
  version integer NOT NULL DEFAULT 0,
  CONSTRAINT stock_movimientos_tipos_pkey PRIMARY KEY (id_tipo_movimiento)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE remito_movimientos_tipos
  OWNER TO retail;



CREATE TABLE remitos
(
  id_remito integer NOT NULL DEFAULT nextval('remito_cabecera_id_remito_cabecera_seq'::regclass),
  id_origen_externo integer, -- cuando es un origen externo se va a hacer referencia a una persona, osea a un proveedor.
  fecha_alta timestamp without time zone NOT NULL,
  id_usuario integer NOT NULL,
  observaciones text,
  origen_is_interno boolean,
  id_origen_interno integer, -- cuando el origen es interno, se hace refenrencia a un deposito de la empresa
  destino_previsto_interno integer,
  destino_previsto_externo integer,
  destino_is_interno boolean,
  fecha_cierre time without time zone,
  id_tipo_movimiento integer NOT NULL,
  version integer NOT NULL DEFAULT 0,
  CONSTRAINT pk_remito_cabecera PRIMARY KEY (id_remito),
  CONSTRAINT fk_destino_persona FOREIGN KEY (destino_previsto_externo)
      REFERENCES personas (id_persona) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_destino_previsto_interno FOREIGN KEY (destino_previsto_interno)
      REFERENCES depositos (id_deposito) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_mercaderia_deposito FOREIGN KEY (id_origen_interno)
      REFERENCES depositos (id_deposito) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_mercaderia_persona FOREIGN KEY (id_origen_externo)
      REFERENCES personas (id_persona) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_remito_cabecera_usuario FOREIGN KEY (id_usuario)
      REFERENCES usuarios (id_usuario) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_remitos_movimientos_tipos FOREIGN KEY (id_tipo_movimiento)
      REFERENCES remito_movimientos_tipos (id_tipo_movimiento) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE remitos
  OWNER TO retail;
COMMENT ON COLUMN remitos.id_origen_externo IS 'cuando es un origen externo se va a hacer referencia a una persona, osea a un proveedor.';
COMMENT ON COLUMN remitos.id_origen_interno IS 'cuando el origen es interno, se hace refenrencia a un deposito de la empresa';


CREATE TABLE remito_detalle
(
  id_remito_detalle serial NOT NULL,
  id_remito integer NOT NULL,
  id_producto integer NOT NULL,
  cantidad numeric(19,2) DEFAULT 0,
  CONSTRAINT pk_remito_detalle PRIMARY KEY (id_remito_detalle),
  CONSTRAINT fk_remito_detalle_producto FOREIGN KEY (id_producto)
      REFERENCES productos (id_producto) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_remito_detalle_remito_cabecera FOREIGN KEY (id_remito)
      REFERENCES remitos (id_remito) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE remito_detalle
  OWNER TO retail;


CREATE TABLE remito_recepciones
(
  id_recepcion serial NOT NULL,
  id_remito integer NOT NULL,
  fecha time without time zone NOT NULL,
  id_usuario integer NOT NULL,
  id_persona integer, -- si el remito tiene salida, este campo no debe ser nulo, ya que debe ser entregado a una persona
  id_deposito integer, -- este campo no es nulo cuando el remito tiene como destino previsto un deposito.
  CONSTRAINT pk_remito_recepciones PRIMARY KEY (id_recepcion),
  CONSTRAINT fk_persona FOREIGN KEY (id_persona)
      REFERENCES personas (id_persona) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_remito FOREIGN KEY (id_remito)
      REFERENCES remitos (id_remito) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_usuario FOREIGN KEY (id_usuario)
      REFERENCES usuarios (id_usuario) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT id_deposito FOREIGN KEY (id_deposito)
      REFERENCES depositos (id_deposito) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE remito_recepciones
  OWNER TO retail;
COMMENT ON COLUMN remito_recepciones.id_persona IS 'si el remito tiene salida, este campo no debe ser nulo, ya que debe ser entregado a una persona';
COMMENT ON COLUMN remito_recepciones.id_deposito IS 'este campo no es nulo cuando el remito tiene como destino previsto un deposito.';

INSERT INTO remito_movimientos_tipos (id_tipo_movimiento, nombre_tipo, version) VALUES (1, 'RECEPCIÓN DE PROVEEDOR', 0);
INSERT INTO remito_movimientos_tipos (id_tipo_movimiento, nombre_tipo, version) VALUES (2, 'VENTA', 0);
INSERT INTO remito_movimientos_tipos (id_tipo_movimiento, nombre_tipo, version) VALUES (3, 'TRASLADO', 0);
INSERT INTO remito_movimientos_tipos (id_tipo_movimiento, nombre_tipo, version) VALUES (4, 'AJUSTES', 0);
INSERT INTO remito_movimientos_tipos (id_tipo_movimiento, nombre_tipo, version) VALUES (6, 'BAJA POR MAL ESTADO', 0);
INSERT INTO remito_movimientos_tipos (id_tipo_movimiento, nombre_tipo, version) VALUES (5, 'BAJA POR ROBO/PÉRDIDA', 0);
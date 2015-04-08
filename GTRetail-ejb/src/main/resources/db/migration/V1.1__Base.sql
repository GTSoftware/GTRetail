--
-- PostgreSQL database dump
--


--
-- Name: bancos; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE bancos (
    id_banco integer NOT NULL,
    razon_social character varying(200) NOT NULL,
    id_pais integer NOT NULL,
    id_provincia integer NOT NULL,
    id_localidad integer NOT NULL,
    direccion character varying(500),
    telefono_fijo character varying(20),
    celular character varying(20),
    cuit character varying(11) NOT NULL,
    id_responsabilidad_iva integer NOT NULL,
    fecha_alta timestamp without time zone NOT NULL,
    activo boolean DEFAULT true NOT NULL,
    observaciones character varying(1024)
);


ALTER TABLE public.bancos OWNER TO retail;

--
-- Name: bancos_cuenta_corriente; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE bancos_cuenta_corriente (
    id_movimiento integer NOT NULL,
    id_cuenta_banco integer NOT NULL,
    fecha_movimiento timestamp without time zone NOT NULL,
    importe_movimiento numeric(19,2) NOT NULL,
    descripcion_movimiento character varying(200) NOT NULL,
    id_registro_contable integer NOT NULL
);


ALTER TABLE public.bancos_cuenta_corriente OWNER TO retail;

--
-- Name: bancos_cuenta_corriente_id_movimiento_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE bancos_cuenta_corriente_id_movimiento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bancos_cuenta_corriente_id_movimiento_seq OWNER TO retail;

--
-- Name: bancos_cuenta_corriente_id_movimiento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE bancos_cuenta_corriente_id_movimiento_seq OWNED BY bancos_cuenta_corriente.id_movimiento;


--
-- Name: bancos_cuentas; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE bancos_cuentas (
    id_cuenta_banco integer NOT NULL,
    id_banco integer NOT NULL,
    descripcion_cuenta character varying(200),
    numero_cuenta character varying(60) NOT NULL,
    numero_sucursal character varying(10),
    cbu character varying(22),
    activo boolean NOT NULL,
    fecha_apertura timestamp without time zone,
    id_moneda integer NOT NULL,
    id_tipo_cuenta_banco integer NOT NULL
);


ALTER TABLE public.bancos_cuentas OWNER TO retail;

--
-- Name: bancos_cuentas_id_cuenta_banco_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE bancos_cuentas_id_cuenta_banco_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bancos_cuentas_id_cuenta_banco_seq OWNER TO retail;

--
-- Name: bancos_cuentas_id_cuenta_banco_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE bancos_cuentas_id_cuenta_banco_seq OWNED BY bancos_cuentas.id_cuenta_banco;


--
-- Name: bancos_id_banco_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE bancos_id_banco_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bancos_id_banco_seq OWNER TO retail;

--
-- Name: bancos_id_banco_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE bancos_id_banco_seq OWNED BY bancos.id_banco;


--
-- Name: bancos_tipos_cuenta; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE bancos_tipos_cuenta (
    id_tipo_cuenta_banco integer NOT NULL,
    nombre_tipo_cuenta character varying(60) NOT NULL
);


ALTER TABLE public.bancos_tipos_cuenta OWNER TO retail;

--
-- Name: bancos_tipos_cuenta_id_tipo_cuenta_banco_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE bancos_tipos_cuenta_id_tipo_cuenta_banco_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bancos_tipos_cuenta_id_tipo_cuenta_banco_seq OWNER TO retail;

--
-- Name: bancos_tipos_cuenta_id_tipo_cuenta_banco_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE bancos_tipos_cuenta_id_tipo_cuenta_banco_seq OWNED BY bancos_tipos_cuenta.id_tipo_cuenta_banco;


--
-- Name: cajas; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE cajas (
    id_caja integer NOT NULL,
    nombre_caja character varying(60) NOT NULL,
    id_sucursal integer NOT NULL,
    fecha_alta timestamp without time zone NOT NULL,
    activo boolean DEFAULT true NOT NULL
);


ALTER TABLE public.cajas OWNER TO retail;

--
-- Name: cajas_categorias_movimientos; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE cajas_categorias_movimientos (
    id_categoria_movimiento integer NOT NULL,
    nombre_categoria_movimiento character varying(100) NOT NULL
);


ALTER TABLE public.cajas_categorias_movimientos OWNER TO retail;

--
-- Name: cajas_categorias_movimientos_id_categoria_movimiento_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE cajas_categorias_movimientos_id_categoria_movimiento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cajas_categorias_movimientos_id_categoria_movimiento_seq OWNER TO retail;

--
-- Name: cajas_categorias_movimientos_id_categoria_movimiento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE cajas_categorias_movimientos_id_categoria_movimiento_seq OWNED BY cajas_categorias_movimientos.id_categoria_movimiento;


--
-- Name: cajas_id_caja_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE cajas_id_caja_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cajas_id_caja_seq OWNER TO retail;

--
-- Name: cajas_id_caja_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE cajas_id_caja_seq OWNED BY cajas.id_caja;


--
-- Name: cajas_movimientos; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE cajas_movimientos (
    id_movimiento_caja integer NOT NULL,
    fecha_movimiento timestamp without time zone NOT NULL,
    importe_movimiento numeric(19,2),
    saldo_acumulado numeric(19,2) NOT NULL,
    id_forma_pago integer NOT NULL,
    id_categoria_movimiento integer NOT NULL,
    observaciones character varying(255),
    id_usuario integer NOT NULL,
    id_caja integer NOT NULL
);


ALTER TABLE public.cajas_movimientos OWNER TO retail;

--
-- Name: cajas_movimientos_id_movimiento_caja_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE cajas_movimientos_id_movimiento_caja_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cajas_movimientos_id_movimiento_caja_seq OWNER TO retail;

--
-- Name: cajas_movimientos_id_movimiento_caja_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE cajas_movimientos_id_movimiento_caja_seq OWNED BY cajas_movimientos.id_movimiento_caja;


--
-- Name: contabilidad_libros; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE contabilidad_libros (
    id_libro integer NOT NULL,
    nombre_libro character varying(100) NOT NULL,
    descripcion_libro character varying(255)
);


ALTER TABLE public.contabilidad_libros OWNER TO retail;

--
-- Name: contabilidad_libros_id_libro_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE contabilidad_libros_id_libro_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contabilidad_libros_id_libro_seq OWNER TO retail;

--
-- Name: contabilidad_libros_id_libro_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE contabilidad_libros_id_libro_seq OWNED BY contabilidad_libros.id_libro;


--
-- Name: contabilidad_monedas; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE contabilidad_monedas (
    id_moneda integer NOT NULL,
    nombre_moneda character varying(100) NOT NULL,
    nombre_corto_moneda character varying(10),
    simbolo_moneda character varying(5)
);


ALTER TABLE public.contabilidad_monedas OWNER TO retail;

--
-- Name: contabilidad_monedas_id_moneda_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE contabilidad_monedas_id_moneda_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contabilidad_monedas_id_moneda_seq OWNER TO retail;

--
-- Name: contabilidad_monedas_id_moneda_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE contabilidad_monedas_id_moneda_seq OWNED BY contabilidad_monedas.id_moneda;


--
-- Name: contabilidad_periodos_contables; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE contabilidad_periodos_contables (
    id_periodo_contable integer NOT NULL,
    nombre_periodo character varying(100) NOT NULL,
    fecha_inicio_periodo timestamp without time zone NOT NULL,
    fecha_fin_periodo timestamp without time zone NOT NULL
);


ALTER TABLE public.contabilidad_periodos_contables OWNER TO retail;

--
-- Name: contabilidad_periodos_contables_id_periodo_contable_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE contabilidad_periodos_contables_id_periodo_contable_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contabilidad_periodos_contables_id_periodo_contable_seq OWNER TO retail;

--
-- Name: contabilidad_periodos_contables_id_periodo_contable_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE contabilidad_periodos_contables_id_periodo_contable_seq OWNED BY contabilidad_periodos_contables.id_periodo_contable;


--
-- Name: contabilidad_plan_cuentas; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE contabilidad_plan_cuentas (
    id_cuenta integer NOT NULL,
    nombre_cuenta character varying(200) NOT NULL,
    numero_cuenta character varying(100),
    id_cuenta_padre integer,
    descripcion_cuenta character varying(2000),
    cuenta_rubro boolean NOT NULL,
    id_tipo_cuenta integer DEFAULT 1 NOT NULL
);


ALTER TABLE public.contabilidad_plan_cuentas OWNER TO retail;

--
-- Name: contabilidad_plan_cuentas_id_cuenta_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE contabilidad_plan_cuentas_id_cuenta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contabilidad_plan_cuentas_id_cuenta_seq OWNER TO retail;

--
-- Name: contabilidad_plan_cuentas_id_cuenta_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE contabilidad_plan_cuentas_id_cuenta_seq OWNED BY contabilidad_plan_cuentas.id_cuenta;


--
-- Name: contabilidad_registro_contable; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE contabilidad_registro_contable (
    id_registro integer NOT NULL,
    id_libro integer NOT NULL,
    fecha_proceso timestamp without time zone NOT NULL,
    usuario character varying(100),
    id_tipo_comprobante integer NOT NULL,
    letra_comprobante character varying(10),
    punto_venta_comprobante character varying(10),
    numero_comprobante character varying(100),
    cuit_emisor_comprobante character varying(11),
    cuit_receptor_comprobante character varying(11),
    fecha_comprobante timestamp without time zone,
    fecha_vencimiento timestamp without time zone,
    id_periodo_contable integer,
    id_periodo_fiscal integer,
    concepto_comprobante character varying(2000),
    id_tipo_operacion integer
);


ALTER TABLE public.contabilidad_registro_contable OWNER TO retail;

--
-- Name: contabilidad_registro_contable_id_registro_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE contabilidad_registro_contable_id_registro_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contabilidad_registro_contable_id_registro_seq OWNER TO retail;

--
-- Name: contabilidad_registro_contable_id_registro_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE contabilidad_registro_contable_id_registro_seq OWNED BY contabilidad_registro_contable.id_registro;


--
-- Name: contabilidad_registro_contable_lineas; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE contabilidad_registro_contable_lineas (
    id_linea_registro integer NOT NULL,
    id_registro_contable integer NOT NULL,
    id_cuenta integer NOT NULL,
    descripcion_linea character varying(1024),
    cantidad numeric(19,2) NOT NULL,
    unidad_medida character varying(20),
    fecha_vencimiento timestamp without time zone NOT NULL,
    importe_debe numeric(19,2) NOT NULL,
    importe_haber numeric(19,2) NOT NULL
);


ALTER TABLE public.contabilidad_registro_contable_lineas OWNER TO retail;

--
-- Name: contabilidad_registro_contable_lineas_id_linea_registro_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE contabilidad_registro_contable_lineas_id_linea_registro_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contabilidad_registro_contable_lineas_id_linea_registro_seq OWNER TO retail;

--
-- Name: contabilidad_registro_contable_lineas_id_linea_registro_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE contabilidad_registro_contable_lineas_id_linea_registro_seq OWNED BY contabilidad_registro_contable_lineas.id_linea_registro;


--
-- Name: contabilidad_tipos_comprobantes; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE contabilidad_tipos_comprobantes (
    id_tipo_comprobante integer NOT NULL,
    nombre_tipo character varying(100) NOT NULL,
    descripcion_tipo character varying(2000)
);


ALTER TABLE public.contabilidad_tipos_comprobantes OWNER TO retail;

--
-- Name: contabilidad_tipos_comprobantes_id_tipo_comprobante_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE contabilidad_tipos_comprobantes_id_tipo_comprobante_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contabilidad_tipos_comprobantes_id_tipo_comprobante_seq OWNER TO retail;

--
-- Name: contabilidad_tipos_comprobantes_id_tipo_comprobante_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE contabilidad_tipos_comprobantes_id_tipo_comprobante_seq OWNED BY contabilidad_tipos_comprobantes.id_tipo_comprobante;


--
-- Name: contabilidad_tipos_cuenta; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE contabilidad_tipos_cuenta (
    id_tipo_cuenta integer NOT NULL,
    nombre_tipo character varying(60) NOT NULL,
    descripcion_tipo character varying(255)
);


ALTER TABLE public.contabilidad_tipos_cuenta OWNER TO retail;

--
-- Name: contabilidad_tipos_cuenta_id_tipo_cuenta_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE contabilidad_tipos_cuenta_id_tipo_cuenta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contabilidad_tipos_cuenta_id_tipo_cuenta_seq OWNER TO retail;

--
-- Name: contabilidad_tipos_cuenta_id_tipo_cuenta_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE contabilidad_tipos_cuenta_id_tipo_cuenta_seq OWNED BY contabilidad_tipos_cuenta.id_tipo_cuenta;


--
-- Name: contabilidad_tipos_operacion; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE contabilidad_tipos_operacion (
    id_tipo_operacion integer NOT NULL,
    nombre_tipo_operacion character varying(100) NOT NULL,
    descripcion_tipo_operacion character varying(2000)
);


ALTER TABLE public.contabilidad_tipos_operacion OWNER TO retail;

--
-- Name: contabilidad_tipos_operacion_id_tipo_operacion_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE contabilidad_tipos_operacion_id_tipo_operacion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contabilidad_tipos_operacion_id_tipo_operacion_seq OWNER TO retail;

--
-- Name: contabilidad_tipos_operacion_id_tipo_operacion_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE contabilidad_tipos_operacion_id_tipo_operacion_seq OWNED BY contabilidad_tipos_operacion.id_tipo_operacion;


--
-- Name: depositos; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE depositos (
    id_deposito integer NOT NULL,
    nombre_deposito character varying(60) NOT NULL,
    id_sucursal integer,
    id_pais integer NOT NULL,
    id_provincia integer NOT NULL,
    id_localidad integer NOT NULL,
    direccion character varying(500),
    fecha_alta timestamp without time zone NOT NULL,
    activo boolean DEFAULT true NOT NULL
);


ALTER TABLE public.depositos OWNER TO retail;

--
-- Name: depositos_id_deposito_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE depositos_id_deposito_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.depositos_id_deposito_seq OWNER TO retail;

--
-- Name: depositos_id_deposito_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE depositos_id_deposito_seq OWNED BY depositos.id_deposito;


--
-- Name: fiscal_alicuotas_iva; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE fiscal_alicuotas_iva (
    id_alicuota_iva integer NOT NULL,
    nombre_alicuota_iva character varying(60) NOT NULL,
    valor_alicuota numeric(19,2) NOT NULL,
    gravar_iva boolean DEFAULT true NOT NULL,
    activo boolean DEFAULT true NOT NULL
);


ALTER TABLE public.fiscal_alicuotas_iva OWNER TO retail;

--
-- Name: fiscal_alicuotas_iva_id_alicuota_iva_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE fiscal_alicuotas_iva_id_alicuota_iva_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fiscal_alicuotas_iva_id_alicuota_iva_seq OWNER TO retail;

--
-- Name: fiscal_alicuotas_iva_id_alicuota_iva_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE fiscal_alicuotas_iva_id_alicuota_iva_seq OWNED BY fiscal_alicuotas_iva.id_alicuota_iva;


--
-- Name: fiscal_letras_comprobantes; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE fiscal_letras_comprobantes (
    id_resoponsabildiad_iva_emisor integer NOT NULL,
    id_resoponsabildiad_iva_receptor integer NOT NULL,
    letra_comprobante character varying(1) NOT NULL
);


ALTER TABLE public.fiscal_letras_comprobantes OWNER TO retail;

--
-- Name: fiscal_libro_iva_ventas; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE fiscal_libro_iva_ventas (
    id_factura integer NOT NULL,
    fecha_factura timestamp without time zone NOT NULL,
    id_responsabilidad_iva integer NOT NULL,
    documento character varying(20) NOT NULL,
    letra_factura character varying(2) NOT NULL,
    punto_venta_factura character varying(4) NOT NULL,
    numero_factura character varying(8) NOT NULL,
    total_factura numeric(19,2),
    id_periodo_fiscal integer NOT NULL,
    id_persona integer NOT NULL,
    id_registro_contable integer,
    anulada boolean DEFAULT false NOT NULL,
    version integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.fiscal_libro_iva_ventas OWNER TO retail;

--
-- Name: fiscal_libro_iva_ventas_id_factura_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE fiscal_libro_iva_ventas_id_factura_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fiscal_libro_iva_ventas_id_factura_seq OWNER TO retail;

--
-- Name: fiscal_libro_iva_ventas_id_factura_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE fiscal_libro_iva_ventas_id_factura_seq OWNED BY fiscal_libro_iva_ventas.id_factura;


--
-- Name: fiscal_libro_iva_ventas_lineas; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE fiscal_libro_iva_ventas_lineas (
    id_linea_libro integer NOT NULL,
    id_factura integer NOT NULL,
    id_alicuota_iva integer NOT NULL,
    neto_gravado numeric(19,2),
    no_gravado numeric(19,2),
    importe_iva numeric(19,2),
    version integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.fiscal_libro_iva_ventas_lineas OWNER TO retail;

--
-- Name: fiscal_libro_iva_ventas_lineas_id_linea_libro_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE fiscal_libro_iva_ventas_lineas_id_linea_libro_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fiscal_libro_iva_ventas_lineas_id_linea_libro_seq OWNER TO retail;

--
-- Name: fiscal_libro_iva_ventas_lineas_id_linea_libro_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE fiscal_libro_iva_ventas_lineas_id_linea_libro_seq OWNED BY fiscal_libro_iva_ventas_lineas.id_linea_libro;


--
-- Name: fiscal_periodos_fiscales; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE fiscal_periodos_fiscales (
    id_periodo_fiscal integer NOT NULL,
    nombre_periodo character varying(100) NOT NULL,
    fecha_inicio_periodo timestamp without time zone NOT NULL,
    fecha_fin_periodo timestamp without time zone NOT NULL,
    version integer DEFAULT 0 NOT NULL,
    periodo_cerrado boolean DEFAULT false NOT NULL
);


ALTER TABLE public.fiscal_periodos_fiscales OWNER TO retail;

--
-- Name: fiscal_periodos_fiscales_id_periodo_fiscal_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE fiscal_periodos_fiscales_id_periodo_fiscal_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fiscal_periodos_fiscales_id_periodo_fiscal_seq OWNER TO retail;

--
-- Name: fiscal_periodos_fiscales_id_periodo_fiscal_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE fiscal_periodos_fiscales_id_periodo_fiscal_seq OWNED BY fiscal_periodos_fiscales.id_periodo_fiscal;


--
-- Name: fiscal_responsabilidades_iva; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE fiscal_responsabilidades_iva (
    id_resoponsabildiad_iva integer NOT NULL,
    nombre_responsabildiad character varying(60) NOT NULL
);


ALTER TABLE public.fiscal_responsabilidades_iva OWNER TO retail;

--
-- Name: fiscal_responsabilidades_iva_id_resoponsabildiad_iva_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE fiscal_responsabilidades_iva_id_resoponsabildiad_iva_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fiscal_responsabilidades_iva_id_resoponsabildiad_iva_seq OWNER TO retail;

--
-- Name: fiscal_responsabilidades_iva_id_resoponsabildiad_iva_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE fiscal_responsabilidades_iva_id_resoponsabildiad_iva_seq OWNED BY fiscal_responsabilidades_iva.id_resoponsabildiad_iva;


--
-- Name: legal_generos; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE legal_generos (
    id_genero integer NOT NULL,
    nombre_genero character varying(30) NOT NULL,
    simbolo character varying(1) NOT NULL,
    id_tipo_personeria integer DEFAULT 1 NOT NULL
);


ALTER TABLE public.legal_generos OWNER TO retail;

--
-- Name: legal_generos_id_genero_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE legal_generos_id_genero_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.legal_generos_id_genero_seq OWNER TO retail;

--
-- Name: legal_generos_id_genero_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE legal_generos_id_genero_seq OWNED BY legal_generos.id_genero;


--
-- Name: legal_tipos_documento; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE legal_tipos_documento (
    id_tipo_documento integer NOT NULL,
    nombre_tipo_documento character varying(100) NOT NULL,
    cantidad_caracteres_minimo integer NOT NULL,
    cantidad_caracteres_maximo integer NOT NULL,
    id_tipo_personeria integer DEFAULT 1 NOT NULL
);


ALTER TABLE public.legal_tipos_documento OWNER TO retail;

--
-- Name: legal_tipos_documento_id_tipo_documento_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE legal_tipos_documento_id_tipo_documento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.legal_tipos_documento_id_tipo_documento_seq OWNER TO retail;

--
-- Name: legal_tipos_documento_id_tipo_documento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE legal_tipos_documento_id_tipo_documento_seq OWNED BY legal_tipos_documento.id_tipo_documento;


--
-- Name: legal_tipos_personeria; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE legal_tipos_personeria (
    id_tipo_personeria integer NOT NULL,
    nombre_tipo character varying(100) NOT NULL
);


ALTER TABLE public.legal_tipos_personeria OWNER TO retail;

--
-- Name: legal_tipos_personeria_id_tipo_personeria_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE legal_tipos_personeria_id_tipo_personeria_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.legal_tipos_personeria_id_tipo_personeria_seq OWNER TO retail;

--
-- Name: legal_tipos_personeria_id_tipo_personeria_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE legal_tipos_personeria_id_tipo_personeria_seq OWNED BY legal_tipos_personeria.id_tipo_personeria;


--
-- Name: negocio_condiciones_operaciones; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE negocio_condiciones_operaciones (
    id_condicion integer NOT NULL,
    nombre_condicion character varying(60) NOT NULL,
    activo boolean DEFAULT true NOT NULL,
    venta boolean DEFAULT true NOT NULL,
    compra boolean DEFAULT true NOT NULL,
    pago_total boolean DEFAULT true NOT NULL,
    version integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.negocio_condiciones_operaciones OWNER TO retail;

--
-- Name: negocio_formas_pago; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE negocio_formas_pago (
    id_forma_pago integer NOT NULL,
    nombre_forma_pago character varying(60) NOT NULL,
    nombre_corto character varying(10),
    venta boolean DEFAULT true NOT NULL,
    compra boolean DEFAULT true NOT NULL
);


ALTER TABLE public.negocio_formas_pago OWNER TO retail;

--
-- Name: parametros; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE parametros (
    nombre_parametro character varying(255) NOT NULL,
    valor_parametro character varying(255),
    descripcion_parametro character varying(1024) NOT NULL
);


ALTER TABLE public.parametros OWNER TO retail;

--
-- Name: personas; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE personas (
    id_persona integer NOT NULL,
    razon_social character varying(200) NOT NULL,
    apellidos character varying(60),
    nombres character varying(60),
    nombre_fantasia character varying(200),
    id_tipo_personeria integer NOT NULL,
    id_pais integer NOT NULL,
    id_provincia integer NOT NULL,
    id_localidad integer NOT NULL,
    calle character varying(100),
    altura character varying(50),
    piso character varying(3),
    depto character varying(5),
    id_tipo_documento integer NOT NULL,
    documento character varying(13) NOT NULL,
    id_responsabilidad_iva integer NOT NULL,
    fecha_alta timestamp without time zone NOT NULL,
    activo boolean DEFAULT true NOT NULL,
    cliente boolean DEFAULT true NOT NULL,
    proveedor boolean DEFAULT false NOT NULL,
    id_genero integer NOT NULL,
    version integer DEFAULT 0 NOT NULL,
    email character varying(100) DEFAULT 'a@a.com'::character varying,
    id_sucursal integer DEFAULT 1 NOT NULL
);


ALTER TABLE public.personas OWNER TO retail;

--
-- Name: personas_cuenta_corriente; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE personas_cuenta_corriente (
    id_movimiento integer NOT NULL,
    id_persona integer NOT NULL,
    fecha_movimiento timestamp without time zone NOT NULL,
    importe_movimiento numeric(19,2) NOT NULL,
    descripcion_movimiento character varying(200) NOT NULL,
    id_registro_contable integer
);


ALTER TABLE public.personas_cuenta_corriente OWNER TO retail;

--
-- Name: personas_cuenta_corriente_id_movimiento_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE personas_cuenta_corriente_id_movimiento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.personas_cuenta_corriente_id_movimiento_seq OWNER TO retail;

--
-- Name: personas_cuenta_corriente_id_movimiento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE personas_cuenta_corriente_id_movimiento_seq OWNED BY personas_cuenta_corriente.id_movimiento;


--
-- Name: personas_id_persona_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE personas_id_persona_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.personas_id_persona_seq OWNER TO retail;

--
-- Name: personas_id_persona_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE personas_id_persona_seq OWNED BY personas.id_persona;


--
-- Name: personas_imagenes; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE personas_imagenes (
    id_imagen integer NOT NULL,
    id_persona integer NOT NULL,
    fecha_alta timestamp without time zone NOT NULL,
    id_tipo_imagen integer NOT NULL,
    observaciones character varying(1024)
);


ALTER TABLE public.personas_imagenes OWNER TO retail;

--
-- Name: personas_imagenes_id_imagen_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE personas_imagenes_id_imagen_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.personas_imagenes_id_imagen_seq OWNER TO retail;

--
-- Name: personas_imagenes_id_imagen_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE personas_imagenes_id_imagen_seq OWNED BY personas_imagenes.id_imagen;


--
-- Name: personas_telefonos; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE personas_telefonos (
    id_telefono integer NOT NULL,
    id_persona integer NOT NULL,
    numero character varying(50) NOT NULL,
    referencia character varying(100)
);


ALTER TABLE public.personas_telefonos OWNER TO retail;

--
-- Name: personas_telefonos_id_telefono_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE personas_telefonos_id_telefono_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.personas_telefonos_id_telefono_seq OWNER TO retail;

--
-- Name: personas_telefonos_id_telefono_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE personas_telefonos_id_telefono_seq OWNED BY personas_telefonos.id_telefono;


--
-- Name: personas_tipos_imagenes; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE personas_tipos_imagenes (
    id_tipo_imagen integer NOT NULL,
    nombre_tipo character varying(60) NOT NULL,
    descripcion_tipo character varying(200)
);


ALTER TABLE public.personas_tipos_imagenes OWNER TO retail;

--
-- Name: personas_tipos_imagenes_id_tipo_imagen_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE personas_tipos_imagenes_id_tipo_imagen_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.personas_tipos_imagenes_id_tipo_imagen_seq OWNER TO retail;

--
-- Name: personas_tipos_imagenes_id_tipo_imagen_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE personas_tipos_imagenes_id_tipo_imagen_seq OWNED BY personas_tipos_imagenes.id_tipo_imagen;


--
-- Name: privilegios; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE privilegios (
    id_privilegio integer NOT NULL,
    nombre_privilegio character varying(100) NOT NULL,
    descripcion_privilegio character varying(1024)
);


ALTER TABLE public.privilegios OWNER TO retail;

--
-- Name: privilegios_gruposx; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE privilegios_gruposx (
    id_privilegio integer NOT NULL,
    id_grupo integer NOT NULL
);


ALTER TABLE public.privilegios_gruposx OWNER TO retail;

--
-- Name: privilegios_id_privilegio_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE privilegios_id_privilegio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.privilegios_id_privilegio_seq OWNER TO retail;

--
-- Name: privilegios_id_privilegio_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE privilegios_id_privilegio_seq OWNED BY privilegios.id_privilegio;


--
-- Name: productos; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE productos (
    id_producto integer NOT NULL,
    codigo_propio character varying(100),
    descripcion character varying(200),
    fecha_alta timestamp without time zone NOT NULL,
    activo boolean DEFAULT true NOT NULL,
    id_tipo_proveeduria integer NOT NULL,
    id_tipo_unidad_compra integer NOT NULL,
    id_tipo_unidad_venta integer NOT NULL,
    costo_adquisicion_neto numeric(19,2) NOT NULL,
    id_alicuota_iva integer NOT NULL,
    utilidad numeric(19,2) NOT NULL,
    stock_total numeric(19,2) DEFAULT 0 NOT NULL,
    id_rubro integer NOT NULL,
    id_sub_rubro integer NOT NULL,
    annos_amortizacion integer DEFAULT 0 NOT NULL,
    ubicacion character varying(20),
    id_proveedor_habitual integer,
    version integer DEFAULT 0 NOT NULL,
    precio_venta numeric(19,4) DEFAULT 1 NOT NULL
);


ALTER TABLE public.productos OWNER TO retail;

--
-- Name: productos_caracteristicas; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE productos_caracteristicas (
    id_caracteristica integer NOT NULL,
    nombre_caracteristica character varying(100) NOT NULL
);


ALTER TABLE public.productos_caracteristicas OWNER TO retail;

--
-- Name: productos_caracteristicas_id_caracteristica_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE productos_caracteristicas_id_caracteristica_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.productos_caracteristicas_id_caracteristica_seq OWNER TO retail;

--
-- Name: productos_caracteristicas_id_caracteristica_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE productos_caracteristicas_id_caracteristica_seq OWNED BY productos_caracteristicas.id_caracteristica;


--
-- Name: productos_id_producto_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE productos_id_producto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.productos_id_producto_seq OWNER TO retail;

--
-- Name: productos_id_producto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE productos_id_producto_seq OWNED BY productos.id_producto;


--
-- Name: productos_imagenes; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE productos_imagenes (
    id_imagen integer NOT NULL,
    id_producto integer NOT NULL,
    id_usuario integer NOT NULL,
    fecha_alta timestamp without time zone NOT NULL,
    descripcion character varying(200),
    imagen bytea NOT NULL
);


ALTER TABLE public.productos_imagenes OWNER TO retail;

--
-- Name: productos_imagenes_id_imagen_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE productos_imagenes_id_imagen_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.productos_imagenes_id_imagen_seq OWNER TO retail;

--
-- Name: productos_imagenes_id_imagen_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE productos_imagenes_id_imagen_seq OWNED BY productos_imagenes.id_imagen;


--
-- Name: productos_porcentajes; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE productos_porcentajes (
    id_porcentaje integer NOT NULL,
    id_producto integer NOT NULL,
    porcentaje numeric(19,2) NOT NULL,
    descripcion_porcentaje character varying(60) NOT NULL
);


ALTER TABLE public.productos_porcentajes OWNER TO retail;

--
-- Name: productos_porcentajes_id_porcentaje_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE productos_porcentajes_id_porcentaje_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.productos_porcentajes_id_porcentaje_seq OWNER TO retail;

--
-- Name: productos_porcentajes_id_porcentaje_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE productos_porcentajes_id_porcentaje_seq OWNED BY productos_porcentajes.id_porcentaje;


--
-- Name: productos_rubros; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE productos_rubros (
    id_rubro integer NOT NULL,
    nombre_rubro character varying(100) NOT NULL
);


ALTER TABLE public.productos_rubros OWNER TO retail;

--
-- Name: productos_rubros_id_rubro_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE productos_rubros_id_rubro_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.productos_rubros_id_rubro_seq OWNER TO retail;

--
-- Name: productos_rubros_id_rubro_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE productos_rubros_id_rubro_seq OWNED BY productos_rubros.id_rubro;


--
-- Name: productos_sub_rubros; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE productos_sub_rubros (
    id_sub_rubro integer NOT NULL,
    id_rubro integer NOT NULL,
    nombre_sub_rubro character varying(100) NOT NULL
);


ALTER TABLE public.productos_sub_rubros OWNER TO retail;

--
-- Name: productos_sub_rubros_id_sub_rubro_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE productos_sub_rubros_id_sub_rubro_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.productos_sub_rubros_id_sub_rubro_seq OWNER TO retail;

--
-- Name: productos_sub_rubros_id_sub_rubro_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE productos_sub_rubros_id_sub_rubro_seq OWNED BY productos_sub_rubros.id_sub_rubro;


--
-- Name: productos_tipos_proveeduria; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE productos_tipos_proveeduria (
    id_tipo_proveeduria integer NOT NULL,
    nombre_tipo_proveeduria character varying(60),
    puede_comprarse boolean NOT NULL,
    puede_venderse boolean NOT NULL,
    control_stock boolean NOT NULL,
    cambiar_precio_venta boolean DEFAULT true NOT NULL
);


ALTER TABLE public.productos_tipos_proveeduria OWNER TO retail;

--
-- Name: productos_tipos_proveeduria_id_tipo_proveeduria_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE productos_tipos_proveeduria_id_tipo_proveeduria_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.productos_tipos_proveeduria_id_tipo_proveeduria_seq OWNER TO retail;

--
-- Name: productos_tipos_proveeduria_id_tipo_proveeduria_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE productos_tipos_proveeduria_id_tipo_proveeduria_seq OWNED BY productos_tipos_proveeduria.id_tipo_proveeduria;


--
-- Name: productos_tipos_unidades; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE productos_tipos_unidades (
    id_tipo_unidad integer NOT NULL,
    nombre_unidad character varying(60) NOT NULL,
    cantidad_entera boolean NOT NULL
);


ALTER TABLE public.productos_tipos_unidades OWNER TO retail;

--
-- Name: productos_tipos_unidades_id_tipo_unidad_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE productos_tipos_unidades_id_tipo_unidad_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.productos_tipos_unidades_id_tipo_unidad_seq OWNER TO retail;

--
-- Name: productos_tipos_unidades_id_tipo_unidad_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE productos_tipos_unidades_id_tipo_unidad_seq OWNED BY productos_tipos_unidades.id_tipo_unidad;


--
-- Name: productos_x_caracteristicas; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE productos_x_caracteristicas (
    id_caracteristica_x_producto integer NOT NULL,
    id_caracteristica integer NOT NULL,
    id_producto integer NOT NULL,
    valor_caracteristica character varying(255) NOT NULL
);


ALTER TABLE public.productos_x_caracteristicas OWNER TO retail;

--
-- Name: productos_x_caracteristicas_id_caracteristica_x_producto_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE productos_x_caracteristicas_id_caracteristica_x_producto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.productos_x_caracteristicas_id_caracteristica_x_producto_seq OWNER TO retail;

--
-- Name: productos_x_caracteristicas_id_caracteristica_x_producto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE productos_x_caracteristicas_id_caracteristica_x_producto_seq OWNED BY productos_x_caracteristicas.id_caracteristica_x_producto;


--
-- Name: proveedores_ordenes_compra; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE proveedores_ordenes_compra (
    id_orden_compra integer NOT NULL,
    fecha_orden_compra timestamp without time zone NOT NULL,
    fecha_estimada_recepcion timestamp without time zone NOT NULL,
    id_condicion_compra integer NOT NULL,
    id_sucursal integer NOT NULL,
    id_usuario integer NOT NULL,
    total numeric(19,2) NOT NULL,
    observaciones character varying(1024),
    anulada boolean DEFAULT false NOT NULL,
    id_proveedor integer NOT NULL
);


ALTER TABLE public.proveedores_ordenes_compra OWNER TO retail;

--
-- Name: proveedores_ordenes_compra_id_orden_compra_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE proveedores_ordenes_compra_id_orden_compra_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.proveedores_ordenes_compra_id_orden_compra_seq OWNER TO retail;

--
-- Name: proveedores_ordenes_compra_id_orden_compra_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE proveedores_ordenes_compra_id_orden_compra_seq OWNED BY proveedores_ordenes_compra.id_orden_compra;


--
-- Name: proveedores_ordenes_compra_lineas; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE proveedores_ordenes_compra_lineas (
    id_linea integer NOT NULL,
    id_orden_compra integer NOT NULL,
    id_producto integer NOT NULL,
    precio_compra_unitario numeric(19,2) NOT NULL,
    cantidad numeric(19,2) NOT NULL,
    sub_total numeric(19,2) NOT NULL,
    cantidad_recibida numeric(19,2)
);


ALTER TABLE public.proveedores_ordenes_compra_lineas OWNER TO retail;

--
-- Name: proveedores_ordenes_compra_lineas_id_linea_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE proveedores_ordenes_compra_lineas_id_linea_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.proveedores_ordenes_compra_lineas_id_linea_seq OWNER TO retail;

--
-- Name: proveedores_ordenes_compra_lineas_id_linea_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE proveedores_ordenes_compra_lineas_id_linea_seq OWNED BY proveedores_ordenes_compra_lineas.id_linea;


--
-- Name: stock_movimientos; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE stock_movimientos (
    id_movimiento_stock integer NOT NULL,
    fecha_movimiento timestamp without time zone NOT NULL,
    id_producto integer NOT NULL,
    cantidad_anterior numeric(19,2) NOT NULL,
    cantidad_movimiento numeric(19,2) NOT NULL,
    cantidad_actual numeric(19,2) NOT NULL,
    id_tipo_movimiento integer NOT NULL,
    observaciones_movimiento character varying(255),
    id_usuario integer NOT NULL,
    costo_total_movimiento numeric(19,2) NOT NULL,
    id_deposito_movimiento integer NOT NULL
);


ALTER TABLE public.stock_movimientos OWNER TO retail;

--
-- Name: stock_movimientos_id_movimiento_stock_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE stock_movimientos_id_movimiento_stock_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.stock_movimientos_id_movimiento_stock_seq OWNER TO retail;

--
-- Name: stock_movimientos_id_movimiento_stock_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE stock_movimientos_id_movimiento_stock_seq OWNED BY stock_movimientos.id_movimiento_stock;


--
-- Name: stock_movimientos_tipos; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE stock_movimientos_tipos (
    id_tipo_movimiento integer NOT NULL,
    nombre_tipo character varying(60) NOT NULL
);


ALTER TABLE public.stock_movimientos_tipos OWNER TO retail;

--
-- Name: stock_movimientos_tipos_id_tipo_movimiento_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE stock_movimientos_tipos_id_tipo_movimiento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.stock_movimientos_tipos_id_tipo_movimiento_seq OWNER TO retail;

--
-- Name: stock_movimientos_tipos_id_tipo_movimiento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE stock_movimientos_tipos_id_tipo_movimiento_seq OWNED BY stock_movimientos_tipos.id_tipo_movimiento;


--
-- Name: sucursales; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE sucursales (
    id_sucursal integer NOT NULL,
    nombre_sucursal character varying(100) NOT NULL,
    id_pais integer NOT NULL,
    id_provincia integer NOT NULL,
    id_localidad integer NOT NULL,
    direccion character varying(500),
    telefono_fijo character varying(20),
    fecha_alta timestamp without time zone NOT NULL,
    activo boolean DEFAULT true NOT NULL,
    version integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.sucursales OWNER TO retail;

--
-- Name: sucursales_id_sucursal_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE sucursales_id_sucursal_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sucursales_id_sucursal_seq OWNER TO retail;

--
-- Name: sucursales_id_sucursal_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE sucursales_id_sucursal_seq OWNED BY sucursales.id_sucursal;


--
-- Name: ubicacion_localidades; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE ubicacion_localidades (
    id_localidad integer NOT NULL,
    id_provincia integer NOT NULL,
    nombre_localidad character varying(100) NOT NULL,
    codigo_postal character varying(20) DEFAULT '0'::character varying NOT NULL,
    version integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.ubicacion_localidades OWNER TO retail;

--
-- Name: ubicacion_localidades_id_localidad_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE ubicacion_localidades_id_localidad_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ubicacion_localidades_id_localidad_seq OWNER TO retail;

--
-- Name: ubicacion_localidades_id_localidad_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE ubicacion_localidades_id_localidad_seq OWNED BY ubicacion_localidades.id_localidad;


--
-- Name: ubicacion_paises; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE ubicacion_paises (
    id_pais integer NOT NULL,
    nombre_pais character varying(60) NOT NULL,
    version integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.ubicacion_paises OWNER TO retail;

--
-- Name: ubicacion_paises_id_pais_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE ubicacion_paises_id_pais_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ubicacion_paises_id_pais_seq OWNER TO retail;

--
-- Name: ubicacion_paises_id_pais_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE ubicacion_paises_id_pais_seq OWNED BY ubicacion_paises.id_pais;


--
-- Name: ubicacion_provincias; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE ubicacion_provincias (
    id_provincia integer NOT NULL,
    id_pais integer NOT NULL,
    nombre_provincia character varying(100) NOT NULL,
    version integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.ubicacion_provincias OWNER TO retail;

--
-- Name: ubicacion_provincias_id_provincia_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE ubicacion_provincias_id_provincia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ubicacion_provincias_id_provincia_seq OWNER TO retail;

--
-- Name: ubicacion_provincias_id_provincia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE ubicacion_provincias_id_provincia_seq OWNED BY ubicacion_provincias.id_provincia;


--
-- Name: usuarios; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE usuarios (
    id_usuario integer NOT NULL,
    nombre_usuario character varying(100) NOT NULL,
    login character varying(60) NOT NULL,
    password character varying(64) NOT NULL,
    fecha_alta timestamp without time zone NOT NULL,
    id_sucursal integer,
    punto_venta character varying(4) DEFAULT '0001'::character varying,
    version integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.usuarios OWNER TO retail;

--
-- Name: usuarios_grupos; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE usuarios_grupos (
    id_grupo integer NOT NULL,
    nombre_grupo character varying(100) NOT NULL
);


ALTER TABLE public.usuarios_grupos OWNER TO retail;

--
-- Name: usuarios_grupos_id_grupo_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE usuarios_grupos_id_grupo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuarios_grupos_id_grupo_seq OWNER TO retail;

--
-- Name: usuarios_grupos_id_grupo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE usuarios_grupos_id_grupo_seq OWNED BY usuarios_grupos.id_grupo;


--
-- Name: usuarios_gruposx; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE usuarios_gruposx (
    id_usuario integer NOT NULL,
    id_grupo integer NOT NULL
);


ALTER TABLE public.usuarios_gruposx OWNER TO retail;

--
-- Name: usuarios_id_usuario_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE usuarios_id_usuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuarios_id_usuario_seq OWNER TO retail;

--
-- Name: usuarios_id_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE usuarios_id_usuario_seq OWNED BY usuarios.id_usuario;


--
-- Name: v_roles_usuarios; Type: VIEW; Schema: public; Owner: retail
--

CREATE VIEW v_roles_usuarios AS
 SELECT us.login,
    us.password,
    gru.nombre_grupo
   FROM ((usuarios us
     JOIN usuarios_gruposx uxg ON ((uxg.id_usuario = us.id_usuario)))
     JOIN usuarios_grupos gru ON ((uxg.id_grupo = gru.id_grupo)));


ALTER TABLE public.v_roles_usuarios OWNER TO retail;

--
-- Name: ventas; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE ventas (
    id_venta integer NOT NULL,
    fecha_venta timestamp without time zone NOT NULL,
    id_usuario integer NOT NULL,
    id_sucursal integer NOT NULL,
    total numeric(19,2) NOT NULL,
    id_condicion_venta integer NOT NULL,
    saldo numeric(19,2) NOT NULL,
    id_registro_iva integer,
    observaciones character varying(1024),
    anulada boolean DEFAULT false NOT NULL,
    id_persona integer NOT NULL,
    id_estado integer NOT NULL,
    version integer NOT NULL,
    remitente character varying(100),
    nroremito character varying(100)
);


ALTER TABLE public.ventas OWNER TO retail;

--
-- Name: ventas_cargos_fijos; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE ventas_cargos_fijos (
    id_cargo_fijo integer NOT NULL,
    fecha_alta timestamp without time zone NOT NULL,
    activo boolean DEFAULT true NOT NULL,
    porcentaje boolean NOT NULL,
    importe_cargo numeric(19,2) NOT NULL,
    id_producto integer NOT NULL,
    id_clasificacion_cliente integer NOT NULL
);


ALTER TABLE public.ventas_cargos_fijos OWNER TO retail;

--
-- Name: ventas_cargos_fijos_id_cargo_fijo_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE ventas_cargos_fijos_id_cargo_fijo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ventas_cargos_fijos_id_cargo_fijo_seq OWNER TO retail;

--
-- Name: ventas_cargos_fijos_id_cargo_fijo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE ventas_cargos_fijos_id_cargo_fijo_seq OWNED BY ventas_cargos_fijos.id_cargo_fijo;


--
-- Name: ventas_condiciones_id_condicion_venta_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE ventas_condiciones_id_condicion_venta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ventas_condiciones_id_condicion_venta_seq OWNER TO retail;

--
-- Name: ventas_condiciones_id_condicion_venta_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE ventas_condiciones_id_condicion_venta_seq OWNED BY negocio_condiciones_operaciones.id_condicion;


--
-- Name: ventas_estados; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE ventas_estados (
    id_estado integer NOT NULL,
    nombre_estado character varying(20) NOT NULL,
    version integer NOT NULL
);


ALTER TABLE public.ventas_estados OWNER TO retail;

--
-- Name: ventas_estados_historico; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE ventas_estados_historico (
    id_estado_historico integer NOT NULL,
    id_venta integer NOT NULL,
    fecha_cambio timestamp without time zone NOT NULL,
    id_estado_anterior integer NOT NULL,
    id_estado_actual integer NOT NULL,
    id_usuario integer NOT NULL
);


ALTER TABLE public.ventas_estados_historico OWNER TO retail;

--
-- Name: ventas_estados_historico_id_estado_historico_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE ventas_estados_historico_id_estado_historico_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ventas_estados_historico_id_estado_historico_seq OWNER TO retail;

--
-- Name: ventas_estados_historico_id_estado_historico_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE ventas_estados_historico_id_estado_historico_seq OWNED BY ventas_estados_historico.id_estado_historico;


--
-- Name: ventas_formas_pago_id_forma_pago_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE ventas_formas_pago_id_forma_pago_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ventas_formas_pago_id_forma_pago_seq OWNER TO retail;

--
-- Name: ventas_formas_pago_id_forma_pago_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE ventas_formas_pago_id_forma_pago_seq OWNED BY negocio_formas_pago.id_forma_pago;


--
-- Name: ventas_id_venta_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE ventas_id_venta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ventas_id_venta_seq OWNER TO retail;

--
-- Name: ventas_id_venta_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE ventas_id_venta_seq OWNED BY ventas.id_venta;


--
-- Name: ventas_lineas; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE ventas_lineas (
    id_linea_venta integer NOT NULL,
    id_venta integer NOT NULL,
    id_producto integer NOT NULL,
    precio_venta_unitario numeric(19,4) NOT NULL,
    cantidad numeric(19,2) NOT NULL,
    sub_total numeric(19,2) NOT NULL,
    costo_neto_unitario numeric(19,4) NOT NULL,
    costo_bruto_unitario numeric(19,4) NOT NULL,
    cantidad_entregada numeric(19,2) NOT NULL,
    id_linea_venta_referencia integer,
    version integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.ventas_lineas OWNER TO retail;

--
-- Name: ventas_lineas_id_linea_venta_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE ventas_lineas_id_linea_venta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ventas_lineas_id_linea_venta_seq OWNER TO retail;

--
-- Name: ventas_lineas_id_linea_venta_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE ventas_lineas_id_linea_venta_seq OWNED BY ventas_lineas.id_linea_venta;


--
-- Name: ventas_pagos; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE ventas_pagos (
    id_pago_venta integer NOT NULL,
    fecha_pago timestamp without time zone NOT NULL,
    id_forma_pago integer NOT NULL,
    importe_total_pagado numeric(19,2),
    observaciones character varying(255),
    id_sucursal integer NOT NULL,
    id_usuario integer NOT NULL,
    id_persona integer NOT NULL,
    version integer DEFAULT 0 NOT NULL,
    id_movimiento_caja integer
);


ALTER TABLE public.ventas_pagos OWNER TO retail;

--
-- Name: ventas_pagos_id_pago_venta_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE ventas_pagos_id_pago_venta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ventas_pagos_id_pago_venta_seq OWNER TO retail;

--
-- Name: ventas_pagos_id_pago_venta_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE ventas_pagos_id_pago_venta_seq OWNED BY ventas_pagos.id_pago_venta;


--
-- Name: ventas_pagos_lineas; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE ventas_pagos_lineas (
    id_linea_pago integer NOT NULL,
    id_pago_venta integer NOT NULL,
    id_venta integer NOT NULL,
    importe numeric(19,2) NOT NULL,
    version integer DEFAULT 0 NOT NULL,
    id_movimiento_caja integer
);


ALTER TABLE public.ventas_pagos_lineas OWNER TO retail;

--
-- Name: ventas_pagos_lineas_id_linea_pago_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE ventas_pagos_lineas_id_linea_pago_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ventas_pagos_lineas_id_linea_pago_seq OWNER TO retail;

--
-- Name: ventas_pagos_lineas_id_linea_pago_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE ventas_pagos_lineas_id_linea_pago_seq OWNED BY ventas_pagos_lineas.id_linea_pago;


--
-- Name: ventas_remitos; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE ventas_remitos (
    id_remito integer NOT NULL,
    fecha_remito timestamp without time zone NOT NULL,
    id_venta integer NOT NULL,
    id_sucursal integer NOT NULL,
    id_usuario integer NOT NULL,
    anulado boolean DEFAULT false NOT NULL,
    observaciones character varying(255)
);


ALTER TABLE public.ventas_remitos OWNER TO retail;

--
-- Name: ventas_remitos_id_remito_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE ventas_remitos_id_remito_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ventas_remitos_id_remito_seq OWNER TO retail;

--
-- Name: ventas_remitos_id_remito_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE ventas_remitos_id_remito_seq OWNED BY ventas_remitos.id_remito;


--
-- Name: ventas_remitos_lineas; Type: TABLE; Schema: public; Owner: retail; Tablespace: 
--

CREATE TABLE ventas_remitos_lineas (
    id_linea_remito integer NOT NULL,
    id_remito integer NOT NULL,
    id_producto integer NOT NULL,
    cantidad numeric(19,2) NOT NULL,
    costo_neto_unitario numeric(19,2) NOT NULL
);


ALTER TABLE public.ventas_remitos_lineas OWNER TO retail;

--
-- Name: ventas_remitos_lineas_id_linea_remito_seq; Type: SEQUENCE; Schema: public; Owner: retail
--

CREATE SEQUENCE ventas_remitos_lineas_id_linea_remito_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ventas_remitos_lineas_id_linea_remito_seq OWNER TO retail;

--
-- Name: ventas_remitos_lineas_id_linea_remito_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: retail
--

ALTER SEQUENCE ventas_remitos_lineas_id_linea_remito_seq OWNED BY ventas_remitos_lineas.id_linea_remito;


--
-- Name: id_banco; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY bancos ALTER COLUMN id_banco SET DEFAULT nextval('bancos_id_banco_seq'::regclass);


--
-- Name: id_movimiento; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY bancos_cuenta_corriente ALTER COLUMN id_movimiento SET DEFAULT nextval('bancos_cuenta_corriente_id_movimiento_seq'::regclass);


--
-- Name: id_cuenta_banco; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY bancos_cuentas ALTER COLUMN id_cuenta_banco SET DEFAULT nextval('bancos_cuentas_id_cuenta_banco_seq'::regclass);


--
-- Name: id_tipo_cuenta_banco; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY bancos_tipos_cuenta ALTER COLUMN id_tipo_cuenta_banco SET DEFAULT nextval('bancos_tipos_cuenta_id_tipo_cuenta_banco_seq'::regclass);


--
-- Name: id_caja; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY cajas ALTER COLUMN id_caja SET DEFAULT nextval('cajas_id_caja_seq'::regclass);


--
-- Name: id_categoria_movimiento; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY cajas_categorias_movimientos ALTER COLUMN id_categoria_movimiento SET DEFAULT nextval('cajas_categorias_movimientos_id_categoria_movimiento_seq'::regclass);


--
-- Name: id_movimiento_caja; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY cajas_movimientos ALTER COLUMN id_movimiento_caja SET DEFAULT nextval('cajas_movimientos_id_movimiento_caja_seq'::regclass);


--
-- Name: id_libro; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY contabilidad_libros ALTER COLUMN id_libro SET DEFAULT nextval('contabilidad_libros_id_libro_seq'::regclass);


--
-- Name: id_moneda; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY contabilidad_monedas ALTER COLUMN id_moneda SET DEFAULT nextval('contabilidad_monedas_id_moneda_seq'::regclass);


--
-- Name: id_periodo_contable; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY contabilidad_periodos_contables ALTER COLUMN id_periodo_contable SET DEFAULT nextval('contabilidad_periodos_contables_id_periodo_contable_seq'::regclass);


--
-- Name: id_cuenta; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY contabilidad_plan_cuentas ALTER COLUMN id_cuenta SET DEFAULT nextval('contabilidad_plan_cuentas_id_cuenta_seq'::regclass);


--
-- Name: id_registro; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY contabilidad_registro_contable ALTER COLUMN id_registro SET DEFAULT nextval('contabilidad_registro_contable_id_registro_seq'::regclass);


--
-- Name: id_linea_registro; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY contabilidad_registro_contable_lineas ALTER COLUMN id_linea_registro SET DEFAULT nextval('contabilidad_registro_contable_lineas_id_linea_registro_seq'::regclass);


--
-- Name: id_tipo_comprobante; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY contabilidad_tipos_comprobantes ALTER COLUMN id_tipo_comprobante SET DEFAULT nextval('contabilidad_tipos_comprobantes_id_tipo_comprobante_seq'::regclass);


--
-- Name: id_tipo_cuenta; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY contabilidad_tipos_cuenta ALTER COLUMN id_tipo_cuenta SET DEFAULT nextval('contabilidad_tipos_cuenta_id_tipo_cuenta_seq'::regclass);


--
-- Name: id_tipo_operacion; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY contabilidad_tipos_operacion ALTER COLUMN id_tipo_operacion SET DEFAULT nextval('contabilidad_tipos_operacion_id_tipo_operacion_seq'::regclass);


--
-- Name: id_deposito; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY depositos ALTER COLUMN id_deposito SET DEFAULT nextval('depositos_id_deposito_seq'::regclass);


--
-- Name: id_alicuota_iva; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY fiscal_alicuotas_iva ALTER COLUMN id_alicuota_iva SET DEFAULT nextval('fiscal_alicuotas_iva_id_alicuota_iva_seq'::regclass);


--
-- Name: id_factura; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY fiscal_libro_iva_ventas ALTER COLUMN id_factura SET DEFAULT nextval('fiscal_libro_iva_ventas_id_factura_seq'::regclass);


--
-- Name: id_linea_libro; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY fiscal_libro_iva_ventas_lineas ALTER COLUMN id_linea_libro SET DEFAULT nextval('fiscal_libro_iva_ventas_lineas_id_linea_libro_seq'::regclass);


--
-- Name: id_periodo_fiscal; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY fiscal_periodos_fiscales ALTER COLUMN id_periodo_fiscal SET DEFAULT nextval('fiscal_periodos_fiscales_id_periodo_fiscal_seq'::regclass);


--
-- Name: id_resoponsabildiad_iva; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY fiscal_responsabilidades_iva ALTER COLUMN id_resoponsabildiad_iva SET DEFAULT nextval('fiscal_responsabilidades_iva_id_resoponsabildiad_iva_seq'::regclass);


--
-- Name: id_genero; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY legal_generos ALTER COLUMN id_genero SET DEFAULT nextval('legal_generos_id_genero_seq'::regclass);


--
-- Name: id_tipo_documento; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY legal_tipos_documento ALTER COLUMN id_tipo_documento SET DEFAULT nextval('legal_tipos_documento_id_tipo_documento_seq'::regclass);


--
-- Name: id_tipo_personeria; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY legal_tipos_personeria ALTER COLUMN id_tipo_personeria SET DEFAULT nextval('legal_tipos_personeria_id_tipo_personeria_seq'::regclass);


--
-- Name: id_condicion; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY negocio_condiciones_operaciones ALTER COLUMN id_condicion SET DEFAULT nextval('ventas_condiciones_id_condicion_venta_seq'::regclass);


--
-- Name: id_forma_pago; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY negocio_formas_pago ALTER COLUMN id_forma_pago SET DEFAULT nextval('ventas_formas_pago_id_forma_pago_seq'::regclass);


--
-- Name: id_persona; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY personas ALTER COLUMN id_persona SET DEFAULT nextval('personas_id_persona_seq'::regclass);


--
-- Name: id_movimiento; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY personas_cuenta_corriente ALTER COLUMN id_movimiento SET DEFAULT nextval('personas_cuenta_corriente_id_movimiento_seq'::regclass);


--
-- Name: id_imagen; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY personas_imagenes ALTER COLUMN id_imagen SET DEFAULT nextval('personas_imagenes_id_imagen_seq'::regclass);


--
-- Name: id_telefono; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY personas_telefonos ALTER COLUMN id_telefono SET DEFAULT nextval('personas_telefonos_id_telefono_seq'::regclass);


--
-- Name: id_tipo_imagen; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY personas_tipos_imagenes ALTER COLUMN id_tipo_imagen SET DEFAULT nextval('personas_tipos_imagenes_id_tipo_imagen_seq'::regclass);


--
-- Name: id_privilegio; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY privilegios ALTER COLUMN id_privilegio SET DEFAULT nextval('privilegios_id_privilegio_seq'::regclass);


--
-- Name: id_producto; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY productos ALTER COLUMN id_producto SET DEFAULT nextval('productos_id_producto_seq'::regclass);


--
-- Name: id_caracteristica; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY productos_caracteristicas ALTER COLUMN id_caracteristica SET DEFAULT nextval('productos_caracteristicas_id_caracteristica_seq'::regclass);


--
-- Name: id_imagen; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY productos_imagenes ALTER COLUMN id_imagen SET DEFAULT nextval('productos_imagenes_id_imagen_seq'::regclass);


--
-- Name: id_porcentaje; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY productos_porcentajes ALTER COLUMN id_porcentaje SET DEFAULT nextval('productos_porcentajes_id_porcentaje_seq'::regclass);


--
-- Name: id_rubro; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY productos_rubros ALTER COLUMN id_rubro SET DEFAULT nextval('productos_rubros_id_rubro_seq'::regclass);


--
-- Name: id_sub_rubro; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY productos_sub_rubros ALTER COLUMN id_sub_rubro SET DEFAULT nextval('productos_sub_rubros_id_sub_rubro_seq'::regclass);


--
-- Name: id_tipo_proveeduria; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY productos_tipos_proveeduria ALTER COLUMN id_tipo_proveeduria SET DEFAULT nextval('productos_tipos_proveeduria_id_tipo_proveeduria_seq'::regclass);


--
-- Name: id_tipo_unidad; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY productos_tipos_unidades ALTER COLUMN id_tipo_unidad SET DEFAULT nextval('productos_tipos_unidades_id_tipo_unidad_seq'::regclass);


--
-- Name: id_caracteristica_x_producto; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY productos_x_caracteristicas ALTER COLUMN id_caracteristica_x_producto SET DEFAULT nextval('productos_x_caracteristicas_id_caracteristica_x_producto_seq'::regclass);


--
-- Name: id_orden_compra; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY proveedores_ordenes_compra ALTER COLUMN id_orden_compra SET DEFAULT nextval('proveedores_ordenes_compra_id_orden_compra_seq'::regclass);


--
-- Name: id_linea; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY proveedores_ordenes_compra_lineas ALTER COLUMN id_linea SET DEFAULT nextval('proveedores_ordenes_compra_lineas_id_linea_seq'::regclass);


--
-- Name: id_movimiento_stock; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY stock_movimientos ALTER COLUMN id_movimiento_stock SET DEFAULT nextval('stock_movimientos_id_movimiento_stock_seq'::regclass);


--
-- Name: id_tipo_movimiento; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY stock_movimientos_tipos ALTER COLUMN id_tipo_movimiento SET DEFAULT nextval('stock_movimientos_tipos_id_tipo_movimiento_seq'::regclass);


--
-- Name: id_sucursal; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY sucursales ALTER COLUMN id_sucursal SET DEFAULT nextval('sucursales_id_sucursal_seq'::regclass);


--
-- Name: id_localidad; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ubicacion_localidades ALTER COLUMN id_localidad SET DEFAULT nextval('ubicacion_localidades_id_localidad_seq'::regclass);


--
-- Name: id_pais; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ubicacion_paises ALTER COLUMN id_pais SET DEFAULT nextval('ubicacion_paises_id_pais_seq'::regclass);


--
-- Name: id_provincia; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ubicacion_provincias ALTER COLUMN id_provincia SET DEFAULT nextval('ubicacion_provincias_id_provincia_seq'::regclass);


--
-- Name: id_usuario; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY usuarios ALTER COLUMN id_usuario SET DEFAULT nextval('usuarios_id_usuario_seq'::regclass);


--
-- Name: id_grupo; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY usuarios_grupos ALTER COLUMN id_grupo SET DEFAULT nextval('usuarios_grupos_id_grupo_seq'::regclass);


--
-- Name: id_venta; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas ALTER COLUMN id_venta SET DEFAULT nextval('ventas_id_venta_seq'::regclass);


--
-- Name: id_cargo_fijo; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas_cargos_fijos ALTER COLUMN id_cargo_fijo SET DEFAULT nextval('ventas_cargos_fijos_id_cargo_fijo_seq'::regclass);


--
-- Name: id_estado_historico; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas_estados_historico ALTER COLUMN id_estado_historico SET DEFAULT nextval('ventas_estados_historico_id_estado_historico_seq'::regclass);


--
-- Name: id_linea_venta; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas_lineas ALTER COLUMN id_linea_venta SET DEFAULT nextval('ventas_lineas_id_linea_venta_seq'::regclass);


--
-- Name: id_pago_venta; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas_pagos ALTER COLUMN id_pago_venta SET DEFAULT nextval('ventas_pagos_id_pago_venta_seq'::regclass);


--
-- Name: id_linea_pago; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas_pagos_lineas ALTER COLUMN id_linea_pago SET DEFAULT nextval('ventas_pagos_lineas_id_linea_pago_seq'::regclass);


--
-- Name: id_remito; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas_remitos ALTER COLUMN id_remito SET DEFAULT nextval('ventas_remitos_id_remito_seq'::regclass);


--
-- Name: id_linea_remito; Type: DEFAULT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas_remitos_lineas ALTER COLUMN id_linea_remito SET DEFAULT nextval('ventas_remitos_lineas_id_linea_remito_seq'::regclass);







--
-- Name: bancos_cuenta_corriente_id_movimiento_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('bancos_cuenta_corriente_id_movimiento_seq', 1, false);


--
-- Data for Name: bancos_cuentas; Type: TABLE DATA; Schema: public; Owner: retail
--



--
-- Name: bancos_cuentas_id_cuenta_banco_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('bancos_cuentas_id_cuenta_banco_seq', 1, false);


--
-- Name: bancos_id_banco_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('bancos_id_banco_seq', 1, false);


--
-- Data for Name: bancos_tipos_cuenta; Type: TABLE DATA; Schema: public; Owner: retail
--
insert into bancos_tipos_cuenta values
(1,'CAJA DE AHORROS');
insert into bancos_tipos_cuenta values
(2,'CUENTA CORRIENTE');


--
-- Name: bancos_tipos_cuenta_id_tipo_cuenta_banco_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('bancos_tipos_cuenta_id_tipo_cuenta_banco_seq', 2, true);




--
-- Name: cajas_categorias_movimientos_id_categoria_movimiento_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('cajas_categorias_movimientos_id_categoria_movimiento_seq', 1, false);


--
-- Name: cajas_id_caja_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('cajas_id_caja_seq', 1, false);




--
-- Name: cajas_movimientos_id_movimiento_caja_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('cajas_movimientos_id_movimiento_caja_seq', 1, false);


--
-- Data for Name: contabilidad_libros; Type: TABLE DATA; Schema: public; Owner: retail
--



--
-- Name: contabilidad_libros_id_libro_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('contabilidad_libros_id_libro_seq', 1, false);



--
-- Name: contabilidad_monedas_id_moneda_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('contabilidad_monedas_id_moneda_seq', 1, false);



--
-- Name: contabilidad_periodos_contables_id_periodo_contable_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('contabilidad_periodos_contables_id_periodo_contable_seq', 1, false);


--
-- Data for Name: contabilidad_plan_cuentas; Type: TABLE DATA; Schema: public; Owner: retail
--

INSERT INTO  contabilidad_plan_cuentas (id_cuenta, nombre_cuenta, numero_cuenta, id_cuenta_padre, descripcion_cuenta, cuenta_rubro, id_tipo_cuenta) VALUES
(1,	'ROOT',	'00000000',	null,	'CUENTA RAÍZ'	,true	,1);



--
-- Name: contabilidad_plan_cuentas_id_cuenta_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('contabilidad_plan_cuentas_id_cuenta_seq', 2, true);



--
-- Name: contabilidad_registro_contable_id_registro_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('contabilidad_registro_contable_id_registro_seq', 1, false);


--
-- Name: contabilidad_registro_contable_lineas_id_linea_registro_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('contabilidad_registro_contable_lineas_id_linea_registro_seq', 1, false);



--
-- Name: contabilidad_tipos_comprobantes_id_tipo_comprobante_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('contabilidad_tipos_comprobantes_id_tipo_comprobante_seq', 1, false);


--
-- Data for Name: contabilidad_tipos_cuenta; Type: TABLE DATA; Schema: public; Owner: retail
--

INSERT INTO contabilidad_tipos_cuenta (id_tipo_cuenta, nombre_tipo, descripcion_tipo) VALUES 
(1,	'ACTIVO',null);
INSERT INTO contabilidad_tipos_cuenta (id_tipo_cuenta, nombre_tipo, descripcion_tipo) VALUES 
(2,	'PASIVO',null);
INSERT INTO contabilidad_tipos_cuenta (id_tipo_cuenta, nombre_tipo, descripcion_tipo) VALUES 
(3,	'PATRIMONIO NETO',null);
INSERT INTO contabilidad_tipos_cuenta (id_tipo_cuenta, nombre_tipo, descripcion_tipo) VALUES 
(4,	'MOVIMIENTOS',null);



--
-- Name: contabilidad_tipos_cuenta_id_tipo_cuenta_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('contabilidad_tipos_cuenta_id_tipo_cuenta_seq', 4, true);



--
-- Name: contabilidad_tipos_operacion_id_tipo_operacion_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('contabilidad_tipos_operacion_id_tipo_operacion_seq', 1, false);




--
-- Name: depositos_id_deposito_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('depositos_id_deposito_seq', 1, false);


--
-- Data for Name: fiscal_alicuotas_iva; Type: TABLE DATA; Schema: public; Owner: retail
--

INSERT INTO fiscal_alicuotas_iva (id_alicuota_iva, nombre_alicuota_iva, valor_alicuota, gravar_iva, activo) VALUES 
(1,	'IVA 21%', 	21.00,	true,	true);
INSERT INTO fiscal_alicuotas_iva (id_alicuota_iva, nombre_alicuota_iva, valor_alicuota, gravar_iva, activo) VALUES 
(2,	'IVA 10,5%',	10.50,	true,	true);
INSERT INTO fiscal_alicuotas_iva (id_alicuota_iva, nombre_alicuota_iva, valor_alicuota, gravar_iva, activo) VALUES 
(3,	'IVA 27%',	27.00,	true,	true);
INSERT INTO fiscal_alicuotas_iva (id_alicuota_iva, nombre_alicuota_iva, valor_alicuota, gravar_iva, activo) VALUES 
(4,	'IVA EXENTO',	0.00,	false,	true);



--
-- Name: fiscal_alicuotas_iva_id_alicuota_iva_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('fiscal_alicuotas_iva_id_alicuota_iva_seq', 4, true);


--
-- Data for Name: fiscal_letras_comprobantes; Type: TABLE DATA; Schema: public; Owner: retail
--

INSERT INTO fiscal_letras_comprobantes (id_resoponsabildiad_iva_emisor, id_resoponsabildiad_iva_receptor, letra_comprobante) VALUES
(2,	1,	'B');
INSERT INTO fiscal_letras_comprobantes (id_resoponsabildiad_iva_emisor, id_resoponsabildiad_iva_receptor, letra_comprobante) VALUES
(2,	2,	'A');
INSERT INTO fiscal_letras_comprobantes (id_resoponsabildiad_iva_emisor, id_resoponsabildiad_iva_receptor, letra_comprobante) VALUES
(2,	3,	'B');
INSERT INTO fiscal_letras_comprobantes (id_resoponsabildiad_iva_emisor, id_resoponsabildiad_iva_receptor, letra_comprobante) VALUES
(2,	4,	'B');
INSERT INTO fiscal_letras_comprobantes (id_resoponsabildiad_iva_emisor, id_resoponsabildiad_iva_receptor, letra_comprobante) VALUES
(3,	1,	'C');
INSERT INTO fiscal_letras_comprobantes (id_resoponsabildiad_iva_emisor, id_resoponsabildiad_iva_receptor, letra_comprobante) VALUES
(3,	2,	'C');
INSERT INTO fiscal_letras_comprobantes (id_resoponsabildiad_iva_emisor, id_resoponsabildiad_iva_receptor, letra_comprobante) VALUES
(3,	3,	'C');
INSERT INTO fiscal_letras_comprobantes (id_resoponsabildiad_iva_emisor, id_resoponsabildiad_iva_receptor, letra_comprobante) VALUES
(3,	4,	'C');






--
-- Name: fiscal_libro_iva_ventas_id_factura_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('fiscal_libro_iva_ventas_id_factura_seq', 7, true);




--
-- Name: fiscal_libro_iva_ventas_lineas_id_linea_libro_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('fiscal_libro_iva_ventas_lineas_id_linea_libro_seq', 8, true);



--
-- Name: fiscal_periodos_fiscales_id_periodo_fiscal_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('fiscal_periodos_fiscales_id_periodo_fiscal_seq', 3, true);


--
-- Data for Name: fiscal_responsabilidades_iva; Type: TABLE DATA; Schema: public; Owner: retail
--

INSERT INTO fiscal_responsabilidades_iva (id_resoponsabildiad_iva, nombre_responsabildiad) VALUES
(1,	'CONSUMIDOR FINAL');
INSERT INTO fiscal_responsabilidades_iva (id_resoponsabildiad_iva, nombre_responsabildiad) VALUES
(2,	'RESPONSABLE INSCRIPTO');
INSERT INTO fiscal_responsabilidades_iva (id_resoponsabildiad_iva, nombre_responsabildiad) VALUES
(3,	'RESPONSABLE MONOTRIBUTO');
INSERT INTO fiscal_responsabilidades_iva (id_resoponsabildiad_iva, nombre_responsabildiad) VALUES
(4,	'EXENTO');



--
-- Name: fiscal_responsabilidades_iva_id_resoponsabildiad_iva_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('fiscal_responsabilidades_iva_id_resoponsabildiad_iva_seq', 4, true);


--
-- Data for Name: legal_generos; Type: TABLE DATA; Schema: public; Owner: retail
--

INSERT INTO legal_generos (id_genero, nombre_genero, simbolo, id_tipo_personeria) VALUES
(1,	'MASCULINO',	'M',	1);
INSERT INTO legal_generos (id_genero, nombre_genero, simbolo, id_tipo_personeria) VALUES
(2,	'FEMENINO',	'F',	1);
INSERT INTO legal_generos (id_genero, nombre_genero, simbolo, id_tipo_personeria) VALUES
(3,	'SIN GENERO',	'S',	2);



--
-- Name: legal_generos_id_genero_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('legal_generos_id_genero_seq', 3, true);


--
-- Data for Name: legal_tipos_documento; Type: TABLE DATA; Schema: public; Owner: retail
--

INSERT INTO legal_tipos_documento (id_tipo_documento, nombre_tipo_documento, cantidad_caracteres_minimo, cantidad_caracteres_maximo, id_tipo_personeria) VALUES
(1,	'DNI',	8,	8,	1);
INSERT INTO legal_tipos_documento (id_tipo_documento, nombre_tipo_documento, cantidad_caracteres_minimo, cantidad_caracteres_maximo, id_tipo_personeria) VALUES
(2,	'CUIT',	11	,11,	2);


--
-- Name: legal_tipos_documento_id_tipo_documento_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('legal_tipos_documento_id_tipo_documento_seq', 2, true);


--
-- Data for Name: legal_tipos_personeria; Type: TABLE DATA; Schema: public; Owner: retail
--

INSERT INTO legal_tipos_personeria (id_tipo_personeria, nombre_tipo) VALUES
(1,	'PERSONA FÍSICA');
INSERT INTO legal_tipos_personeria (id_tipo_personeria, nombre_tipo) VALUES 
(2,	'PERSONA JURÍDICA');



--
-- Name: legal_tipos_personeria_id_tipo_personeria_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('legal_tipos_personeria_id_tipo_personeria_seq', 2, true);


--
-- Data for Name: negocio_condiciones_operaciones; Type: TABLE DATA; Schema: public; Owner: retail
--

INSERT  INTO negocio_condiciones_operaciones (id_condicion, nombre_condicion, activo, venta, compra, pago_total, version) values
(1,	'CONTADO',	true,	true,	true,	true,	0);
INSERT  INTO negocio_condiciones_operaciones (id_condicion, nombre_condicion, activo, venta, compra, pago_total, version) values
(2, 	'CUENTA CORRIENTE',	true,	true,	true,	true,	0);



--
-- Data for Name: negocio_formas_pago; Type: TABLE DATA; Schema: public; Owner: retail
--

INSERT INTO negocio_formas_pago (id_forma_pago, nombre_forma_pago, nombre_corto, venta, compra) VALUES
(1,	'EFECTIVO',	'EFE',	true,	true);
INSERT INTO negocio_formas_pago (id_forma_pago, nombre_forma_pago, nombre_corto, venta, compra) VALUES
(2,	'TARJETAS DE CRÉDITO',	'TC',	true	,true);
INSERT INTO negocio_formas_pago (id_forma_pago, nombre_forma_pago, nombre_corto, venta, compra) VALUES
(3,	'TARJETAS DE DÉBITO',	'TD',	true	,true);
INSERT INTO negocio_formas_pago (id_forma_pago, nombre_forma_pago, nombre_corto, venta, compra) VALUES
(4,	'CHEQUES',	'CHQ',	true,	true);



--
-- Data for Name: parametros; Type: TABLE DATA; Schema: public; Owner: retail
--

INSERT INTO parametros (nombre_parametro, valor_parametro, descripcion_parametro) VALUES
('empresa.nombre',	'Empresa de pruebas',	'Nombre de la empresa');
INSERT INTO parametros (nombre_parametro, valor_parametro, descripcion_parametro) VALUES
('empresa.provincia',	'Chaco',	'Provincia de la empresa');
INSERT INTO parametros (nombre_parametro, valor_parametro, descripcion_parametro) VALUES
('empresa.cuit',	'2024257719',	'CUIT de la empresa');
INSERT INTO parametros (nombre_parametro, valor_parametro, descripcion_parametro) VALUES
('empresa.direccion',	'Av Alberdi 123',	'Dirección de la empresa');
INSERT INTO parametros (nombre_parametro, valor_parametro, descripcion_parametro) VALUES
('empresa.razon_social',	'Pruebas SRL',	'Razón social de la empresa');
INSERT INTO parametros (nombre_parametro, valor_parametro, descripcion_parametro) VALUES
('empresa.telefono',	'(0362) 412345',	'Teléfono fijo de la empresa');
INSERT INTO parametros (nombre_parametro, valor_parametro, descripcion_parametro) VALUES
('empresa.nombre_fantasia',	'GT Software',	'El nombre de fantasía de la empresa');
INSERT INTO parametros (nombre_parametro, valor_parametro, descripcion_parametro) VALUES
('empresa.localidad',	'Resistencia',	'Localidad de la empresa');
INSERT INTO parametros (nombre_parametro, valor_parametro, descripcion_parametro) VALUES
('facturacion.preimreso.cantidad_copias',	'3',	'Cantidad de copias que debe imprimir el sistema para formularios preimpresos');
INSERT INTO parametros (nombre_parametro, valor_parametro, descripcion_parametro) VALUES
('empresa.email',	'empresa@empresa.com',	'Email de la empresa');
INSERT INTO parametros (nombre_parametro, valor_parametro, descripcion_parametro) VALUES
('presupuesto.impresion.cantidad_copias',	'2',	'Cantidad de copias que debe imprimir el  sistema para los presupuestos');




--
-- Name: personas_cuenta_corriente_id_movimiento_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('personas_cuenta_corriente_id_movimiento_seq', 13, true);


--
-- Name: personas_id_persona_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('personas_id_persona_seq', 8, true);


--
-- Data for Name: personas_imagenes; Type: TABLE DATA; Schema: public; Owner: retail
--


--
-- Name: personas_imagenes_id_imagen_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('personas_imagenes_id_imagen_seq', 1, false);



--
-- Name: personas_telefonos_id_telefono_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('personas_telefonos_id_telefono_seq', 6, true);




--
-- Name: personas_tipos_imagenes_id_tipo_imagen_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('personas_tipos_imagenes_id_tipo_imagen_seq', 1, false);




--
-- Name: privilegios_id_privilegio_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('privilegios_id_privilegio_seq', 1, false);





--
-- Name: productos_caracteristicas_id_caracteristica_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('productos_caracteristicas_id_caracteristica_seq', 1, false);


--
-- Name: productos_id_producto_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('productos_id_producto_seq', 8, true);


--
-- Data for Name: productos_imagenes; Type: TABLE DATA; Schema: public; Owner: retail
--



--
-- Name: productos_imagenes_id_imagen_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('productos_imagenes_id_imagen_seq', 1, false);


--
-- Data for Name: productos_porcentajes; Type: TABLE DATA; Schema: public; Owner: retail
--



--
-- Name: productos_porcentajes_id_porcentaje_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('productos_porcentajes_id_porcentaje_seq', 1, false);


--
-- Data for Name: productos_rubros; Type: TABLE DATA; Schema: public; Owner: retail
--

INSERT INTO productos_rubros (id_rubro, nombre_rubro) VALUES
(1,	'SIN RUBRO');



--
-- Name: productos_rubros_id_rubro_seq; Type: SEQUENCE SET; Schema: public; Owner: retail
--

SELECT pg_catalog.setval('productos_rubros_id_rubro_seq', 2, true);


--
-- Data for Name: productos_sub_rubros; Type: TABLE DATA; Schema: public; Owner: retail
--

INSERT INTO  productos_sub_rubros (id_sub_rubro, id_rubro, nombre_sub_rubro) VALUES
(1,	1,	'SIN SUB RUBRO');



--
-- Data for Name: productos_tipos_proveeduria; Type: TABLE DATA; Schema: public; Owner: retail
--

INSERT INTO productos_tipos_proveeduria (id_tipo_proveeduria, nombre_tipo_proveeduria, puede_comprarse, puede_venderse, control_stock, cambiar_precio_venta) values
(1,	'BIENES DE CAMBIO',	true,	true,	true,	false);
INSERT INTO productos_tipos_proveeduria (id_tipo_proveeduria, nombre_tipo_proveeduria, puede_comprarse, puede_venderse, control_stock, cambiar_precio_venta) values

(2,	'BIENES DE USO',	true,	false,	true,	false);
INSERT INTO productos_tipos_proveeduria (id_tipo_proveeduria, nombre_tipo_proveeduria, puede_comprarse, puede_venderse, control_stock, cambiar_precio_venta) values

(3,	'SERVICIOS',	true,	true,	false,	false);
INSERT INTO productos_tipos_proveeduria (id_tipo_proveeduria, nombre_tipo_proveeduria, puede_comprarse, puede_venderse, control_stock, cambiar_precio_venta) values
(4,	'DESCUENTOS ESPECIALES',	false,	true,	false,	true);



--
-- Data for Name: productos_tipos_unidades; Type: TABLE DATA; Schema: public; Owner: retail
--

INSERT INTO productos_tipos_unidades (id_tipo_unidad, nombre_unidad, cantidad_entera) values
(1,	'UNIDAD',	true);

INSERT INTO productos_tipos_unidades (id_tipo_unidad, nombre_unidad, cantidad_entera) values
(2,	'METROS',	false);

INSERT INTO productos_tipos_unidades (id_tipo_unidad, nombre_unidad, cantidad_entera) values
(3,	'LITROS',	false);

INSERT INTO productos_tipos_unidades (id_tipo_unidad, nombre_unidad, cantidad_entera) values
(4,	'KILOGRAMO',	false);

INSERT INTO productos_tipos_unidades (id_tipo_unidad, nombre_unidad, cantidad_entera) values
(5,	'TONELADA',	false);

INSERT INTO productos_tipos_unidades (id_tipo_unidad, nombre_unidad, cantidad_entera) values
(6,	'BULTO',	false);


--
-- Data for Name: stock_movimientos_tipos; Type: TABLE DATA; Schema: public; Owner: retail
--

INSERT INTO stock_movimientos_tipos (id_tipo_movimiento, nombre_tipo) VALUES
(1,	'RECEPCIÓN DE PROVEEDOR');
INSERT INTO stock_movimientos_tipos (id_tipo_movimiento, nombre_tipo) VALUES
(2,	'VENTA');
INSERT INTO stock_movimientos_tipos (id_tipo_movimiento, nombre_tipo) VALUES
(3,	'TRASLADO');
INSERT INTO stock_movimientos_tipos (id_tipo_movimiento, nombre_tipo) VALUES
(4,	'AJUSTES');
INSERT INTO stock_movimientos_tipos (id_tipo_movimiento, nombre_tipo) VALUES
(6,	'BAJA POR MAL ESTADO');
INSERT INTO stock_movimientos_tipos (id_tipo_movimiento, nombre_tipo) VALUES
(5,	'BAJA POR ROBO/PÉRDIDA');



--
-- Data for Name: ubicacion_paises; Type: TABLE DATA; Schema: public; Owner: retail
--

INSERT INTO ubicacion_paises (id_pais, nombre_pais, version) VALUES
(1,	'ARGENTINA',	0);
INSERT INTO ubicacion_paises (id_pais, nombre_pais, version) VALUES
(2,	'BOLIVIA',	0);
INSERT INTO ubicacion_paises (id_pais, nombre_pais, version) VALUES
(3,	'BRASIL',	0);



--
-- Data for Name: ubicacion_provincias; Type: TABLE DATA; Schema: public; Owner: retail
--

INSERT INTO ubicacion_provincias VALUES (1, 1, 'CHACO', 0);
INSERT INTO ubicacion_provincias VALUES (2, 1, 'FORMOSA', 0);
INSERT INTO ubicacion_provincias VALUES (3, 1, 'MISIONES', 0);
INSERT INTO ubicacion_provincias VALUES (4, 1, 'SALTA', 0);
INSERT INTO ubicacion_provincias VALUES (5, 1, 'SANTA FE', 0);
INSERT INTO ubicacion_provincias VALUES (8, 1, 'TUCUMÃN', 0);
INSERT INTO ubicacion_provincias VALUES (9, 1, 'BUENOS AIRES', 0);
INSERT INTO ubicacion_provincias VALUES (10, 1, 'SANTA CRUZ', 0);
INSERT INTO ubicacion_provincias VALUES (11, 1, 'LA RIOJA', 0);
INSERT INTO ubicacion_provincias VALUES (12, 1, 'CHUBUT', 0);
INSERT INTO ubicacion_provincias VALUES (13, 1, 'SAN JUAN', 0);
INSERT INTO ubicacion_provincias VALUES (14, 1, 'SAN LUIS', 0);
INSERT INTO ubicacion_provincias VALUES (15, 1, 'MENDOZA', 0);
INSERT INTO ubicacion_provincias VALUES (16, 1, 'NEUQUEN', 0);
INSERT INTO ubicacion_provincias VALUES (17, 1, 'RIO NEGRO', 0);
INSERT INTO ubicacion_provincias VALUES (18, 1, 'LA PAMPA', 0);
INSERT INTO ubicacion_provincias VALUES (19, 1, 'JUJUY', 0);
INSERT INTO ubicacion_provincias VALUES (20, 1, 'ENTRE RIOS', 0);
INSERT INTO ubicacion_provincias VALUES (21, 1, 'CORDOBA', 0);
INSERT INTO ubicacion_provincias VALUES (23, 1, 'CATAMARCA', 0);
INSERT INTO ubicacion_provincias VALUES (6, 1, 'CORRIENTES', 0);
INSERT INTO ubicacion_provincias VALUES (7, 1, 'SANTIAGO DEL ESTERO', 0);
INSERT INTO ubicacion_provincias VALUES (22, 1, 'TIERRA DEL FIEGO', 0);

--Localidades
INSERT INTO ubicacion_localidades VALUES (1, 1, 'RESISTENCIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2, 1, 'AVIA TERAI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3, 1, 'CAMPO LARGO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4, 1, 'CHARATA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (5, 1, 'COLONIA BENÃTEZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (6, 1, 'COLONIA ELISA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (7, 1, 'COLONIAS UNIDAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (8, 1, 'COMANDANCIA FRÃAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (9, 1, 'CONCEPCIÃN DEL BERMEJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (10, 1, 'CORONEL DU GRATY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (11, 1, 'CORZUELA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (12, 1, 'EL PARANACITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (13, 1, 'EL SAUZALITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (14, 1, 'FORTÃN BELGRANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (15, 1, 'GANCEDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (16, 1, 'GENERAL JOSÃ DE SAN MARTÃN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (17, 1, 'GENERAL PINEDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (18, 1, 'HERMOSO CAMPO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (19, 1, 'HORQUILLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (20, 1, 'JUAN JOSE CASTELLI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (21, 1, 'LA CLOTILDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (22, 1, 'LA ESCONDIDA (ARGENTINA)', '0', 0);
INSERT INTO ubicacion_localidades VALUES (23, 1, 'LA LEONESA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (24, 1, 'LA TIGRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (25, 1, 'LA VERDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (26, 1, 'LAS BREÃAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (27, 1, 'LAS GARCITAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (28, 1, 'LAS PALMAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (29, 1, 'LOS FRENTONES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (30, 1, 'MACHAGAI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (31, 1, 'MAKALLÃ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (32, 1, 'MARGARITA BELÃN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (33, 1, 'MIRAFLORES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (34, 1, 'MISIÃN NUEVA POMPEYA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (35, 1, 'NAPENAY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (36, 1, 'PAMPA DEL INDIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (37, 1, 'PAMPA DEL INFIERNO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (38, 1, 'PRESIDENCIA DE LA PLAZA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (39, 1, 'PRESIDENCIA ROCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (40, 1, 'PUERTO BERMEJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (41, 1, 'PUERTO TIROL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (42, 1, 'QUITILIPI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (43, 1, 'SAN BERNARDO (CHACO)', '0', 0);
INSERT INTO ubicacion_localidades VALUES (44, 1, 'PARAJE SAN FERNANDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (45, 1, 'SANTA SYLVINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (46, 1, 'TACO POZO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (47, 1, 'TRES ISLETAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (48, 1, 'VILLA ÃNGELA', '3540', 0);
INSERT INTO ubicacion_localidades VALUES (49, 1, 'VILLA BERTHET', '0', 0);
INSERT INTO ubicacion_localidades VALUES (50, 1, 'VILLA RÃO BERMEJITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (51, 1, 'BARRANQUERAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (52, 1, 'FONTANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (53, 1, 'LA LIGURIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (54, 1, 'PUERTO VILELAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (55, 5, 'AARON CASTELLANOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (56, 5, 'ACEBAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (57, 5, 'ADELA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (58, 5, 'AERO CLUB ARGENTINO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (59, 5, 'ALBARELLOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (60, 5, 'ALCORTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (61, 5, 'ALDAO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (62, 5, 'ALEJANDRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (63, 5, 'ALTO VERDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (64, 5, 'ALVAREZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (65, 5, 'ALVEAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (66, 5, 'AMBROSETTI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (67, 5, 'AMENABAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (68, 5, 'ANDINO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (69, 5, 'ANGEL GALLARDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (70, 5, 'ANGELICA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (71, 5, 'ANTARTIDA ARGENTINA ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (72, 5, 'ANTONIO PINI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (73, 5, 'AREQUITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (74, 5, 'ARIJON ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (75, 5, 'ARMINDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (76, 5, 'ARMSTRONG', '0', 0);
INSERT INTO ubicacion_localidades VALUES (77, 5, 'AROCENA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (78, 5, 'AROMOS ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (79, 5, 'ARRASCAETA ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (80, 5, 'ARROYO AGUIAR ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (81, 5, 'ARROYO CEIBAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (82, 5, 'ARROYO DEL REY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (83, 5, 'ARROYO LEYES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (84, 5, 'ARRUFO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (85, 5, 'ARTEAGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (86, 5, 'ASCOCHINGAS ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (87, 5, 'ATALIVA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (88, 5, 'AURELIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (89, 5, 'AURELIA NORTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (90, 5, 'AURELIA SUR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (91, 5, 'AVELLANEDA ,EST.EWALD', '0', 0);
INSERT INTO ubicacion_localidades VALUES (92, 5, 'BARRANCAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (93, 5, 'BAUER Y SIGEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (94, 5, 'BERABEVU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (95, 5, 'BERNA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (96, 5, 'BERNARDO DE IRIGOYEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (97, 5, 'BIGAND', '0', 0);
INSERT INTO ubicacion_localidades VALUES (98, 5, 'BOMBAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (99, 5, 'BOUQUET', '0', 0);
INSERT INTO ubicacion_localidades VALUES (100, 5, 'BUSTINZA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (101, 5, 'CABAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (102, 5, 'CACIQUE ARIACAIQUIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (103, 5, 'CAFFERATA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (104, 5, 'CALCHAQUI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (105, 5, 'CAMPO GARAY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (106, 5, 'CAMPO REDONDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (107, 5, 'CAÃADA DE GOMEZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (108, 5, 'CAÃADA DEL UCLE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (109, 5, 'CAÃADA OMBU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (110, 5, 'CAÃADA RICA ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (111, 5, 'CAÃADA ROSQUIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (112, 5, 'CAPITAN BERMUDEZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (113, 5, 'CAPIVARA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (114, 5, 'CARAGUATAY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (115, 5, 'CARCARAÃA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (116, 5, 'CARMEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (117, 5, 'CARRERAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (118, 5, 'CARRIZALES ,EST.CLARKE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (119, 5, 'CASABLANCA ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (120, 5, 'CASALEGNO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (121, 5, 'CASAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (122, 5, 'CASILDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (123, 5, 'CASTELAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (124, 5, 'CASTELLANOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (125, 5, 'CASTELLANOS, AARON ,EST.CASTELLANOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (126, 5, 'CAVOUR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (127, 5, 'CAYASTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (128, 5, 'CAYASTACITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (129, 5, 'CENTENO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (130, 5, 'CERES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (131, 5, 'CERRITOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (132, 5, 'CHABAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (133, 5, 'CHAÃAR LADEADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (134, 5, 'CHAPUY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (135, 5, 'CHATEAUBRIAND ,EST.MURPHY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (136, 5, 'CHOVET', '0', 0);
INSERT INTO ubicacion_localidades VALUES (137, 5, 'CHRISTOPHERSEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (138, 5, 'CLASON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (139, 5, 'CLUCELLAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (140, 5, 'CLUCELLAS ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (141, 5, 'COLASTINE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (142, 5, 'COLMENA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (143, 5, 'COLONIA ALDAO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (144, 5, 'COLONIA ANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (145, 5, 'COLONIA ANGELONI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (146, 5, 'COLONIA BELGRANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (147, 5, 'COLONIA BOSSI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (148, 5, 'COLONIA CELLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (149, 5, 'COLONIA DOLORES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (150, 5, 'COLONIA DOLORES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (151, 5, 'COLONIA EL TOBA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (152, 5, 'COLONIA ELLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (153, 5, 'COLONIA LA CAMILA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (154, 5, 'COLONIA MASCIAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (155, 5, 'COLONIA MEDICI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (156, 5, 'COLONIA RAQUEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (157, 5, 'COLONIA ROSA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (158, 5, 'COLONIA SILVA ,EST.ABIPONES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (159, 5, 'COLONIA TACURALES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (160, 5, 'CONSTANZA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (161, 5, 'CONSTITUYENTES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (162, 5, 'CORONDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (163, 5, 'CORONEL ARNOLD', '0', 0);
INSERT INTO ubicacion_localidades VALUES (164, 5, 'CORONEL BOGADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (165, 5, 'CORONEL FRAGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (166, 5, 'CORONEL RODRIGUEZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (167, 5, 'CORONEL ROSETTI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (168, 5, 'CORREA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (169, 5, 'CRISPI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (170, 5, 'CULLEN ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (171, 5, 'CULULU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (172, 5, 'CURUPAITY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (173, 5, 'DENIS, GREGORIA PEREZ DE ,EST.EL NOCHERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (174, 5, 'DIAZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (175, 5, 'DIEGO DE ALVEAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (176, 5, 'DOCTOR BARROS PAZOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (177, 5, 'DURHAM ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (178, 5, 'ECHEVERRIA, VICENTE A.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (179, 5, 'EDISON, TOMAS A.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (180, 5, 'EGUSQUIZA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (181, 5, 'EL BAYO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (182, 5, 'EL CANTOR ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (183, 5, 'EL JARDIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (184, 5, 'EL LAUREL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (185, 5, 'EL RABON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (186, 5, 'EL SOMBRERITO ,EST.PAUL GROUSSAC', '0', 0);
INSERT INTO ubicacion_localidades VALUES (187, 5, 'EL TREBOL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (188, 5, 'ELISA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (189, 5, 'ELORTONDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (190, 5, 'EMILIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (191, 5, 'EMPALME VILLA CONSTITUCION', '0', 0);
INSERT INTO ubicacion_localidades VALUES (192, 5, 'ESCALADA, MARCELINO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (193, 5, 'ESCALADA, MIGUEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (194, 5, 'ESMERALDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (195, 5, 'ESPERANZA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (196, 5, 'ESPIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (197, 5, 'ESTHER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (198, 5, 'ESTRADA, JOSE M.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (199, 5, 'EUSEBIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (200, 5, 'FELICIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (201, 5, 'FIGHIERA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (202, 5, 'FIRMAT', '0', 0);
INSERT INTO ubicacion_localidades VALUES (203, 5, 'FLOR DE ORO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (204, 5, 'FLORENCIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (205, 5, 'FLORIDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (206, 5, 'FORTIN ATAHUALPA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (207, 5, 'FRANCK', '0', 0);
INSERT INTO ubicacion_localidades VALUES (208, 5, 'FRAY LUIS BELTRAN ,EST.TTE.CNL.L.BELTRAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (209, 5, 'FRONTERA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (210, 5, 'FUENTES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (211, 5, 'FUNES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (212, 5, 'GALISTEO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (213, 5, 'GALLARDO, ANGEL ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (214, 5, 'GALVEZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (215, 5, 'GARABATO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (216, 5, 'GARIBALDI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (217, 5, 'GENERAL GELLY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (218, 5, 'GENERAL LAGOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (219, 5, 'GESSLER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (220, 5, 'GOBERNADOR CANDIOTI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (221, 5, 'GOBERNADOR CRESPO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (222, 5, 'GODEKEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (223, 5, 'GODOY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (224, 5, 'GOLONDRINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (225, 5, 'GOMEZ CELLO, PEDRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (226, 5, 'GRANADERO BAIGORRIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (227, 5, 'GRANADERO BLAS BARGAS ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (228, 5, 'GRANADEROS ,PDA.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (229, 5, 'GRANEROS ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (230, 5, 'GRUTLY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (231, 5, 'GRUTLY NORTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (232, 5, 'GUADALUPE ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (233, 5, 'GUADALUPE NORTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (234, 5, 'GUASUNCHO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (235, 5, 'GUAYCURU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (236, 5, 'HANSEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (237, 5, 'HELVECIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (238, 5, 'HERSILIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (239, 5, 'HUANQUEROS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (240, 5, 'HUGENTOBLER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (241, 5, 'HUGES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (242, 5, 'HUMBOLDT', '0', 0);
INSERT INTO ubicacion_localidades VALUES (243, 5, 'HUME ,EST.EL GAUCHO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (244, 5, 'IBARLUCEA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (245, 5, 'INDEPENDENCIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (246, 5, 'INGENIERO BOASI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (247, 5, 'INGENIERO CHANOURDIE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (248, 5, 'INTIYACO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (249, 5, 'IRIGOYEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (250, 5, 'IRIGOYEN, BERNARDO DE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (251, 5, 'IRIONDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (252, 5, 'ITURRASPE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (253, 5, 'JOSEFINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (254, 5, 'JUNCAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (255, 5, 'KILOMETR 465 ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (256, 5, 'KILOMETRO 17 ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (257, 5, 'KILOMETRO 187 ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (258, 5, 'KILOMETRO 213 ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (259, 5, 'KILOMETRO 320', '0', 0);
INSERT INTO ubicacion_localidades VALUES (260, 5, 'KILOMETRO 392 ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (261, 5, 'KILOMETRO 403 ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (262, 5, 'KILOMETRO 405 ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (263, 5, 'KILOMETRO 408 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (264, 5, 'KILOMETRO 41 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (265, 5, 'KILOMETRO 41 ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (266, 5, 'KILOMETRO 443 ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (267, 5, 'KILOMETRO 468 ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (268, 5, 'KILOMETRO 483 ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (269, 5, 'KILOMETRO 501 ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (270, 5, 'KILOMETRO 85 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (271, 5, 'KILOMETRO 9 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (272, 5, 'LA BLANCA CENTRAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (273, 5, 'LA BRAVA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (274, 5, 'LA CABRAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (275, 5, 'LA CALIFORNIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (276, 5, 'LA CHISPA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (277, 5, 'LA CRIOLLA ,EST.CAÃADITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (278, 5, 'LA GALLARETA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (279, 5, 'LA GUARDIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (280, 5, 'LA LUCILA ,EST.LUCILA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (281, 5, 'LA PELADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (282, 5, 'LA PENCA Y CARAGUATA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (283, 5, 'LA RESERVA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (284, 5, 'LA RUBIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (285, 5, 'LA VANGUARDIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (286, 5, 'LA ZULEMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (287, 5, 'LABORDEBOY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (288, 5, 'LAGUNA PAIVA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (289, 5, 'LANDETA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (290, 5, 'LANTERI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (291, 5, 'LARGUIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (292, 5, 'LARRECHEA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (293, 5, 'LAS AVISPAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (294, 5, 'LAS BANDURRIAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (295, 5, 'LAS CAÃAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (296, 5, 'LAS FLORES ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (297, 5, 'LAS GAMAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (298, 5, 'LAS GRAZAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (299, 5, 'LAS PALMERAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (300, 5, 'LAS PAREJAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (301, 5, 'LAS PETACAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (302, 5, 'LAS ROSAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (303, 5, 'LAS TOSCAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (304, 5, 'LAS TROJAS ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (305, 5, 'LAS TUNAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (306, 5, 'LAZZARINO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (307, 5, 'LEHMANN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (308, 5, 'LEIVA, LUCIANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (309, 5, 'LLAMBI CAMPBELL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (310, 5, 'LOGROÃO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (311, 5, 'LOMA ALTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (312, 5, 'LOPEZ ,EST.SAN MARTIN DE TOURS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (313, 5, 'LOPEZ, LUCIO V.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (314, 5, 'LOS AMORES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (315, 5, 'LOS CARDOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (316, 5, 'LOS CERRILLOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (317, 5, 'LOS CHARABONES ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (318, 5, 'LOS MOLINOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (319, 5, 'LOS NOGALES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (320, 5, 'LOS QUIRQUINCHOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (321, 5, 'LOS SEMBRADOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (322, 5, 'LOS TABANOS ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (323, 5, 'LUDUEÃA ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (324, 5, 'MACIEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (325, 5, 'MAGGIOLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (326, 5, 'MALABRIGO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (327, 5, 'MANUCHO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (328, 5, 'MARGARITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (329, 5, 'MARIA EUGENIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (330, 5, 'MARIA JUANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (331, 5, 'MARIA SUSANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (332, 5, 'MARIA TERESA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (333, 5, 'MATILDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (334, 5, 'MAUA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (335, 5, 'MELINCUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (336, 5, 'MERCEDITAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (337, 5, 'MOCOVI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (338, 5, 'MOLINA, JUAN B.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (339, 5, 'MOLINAS, NICANOR E.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (340, 5, 'MONIGOTES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (341, 5, 'MONJE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (342, 5, 'MONTE FLORES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (343, 5, 'MONTE VERA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (344, 5, 'MONTEFIORE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (345, 5, 'MONTES DE OCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (346, 5, 'MOUSSY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (347, 5, 'ÃANDUCITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (348, 5, 'NARE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (349, 5, 'NELSON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (350, 5, 'NUEVA ITALIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (351, 5, 'NUEVA LEHMANN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (352, 5, 'NUEVO ALBERDI ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (353, 5, 'NUEVO TORINO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (354, 5, 'OGILVIE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (355, 5, 'OLIVEROS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (356, 5, 'OMBU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (357, 5, 'OROÃO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (358, 5, 'PALACIOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (359, 5, 'PALACIOS, LUIS ,EST.LA SALADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (360, 5, 'PAVON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (361, 5, 'PAVON ARRIBA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (362, 5, 'PAZ, FRANCISCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (363, 5, 'PAZ, MAXIMO ,EST.PAZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (364, 5, 'PELLEGRINI, CARLOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (365, 5, 'PEREYRA, ZENON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (366, 5, 'PEREZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (367, 5, 'PEYRANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (368, 5, 'PIAMONTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (369, 5, 'PILAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (370, 5, 'PINI, ANTONIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (371, 5, 'PINO DE SAN LORENZO ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (372, 5, 'PIQUETE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (373, 5, 'POMPEYA ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (374, 5, 'PORTUGALETTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (375, 5, 'POZO BORRADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (376, 5, 'PRESIDENTE ROCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (377, 5, 'PROGRESO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (378, 5, 'PROVIDENCIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (379, 5, 'PUEBLO ANDINO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (380, 5, 'PUEBLO MARINI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (381, 5, 'PUEBLO MUÃOZ ,EST.BERNARD', '0', 0);
INSERT INTO ubicacion_localidades VALUES (382, 5, 'PUEBLO NUEVO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (383, 5, 'PUERTO GABOTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (384, 5, 'PUERTO GENERAL SAN MARTIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (385, 5, 'PUERTO OCAMPO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (386, 5, 'PUERTO PIRACUACITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (387, 5, 'PUERTO RECONQUISTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (388, 5, 'PUERTO SAN LORENZO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (389, 5, 'PUJATO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (390, 5, 'RAFAELA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (391, 5, 'RAMAYON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (392, 5, 'RAMONA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (393, 5, 'RAMS, ESTEBAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (394, 5, 'RASTREADOR FOURNIER ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (395, 5, 'RECONQUISTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (396, 5, 'RECREO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (397, 5, 'RICARDONE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (398, 5, 'RINCON DEL POTRERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (399, 5, 'RINCON DEL QUEBRACHO ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (400, 5, 'ROLDAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (401, 5, 'ROMANG', '0', 0);
INSERT INTO ubicacion_localidades VALUES (402, 5, 'ROSARIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (403, 5, 'RUEDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (404, 5, 'RUFINO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (405, 5, 'RUNCIMAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (406, 5, 'SA PEREYRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (407, 5, 'SAAVEDRA, MARIANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (408, 5, 'SAGUIER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (409, 5, 'SALADERO M.CABAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (410, 5, 'SALTO GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (411, 5, 'SAN ANTONIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (412, 5, 'SAN ANTONIO DE OBLIGADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (413, 5, 'SAN BERNARDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (414, 5, 'SAN BERNARDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (415, 5, 'SAN CARLOS ,EMP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (416, 5, 'SAN CARLOS CENTRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (417, 5, 'SAN CARLOS NORTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (418, 5, 'SAN CARLOS SUR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (419, 5, 'SAN CRISTOBAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (420, 5, 'SAN EDUARDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (421, 5, 'SAN ESTANISLAO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (422, 5, 'SAN EUGENIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (423, 5, 'SAN FABIAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (424, 5, 'SAN FRANCISCO DE SANTA FE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (425, 5, 'SAN GENARO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (426, 5, 'SAN GENARO NORTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (427, 5, 'SAN GREGORIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (428, 5, 'SAN GUILLERMO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (429, 5, 'SAN JAVIER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (430, 5, 'SAN JERONIMO DEL SAUCE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (431, 5, 'SAN JERONIMO NORTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (432, 5, 'SAN JERONIMO SUR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (433, 5, 'SAN JORGE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (434, 5, 'SAN JOSE DE LA ESQUINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (435, 5, 'SAN JOSE DEL RINCON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (436, 5, 'SAN JUSTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (437, 5, 'SAN LORENZO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (438, 5, 'SAN MARTIN DE LAS ESCOBAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (439, 5, 'SAN MARTIN NORTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (440, 5, 'SAN PEDRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (441, 5, 'SAN RICARDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (442, 5, 'SAN VICENTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (443, 5, 'SANCTI SPIRITU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (444, 5, 'SANFORD', '0', 0);
INSERT INTO ubicacion_localidades VALUES (445, 5, 'SANTA CLARA DE BUENA VISTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (446, 5, 'SANTA CLARA DE SAGUIER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (447, 5, 'SANTA EMILIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (448, 5, 'SANTA FE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (449, 5, 'SANTA ISABEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (450, 5, 'SANTA LUCIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (451, 5, 'SANTA MARGARITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (452, 5, 'SANTA ROSA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (453, 5, 'SANTA TERESA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (454, 5, 'SANTO DOMINGO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (455, 5, 'SANTO TOME', '0', 0);
INSERT INTO ubicacion_localidades VALUES (456, 5, 'SANTURCE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (457, 5, 'SARGENTO CABRAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (458, 5, 'SARMIENTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (459, 5, 'SASTRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (460, 5, 'SAUCE VIEJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (461, 5, 'SCHIFFNER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (462, 5, 'SERODINO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (463, 5, 'SOLDINI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (464, 5, 'SOLEDAD', '0', 0);
INSERT INTO ubicacion_localidades VALUES (465, 5, 'SORRENTO ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (466, 5, 'SOUTOMAYOR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (467, 5, 'STEPHENSON ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (468, 5, 'SUARDI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (469, 5, 'SUNCHALES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (470, 5, 'SUSANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (471, 5, 'TACUARENDI ,EMB.KILOMETRO 421', '0', 0);
INSERT INTO ubicacion_localidades VALUES (472, 5, 'TACURAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (473, 5, 'TARRAGONA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (474, 5, 'TARTAGAL ,EST.EL TAJAMAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (475, 5, 'TEODELINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (476, 5, 'TEODELINA ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (477, 5, 'THEOBALD', '0', 0);
INSERT INTO ubicacion_localidades VALUES (478, 5, 'TIMBUES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (479, 5, 'TOBA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (480, 5, 'TORRES, MIGUEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (481, 5, 'TORTUGAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (482, 5, 'TOSTADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (483, 5, 'TOTORAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (484, 5, 'TRAILL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (485, 5, 'UMBERTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (486, 5, 'URANGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (487, 5, 'VENADO TUERTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (488, 5, 'VERA ,EST.GOBERNADOR VERA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (489, 5, 'VERA MUJICA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (490, 5, 'VERA Y PINTADO ,EST.GUARANIES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (491, 5, 'VIDELA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (492, 5, 'VILA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (493, 5, 'VILLA AMELIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (494, 5, 'VILLA AMERICA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (495, 5, 'VILLA ANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (496, 5, 'VILLA CAÃAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (497, 5, 'VILLA CONSTITUCION', '0', 0);
INSERT INTO ubicacion_localidades VALUES (498, 5, 'VILLA DIEGO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (499, 5, 'VILLA ELOISA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (500, 5, 'VILLA ESTELA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (501, 5, 'VILLA GOBERNADOR GALVEZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (502, 5, 'VILLA GUILLERMINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (503, 5, 'VILLA MARGARITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (504, 5, 'VILLA MINETTI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (505, 5, 'VILLA MUGUETA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (506, 5, 'VILLA OCAMPO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (507, 5, 'VILLA SARALEGUI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (508, 5, 'VILLA TRINIDAD', '0', 0);
INSERT INTO ubicacion_localidades VALUES (509, 5, 'VILLADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (510, 5, 'VILLE, MOISES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (511, 5, 'VIRGINIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (512, 5, 'VUELTA DEL PARAGUAYO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (513, 5, 'WHEELWRIGHT', '0', 0);
INSERT INTO ubicacion_localidades VALUES (514, 5, 'WILDERMUTH ,EST.GRANADERO B.BUSTOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (515, 5, 'ZAVALLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (516, 6, '9 DE JULIO ,EST.PUEBLO DE JULIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (517, 6, 'ACUÃA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (518, 6, 'AGUAPEY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (519, 6, 'AGUAY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (520, 6, 'ALEM CUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (521, 6, 'ALFONSO LOMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (522, 6, 'ALVEAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (523, 6, 'ANGUA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (524, 6, 'APIPE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (525, 6, 'ARERUNGUA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (526, 6, 'ARROYITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (527, 6, 'ARROYO AMBROSIO ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (528, 6, 'ARROYO GONZALEZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (529, 6, 'ARROYO PONTON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (530, 6, 'BAIBIENE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (531, 6, 'BAÃADO SUR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (532, 6, 'BATEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (533, 6, 'BELLA VISTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (534, 6, 'BERON DE ASTRADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (535, 6, 'BONPLAND', '0', 0);
INSERT INTO ubicacion_localidades VALUES (536, 6, 'BOQUERON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (537, 6, 'BUENA VISTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (538, 6, 'CAA GUAZU ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (539, 6, 'CABRED', '0', 0);
INSERT INTO ubicacion_localidades VALUES (540, 6, 'CAMPO GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (541, 6, 'CAMPO GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (542, 6, 'CAÃADITAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (543, 6, 'CAPILLITAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (544, 6, 'CAPITAN JOAQUIN MADARIAGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (545, 6, 'CAZA PAVA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (546, 6, 'CAZADORES CORRENTINOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (547, 6, 'CERRITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (548, 6, 'CERRUDO CUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (549, 6, 'CHAVARRIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (550, 6, 'COLONIA 3 DE ABRIL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (551, 6, 'COLONIA BARRIENTOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (552, 6, 'COLONIA BUEN RETIRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (553, 6, 'COLONIA CARLOS PELLEGRINI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (554, 6, 'COLONIA CAROLINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (555, 6, 'COLONIA CAROLINA ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (556, 6, 'COLONIA GARABI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (557, 6, 'COLONIA ISABEL VICTORIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (558, 6, 'COLONIA MADARIAGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (559, 6, 'COLONIA PANDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (560, 6, 'COLONIA PORVENIR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (561, 6, 'COLONIA PROGRESO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (562, 6, 'COLONIA PUCHETA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (563, 6, 'COLONIA ROMERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (564, 6, 'COLONIA SAN MARTIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (565, 6, 'COLONIA SARGENTO JUAN B. CABRAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (566, 6, 'COLONIA TATACUA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (567, 6, 'COLONIA YATAYTI CALLE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (568, 6, 'CONCEPCION', '0', 0);
INSERT INTO ubicacion_localidades VALUES (569, 6, 'CONI, EMILIO R.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (570, 6, 'CORONEL ABRAHAM SCHWEIZER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (571, 6, 'CORONEL DESIDERIO SOSA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (572, 6, 'CORRIENTES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (573, 6, 'COSTA GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (574, 6, 'CRUCESITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (575, 6, 'CRUZ DE LOS MILAGROS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (576, 6, 'CUAY GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (577, 6, 'CURUZU CUATIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (578, 6, 'DERQUI, MANUEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (579, 6, 'DESMOCHADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (580, 6, 'DIAZ COLODRERO,PEDRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (581, 6, 'DOCTOR FELIX MARIA GOMEZ ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (582, 6, 'EJERCITO ARGENTINO ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (583, 6, 'EL SOMBRERO ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (584, 6, 'EMPEDRADO LIMPIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (585, 6, 'ENSENADA GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (586, 6, 'ESQUINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (587, 6, 'FANEGAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (588, 6, 'FERNANDEZ, PEDRO R.,EST.M.F.MANSILLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (589, 6, 'GALARZA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (590, 6, 'GARRUCHOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (591, 6, 'GDOR.IGR.VALENTIN VIRASORO.GDOR.VIRASORO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (592, 6, 'GOBERNADOR JUAN E.MARTINEZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (593, 6, 'GOYA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (594, 6, 'GUAVIRAVI ,EST.25 DE FEBRERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (595, 6, 'GUAYQUIRARO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (596, 6, 'HERLITKZA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (597, 6, 'HORMIGUERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (598, 6, 'IBAHAY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (599, 6, 'IFRAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (600, 6, 'INGENIO PRIMER CORRENTINO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (601, 6, 'ISOQUI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (602, 6, 'ITA IBATE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (603, 6, 'ITATI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (604, 6, 'ITUZAINGO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (605, 6, 'KILOMETR 402 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (606, 6, 'KILOMETR0 167 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (607, 6, 'KILOMETR0 301 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (608, 6, 'KILOMETRO 104 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (609, 6, 'KILOMETRO 120 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (610, 6, 'KILOMETRO 161 ,PDA.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (611, 6, 'KILOMETRO 173 ,EMB', '0', 0);
INSERT INTO ubicacion_localidades VALUES (612, 6, 'KILOMETRO 182 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (613, 6, 'KILOMETRO 204 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (614, 6, 'KILOMETRO 374 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (615, 6, 'KILOMETRO 382 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (616, 6, 'KILOMETRO 387 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (617, 6, 'KILOMETRO 394 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (618, 6, 'KILOMETRO 396 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (619, 6, 'KILOMETRO 405 .AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (620, 6, 'KILOMETRO 406 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (621, 6, 'KILOMETRO 410 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (622, 6, 'KILOMETRO 416 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (623, 6, 'KILOMETRO 431 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (624, 6, 'KILOMETRO 442 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (625, 6, 'KILOMETRO 451 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (626, 6, 'KILOMETRO 459 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (627, 6, 'KILOMETRO 470 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (628, 6, 'KILOMETRO 476 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (629, 6, 'KILOMETRO 479 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (630, 6, 'KILOMETRO 485 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (631, 6, 'KILOMETRO 489 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (632, 6, 'KILOMETRO 492 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (633, 6, 'KILOMETRO 501 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (634, 6, 'KILOMETRO 504 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (635, 6, 'KILOMETRO 506 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (636, 6, 'KILOMETRO 512 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (637, 6, 'KILOMETRO 516 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (638, 6, 'KILOMETRO 517 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (639, 6, 'LA CASUALIDAD', '0', 0);
INSERT INTO ubicacion_localidades VALUES (640, 6, 'LA CRUZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (641, 6, 'LABOUGLE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (642, 6, 'LAGUNA BRAVA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (643, 6, 'LAS LOMAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (644, 6, 'LAS PALMITAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (645, 6, 'LAVALLE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (646, 6, 'LIBERTAD', '0', 0);
INSERT INTO ubicacion_localidades VALUES (647, 6, 'LIBERTAD ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (648, 6, 'LOMAS DE GALARZA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (649, 6, 'LOMAS DE VALLEJOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (650, 6, 'LORETO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (651, 6, 'LOZA, MARIANO I. ,EST.JUSTINO SOLARI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (652, 6, 'MALOYAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (653, 6, 'MALVINAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (654, 6, 'MANANTIALES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (655, 6, 'MBURUCUYA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (656, 6, 'MERCEDES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (657, 6, 'MINUANES ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (658, 6, 'MONTE CASEROS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (659, 6, 'MONTE GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (660, 6, 'MORA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (661, 6, 'MUCHAS ISLAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (662, 6, 'NARANJITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (663, 6, 'NUESTRA SEÃORA DEL ROSARIO DE CAA CATI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (664, 6, 'OMBUCITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (665, 6, 'PAGO LARGO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (666, 6, 'PAGO REDONDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (667, 6, 'PALMAR GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (668, 6, 'PANCHO CUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (669, 6, 'PARAJE SAN ISIDRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (670, 6, 'PASO DE LA PATRIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (671, 6, 'PASO DE LOS LIBRES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (672, 6, 'PASO FLORENTIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (673, 6, 'PASO ITU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (674, 6, 'PASO PESOA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (675, 6, 'PERUGORRIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (676, 6, 'PLAYADITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (677, 6, 'PUCHETA ,PDA.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (678, 6, 'PUEBLO LIBERTADOR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (679, 6, 'PUENTE BATEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (680, 6, 'PUERTO EMPEDRADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (681, 6, 'PUERTO GOYA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (682, 6, 'PUERTO LAVALLE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (683, 6, 'PUISOYE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (684, 6, 'PUJOL, JUAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (685, 6, 'PUNTA GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (686, 6, 'RAMADA PASO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (687, 6, 'RAMONES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (688, 6, 'RIACHUELO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (689, 6, 'SAENZ VALIENTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (690, 6, 'SALADAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (691, 6, 'SALDAÃA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (692, 6, 'SAN ANTONIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (693, 6, 'SAN CARLOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (694, 6, 'SAN COSME', '0', 0);
INSERT INTO ubicacion_localidades VALUES (695, 6, 'SAN GABRIEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (696, 6, 'SAN LORENZO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (697, 6, 'SAN LUIS DEL PALMAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (698, 6, 'SAN MIGUEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (699, 6, 'SAN ROQUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (700, 6, 'SAN SALVADOR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (701, 6, 'SANTA ANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (702, 6, 'SANTA LUCIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (703, 6, 'SANTA ROSA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (704, 6, 'SANTILLAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (705, 6, 'SANTO TOME', '0', 0);
INSERT INTO ubicacion_localidades VALUES (706, 6, 'SAUCE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (707, 6, 'TABAY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (708, 6, 'TACUARAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (709, 6, 'TACUARITAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (710, 6, 'TAPEBICUA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (711, 6, 'TIMBOCITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (712, 6, 'TORRENT', '0', 0);
INSERT INTO ubicacion_localidades VALUES (713, 6, 'UGUAY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (714, 6, 'VACA CUA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (715, 6, 'VILLA CORDOBA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (716, 6, 'YAHAPE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (717, 6, 'YAPEYU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (718, 6, 'YAPEYU ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (719, 6, 'YOFRE, FELIPE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (720, 6, 'YUQUERI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (721, 9, '16 DE JULIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (722, 9, '17 DE AGOSTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (723, 9, '20 DE JUNIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (724, 9, '20 DE JUNIO ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (725, 9, '25 DE MAYO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (726, 9, '30 DE AGOSTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (727, 9, '9 DE JULIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (728, 9, 'A.A.FERNANDEZ ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (729, 9, 'A.F.ORMA ,PDA.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (730, 9, 'ABASTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (731, 9, 'ABBOT', '0', 0);
INSERT INTO ubicacion_localidades VALUES (732, 9, 'ABEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (733, 9, 'ACASSUSO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (734, 9, 'ACEVEDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (735, 9, 'ACHUPALLAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (736, 9, 'ADELA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (737, 9, 'AEROPUERTO EZEIZA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (738, 9, 'AGOTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (739, 9, 'AGUARA ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (740, 9, 'AGUSTIN FERRARI ,PDA.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (741, 9, 'AGUSTIN ROCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (742, 9, 'AGUSTINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (743, 9, 'ALAGON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (744, 9, 'ALAMOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (745, 9, 'ALASTUEY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (746, 9, 'ALBARIÃO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (747, 9, 'ALBERDI VIEJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (748, 9, 'ALBERTI ,EST. ANDRES VACCAREZZA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (749, 9, 'ALDEA ROMANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (750, 9, 'ALDO BONZI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (751, 9, 'ALEGRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (752, 9, 'ALEJANDRO KORN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (753, 9, 'ALEJANDRO PETION ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (754, 9, 'ALFEREZ SAN MARTIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (755, 9, 'ALFREDO DEMARCHI ,EST. QUIROGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (756, 9, 'ALMIRANTE BROWN ,EST. ADROGUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (757, 9, 'ALMIRANTE SOLIER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (758, 9, 'ALSINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (759, 9, 'ALTA VISTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (760, 9, 'ALTAMIRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (761, 9, 'ALTAMIRANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (762, 9, 'ALTIMPERGHER ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (763, 9, 'ALTONA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (764, 9, 'ALVAREZ DE TOLEDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (765, 9, 'ALVAREZ JONTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (766, 9, 'ALZAGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (767, 9, 'AMALIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (768, 9, 'AMEGHINO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (769, 9, 'ANASAGASTI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (770, 9, 'ANCON ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (771, 9, 'ANDAT', '0', 0);
INSERT INTO ubicacion_localidades VALUES (772, 9, 'ANDERSON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (773, 9, 'ANGEL ECHEVERRY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (774, 9, 'ANTONIO CARBONI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (775, 9, 'APARICIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (776, 9, 'ARANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (777, 9, 'ARANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (778, 9, 'ARAUJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (779, 9, 'ARBOLEDAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (780, 9, 'ARBOLITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (781, 9, 'ARENALES ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (782, 9, 'ARENAZA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (783, 9, 'ARGERICH', '0', 0);
INSERT INTO ubicacion_localidades VALUES (784, 9, 'ARIEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (785, 9, 'ARISTOBULO DEL VALLE ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (786, 9, 'ARRECIFES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (787, 9, 'ARRIBEÃOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (788, 9, 'ARROYO CORTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (789, 9, 'ARROYO DE LA CRUZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (790, 9, 'ARROYO DE LOS HUESOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (791, 9, 'ARROYO DEL MEDIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (792, 9, 'ARROYO DULCE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (793, 9, 'ARROYO VENADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (794, 9, 'ARTURO SEGUI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (795, 9, 'ARTURO VATTEONE ,EST', '0', 0);
INSERT INTO ubicacion_localidades VALUES (796, 9, 'ASAMBLEA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (797, 9, 'ASCENSION', '0', 0);
INSERT INTO ubicacion_localidades VALUES (798, 9, 'ASTURIAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (799, 9, 'ATALAYA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (800, 9, 'ATILIO PESSAGNO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (801, 9, 'ATUCHA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (802, 9, 'AVELLANEDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (803, 9, 'AVESTRUZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (804, 9, 'AYACUCHO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (805, 9, 'AZCUENAGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (806, 9, 'AZOPARDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (807, 9, 'AZUCENA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (808, 9, 'AZUL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (809, 9, 'BACACAY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (810, 9, 'BACCAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (811, 9, 'BADANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (812, 9, 'BAHIA BLANCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (813, 9, 'BAHIA SAN BLAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (814, 9, 'BAIGORRITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (815, 9, 'BAJO HONDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (816, 9, 'BALCARCE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (817, 9, 'BALNEARIO ORENSE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (818, 9, 'BALNEARIO ORIENTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (819, 9, 'BALNEARIO PEHUEN-CO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (820, 9, 'BALSA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (821, 9, 'BANCALARI ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (822, 9, 'BANDERALO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (823, 9, 'BANFIELD', '0', 0);
INSERT INTO ubicacion_localidades VALUES (824, 9, 'BARADERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (825, 9, 'BARISSO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (826, 9, 'BARKER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (827, 9, 'BARRIO COLONIA ALEGRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (828, 9, 'BARRIO EL RETAZO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (829, 9, 'BARRIO SAN PATRICIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (830, 9, 'BARROW', '0', 0);
INSERT INTO ubicacion_localidades VALUES (831, 9, 'BARTOLOME BAVIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (832, 9, 'BARTOLOME MITRE ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (833, 9, 'BATAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (834, 9, 'BATHURST', '0', 0);
INSERT INTO ubicacion_localidades VALUES (835, 9, 'BAUDRIX', '0', 0);
INSERT INTO ubicacion_localidades VALUES (836, 9, 'BAYAUCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (837, 9, 'BELEN DE ESCOBAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (838, 9, 'BELLA VISTA ,EST.TTE.GRL.RICCHIERI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (839, 9, 'BELLOCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (840, 9, 'BENAVIDEZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (841, 9, 'BENITEZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (842, 9, 'BERAZATEGUI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (843, 9, 'BERDIER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (844, 9, 'BERMUDEZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (845, 9, 'BERNAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (846, 9, 'BERRAONDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (847, 9, 'BERUTTI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (848, 9, 'BILLINGHURST', '0', 0);
INSERT INTO ubicacion_localidades VALUES (849, 9, 'BLANCA GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (850, 9, 'BLANDENGUES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (851, 9, 'BLAQUIER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (852, 9, 'BLAS DURAÃONA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (853, 9, 'BOCA DEL TORO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (854, 9, 'BOCAYUVA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (855, 9, 'BONNEMENT ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (856, 9, 'BORDENAVE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (857, 9, 'BORDEU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (858, 9, 'BORGES ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (859, 9, 'BOSCH', '0', 0);
INSERT INTO ubicacion_localidades VALUES (860, 9, 'BOSQUES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (861, 9, 'BOULOGNE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (862, 9, 'BRAGADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (863, 9, 'BRANDSEN ,EST.CNL.BRANDSEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (864, 9, 'BUCHANAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (865, 9, 'BURZACO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (866, 9, 'CABILDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (867, 9, 'CACHARI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (868, 9, 'CADRET', '0', 0);
INSERT INTO ubicacion_localidades VALUES (869, 9, 'CAIOMUTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (870, 9, 'CALDERON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (871, 9, 'CALFUCURA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (872, 9, 'CALVO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (873, 9, 'CAMBACERES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (874, 9, 'CAMET', '0', 0);
INSERT INTO ubicacion_localidades VALUES (875, 9, 'CAMPANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (876, 9, 'CAMPO DE MAYO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (877, 9, 'CAMPODONICO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (878, 9, 'CAN, ROBERTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (879, 9, 'CAÃADA MARIANO ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (880, 9, 'CAÃADA SECA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (881, 9, 'CANAL SAN FERNANDO ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (882, 9, 'CANGALLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (883, 9, 'CANNING', '0', 0);
INSERT INTO ubicacion_localidades VALUES (884, 9, 'CANONIGO GORRITI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (885, 9, 'CAÃUELAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (886, 9, 'CAPILLA DEL SEÃOR ,EST.CAPILLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (887, 9, 'CAPITAN CASTRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (888, 9, 'CAPITAN SARMIENTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (889, 9, 'CARABELAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (890, 9, 'CARAPACHAY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (891, 9, 'CARBONI, ANTONIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (892, 9, 'CARDENAL CAGLIERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (893, 9, 'CARHUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (894, 9, 'CARMEN DE ARECO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (895, 9, 'CASALINS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (896, 9, 'CASANOVA, ISIDRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (897, 9, 'CASARES, CARLOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (898, 9, 'CASARES, VICENTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (899, 9, 'CASAS, JOSE B.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (900, 9, 'CASBAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (901, 9, 'CASCADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (902, 9, 'CASCALLARES, MICAELA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (903, 9, 'CASEROS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (904, 9, 'CASEY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (905, 9, 'CASTELAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (906, 9, 'CASTELLI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (907, 9, 'CASTILLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (908, 9, 'CASTILLO, RAFAEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (909, 9, 'CAZON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (910, 9, 'CENTRO AGRICOLA EL PATO ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (911, 9, 'CERRITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (912, 9, 'CHACABUCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (913, 9, 'CHANCAY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (914, 9, 'CHAPADMALAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (915, 9, 'CHAPALEUFU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (916, 9, 'CHAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (917, 9, 'CHASCOMUS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (918, 9, 'CHASICO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (919, 9, 'CHENAUT', '0', 0);
INSERT INTO ubicacion_localidades VALUES (920, 9, 'CHICLANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (921, 9, 'CHILLAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (922, 9, 'CHIVILCOY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (923, 9, 'CHOIQUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (924, 9, 'CIRCUNVALACION ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (925, 9, 'CITY BELL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (926, 9, 'CIUDAD GRL.MARTIN MIGUEL DE GUEMES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (927, 9, 'CIUDADELA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (928, 9, 'CLARAZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (929, 9, 'CLAROMECO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (930, 9, 'CLAYPOLE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (931, 9, 'COBO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (932, 9, 'COBO, MANUEL J.,EST.LEZAMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (933, 9, 'COCHRANE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (934, 9, 'COLIQUEO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (935, 9, 'COLMAN, MARTIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (936, 9, 'COLON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (937, 9, 'COLONIA HOGAR RICARDO GUTIERREZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (938, 9, 'COLONIA SAN MIGUEL ARCANGEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (939, 9, 'COLONIA SERE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (940, 9, 'COLONIA VELAZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (941, 9, 'COMANDANTE GIRIBONE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (942, 9, 'COMANDANTE NICANOR OTAMENDI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (943, 9, 'COMODORO PY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (944, 9, 'CONESA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (945, 9, 'COPETONAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (946, 9, 'CORACEROS ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (947, 9, 'CORAZZI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (948, 9, 'CORBETT', '0', 0);
INSERT INTO ubicacion_localidades VALUES (949, 9, 'CORONADO, MARTIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (950, 9, 'CORONEL BOERR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (951, 9, 'CORONEL BRANDSEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (952, 9, 'CORONEL CHARLONE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (953, 9, 'CORONEL DORREGO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (954, 9, 'CORONEL FALCON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (955, 9, 'CORONEL GRANADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (956, 9, 'CORONEL MARCELINO FRYRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (957, 9, 'CORONEL MARTINEZ DE HOZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (958, 9, 'CORONEL MARTINIANO CHILAVERT ,PDA.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (959, 9, 'CORONEL MOM', '0', 0);
INSERT INTO ubicacion_localidades VALUES (960, 9, 'CORONEL PRINGLES ,EST.PRINGLES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (961, 9, 'CORONEL RODOLFO BUNGE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (962, 9, 'CORONEL SUAREZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (963, 9, 'CORONEL VIDAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (964, 9, 'CORREAS, IGNACIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (965, 9, 'CORTI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (966, 9, 'COSTA, EDUARDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (967, 9, 'COVELLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (968, 9, 'CRISTIANO MUERTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (969, 9, 'CROTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (970, 9, 'CURA MALAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (971, 9, 'CURARU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (972, 9, 'D ORBIGNY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (973, 9, 'DAIREAUX', '0', 0);
INSERT INTO ubicacion_localidades VALUES (974, 9, 'DARREGUEIRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (975, 9, 'DE BARY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (976, 9, 'DE ELIA, AGUSTIN ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (977, 9, 'DE LA CANAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (978, 9, 'DE LA GARMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (979, 9, 'DE LA PLAZA, VICTORINO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (980, 9, 'DE LA RIESTRA, NORBERTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (981, 9, 'DE LUCA, ESTEBAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (982, 9, 'DE VICTORIA, FRANCISCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (983, 9, 'DEFFERRARI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (984, 9, 'DEL CARRIL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (985, 9, 'DEL VALLE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (986, 9, 'DEL VALLE,ARISTOBULO ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (987, 9, 'DEL VISO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (988, 9, 'DELTA ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (989, 9, 'DENNEHY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (990, 9, 'DESCALZI, NICOLAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (991, 9, 'DIQUE LUJAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (992, 9, 'DOCTOR DOMINGO HAROSTEGUY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (993, 9, 'DOCTOR RICARDO LEVENE ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (994, 9, 'DOLORES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (995, 9, 'DOMSELAAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (996, 9, 'DON BOSCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (997, 9, 'DON CIPRIANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (998, 9, 'DON TORCUATO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (999, 9, 'DOS HERMANOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1000, 9, 'DOYLE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1001, 9, 'DRABBLE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1002, 9, 'DRYSDALE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1003, 9, 'DUCOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1004, 9, 'DUDIGNAC', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1005, 9, 'DUFAUR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1006, 9, 'DUGGAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1007, 9, 'DUHAU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1008, 9, 'DURAÃONA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1009, 9, 'DUSSAUD ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1010, 9, 'EGAÃA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1011, 9, 'EL ARBOLITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1012, 9, 'EL DIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1013, 9, 'EL DIVISORIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1014, 9, 'EL DORADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1015, 9, 'EL LENGUARAZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1016, 9, 'EL MORO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1017, 9, 'EL PALOMAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1018, 9, 'EL PARAISO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1019, 9, 'EL PENSAMIENTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1020, 9, 'EL PEREGRINO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1021, 9, 'EL TALAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1022, 9, 'EL TEJAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1023, 9, 'EL TRIGO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1024, 9, 'EL TRIUNFO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1025, 9, 'EL ZORRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1026, 9, 'ELIZALDE, RUFINO DE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1027, 9, 'ELORDI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1028, 9, 'ELVIRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1029, 9, 'EMMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1030, 9, 'ENCINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1031, 9, 'ENERGIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1032, 9, 'ENSENADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1033, 9, 'EPUMER ,EST.YUTUYACO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1034, 9, 'ESCALADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1035, 9, 'ESCALADA, REMEDIOS DE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1036, 9, 'ESCOBAR, BELEN DE ,EST.ESCOBAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1037, 9, 'ESCRIBANO, PEDRO N.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1038, 9, 'ESPARTILLAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1039, 9, 'ESPIGAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1040, 9, 'ESPORA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1041, 9, 'ESQUINA NEGRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1042, 9, 'ESTELA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1043, 9, 'ESTHER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1044, 9, 'ESTOMBA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1045, 9, 'ESTRUGAMOU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1046, 9, 'ETCHEVERRY, ANGEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1047, 9, 'EZEIZA, JOSE MARIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1048, 9, 'EZPELETA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1049, 9, 'FAIR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1050, 9, 'FARO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1051, 9, 'FAUZON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1052, 9, 'FERNANDEZ, A.A.,PDA.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1053, 9, 'FERNANDEZ, JUAN N.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1054, 9, 'FERRARI, AGUSTIN ,PDA.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1055, 9, 'FERRARI, JOSE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1056, 9, 'FERRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1057, 9, 'FIORITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1058, 9, 'FLORENCIO VARELA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1059, 9, 'FLORIDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1060, 9, 'FLORIDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1061, 9, 'FORTABAT, ALFREDO ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1062, 9, 'FORTIN ACHA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1063, 9, 'FORTIN OLAVARRIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1064, 9, 'FORTIN TIBURCIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1065, 9, 'FORTIN VIGILANCIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1066, 9, 'FRENCH', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1067, 9, 'FULTON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1068, 9, 'GAHAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1069, 9, 'GAMBIER ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1070, 9, 'GAMEN, PEDRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1071, 9, 'GANDARA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1072, 9, 'GARCIA DEL RIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1073, 9, 'GARCIA, MANUEL J.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1074, 9, 'GARDEY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1075, 9, 'GARIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1076, 9, 'GARRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1077, 9, 'GASCON, ESTEBAN AGUSTIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1078, 9, 'GENERAL ALVEAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1079, 9, 'GENERAL ARENALES ,EST.ARENALES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1080, 9, 'GENERAL BELGRANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1081, 9, 'GENERAL CONESA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1082, 9, 'GENERAL DANIEL CERRI ,EST.GRL.CERRI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1083, 9, 'GENERAL GUIDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1084, 9, 'GENERAL HORNOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1085, 9, 'GENERAL JUAN MADARIAGA ,EST.G.MADARIAGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1086, 9, 'GENERAL LA MADRID', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1087, 9, 'GENERAL LAS HERAS ,EST.LAS HERAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1088, 9, 'GENERAL LAVALLE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1089, 9, 'GENERAL MANSILLA ,EST.BARTOLOME BAVIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1090, 9, 'GENERAL OBRIEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1091, 9, 'GENERAL PACHECO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1092, 9, 'GENERAL PINTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1093, 9, 'GENERAL PIRAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1094, 9, 'GENERAL RODRIGUEZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1095, 9, 'GENERAL ROJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1096, 9, 'GENERAL RONDEAU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1097, 9, 'GENERAL SAN MARTIN ,EST.SAN MARTIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1098, 9, 'GENERAL VIAMONTE ,EST.LOS TOLDOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1099, 9, 'GENERAL VILLEGAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1100, 9, 'GERLI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1101, 9, 'GERMANIA ,EST.MAYOR ORELLANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1102, 9, 'GIL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1103, 9, 'GIRODIAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1104, 9, 'GIRONDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1105, 9, 'GLEW', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1106, 9, 'GNECCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1107, 9, 'GOBERNADOR ARIAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1108, 9, 'GOBERNADOR CASTRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1109, 9, 'GOBERNADOR EDUARDO ARANA ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1110, 9, 'GOBERNADOR LUIS GARCIA ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1111, 9, 'GOBERNADOR MONTEVERDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1112, 9, 'GOBERNADOR UDAONDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1113, 9, 'GOBERNADOR UGARTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1114, 9, 'GOMEZ, VALENTIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1115, 9, 'GONDRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1116, 9, 'GONNET, MANUEL B.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1117, 9, 'GONZALEZ CATAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1118, 9, 'GONZALEZ CHAVES, ADOLFO ,EST.G.CHAVES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1119, 9, 'GONZALEZ MORENO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1120, 9, 'GORCHS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1121, 9, 'GORINA, JOAQUIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1122, 9, 'GOROSTIAGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1123, 9, 'GOUIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1124, 9, 'GOYENA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1125, 9, 'GOYENECHE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1126, 9, 'GRACIARENA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1127, 9, 'GRAND BOURG', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1128, 9, 'GRAVIÃA, RICARDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1129, 9, 'GRUNBEIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1130, 9, 'GRUNBEIN ,EST.EMP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1131, 9, 'GUAMINI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1132, 9, 'GUANACO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1133, 9, 'GUERNICA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1134, 9, 'GUERRERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1135, 9, 'GUERRICO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1136, 9, 'GUILLON, LUIS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1137, 9, 'GUISASOLA,JOSE A.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1138, 9, 'GUNTHER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1139, 9, 'GUTIERREZ, JUAN M.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1140, 9, 'HAEDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1141, 9, 'HALE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1142, 9, 'HEAVY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1143, 9, 'HENDERSON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1144, 9, 'HEREFORD', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1145, 9, 'HERNANDEZ, JOSE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1146, 9, 'HERRERA VEGAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1147, 9, 'HINOJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1148, 9, 'HIRSCH, MAURICIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1149, 9, 'HORTENSIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1150, 9, 'HUANGUELEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1151, 9, 'HUDSON, GUILLERMO E.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1152, 9, 'HUERGO,DELFIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1153, 9, 'HUETEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1154, 9, 'HUNTER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1155, 9, 'HURLINGHAM', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1156, 9, 'HUSARES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1157, 9, 'IBAÃEZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1158, 9, 'IBARRA,JUAN F.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1159, 9, 'IGARZABAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1160, 9, 'INDART, INES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1161, 9, 'INDIO RICO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1162, 9, 'INGENIERO ANDRES VILLANUEVA ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1163, 9, 'INGENIERO BALBIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1164, 9, 'INGENIERO BEAUGEY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1165, 9, 'INGENIERO BUNGE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1166, 9, 'INGENIERO JUAN ALLAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1167, 9, 'INGENIERO MASCHWITZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1168, 9, 'INGENIERO MONETA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1169, 9, 'INGENIERO PABLO NOGUES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1170, 9, 'INGENIERO SANTIAGO BRIAN ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1171, 9, 'INGENIERO THOMPSON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1172, 9, 'INGENIERO WIHTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1173, 9, 'INGENIERO WILLIAMS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1174, 9, 'INVERNADAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1175, 9, 'IRALA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1176, 9, 'IRAOLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1177, 9, 'IRENE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1178, 9, 'IRIARTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1179, 9, 'ISLAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1180, 9, 'ITURREGUI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1181, 9, 'ITUZAINGO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1182, 9, 'JEPPENER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1183, 9, 'JOFRE, TOMAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1184, 9, 'JUANCHO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1185, 9, 'JUAREZ, BENITO ,EST.JUAREZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1186, 9, 'JUNIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1187, 9, 'JUSTO VILLEGAS ,PDA.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1188, 9, 'JUSTO, JUAN B. ,PDA.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1189, 9, 'KILOMERO 8 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1190, 9, 'KILOMETRO 12 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1191, 9, 'KILOMETRO 17 ,PDA.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1192, 9, 'KILOMETRO 19 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1193, 9, 'KILOMETRO 27 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1194, 9, 'KILOMETRO 34 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1195, 9, 'KILOMETRO 36 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1196, 9, 'KILOMETRO 40 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1197, 9, 'KILOMETRO 40 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1198, 9, 'KILOMETRO 45 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1199, 9, 'KILOMETRO 48 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1200, 9, 'KILOMETRO 53 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1201, 9, 'KILOMETRO 54 ,PDA.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1202, 9, 'KILOMETRO 55 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1203, 9, 'KILOMETRO 641 ,PDA.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1204, 9, 'KILOMETRO 9 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1205, 9, 'KORN, ALEJANDRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1206, 9, 'KRABBE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1207, 9, 'LA ANGELITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1208, 9, 'LA BARRANCOSA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1209, 9, 'LA BEBA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1210, 9, 'LA CARRETA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1211, 9, 'LA COLINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1212, 9, 'LA CONSTANCIA ,AP.KILOMETRO 360', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1213, 9, 'LA COPETA ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1214, 9, 'LA CUMBRE ,PDA.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1215, 9, 'LA DELFINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1216, 9, 'LA DORITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1217, 9, 'LA EMILIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1218, 9, 'LA INVENCIBLE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1219, 9, 'LA LARGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1220, 9, 'LA LIMPIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1221, 9, 'LA LUCILA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1222, 9, 'LA LUCILA DEL MAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1223, 9, 'LA LUISA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1224, 9, 'LA MANUELA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1225, 9, 'LA MASCOTA ,EST.MASCOTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1226, 9, 'LA NEGRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1227, 9, 'LA NEVADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1228, 9, 'LA NIÃA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1229, 9, 'LA NORIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1230, 9, 'LA ORIENTAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1231, 9, 'LA PALA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1232, 9, 'LA PASTORA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1233, 9, 'LA PLATA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1234, 9, 'LA PORTEÃA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1235, 9, 'LA PRIMAVERA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1236, 9, 'LA PROVIDENCIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1237, 9, 'LA REFORMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1238, 9, 'LA REJA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1239, 9, 'LA RICA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1240, 9, 'LA SALADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1241, 9, 'LA SOFIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1242, 9, 'LA SORTIJA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1243, 9, 'LA VIOLETA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1244, 9, 'LA VITICOLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1245, 9, 'LA ZANJA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1246, 9, 'LABARDEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1247, 9, 'LAFERRERE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1248, 9, 'LAGO EPECUEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1249, 9, 'LAGUNA ALSINA ,EST.BONIFACIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1250, 9, 'LAMARCA, EMILIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1251, 9, 'LANGUEYU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1252, 9, 'LANUS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1253, 9, 'LAPLACETE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1254, 9, 'LAPRIDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1255, 9, 'LARRE, SANTIAGO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1256, 9, 'LARTIGAU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1257, 9, 'LAS ARMAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1258, 9, 'LAS BARRANCAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1259, 9, 'LAS CHACRAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1260, 9, 'LAS FLORES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1261, 9, 'LAS HERMANAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1262, 9, 'LAS MALVINAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1263, 9, 'LAS MARIANAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1264, 9, 'LAS NUTRIAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1265, 9, 'LAS PALMAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1266, 9, 'LAS PARVAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1267, 9, 'LAS TOSCAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1268, 9, 'LAS VAQUERIAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1269, 9, 'LASALLE, PEDRO P.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1270, 9, 'LASTRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1271, 9, 'LAVALLE, ENRIQUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1272, 9, 'LAVALLOL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1273, 9, 'LAZZARINO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1274, 9, 'LERTORA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1275, 9, 'LEUBUCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1276, 9, 'LEVALLE, NICOLAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1277, 9, 'LIBANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1278, 9, 'LIBERTAD', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1279, 9, 'LIBRES DEL SUD', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1280, 9, 'LICENCIADO MATIENZO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1281, 9, 'LIMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1282, 9, 'LIN CALEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1283, 9, 'LINCOLN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1284, 9, 'LLORENTE, GALO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1285, 9, 'LOBERIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1286, 9, 'LOBOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1287, 9, 'LOMA NEGRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1288, 9, 'LOMA PARAGUAYA ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1289, 9, 'LOMA VERDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1290, 9, 'LOMAS DE ZAMORA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1291, 9, 'LONGCHAMPS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1292, 9, 'LOPEZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1293, 9, 'LOPEZ CAMELO ,PDA.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1294, 9, 'LOPEZ LECUBE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1295, 9, 'LOPEZ, VICENTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1296, 9, 'LOS ANGELES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1297, 9, 'LOS GAUCHOS ,PDA.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1298, 9, 'LOS INDIOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1299, 9, 'LOS PINOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1300, 9, 'LOS POLVORINES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1301, 9, 'LOUGE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1302, 9, 'LOURDES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1303, 9, 'LUJAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1304, 9, 'LUMB', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1305, 9, 'LURO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1306, 9, 'LURO, PEDRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1307, 9, 'MACEDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1308, 9, 'MACHITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1309, 9, 'MADERO, FRANCISCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1310, 9, 'MAGDALA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1311, 9, 'MAGDALENA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1312, 9, 'MAGNANO, FRANCISCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1313, 9, 'MAGUIRRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1314, 9, 'MAIPU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1315, 9, 'MALAVER ,PDA.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1316, 9, 'MAMAGUITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1317, 9, 'MAPIS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1318, 9, 'MAQUINISTA SAVIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1319, 9, 'MAR DE AJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1320, 9, 'MAR DEL PLATA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1321, 9, 'MAR DEL TUYU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1322, 9, 'MARI LAUQUEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1323, 9, 'MARIA IGNACIA ,EST.VEIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1324, 9, 'MARIA LUCILA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1325, 9, 'MARMOL, JOSE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1326, 9, 'MARTIN FIERRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1327, 9, 'MARTINEZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1328, 9, 'MARUCHA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1329, 9, 'MASUREL ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1330, 9, 'MATHEU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1331, 9, 'MAYOR BURATOVICH', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1332, 9, 'MAZA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1333, 9, 'MECHONGUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1334, 9, 'MEDANOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1335, 9, 'MEMBRILLAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1336, 9, 'MERCEDES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1337, 9, 'MERLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1338, 9, 'MERLO GOMEZ ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1339, 9, 'MICHEO, JOSE M.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1340, 9, 'MIGUELETE ,PDA.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1341, 9, 'MINISTRO RIVADAVIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1342, 9, 'MIRA PAMPA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1343, 9, 'MIRAMAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1344, 9, 'MIRAMONTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1345, 9, 'MIRANDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1346, 9, 'MITRE, BARTOLOME ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1347, 9, 'MOCTEZUMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1348, 9, 'MOLINA, CLAUDIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1349, 9, 'MONASTERIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1350, 9, 'MONES CAZON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1351, 9, 'MONROE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1352, 9, 'MONSALVO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1353, 9, 'MONTE CHINGOLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1354, 9, 'MONTE GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1355, 9, 'MONTE HERMOSO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1356, 9, 'MONTE VELOZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1357, 9, 'MONTES DE OCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1358, 9, 'MOORES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1359, 9, 'MOQUEHUA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1360, 9, 'MORENO, MARIANO ,EST.MORENO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1361, 9, 'MORON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1362, 9, 'MORRIS, WILIAM C.,PDA.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1363, 9, 'MORSE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1364, 9, 'MOSCONI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1365, 9, 'MOURAS ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1366, 9, 'MUÃIZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1367, 9, 'MUÃOZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1368, 9, 'MUNRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1369, 9, 'MURATURE, FRANCISCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1370, 9, 'NAHUEL RUCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1371, 9, 'NAON, CARLOS M.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1372, 9, 'NAPALEOFU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1373, 9, 'NAPOSTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1374, 9, 'NAVARRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1375, 9, 'NECOCHEA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1376, 9, 'NECOL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1377, 9, 'NEILD, RAMON J.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1378, 9, 'NEWTON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1379, 9, 'NIEVES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1380, 9, 'NORUMBEGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1381, 9, 'NUEVA PLATA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1382, 9, 'NUEVA ROMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1383, 9, 'NUEVA SUIZA ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1384, 9, 'OBLIGADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1385, 9, 'OBLIGADO, RAFAEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1386, 9, 'OCAMPO, MANUEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1387, 9, 'OCHANDIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1388, 9, 'OHIGGINS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1389, 9, 'OLASCOAGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1390, 9, 'OLAVARRIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1391, 9, 'OLIDEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1392, 9, 'OLIVERA, NICANOR ,EST.LA DULCE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1393, 9, 'OLIVOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1394, 9, 'OLMOS, LISANDRO ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1395, 9, 'OMBU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1396, 9, 'OMBUCTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1397, 9, 'ORDOQUI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1398, 9, 'ORENSE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1399, 9, 'ORIENTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1400, 9, 'ORMA, ADOLFO F. ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1401, 9, 'ORTIZ BASUALDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1402, 9, 'ORTIZ DE ROSAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1403, 9, 'OSTENDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1404, 9, 'OTOÃO ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1405, 9, 'PADILLA, MIGUEL MANUEL ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1406, 9, 'PARAGUIL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1407, 9, 'PARDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1408, 9, 'PARISH', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1409, 9, 'PARRAVICINI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1410, 9, 'PASCO ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1411, 9, 'PASMAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1412, 9, 'PASO DEL REY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1413, 9, 'PASO MAYOR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1414, 9, 'PASO, JUAN JOSE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1415, 9, 'PASTEUR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1416, 9, 'PATAGONES, CARMEN DE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1417, 9, 'PATRICIOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1418, 9, 'PAYRO, ROBERTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1419, 9, 'PAZ, JOSE C.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1420, 9, 'PAZ, MARCOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1421, 9, 'PAZ, MAXIMO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1422, 9, 'PAZOS KANKI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1423, 9, 'PEARSON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1424, 9, 'PEDERNALES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1425, 9, 'PEHUAJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1426, 9, 'PEHUELCHES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1427, 9, 'PEHUEN-CO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1428, 9, 'PELICURA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1429, 9, 'PELLEGRINI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1430, 9, 'PERALTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1431, 9, 'PEREDA, VICENTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1432, 9, 'PEREZ MILLAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1433, 9, 'PEREZ, ROQUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1434, 9, 'PERGAMINO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1435, 9, 'PESSAGNO, ATILIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1436, 9, 'PETION, ALEJANDRO ,PDA.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1437, 9, 'PICHINCHA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1438, 9, 'PIEDRA ECHADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1439, 9, 'PIEDRITAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1440, 9, 'PIERES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1441, 9, 'PIGUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1442, 9, 'PILA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1443, 9, 'PILAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1444, 9, 'PILLAHUINCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1445, 9, 'PINAMAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1446, 9, 'PIÃERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1447, 9, 'PIÃEYRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1448, 9, 'PIPINAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1449, 9, 'PIROVANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1450, 9, 'PLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1451, 9, 'PLATANOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1452, 9, 'PLAZA MONTERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1453, 9, 'POBLET', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1454, 9, 'POLVAREDAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1455, 9, 'PONTAUT', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1456, 9, 'PONTEVEDRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1457, 9, 'PORTELA, IRINEO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1458, 9, 'PORVENIR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1459, 9, 'POURTALE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1460, 9, 'PRADERE, JUAN A.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1461, 9, 'PRESIDENTE QUINTANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1462, 9, 'PRIMERA JUNTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1463, 9, 'PUAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1464, 9, 'PUEBLITOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1465, 9, 'PUENTE CASCALLARES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1466, 9, 'PUERTO BELGRANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1467, 9, 'PUERTO CUATREROS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1468, 9, 'PUERTO GALVAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1469, 9, 'PUERTO ROSALES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1470, 9, 'PUNTA ALTA ,EST.ALMIRANTE SOLIER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1471, 9, 'PUNTA CHICA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1472, 9, 'PUNTA INDIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1473, 9, 'PUNTA LARA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1474, 9, 'QUENUMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1475, 9, 'QUEQUEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1476, 9, 'QUERANDI ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1477, 9, 'QUERANDIES ,EMP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1478, 9, 'QUILCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1479, 9, 'QUILMES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1480, 9, 'QUIÃIHUAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1481, 9, 'QUIRNO COSTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1482, 9, 'QUIROGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1483, 9, 'RAMALLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1484, 9, 'RAMOS MEJIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1485, 9, 'RAMOS OTERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1486, 9, 'RANCAGUA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1487, 9, 'RANCHOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1488, 9, 'RANELAGH', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1489, 9, 'RAUCH', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1490, 9, 'RAULET', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1491, 9, 'RAWSON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1492, 9, 'REAL AUDIENCIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1493, 9, 'RECALDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1494, 9, 'RESERVA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1495, 9, 'RETA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1496, 9, 'RINGUELET', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1497, 9, 'RIO LUJAN ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1498, 9, 'RIO SALADO ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1499, 9, 'RIO TALA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1500, 9, 'RIVADAVIA ,EST.AMERICA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1501, 9, 'RIVADEO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1502, 9, 'RIVAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1503, 9, 'RIVERA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1504, 9, 'ROBERTS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1505, 9, 'ROCA, AGUSTIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1506, 9, 'ROCHA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1507, 9, 'ROJAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1508, 9, 'ROLDAN, MARIANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1509, 9, 'ROLITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1510, 9, 'ROMERO, ELIAS ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1511, 9, 'ROMERO, MELCHOR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1512, 9, 'ROOSEVELT', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1513, 9, 'ROSAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1514, 9, 'RUBEN DARIO ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1515, 9, 'RUIZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1516, 9, 'SAAVEDRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1517, 9, 'SAENZ PEÃA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1518, 9, 'SALADILLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1519, 9, 'SALAS, CARLOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1520, 9, 'SALAZAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1521, 9, 'SALDUNGARAY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1522, 9, 'SALINERA ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1523, 9, 'SALLIQUELO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1524, 9, 'SALTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1525, 9, 'SALVADOR MARIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1526, 9, 'SAN AGUSTIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1527, 9, 'SAN ANDRES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1528, 9, 'SAN ANDRES DE GILES ,EST.GILES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1529, 9, 'SAN ANTONIO DE ARECO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1530, 9, 'SAN ANTONIO DE PADUA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1531, 9, 'SAN BERNARDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1532, 9, 'SAN BERNARDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1533, 9, 'SAN CARLOS DE BOLIVAR ,EST.BOLIVAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1534, 9, 'SAN CAYETANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1535, 9, 'SAN CLEMENTE DEL TUYU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1536, 9, 'SAN ELADIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1537, 9, 'SAN EMILIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1538, 9, 'SAN ENRIQUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1539, 9, 'SAN FERMIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1540, 9, 'SAN FERNANDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1541, 9, 'SAN FRANCISCO DE BELLOCQ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1542, 9, 'SAN FRANCISCO SOLANO ,PDA.KILOMETRO 26', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1543, 9, 'SAN GERMAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1544, 9, 'SAN IGNACIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1545, 9, 'SAN ISIDRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1546, 9, 'SAN JORGE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1547, 9, 'SAN JOSE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1548, 9, 'SAN JOSE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1549, 9, 'SAN JUSTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1550, 9, 'SAN MANUEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1551, 9, 'SAN MAURICIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1552, 9, 'SAN MAYOL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1553, 9, 'SAN MIGUEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1554, 9, 'SAN MIGUEL ,EST.GENERAL SARMIENTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1555, 9, 'SAN MIGUEL DEL MONTE ,EST.MONTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1556, 9, 'SAN NICOLAS DE LOS ARROYOS,EST.S.NICOLAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1557, 9, 'SAN PATRICIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1558, 9, 'SAN PEDRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1559, 9, 'SAN ROMAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1560, 9, 'SAN VICENTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1561, 9, 'SANDRINI ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1562, 9, 'SANSINENA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1563, 9, 'SANTA CATALINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1564, 9, 'SANTA COLOMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1565, 9, 'SANTA ELENA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1566, 9, 'SANTA ELEODORA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1567, 9, 'SANTA INES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1568, 9, 'SANTA LUCIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1569, 9, 'SANTA LUCIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1570, 9, 'SANTA MARIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1571, 9, 'SANTA REGINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1572, 9, 'SANTA REGINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1573, 9, 'SANTA TERESITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1574, 9, 'SANTAMARINA, RAMON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1575, 9, 'SANTO DOMINGO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1576, 9, 'SANTOS AREVALO, JOSE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1577, 9, 'SANTOS LUGARES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1578, 9, 'SANTOS UNZUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1579, 9, 'SARANDI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1580, 9, 'SARASA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1581, 9, 'SATURNO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1582, 9, 'SEGUI, ARTURO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1583, 9, 'SEGUROLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1584, 9, 'SEVIGNE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1585, 9, 'SHAW', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1586, 9, 'SIERRA CHICA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1587, 9, 'SIERRA DE LA VENTANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1588, 9, 'SIERRAS BAYAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1589, 9, 'SMITH', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1590, 9, 'SOLA, FELIPE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1591, 9, 'SOLANET', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1592, 9, 'SOSA, INOCENCIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1593, 9, 'SOURDEAUX, ADOLFO ,PDA.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1594, 9, 'SOURIGUES, CARLOS ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1595, 9, 'SPANO, GUIDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1596, 9, 'SPEGAZZINI, CARLOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1597, 9, 'SPURR ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1598, 9, 'STEGMANN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1599, 9, 'STROEDER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1600, 9, 'SUAREZ, JOSE LEON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1601, 9, 'SUAREZ, TRISTAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1602, 9, 'SUIPACHA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1603, 9, 'SUNDBLAD', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1604, 9, 'TABLADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1605, 9, 'TACUARI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1606, 9, 'TAMANGUEYU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1607, 9, 'TANDIL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1608, 9, 'TAPALQUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1609, 9, 'TAPIALES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1610, 9, 'TATAY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1611, 9, 'TEJEDOR, CARLOS ,EST.TEJEDOR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1612, 9, 'TEMPERLEY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1613, 9, 'TENIENTE CORONEL MIÃANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1614, 9, 'TENIENTE ORIGONE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1615, 9, 'THAMES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1616, 9, 'TIGRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1617, 9, 'TIMOTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1618, 9, 'TIMOTE ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1619, 9, 'TODD', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1620, 9, 'TOLOSA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1621, 9, 'TORNQUIST', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1622, 9, 'TORTUGUITAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1623, 9, 'TRENQUE LAUQUEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1624, 9, 'TRES ALGARROBOS ,EST.CUENCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1625, 9, 'TRES ARROYOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1626, 9, 'TRES LAGUNAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1627, 9, 'TRES LOMAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1628, 9, 'TRES PICOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1629, 9, 'TRIUNVIRATO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1630, 9, 'TRONCONI, JUAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1631, 9, 'TRONGE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1632, 9, 'TURDERA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1633, 9, 'TUYUTI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1634, 9, 'UBALLES, EUFEMIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1635, 9, 'UDAQUIOLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1636, 9, 'UNION FERROVIARIA ,PDA.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1637, 9, 'UNZUE, MARIANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1638, 9, 'URDAMPILLETA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1639, 9, 'URIBURU, TEDIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1640, 9, 'VALDES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1641, 9, 'VALLIMANCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1642, 9, 'VARELA, FLORENCIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1643, 9, 'VASQUEZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1644, 9, 'VATTEONE, ARTURO ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1645, 9, 'VEDIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1646, 9, 'VELLOSO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1647, 9, 'VERGARA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1648, 9, 'VERONICA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1649, 9, 'VICEALMIRANTE E.MONTES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1650, 9, 'VICTORIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1651, 9, 'VIDELA DORNA, ZENON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1652, 9, 'VIEYTES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1653, 9, 'VILELA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1654, 9, 'VILLA ADELINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1655, 9, 'VILLA ANGELICA ,EST,EL SOCORRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1656, 9, 'VILLA BALLESTER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1657, 9, 'VILLA BARILARI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1658, 9, 'VILLA BOSCH ,PDA.JUAN MARIA BOSCH', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1659, 9, 'VILLA CARAZA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1660, 9, 'VILLA CASTELAR ,EST.ERIZE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1661, 9, 'VILLA CRISTOBAL COLON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1662, 9, 'VILLA DE MAYO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1663, 9, 'VILLA DIAMANTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1664, 9, 'VILLA DOMINICO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1665, 9, 'VILLA EL CACIQUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1666, 9, 'VILLA ELISA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1667, 9, 'VILLA ESPAÃA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1668, 9, 'VILLA GARIBALDI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1669, 9, 'VILLA GENERAL ARIAS ,EST.KILOMETRO 638', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1670, 9, 'VILLA GENERAL SAVIO EST.SANCHEZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1671, 9, 'VILLA GESEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1672, 9, 'VILLA GIAMBRUNO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1673, 9, 'VILLA HARDING GREEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1674, 9, 'VILLA IRIS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1675, 9, 'VILLA LUZURIAGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1676, 9, 'VILLA LYCH PUEYRREDON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1677, 9, 'VILLA LYNCH ,EST.CNL.FRANCISCO LYNCH', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1678, 9, 'VILLA MADERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1679, 9, 'VILLA MOLL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1680, 9, 'VILLA NUMANCIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1681, 9, 'VILLA OLGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1682, 9, 'VILLA PRONSATO ,EST.CORONEL MALDONADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1683, 9, 'VILLA SABOYA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1684, 9, 'VILLA SAUZE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1685, 9, 'VILLA SENA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1686, 9, 'VILLA SERRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1687, 9, 'VILLALONGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1688, 9, 'VILLANUEVA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1689, 9, 'VILLARS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1690, 9, 'VIÃA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1691, 9, 'VIRREYES ,EST.FACUNDO QUIROGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1692, 9, 'VIVORATA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1693, 9, 'VIVORATA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1694, 9, 'VOLUNTAD', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1695, 9, 'VUCETICH, JUAN ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1696, 9, 'WARNES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1697, 9, 'WILDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1698, 9, 'YERBAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1699, 9, 'YRAIZOZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1700, 9, 'ZAMUDIO ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1701, 9, 'ZARATE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1702, 9, 'ZAVALIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1703, 9, 'ZEBALLOS, ESTANISLAO SEVERO ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1704, 9, 'ZENTENA ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1705, 9, 'ZUBIAURRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1706, 7, 'ABRA GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1707, 7, 'AEROLITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1708, 7, 'AGUSTINA LIBARONA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1709, 7, 'AHI VEREMOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1710, 7, 'ALGARROBAL VIEJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1711, 7, 'ALHUAMPA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1712, 7, 'AMAMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1713, 7, 'AMBARGASTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1714, 7, 'AMICHA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1715, 7, 'AÃATUYA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1716, 7, 'ANCAJAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1717, 7, 'ANIMAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1718, 7, 'ANTAJE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1719, 7, 'ANTONIO E. TALBOT ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1720, 7, 'ARAGONES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1721, 7, 'ARBOL BLANCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1722, 7, 'ARDILES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1723, 7, 'ARGENTINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1724, 7, 'ARMONIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1725, 7, 'ARRAGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1726, 7, 'AVERIAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1727, 7, 'BAEZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1728, 7, 'BAJO GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1729, 7, 'BANDERA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1730, 7, 'BANDERA BAJADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1731, 7, 'BARRANCAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1732, 7, 'BARRIALITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1733, 7, 'BELGRANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1734, 7, 'BELGRANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1735, 7, 'BELTRAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1736, 7, 'BLANCA POZO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1737, 7, 'BOBADAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1738, 7, 'BOQUERON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1739, 7, 'BREA POZO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1740, 7, 'CAJON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1741, 7, 'CAMPO DEL CIELO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1742, 7, 'CAMPO GALLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1743, 7, 'CAMPO GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1744, 7, 'CAÃADA ESCOBAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1745, 7, 'CARTAVIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1746, 7, 'CASARES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1747, 7, 'CEJOLAO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1748, 7, 'CHAUPI POZO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1749, 7, 'CHILCA JULIANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1750, 7, 'CHILCA LA LOMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1751, 7, 'CHOYA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1752, 7, 'CLODOMIRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1753, 7, 'COLONIA ARBOL BLANCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1754, 7, 'COLONIA DORA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1755, 7, 'COLONIA SAN JUAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1756, 7, 'COLONIA TOTORILLAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1757, 7, 'CORONEL MANUEL L.RICO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1758, 7, 'CUATRO BOCAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1759, 7, 'CUCHI CORRAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1760, 7, 'DOÃA LUISA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1761, 7, 'DONADEU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1762, 7, 'EL CABURE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1763, 7, 'EL CAMBIADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1764, 7, 'EL CEIBAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1765, 7, 'EL CERRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1766, 7, 'EL CHARCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1767, 7, 'EL COLORADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1768, 7, 'EL HOYO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1769, 7, 'EL MALACARA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1770, 7, 'EL MOJON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1771, 7, 'EL PERTIGO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1772, 7, 'EL POLEAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1773, 7, 'EL PORVENIR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1774, 7, 'EL PUESTITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1775, 7, 'EL SETENTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1776, 7, 'EL TOBIANO ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1777, 7, 'EL ZANJON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1778, 7, 'FERNANDEZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1779, 7, 'FORRES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1780, 7, 'FORTIN INCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1781, 7, 'FRIAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1782, 7, 'GARZA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1783, 7, 'GIRARDET', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1784, 7, 'GRAMILLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1785, 7, 'GRANADERO GATICA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1786, 7, 'GUAMPACHO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1787, 7, 'GUARDIA ESCOLTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1788, 7, 'HAASE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1789, 7, 'HERRERA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1790, 7, 'HOYON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1791, 7, 'HUACHANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1792, 7, 'HUAICO HONDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1793, 7, 'HURITU HUASI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1794, 7, 'HUYAMAMPA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1795, 7, 'ICAÃO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1796, 7, 'INGENIERO CARLOS CHRISTIERNSON ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1797, 7, 'ISCA YACU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1798, 7, 'KILOMETR0 80 ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1799, 7, 'KILOMETR0699 ,EMP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1800, 7, 'KILOMETRO 101 ,PDA.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1801, 7, 'KILOMETRO 1362 ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1802, 7, 'KILOMETRO 1380 ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1803, 7, 'KILOMETRO 1391 ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1804, 7, 'KILOMETRO 153 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1805, 7, 'KILOMETRO 18 ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1806, 7, 'KILOMETRO 390 ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1807, 7, 'KILOMETRO 433 ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1808, 7, 'KILOMETRO 436 ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1809, 7, 'KILOMETRO 454 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1810, 7, 'KILOMETRO 473 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1811, 7, 'KILOMETRO 494', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1812, 7, 'KILOMETRO 499 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1813, 7, 'KILOMETRO 546 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1814, 7, 'KILOMETRO 546 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1815, 7, 'KILOMETRO 55 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1816, 7, 'KILOMETRO 606 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1817, 7, 'KILOMETRO 613 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1818, 7, 'KILOMETRO 645 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1819, 7, 'KILOMETRO 659 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1820, 7, 'KILOMETRO 969 ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1821, 7, 'LA ALOJA ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1822, 7, 'LA AURORA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1823, 7, 'LA BANDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1824, 7, 'LA CAÃADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1825, 7, 'LA DONOSA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1826, 7, 'LA FIRMEZA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1827, 7, 'LA FRAGUA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1828, 7, 'LA INVERNADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1829, 7, 'LA ISLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1830, 7, 'LA NENA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1831, 7, 'LA PALOMA ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1832, 7, 'LA PUNTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1833, 7, 'LA TAPA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1834, 7, 'LAPRIDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1835, 7, 'LAS ABRAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1836, 7, 'LAS CARPAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1837, 7, 'LAS FLORES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1838, 7, 'LAS LOMITAS BLANCAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1839, 7, 'LAS TINAJAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1840, 7, 'LAVALLE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1841, 7, 'LEZCANOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1842, 7, 'LIBARONA, AGUSTINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1843, 7, 'LIBERTAD', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1844, 7, 'LILO VIEJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1845, 7, 'LLAJTA MAUCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1846, 7, 'LOS JURIES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1847, 7, 'LOS LINARES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1848, 7, 'LOS MILAGROS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1849, 7, 'LOS NARANJOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1850, 7, 'LOS NUÃEZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1851, 7, 'LOS PIRPINTOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1852, 7, 'LOS QUIROGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1853, 7, 'LOS TELARES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1854, 7, 'LOS TIGRES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1855, 7, 'LUGONES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1856, 7, 'MACO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1857, 7, 'MAILIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1858, 7, 'MAL PASO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1859, 7, 'MALBRAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1860, 7, 'MATARA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1861, 7, 'MEDELLIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1862, 7, 'MEDIA LUNA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1863, 7, 'MELERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1864, 7, 'MIRAVAL, HERNAN M.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1865, 7, 'MISTOL PAMPA ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1866, 7, 'MONTE ALTO ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1867, 7, 'MONTE QUEMADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1868, 7, 'MORALES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1869, 7, 'NASALO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1870, 7, 'NUEVA ESPERANZA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1871, 7, 'NUEVA FRANCIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1872, 7, 'NUEVO SIMBOLAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1873, 7, 'OTUMPA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1874, 7, 'PALO NEGRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1875, 7, 'PAMPA DE LOS GUANACOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1876, 7, 'PAMPA MAYO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1877, 7, 'PAMPA MUYOJ ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1878, 7, 'PAMPA POZO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1879, 7, 'PASO DE LOS OSCARES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1880, 7, 'PATAY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1881, 7, 'PIEDRAS BLANCAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1882, 7, 'POZO BETBEDER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1883, 7, 'POZO DEL CASTAÃO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1884, 7, 'POZO DEL TOBA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1885, 7, 'POZO DULCE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1886, 7, 'POZO HONDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1887, 7, 'POZO HUASCHO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1888, 7, 'POZUELOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1889, 7, 'PROVIDENCIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1890, 7, 'PUNA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1891, 7, 'PUNI TAJO ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1892, 7, 'QUEBRACHITOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1893, 7, 'QUEBRACHO COTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1894, 7, 'QUILUMPA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1895, 7, 'QUIMILI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1896, 7, 'RAMIREZ DE VELAZCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1897, 7, 'RAPELLI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1898, 7, 'REAL SAYANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1899, 7, 'ROBLES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1900, 7, 'ROVERSI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1901, 7, 'SABAGASTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1902, 7, 'SACHAYOJ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1903, 7, 'SALAVINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1904, 7, 'SAN GREGORIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1905, 7, 'SAN GREGORIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1906, 7, 'SAN PABLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1907, 7, 'SAN PEDRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1908, 7, 'SAN RAMON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1909, 7, 'SAN RAMON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1910, 7, 'SANTA BARBARA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1911, 7, 'SANTA CATALINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1912, 7, 'SANTA CRUZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1913, 7, 'SANTA JUSTINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1914, 7, 'SANTA MARIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1915, 7, 'SANTA MARIA ,EST.INGENIERO EZCURRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1916, 7, 'SANTA MARIA SALOME', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1917, 7, 'SANTIAGO DEL ESTERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1918, 7, 'SANTO DOMINGO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1919, 7, 'SANTO DOMINGO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1920, 7, 'SANTOS LUGARES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1921, 7, 'SELVA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1922, 7, 'SEÃORA PUJIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1923, 7, 'SIETE ARBOLES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1924, 7, 'SIMBOL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1925, 7, 'SIMBOL BAJO ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1926, 7, 'SIMBOLAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1927, 7, 'SOL DE JULIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1928, 7, 'SOL DE MAYO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1929, 7, 'SUMAMAO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1930, 7, 'SUMAMPA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1931, 7, 'SUNCHO CORRAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1932, 7, 'SURIHUAYA ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1933, 7, 'TABOADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1934, 7, 'TACANITAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1935, 7, 'TACO POZO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1936, 7, 'TERMAS DE RIO HONDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1937, 7, 'TINTINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1938, 7, 'TIUN PUNCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1939, 7, 'TOBAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1940, 7, 'TRES MOJONES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1941, 7, 'TRES POZOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1942, 7, 'URUTAU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1943, 7, 'VACA HUANUNA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1944, 7, 'VILELAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1945, 7, 'VILLA ADELA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1946, 7, 'VILLA ATAMISQUI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1947, 7, 'VILLA BRAÃA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1948, 7, 'VILLA ELVIRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1949, 7, 'VILLA FIGUEROA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1950, 7, 'VILLA GENERAL MITRE ,EST.PINTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1951, 7, 'VILLA GUASAYAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1952, 7, 'VILLA JIMENEZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1953, 7, 'VILLA LA PUNTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1954, 7, 'VILLA MATARA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1955, 7, 'VILLA MATILDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1956, 7, 'VILLA MATOQUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1957, 7, 'VILLA MERCEDES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1958, 7, 'VILLA OJO DE AGUA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1959, 7, 'VILLA PALMAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1960, 7, 'VILLA QUEBRACHOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1961, 7, 'VILLA RIO HONDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1962, 7, 'VILLA SAN MARTIN ,EST.LORETO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1963, 7, 'VILLA SILIPICA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1964, 7, 'VILLA UNION', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1965, 7, 'VILMER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1966, 7, 'VINARA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1967, 7, 'YOUNG, TOMAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1968, 7, 'YUCHAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1969, 7, 'ZANJON ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1970, 9, 'CABA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1971, 2, 'APAYEREY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1972, 2, 'BARTOLOME DE LAS CASAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1973, 2, 'BOUVIER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1974, 2, 'BRUCHARD', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1975, 2, 'BUENA VISTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1976, 2, 'CABO 1RO. LUGONES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1977, 2, 'CAMPO ALEGRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1978, 2, 'CLORINDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1979, 2, 'COLONIA ALTO ALEGRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1980, 2, 'COLONIA BUENA VISTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1981, 2, 'COLONIA CAMPO VILLAFAÃE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1982, 2, 'COLONIA PASTORIL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1983, 2, 'COLONIA PERIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1984, 2, 'COMANDANTE FONTANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1985, 2, 'CORONEL ARGENTINO LARRABURE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1986, 2, 'DE LAS CASAS, BARTOLOME', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1987, 2, 'DEL CAMPO, ESTANISLAO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1988, 2, 'DOCTOR CARLOS MONTAG', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1989, 2, 'DOCTOR E.RAMOS MEJIAS ,EST.CHIRIGUANOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1990, 2, 'DOCTOR GUMERSINDO SAYAGO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1991, 2, 'DOCTOR JOSE DE GASPRI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1992, 2, 'DOCTOR LUIS AGOTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1993, 2, 'EL CHAÃARAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1994, 2, 'EL COATI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1995, 2, 'EL COLORADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1996, 2, 'EL PERDIDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1997, 2, 'EL TOTORAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1998, 2, 'ESPINILLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (1999, 2, 'ESTERO PATIÃO ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2000, 2, 'FORMOSA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2001, 2, 'FORTIN PILCOMAYO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2002, 2, 'FORTIN SARGENTO 1Âº LEYES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2003, 2, 'FORTIN SOLEDAD', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2004, 2, 'GENERAL ENRIQUE MOSCONI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2005, 2, 'GENERAL FRANCISCO BASILIANO BOSCH', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2006, 2, 'GRAN GUARDIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2007, 2, 'GUADALCAZAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2008, 2, 'HERRADURA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2009, 2, 'IBARRETA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2010, 2, 'INGENIERO ENRIQUE H.FAURE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2011, 2, 'INGENIERO GUILLERMO N.JUAREZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2012, 2, 'KILOMETRO 1769', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2013, 2, 'KILOMETRO 1895 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2014, 2, 'KILOMETRO 213', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2015, 2, 'LA ESPERANZA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2016, 2, 'LA FLORENCIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2017, 2, 'LA FRONTERA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2018, 2, 'LA ISLETA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2019, 2, 'LA PRIMAVERA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2020, 2, 'LA SIRENA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2021, 2, 'LAGUNA BLANCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2022, 2, 'LAGUNA NAICK-NECK', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2023, 2, 'LAGUNA YEMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2024, 2, 'LAPRIDA, FRANCISCO NARCISO DE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2025, 2, 'LAS LOMITAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2026, 2, 'LOA MATACOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2027, 2, 'LOS PILAGAS ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2028, 2, 'MEDIA LUNA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2029, 2, 'MISION TACAAGLE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2030, 2, 'MISION YACARE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2031, 2, 'MONTE LINDO CHICO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2032, 2, 'PALMA SOLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2033, 2, 'PALMA SOLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2034, 2, 'PALMARCITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2035, 2, 'PALO SANTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2036, 2, 'PASO, JUAN JOSE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2037, 2, 'PIRANE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2038, 2, 'PORTEÃO VIEJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2039, 2, 'POSTA CAMBIO A ZALAZAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2040, 2, 'POSTA KILOMETRO 45', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2041, 2, 'POZO DEL TIGRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2042, 2, 'PRESIDENTE YRIGOYEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2043, 2, 'PUERTO CABO IRIGOYEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2044, 2, 'PUERTO DALMACIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2045, 2, 'PUERTO PILCOMAYO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2046, 2, 'PUERTO VELAZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2047, 2, 'SAN CAMILO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2048, 2, 'SAN FRANCISCO DE LAISHI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2049, 2, 'SAN HILARIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2050, 2, 'SAN MARTIN 1', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2051, 2, 'SAN MARTIN 2', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2052, 2, 'SAN RAMON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2053, 2, 'SARGENTO AYUDANTE VICTOR SANABRIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2054, 2, 'SELVA MARIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2055, 2, 'SIETE PALMAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2056, 2, 'TENIENTE BROWN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2057, 2, 'TENIENTE GENERAL JUAN CARLOS SANCHEZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2058, 2, 'TENIENTE GENERAL ROSENDO M FRAGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2059, 2, 'TRES LAGUNAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2060, 2, 'VILLA ESCOLAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2061, 3, '25 DE MAYO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2062, 3, '9 DE JULIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2063, 3, 'ALBA POSE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2064, 3, 'ALMAFUERTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2065, 3, 'APOSTOLES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2066, 3, 'ARISTOBULO DEL VALLE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2067, 3, 'ARROYO DEL MEDIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2068, 3, 'AZARA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2069, 3, 'BARRA BONITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2070, 3, 'BARRA CONCEPCION', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2071, 3, 'BARRANCON SAN PEDRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2072, 3, 'BELLA VISTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2073, 3, 'BERNARDO DE IRIGOYEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2074, 3, 'BONPLAND', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2075, 3, 'CAA YARI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2076, 3, 'CAMPANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2077, 3, 'CAMPIÃAS DE AMERICA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2078, 3, 'CAMPO GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2079, 3, 'CAMPO RAMON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2080, 3, 'CAMPO VIERA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2081, 3, 'CANDELARIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2082, 3, 'CAPIOVI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2083, 3, 'CAPIOVICINO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2084, 3, 'CATARATAS DEL IGUAZU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2085, 3, 'CERRO AZUL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2086, 3, 'CERRO CORA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2087, 3, 'COLONIA APOSTOLES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2088, 3, 'COLONIA ARISTOBULO DEL VALLE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2089, 3, 'COLONIA AURORA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2090, 3, 'COLONIA MBOPICUA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2091, 3, 'COLONIA NARANJITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2092, 3, 'COLONIA SANTA TERESA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2093, 3, 'COLONIA YABEBIRY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2094, 3, 'CONCEPCION DE LA SIERRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2095, 3, 'CORPUS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2096, 3, 'CUÃAPIRU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2097, 3, 'DEL VALLE, ARISTOBULO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2098, 3, 'DESEADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2099, 3, 'DOS ARROYOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2100, 3, 'DOS DE MAYO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2101, 3, 'EL ALCAZAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2102, 3, 'EL PARAISO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2103, 3, 'EL SOBERBIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2104, 3, 'EL TIGRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2105, 3, 'ELDORADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2106, 3, 'FACHINAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2107, 3, 'FRACRAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2108, 3, 'GARUPA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2109, 3, 'GENERAL ALVEAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2110, 3, 'GENERAL GUEMES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2111, 3, 'GOBERNADOR JUAN J.LANUSSE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2112, 3, 'GOBERNADOR LOPEZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2113, 3, 'GOBERNADOR ROCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2114, 3, 'GUARANI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2115, 3, 'INVERNADA DE ITACARUARE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2116, 3, 'IRIGOYEN, BERNARDO DE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2117, 3, 'ITACARUARE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2118, 3, 'JARDIN AMERICA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2119, 3, 'KILOMETRO 18', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2120, 3, 'KILOMETRO 538 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2121, 3, 'KILOMETRO 546 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2122, 3, 'KILOMETRO 577', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2123, 3, 'LA GRUTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2124, 3, 'LAHARRAGE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2125, 3, 'LANUS, MIGUEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2126, 3, 'LAS TUNAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2127, 3, 'LEIS ,PDA.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2128, 3, 'LORETO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2129, 3, 'LOS HELECHOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2130, 3, 'MACHADINO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2131, 3, 'MARTIRES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2132, 3, 'MOJON GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2133, 3, 'MONTEAGUDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2134, 3, 'MONTECARLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2135, 3, 'ÃACANGUAZU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2136, 3, 'OBERA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2137, 3, 'PANAMBI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2138, 3, 'PIÃALITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2139, 3, 'PINDAPOY ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2140, 3, 'POSADAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2141, 3, 'PROFUNDIDAD', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2142, 3, 'PUEBLO TARUMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2143, 3, 'PUERTO 3 DE MAYO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2144, 3, 'PUERTO AVELLANEDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2145, 3, 'PUERTO BOSSETTI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2146, 3, 'PUERTO CARAGUATAY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2147, 3, 'PUERTO CONCEPCION', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2148, 3, 'PUERTO DELICIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2149, 3, 'PUERTO ELDORADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2150, 3, 'PUERTO ELVECIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2151, 3, 'PUERTO ERRECABORDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2152, 3, 'PUERTO ESPAÃA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2153, 3, 'PUERTO ESPERANZA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2154, 3, 'PUERTO GARUHAPE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2155, 3, 'PUERTO GISELA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2156, 3, 'PUERTO IGUAZU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2157, 3, 'PUERTO INGENIERO MORANDI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2158, 3, 'PUERTO LEONI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2159, 3, 'PUERTO LIBERTAD', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2160, 3, 'PUERTO LONDERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2161, 3, 'PUERTO MADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2162, 3, 'PUERTO MENOCCHIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2163, 3, 'PUERTO NARANJITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2164, 3, 'PUERTO OASIS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2165, 3, 'PUERTO ORO VERDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2166, 3, 'PUERTO PARAISO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2167, 3, 'PUERTO PARANAY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2168, 3, 'PUERTO PAULITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2169, 3, 'PUERTO PENINSULA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2170, 3, 'PUERTO PINARES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2171, 3, 'PUERTO PIRAY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2172, 3, 'PUERTO RICO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2173, 3, 'PUERTO ROSARIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2174, 3, 'PUERTO SAN ANTONIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2175, 3, 'PUERTO SAN ISIDRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2176, 3, 'PUERTO SAN LUCAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2177, 3, 'PUERTO SAN MARTIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2178, 3, 'PUERTO SEGUNDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2179, 3, 'PUERTO VICTORIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2180, 3, 'PUERTO YRIGOYEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2181, 3, 'PURTO MINERAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2182, 3, 'RUIZ DE MONTOYA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2183, 3, 'SALTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2184, 3, 'SAN ANTONIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2185, 3, 'SAN FRANCISCO DE ASIS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2186, 3, 'SAN IGNACIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2187, 3, 'SAN JAVIER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2188, 3, 'SAN JOSE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2189, 3, 'SAN PEDRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2190, 3, 'SAN VICENTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2191, 3, 'SANTA ANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2192, 3, 'SANTA MARIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2193, 3, 'SANTA RITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2194, 3, 'SANTA RITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2195, 3, 'SANTO PIPO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2196, 3, 'TACUARUZU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2197, 3, 'TOBUNA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2198, 3, 'TRES CAPONES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2199, 3, 'VILLA LONCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2200, 3, 'WANDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2201, 3, 'YRIGOYEN, HIPOLITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2202, 20, '1Â° DE MAYO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2203, 20, '20 DE SEPTIEMBRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2204, 20, 'ACHAGUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2205, 20, 'ALBERTO GERCHUNOF ,PDA.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2206, 20, 'ALDEA ASUNCION', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2207, 20, 'ALDEA BRASILERA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2208, 20, 'ALDEA CUESTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2209, 20, 'ALDEA MARIA LUISA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2210, 20, 'ALDEA PROTESTANTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2211, 20, 'ALDEA SAN ANTONIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2212, 20, 'ALDEA SAN GREGORIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2213, 20, 'ALDEA SAN JUAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2214, 20, 'ALDEA SANTA MARIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2215, 20, 'ALDEA SPATZENKUTTER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2216, 20, 'ALDEA VALLE MARIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2217, 20, 'ALGARROBITOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2218, 20, 'ALTAMIRANO NORTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2219, 20, 'ALTAMIRANO SUR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2220, 20, 'ANTELO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2221, 20, 'ARANGUREN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2222, 20, 'ARROYO BARU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2223, 20, 'ARROYO CLE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2224, 20, 'ARROYO DEL MEDIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2225, 20, 'ARROYO PALMAR ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2226, 20, 'ARROYO URQUIZA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2227, 20, 'ATENCIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2228, 20, 'BAJADA GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2229, 20, 'BASAVILBASO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2230, 20, 'BELGRANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2231, 20, 'BENITO LEGEREN ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2232, 20, 'BERDUC ,EST.MARTINIANO LEGUIZAMON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2233, 20, 'BERISSO ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2234, 20, 'BETBEDER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2235, 20, 'BOCA DEL TIGRE ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2236, 20, 'BOVRIL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2237, 20, 'CALABACILLAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2238, 20, 'CAMPS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2239, 20, 'CARBO, ENRIQUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2240, 20, 'CEIBAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2241, 20, 'CERRITO ,EST.COLONIA CERRITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2242, 20, 'CHAJARI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2243, 20, 'CHAÃAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2244, 20, 'CLARA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2245, 20, 'COLON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2246, 20, 'COLONIA ARGENTINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2247, 20, 'COLONIA ARGENTINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2248, 20, 'COLONIA ARRUABARRENA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2249, 20, 'COLONIA AVIGDOR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2250, 20, 'COLONIA BELEZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2251, 20, 'COLONIA BUENA VISTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2252, 20, 'COLONIA CASEROS ,EST.CASEROS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2253, 20, 'COLONIA CENTENARIO ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2254, 20, 'COLONIA DELTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2255, 20, 'COLONIA EL CARMEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2256, 20, 'COLONIA EL SAUCE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2257, 20, 'COLONIA ELIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2258, 20, 'COLONIA ENSANCHE SAUCE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2259, 20, 'COLONIA HAMBIS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2260, 20, 'COLONIA HOCKER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2261, 20, 'COLONIA HUGHES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2262, 20, 'COLONIA NUEVA ALEMANIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2263, 20, 'COLONIA SAN ERNESTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2264, 20, 'COLONIA SAN MIGUEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2265, 20, 'COLONIA SAN VICENTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2266, 20, 'CONCEPCION DEL URUGUAY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2267, 20, 'CONCORDIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2268, 20, 'CONSCRIPTO BERNARDI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2269, 20, 'COSTA GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2270, 20, 'CRESPO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2271, 20, 'CUCHILLA REDONDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2272, 20, 'CURTIEMBRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2273, 20, 'DIAMANTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2274, 20, 'DON CRISTOBAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2275, 20, 'DON GONZALO ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2276, 20, 'DURAZNO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2277, 20, 'EL BRILLANTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2278, 20, 'EL CIMARRON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2279, 20, 'EL GUALEGUAY ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2280, 20, 'EL MOCHO ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2281, 20, 'EL PAGO ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2282, 20, 'EL PALENQUE ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2283, 20, 'EL PINGO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2284, 20, 'EL REDOMON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2285, 20, 'ESCRIÃA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2286, 20, 'ESTACAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2287, 20, 'FABRICA COLON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2288, 20, 'FEBRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2289, 20, 'FEDERACION', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2290, 20, 'FEDERAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2291, 20, 'FERNANDEZ ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2292, 20, 'FORTUNA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2293, 20, 'GARAT, JUAN ,EST.GARAT', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2294, 20, 'GENERAL ALMADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2295, 20, 'GENERAL ALVEAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2296, 20, 'GENERAL CAMPOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2297, 20, 'GENERAL GALARZA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2298, 20, 'GENERAL GUEMES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2299, 20, 'GENERAL RACEDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2300, 20, 'GENERAL RAMIREZ ,EST.RAMIREZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2301, 20, 'GILBERT', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2302, 20, 'GOBERNADOR MANSILLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2303, 20, 'GONZALEZ CALDERON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2304, 20, 'GONZALEZ, LUCAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2305, 20, 'GUALEGUAY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2306, 20, 'GUALEGUAYCHU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2307, 20, 'GUARDAMONTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2308, 20, 'HASENKAMP', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2309, 20, 'HERNANDEZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2310, 20, 'HERRERA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2311, 20, 'HOLT ,EST', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2312, 20, 'IBICUY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2313, 20, 'IRAZUSTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2314, 20, 'JUAN JORGE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2315, 20, 'JUBILEO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2316, 20, 'KILOMETRO 160 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2317, 20, 'KILOMETRO 180 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2318, 20, 'KILOMETRO 183,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2319, 20, 'KILOMETRO 200 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2320, 20, 'KILOMETRO 208 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2321, 20, 'KILOMETRO 213 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2322, 20, 'KILOMETRO 306 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2323, 20, 'KILOMETRO 325 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2324, 20, 'KILOMETRO 33 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2325, 20, 'KILOMETRO 340 ,PDA.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2326, 20, 'KILOMETRO 361 ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2327, 20, 'KILOMETRO 389 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2328, 20, 'KILOMETRO 45 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2329, 20, 'LA ARGENTINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2330, 20, 'LA CALANDRIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2331, 20, 'LA CLARITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2332, 20, 'LA CRIOLLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2333, 20, 'LA ESMERALDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2334, 20, 'LA GRANJA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2335, 20, 'LA HIERRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2336, 20, 'LA LLAVE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2337, 20, 'LA PAZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2338, 20, 'LA PICADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2339, 20, 'LA QUERENCIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2340, 20, 'LA VIRGEN ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2341, 20, 'LAGUNA LARGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2342, 20, 'LARROQUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2343, 20, 'LAS CUEVAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2344, 20, 'LAS DELICIAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2345, 20, 'LAS GARZAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2346, 20, 'LAS MERCEDES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2347, 20, 'LAS MOSCAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2348, 20, 'LAS MULITAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2349, 20, 'LAZO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2350, 20, 'LEDESMA, CLODOMIRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2351, 20, 'LEGUIZAMON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2352, 20, 'LIBAROS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2353, 20, 'LIEBIG', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2354, 20, 'LOS CHARRUAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2355, 20, 'LOS CONQUISTADORES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2356, 20, 'MACIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2357, 20, 'MAGNASCO, OSVALDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2358, 20, 'MANDISOVI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2359, 20, 'MAZARUCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2360, 20, 'MEDANOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2361, 20, 'MIÃONES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2362, 20, 'MOJONES NORTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2363, 20, 'MOJONES SUR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2364, 20, 'MOLINO DOLL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2365, 20, 'MONTIEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2366, 20, 'MONTOYA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2367, 20, 'MOREIRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2368, 20, 'NOGOYA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2369, 20, 'NUEVA ESCOCIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2370, 20, 'NUEVA VIZCAYA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2371, 20, 'ORO VERDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2372, 20, 'PALAVECINO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2373, 20, 'PALMAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2374, 20, 'PALO A PIQUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2375, 20, 'PARANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2376, 20, 'PARANACITO ,EST.LIB.GRL.SAN MARTIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2377, 20, 'PARERA, FAUSTINO M.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2378, 20, 'PARERA, RAMONA A. ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2379, 20, 'PASO DE LA LAGUNA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2380, 20, 'PASTOR BRITOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2381, 20, 'PEDERNAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2382, 20, 'PERDICES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2383, 20, 'PIEDRAS BLANCAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2384, 20, 'PILOTO AVILA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2385, 20, 'PRIMER CONGRESO ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2386, 20, 'PRONUNCIAMIENTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2387, 20, 'PUEBLO ARRUA ,EST.ALCARAZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2388, 20, 'PUEBLO BRUGO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2389, 20, 'PUEBLO FERRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2390, 20, 'PUEBLO GENERAL PAZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2391, 20, 'PUEBLO MORENO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2392, 20, 'PUERTO CUPALEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2393, 20, 'PUERTO IBICUY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2394, 20, 'PUERTO UNZUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2395, 20, 'PUIGGARI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2396, 20, 'RAICES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2397, 20, 'RINCON DE NOGOYA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2398, 20, 'ROCAMORA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2399, 20, 'ROSARIO DE TALA ,EST.TALA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2400, 20, 'SAN BENITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2401, 20, 'SAN GUSTAVO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2402, 20, 'SAN JAIME', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2403, 20, 'SAN JORGE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2404, 20, 'SAN JOSE DE FELICIANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2405, 20, 'SAN JUSTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2406, 20, 'SAN RAMON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2407, 20, 'SAN SALVADOR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2408, 20, 'SAN VICTOR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2409, 20, 'SANTA ANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2410, 20, 'SANTA ANITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2411, 20, 'SANTA ELENA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2412, 20, 'SAUCE DE LUNA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2413, 20, 'SAUCE PINTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2414, 20, 'SEGUI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2415, 20, 'SOLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2416, 20, 'SOSA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2417, 20, 'STROBEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2418, 20, 'TABOSSI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2419, 20, 'TALITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2420, 20, 'TALITAS ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2421, 20, 'TENIENTE 1Â° BRIGIDO CAINZO ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2422, 20, 'TEZANOS PINTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2423, 20, 'TRES BOCAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2424, 20, 'UBAJAY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2425, 20, 'URDINARRAIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2426, 20, 'VIALE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2427, 20, 'VICTORIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2428, 20, 'VILLA DEL ROSARIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2429, 20, 'VILLA DOMINGUEZ ,EST.DOMINGUEZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2430, 20, 'VILLA ELISA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2431, 20, 'VILLA HERNANDARIAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2432, 20, 'VILLA MANTERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2433, 20, 'VILLA MARIA GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2434, 20, 'VILLA NUEVA MONTEVIDEO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2435, 20, 'VILLA PARANACITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2436, 20, 'VILLA SAN JOSE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2437, 20, 'VILLA SAN JOSE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2438, 20, 'VILLA SAN MARCIAL ,EST.GDR.URQUIZA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2439, 20, 'VILLA URQUIZA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2440, 20, 'VILLA ZORRAQUIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2441, 20, 'VILLAGUAY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2442, 20, 'YAROS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2443, 20, 'YERUA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2444, 20, 'YESO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2445, 20, 'YUQUERI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2446, 21, 'ACHIRAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2447, 21, 'ADELIA MARIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2448, 21, 'AGUA DE LAS PIEDRAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2449, 21, 'AGUA DE ORO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2450, 21, 'AGUA DEL TALA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2451, 21, 'AGUAS DE RAMON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2452, 21, 'AGUILA BLANCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2453, 21, 'ALCIRA ,EST.GIGENA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2454, 21, 'ALEJANDRO ROCA ,EST. ALEJANDRO.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2455, 21, 'ALEJO LEDESMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2456, 21, 'ALICIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2457, 21, 'ALMAFUERTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2458, 21, 'ALPA CORRAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2459, 21, 'ALTA CORDOBA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2460, 21, 'ALTA CORDOBA ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2461, 21, 'ALTA GRACIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2462, 21, 'ALTAUTINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2463, 21, 'ALTO ALEGRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2464, 21, 'ALTO DEL TALA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2465, 21, 'ALTO FRESCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2466, 21, 'ALTO SAN PEDRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2467, 21, 'ALTOS DE CHIPION', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2468, 21, 'AMBOY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2469, 21, 'AMBUL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2470, 21, 'ANA ZUMARAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2471, 21, 'ARBOL BLANCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2472, 21, 'ARGUELLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2473, 21, 'ARIAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2474, 21, 'ARROYITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2475, 21, 'ARROYO ALGODON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2476, 21, 'ARROYO CABRAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2477, 21, 'ASCOCHINGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2478, 21, 'ASSUNTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2479, 21, 'ATAHONA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2480, 21, 'ATOS PAMPA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2481, 21, 'AUSONIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2482, 21, 'AVELLANEDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2483, 21, 'BAJO CHICO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2484, 21, 'BALDE DE LA MORA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2485, 21, 'BALLESTEROS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2486, 21, 'BALLESTEROS SUR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2487, 21, 'BALNEARIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2488, 21, 'BAÃADO DE SOTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2489, 21, 'BARRANCA YACO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2490, 21, 'BARRETO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2491, 21, 'BARRIO FLORES ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2492, 21, 'BELL VILLE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2493, 21, 'BENGOLEA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2494, 21, 'BENJAMIN GOULD', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2495, 21, 'BERROTARAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2496, 21, 'BIALET MASSE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2497, 21, 'BLAS DE ROSALES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2498, 21, 'BOCA DEL TIGRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2499, 21, 'BOUWER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2500, 21, 'BRINKMANN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2501, 21, 'BUCHARDO ,EST.HIPOLITO BOUCHARD', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2502, 21, 'BUEN RETIRO ,AP.EL VADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2503, 21, 'BUENA VISTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2504, 21, 'BUEY MUERTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2505, 21, 'BULNES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2506, 21, 'BURMEISTER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2507, 21, 'CABANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2508, 21, 'CACHI YACO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2509, 21, 'CACHIYUYO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2510, 21, 'CALCHIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2511, 21, 'CALMAYO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2512, 21, 'CAMILO ALDAO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2513, 21, 'CAMINIAGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2514, 21, 'CAMPO GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2515, 21, 'CAÃADA DE ALVAREZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2516, 21, 'CAÃADA DE JUME', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2517, 21, 'CAÃADA DE LUQUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2518, 21, 'CAÃADA DE MACHADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2519, 21, 'CAÃADA DE RIO PINTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2520, 21, 'CAÃADA DE SALAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2521, 21, 'CAÃADA DEL CORO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2522, 21, 'CAÃADA HONDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2523, 21, 'CANALS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2524, 21, 'CANDELARIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2525, 21, 'CANDELARIA NORTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2526, 21, 'CANDELARIA SUD', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2527, 21, 'CANDONGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2528, 21, 'CANTERAS EL SAUCE ,EST.EMP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2529, 21, 'CANTERAS QUILPO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2530, 21, 'CAPILLA DE LA SAGRADA FAMILIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2531, 21, 'CAPILLA DE LOS REMEDIOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2532, 21, 'CAPILLA DE SITON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2533, 21, 'CAPILLA DEL CARMEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2534, 21, 'CAPILLA DEL MONTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2535, 21, 'CAPITAN GENERAL BERNARDO O HIGGINS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2536, 21, 'CARCANO, RAMON J.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2537, 21, 'CARNERILLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2538, 21, 'CAROLINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2539, 21, 'CAROYA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2540, 21, 'CARRILOBO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2541, 21, 'CASA BAMBA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2542, 21, 'CASA GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2543, 21, 'CASERIO LA LONJA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2544, 21, 'CASSAFOUSTH', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2545, 21, 'CASTRO URDIALES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2546, 21, 'CAVALANGO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2547, 21, 'CAVANAGH', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2548, 21, 'CAYUQUEO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2549, 21, 'CERRO COLORADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2550, 21, 'CHAJAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2551, 21, 'CHALACEA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2552, 21, 'CHALACEA CENTRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2553, 21, 'CHAMICO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2554, 21, 'CHAÃAR VIEJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2555, 21, 'CHAÃARITOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2556, 21, 'CHANCANI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2557, 21, 'CHARBONIER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2558, 21, 'CHARRAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2559, 21, 'CHAZON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2560, 21, 'CHILIBROSTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2561, 21, 'CHUCHIRAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2562, 21, 'CHUCUL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2563, 21, 'CHUÃA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2564, 21, 'CHUÃA HUASI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2565, 21, 'CHURQUI CAÃADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2566, 21, 'CIENAGA DE ALLENDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2567, 21, 'CIENAGA DE BRITOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2568, 21, 'CIENAGA DEL CORO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2569, 21, 'CINTRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2570, 21, 'COLAZO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2571, 21, 'COLONIA 10 DE JULIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2572, 21, 'COLONIA ALMADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2573, 21, 'COLONIA ALMADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2574, 21, 'COLONIA ANITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2575, 21, 'COLONIA ANITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2576, 21, 'COLONIA BISMARCK', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2577, 21, 'COLONIA BREMEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2578, 21, 'COLONIA CAROYA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2579, 21, 'COLONIA COYUNDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2580, 21, 'COLONIA EL CARMEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2581, 21, 'COLONIA LA TORDILLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2582, 21, 'COLONIA LAS CUATRO ESQUINAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2583, 21, 'COLONIA MACKINLAY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2584, 21, 'COLONIA MALBERTINA ,EMB.KILOMETRO 531', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2585, 21, 'COLONIA MARINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2586, 21, 'COLONIA PROSPERIDAD', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2587, 21, 'COLONIA RIO CHICO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2588, 21, 'COLONIA SAN BARTOLOME', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2589, 21, 'COLONIA SAN PEDRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2590, 21, 'COLONIA SAN RAFAEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2591, 21, 'COLONIA SANTA MARIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2592, 21, 'COLONIA SANTA MARIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2593, 21, 'COLONIA TIROLESA ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2594, 21, 'COLONIA VEINTICINCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2595, 21, 'COLONIA VIDELA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2596, 21, 'COLONIA VIGNAUD', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2597, 21, 'COLONIA WALTELINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2598, 21, 'CONLARA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2599, 21, 'COPACABANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2600, 21, 'CORDOBA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2601, 21, 'CORONEL BAIGORRIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2602, 21, 'CORONEL MOLDES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2603, 21, 'CORONEL OLMEDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2604, 21, 'CORRAL DE BARRANCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2605, 21, 'CORRAL DE BUSTOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2606, 21, 'CORRAL DEL BAJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2607, 21, 'CORRALITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2608, 21, 'CORTES, JERONIMO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2609, 21, 'COSME', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2610, 21, 'COSQUIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2611, 21, 'COSTA SACATE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2612, 21, 'COTAGAITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2613, 21, 'CRAIK, JAMES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2614, 21, 'CRUZ ALTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2615, 21, 'CRUZ CHICA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2616, 21, 'CRUZ DE CAÃA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2617, 21, 'CRUZ DEL EJE ,EST.NORTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2618, 21, 'CRUZ DEL QUEMADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2619, 21, 'CRUZ GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2620, 21, 'CRUZ MOJADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2621, 21, 'CUATRO CAMINOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2622, 21, 'CUATRO VIENTOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2623, 21, 'CUCHI CORRAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2624, 21, 'CUEVA DE INDIOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2625, 21, 'CUEVA DE LOS PAJARITOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2626, 21, 'CURAPALIGUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2627, 21, 'DE LA QUINTANA, JOSE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2628, 21, 'DE LA SERNA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2629, 21, 'DEAN FUNES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2630, 21, 'DEL CAMPILLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2631, 21, 'DESPEÃADEROS ,EST.DR.LUCAS A.DE OLMOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2632, 21, 'DEVOTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2633, 21, 'DIQUE SAN ROQUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2634, 21, 'DOCTOR NICASIO SALAS OROÃO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2635, 21, 'DOLORES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2636, 21, 'DUMESNIL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2637, 21, 'EL ARAÃADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2638, 21, 'EL BAÃADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2639, 21, 'EL BAÃADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2640, 21, 'EL BRETE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2641, 21, 'EL CADILLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2642, 21, 'EL CANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2643, 21, 'EL CHACHO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2644, 21, 'EL CRISPIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2645, 21, 'EL DIQUECITO ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2646, 21, 'EL DURAZNO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2647, 21, 'EL DURAZNO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2648, 21, 'EL FARO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2649, 21, 'EL FLORENTINO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2650, 21, 'EL FORTIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2651, 21, 'EL FUERTECITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2652, 21, 'EL GATEADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2653, 21, 'EL MANZANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2654, 21, 'EL MIRADOR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2655, 21, 'EL PAMPERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2656, 21, 'EL PAYADOR ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2657, 21, 'EL PERCHEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2658, 21, 'EL PERCHEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2659, 21, 'EL PONIENTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2660, 21, 'EL PORTEZUELO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2661, 21, 'EL QUEBRACHO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2662, 21, 'EL QUEBRACHO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2663, 21, 'EL RASTREADOR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2664, 21, 'EL RETIRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2665, 21, 'EL ROSARIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2666, 21, 'EL SAUCE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2667, 21, 'EL SUNCHO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2668, 21, 'EL TAMBO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2669, 21, 'EL TIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2670, 21, 'EL TOSTADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2671, 21, 'EL TUSCAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2672, 21, 'EL VENCE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2673, 21, 'EL ZAINO ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2674, 21, 'EL ZAPATO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2675, 21, 'ELCANO, SEBASTIAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2676, 21, 'ELENA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2677, 21, 'EMBALSE MINISTRO JUAN PISTARINI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2678, 21, 'ENCRUCIJADAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2679, 21, 'ESCALANTE, WENCESLAO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2680, 21, 'ESCUELA PARACAIDISTAS ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2681, 21, 'ESPINILLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2682, 21, 'ESQUINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2683, 21, 'ESTANCIA DE GUADALUPE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2684, 21, 'ETRURIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2685, 21, 'FALDA DEL CARMEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2686, 21, 'FERREYRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2687, 21, 'FLORA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2688, 21, 'FRAGUEIRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2689, 21, 'FRAY CAYETANO RODRIGUEZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2690, 21, 'FREYRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2691, 21, 'FUNES, DOMINGO ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2692, 21, 'FUNES, PEDRO E.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2693, 21, 'GARCIA, RAFAEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2694, 21, 'GAVILAN ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2695, 21, 'GENERAL BALDISSERA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2696, 21, 'GENERAL CABRERA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2697, 21, 'GENERAL DEHEZA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2698, 21, 'GENERAL FOTHERINGHAM', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2699, 21, 'GENERAL LEVALLE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2700, 21, 'GENERAL ORTIZ DE OCAMPO ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2701, 21, 'GENERAL PAZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2702, 21, 'GENERAL ROCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2703, 21, 'GENERAL SOLER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2704, 21, 'GOULD, BENJAMIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2705, 21, 'GUANACO MUERTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2706, 21, 'GUARDIA VIEJA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2707, 21, 'GUASAPAMPA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2708, 21, 'GUATIMOZIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2709, 21, 'GUIÃAZU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2710, 21, 'GUTEMBERG', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2711, 21, 'HERNANDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2712, 21, 'HUANCHILLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2713, 21, 'HUASCHA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2714, 21, 'HUERTA GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2715, 21, 'HUINCA RENANCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2716, 21, 'IDIAZABAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2717, 21, 'IGLESIA VIEJA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2718, 21, 'IMPIRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2719, 21, 'INGENIERO MALMEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2720, 21, 'INRIVILLE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2721, 21, 'ISCHILIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2722, 21, 'ISCHILIN VIEJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2723, 21, 'ISLA DE SAN ANTONIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2724, 21, 'ISLA VERDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2725, 21, 'ITALO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2726, 21, 'JEANMAIRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2727, 21, 'JESUS MARIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2728, 21, 'JUAREZ CELMAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2729, 21, 'JUAREZ, MARCOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2730, 21, 'KILOMETRO 1,5 AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2731, 21, 'KILOMETRO 136 ,EMP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2732, 21, 'KILOMETRO 16', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2733, 21, 'KILOMETRO 16', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2734, 21, 'KILOMETRO 182 ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2735, 21, 'KILOMETRO 23', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2736, 21, 'KILOMETRO 23', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2737, 21, 'KILOMETRO 25', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2738, 21, 'KILOMETRO 271 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2739, 21, 'KILOMETRO 316 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2740, 21, 'KILOMETRO 394 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2741, 21, 'KILOMETRO 466 ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2742, 21, 'KILOMETRO 480 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2743, 21, 'KILOMETRO 541 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2744, 21, 'KILOMETRO 563 ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2745, 21, 'KILOMETRO 57 ,EST.EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2746, 21, 'KILOMETRO 579 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2747, 21, 'KILOMETRO 581 ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2748, 21, 'KILOMETRO 608 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2749, 21, 'KILOMETRO 618 ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2750, 21, 'KILOMETRO 636 ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2751, 21, 'KILOMETRO 651 ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2752, 21, 'KILOMETRO 679', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2753, 21, 'KILOMETRO 691 ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2754, 21, 'KILOMETRO 7', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2755, 21, 'KILOMETRO 730 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2756, 21, 'KILOMETRO 745', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2757, 21, 'KILOMETRO 859 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2758, 21, 'KILOMETRO 865 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2759, 21, 'KILOMETRO 881 ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2760, 21, 'KILOMETRO 9', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2761, 21, 'KILOMETRO 907 ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2762, 21, 'KILOMETRO 931', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2763, 21, 'LA AGUADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2764, 21, 'LA ARGENTINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2765, 21, 'LA BISMUTINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2766, 21, 'LA BUENA PARADA ,EST.COMECHINGONES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2767, 21, 'LA CALERA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2768, 21, 'LA CAÃADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2769, 21, 'LA CAÃADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2770, 21, 'LA CARLOTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2771, 21, 'LA CAUTIVA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2772, 21, 'LA CORTADERA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2773, 21, 'LA CRUZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2774, 21, 'LA CUESTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2775, 21, 'LA CUMBRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2776, 21, 'LA ESPERANZA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2777, 21, 'LA ESQUINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2778, 21, 'LA ESTANCIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2779, 21, 'LA FALDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2780, 21, 'LA FRANCIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2781, 21, 'LA GILDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2782, 21, 'LA GRANJA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2783, 21, 'LA HERRADURA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2784, 21, 'LA HIGUERA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2785, 21, 'LA JUANITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2786, 21, 'LA LAGUNA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2787, 21, 'LA MAJADILLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2788, 21, 'LA MUDANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2789, 21, 'LA NACIONAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2790, 21, 'LA PALESTINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2791, 21, 'LA PAMPA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2792, 21, 'LA PAQUITA ,EST.PRESIDENTE F.ALCORTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2793, 21, 'LA PARA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2794, 21, 'LA PATRIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2795, 21, 'LA PAZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2796, 21, 'LA PENCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2797, 21, 'LA PLAYA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2798, 21, 'LA PLAYOSA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2799, 21, 'LA POBLACION', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2800, 21, 'LA POSTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2801, 21, 'LA PUERTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2802, 21, 'LA PUERTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2803, 21, 'LA RAMADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2804, 21, 'LA REPRESA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2805, 21, 'LA RINCONADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2806, 21, 'LA SERRANITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2807, 21, 'LA TABLADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2808, 21, 'LA TABLADA ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2809, 21, 'LA TIGRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2810, 21, 'LA TOMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2811, 21, 'LA TOMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2812, 21, 'LABORDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2813, 21, 'LABOULAYE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2814, 21, 'LAGUNA DEL MONTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2815, 21, 'LAGUNA DEL SUNCHO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2816, 21, 'LAGUNA LARGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2817, 21, 'LAGUNA OSCURA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2818, 21, 'LAGUNILLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2819, 21, 'LARSEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2820, 21, 'LAS ACEQUIAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2821, 21, 'LAS ARRIAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2822, 21, 'LAS AVERIAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2823, 21, 'LAS BAJADAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2824, 21, 'LAS CALLES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2825, 21, 'LAS CAÃITAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2826, 21, 'LAS CHACRAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2827, 21, 'LAS CHACRAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2828, 21, 'LAS CHACRAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2829, 21, 'LAS CHACRAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2830, 21, 'LAS ENSENADAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2831, 21, 'LAS GAMAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2832, 21, 'LAS HIGUERAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2833, 21, 'LAS ISLETILLAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2834, 21, 'LAS JUNTURAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2835, 21, 'LAS MOJARRAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2836, 21, 'LAS MOSTAZAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2837, 21, 'LAS MOSTAZAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2838, 21, 'LAS PALMAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2839, 21, 'LAS PEÃAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2840, 21, 'LAS PEÃAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2841, 21, 'LAS PERDICES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2842, 21, 'LAS RABONAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2843, 21, 'LAS SALADAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2844, 21, 'LAS TAPIAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2845, 21, 'LAS TAPIAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2846, 21, 'LAS TOSCAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2847, 21, 'LAS TOSCAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2848, 21, 'LAS VARAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2849, 21, 'LAS VARILLAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2850, 21, 'LAS VERTIENTES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2851, 21, 'LASPIUR, SATURNINO M.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2852, 21, 'LECUEDER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2853, 21, 'LEDESMA, ALEJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2854, 21, 'LEGUIZAMON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2855, 21, 'LEONES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2856, 21, 'LOA ALAMOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2857, 21, 'LOA ALGARROBOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2858, 21, 'LOMAS DE SAN JOSE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2859, 21, 'LOS ALFALFARES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2860, 21, 'LOS ALTOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2861, 21, 'LOS CADILLOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2862, 21, 'LOS CALLEJONES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2863, 21, 'LOS CERRILLOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2864, 21, 'LOS CERRILLOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2865, 21, 'LOS CHAÃARES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2866, 21, 'LOS CHAÃARITOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2867, 21, 'LOS CISNES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2868, 21, 'LOS COCOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2869, 21, 'LOS COMETIERRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2870, 21, 'LOS CONDORES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2871, 21, 'LOS GIGANTES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2872, 21, 'LOS HORNILLOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2873, 21, 'LOS HOYOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2874, 21, 'LOS JAGUELES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2875, 21, 'LOS MANANTIALES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2876, 21, 'LOS MEDANITOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2877, 21, 'LOS MISTOLES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2878, 21, 'LOS MOLINOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2879, 21, 'LOS MOLLES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2880, 21, 'LOS MOLLES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2881, 21, 'LOS MORTERITOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2882, 21, 'LOS PORONGOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2883, 21, 'LOS POZOS ,AP.KILOMETRO 827', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2884, 21, 'LOS QUEBRACHITOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2885, 21, 'LOS QUEBRACHITOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2886, 21, 'LOS REARTES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2887, 21, 'LOS SAUCES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2888, 21, 'LOS SURGENTES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2889, 21, 'LOS TARTAROS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2890, 21, 'LOS ZORROS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2891, 21, 'LOZADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2892, 21, 'LUCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2893, 21, 'LUGONES, LEOPOLDO ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2894, 21, 'LUQUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2895, 21, 'LUTTI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2896, 21, 'LUXARDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2897, 21, 'LUYABA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2898, 21, 'MACHA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2899, 21, 'MAGIGASTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2900, 21, 'MAJADA DE SANTIAGO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2901, 21, 'MALAGUENO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2902, 21, 'MALENA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2903, 21, 'MALLIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2904, 21, 'MALVINAS ARGENTINAS ,EST.KILOMETRO 711', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2905, 21, 'MANANTIALES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2906, 21, 'MANFREDI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2907, 21, 'MANSILLA, LUCIO V.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2908, 21, 'MAQUINISTA GALLINI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2909, 21, 'MARULL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2910, 21, 'MATORRALES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2911, 21, 'MATTALDI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2912, 21, 'MEDIA LUNA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2913, 21, 'MEDIA NARANJA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2914, 21, 'MELO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2915, 21, 'MENDIOLAZA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2916, 21, 'MINA CLAVERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2917, 21, 'MIRAMAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2918, 21, 'MOLINARI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2919, 21, 'MONTE BUEY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2920, 21, 'MONTE CRISTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2921, 21, 'MONTE DE LOS GAUCHOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2922, 21, 'MONTE DEL ROSARIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2923, 21, 'MONTE LEÃA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2924, 21, 'MONTE MAIZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2925, 21, 'MONTE RALO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2926, 21, 'MONTE REDONDO ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2927, 21, 'MORRISON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2928, 21, 'MORTEROS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2929, 21, 'MULA MUERTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2930, 21, 'MUSSI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2931, 21, 'NARVAJA, TRISTAN ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2932, 21, 'NAZCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2933, 21, 'NINALQUIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2934, 21, 'NOETINGER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2935, 21, 'NONO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2936, 21, 'NUEVA ALDALUCIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2937, 21, 'NUÃEZ DEL ORADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2938, 21, 'OBISPO TREJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2939, 21, 'OJO DE AGUA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2940, 21, 'OJO DE AGUA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2941, 21, 'OLAEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2942, 21, 'OLAETA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2943, 21, 'OLIVA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2944, 21, 'OLMOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2945, 21, 'ONAGOITY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2946, 21, 'ONCATIVO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2947, 21, 'ONGAMIRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2948, 21, 'ORDOÃEZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2949, 21, 'ORO GRUESO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2950, 21, 'PACHANGO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2951, 21, 'PACHECO DE MELO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2952, 21, 'PAJAS BLANCAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2953, 21, 'PAMPAYASTA NORTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2954, 21, 'PAMPAYASTA SUR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2955, 21, 'PAN DE AZUCAR ,AP.KILOMETRO 592', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2956, 21, 'PANAHOLMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2957, 21, 'PASCANAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2958, 21, 'PASCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2959, 21, 'PASO DEL RIO SECO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2960, 21, 'PASO VIEJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2961, 21, 'PAUNERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2962, 21, 'PAVIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2963, 21, 'PEGASANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2964, 21, 'PELLICO, SILVIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2965, 21, 'PETER, JAIME', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2966, 21, 'PICHANAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2967, 21, 'PIEDRA BLANCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2968, 21, 'PIEDRAS ANCHAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2969, 21, 'PIEDRAS BLANCAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2970, 21, 'PIEDRITA BLANCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2971, 21, 'PIEDRITAS ROSADAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2972, 21, 'PILAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2973, 21, 'PINAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2974, 21, 'PINCEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2975, 21, 'PINTOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2976, 21, 'PIQUILLIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2977, 21, 'PIZARRO, MODESTINO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2978, 21, 'PLAYA GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2979, 21, 'PLAZA BRUNO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2980, 21, 'PLAZA COLAZO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2981, 21, 'PLAZA DE MERCEDES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2982, 21, 'PLAZA DEHEZA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2983, 21, 'PLAZA LUXARDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2984, 21, 'PLAZA SAN FRANCISCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2985, 21, 'PLAZA VIDELA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2986, 21, 'POCHO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2987, 21, 'PORTEÃA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2988, 21, 'POSSE, JUSTINIANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2989, 21, 'POZO DE LA LOMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2990, 21, 'POZO DE LA PAMPA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2991, 21, 'POZO DE LAS OLLAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2992, 21, 'POZO DEL CHAÃAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2993, 21, 'POZO DEL MOLLE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2994, 21, 'POZO DEL MORO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2995, 21, 'POZO DEL TIGRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2996, 21, 'PRETOT FREYRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2997, 21, 'PUEBLO ITALIANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2998, 21, 'PUEBLO VIEJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (2999, 21, 'PUERTA COLORADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3000, 21, 'PUESTO DE AFUERA ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3001, 21, 'PUESTO DE CASTRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3002, 21, 'PUESTO DEL ROSARIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3003, 21, 'PUESTO PUCHETA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3004, 21, 'PUESTO SAN JOSE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3005, 21, 'PUNILLA ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3006, 21, 'PUNTA DEL AGUA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3007, 21, 'PUNTA DEL AGUA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3008, 21, 'PUNTA DEL AGUA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3009, 21, 'QUEBRACHO HERRADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3010, 21, 'QUEBRADA DE LOS POZOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3011, 21, 'QUILINO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3012, 21, 'QUISQUIZACATE ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3013, 21, 'RANGEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3014, 21, 'RANQUELES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3015, 21, 'RARA FORTUNA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3016, 21, 'RAYO CORTADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3017, 21, 'RINCON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3018, 21, 'RINCON DE LUNA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3019, 21, 'RIO BAMBA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3020, 21, 'RIO CEBALLOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3021, 21, 'RIO CUARTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3022, 21, 'RIO CUARTO NORTE ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3023, 21, 'RIO DE LOS MOLINOS ,PDA.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3024, 21, 'RIO DE LOS SAUCES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3025, 21, 'RIO HONDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3026, 21, 'RIO PRIMERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3027, 21, 'RIO SEGUNDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3028, 21, 'RIO TERCERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3029, 21, 'RIVERA INDARTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3030, 21, 'ROCA, ALEJANDRO ,EST.ALEJANDRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3031, 21, 'RODEO GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3032, 21, 'RODEO VIEJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3033, 21, 'RODRIGUEZ DEL BUSTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3034, 21, 'ROJAS, DIEGO DE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3035, 21, 'ROSALES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3036, 21, 'ROSALES, BLAS DE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3037, 21, 'RUIZ DIAZ DE GUZMAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3038, 21, 'RUMIYACO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3039, 21, 'SACANTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3040, 21, 'SAGUION', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3041, 21, 'SAIRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3042, 21, 'SALADILLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3043, 21, 'SALAS, MIGUEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3044, 21, 'SALDAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3045, 21, 'SALGUERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3046, 21, 'SALSACATE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3047, 21, 'SALSIPUEDES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3048, 21, 'SAMPACHO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3049, 21, 'SAN AGUSTIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3050, 21, 'SAN AGUSTIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3051, 21, 'SAN AMBROSIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3052, 21, 'SAN ANTINIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3053, 21, 'SAN ANTONIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3054, 21, 'SAN ANTONIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3055, 21, 'SAN ANTONIO DE ARREDONDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3056, 21, 'SAN ANTONIO DE LITIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3057, 21, 'SAN BARTOLOME', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3058, 21, 'SAN BASILIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3059, 21, 'SAN BUENAVENTURA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3060, 21, 'SAN BUENAVENTURA ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3061, 21, 'SAN CARLOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3062, 21, 'SAN CARLOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3063, 21, 'SAN CLEMENTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3064, 21, 'SAN ESTEBAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3065, 21, 'SAN FERNANDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3066, 21, 'SAN FRANCISCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3067, 21, 'SAN FRANCISCO DEL CHAÃAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3068, 21, 'SAN GERONIMO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3069, 21, 'SAN IGNACIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3070, 21, 'SAN IGNACIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3071, 21, 'SAN JAVIER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3072, 21, 'SAN JOAQUIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3073, 21, 'SAN JOSE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3074, 21, 'SAN JOSE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3075, 21, 'SAN JOSE DE LA DORMIDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3076, 21, 'SAN JOSE DE LAS SALINAS ,EST.SAN JOSE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3077, 21, 'SAN JOSE DEL SALTEÃO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3078, 21, 'SAN LORENZO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3079, 21, 'SAN LUIS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3080, 21, 'SAN MARCOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3081, 21, 'SAN MARCOS SIERRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3082, 21, 'SAN MIGUEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3083, 21, 'SAN PEDRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3084, 21, 'SAN PEDRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3085, 21, 'SAN PEDRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3086, 21, 'SAN PEDRO DE TOYOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3087, 21, 'SAN PEDRO NORTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3088, 21, 'SAN RAFAEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3089, 21, 'SAN ROQUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3090, 21, 'SAN SEVERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3091, 21, 'SAN VICENTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3092, 21, 'SANABRIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3093, 21, 'SANTA ANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3094, 21, 'SANTA CATALINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3095, 21, 'SANTA CATALINA ,EST.HOLMBERG', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3096, 21, 'SANTA CRUZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3097, 21, 'SANTA EUFEMIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3098, 21, 'SANTA ISABEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3099, 21, 'SANTA MAGDALENA ,EST.JOVITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3100, 21, 'SANTA MARIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3101, 21, 'SANTA MARIA DE PUNILLA ,EST.SANTA MARIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3102, 21, 'SANTA ROSA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3103, 21, 'SANTA ROSA DE CALAMUCHITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3104, 21, 'SANTA ROSA DE RIO PRIMERO ,EST.V.S.ROSA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3105, 21, 'SANTA SABINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3106, 21, 'SANTA TERESA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3107, 21, 'SANTA VICTORIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3108, 21, 'SARMIENTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3109, 21, 'SAUCE DE LOS QUEVEDOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3110, 21, 'SEEBER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3111, 21, 'SERRANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3112, 21, 'SERREZUELA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3113, 21, 'SIMBOLAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3114, 21, 'SINSACATE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3115, 21, 'SOCONCHO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3116, 21, 'SOTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3117, 21, 'SUCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3118, 21, 'SUQUIA ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3119, 21, 'SVILLA NUEVA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3120, 21, 'TABAQUILLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3121, 21, 'TALA CAÃADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3122, 21, 'TALA CRUZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3123, 21, 'TALA NORTE ,EST.EL ALCALDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3124, 21, 'TALAINI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3125, 21, 'TANCACHA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3126, 21, 'TANTI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3127, 21, 'TANTI NUEVO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3128, 21, 'TASMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3129, 21, 'TEGUA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3130, 21, 'TEMPLE, SANTIAGO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3131, 21, 'THEA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3132, 21, 'TICINO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3133, 21, 'TILQUICHO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3134, 21, 'TINOCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3135, 21, 'TIO PUJIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3136, 21, 'TOLEDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3137, 21, 'TOSNO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3138, 21, 'TOSQUITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3139, 21, 'TOTORALEJOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3140, 21, 'TRANSITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3141, 21, 'TRAVESIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3142, 21, 'TRES ARBOLES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3143, 21, 'TRINCHERA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3144, 21, 'TUCLAME', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3145, 21, 'UCACHA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3146, 21, 'UNQUILLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3147, 21, 'VALLE HERMOSO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3148, 21, 'VELEZ SARSFIELD, DALMACIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3149, 21, 'VELEZ SARSFIELD, DALMACIO ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3150, 21, 'VIAMONTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3151, 21, 'VICUÃA MACKENNA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3152, 21, 'VILLA AIZACATE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3153, 21, 'VILLA ALICIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3154, 21, 'VILLA ALLENDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3155, 21, 'VILLA ASCASUBI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3156, 21, 'VILLA BUSTOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3157, 21, 'VILLA CANDELARIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3158, 21, 'VILLA CARLOS PAZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3159, 21, 'VILLA CERRO AZUL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3160, 21, 'VILLA COLANCHANGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3161, 21, 'VILLA COLONIA ITALIANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3162, 21, 'VILLA CONCEPCION DEL TIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3163, 21, 'VILLA CORAZON DE MARIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3164, 21, 'VILLA CURA BROCHERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3165, 21, 'VILLA DE LAS ROSAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3166, 21, 'VILLA DE MARIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3167, 21, 'VILLA DE SOTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3168, 21, 'VILLA DEL DIQUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3169, 21, 'VILLA DEL LAGO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3170, 21, 'VILLA DEL ROSARIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3171, 21, 'VILLA DEL TOTORAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3172, 21, 'VILLA DEL TRANSITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3173, 21, 'VILLA DOLORES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3174, 21, 'VILLA ELISA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3175, 21, 'VILLA ESQUIU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3176, 21, 'VILLA FONTANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3177, 21, 'VILLA GARCIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3178, 21, 'VILLA GENERAL BELGRANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3179, 21, 'VILLA GIARDINO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3180, 21, 'VILLA GUTIERREZ ,EMB. KM. 807', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3181, 21, 'VILLA HUIDOBRO ,EST.CAÃADA VERDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3182, 21, 'VILLA ICHO CRUZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3183, 21, 'VILLA INDEPENDENCIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3184, 21, 'VILLA LOS MOLINOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3185, 21, 'VILLA MARIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3186, 21, 'VILLA MODERNA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3187, 21, 'VILLA ÃU PORA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3188, 21, 'VILLA QUILINO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3189, 21, 'VILLA RAFAEL BENEGAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3190, 21, 'VILLA REDUCCION', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3191, 21, 'VILLA ROSARIO DEL SALADILLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3192, 21, 'VILLA ROSSI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3193, 21, 'VILLA RUMIPAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3194, 21, 'VILLA SAN ANTONIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3195, 21, 'VILLA SAN ESTEBAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3196, 21, 'VILLA SARMIENTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3197, 21, 'VILLA TULUMBA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3198, 21, 'VILLA VALERIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3199, 21, 'VILLA VAUDAGNA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3200, 21, 'VILLA VISO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3201, 21, 'VIVAS, PEDRO E. ,EMB.KILOMETRO 658', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3202, 21, 'VIVERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3203, 21, 'WASHINGTON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3204, 21, 'WATT ,EST.MELIDEO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3205, 21, 'YACANTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3206, 21, 'YACANTO DE CALAMUCHITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3207, 21, 'YOCSINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3208, 21, 'ZUMARAN, ANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3209, 1, 'SAENZ PEÃA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3210, 4, '20 DE FEBRERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3211, 4, 'ACAMBUCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3212, 4, 'AGUA BLANCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3213, 4, 'AGUA SUCIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3214, 4, 'AGUARAY ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3215, 4, 'ALEMANIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3216, 4, 'ALGARROBAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3217, 4, 'ALMIRANTE BROWN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3218, 4, 'ALTO DE FLORES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3219, 4, 'ALTO DEL MISTOL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3220, 4, 'AMBLAYO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3221, 4, 'AMPASCACHI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3222, 4, 'ANGASTACO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3223, 4, 'ANGOSTURA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3224, 4, 'ANIMANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3225, 4, 'ANTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3226, 4, 'ANTILLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3227, 4, 'ANTONIO QUIJARRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3228, 4, 'APOLINARIO SARAVIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3229, 4, 'ARENAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3230, 4, 'BAJO GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3231, 4, 'BALBOA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3232, 4, 'BELLA VISTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3233, 4, 'BETANIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3234, 4, 'CABEZA DE BUEY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3235, 4, 'CACHI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3236, 4, 'CACHIÃAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3237, 4, 'CACHIPAMPA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3238, 4, 'CAFAYATE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3239, 4, 'CAIPE ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3240, 4, 'CAMPICHUELO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3241, 4, 'CAMPO ALEGRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3242, 4, 'CAMPO CASEROS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3243, 4, 'CAMPO DURAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3244, 4, 'CAMPO GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3245, 4, 'CAMPO QUIJANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3246, 4, 'CAMPO SANTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3247, 4, 'CAPITAN JUAN PAGE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3248, 4, 'CASTAÃARES ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3249, 4, 'CEIBALITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3250, 4, 'CERRILLOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3251, 4, 'CHAGUARAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3252, 4, 'CHAÃAR MUYO ,EST.CNL.OLLEROS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3253, 4, 'CHICOANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3254, 4, 'CHICOANA ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3255, 4, 'CHORRILLOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3256, 4, 'CHORROARIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3257, 4, 'CHUCULAQUI ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3258, 4, 'COBRES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3259, 4, 'COCHABAMBA ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3260, 4, 'COLONIA SANTA ROSA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3261, 4, 'COPO QUILE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3262, 4, 'CORONEL CORNEJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3263, 4, 'CORONEL JUAN SOLA ,EST.MORILLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3264, 4, 'CORONEL MOLDES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3265, 4, 'CORONEL MOLLINEDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3266, 4, 'CORONEL VIDT', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3267, 4, 'CORRALITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3268, 4, 'CORRALITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3269, 4, 'CRUZ QUEMADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3270, 4, 'DE ALMAGRO, DIEGO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3271, 4, 'DOCTOR FACUNDO ZUVIRIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3272, 4, 'DRAGONES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3273, 4, 'EL AIBAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3274, 4, 'EL ALISAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3275, 4, 'EL BORDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3276, 4, 'EL BORDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3277, 4, 'EL BOTIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3278, 4, 'EL CARRIL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3279, 4, 'EL CEBILLAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3280, 4, 'EL CEIBALITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3281, 4, 'EL DURAZNO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3282, 4, 'EL ENCON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3283, 4, 'EL GALPON ,EST.FOGUISTA JORGE.F.SUAREZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3284, 4, 'EL NARANJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3285, 4, 'EL PAJEAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3286, 4, 'EL PIQUETE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3287, 4, 'EL PUCARA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3288, 4, 'EL QUEBRACHAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3289, 4, 'EL SALADILLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3290, 4, 'EL TALA ,EST.RUIZ DE LOS LLANOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3291, 4, 'EL TUNAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3292, 4, 'EL TUNAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3293, 4, 'EMBARCACION', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3294, 4, 'ESCOIPE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3295, 4, 'ESQUINA DE QUISTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3296, 4, 'ESTECO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3297, 4, 'GAONA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3298, 4, 'GENERAL ALVARADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3299, 4, 'GENERAL BALLIVIAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3300, 4, 'GENERAL GUEMES ,EST.GUEMES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3301, 4, 'GENERAL MOSCONI ,EST.VESPUCIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3302, 4, 'GENERAL PIZARRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3303, 4, 'GOBERNADOR MANUEL SOLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3304, 4, 'GONZALEZ, Â°JOAQUIN V.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3305, 4, 'GUACHIPAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3306, 4, 'GUAYACAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3307, 4, 'HICKMANN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3308, 4, 'HORCONES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3309, 4, 'INCAHUASI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3310, 4, 'INGENIERO MAURY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3311, 4, 'IRUYA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3312, 4, 'JASIMANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3313, 4, 'JURAMENTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3314, 4, 'KILOMETRO 1088 ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3315, 4, 'KILOMETRO 1104 ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3316, 4, 'KILOMETRO 1125 ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3317, 4, 'KILOMETRO 1129 ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3318, 4, 'KILOMETRO 1152 ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3319, 4, 'KILOMETRO 1156 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3320, 4, 'KILOMETRO 1291', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3321, 4, 'KILOMETRO 1291 ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3322, 4, 'KILOMETRO 1299', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3323, 4, 'KILOMETRO 1305 ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3324, 4, 'KILOMETRO 1357', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3325, 4, 'KILOMETRO 1424', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3326, 4, 'KILOMETRO 1448', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3327, 4, 'KILOMETRO 1506', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3328, 4, 'LA CALDERA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3329, 4, 'LA CANDELARIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3330, 4, 'LA CANDELARIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3331, 4, 'LA CORZUELA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3332, 4, 'LA ESTRELLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3333, 4, 'LA FLORIDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3334, 4, 'LA ISLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3335, 4, 'LA MERCED', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3336, 4, 'LA POMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3337, 4, 'LA QUESERA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3338, 4, 'LA ROSITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3339, 4, 'LA SILLETA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3340, 4, 'LA TRAMPA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3341, 4, 'LA UNION', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3342, 4, 'LA VIÃA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3343, 4, 'LAS CAPILLAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3344, 4, 'LAS LAJITAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3345, 4, 'LAS LLAVES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3346, 4, 'LAS MERCEDES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3347, 4, 'LAS MESITAS ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3348, 4, 'LAS PALMAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3349, 4, 'LAS SALADAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3350, 4, 'LAS TORTUGAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3351, 4, 'LAS TRES MARIAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3352, 4, 'LAS VIBORAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3353, 4, 'LORO HUASI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3354, 4, 'LOS BAÃOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3355, 4, 'LOS BLANCOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3356, 4, 'LOS MOGOTES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3357, 4, 'LOS NOGALES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3358, 4, 'LOS PATOS ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3359, 4, 'LOS SAUCES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3360, 4, 'LUMBRERA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3361, 4, 'LURACATAO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3362, 4, 'MACAPILLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3363, 4, 'MARTINEZ DEL TINEO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3364, 4, 'MATORRAS, JERONIMO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3365, 4, 'MESETA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3366, 4, 'METAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3367, 4, 'METAN VIEJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3368, 4, 'MINA CONCORDIA ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3369, 4, 'MINA LA CASUALIDAD', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3370, 4, 'MOJOTORO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3371, 4, 'MOLINOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3372, 4, 'MONTEVERDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3373, 4, 'MORALES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3374, 4, 'MORENILLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3375, 4, 'MUÃANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3376, 4, 'NAZARENO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3377, 4, 'NUESTRA SEÃORA DE TALAVERA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3378, 4, 'OSMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3379, 4, 'OSMA ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3380, 4, 'OVANDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3381, 4, 'PADRE LOZANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3382, 4, 'PALERMO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3383, 4, 'PALOMITAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3384, 4, 'PAMPA GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3385, 4, 'PATRON COSTAS, LUIS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3386, 4, 'PAYOGASTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3387, 4, 'PICHANAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3388, 4, 'PIQUETE CABADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3389, 4, 'PIQUIRENDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3390, 4, 'PLUMA DEL PATO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3391, 4, 'POTRERILLOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3392, 4, 'POTRERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3393, 4, 'POTRERO DE URIBURU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3394, 4, 'POZO BRAVO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3395, 4, 'POZO CERRADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3396, 4, 'POZO DE LA ESQUINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3397, 4, 'PROFESOR SALVADOR MAZZA ,EST.POCITOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3398, 4, 'PUCARA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3399, 4, 'PUEBLO VIEJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3400, 4, 'PUENTE DE PLATA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3401, 4, 'PUERTA DE DIAZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3402, 4, 'PUERTA TASTIL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3403, 4, 'PUERTO LA PAZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3404, 4, 'PUNTA DEL AGUA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3405, 4, 'QUEBRADA DEL AGUA ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3406, 4, 'QUIJARRO, ANTONIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3407, 4, 'RIO ANCHO ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3408, 4, 'RIO DEL VALLE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3409, 4, 'RIO PESCADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3410, 4, 'RIO PIEDRAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3411, 4, 'RIO SECO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3412, 4, 'RIO URUEÃA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3413, 4, 'RIVADAVIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3414, 4, 'ROSARIO DE LA FRONTERA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3415, 4, 'ROSARIO DE LERMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3416, 4, 'ROSARIO DEL DORADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3417, 4, 'SALAR DE POCITOS ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3418, 4, 'SALTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3419, 4, 'SAN AGUSTIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3420, 4, 'SAN ANTONIO DE LOS COBRES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3421, 4, 'SAN CARLOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3422, 4, 'SAN JOSE DE ESCALCHI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3423, 4, 'SAN LORENZO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3424, 4, 'SAN NICOLAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3425, 4, 'SAN PEDRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3426, 4, 'SAN RAMON DE LA NUEVA ORAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3427, 4, 'SANTA MARIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3428, 4, 'SANTA ROSA DE LOS PASTOS GRANDES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3429, 4, 'SANTA ROSA DE TASTIL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3430, 4, 'SANTA VICTORIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3431, 4, 'SANTA VICTORIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3432, 4, 'SANTO DOMINGO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3433, 4, 'SARAVIA, APOLINARIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3434, 4, 'SAUCELITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3435, 4, 'SCHNEIDEWIND', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3436, 4, 'SECLANTAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3437, 4, 'SENDA HACHADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3438, 4, 'SENILLOSA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3439, 4, 'SOCOMPA ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3440, 4, 'SUNCHAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3441, 4, 'TABACAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3442, 4, 'TACA TACA ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3443, 4, 'TACUARA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3444, 4, 'TACUY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3445, 4, 'TALAPAMPA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3446, 4, 'TARTAGAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3447, 4, 'TEDIN, VIRGILIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3448, 4, 'TOBANTIRENDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3449, 4, 'TOLAR GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3450, 4, 'TOLLOCHE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3451, 4, 'TOLOMBON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3452, 4, 'TONCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3453, 4, 'TONONO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3454, 4, 'TRES YUCHANES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3455, 4, 'UNQUILLAL ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3456, 4, 'URIZAR, ESTEBAN DE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3457, 4, 'URUNDEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3458, 4, 'VAQUEROS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3459, 4, 'VEGA DE ARZARO ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3460, 4, 'VILLA SARMIENTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3461, 4, 'VIRREY TOLEDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3462, 4, 'VUELTA DE LOS TOBAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3463, 4, 'YARIGUARENDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3464, 4, 'YATASTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3465, 4, 'YUCHAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3466, 8, '7 DE ABRIL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3467, 8, 'ACEQUIONES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3468, 8, 'ACHERAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3469, 8, 'AGUA DULCE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3470, 8, 'AGUA DULCE ,EMP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3471, 8, 'AGUILARES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3472, 8, 'ALDERETES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3473, 8, 'ALPACHIRI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3474, 8, 'ALTA GRACIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3475, 8, 'ALTO DE MEDINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3476, 8, 'ALTO VERDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3477, 8, 'AMAICHA DEL VALLE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3478, 8, 'AMBERES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3479, 8, 'AMPATA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3480, 8, 'ANCAJULI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3481, 8, 'ANFAMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3482, 8, 'ARAOZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3483, 8, 'ARBOLES GRANDES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3484, 8, 'ARCADIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3485, 8, 'ARROYO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3486, 8, 'ATAHONA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3487, 8, 'BAJASTINE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3488, 8, 'BALDERRAMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3489, 8, 'BANDA DEL RIO SALI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3490, 8, 'BELICHA HUAICO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3491, 8, 'BELLA VISTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3492, 8, 'BENJAMIN ARAOZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3493, 8, 'BENJAMIN PAZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3494, 8, 'BURRUYACU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3495, 8, 'BUSTAMANTE ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3496, 8, 'CACHI HUASI ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3497, 8, 'CACHI YACO ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3498, 8, 'CAMPO BELLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3499, 8, 'CAÃETE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3500, 8, 'CEVIL POZO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3501, 8, 'CEVIL REDONDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3502, 8, 'CHABELA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3503, 8, 'CHAÃARITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3504, 8, 'CHICLIGASTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3505, 8, 'CHILCAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3506, 8, 'CHOROMORO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3507, 8, 'CHORRILLOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3508, 8, 'CHUANTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3509, 8, 'CIUDACITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3510, 8, 'CIUDAD HOSPITAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3511, 8, 'CIUDAD UNIVERSITARIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3512, 8, 'COCHUNA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3513, 8, 'COLALAO DEL VALLE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3514, 8, 'COLOMBRES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3515, 8, 'COLONIA NUEVA TRINIDAD', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3516, 8, 'COLONIA SAN JORGE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3517, 8, 'COLONIA SAN JOSE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3518, 8, 'CONCEPCION', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3519, 8, 'CRUZ DEL NORTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3520, 8, 'DIQUE EL CADILLAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3521, 8, 'EL ASERRADERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3522, 8, 'EL BAÃADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3523, 8, 'EL BARCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3524, 8, 'EL BRACHO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3525, 8, 'EL CADILLAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3526, 8, 'EL CHAÃAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3527, 8, 'EL COLMENAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3528, 8, 'EL DURAZNITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3529, 8, 'EL GUARDAMONTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3530, 8, 'EL JARDIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3531, 8, 'EL MOLINO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3532, 8, 'EL MOLLAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3533, 8, 'EL NARANJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3534, 8, 'EL OJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3535, 8, 'EL PORVENIR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3536, 8, 'EL SUNCHAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3537, 8, 'EL ZAPALLAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3538, 8, 'ENCRUCIJADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3539, 8, 'ESCABA ABAJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3540, 8, 'ESCABA ARRIBA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3541, 8, 'ESQUINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3542, 8, 'FAMAILLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3543, 8, 'FINCA ELISA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3544, 8, 'GALLO, DELFIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3545, 8, 'GARCIA FERNANDEZ, MANUEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3546, 8, 'GASTONA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3547, 8, 'GOBERNADOR GARMENDIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3548, 8, 'GOBERNADOR NOUGUES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3549, 8, 'GOBERNADOR PIEDRABUENA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3550, 8, 'GRANEROS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3551, 8, 'GUZMAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3552, 8, 'HUASA PAMPA NORTE ,EST.HUASA PAMPA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3553, 8, 'HUASA PAMPA SUR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3554, 8, 'ICHIPUCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3555, 8, 'INGENIO AMALIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3556, 8, 'INGENIO CONCEPCION', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3557, 8, 'INGENIO CRUZ ALTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3558, 8, 'INGENIO LA CORONA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3559, 8, 'INGENIO LA ESPERANZA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3560, 8, 'INGENIO LA FLORIDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3561, 8, 'INGENIO LA FRONTERITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3562, 8, 'INGENIO LASTENIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3563, 8, 'INGENIO LULES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3564, 8, 'INGENIO LULES ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3565, 8, 'INGENIO SAN JOSE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3566, 8, 'INGENIO SAN MIGUEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3567, 8, 'INGENIO SAN PABLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3568, 8, 'INGENIO SANTA ANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3569, 8, 'INGENIO SANTA LUCIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3570, 8, 'KILOMETRO 10 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3571, 8, 'KILOMETRO 102 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3572, 8, 'KILOMETRO 1185 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3573, 8, 'KILOMETRO 1194 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3574, 8, 'KILOMETRO 1207 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3575, 8, 'KILOMETRO 1213 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3576, 8, 'KILOMETRO 1235 ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3577, 8, 'KILOMETRO 1248 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3578, 8, 'KILOMETRO 1254 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3579, 8, 'KILOMETRO 1256 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3580, 8, 'KILOMETRO 29 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3581, 8, 'KILOMETRO 36 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3582, 8, 'KILOMETRO 46 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3583, 8, 'KILOMETRO 5 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3584, 8, 'KILOMETRO 55 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3585, 8, 'KILOMETRO 770 ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3586, 8, 'KILOMETRO 781 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3587, 8, 'KILOMETRO 784 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3588, 8, 'KILOMETRO 794 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3589, 8, 'KILOMETRO 808 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3590, 8, 'KILOMETRO 810 ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3591, 8, 'KILOMETRO 825', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3592, 8, 'KILOMETRO 847 ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3593, 8, 'KILOMETRO 99,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3594, 8, 'LA ANGOSTURA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3595, 8, 'LA CAÃADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3596, 8, 'LA COCHA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3597, 8, 'LA CRUZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3598, 8, 'LA ENCANTADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3599, 8, 'LA FALDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3600, 8, 'LA HIGUERA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3601, 8, 'LA INVERNADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3602, 8, 'LA MADRID', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3603, 8, 'LA POSTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3604, 8, 'LA RAMADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3605, 8, 'LA REDUCCION', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3606, 8, 'LA RINCONADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3607, 8, 'LA TIPA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3608, 8, 'LA TRINIDAD', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3609, 8, 'LAGUNA DE ROBLES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3610, 8, 'LAS CEJAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3611, 8, 'LAS GUCHAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3612, 8, 'LAS PIEDRITAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3613, 8, 'LEALES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3614, 8, 'LILLO, MIGUEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3615, 8, 'LOMA VERDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3616, 8, 'LOPEZ DOMINGUEZ ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3617, 8, 'LOS AGUIRRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3618, 8, 'LOS CHAÃARES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3619, 8, 'LOS GOMEZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3620, 8, 'LOS GUTIERREZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3621, 8, 'LOS PEREYRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3622, 8, 'LOS PUESTOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3623, 8, 'LOS RALOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3624, 8, 'LOS SARMIENTOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3625, 8, 'LOS SOSA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3626, 8, 'LUISIANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3627, 8, 'LULES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3628, 8, 'LUNAREJOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3629, 8, 'MACIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3630, 8, 'MACOMITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3631, 8, 'MALVINAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3632, 8, 'MANANTIAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3633, 8, 'MARIÃO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3634, 8, 'MATE DE LUNA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3635, 8, 'MEDINAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3636, 8, 'MENDEZ, PEDRO G.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3637, 8, 'MILLAN, DOMINGO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3638, 8, 'MIRAFLORES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3639, 8, 'MIXTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3640, 8, 'MONTE GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3641, 8, 'MONTEAGUDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3642, 8, 'MONTEROS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3643, 8, 'MUNDO NUEVO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3644, 8, 'MUÃECAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3645, 8, 'NARANJITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3646, 8, 'NUEVA ESPAÃA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3647, 8, 'NUEVA TRINIDAD', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3648, 8, 'ORAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3649, 8, 'PACARA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3650, 8, 'PADILLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3651, 8, 'PALA PALA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3652, 8, 'PAZ, BENJAMIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3653, 8, 'PAZ, LEOCADIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3654, 8, 'PAZ, MARCOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3655, 8, 'PEDRAZA, MANUELA ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3656, 8, 'POLITO ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3657, 8, 'POTRERO DE LAS TABLAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3658, 8, 'POZO DEL ALTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3659, 8, 'PUEBLO VIEJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3660, 8, 'PUERTA DE SAN JAVIER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3661, 8, 'RACO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3662, 8, 'RANCHILLOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3663, 8, 'REQUELME', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3664, 8, 'RIO CHICO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3665, 8, 'RIO COLORADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3666, 8, 'RIO DEL NIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3667, 8, 'RIO SECO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3668, 8, 'ROMERO POZO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3669, 8, 'ROUGES, LEON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3670, 8, 'RUMI PUNCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3671, 8, 'SAN AGUSTIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3672, 8, 'SAN ANDRES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3673, 8, 'SAN FELIPE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3674, 8, 'SAN IGNACIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3675, 8, 'SAN JAVIER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3676, 8, 'SAN JOSE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3677, 8, 'SAN MIGUEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3678, 8, 'SAN MIGUEL DE TUCUMAN ,EST.TUCUMAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3679, 8, 'SAN PABLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3680, 8, 'SAN PATRICIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3681, 8, 'SAN PEDRO DE COLALAO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3682, 8, 'SAN RAFAEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3683, 8, 'SAN VICENTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3684, 8, 'SANTA BARBARA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3685, 8, 'SANTA CRUZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3686, 8, 'SANTA ROSA DE LEALES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3687, 8, 'SIAMBON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3688, 8, 'SIMOCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3689, 8, 'SINQUIEAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3690, 8, 'SOL DE MAYO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3691, 8, 'SUELDOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3692, 8, 'TACANAS ,EST.SUPERINTENDENTE LEDESMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3693, 8, 'TACO PALTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3694, 8, 'TACO RALO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3695, 8, 'TAFI DEL VALLE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3696, 8, 'TAFI VIEJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3697, 8, 'TAFICILLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3698, 8, 'TALA POZO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3699, 8, 'TAPIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3700, 8, 'TARUCA PAMPA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3701, 8, 'TICUCHO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3702, 8, 'TIMBO NUEVO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3703, 8, 'TIMBO VIEJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3704, 8, 'TINAJEROS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3705, 8, 'TRANCAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3706, 8, 'TREJOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3707, 8, 'TRES POZOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3708, 8, 'TUNA SOLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3709, 8, 'TUSQUITAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3710, 8, 'URIZAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3711, 8, 'UTURUNCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3712, 8, 'VILLA CARMELA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3713, 8, 'VILLA LUJAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3714, 8, 'VILLA NUEVA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3715, 8, 'VILLA PADRE MONTI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3716, 8, 'VILLA QUINTEROS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3717, 8, 'VILLA RESCATE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3718, 8, 'VIPOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3719, 8, 'YACUCHINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3720, 8, 'YANIMAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3721, 8, 'YERBA BUENA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3722, 23, '23 DE AGOSTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3723, 23, 'ABDON CASTRO TOLAY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3724, 23, 'ABRA LAITE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3725, 23, 'ABRA PAMPA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3726, 23, 'AGUA CALIENTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3727, 23, 'AGUA CALIENTE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3728, 23, 'AGUA CHICA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3729, 23, 'AGUADITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3730, 23, 'AGUAS CALIENTES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3731, 23, 'ALTO LA TORRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3732, 23, 'ALTO LA VIÃA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3733, 23, 'BORDO LA ISLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3734, 23, 'CAIMANCITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3735, 23, 'CALILEGUA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3736, 23, 'CANGREJILLOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3737, 23, 'CAPLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3738, 23, 'CARACARA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3739, 23, 'CARAHUASI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3740, 23, 'CASABINDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3741, 23, 'CASPALA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3742, 23, 'CASTRO TOLAY, ABDON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3743, 23, 'CATUA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3744, 23, 'CERRILLOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3745, 23, 'CHALICAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3746, 23, 'CHAÃI ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3747, 23, 'CHAUPI RODERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3748, 23, 'CHURQUI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3749, 23, 'CIENAGUILLAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3750, 23, 'COCHINOCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3751, 23, 'CONDOR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3752, 23, 'CORANZULI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3753, 23, 'CORAYA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3754, 23, 'CORRAL BLANCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3755, 23, 'EL AGUILAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3756, 23, 'EL BRETE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3757, 23, 'EL CARMEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3758, 23, 'EL CARRIL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3759, 23, 'EL CEIBAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3760, 23, 'EL MORENO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3761, 23, 'EL PARQUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3762, 23, 'EL PIQUETE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3763, 23, 'EL PORVENIR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3764, 23, 'EL QUEMADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3765, 23, 'EL REMATE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3766, 23, 'EL SUNCHAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3767, 23, 'ESCAYA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3768, 23, 'FRAILE PINTADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3769, 23, 'GALAN, JUAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3770, 23, 'GUERRERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3771, 23, 'HORNILLOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3772, 23, 'HUACALERA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3773, 23, 'HUMAHUACA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3774, 23, 'INGENIO LA ESPERANZA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3775, 23, 'INTI CANCHA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3776, 23, 'ITURBE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3777, 23, 'KILOMETRO 1129 ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3778, 23, 'KILOMETRO 1137 ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3779, 23, 'KILOMETRO 1149 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3780, 23, 'KILOMETRO 1183 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3781, 23, 'KILOMETRO 1210 ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3782, 23, 'KILOMETRO 1243 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3783, 23, 'KILOMETRO 1247', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3784, 23, 'KILOMETRO 1278', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3785, 23, 'KILOMETRO 1289 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3786, 23, 'KILOMETRO 1321 ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3787, 23, 'KILOMETRO 1373 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3788, 23, 'LA ALMONA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3789, 23, 'LA CAPILLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3790, 23, 'LA CUEVA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3791, 23, 'LA MENDIETA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3792, 23, 'LA QUIACA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3793, 23, 'LA TOMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3794, 23, 'LAGUNA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3795, 23, 'LAS CUEVAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3796, 23, 'LEON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3797, 23, 'LIBERTADOR GRL.SAN MARTIN, EST.LEDESMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3798, 23, 'LOS ALISOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3799, 23, 'LOS BLANCOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3800, 23, 'LOS CHANCHILLOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3801, 23, 'LOS LAPACHOS ,EST.MAQUINISTA VERON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3802, 23, 'LOS PERALES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3803, 23, 'MADREJON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3804, 23, 'MAIMARA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3805, 23, 'MEDANITOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3806, 23, 'MINA AJEDREZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3807, 23, 'MINA PIRQUITAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3808, 23, 'MONTE RICO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3809, 23, 'MONTE RICO ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3810, 23, 'OLACAPATO ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3811, 23, 'OLAROZ CHICO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3812, 23, 'OLAROZ GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3813, 23, 'ORATORIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3814, 23, 'OROSMAYO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3815, 23, 'PAIRIQUE CHICO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3816, 23, 'PAIRIQUE GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3817, 23, 'PALCA DE APARZO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3818, 23, 'PALMA SOLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3819, 23, 'PALPALA ,EST.GRL.MANUEL N.SAVIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3820, 23, 'PAMPA BLANCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3821, 23, 'PAMPICHUELA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3822, 23, 'PERICO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3823, 23, 'PUEBLO LEDESMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3824, 23, 'PUEBLO VIEJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3825, 23, 'PUESTO DEL MARQUES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3826, 23, 'PUESTO GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3827, 23, 'PUMAHUASI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3828, 23, 'PURMAMARCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3829, 23, 'QUICHAGUA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3830, 23, 'REYES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3831, 23, 'RINCONADA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3832, 23, 'RINCONADILLAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3833, 23, 'RIO BLANCO ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3834, 23, 'RIO GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3835, 23, 'RODERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3836, 23, 'ROSARIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3837, 23, 'ROSARIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3838, 23, 'SAN ANTONIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3839, 23, 'SAN BERNARDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3840, 23, 'SAN JAVIER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3841, 23, 'SAN JUAN DE DIOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3842, 23, 'SAN JUAN DE ORO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3843, 23, 'SAN JUAN DE PALCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3844, 23, 'SAN JUANCITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3845, 23, 'SAN JUANCITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3846, 23, 'SAN PEDRITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3847, 23, 'SAN PEDRO ,EST.SAN PEDRO DE JUJUY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3848, 23, 'SAN SALVADOR DE JUJUY ,EST.JUJUY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3849, 23, 'SANTA ANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3850, 23, 'SANTA BARBARA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3851, 23, 'SANTA CATALINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3852, 23, 'SANTA CLARA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3853, 23, 'SANTO DOMINGO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3854, 23, 'SAYATE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3855, 23, 'SENADOR PEREZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3856, 23, 'SEY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3857, 23, 'SIBERIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3858, 23, 'SOL DE MAYO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3859, 23, 'SURIPUGIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3860, 23, 'SUSQUES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3861, 23, 'TABLADITAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3862, 23, 'TAFNA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3863, 23, 'TERMAS DE REYES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3864, 23, 'TILCARA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3865, 23, 'TIMON CRUZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3866, 23, 'TIOMAYO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3867, 23, 'TIONSO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3868, 23, 'TRES CRUCES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3869, 23, 'TRES MORROS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3870, 23, 'TUMBAYA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3871, 23, 'TURU TARI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3872, 23, 'TUSAQUILLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3873, 23, 'UQUIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3874, 23, 'VALLE COLORADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3875, 23, 'VALLE GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3876, 23, 'VILLA ACHAVAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3877, 23, 'VOLCAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3878, 23, 'YALA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3879, 23, 'YAVI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3880, 23, 'YOSCABA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3881, 23, 'YUTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3882, 23, 'ZAPLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3883, 10, '28 DE NOVIEMBRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3884, 10, 'AGUADA ALEGRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3885, 10, 'AGUADA ESCONDIDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3886, 10, 'AGUADA GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3887, 10, 'ALMA GAUCHA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3888, 10, 'ALQUINTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3889, 10, 'ANTONIO DE BIEDMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3890, 10, 'ARISTIZABAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3891, 10, 'BAHIA LAURA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3892, 10, 'BAHIA OSO MARINO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3893, 10, 'BAJO DE LOS CARACOLES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3894, 10, 'BAJO FUEGO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3895, 10, 'BAJO PIEDRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3896, 10, 'BELLA VISTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3897, 10, 'BELLA VISTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3898, 10, 'BELLA VISTA ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3899, 10, 'CABO BLANCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3900, 10, 'CABO BUEN TIEMPO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3901, 10, 'CABO VIRGENES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3902, 10, 'CALAFATE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3903, 10, 'CALETA OLIVIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3904, 10, 'CAÃADON 11 DE SEPTIEMBRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3905, 10, 'CAÃADON SECO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3906, 10, 'CANCHA CARRERAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3907, 10, 'CAPITAN EYROA ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3908, 10, 'CERRO BLANCO ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3909, 10, 'COLONIA PERITO MORENO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3910, 10, 'COMANDANTE LUIS PIEDRA BUENA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3911, 10, 'COMANDANTE LUIS PIEDRA BUENA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3912, 10, 'COY AIKE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3913, 10, 'DE BIEDMA, ANTONIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3914, 10, 'DUFOUR, JULIA ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3915, 10, 'EL CALAFATE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3916, 10, 'EL CERRITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3917, 10, 'EL CONDOR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3918, 10, 'EL PLUMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3919, 10, 'EL PORTEZUELO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3920, 10, 'EL SALADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3921, 10, 'EL TURBIO ,EST.GOBERNADOR MEYER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3922, 10, 'EL VOLCAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3923, 10, 'EL ZURDO ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3924, 10, 'ESPERANZA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3925, 10, 'FITZ ROY', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3926, 10, 'FLECHA NEGRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3927, 10, 'FLORIDA NEGRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3928, 10, 'FUENTES DEL COYLE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3929, 10, 'FUHR, CHARLES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3930, 10, 'GENDARME BARRETO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3931, 10, 'GOBERNADOR GREGORES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3932, 10, 'GOBERNADOR LISTA ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3933, 10, 'GOBERNADOR MAYER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3934, 10, 'GOBERNADOR MOYANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3935, 10, 'GOBERNADOR MOYANO ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3936, 10, 'GUER AIKE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3937, 10, 'HILL STATION', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3938, 10, 'JARAMILLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3939, 10, 'KILOMETRO 199 ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3940, 10, 'KOLUEL KAYKE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3941, 10, 'LA ARAGONESA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3942, 10, 'LA MARIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3943, 10, 'LAGO CARDIEL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3944, 10, 'LAGO POSADAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3945, 10, 'LAGO SAN MARTIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3946, 10, 'LAGO VIEDMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3947, 10, 'LAGUNA GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3948, 10, 'LAI AIKE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3949, 10, 'LAS HERAS ,EST.COLONIA LAS HERAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3950, 10, 'LAS TRES HERMANAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3951, 10, 'LISTA, RAMON ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3952, 10, 'LOS ANTIGUOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3953, 10, 'LOS MONOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3954, 10, 'MATA AMARILLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3955, 10, 'MATA GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3956, 10, 'MAZARREDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3957, 10, 'MINERALES ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3958, 10, 'MONTE AYMOND', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3959, 10, 'MONTE DINERO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3960, 10, 'MONTE LEON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3961, 10, 'PAMPA ALTA ,EMB.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3962, 10, 'PASO GREGORES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3963, 10, 'PASO RODOLFO ROBALLOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3964, 10, 'PERITO MORENO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3965, 10, 'PICO TRUNCADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3966, 10, 'PIEDRA CLAVADA ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3967, 10, 'PUERTO BANDERA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3968, 10, 'PUERTO COIG', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3969, 10, 'PUERTO DESEADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3970, 10, 'PUERTO SAN JULIAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3971, 10, 'PUERTO SANTA CRUZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3972, 10, 'RINCON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3973, 10, 'RIO CHICO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3974, 10, 'RIO GALLEGOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3975, 10, 'RIO TURBIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3976, 10, 'TAMEL AIKE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3977, 10, 'TAPI AIKE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3978, 10, 'TEHUELCHES ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3979, 10, 'TELLIER', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3980, 10, 'TRES CERROS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3981, 10, 'TRES LAGOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3982, 10, 'TUCU TUCU', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3983, 11, 'AGUA BLANCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3984, 11, 'AGUAYO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3985, 11, 'AICUÃA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3986, 11, 'AIMOGASTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3987, 11, 'ALCAZAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3988, 11, 'ALPASINCHE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3989, 11, 'AMANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3990, 11, 'AMBIL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3991, 11, 'AMINGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3992, 11, 'ANDALUCAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3993, 11, 'ANGUINAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3994, 11, 'ANGULOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3995, 11, 'ANILLACO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3996, 11, 'ANIMAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3997, 11, 'ANJULLON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3998, 11, 'ANTINACO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (3999, 11, 'ARAUCO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4000, 11, 'ATILES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4001, 11, 'BAJO DEL GALLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4002, 11, 'BAJO GRANDE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4003, 11, 'BAJO HONDO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4004, 11, 'BANDA FLORIDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4005, 11, 'BAZAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4006, 11, 'BELLA VISTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4007, 11, 'CABOLLAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4008, 11, 'CAMPANAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4009, 11, 'CARRIZAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4010, 11, 'CARRIZAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4011, 11, 'CARRIZAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4012, 11, 'CASTRO BARROS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4013, 11, 'CATINZACO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4014, 11, 'CHAMICAL ,EST.GOBERNADOR GORDILLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4015, 11, 'CHAÃAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4016, 11, 'CHAÃARMUYO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4017, 11, 'CHELCOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4018, 11, 'CHEPES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4019, 11, 'CHEPES VIEJO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4020, 11, 'CHILECITO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4021, 11, 'CHUQUIS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4022, 11, 'COCHANGASTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4023, 11, 'COMANDANTE LEAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4024, 11, 'CORRAL DE ISAAC', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4025, 11, 'CORTADERAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4026, 11, 'CUATRO ESQUINAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4027, 11, 'EL ALTO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4028, 11, 'EL BARREAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4029, 11, 'EL CALDEN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4030, 11, 'EL CHIFLON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4031, 11, 'EL CONDADO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4032, 11, 'EL MOLLAR', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4033, 11, 'EL PORTEZUELO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4034, 11, 'EL POTRERILLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4035, 11, 'EL QUEBRACHO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4036, 11, 'EL RODEO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4037, 11, 'EL SUNCHAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4038, 11, 'EL TALA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4039, 11, 'EL VILGO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4040, 11, 'FAMATINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4041, 11, 'GUANDACOL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4042, 11, 'ILISCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4043, 11, 'JAGUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4044, 11, 'KILOMETRO 875 ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4045, 11, 'KILOMETRO 891 ,AP.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4046, 11, 'LA AGUADITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4047, 11, 'LA AGUADITA DE LOS BRIZUELA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4048, 11, 'LA BANDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4049, 11, 'LA CALERA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4050, 11, 'LA CIENAGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4051, 11, 'LA CIENAGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4052, 11, 'LA FALDA DE CITAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4053, 11, 'LA ISLA ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4054, 11, 'LA JARILLA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4055, 11, 'LA MERCED', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4056, 11, 'LA PERSEGUIDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4057, 11, 'LA PUERTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4058, 11, 'LA REPRESA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4059, 11, 'LA RIOJA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4060, 11, 'LAS CLOACAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4061, 11, 'LAS COLORADAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4062, 11, 'LAS PADERCITAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4063, 11, 'LAS TOSCAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4064, 11, 'LOMA BLANCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4065, 11, 'LOMA LARGA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4066, 11, 'LOS AGUIRRES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4067, 11, 'LOS ALANICES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4068, 11, 'LOS BALDES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4069, 11, 'LOS MOGOTES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4070, 11, 'LOS MOLINOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4071, 11, 'LOS PALACIOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4072, 11, 'LOS SARMIENTOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4073, 11, 'LOS TAMBILLOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4074, 11, 'MACHIGASTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4075, 11, 'MAJADITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4076, 11, 'MALANZAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4077, 11, 'MALLIGASTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4078, 11, 'MASCASIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4079, 11, 'MAZAN ,EST.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4080, 11, 'MILAGRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4081, 11, 'MINA LA MEJICANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4082, 11, 'MIRANDA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4083, 11, 'MOLLACO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4084, 11, 'NACATE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4085, 11, 'NONOGASTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4086, 11, 'ÃOQUEBE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4087, 11, 'OBRAJE DE TOSCANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4088, 11, 'OLPAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4089, 11, 'OLTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4090, 11, 'PAGANCILLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4091, 11, 'PAGANZO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4092, 11, 'PATQUIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4093, 11, 'PINCHAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4094, 11, 'PITUIL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4095, 11, 'PORTEZUELO DE ARCE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4096, 11, 'POZO DE AVILA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4097, 11, 'POZO DE PIEDRA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4098, 11, 'PUERTO ALEGRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4099, 11, 'PUERTO ALEGRE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4100, 11, 'PUNTA DE LOS LLANOS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4101, 11, 'REAL DEL CADILLO ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4102, 11, 'RETAMAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4103, 11, 'RIVADAVIA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4104, 11, 'SALADILLO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4105, 11, 'SAN ANTONIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4106, 11, 'SAN ANTONIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4107, 11, 'SAN BLAS', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4108, 11, 'SAN ISIDRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4109, 11, 'SAN JUAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4110, 11, 'SAN PEDRO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4111, 11, 'SAN RAMON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4112, 11, 'SAN ROQUE', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4113, 11, 'SAN SOLANO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4114, 11, 'SANOGASTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4115, 11, 'SANTA CLARA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4116, 11, 'SANTA CRUZ', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4117, 11, 'SANTA CRUZ ,DV.', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4118, 11, 'SANTO DOMINGO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4119, 11, 'SCHAQUI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4120, 11, 'SIERRA BRAVA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4121, 11, 'SOLCA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4122, 11, 'TALAMUYUNA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4123, 11, 'TALVA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4124, 11, 'TAMA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4125, 11, 'TASQUIN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4126, 11, 'TELLO, DESIDERIO', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4127, 11, 'TERMAS SANTA TERESITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4128, 11, 'TOTORAL', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4129, 11, 'TUIZON', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4130, 11, 'ULAPES', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4131, 11, 'VICHIGASTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4132, 11, 'VILLA CASANA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4133, 11, 'VILLA CASTELLI', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4134, 11, 'VILLA MAZAN', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4135, 11, 'VILLA SANAGASTA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4136, 11, 'VILLA SANTA RITA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4137, 11, 'VILLA UNION', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4138, 11, 'VINCHINA', '0', 0);
INSERT INTO ubicacion_localidades VALUES (4139, 14, '20 DE FEBRERO', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (4140, 14, '3 ESQUINAS', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (4141, 14, '9 DE JULIO', '5831', 0);
INSERT INTO ubicacion_localidades VALUES (4142, 14, '9 DE JULIO', '5757', 0);
INSERT INTO ubicacion_localidades VALUES (4143, 14, 'ÃURILAY', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4144, 14, 'ACASAPE', '5722', 0);
INSERT INTO ubicacion_localidades VALUES (4145, 14, 'ADOLFO RODRIGUEZ SAA', '5777', 0);
INSERT INTO ubicacion_localidades VALUES (4146, 14, 'AGUA AMARGA', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4147, 14, 'AGUA DEL PORTEZUELO', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (4148, 14, 'AGUA FRIA', '5831', 0);
INSERT INTO ubicacion_localidades VALUES (4149, 14, 'AGUA HEDIONDA', '5705', 0);
INSERT INTO ubicacion_localidades VALUES (4150, 14, 'AGUA LINDA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4151, 14, 'AGUA SALADA', '5751', 0);
INSERT INTO ubicacion_localidades VALUES (4152, 14, 'AGUA SEBALLE', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4153, 14, 'AGUADA', '5831', 0);
INSERT INTO ubicacion_localidades VALUES (4154, 14, 'AGUADITA', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4155, 14, 'AGUADITAS', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (4156, 14, 'AGUAS DE PIEDRAS', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4157, 14, 'AHI VEREMOS', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4158, 14, 'ALANICES', '5777', 0);
INSERT INTO ubicacion_localidades VALUES (4159, 14, 'ALAZANAS', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4160, 14, 'ALEGRIA', '6389', 0);
INSERT INTO ubicacion_localidades VALUES (4161, 14, 'ALFALAND', '5731', 0);
INSERT INTO ubicacion_localidades VALUES (4162, 14, 'ALGARROBAL', '5705', 0);
INSERT INTO ubicacion_localidades VALUES (4163, 14, 'ALGARROBAL', '5775', 0);
INSERT INTO ubicacion_localidades VALUES (4164, 14, 'ALGARROBITOS', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4165, 14, 'ALGARROBOS GRANDES', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4166, 14, 'ALTA GRACIA', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4167, 14, 'ALTILLO', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4168, 14, 'ALTO', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4169, 14, 'ALTO BLANCO', '5700', 0);
INSERT INTO ubicacion_localidades VALUES (4170, 14, 'ALTO DE LA LEÃA', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4171, 14, 'ALTO DEL LEON', '5722', 0);
INSERT INTO ubicacion_localidades VALUES (4172, 14, 'ALTO DEL MOLLE', '5755', 0);
INSERT INTO ubicacion_localidades VALUES (4173, 14, 'ALTO DEL VALLE', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4174, 14, 'ALTO DEL VALLE', '5755', 0);
INSERT INTO ubicacion_localidades VALUES (4175, 14, 'ALTO GRANDE', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4176, 14, 'ALTO GRANDE', '5700', 0);
INSERT INTO ubicacion_localidades VALUES (4177, 14, 'ALTO LINDO', '5883', 0);
INSERT INTO ubicacion_localidades VALUES (4178, 14, 'ALTO NEGRO', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4179, 14, 'ALTO PELADO', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4180, 14, 'ALTO PENCOSO', '5724', 0);
INSERT INTO ubicacion_localidades VALUES (4181, 14, 'ALTO VERDE', '5741', 0);
INSERT INTO ubicacion_localidades VALUES (4182, 14, 'ANCAMILLA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4183, 14, 'ANCHORENA', '6389', 0);
INSERT INTO ubicacion_localidades VALUES (4184, 14, 'ANGELITA', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4185, 14, 'ANTIHUASI', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4186, 14, 'ARBOL SOLO', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4187, 14, 'ARBOL VERDE', '5715', 0);
INSERT INTO ubicacion_localidades VALUES (4188, 14, 'ARBOLEDA', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4189, 14, 'ARBOLES BLANCOS', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (4190, 14, 'ARENILLA', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4191, 14, 'ARIZONA', '6389', 0);
INSERT INTO ubicacion_localidades VALUES (4192, 14, 'ARROYO DE VILCHES', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (4193, 14, 'ARROYO LA CAL', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4194, 14, 'AVANZADA', '5738', 0);
INSERT INTO ubicacion_localidades VALUES (4195, 14, 'AVIADOR ORIGONE', '5741', 0);
INSERT INTO ubicacion_localidades VALUES (4196, 14, 'BAÃADITO', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4197, 14, 'BAÃADITO VIEJO', '5709', 0);
INSERT INTO ubicacion_localidades VALUES (4198, 14, 'BAÃADO', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (4199, 14, 'BAÃADO DE CAUTANA', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4200, 14, 'BAÃADO LINDO', '5705', 0);
INSERT INTO ubicacion_localidades VALUES (4201, 14, 'BAÃOS ZAPALLAR', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4202, 14, 'BAGUAL', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4203, 14, 'BAJADA', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4204, 14, 'BAJADA NUEVA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4205, 14, 'BAJO DE CONLARA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4206, 14, 'BAJO DE LA CRUZ', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4207, 14, 'BAJO GRANDE', '5775', 0);
INSERT INTO ubicacion_localidades VALUES (4208, 14, 'BAJO LA LAGUNA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4209, 14, 'BAJOS HONDOS', '5741', 0);
INSERT INTO ubicacion_localidades VALUES (4210, 14, 'BALCARCE', '5883', 0);
INSERT INTO ubicacion_localidades VALUES (4211, 14, 'BALDA', '5722', 0);
INSERT INTO ubicacion_localidades VALUES (4212, 14, 'BALDE', '5883', 0);
INSERT INTO ubicacion_localidades VALUES (4213, 14, 'BALDE', '5724', 0);
INSERT INTO ubicacion_localidades VALUES (4214, 14, 'BALDE AHUMADA', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (4215, 14, 'BALDE DE AMIRA', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4216, 14, 'BALDE DE ARRIBA', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (4217, 14, 'BALDE DE AZCURRA', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (4218, 14, 'BALDE DE ESCUDERO', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4219, 14, 'BALDE DE GARCIA', '5715', 0);
INSERT INTO ubicacion_localidades VALUES (4220, 14, 'BALDE DE GUARDIA', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (4221, 14, 'BALDE DE GUIÃAZU', '5715', 0);
INSERT INTO ubicacion_localidades VALUES (4222, 14, 'BALDE DE LA ISLA', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4223, 14, 'BALDE DE LA LINEA', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (4224, 14, 'BALDE DE LEDESMA', '5715', 0);
INSERT INTO ubicacion_localidades VALUES (4225, 14, 'BALDE DE MONTE', '5715', 0);
INSERT INTO ubicacion_localidades VALUES (4226, 14, 'BALDE DE NUEVO', '5715', 0);
INSERT INTO ubicacion_localidades VALUES (4227, 14, 'BALDE DE PUERTAS', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (4228, 14, 'BALDE DE QUINES', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (4229, 14, 'BALDE DE TORRES', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (4230, 14, 'BALDE DEL ESCUDERO', '5715', 0);
INSERT INTO ubicacion_localidades VALUES (4231, 14, 'BALDE DEL ROSARIO', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4232, 14, 'BALDE EL CARRIL', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (4233, 14, 'BALDE HONDO', '5705', 0);
INSERT INTO ubicacion_localidades VALUES (4234, 14, 'BALDE RETAMO', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4235, 14, 'BALDE VIEJO', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4236, 14, 'BALDECITO', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (4237, 14, 'BALDECITO LA PAMPA', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (4238, 14, 'BANDA SUD', '5705', 0);
INSERT INTO ubicacion_localidades VALUES (4239, 14, 'BARRANCA COLORADA', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4240, 14, 'BARRANQUITAS', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (4241, 14, 'BARRIAL', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4242, 14, 'BARRIALES', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (4243, 14, 'BARRIO BLANCO', '5777', 0);
INSERT INTO ubicacion_localidades VALUES (4244, 14, 'BARZOLA', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4245, 14, 'BATAVIA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4246, 14, 'BEAZLEY', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4247, 14, 'BEBEDERO', '5724', 0);
INSERT INTO ubicacion_localidades VALUES (4248, 14, 'BEBIDA', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4249, 14, 'BECERRA', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4250, 14, 'BELLA ESTANCIA', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4251, 14, 'BELLA VISTA', '5705', 0);
INSERT INTO ubicacion_localidades VALUES (4252, 14, 'BELLA VISTA', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (4253, 14, 'BELLA VISTA', '5835', 0);
INSERT INTO ubicacion_localidades VALUES (4254, 14, 'BELLA VISTA', '5724', 0);
INSERT INTO ubicacion_localidades VALUES (4255, 14, 'BILLIKEN', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4256, 14, 'BOCA DE LA QUEBRADA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4257, 14, 'BOCA DEL RIO', '5831', 0);
INSERT INTO ubicacion_localidades VALUES (4258, 14, 'BOTIJAS', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (4259, 14, 'BUENA ESPERANZA', '6277', 0);
INSERT INTO ubicacion_localidades VALUES (4260, 14, 'BUENA VENTURA', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4261, 14, 'BUENA VISTA', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4262, 14, 'CAÃA LARGA', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4263, 14, 'CAÃADA', '5715', 0);
INSERT INTO ubicacion_localidades VALUES (4264, 14, 'CAÃADA', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (4265, 14, 'CAÃADA ANGOSTA', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4266, 14, 'CAÃADA BLANCA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4267, 14, 'CAÃADA DE ATRAS', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (4268, 14, 'CAÃADA DE LA NEGRA', '5777', 0);
INSERT INTO ubicacion_localidades VALUES (4269, 14, 'CAÃADA DE LAS LAGUNAS', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (4270, 14, 'CAÃADA DE LOS TIGRES', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (4271, 14, 'CAÃADA DE VILAN', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4272, 14, 'CAÃADA DEL PASTO', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4273, 14, 'CAÃADA DEL PUESTITO', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4274, 14, 'CAÃADA GRANDE', '5777', 0);
INSERT INTO ubicacion_localidades VALUES (4275, 14, 'CAÃADA HONDA', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4276, 14, 'CAÃADA HONDA DE GUZMAN', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4277, 14, 'CAÃADA LA NEGRA', '5881', 0);
INSERT INTO ubicacion_localidades VALUES (4278, 14, 'CAÃADA LA TIENDA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4279, 14, 'CAÃADA QUEMADA', '5709', 0);
INSERT INTO ubicacion_localidades VALUES (4280, 14, 'CAÃADA QUEMADA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4281, 14, 'CAÃADA SAN ANTONIO', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4282, 14, 'CAÃADA VERDE', '5777', 0);
INSERT INTO ubicacion_localidades VALUES (4283, 14, 'CAÃADITAS', '5755', 0);
INSERT INTO ubicacion_localidades VALUES (4284, 14, 'CAÃITAS', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (4285, 14, 'CABEZA DE NOVILLO', '5779', 0);
INSERT INTO ubicacion_localidades VALUES (4286, 14, 'CACHI CORRAL', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4287, 14, 'CAIN DE LOS TIGRES', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (4288, 14, 'CALDENADAS', '5738', 0);
INSERT INTO ubicacion_localidades VALUES (4289, 14, 'CALERA ARGENTINA', '5759', 0);
INSERT INTO ubicacion_localidades VALUES (4290, 14, 'CALERAS CAÃADA GRANDE', '5759', 0);
INSERT INTO ubicacion_localidades VALUES (4291, 14, 'CAMPANARIO', '5705', 0);
INSERT INTO ubicacion_localidades VALUES (4292, 14, 'CAMPO DE SAN PEDRO', '5722', 0);
INSERT INTO ubicacion_localidades VALUES (4293, 14, 'CANDELARIA', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (4294, 14, 'CANTANTAL', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (4295, 14, 'CANTERAS SANTA ISABEL', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4296, 14, 'CAPELEN', '5741', 0);
INSERT INTO ubicacion_localidades VALUES (4297, 14, 'CARMELO', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (4298, 14, 'CAROLINA', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4299, 14, 'CARPINTERIA', '5883', 0);
INSERT INTO ubicacion_localidades VALUES (4300, 14, 'CASA DE CONDOR', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4301, 14, 'CASA DE PIEDRA', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4302, 14, 'CASA DE PIEDRA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4303, 14, 'CASAS VIEJAS', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4304, 14, 'CASIMIRO GOMEZ', '6214', 0);
INSERT INTO ubicacion_localidades VALUES (4305, 14, 'CEBOLLAR', '5709', 0);
INSERT INTO ubicacion_localidades VALUES (4306, 14, 'CENTENARIO', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4307, 14, 'CERRITO', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4308, 14, 'CERRITO BLANCO', '5777', 0);
INSERT INTO ubicacion_localidades VALUES (4309, 14, 'CERRITO NEGRO', '5770', 0);
INSERT INTO ubicacion_localidades VALUES (4310, 14, 'CERRO BAYO', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (4311, 14, 'CERRO BLANCO', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4312, 14, 'CERRO BLANCO', '5731', 0);
INSERT INTO ubicacion_localidades VALUES (4313, 14, 'CERRO COLORADO', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4314, 14, 'CERRO DE LA PILA', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4315, 14, 'CERRO DE ORO', '5881', 0);
INSERT INTO ubicacion_localidades VALUES (4316, 14, 'CERRO DE PIEDRA', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4317, 14, 'CERRO NEGRO', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (4318, 14, 'CERRO NEGRO', '5731', 0);
INSERT INTO ubicacion_localidades VALUES (4319, 14, 'CERRO VARELA', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4320, 14, 'CERRO VERDE', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4321, 14, 'CERRO VIEJO', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4322, 14, 'CERROS LARGOS', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4323, 14, 'CHAÃAR DE LA LEGUA', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (4324, 14, 'CHAÃARITOS', '5883', 0);
INSERT INTO ubicacion_localidades VALUES (4325, 14, 'CHACARITAS', '5755', 0);
INSERT INTO ubicacion_localidades VALUES (4326, 14, 'CHACRA LA PRIMAVERA', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (4327, 14, 'CHACRAS DEL CANTARO', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4328, 14, 'CHACRAS VIEJAS', '5770', 0);
INSERT INTO ubicacion_localidades VALUES (4329, 14, 'CHALANTA', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4330, 14, 'CHANCARITA', '5831', 0);
INSERT INTO ubicacion_localidades VALUES (4331, 14, 'CHARLONE', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4332, 14, 'CHARLONES', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4333, 14, 'CHILCAS', '5777', 0);
INSERT INTO ubicacion_localidades VALUES (4334, 14, 'CHIMBAS', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (4335, 14, 'CHIMBORAZO', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (4336, 14, 'CHIPICAL', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4337, 14, 'CHISCHACA', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4338, 14, 'CHISCHAQUITA', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4339, 14, 'CHOSMES', '5724', 0);
INSERT INTO ubicacion_localidades VALUES (4340, 14, 'CHUTUSA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4341, 14, 'CIUDAD JARDIN DE SAN LUIS', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (4342, 14, 'COCHENELOS', '6277', 0);
INSERT INTO ubicacion_localidades VALUES (4343, 14, 'COCHEQUINGAN', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4344, 14, 'COLONIA BELLA VISTA', '5735', 0);
INSERT INTO ubicacion_localidades VALUES (4345, 14, 'COLONIA CALZADA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4346, 14, 'COLONIA EL CAMPAMENTO', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4347, 14, 'COLONIA LA FLORIDA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4348, 14, 'COLONIA LUNA', '5738', 0);
INSERT INTO ubicacion_localidades VALUES (4349, 14, 'COLONIA SANTA VIRGINIA', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4350, 14, 'COLONIA URDANIZ', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4351, 14, 'COMANDANTE GRANVILLE', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4352, 14, 'CONCARAN', '5770', 0);
INSERT INTO ubicacion_localidades VALUES (4353, 14, 'CONLARA', '5759', 0);
INSERT INTO ubicacion_localidades VALUES (4354, 14, 'CONSUELO', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4355, 14, 'CONSULTA', '5709', 0);
INSERT INTO ubicacion_localidades VALUES (4356, 14, 'CORONEL ALZOGARAY', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (4357, 14, 'CORONEL SEGOVIA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4358, 14, 'CORRAL DE PIEDRA', '5709', 0);
INSERT INTO ubicacion_localidades VALUES (4359, 14, 'CORRAL DE TORRES', '5777', 0);
INSERT INTO ubicacion_localidades VALUES (4360, 14, 'CORRAL DEL TALA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4361, 14, 'CORRALES', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4362, 14, 'CORTADERAS', '5883', 0);
INSERT INTO ubicacion_localidades VALUES (4363, 14, 'CORTADERAS', '5700', 0);
INSERT INTO ubicacion_localidades VALUES (4364, 14, 'CORTADERAS', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (4365, 14, 'CRAMER', '5733', 0);
INSERT INTO ubicacion_localidades VALUES (4366, 14, 'CRUCECITAS', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (4367, 14, 'CRUZ BRILLANTE', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4368, 14, 'CRUZ DE CAÃA', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4369, 14, 'CRUZ DE PIEDRA', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4370, 14, 'CUATRO ESQUINAS', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (4371, 14, 'CUATRO ESQUINAS', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4372, 14, 'CUEVA DE TIGRE', '5755', 0);
INSERT INTO ubicacion_localidades VALUES (4373, 14, 'DANIEL DONOVAN', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4374, 14, 'DIQUE LA FLORIDA', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4375, 14, 'DIVISADERO', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4376, 14, 'DIVISADERO', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4377, 14, 'DOMINGUEZ', '5835', 0);
INSERT INTO ubicacion_localidades VALUES (4378, 14, 'DORMIDA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4379, 14, 'DURAZNITO', '5777', 0);
INSERT INTO ubicacion_localidades VALUES (4380, 14, 'DURAZNITO', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (4381, 14, 'EL  VALLE', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4382, 14, 'EL AGUILA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4383, 14, 'EL ALTO', '5715', 0);
INSERT INTO ubicacion_localidades VALUES (4384, 14, 'EL AMPARO', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4385, 14, 'EL ARENAL', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4386, 14, 'EL ARROYO', '5770', 0);
INSERT INTO ubicacion_localidades VALUES (4387, 14, 'EL BAÃADO', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4388, 14, 'EL BAÃADO', '5770', 0);
INSERT INTO ubicacion_localidades VALUES (4389, 14, 'EL BAGUAL', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4390, 14, 'EL BAJO', '5770', 0);
INSERT INTO ubicacion_localidades VALUES (4391, 14, 'EL BALDE', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4392, 14, 'EL BALDECITO', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4393, 14, 'EL BARRIAL', '5715', 0);
INSERT INTO ubicacion_localidades VALUES (4394, 14, 'EL BLANCO', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4395, 14, 'EL BURRITO', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4396, 14, 'EL CADILLO', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (4397, 14, 'EL CALDEN', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4398, 14, 'EL CALDEN', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4399, 14, 'EL CALDEN', '5770', 0);
INSERT INTO ubicacion_localidades VALUES (4400, 14, 'EL CALDEN', '5741', 0);
INSERT INTO ubicacion_localidades VALUES (4401, 14, 'EL CAMPAMENTO', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4402, 14, 'EL CARDAL', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4403, 14, 'EL CARMEN', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4404, 14, 'EL CARMEN', '5738', 0);
INSERT INTO ubicacion_localidades VALUES (4405, 14, 'EL CAVADO', '5770', 0);
INSERT INTO ubicacion_localidades VALUES (4406, 14, 'EL CAZADOR', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4407, 14, 'EL CERRITO', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4408, 14, 'EL CERRO', '5770', 0);
INSERT INTO ubicacion_localidades VALUES (4409, 14, 'EL CHAÃAR', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4410, 14, 'EL CHAÃAR', '5751', 0);
INSERT INTO ubicacion_localidades VALUES (4411, 14, 'EL CHAÃAR', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (4412, 14, 'EL CHAÃAR', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4413, 14, 'EL CHARABON', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4414, 14, 'EL CHARCO', '5724', 0);
INSERT INTO ubicacion_localidades VALUES (4415, 14, 'EL CHORRILLO', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4416, 14, 'EL CINCO', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4417, 14, 'EL CONDOR', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4418, 14, 'EL CORO', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4419, 14, 'EL DICHOSO', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4420, 14, 'EL DIQUE', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (4421, 14, 'EL DURAZNO', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4422, 14, 'EL ESPINILLO', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4423, 14, 'EL ESPINILLO', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4424, 14, 'EL ESPINILLO', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4425, 14, 'EL FORTIN', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (4426, 14, 'EL HORMIGUERO', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (4427, 14, 'EL HORNITO', '5755', 0);
INSERT INTO ubicacion_localidades VALUES (4428, 14, 'EL INJERTO', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4429, 14, 'EL JARILLAL', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4430, 14, 'EL LECHUZO', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4431, 14, 'EL MANANTIAL', '5709', 0);
INSERT INTO ubicacion_localidades VALUES (4432, 14, 'EL MANANTIAL ESCONDIDO', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4433, 14, 'EL MANGRULLO', '5738', 0);
INSERT INTO ubicacion_localidades VALUES (4434, 14, 'EL MARTILLO', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4435, 14, 'EL MATACO', '5724', 0);
INSERT INTO ubicacion_localidades VALUES (4436, 14, 'EL MILAGRO', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4437, 14, 'EL MOLINO', '5709', 0);
INSERT INTO ubicacion_localidades VALUES (4438, 14, 'EL MOLLAR', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (4439, 14, 'EL MOLLARCITO', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (4440, 14, 'EL MOLLE', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4441, 14, 'EL MOLLE', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4442, 14, 'EL MORRO', '5731', 0);
INSERT INTO ubicacion_localidades VALUES (4443, 14, 'EL NASAO', '5738', 0);
INSERT INTO ubicacion_localidades VALUES (4444, 14, 'EL NEGRO', '5724', 0);
INSERT INTO ubicacion_localidades VALUES (4445, 14, 'EL OASIS', '6277', 0);
INSERT INTO ubicacion_localidades VALUES (4446, 14, 'EL OLMO', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (4447, 14, 'EL PAJARETE', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4448, 14, 'EL PANTANILLO', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4449, 14, 'EL PANTANO', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4450, 14, 'EL PARAGUAY', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4451, 14, 'EL PARAISO', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4452, 14, 'EL PARAISO', '5755', 0);
INSERT INTO ubicacion_localidades VALUES (4453, 14, 'EL PASAJERO', '5731', 0);
INSERT INTO ubicacion_localidades VALUES (4454, 14, 'EL PAYERO', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4455, 14, 'EL PEDERNAL', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4456, 14, 'EL PEJE', '5770', 0);
INSERT INTO ubicacion_localidades VALUES (4457, 14, 'EL PICHE', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4458, 14, 'EL PIGUE', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4459, 14, 'EL PIMPOLLO', '5717', 0);
INSERT INTO ubicacion_localidades VALUES (4460, 14, 'EL PLATEADO', '5731', 0);
INSERT INTO ubicacion_localidades VALUES (4461, 14, 'EL PLUMERITO', '5637', 0);
INSERT INTO ubicacion_localidades VALUES (4462, 14, 'EL POCITO', '5715', 0);
INSERT INTO ubicacion_localidades VALUES (4463, 14, 'EL POLEO', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (4464, 14, 'EL POLEO', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (4465, 14, 'EL PORTEZUELO', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4466, 14, 'EL PORVENIR', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4467, 14, 'EL PORVENIR', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (4468, 14, 'EL POTRERILLO', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4469, 14, 'EL POTRERO DE LEYES', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4470, 14, 'EL POZO', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4471, 14, 'EL PROGRESO', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4472, 14, 'EL PUEBLITO', '5777', 0);
INSERT INTO ubicacion_localidades VALUES (4473, 14, 'EL PUERTO', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4474, 14, 'EL PUESTITO', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (4475, 14, 'EL PUESTO', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4476, 14, 'EL PUESTO', '5759', 0);
INSERT INTO ubicacion_localidades VALUES (4477, 14, 'EL PUESTO', '5755', 0);
INSERT INTO ubicacion_localidades VALUES (4478, 14, 'EL QUEBRACHO', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4479, 14, 'EL QUEBRACHO', '5700', 0);
INSERT INTO ubicacion_localidades VALUES (4480, 14, 'EL QUINGUAL', '6277', 0);
INSERT INTO ubicacion_localidades VALUES (4481, 14, 'EL RAMBLON', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4482, 14, 'EL RECUERDO', '5883', 0);
INSERT INTO ubicacion_localidades VALUES (4483, 14, 'EL RECUERDO', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4484, 14, 'EL RETAMO', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4485, 14, 'EL RIECITO', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4486, 14, 'EL RINCON', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (4487, 14, 'EL RINCON', '5881', 0);
INSERT INTO ubicacion_localidades VALUES (4488, 14, 'EL RINCON', '5755', 0);
INSERT INTO ubicacion_localidades VALUES (4489, 14, 'EL RIO', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4490, 14, 'EL RODEO', '6389', 0);
INSERT INTO ubicacion_localidades VALUES (4491, 14, 'EL RODEO', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (4492, 14, 'EL ROSARIO', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4493, 14, 'EL SALADO', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4494, 14, 'EL SALADO', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4495, 14, 'EL SALADO DE AMAYA', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (4496, 14, 'EL SALTO', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4497, 14, 'EL SALTO', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4498, 14, 'EL SALVADOR', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (4499, 14, 'EL SARCO', '5731', 0);
INSERT INTO ubicacion_localidades VALUES (4500, 14, 'EL SAUCE', '5770', 0);
INSERT INTO ubicacion_localidades VALUES (4501, 14, 'EL SAUCE', '5831', 0);
INSERT INTO ubicacion_localidades VALUES (4502, 14, 'EL SEMBRADO', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (4503, 14, 'EL SOCORRO', '5770', 0);
INSERT INTO ubicacion_localidades VALUES (4504, 14, 'EL SOCORRO', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4505, 14, 'EL TALA', '5835', 0);
INSERT INTO ubicacion_localidades VALUES (4506, 14, 'EL TALA', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4507, 14, 'EL TALITA', '5751', 0);
INSERT INTO ubicacion_localidades VALUES (4508, 14, 'EL TALITA', '5831', 0);
INSERT INTO ubicacion_localidades VALUES (4509, 14, 'EL TALITA', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4510, 14, 'EL TALITA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4511, 14, 'EL TEMBLEQUE', '5715', 0);
INSERT INTO ubicacion_localidades VALUES (4512, 14, 'EL TORCIDO', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (4513, 14, 'EL TORO MUERTO', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4514, 14, 'EL TOTORAL', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (4515, 14, 'EL TOTORAL', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4516, 14, 'EL VALLE', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4517, 14, 'EL VALLE', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4518, 14, 'EL VALLECITO', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4519, 14, 'EL VALLECITO', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (4520, 14, 'EL VERANO', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (4521, 14, 'EL VERANO', '6277', 0);
INSERT INTO ubicacion_localidades VALUES (4522, 14, 'EL VOLCAN', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4523, 14, 'EL YACATAN', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4524, 14, 'EL ZAMPAL', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4525, 14, 'EL ZAPALLAR', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4526, 14, 'ELEODORO LOBOS', '5722', 0);
INSERT INTO ubicacion_localidades VALUES (4527, 14, 'EMBALSE LA FLORIDA', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4528, 14, 'ENSENADA', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (4529, 14, 'ENTRE RIOS', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4530, 14, 'ESPINILLO', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4531, 14, 'ESTABLECIMIENTO LAS FLORES', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4532, 14, 'ESTACION ZANJITAS', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4533, 14, 'ESTANCIA', '5883', 0);
INSERT INTO ubicacion_localidades VALUES (4534, 14, 'ESTANCIA 30 DE OCTUBRE', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4535, 14, 'ESTANCIA DON ARTURO', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4536, 14, 'ESTANCIA EL CHAMICO', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (4537, 14, 'ESTANCIA EL DIVISADERO', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (4538, 14, 'ESTANCIA EL MEDANO', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4539, 14, 'ESTANCIA EL QUEBRACHAL', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (4540, 14, 'ESTANCIA EL SALADO', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4541, 14, 'ESTANCIA EL SAUCECITO', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (4542, 14, 'ESTANCIA GRANDE', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4543, 14, 'ESTANCIA LA BLANCA', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4544, 14, 'ESTANCIA LA GUARDIA', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (4545, 14, 'ESTANCIA LA GUILLERMINA', '5731', 0);
INSERT INTO ubicacion_localidades VALUES (4546, 14, 'ESTANCIA LA MORENA', '5731', 0);
INSERT INTO ubicacion_localidades VALUES (4547, 14, 'ESTANCIA LA RESERVA', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (4548, 14, 'ESTANCIA LA UNION', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4549, 14, 'ESTANCIA LA ZULEMITA', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (4550, 14, 'ESTANCIA LAS BEBIDAS', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (4551, 14, 'ESTANCIA LOS HERMANOS', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (4552, 14, 'ESTANCIA LOS NOGALES', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (4553, 14, 'ESTANCIA RIVADAVIA', '5700', 0);
INSERT INTO ubicacion_localidades VALUES (4554, 14, 'ESTANCIA SAN ALBERTO', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (4555, 14, 'ESTANCIA SAN ANTONIO', '5731', 0);
INSERT INTO ubicacion_localidades VALUES (4556, 14, 'ESTANCIA SAN FRANCISCO', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (4557, 14, 'ESTANCIA SAN ROQUE', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4558, 14, 'ESTANCIA TRES ARBOLES', '5881', 0);
INSERT INTO ubicacion_localidades VALUES (4559, 14, 'ESTANZUELA', '5831', 0);
INSERT INTO ubicacion_localidades VALUES (4560, 14, 'FAVELLI', '5636', 0);
INSERT INTO ubicacion_localidades VALUES (4561, 14, 'FENOGLIO', '5770', 0);
INSERT INTO ubicacion_localidades VALUES (4562, 14, 'FLORIDA', '5715', 0);
INSERT INTO ubicacion_localidades VALUES (4563, 14, 'FORTIN EL PATRIA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4564, 14, 'FORTIN SALTO', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4565, 14, 'FORTUNA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4566, 14, 'FORTUNA DE SAN JUAN', '5755', 0);
INSERT INTO ubicacion_localidades VALUES (4567, 14, 'FRAGA', '5736', 0);
INSERT INTO ubicacion_localidades VALUES (4568, 14, 'FRISIA', '6277', 0);
INSERT INTO ubicacion_localidades VALUES (4569, 14, 'GENERAL PEDERNERA', '5738', 0);
INSERT INTO ubicacion_localidades VALUES (4570, 14, 'GENERAL URQUIZA', '5763', 0);
INSERT INTO ubicacion_localidades VALUES (4571, 14, 'GIGANTE', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4572, 14, 'GLORIA A DIOS', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4573, 14, 'GORGONTA', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4574, 14, 'GRUTA DE INTIHUASI', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4575, 14, 'GUALTARAN', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (4576, 14, 'GUANACO', '5759', 0);
INSERT INTO ubicacion_localidades VALUES (4577, 14, 'GUANACO PAMPA', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (4578, 14, 'GUASQUITA', '5724', 0);
INSERT INTO ubicacion_localidades VALUES (4579, 14, 'GUZMAN', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (4580, 14, 'HINOJITO', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4581, 14, 'HINOJOS', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4582, 14, 'HIPOLITO YRIGOYEN', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4583, 14, 'HORNITO', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4584, 14, 'HUALTARAN', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4585, 14, 'HUCHISSON', '5759', 0);
INSERT INTO ubicacion_localidades VALUES (4586, 14, 'HUEJEDA', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4587, 14, 'HUERTAS', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (4588, 14, 'INTIGUASI', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4589, 14, 'INVERNADA', '5777', 0);
INSERT INTO ubicacion_localidades VALUES (4590, 14, 'ISLA', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4591, 14, 'ISLA', '5873', 0);
INSERT INTO ubicacion_localidades VALUES (4592, 14, 'ISLITAS', '5715', 0);
INSERT INTO ubicacion_localidades VALUES (4593, 14, 'ISONDU', '5735', 0);
INSERT INTO ubicacion_localidades VALUES (4594, 14, 'JARILLA', '5724', 0);
INSERT INTO ubicacion_localidades VALUES (4595, 14, 'JUAN JORBA', '5731', 0);
INSERT INTO ubicacion_localidades VALUES (4596, 14, 'JUAN LLERENA', '5735', 0);
INSERT INTO ubicacion_localidades VALUES (4597, 14, 'JUAN W GEZ', '5722', 0);
INSERT INTO ubicacion_localidades VALUES (4598, 14, 'JUANA KOSLAY', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4599, 14, 'JUANTE', '5731', 0);
INSERT INTO ubicacion_localidades VALUES (4600, 14, 'JUSTO DARACT', '5738', 0);
INSERT INTO ubicacion_localidades VALUES (4601, 14, 'KILOMETRO 656', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (4602, 14, 'LA ADELA', '5736', 0);
INSERT INTO ubicacion_localidades VALUES (4603, 14, 'LA AGUA NUEVA', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4604, 14, 'LA AGUADA', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4605, 14, 'LA AGUADA', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4606, 14, 'LA AGUADA', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4607, 14, 'LA AGUADA', '5831', 0);
INSERT INTO ubicacion_localidades VALUES (4608, 14, 'LA AGUADA DE LAS ANIMAS', '5871', 0);
INSERT INTO ubicacion_localidades VALUES (4609, 14, 'LA AGUEDA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4610, 14, 'LA ALAMEDA', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4611, 14, 'LA ALAMEDA', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4612, 14, 'LA ALCORTEÃA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4613, 14, 'LA ALEGRIA', '5871', 0);
INSERT INTO ubicacion_localidades VALUES (4614, 14, 'LA ALIANZA', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4615, 14, 'LA AMALIA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4616, 14, 'LA AMARGA', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4617, 14, 'LA ANGELINA', '5731', 0);
INSERT INTO ubicacion_localidades VALUES (4618, 14, 'LA ANGOSTURA', '5873', 0);
INSERT INTO ubicacion_localidades VALUES (4619, 14, 'LA ARBOLEDA', '5751', 0);
INSERT INTO ubicacion_localidades VALUES (4620, 14, 'LA ARGENTINA', '5835', 0);
INSERT INTO ubicacion_localidades VALUES (4621, 14, 'LA ARMONIA', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (4622, 14, 'LA AROMA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4623, 14, 'LA ATALAYA', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4624, 14, 'LA AURORA', '5835', 0);
INSERT INTO ubicacion_localidades VALUES (4625, 14, 'LA AURORA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4626, 14, 'LA AURORA', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (4627, 14, 'LA BAJADA', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (4628, 14, 'LA BAJADA', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4629, 14, 'LA BAVA', '5705', 0);
INSERT INTO ubicacion_localidades VALUES (4630, 14, 'LA BAVARIA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4631, 14, 'LA BERTITA', '5731', 0);
INSERT INTO ubicacion_localidades VALUES (4632, 14, 'LA BOLIVIA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4633, 14, 'LA BONITA', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4634, 14, 'LA BREA', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4635, 14, 'LA BREA', '5724', 0);
INSERT INTO ubicacion_localidades VALUES (4636, 14, 'LA CAÃADA', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4637, 14, 'LA CAÃADA', '5831', 0);
INSERT INTO ubicacion_localidades VALUES (4638, 14, 'LA CAÃADA', '5637', 0);
INSERT INTO ubicacion_localidades VALUES (4639, 14, 'LA CAÃADA', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4640, 14, 'LA CABRA', '5724', 0);
INSERT INTO ubicacion_localidades VALUES (4641, 14, 'LA CALAGUALA', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4642, 14, 'LA CALDERA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4643, 14, 'LA CALERA', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4644, 14, 'LA CARMEN', '5738', 0);
INSERT INTO ubicacion_localidades VALUES (4645, 14, 'LA CARMENCITA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4646, 14, 'LA CAUTIVA', '5736', 0);
INSERT INTO ubicacion_localidades VALUES (4647, 14, 'LA CELIA', '5873', 0);
INSERT INTO ubicacion_localidades VALUES (4648, 14, 'LA CHAÃARIENTA', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4649, 14, 'LA CHERINDU', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4650, 14, 'LA CHILCA', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4651, 14, 'LA CHILCA', '5777', 0);
INSERT INTO ubicacion_localidades VALUES (4652, 14, 'LA CHILLA', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (4653, 14, 'LA CIENAGA', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4654, 14, 'LA COCHA', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (4655, 14, 'LA COLINA', '6269', 0);
INSERT INTO ubicacion_localidades VALUES (4656, 14, 'LA COLONIA', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (4657, 14, 'LA COLONIA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4658, 14, 'LA CORA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4659, 14, 'LA CORINA', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4660, 14, 'LA CORTADERA', '5700', 0);
INSERT INTO ubicacion_localidades VALUES (4661, 14, 'LA COSTA', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4662, 14, 'LA CRISTINA', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (4663, 14, 'LA CRUCECITA', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (4664, 14, 'LA CUMBRE', '5883', 0);
INSERT INTO ubicacion_localidades VALUES (4665, 14, 'LA DELIA', '5722', 0);
INSERT INTO ubicacion_localidades VALUES (4666, 14, 'LA DONOSTIA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4667, 14, 'LA DORA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4668, 14, 'LA DUDA', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4669, 14, 'LA DULCE', '6277', 0);
INSERT INTO ubicacion_localidades VALUES (4670, 14, 'LA DULCE', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4671, 14, 'LA ELENA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4672, 14, 'LA ELENITA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4673, 14, 'LA ELIDA', '5738', 0);
INSERT INTO ubicacion_localidades VALUES (4674, 14, 'LA ELISA', '5741', 0);
INSERT INTO ubicacion_localidades VALUES (4675, 14, 'LA ELVIRA', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (4676, 14, 'LA ELVIRA', '5770', 0);
INSERT INTO ubicacion_localidades VALUES (4677, 14, 'LA EMILIA', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4678, 14, 'LA EMMA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4679, 14, 'LA EMPAJADA', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4680, 14, 'LA ERNESTINA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4681, 14, 'LA ESCONDIDA', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4682, 14, 'LA ESCONDIDA', '5775', 0);
INSERT INTO ubicacion_localidades VALUES (4683, 14, 'LA ESCONDIDA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4684, 14, 'LA ESMERALDA', '6277', 0);
INSERT INTO ubicacion_localidades VALUES (4685, 14, 'LA ESPERANZA', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4686, 14, 'LA ESPERANZA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4687, 14, 'LA ESPERANZA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4688, 14, 'LA ESPESURA', '5700', 0);
INSERT INTO ubicacion_localidades VALUES (4689, 14, 'LA ESQUINA', '5709', 0);
INSERT INTO ubicacion_localidades VALUES (4690, 14, 'LA ESQUINA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4691, 14, 'LA ESQUINA DEL RIO', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4692, 14, 'LA ESTANCIA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4693, 14, 'LA ESTANZUELA', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (4694, 14, 'LA ESTRELLA', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4695, 14, 'LA ESTRELLA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4696, 14, 'LA ETHEL', '6277', 0);
INSERT INTO ubicacion_localidades VALUES (4697, 14, 'LA EULOGIA', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4698, 14, 'LA FELISA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4699, 14, 'LA FINCA', '5871', 0);
INSERT INTO ubicacion_localidades VALUES (4700, 14, 'LA FLECHA', '5741', 0);
INSERT INTO ubicacion_localidades VALUES (4701, 14, 'LA FLORIDA', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4702, 14, 'LA FLORIDA', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4703, 14, 'LA FLORIDA', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4704, 14, 'LA FLORIDA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4705, 14, 'LA FLORIDA', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (4706, 14, 'LA FRAGUA', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4707, 14, 'LA GAMA', '5731', 0);
INSERT INTO ubicacion_localidades VALUES (4708, 14, 'LA GARRAPATA', '5738', 0);
INSERT INTO ubicacion_localidades VALUES (4709, 14, 'LA GARZA', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4710, 14, 'LA GAVIOTA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4711, 14, 'LA GERMANIA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4712, 14, 'LA GITANA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4713, 14, 'LA GRAMILLA', '5770', 0);
INSERT INTO ubicacion_localidades VALUES (4714, 14, 'LA GUARDIA', '5759', 0);
INSERT INTO ubicacion_localidades VALUES (4715, 14, 'LA HERMOSURA', '5831', 0);
INSERT INTO ubicacion_localidades VALUES (4716, 14, 'LA HIGUERITA', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4717, 14, 'LA HOLANDA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4718, 14, 'LA HORTENSIA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4719, 14, 'LA HUERTA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4720, 14, 'LA HUERTITA', '5755', 0);
INSERT INTO ubicacion_localidades VALUES (4721, 14, 'LA IBERIA', '5731', 0);
INSERT INTO ubicacion_localidades VALUES (4722, 14, 'LA INVERNADA', '6277', 0);
INSERT INTO ubicacion_localidades VALUES (4723, 14, 'LA IRENE', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4724, 14, 'LA ISABEL', '5743', 0);
INSERT INTO ubicacion_localidades VALUES (4725, 14, 'LA ISLA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4726, 14, 'LA JAVIERA', '5731', 0);
INSERT INTO ubicacion_localidades VALUES (4727, 14, 'LA JERGA', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4728, 14, 'LA JOSEFA', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4729, 14, 'LA JOSEFA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4730, 14, 'LA JOSEFINA', '5741', 0);
INSERT INTO ubicacion_localidades VALUES (4731, 14, 'LA JUANA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4732, 14, 'LA JUANITA', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4733, 14, 'LA JUANITA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4734, 14, 'LA JULIA', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4735, 14, 'LA JUSTA', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4736, 14, 'LA LAURA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4737, 14, 'LA LECHUGA', '5637', 0);
INSERT INTO ubicacion_localidades VALUES (4738, 14, 'LA LEGUA', '5709', 0);
INSERT INTO ubicacion_localidades VALUES (4739, 14, 'LA LINDA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4740, 14, 'LA LINEA', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4741, 14, 'LA LOMA', '5871', 0);
INSERT INTO ubicacion_localidades VALUES (4742, 14, 'LA LUISA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4743, 14, 'LA MAGDALENA', '5738', 0);
INSERT INTO ubicacion_localidades VALUES (4744, 14, 'LA MAJADA', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4745, 14, 'LA MARAVILLA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4746, 14, 'LA MARGARITA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4747, 14, 'LA MARGARITA CARLOTA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4748, 14, 'LA MARIA', '5722', 0);
INSERT INTO ubicacion_localidades VALUES (4749, 14, 'LA MARIA ESTHER', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4750, 14, 'LA MARIA LUISA', '6277', 0);
INSERT INTO ubicacion_localidades VALUES (4751, 14, 'LA MAROMA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4752, 14, 'LA MASCOTA', '5738', 0);
INSERT INTO ubicacion_localidades VALUES (4753, 14, 'LA MASCOTA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4754, 14, 'LA MEDIA LEGUA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4755, 14, 'LA MEDULA', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (4756, 14, 'LA MELINA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4757, 14, 'LA MERCED', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4758, 14, 'LA MESILLA', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (4759, 14, 'LA MINA', '5755', 0);
INSERT INTO ubicacion_localidades VALUES (4760, 14, 'LA MODERNA', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (4761, 14, 'LA NEGRA', '5731', 0);
INSERT INTO ubicacion_localidades VALUES (4762, 14, 'LA NEGRITA', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (4763, 14, 'LA NELIDA', '5700', 0);
INSERT INTO ubicacion_localidades VALUES (4764, 14, 'LA NUTRIA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4765, 14, 'LA PALMIRA', '5741', 0);
INSERT INTO ubicacion_localidades VALUES (4766, 14, 'LA PAMPA', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4767, 14, 'LA PATRIA', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (4768, 14, 'LA PEREGRINA', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4769, 14, 'LA PETRA', '5751', 0);
INSERT INTO ubicacion_localidades VALUES (4770, 14, 'LA PLATA', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (4771, 14, 'LA PORFIA', '5705', 0);
INSERT INTO ubicacion_localidades VALUES (4772, 14, 'LA PORTADA', '5731', 0);
INSERT INTO ubicacion_localidades VALUES (4773, 14, 'LA PRIMAVERA', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4774, 14, 'LA PRIMAVERA', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (4775, 14, 'LA PROVIDENCIA', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4776, 14, 'LA PUERTA', '5755', 0);
INSERT INTO ubicacion_localidades VALUES (4777, 14, 'LA QUEBRADA', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4778, 14, 'LA RAMADA', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (4779, 14, 'LA RAMADA', '5881', 0);
INSERT INTO ubicacion_localidades VALUES (4780, 14, 'LA RAMADA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4781, 14, 'LA REALIDAD', '5741', 0);
INSERT INTO ubicacion_localidades VALUES (4782, 14, 'LA REFORMA', '5724', 0);
INSERT INTO ubicacion_localidades VALUES (4783, 14, 'LA REINA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4784, 14, 'LA REPRESA', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4785, 14, 'LA REPRESITA', '5705', 0);
INSERT INTO ubicacion_localidades VALUES (4786, 14, 'LA REPRESITA', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4787, 14, 'LA RESERVA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4788, 14, 'LA RESERVA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4789, 14, 'LA RESISTENCIA', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (4790, 14, 'LA RINCONADA', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4791, 14, 'LA RIOJITA', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (4792, 14, 'LA ROSADA', '5831', 0);
INSERT INTO ubicacion_localidades VALUES (4793, 14, 'LA ROSALIA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4794, 14, 'LA ROSINA', '6277', 0);
INSERT INTO ubicacion_localidades VALUES (4795, 14, 'LA SALA', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (4796, 14, 'LA SALUD', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4797, 14, 'LA SALVADORA', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (4798, 14, 'LA SANDIA', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4799, 14, 'LA SEÃA', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4800, 14, 'LA SEGUNDA', '6277', 0);
INSERT INTO ubicacion_localidades VALUES (4801, 14, 'LA SELVA', '5724', 0);
INSERT INTO ubicacion_localidades VALUES (4802, 14, 'LA SERRANA', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4803, 14, 'LA SILESIA', '5741', 0);
INSERT INTO ubicacion_localidades VALUES (4804, 14, 'LA SIRENA', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (4805, 14, 'LA SUIZA', '5759', 0);
INSERT INTO ubicacion_localidades VALUES (4806, 14, 'LA TIGRA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4807, 14, 'LA TOMA', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4808, 14, 'LA TOSCA', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4809, 14, 'LA TOTORA', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4810, 14, 'LA TOTORA', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4811, 14, 'LA TOTORA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4812, 14, 'LA TRANCA', '5421', 0);
INSERT INTO ubicacion_localidades VALUES (4813, 14, 'LA TRAVESIA', '6389', 0);
INSERT INTO ubicacion_localidades VALUES (4814, 14, 'LA TULA', '5738', 0);
INSERT INTO ubicacion_localidades VALUES (4815, 14, 'LA TUSCA', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (4816, 14, 'LA ULBARA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4817, 14, 'LA UNION', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4818, 14, 'LA UNION', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4819, 14, 'LA UNION', '5724', 0);
INSERT INTO ubicacion_localidades VALUES (4820, 14, 'LA URUGUAYA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4821, 14, 'LA VACA', '6389', 0);
INSERT INTO ubicacion_localidades VALUES (4822, 14, 'LA VENECIA', '5735', 0);
INSERT INTO ubicacion_localidades VALUES (4823, 14, 'LA VERDE', '6389', 0);
INSERT INTO ubicacion_localidades VALUES (4824, 14, 'LA VERDE', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4825, 14, 'LA VERTIENTE', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4826, 14, 'LA VERTIENTE', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4827, 14, 'LA YERBA BUENA', '5722', 0);
INSERT INTO ubicacion_localidades VALUES (4828, 14, 'LA YESERA', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4829, 14, 'LAFINUR', '5871', 0);
INSERT INTO ubicacion_localidades VALUES (4830, 14, 'LAGUNA BRAVA', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4831, 14, 'LAGUNA CAPELEN', '5741', 0);
INSERT INTO ubicacion_localidades VALUES (4832, 14, 'LAGUNA DE LA CAÃADA', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (4833, 14, 'LAGUNA DE LOS PATOS', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (4834, 14, 'LAGUNA DE PATOS', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (4835, 14, 'LAGUNA LARGA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4836, 14, 'LAGUNA SAYAPE', '5741', 0);
INSERT INTO ubicacion_localidades VALUES (4837, 14, 'LAGUNA SECA', '5724', 0);
INSERT INTO ubicacion_localidades VALUES (4838, 14, 'LAS AGUADAS', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (4839, 14, 'LAS AROMAS', '6277', 0);
INSERT INTO ubicacion_localidades VALUES (4840, 14, 'LAS BAJADAS', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (4841, 14, 'LAS BARRANCAS', '5871', 0);
INSERT INTO ubicacion_localidades VALUES (4842, 14, 'LAS BARRANCAS', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4843, 14, 'LAS BARRANQUITAS', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4844, 14, 'LAS BARRANQUITAS', '5755', 0);
INSERT INTO ubicacion_localidades VALUES (4845, 14, 'LAS CAÃAS', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (4846, 14, 'LAS CAÃITAS', '5722', 0);
INSERT INTO ubicacion_localidades VALUES (4847, 14, 'LAS CABRAS', '5715', 0);
INSERT INTO ubicacion_localidades VALUES (4848, 14, 'LAS CANTERAS', '5759', 0);
INSERT INTO ubicacion_localidades VALUES (4849, 14, 'LAS CARITAS', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4850, 14, 'LAS CAROLINAS', '5731', 0);
INSERT INTO ubicacion_localidades VALUES (4851, 14, 'LAS CARRETAS', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4852, 14, 'LAS CHACRAS', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4853, 14, 'LAS CHACRAS DE SAN MARTIN', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4854, 14, 'LAS CHACRAS DE SAN MARTIN', '5755', 0);
INSERT INTO ubicacion_localidades VALUES (4855, 14, 'LAS CHACRITAS', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4856, 14, 'LAS CHILCAS', '5777', 0);
INSERT INTO ubicacion_localidades VALUES (4857, 14, 'LAS CHIMBAS', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (4858, 14, 'LAS CLARITAS', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4859, 14, 'LAS COLONIAS', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4860, 14, 'LAS CORTADERAS', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4861, 14, 'LAS DELICIAS', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4862, 14, 'LAS ENCADENADAS', '5738', 0);
INSERT INTO ubicacion_localidades VALUES (4863, 14, 'LAS FLORES', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4864, 14, 'LAS FLORES', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4865, 14, 'LAS GALERAS', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4866, 14, 'LAS GAMAS', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4867, 14, 'LAS GITANAS', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4868, 14, 'LAS HIGUERAS', '5722', 0);
INSERT INTO ubicacion_localidades VALUES (4869, 14, 'LAS HIGUERAS', '5755', 0);
INSERT INTO ubicacion_localidades VALUES (4870, 14, 'LAS ISLITAS', '5715', 0);
INSERT INTO ubicacion_localidades VALUES (4871, 14, 'LAS LAGUNAS', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (4872, 14, 'LAS LAGUNAS', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4873, 14, 'LAS LAGUNAS', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (4874, 14, 'LAS LAGUNITAS', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (4875, 14, 'LAS LAGUNITAS', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4876, 14, 'LAS LAJAS', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4877, 14, 'LAS LOMAS', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4878, 14, 'LAS LOMAS', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4879, 14, 'LAS MANGAS', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4880, 14, 'LAS MARTINETAS', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4881, 14, 'LAS MELADAS', '5738', 0);
INSERT INTO ubicacion_localidades VALUES (4882, 14, 'LAS MESIAS', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (4883, 14, 'LAS MESTIZAS', '6277', 0);
INSERT INTO ubicacion_localidades VALUES (4884, 14, 'LAS NIEVES', '5770', 0);
INSERT INTO ubicacion_localidades VALUES (4885, 14, 'LAS PALMAS', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (4886, 14, 'LAS PALOMAS', '5715', 0);
INSERT INTO ubicacion_localidades VALUES (4887, 14, 'LAS PAMPITAS', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (4888, 14, 'LAS PEÃAS', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4889, 14, 'LAS PIEDRITAS', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4890, 14, 'LAS PLAYAS', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4891, 14, 'LAS PLAYAS ARGENTINAS', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (4892, 14, 'LAS PRADERAS', '5731', 0);
INSERT INTO ubicacion_localidades VALUES (4893, 14, 'LAS PUERTAS', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4894, 14, 'LAS RAICES', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (4895, 14, 'LAS ROSADAS', '5715', 0);
INSERT INTO ubicacion_localidades VALUES (4896, 14, 'LAS ROSAS', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4897, 14, 'LAS SALINAS', '5705', 0);
INSERT INTO ubicacion_localidades VALUES (4898, 14, 'LAS TIGRAS', '5777', 0);
INSERT INTO ubicacion_localidades VALUES (4899, 14, 'LAS TOSCAS', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4900, 14, 'LAS TOTORITAS', '5738', 0);
INSERT INTO ubicacion_localidades VALUES (4901, 14, 'LAS TRES CAÃADAS', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4902, 14, 'LAS VISCACHERAS', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4903, 14, 'LAURA ELISA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4904, 14, 'LAVAISSE', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (4905, 14, 'LEANDRO N ALEM', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4906, 14, 'LIBORIO LUNA', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (4907, 14, 'LINCE', '5722', 0);
INSERT INTO ubicacion_localidades VALUES (4908, 14, 'LINDO', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (4909, 14, 'LOMA DEL MEDIO', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4910, 14, 'LOMAS BLANCAS', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (4911, 14, 'LOMITAS', '5871', 0);
INSERT INTO ubicacion_localidades VALUES (4912, 14, 'LONGARI', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4913, 14, 'LOS AGUADOS', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4914, 14, 'LOS ALAMOS', '5831', 0);
INSERT INTO ubicacion_localidades VALUES (4915, 14, 'LOS ALAMOS', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4916, 14, 'LOS ALGARROBITOS', '5715', 0);
INSERT INTO ubicacion_localidades VALUES (4917, 14, 'LOS ALGARROBOS', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4918, 14, 'LOS ALGARROBOS', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4919, 14, 'LOS ALMACIGOS', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (4920, 14, 'LOS ARADITOS', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4921, 14, 'LOS ARCES', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (4922, 14, 'LOS ARGUELLOS', '5777', 0);
INSERT INTO ubicacion_localidades VALUES (4923, 14, 'LOS ARROYOS', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4924, 14, 'LOS BARRIALES', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4925, 14, 'LOS CAJONES', '5871', 0);
INSERT INTO ubicacion_localidades VALUES (4926, 14, 'LOS CARRICITOS', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4927, 14, 'LOS CERRILLOS', '5715', 0);
INSERT INTO ubicacion_localidades VALUES (4928, 14, 'LOS CERRILLOS', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4929, 14, 'LOS CERRITOS', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4930, 14, 'LOS CESARES', '5738', 0);
INSERT INTO ubicacion_localidades VALUES (4931, 14, 'LOS CHAÃARES', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4932, 14, 'LOS CHAÃARES', '5835', 0);
INSERT INTO ubicacion_localidades VALUES (4933, 14, 'LOS CHAÃARES', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4934, 14, 'LOS CHAÃARES', '5777', 0);
INSERT INTO ubicacion_localidades VALUES (4935, 14, 'LOS CHAÃARITOS', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4936, 14, 'LOS CHANCAROS', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4937, 14, 'LOS CHENAS', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4938, 14, 'LOS CISNES', '5731', 0);
INSERT INTO ubicacion_localidades VALUES (4939, 14, 'LOS CLAVELES', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4940, 14, 'LOS COMEDEROS', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4941, 14, 'LOS COMEDORES', '5770', 0);
INSERT INTO ubicacion_localidades VALUES (4942, 14, 'LOS CONDORES', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (4943, 14, 'LOS COROS', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4944, 14, 'LOS CORRALES', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4945, 14, 'LOS CORRALITOS', '5759', 0);
INSERT INTO ubicacion_localidades VALUES (4946, 14, 'LOS CUADROS', '5835', 0);
INSERT INTO ubicacion_localidades VALUES (4947, 14, 'LOS DOS RIOS', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4948, 14, 'LOS DUEROS', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4949, 14, 'LOS DURAZNITOS', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (4950, 14, 'LOS DURAZNOS', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4951, 14, 'LOS ESPINILLOS', '5883', 0);
INSERT INTO ubicacion_localidades VALUES (4952, 14, 'LOS ESQUINEROS', '5738', 0);
INSERT INTO ubicacion_localidades VALUES (4953, 14, 'LOS HINOJOS', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4954, 14, 'LOS HUAYCOS', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4955, 14, 'LOS JAGUELES', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (4956, 14, 'LOS LECHUZONES', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4957, 14, 'LOS LOBOS', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (4958, 14, 'LOS LOBOS', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (4959, 14, 'LOS MANANTIALES', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (4960, 14, 'LOS MEDANITOS', '5751', 0);
INSERT INTO ubicacion_localidades VALUES (4961, 14, 'LOS MEDANOS', '5738', 0);
INSERT INTO ubicacion_localidades VALUES (4962, 14, 'LOS MENBRILLOS', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (4963, 14, 'LOS MENDOCINOS', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (4964, 14, 'LOS MOLLECITOS', '5759', 0);
INSERT INTO ubicacion_localidades VALUES (4965, 14, 'LOS MOLLES', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4966, 14, 'LOS MOLLES', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (4967, 14, 'LOS MOLLES', '5883', 0);
INSERT INTO ubicacion_localidades VALUES (4968, 14, 'LOS MOLLES', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4969, 14, 'LOS MONTES', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4970, 14, 'LOS NOQUES', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (4971, 14, 'LOS OSCUROS', '6277', 0);
INSERT INTO ubicacion_localidades VALUES (4972, 14, 'LOS PASITOS', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4973, 14, 'LOS PEJES', '5709', 0);
INSERT INTO ubicacion_localidades VALUES (4974, 14, 'LOS PEROS', '5777', 0);
INSERT INTO ubicacion_localidades VALUES (4975, 14, 'LOS POLEOS', '5770', 0);
INSERT INTO ubicacion_localidades VALUES (4976, 14, 'LOS POZOS', '5738', 0);
INSERT INTO ubicacion_localidades VALUES (4977, 14, 'LOS PUESTOS', '5770', 0);
INSERT INTO ubicacion_localidades VALUES (4978, 14, 'LOS PUQUIOS', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4979, 14, 'LOS QUEBRACHOS', '5770', 0);
INSERT INTO ubicacion_localidades VALUES (4980, 14, 'LOS RAMBLONES', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (4981, 14, 'LOS ROLDANES', '5777', 0);
INSERT INTO ubicacion_localidades VALUES (4982, 14, 'LOS SAUCES', '5775', 0);
INSERT INTO ubicacion_localidades VALUES (4983, 14, 'LOS SAUCES', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4984, 14, 'LOS TALAS', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4985, 14, 'LOS TALAS', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (4986, 14, 'LOS TAMARIÃOS', '5724', 0);
INSERT INTO ubicacion_localidades VALUES (4987, 14, 'LOS TAPIALES', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (4988, 14, 'LOS TELARIOS', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (4989, 14, 'LOS TIGRES', '5777', 0);
INSERT INTO ubicacion_localidades VALUES (4990, 14, 'LOS VALLES', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (4991, 14, 'LUJAN', '5709', 0);
INSERT INTO ubicacion_localidades VALUES (4992, 14, 'MACHAO', '6277', 0);
INSERT INTO ubicacion_localidades VALUES (4993, 14, 'MANANTIAL', '5709', 0);
INSERT INTO ubicacion_localidades VALUES (4994, 14, 'MANANTIAL', '5770', 0);
INSERT INTO ubicacion_localidades VALUES (4995, 14, 'MANANTIAL BLANCO', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (4996, 14, 'MANANTIAL DE FLORES', '5775', 0);
INSERT INTO ubicacion_localidades VALUES (4997, 14, 'MANANTIAL DE RENCA', '5759', 0);
INSERT INTO ubicacion_localidades VALUES (4998, 14, 'MANANTIAL GRANDE', '5751', 0);
INSERT INTO ubicacion_localidades VALUES (4999, 14, 'MANANTIAL LINDO', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (5000, 14, 'MANANTIALES', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (5001, 14, 'MANTILLA', '5724', 0);
INSERT INTO ubicacion_localidades VALUES (5002, 14, 'MARAVILLA', '5705', 0);
INSERT INTO ubicacion_localidades VALUES (5003, 14, 'MARAY', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (5004, 14, 'MARLITO', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (5005, 14, 'MARMOL VERDE', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (5006, 14, 'MARTIN DE LOYOLA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (5007, 14, 'MATACO', '5724', 0);
INSERT INTO ubicacion_localidades VALUES (5008, 14, 'MEDANO BALLO', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (5009, 14, 'MEDANO CHICO', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (5010, 14, 'MEDANO GRANDE', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (5011, 14, 'MEDANOS', '5736', 0);
INSERT INTO ubicacion_localidades VALUES (5012, 14, 'MEDIA LUNA', '5755', 0);
INSERT INTO ubicacion_localidades VALUES (5013, 14, 'MERCEDES', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (5014, 14, 'MERLO', '5881', 0);
INSERT INTO ubicacion_localidades VALUES (5015, 14, 'MILAGRO', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (5016, 14, 'MINA CAROLINA', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (5017, 14, 'MINA LOS CONDORES', '5770', 0);
INSERT INTO ubicacion_localidades VALUES (5018, 14, 'MINA SANTO DOMINGO', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (5019, 14, 'MOLLECITO', '5759', 0);
INSERT INTO ubicacion_localidades VALUES (5020, 14, 'MONTE CARMELO', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (5021, 14, 'MONTE CHIQUITO', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (5022, 14, 'MONTE COCHEQUINGAN', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (5023, 14, 'MONTE VERDE', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (5024, 14, 'MOSMOTA', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (5025, 14, 'MOYAR', '5777', 0);
INSERT INTO ubicacion_localidades VALUES (5026, 14, 'MOYARCITO', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (5027, 14, 'NAHUEL MAPA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (5028, 14, 'NARANJO', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (5029, 14, 'NASCHEL', '5759', 0);
INSERT INTO ubicacion_localidades VALUES (5030, 14, 'NAVIA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (5031, 14, 'NEGRO MUERTO', '5724', 0);
INSERT INTO ubicacion_localidades VALUES (5032, 14, 'NILINAST', '6277', 0);
INSERT INTO ubicacion_localidades VALUES (5033, 14, 'NO ES MIA', '5831', 0);
INSERT INTO ubicacion_localidades VALUES (5034, 14, 'NOGOLI', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (5035, 14, 'NOSSAR', '5741', 0);
INSERT INTO ubicacion_localidades VALUES (5036, 14, 'NUEVA CONSTITUCION', '5636', 0);
INSERT INTO ubicacion_localidades VALUES (5037, 14, 'NUEVA ESCOCIA', '5743', 0);
INSERT INTO ubicacion_localidades VALUES (5038, 14, 'NUEVA ESPERANZA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (5039, 14, 'NUEVA GALIA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (5040, 14, 'OJO DE AGUA', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (5041, 14, 'OJO DE AGUA', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (5042, 14, 'OJO DE AGUA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (5043, 14, 'OJO DEL RIO', '5777', 0);
INSERT INTO ubicacion_localidades VALUES (5044, 14, 'ONCE DE MAYO', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (5045, 14, 'OTRA BANDA', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (5046, 14, 'PAINES', '5736', 0);
INSERT INTO ubicacion_localidades VALUES (5047, 14, 'PAJE', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (5048, 14, 'PALIGUANTA', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (5049, 14, 'PALOMAR', '5724', 0);
INSERT INTO ubicacion_localidades VALUES (5050, 14, 'PAMPA', '5770', 0);
INSERT INTO ubicacion_localidades VALUES (5051, 14, 'PAMPA DE LOS GOBERNADORES', '5757', 0);
INSERT INTO ubicacion_localidades VALUES (5052, 14, 'PAMPA DEL BAJO', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (5053, 14, 'PAMPA DEL TAMBORERO', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (5054, 14, 'PAMPA GRANDE', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (5055, 14, 'PAMPA GRANDE', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (5056, 14, 'PAMPA INVERNADA', '5705', 0);
INSERT INTO ubicacion_localidades VALUES (5057, 14, 'PAMPITA', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (5058, 14, 'PANTANILLO', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (5059, 14, 'PANTANILLOS', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (5060, 14, 'PAPAGAYOS', '5883', 0);
INSERT INTO ubicacion_localidades VALUES (5061, 14, 'PARAISO', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (5062, 14, 'PASO ANCHO', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (5063, 14, 'PASO DE CUERO', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (5064, 14, 'PASO DE LA CRUZ', '5777', 0);
INSERT INTO ubicacion_localidades VALUES (5065, 14, 'PASO DE LA TIERRA', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (5066, 14, 'PASO DE LAS CARRETAS', '5736', 0);
INSERT INTO ubicacion_localidades VALUES (5067, 14, 'PASO DE LAS SALINAS', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (5068, 14, 'PASO DE LAS SIERRAS', '5873', 0);
INSERT INTO ubicacion_localidades VALUES (5069, 14, 'PASO DE LAS TOSCAS', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (5070, 14, 'PASO DE LAS VACAS', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (5071, 14, 'PASO DE LOS ALGARROBOS', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (5072, 14, 'PASO DE LOS BAYOS', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (5073, 14, 'PASO DE LOS GAUCHOS', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (5074, 14, 'PASO DE PIEDRA', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (5075, 14, 'PASO DEL MEDIO', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (5076, 14, 'PASO DEL REY', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (5077, 14, 'PASO GRANDE', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (5078, 14, 'PASO JUAN GOMEZ', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (5079, 14, 'PASO LOS ALGARROBOS', '5724', 0);
INSERT INTO ubicacion_localidades VALUES (5080, 14, 'PASTAL', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (5081, 14, 'PATIO LIMPIO', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (5082, 14, 'PEÃON COLORADO', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (5083, 14, 'PEDERNERA', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (5084, 14, 'PENICE', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (5085, 14, 'PESCADORES', '5700', 0);
INSERT INTO ubicacion_localidades VALUES (5086, 14, 'PICOS YACU', '5777', 0);
INSERT INTO ubicacion_localidades VALUES (5087, 14, 'PIE DE LA CUESTA', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (5088, 14, 'PIEDRA BLANCA', '5881', 0);
INSERT INTO ubicacion_localidades VALUES (5089, 14, 'PIEDRA BOLA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (5090, 14, 'PIEDRA LARGA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (5091, 14, 'PIEDRA ROSADA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (5092, 14, 'PIEDRA SOLA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (5093, 14, 'PIEDRAS ANCHAS', '5755', 0);
INSERT INTO ubicacion_localidades VALUES (5094, 14, 'PIEDRAS BLANCAS', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (5095, 14, 'PIEDRAS CHATAS', '5759', 0);
INSERT INTO ubicacion_localidades VALUES (5096, 14, 'PIQUILLINES', '5835', 0);
INSERT INTO ubicacion_localidades VALUES (5097, 14, 'PISCOYACO', '5775', 0);
INSERT INTO ubicacion_localidades VALUES (5098, 14, 'PIZARRAS BAJO VELEZ', '5777', 0);
INSERT INTO ubicacion_localidades VALUES (5099, 14, 'PLACILLA', '6277', 0);
INSERT INTO ubicacion_localidades VALUES (5100, 14, 'PLANTA DE SANDIA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (5101, 14, 'PLUMERITO', '5637', 0);
INSERT INTO ubicacion_localidades VALUES (5102, 14, 'POCITOS', '5715', 0);
INSERT INTO ubicacion_localidades VALUES (5103, 14, 'POLLEDO', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (5104, 14, 'PORTADA DEL SAUCE', '5741', 0);
INSERT INTO ubicacion_localidades VALUES (5105, 14, 'PORTEZUELO', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (5106, 14, 'PORVENIR', '5835', 0);
INSERT INTO ubicacion_localidades VALUES (5107, 14, 'POSTA DE FIERRO', '5831', 0);
INSERT INTO ubicacion_localidades VALUES (5108, 14, 'POSTA DEL PORTEZUELO', '5705', 0);
INSERT INTO ubicacion_localidades VALUES (5109, 14, 'POTRERILLO', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (5110, 14, 'POTRERO DE LOS FUNES', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (5111, 14, 'POZO CAVADO', '5705', 0);
INSERT INTO ubicacion_localidades VALUES (5112, 14, 'POZO CERCADO', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (5113, 14, 'POZO DE LAS RAICES', '5777', 0);
INSERT INTO ubicacion_localidades VALUES (5114, 14, 'POZO DE LOS RAYOS', '5705', 0);
INSERT INTO ubicacion_localidades VALUES (5115, 14, 'POZO DEL CARRIL', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (5116, 14, 'POZO DEL ESPINILLO', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (5117, 14, 'POZO DEL MEDIO', '5715', 0);
INSERT INTO ubicacion_localidades VALUES (5118, 14, 'POZO DEL MOLLE', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (5119, 14, 'POZO DEL TALA', '5705', 0);
INSERT INTO ubicacion_localidades VALUES (5120, 14, 'POZO ESCONDIDO', '5700', 0);
INSERT INTO ubicacion_localidades VALUES (5121, 14, 'POZO FRIO', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (5122, 14, 'POZO SANTIAGO', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (5123, 14, 'POZO SECO', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (5124, 14, 'POZO SIMON', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (5125, 14, 'PRIMER AGUA', '5831', 0);
INSERT INTO ubicacion_localidades VALUES (5126, 14, 'PUENTE HIERRO', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (5127, 14, 'PUENTE LA ORQUETA', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (5128, 14, 'PUERTA COLORADA', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (5129, 14, 'PUERTA DE LA ISLA', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (5130, 14, 'PUERTA DE PALO', '5770', 0);
INSERT INTO ubicacion_localidades VALUES (5131, 14, 'PUERTO ALEGRE', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (5132, 14, 'PUERTO RICO', '5705', 0);
INSERT INTO ubicacion_localidades VALUES (5133, 14, 'PUESTITO', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (5134, 14, 'PUESTO BELLA VISTA', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (5135, 14, 'PUESTO DE LOS JUMES', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (5136, 14, 'PUESTO DE TABARES', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (5137, 14, 'PUESTO EL TALA', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (5138, 14, 'PUESTO PAMPA INVERNADA', '5705', 0);
INSERT INTO ubicacion_localidades VALUES (5139, 14, 'PUESTO QUEBRADA CAL', '5705', 0);
INSERT INTO ubicacion_localidades VALUES (5140, 14, 'PUESTO ROBERTO', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (5141, 14, 'PUESTO TALAR', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (5142, 14, 'PUNILLA', '5831', 0);
INSERT INTO ubicacion_localidades VALUES (5143, 14, 'PUNTA DE AGUA', '5873', 0);
INSERT INTO ubicacion_localidades VALUES (5144, 14, 'PUNTA DE LA LOMA', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (5145, 14, 'PUNTA DE LA SIERRA', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (5146, 14, 'PUNTA DEL ALTO', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (5147, 14, 'PUNTA DEL CERRO', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (5148, 14, 'PUNTOS DE AGUA', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (5149, 14, 'PUNTOS DE LA LINEA', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (5150, 14, 'QUEBRACHITO', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (5151, 14, 'QUEBRADA DE LA BURRA', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (5152, 14, 'QUEBRADA DE LA MORA', '5755', 0);
INSERT INTO ubicacion_localidades VALUES (5153, 14, 'QUEBRADA DE LOS BARROSOS', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (5154, 14, 'QUEBRADA DE SAN VICENTE', '5770', 0);
INSERT INTO ubicacion_localidades VALUES (5155, 14, 'QUEBRADA DEL TIGRE', '5881', 0);
INSERT INTO ubicacion_localidades VALUES (5156, 14, 'QUEBRADA HONDA', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (5157, 14, 'QUINES', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (5158, 14, 'RAMADITA', '5705', 0);
INSERT INTO ubicacion_localidades VALUES (5159, 14, 'RAMBLONES', '5705', 0);
INSERT INTO ubicacion_localidades VALUES (5160, 14, 'RANQUELCO', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (5161, 14, 'REAL', '5831', 0);
INSERT INTO ubicacion_localidades VALUES (5162, 14, 'RECONQUISTA', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (5163, 14, 'RECREO', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (5164, 14, 'REFORMA CHICA', '5724', 0);
INSERT INTO ubicacion_localidades VALUES (5165, 14, 'RENCA', '5775', 0);
INSERT INTO ubicacion_localidades VALUES (5166, 14, 'REPRESA DEL CARMEN', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (5167, 14, 'REPRESA DEL CHAÃAR', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (5168, 14, 'REPRESA DEL MONTE', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (5169, 14, 'REPRESA DEL MONTE', '5700', 0);
INSERT INTO ubicacion_localidades VALUES (5170, 14, 'RETAMO', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (5171, 14, 'RETAZO DEL MONTE', '5759', 0);
INSERT INTO ubicacion_localidades VALUES (5172, 14, 'RETIRO', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (5173, 14, 'RIECITO', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (5174, 14, 'RINCON DEL CARMEN', '5779', 0);
INSERT INTO ubicacion_localidades VALUES (5175, 14, 'RINCON DEL ESTE', '5881', 0);
INSERT INTO ubicacion_localidades VALUES (5176, 14, 'RIO GRANDE', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (5177, 14, 'RIO JUAN GOMEZ', '5705', 0);
INSERT INTO ubicacion_localidades VALUES (5178, 14, 'RIO QUINTO', '5738', 0);
INSERT INTO ubicacion_localidades VALUES (5179, 14, 'RIOJITA', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (5180, 14, 'RODEO CADENAS', '5705', 0);
INSERT INTO ubicacion_localidades VALUES (5181, 14, 'ROMANCE', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (5182, 14, 'ROSALES', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (5183, 14, 'RUMIGUASI', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (5184, 14, 'SALADILLO', '5751', 0);
INSERT INTO ubicacion_localidades VALUES (5185, 14, 'SALADO', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (5186, 14, 'SALADO DE AMAYA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (5187, 14, 'SALINAS', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (5188, 14, 'SALINAS DEL BEBEDERO', '5724', 0);
INSERT INTO ubicacion_localidades VALUES (5189, 14, 'SALITRAL', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (5190, 14, 'SALTO CHICO', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (5191, 14, 'SAN AGUSTIN', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (5192, 14, 'SAN ALBERTO', '5831', 0);
INSERT INTO ubicacion_localidades VALUES (5193, 14, 'SAN ALEJANDRO', '5831', 0);
INSERT INTO ubicacion_localidades VALUES (5194, 14, 'SAN ANTONIO', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (5195, 14, 'SAN ANTONIO', '5835', 0);
INSERT INTO ubicacion_localidades VALUES (5196, 14, 'SAN ANTONIO', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (5197, 14, 'SAN ANTONIO', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (5198, 14, 'SAN ANTONIO', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (5199, 14, 'SAN ANTONIO', '5724', 0);
INSERT INTO ubicacion_localidades VALUES (5200, 14, 'SAN CAMILO', '5743', 0);
INSERT INTO ubicacion_localidades VALUES (5201, 14, 'SAN CARLOS', '6389', 0);
INSERT INTO ubicacion_localidades VALUES (5202, 14, 'SAN CELESTINO', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (5203, 14, 'SAN FCO DEL MONTE DE ORO', '5705', 0);
INSERT INTO ubicacion_localidades VALUES (5204, 14, 'SAN FELIPE', '5759', 0);
INSERT INTO ubicacion_localidades VALUES (5205, 14, 'SAN FERNANDO', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (5206, 14, 'SAN GERONIMO', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (5207, 14, 'SAN GERONIMO', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (5208, 14, 'SAN GREGORIO', '5751', 0);
INSERT INTO ubicacion_localidades VALUES (5209, 14, 'SAN IGNACIO', '5736', 0);
INSERT INTO ubicacion_localidades VALUES (5210, 14, 'SAN ISIDRO', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (5211, 14, 'SAN ISIDRO', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (5212, 14, 'SAN ISIDRO', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (5213, 14, 'SAN ISIDRO', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (5214, 14, 'SAN JORGE', '5715', 0);
INSERT INTO ubicacion_localidades VALUES (5215, 14, 'SAN JORGE', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (5216, 14, 'SAN JORGE', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (5217, 14, 'SAN JOSE', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (5218, 14, 'SAN JOSE', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (5219, 14, 'SAN JOSE', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (5220, 14, 'SAN JOSE', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (5221, 14, 'SAN JOSE DE LOS CHAÃARES', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (5222, 14, 'SAN JOSE DEL DURAZNO', '5741', 0);
INSERT INTO ubicacion_localidades VALUES (5223, 14, 'SAN JUAN', '6277', 0);
INSERT INTO ubicacion_localidades VALUES (5224, 14, 'SAN JUAN DE TASTU', '5731', 0);
INSERT INTO ubicacion_localidades VALUES (5225, 14, 'SAN LORENZO', '5757', 0);
INSERT INTO ubicacion_localidades VALUES (5226, 14, 'SAN LORENZO', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (5227, 14, 'SAN LUIS', '5700', 0);
INSERT INTO ubicacion_localidades VALUES (5228, 14, 'SAN MARTIN', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (5229, 14, 'SAN MARTIN', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (5230, 14, 'SAN MARTIN', '5755', 0);
INSERT INTO ubicacion_localidades VALUES (5231, 14, 'SAN MIGUEL', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (5232, 14, 'SAN MIGUEL', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (5233, 14, 'SAN MIGUEL', '5881', 0);
INSERT INTO ubicacion_localidades VALUES (5234, 14, 'SAN MIGUEL', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (5235, 14, 'SAN NICOLAS PUNILLA', '5831', 0);
INSERT INTO ubicacion_localidades VALUES (5236, 14, 'SAN PABLO', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (5237, 14, 'SAN PEDRO', '3611', 0);
INSERT INTO ubicacion_localidades VALUES (5238, 14, 'SAN PEDRO', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (5239, 14, 'SAN PEDRO', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (5240, 14, 'SAN PEDRO', '5831', 0);
INSERT INTO ubicacion_localidades VALUES (5241, 14, 'SAN PEDRO', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (5242, 14, 'SAN RAFAEL', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (5243, 14, 'SAN RAIMUNDO', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (5244, 14, 'SAN RAMON', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (5245, 14, 'SAN RAMON', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (5246, 14, 'SAN RAMON SUD', '5775', 0);
INSERT INTO ubicacion_localidades VALUES (5247, 14, 'SAN ROQUE', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (5248, 14, 'SAN ROQUE', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (5249, 14, 'SAN ROQUE', '5700', 0);
INSERT INTO ubicacion_localidades VALUES (5250, 14, 'SAN RUFINO', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (5251, 14, 'SAN SALVADOR', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (5252, 14, 'SAN VICENTE', '5770', 0);
INSERT INTO ubicacion_localidades VALUES (5253, 14, 'SAN VICENTE', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (5254, 14, 'SANT ANA', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (5255, 14, 'SANTA ANA', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (5256, 14, 'SANTA ANA', '5881', 0);
INSERT INTO ubicacion_localidades VALUES (5257, 14, 'SANTA CATALINA', '5738', 0);
INSERT INTO ubicacion_localidades VALUES (5258, 14, 'SANTA CECILIA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (5259, 14, 'SANTA CLARA', '5711', 0);
INSERT INTO ubicacion_localidades VALUES (5260, 14, 'SANTA CLARA', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (5261, 14, 'SANTA CLARA', '5751', 0);
INSERT INTO ubicacion_localidades VALUES (5262, 14, 'SANTA CLARA', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (5263, 14, 'SANTA CLARA', '5831', 0);
INSERT INTO ubicacion_localidades VALUES (5264, 14, 'SANTA CLARA', '5157', 0);
INSERT INTO ubicacion_localidades VALUES (5265, 14, 'SANTA CLARA', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (5266, 14, 'SANTA DIONISIA', '5722', 0);
INSERT INTO ubicacion_localidades VALUES (5267, 14, 'SANTA FELISA', '5831', 0);
INSERT INTO ubicacion_localidades VALUES (5268, 14, 'SANTA ISABEL', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (5269, 14, 'SANTA ISABEL', '5831', 0);
INSERT INTO ubicacion_localidades VALUES (5270, 14, 'SANTA ISABEL', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (5271, 14, 'SANTA LUCIA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (5272, 14, 'SANTA LUCIA', '5881', 0);
INSERT INTO ubicacion_localidades VALUES (5273, 14, 'SANTA LUCINDA', '5881', 0);
INSERT INTO ubicacion_localidades VALUES (5274, 14, 'SANTA MARIA', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (5275, 14, 'SANTA MARIA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (5276, 14, 'SANTA MARIA', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (5277, 14, 'SANTA MARTINA', '5770', 0);
INSERT INTO ubicacion_localidades VALUES (5278, 14, 'SANTA RITA', '5724', 0);
INSERT INTO ubicacion_localidades VALUES (5279, 14, 'SANTA ROSA', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (5280, 14, 'SANTA ROSA', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (5281, 14, 'SANTA ROSA DE CONLARA', '5777', 0);
INSERT INTO ubicacion_localidades VALUES (5282, 14, 'SANTA ROSA DEL GIGANTE', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (5283, 14, 'SANTA RUFINA', '5709', 0);
INSERT INTO ubicacion_localidades VALUES (5284, 14, 'SANTA SIMONA', '5770', 0);
INSERT INTO ubicacion_localidades VALUES (5285, 14, 'SANTA TERESA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (5286, 14, 'SANTA TERESA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (5287, 14, 'SANTA TERESA', '5700', 0);
INSERT INTO ubicacion_localidades VALUES (5288, 14, 'SANTA TERESITA', '5709', 0);
INSERT INTO ubicacion_localidades VALUES (5289, 14, 'SANTA VICTORIA', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (5290, 14, 'SANTO DOMINGO', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (5291, 14, 'SANTO DOMINGO', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (5292, 14, 'SANTO DOMINGO', '5700', 0);
INSERT INTO ubicacion_localidades VALUES (5293, 14, 'SANTO DOMINGO', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (5294, 14, 'SAUCE', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (5295, 14, 'SAUCESITO', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (5296, 14, 'SEIS DE SEPTIEMBRE', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (5297, 14, 'SELCI', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (5298, 14, 'SERAFINA', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (5299, 14, 'SOCOSCORA', '5705', 0);
INSERT INTO ubicacion_localidades VALUES (5300, 14, 'SOL DE ABRIL', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (5301, 14, 'SOL DE ABRIL', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (5302, 14, 'SOL DE ABRIL', '5757', 0);
INSERT INTO ubicacion_localidades VALUES (5303, 14, 'SOL DE ABRIL (DPTO-SAN MARTIN)', '5755', 0);
INSERT INTO ubicacion_localidades VALUES (5304, 14, 'SOLOBASTA', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (5305, 14, 'SOLOLOSTA', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (5306, 14, 'TALA VERDE', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (5307, 14, 'TALARCITO', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (5308, 14, 'TALITA', '5881', 0);
INSERT INTO ubicacion_localidades VALUES (5309, 14, 'TAMASCANES', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (5310, 14, 'TAMBOREO', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (5311, 14, 'TASTO', '5731', 0);
INSERT INTO ubicacion_localidades VALUES (5312, 14, 'TAZA BLANCA', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (5313, 14, 'TEMERARIA', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (5314, 14, 'TILISARAO', '5773', 0);
INSERT INTO ubicacion_localidades VALUES (5315, 14, 'TINTITACO', '5705', 0);
INSERT INTO ubicacion_localidades VALUES (5316, 14, 'TOIGUS', '5831', 0);
INSERT INTO ubicacion_localidades VALUES (5317, 14, 'TOINGUA', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (5318, 14, 'TORO BAYO', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (5319, 14, 'TORO NEGRO', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (5320, 14, 'TOSCAL', '5636', 0);
INSERT INTO ubicacion_localidades VALUES (5321, 14, 'TOTORAL', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (5322, 14, 'TOTORAL', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (5323, 14, 'TOTORILLA', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (5324, 14, 'TRANSVAL', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (5325, 14, 'TRAPICHE', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (5326, 14, 'TRAVESIA', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (5327, 14, 'TRECE DE ENERO', '5741', 0);
INSERT INTO ubicacion_localidades VALUES (5328, 14, 'TREINTA DE OCTUBRE', '5637', 0);
INSERT INTO ubicacion_localidades VALUES (5329, 14, 'TRES CAÃADAS', '5713', 0);
INSERT INTO ubicacion_localidades VALUES (5330, 14, 'TRES CAÃADAS', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (5331, 14, 'TRES LOMAS', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (5332, 14, 'TRES MARIAS', '5700', 0);
INSERT INTO ubicacion_localidades VALUES (5333, 14, 'TRES PUERTAS', '5719', 0);
INSERT INTO ubicacion_localidades VALUES (5334, 14, 'TUKIROS', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (5335, 14, 'UCHAIMA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (5336, 14, 'UNION', '6216', 0);
INSERT INTO ubicacion_localidades VALUES (5337, 14, 'UNQUILLO', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (5338, 14, 'USIYAL', '5637', 0);
INSERT INTO ubicacion_localidades VALUES (5339, 14, 'USPARA', '5835', 0);
INSERT INTO ubicacion_localidades VALUES (5340, 14, 'VACAS MUERTAS', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (5341, 14, 'VALLE DE LA PANCANTA', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (5342, 14, 'VALLE HERMOSO', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (5343, 14, 'VALLE SAN AGUSTIN', '5775', 0);
INSERT INTO ubicacion_localidades VALUES (5344, 14, 'VALLE SAN JOSE', '5775', 0);
INSERT INTO ubicacion_localidades VALUES (5345, 14, 'VALLECITO', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (5346, 14, 'VARELA', '5721', 0);
INSERT INTO ubicacion_localidades VALUES (5347, 14, 'VENTA DE LOS RIOS', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (5348, 14, 'VIEJA ESTANCIA', '5771', 0);
INSERT INTO ubicacion_localidades VALUES (5349, 14, 'VILLA DE LA QUEBRADA', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (5350, 14, 'VILLA DE PRAGA', '5753', 0);
INSERT INTO ubicacion_localidades VALUES (5351, 14, 'VILLA DEL CARMEN', '5835', 0);
INSERT INTO ubicacion_localidades VALUES (5352, 14, 'VILLA DOLORES', '5770', 0);
INSERT INTO ubicacion_localidades VALUES (5353, 14, 'VILLA ELENA', '5883', 0);
INSERT INTO ubicacion_localidades VALUES (5354, 14, 'VILLA GENERAL ROCA', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (5355, 14, 'VILLA LARCA', '5883', 0);
INSERT INTO ubicacion_localidades VALUES (5356, 14, 'VILLA LUISA', '5881', 0);
INSERT INTO ubicacion_localidades VALUES (5357, 14, 'VILLA REYNOLDS', '5733', 0);
INSERT INTO ubicacion_localidades VALUES (5358, 14, 'VILLA SANTIAGO', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (5359, 14, 'VIRARCO', '5701', 0);
INSERT INTO ubicacion_localidades VALUES (5360, 14, 'VISCACHERAS', '5731', 0);
INSERT INTO ubicacion_localidades VALUES (5361, 14, 'VISTA ALEGRE', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (5362, 14, 'VISTA HERMOSA', '5730', 0);
INSERT INTO ubicacion_localidades VALUES (5363, 14, 'VIVA LA PATRIA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (5364, 14, 'VIZCACHERAS', '5703', 0);
INSERT INTO ubicacion_localidades VALUES (5365, 14, 'VOLCAN ESTANZUELA', '5835', 0);
INSERT INTO ubicacion_localidades VALUES (5366, 14, 'YACORO', '5750', 0);
INSERT INTO ubicacion_localidades VALUES (5367, 14, 'ZAMPAL', '5707', 0);
INSERT INTO ubicacion_localidades VALUES (5368, 15, '12 DE OCTUBRE', '5596', 0);
INSERT INTO ubicacion_localidades VALUES (5369, 15, '25 DE MAYO', '5615', 0);
INSERT INTO ubicacion_localidades VALUES (5370, 15, '3 DE MAYO', '5543', 0);
INSERT INTO ubicacion_localidades VALUES (5371, 15, '9 DE JULIO', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (5372, 15, 'ÃACUÃAN', '5595', 0);
INSERT INTO ubicacion_localidades VALUES (5373, 15, 'ÃANCUÃAN', '5595', 0);
INSERT INTO ubicacion_localidades VALUES (5374, 15, 'ADRIAN MATURANO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5375, 15, 'AGRELO', '5509', 0);
INSERT INTO ubicacion_localidades VALUES (5376, 15, 'AGUA AMARGA', '5563', 0);
INSERT INTO ubicacion_localidades VALUES (5377, 15, 'AGUA BOTADA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5378, 15, 'AGUA DE CABRERA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5379, 15, 'AGUA DE DIAZ', '5545', 0);
INSERT INTO ubicacion_localidades VALUES (5380, 15, 'AGUA DE LA MULA', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (5381, 15, 'AGUA DE LOS MANANTIALES', '5549', 0);
INSERT INTO ubicacion_localidades VALUES (5382, 15, 'AGUA DEL CHANCHO', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5383, 15, 'AGUA ESCONDIDA', '5621', 0);
INSERT INTO ubicacion_localidades VALUES (5384, 15, 'AGUA NUEVA', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (5385, 15, 'AGUA RICA', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (5386, 15, 'AGUADA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5387, 15, 'AGUADA DE TORO', '5621', 0);
INSERT INTO ubicacion_localidades VALUES (5388, 15, 'AGUADA PENEPE', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5389, 15, 'AGUADA PEREZ', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5390, 15, 'AGUADA PUESTO LA TOTORA', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (5391, 15, 'ALBERTO FLORES', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (5392, 15, 'ALEJANDRO PEREZ', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5393, 15, 'ALFREDO LUCERO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5394, 15, 'ALGARROBAL ABAJO', '5541', 0);
INSERT INTO ubicacion_localidades VALUES (5395, 15, 'ALGARROBAL ARRIBA', '5541', 0);
INSERT INTO ubicacion_localidades VALUES (5396, 15, 'ALGARROBITO', '5537', 0);
INSERT INTO ubicacion_localidades VALUES (5397, 15, 'ALGARROBO', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (5398, 15, 'ALGARROBO DE SORTUE', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (5399, 15, 'ALGARROBO GRANDE', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (5400, 15, 'ALPATACAL', '5591', 0);
INSERT INTO ubicacion_localidades VALUES (5401, 15, 'ALTO AMARILLO', '5537', 0);
INSERT INTO ubicacion_localidades VALUES (5402, 15, 'ALTO CON ZAMPA', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5403, 15, 'ALTO DE LAS ARAÃAS', '5519', 0);
INSERT INTO ubicacion_localidades VALUES (5404, 15, 'ALTO DE LOS MANANTIALES', '5549', 0);
INSERT INTO ubicacion_localidades VALUES (5405, 15, 'ALTO DE LOS PERROS', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5406, 15, 'ALTO DE LOS SAPOS', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5407, 15, 'ALTO DEL OLVIDO', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (5408, 15, 'ALTO DEL PLOMO', '5549', 0);
INSERT INTO ubicacion_localidades VALUES (5409, 15, 'ALTO DEL SALVADOR', '5570', 0);
INSERT INTO ubicacion_localidades VALUES (5410, 15, 'ALTO GRANDE', '5572', 0);
INSERT INTO ubicacion_localidades VALUES (5411, 15, 'ALTO GRANDE', '5543', 0);
INSERT INTO ubicacion_localidades VALUES (5412, 15, 'ALTO TRES COMPADRES', '5537', 0);
INSERT INTO ubicacion_localidades VALUES (5413, 15, 'ALTO VERDE', '5572', 0);
INSERT INTO ubicacion_localidades VALUES (5414, 15, 'ALTO VERDE', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (5415, 15, 'ALVAREZ', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5416, 15, 'ALVAREZ CONDARCO', '5549', 0);
INSERT INTO ubicacion_localidades VALUES (5417, 15, 'ANA DE DONAIRE', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5418, 15, 'ANACLETA VIUDA DE PEREZ', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5419, 15, 'ANCHORIS', '5509', 0);
INSERT INTO ubicacion_localidades VALUES (5420, 15, 'ANCON', '5561', 0);
INSERT INTO ubicacion_localidades VALUES (5421, 15, 'ANDRADE', '5575', 0);
INSERT INTO ubicacion_localidades VALUES (5422, 15, 'ANDRES PEREZ', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5423, 15, 'ANTONIO SOSA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5424, 15, 'ARAGANITA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5425, 15, 'ARANCIBIA', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5426, 15, 'ARANZABAL', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5427, 15, 'ARBOLEDA', '5561', 0);
INSERT INTO ubicacion_localidades VALUES (5428, 15, 'ARENALES', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5429, 15, 'ARISTIDES VILLANUEVA', '5609', 0);
INSERT INTO ubicacion_localidades VALUES (5430, 15, 'ARROYITO', '5537', 0);
INSERT INTO ubicacion_localidades VALUES (5431, 15, 'ARROYO CLARO', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (5432, 15, 'ARROYO LOS SAUCES', '5563', 0);
INSERT INTO ubicacion_localidades VALUES (5433, 15, 'ASUNCION', '5535', 0);
INSERT INTO ubicacion_localidades VALUES (5434, 15, 'ATUEL SUD', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (5435, 15, 'AZCURRA', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (5436, 15, 'B DE ARAYA', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5437, 15, 'B DE QUEBRADO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5438, 15, 'B ELENA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5439, 15, 'BAÃADO VERDE', '5598', 0);
INSERT INTO ubicacion_localidades VALUES (5440, 15, 'BAÃOS DE AZUFRE', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (5441, 15, 'BAÃOS DE CAPIZ', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5442, 15, 'BAÃOS LA SALADA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5443, 15, 'BAÃOS LUNLUNTA', '5507', 0);
INSERT INTO ubicacion_localidades VALUES (5444, 15, 'BAJADA ARAUJO', '5535', 0);
INSERT INTO ubicacion_localidades VALUES (5445, 15, 'BAJADA DE LA SALADA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5446, 15, 'BAJADA DE LOS GAUCHOS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5447, 15, 'BAJADA DE LOS PAPAGAYOS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5448, 15, 'BAJADA DE YAUCHA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5449, 15, 'BAJADA DEL AGUA ESCONDIDA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5450, 15, 'BAJADA DEL FUERTE', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5451, 15, 'BAJADA DEL PERRO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5452, 15, 'BAJADA DEL SAUCE', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5453, 15, 'BAJO DEL PELUDO', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5454, 15, 'BAJO DEL YUYO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5455, 15, 'BALDE DE LA JARILLA', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (5456, 15, 'BALDE DE LOS GAUCHOS', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (5457, 15, 'BALDE DE PIEDRA', '5596', 0);
INSERT INTO ubicacion_localidades VALUES (5458, 15, 'BALDE E AQUERA', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (5459, 15, 'BALDE EL SOSNEADO', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (5460, 15, 'BALDE JOFRE', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (5461, 15, 'BALDE LA PICHANA VIEJA', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (5462, 15, 'BALDE LAS CARPAS', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (5463, 15, 'BALDE LAS CATITAS', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (5464, 15, 'BALDE LAS LAGUNITAS', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5465, 15, 'BALDE NUEVO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5466, 15, 'BALDE SAN AGUSTIN', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (5467, 15, 'BANDERITA', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5468, 15, 'BARCALA', '5587', 0);
INSERT INTO ubicacion_localidades VALUES (5469, 15, 'BARDAS BLANCAS', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (5470, 15, 'BARRANCAS', '5517', 0);
INSERT INTO ubicacion_localidades VALUES (5471, 15, 'BARREAL COLORADO', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5472, 15, 'BARREAL DE LA MESILLA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5473, 15, 'BARREAL DE LA PAMPA SECA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5474, 15, 'BARREAL JOSE LUIS', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5475, 15, 'BARREAL PAJARO MUERTO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5476, 15, 'BARRIALES LOS RANCHOS', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5477, 15, 'BARRIO FERRI', '5531', 0);
INSERT INTO ubicacion_localidades VALUES (5478, 15, 'BARRIO JARDIN LUZURIAGA', '5513', 0);
INSERT INTO ubicacion_localidades VALUES (5479, 15, 'BARRIO LENCINA', '5579', 0);
INSERT INTO ubicacion_localidades VALUES (5480, 15, 'BARRIO RIVADAVIA', '5579', 0);
INSERT INTO ubicacion_localidades VALUES (5481, 15, 'BARRIO SAN EDUARDO', '5513', 0);
INSERT INTO ubicacion_localidades VALUES (5482, 15, 'BARRIO VILLA ADELA', '5584', 0);
INSERT INTO ubicacion_localidades VALUES (5483, 15, 'BECERRA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5484, 15, 'BELLE VILLE', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5485, 15, 'BELTRAN', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5486, 15, 'BERMEJO', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (5487, 15, 'BLANCO ENCALADA', '5549', 0);
INSERT INTO ubicacion_localidades VALUES (5488, 15, 'BLAS PANELO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5489, 15, 'BOLICHE', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5490, 15, 'BOLICHE LOS BARREALES', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (5491, 15, 'BORDE DE LA LINEA', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5492, 15, 'BORDO AMARILLO DE LA CRUZ PIED', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5493, 15, 'BORDO EL ALGARROBO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5494, 15, 'BORDO LECHUZO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5495, 15, 'BORDOS DEL PLUMERILLO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5496, 15, 'BOWEN', '5634', 0);
INSERT INTO ubicacion_localidades VALUES (5497, 15, 'BOYEROS', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5498, 15, 'BUEN ORDEN', '5571', 0);
INSERT INTO ubicacion_localidades VALUES (5499, 15, 'BUENA NUEVA', '5523', 0);
INSERT INTO ubicacion_localidades VALUES (5500, 15, 'BUENA VISTA', '5525', 0);
INSERT INTO ubicacion_localidades VALUES (5501, 15, 'BUITRERA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5502, 15, 'BUTA BILLON', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5503, 15, 'C GONZALES VIDELA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5504, 15, 'CA DEL DIABLO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5505, 15, 'CAÃADA AMARILLA', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (5506, 15, 'CAÃADA ANCHA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5507, 15, 'CAÃADA COLORADA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5508, 15, 'CAÃADA SECA', '5603', 0);
INSERT INTO ubicacion_localidades VALUES (5509, 15, 'CAÃADITA ALEGRE', '5519', 0);
INSERT INTO ubicacion_localidades VALUES (5510, 15, 'CAÃADON DE BONILLA', '5621', 0);
INSERT INTO ubicacion_localidades VALUES (5511, 15, 'CABEZA DE VACA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5512, 15, 'CACHEUTA', '5549', 0);
INSERT INTO ubicacion_localidades VALUES (5513, 15, 'CADETES DE CHILE', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5514, 15, 'CAJON DE MAYO', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (5515, 15, 'CAJON GRANDE', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5516, 15, 'CALLE LARGA VIEJA', '5605', 0);
INSERT INTO ubicacion_localidades VALUES (5517, 15, 'CALLE TERRADA', '5507', 0);
INSERT INTO ubicacion_localidades VALUES (5518, 15, 'CALMUCO', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (5519, 15, 'CAMP VIZCACHERAS YPF', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5520, 15, 'CAMPAMENTO CACHEUTA YPF', '5549', 0);
INSERT INTO ubicacion_localidades VALUES (5521, 15, 'CAMPAMENTO CARAPACHO', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (5522, 15, 'CAMPAMENTO RANQUILCO', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5523, 15, 'CAMPAMENTOS', '5579', 0);
INSERT INTO ubicacion_localidades VALUES (5524, 15, 'CAMPO DE LOS ANDES', '5565', 0);
INSERT INTO ubicacion_localidades VALUES (5525, 15, 'CAMPO EL ALAMO', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5526, 15, 'CAMPO EL TORO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5527, 15, 'CANAL PESCARA', '5525', 0);
INSERT INTO ubicacion_localidades VALUES (5528, 15, 'CANALEJAS', '5636', 0);
INSERT INTO ubicacion_localidades VALUES (5529, 15, 'CANCHA DE ESQUI', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5530, 15, 'CAPDEVILLE', '5543', 0);
INSERT INTO ubicacion_localidades VALUES (5531, 15, 'CAPILLA DEL COVADITO', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (5532, 15, 'CAPILLA DEL ROSARIO', '5523', 0);
INSERT INTO ubicacion_localidades VALUES (5533, 15, 'CAPILLA SAN JOSE', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (5534, 15, 'CAPITAN MONTOYA', '5601', 0);
INSERT INTO ubicacion_localidades VALUES (5535, 15, 'CAPIZ', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (5536, 15, 'CARACOLES', '5557', 0);
INSERT INTO ubicacion_localidades VALUES (5537, 15, 'CARBOMETAL', '5505', 0);
INSERT INTO ubicacion_localidades VALUES (5538, 15, 'CARLOS SUBERCASEUX', '5549', 0);
INSERT INTO ubicacion_localidades VALUES (5539, 15, 'CARMENSA', '5621', 0);
INSERT INTO ubicacion_localidades VALUES (5540, 15, 'CARRIL NORTE', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (5541, 15, 'CARRIZAL  DE  ARRIBA', '5509', 0);
INSERT INTO ubicacion_localidades VALUES (5542, 15, 'CARRIZAL DE ABAJO', '5509', 0);
INSERT INTO ubicacion_localidades VALUES (5543, 15, 'CARRODILLA  LA PUNTILLA', '5505', 0);
INSERT INTO ubicacion_localidades VALUES (5544, 15, 'CARTELLONE', '5531', 0);
INSERT INTO ubicacion_localidades VALUES (5545, 15, 'CASA DE ADOBE', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5546, 15, 'CASA DE LAS PEÃAS', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (5547, 15, 'CASA DE PIEDRA', '5549', 0);
INSERT INTO ubicacion_localidades VALUES (5548, 15, 'CASAS VIEJAS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5549, 15, 'CASILLA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5550, 15, 'CATITAS VIEJAS', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5551, 15, 'CENTRAL HIDROELECTRICA 1', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (5552, 15, 'CENTRAL HIDROELECTRICA 2', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (5553, 15, 'CEPILLO VIEJO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5554, 15, 'CERRILLOS', '5509', 0);
INSERT INTO ubicacion_localidades VALUES (5555, 15, 'CERRILLOS AL NORTE', '5509', 0);
INSERT INTO ubicacion_localidades VALUES (5556, 15, 'CERRILLOS AL SUD', '5509', 0);
INSERT INTO ubicacion_localidades VALUES (5557, 15, 'CERRILLOS NEGROS', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5558, 15, 'CERRITO MORO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5559, 15, 'CERRO ACONCAGUA', '5500', 0);
INSERT INTO ubicacion_localidades VALUES (5560, 15, 'CERRO AGUA NEGRA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5561, 15, 'CERRO AGUA SALADA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5562, 15, 'CERRO AGUADITA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5563, 15, 'CERRO ALOJAMIENTO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5564, 15, 'CERRO ALQUITRAN', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (5565, 15, 'CERRO ALTO DE LAS PEÃAS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5566, 15, 'CERRO ALVARADO CENTRO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5567, 15, 'CERRO ANGOSTURA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5568, 15, 'CERRO ARROYO HONDO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5569, 15, 'CERRO ASPERO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5570, 15, 'CERRO BALEADO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5571, 15, 'CERRO BARAUCA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5572, 15, 'CERRO BARBARAN O TRES PICOS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5573, 15, 'CERRO BAY', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5574, 15, 'CERRO BLANCO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5575, 15, 'CERRO BONETE', '5545', 0);
INSERT INTO ubicacion_localidades VALUES (5576, 15, 'CERRO BRAVO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5577, 15, 'CERRO CATEDRAL', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5578, 15, 'CERRO CHATO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5579, 15, 'CERRO CHIQUERO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5580, 15, 'CERRO CIELO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5581, 15, 'CERRO CIENAGA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5582, 15, 'CERRO CLEMENTINO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5583, 15, 'CERRO COLOR', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5584, 15, 'CERRO COLORADO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5585, 15, 'CERRO COLORADO DE LAS LAGUNILL', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5586, 15, 'CERRO CORTADERAS', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5587, 15, 'CERRO CUERNO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5588, 15, 'CERRO CUPULA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5589, 15, 'CERRO DE LA BANDEROLA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5590, 15, 'CERRO DE LAS LEÃAS', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5591, 15, 'CERRO DE LOS BURROS', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5592, 15, 'CERRO DE LOS DEDOS', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5593, 15, 'CERRO DE LOS LEONES', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5594, 15, 'CERRO DE LOS POTRERILLOS', '5500', 0);
INSERT INTO ubicacion_localidades VALUES (5595, 15, 'CERRO DEL CHACAY', '5621', 0);
INSERT INTO ubicacion_localidades VALUES (5596, 15, 'CERRO DEL MEDIO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5597, 15, 'CERRO DEL POZO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5598, 15, 'CERRO DEL RINCON BAYO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5599, 15, 'CERRO DEL ZORRO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5600, 15, 'CERRO DIVISADERO DE LA CIENEGU', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5601, 15, 'CERRO DURAZNO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5602, 15, 'CERRO EL GUANACO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5603, 15, 'CERRO FIERO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5604, 15, 'CERRO FUNDICION', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5605, 15, 'CERRO GASPAR', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5606, 15, 'CERRO GRANDE', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5607, 15, 'CERRO GUADALOSO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5608, 15, 'CERRO HORQUETA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5609, 15, 'CERRO INVERNADA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5610, 15, 'CERRO JUAN POBRE', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5611, 15, 'CERRO L CORRALES', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5612, 15, 'CERRO LA INVERNADA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5613, 15, 'CERRO LA MANO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5614, 15, 'CERRO LAGAÃOSO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5615, 15, 'CERRO LAS PIEDRAS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5616, 15, 'CERRO LOS BAJOS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5617, 15, 'CERRO LOS BARROS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5618, 15, 'CERRO LOS DIENTITOS', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5619, 15, 'CERRO MANANTIAL', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5620, 15, 'CERRO MASILLAS', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5621, 15, 'CERRO MELOCOTON', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5622, 15, 'CERRO MEXICO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5623, 15, 'CERRO MONTURA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5624, 15, 'CERRO NEGROS DE LAS MESILLAS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5625, 15, 'CERRO NEVADO', '5610', 0);
INSERT INTO ubicacion_localidades VALUES (5626, 15, 'CERRO NEVADO', '5621', 0);
INSERT INTO ubicacion_localidades VALUES (5627, 15, 'CERRO PAMPA SECA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5628, 15, 'CERRO PAN DE AZUCAR', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5629, 15, 'CERRO PANTA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5630, 15, 'CERRO PELADO', '5549', 0);
INSERT INTO ubicacion_localidades VALUES (5631, 15, 'CERRO PIEDRA COLORADA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5632, 15, 'CERRO PLOMO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5633, 15, 'CERRO PONDERADO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5634, 15, 'CERRO POTRERILLOS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5635, 15, 'CERRO POZO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5636, 15, 'CERRO PUNTA DE AGUA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5637, 15, 'CERRO PUNTILLA NEGRA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5638, 15, 'CERRO PUNTUDO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5639, 15, 'CERRO PUQUIOS', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5640, 15, 'CERRO RIQUITIPANCHE', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5641, 15, 'CERRO SAN LORENZO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5642, 15, 'CERRO SANTA MARIA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5643, 15, 'CERRO SAPO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5644, 15, 'CERRO TIGRE', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5645, 15, 'CERRO TOLOSA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5646, 15, 'CERRO TUNDUQUERA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5647, 15, 'CERRO YALGUARAS', '5621', 0);
INSERT INTO ubicacion_localidades VALUES (5648, 15, 'CERRO YARETA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5649, 15, 'CERROS COLORADOS', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5650, 15, 'CESPEDES', '5517', 0);
INSERT INTO ubicacion_localidades VALUES (5651, 15, 'CHAÃARAL REDONDO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5652, 15, 'CHACHAO', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5653, 15, 'CHACHARALEN', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (5654, 15, 'CHACHINGO', '5517', 0);
INSERT INTO ubicacion_localidades VALUES (5655, 15, 'CHACRAS DE CORIA', '5505', 0);
INSERT INTO ubicacion_localidades VALUES (5656, 15, 'CHACRAS DE LIMA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5657, 15, 'CHAMUSCAO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5658, 15, 'CHAPANAY', '5589', 0);
INSERT INTO ubicacion_localidades VALUES (5659, 15, 'CHARCO VACAS', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5660, 15, 'CHILECITO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5661, 15, 'CHILOTE', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5662, 15, 'CHIMBA', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (5663, 15, 'CHIVILCOY', '5571', 0);
INSERT INTO ubicacion_localidades VALUES (5664, 15, 'CIENAGA DEL ALTO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5665, 15, 'CIRCUNVALACION', '5591', 0);
INSERT INTO ubicacion_localidades VALUES (5666, 15, 'CIRILO MAHONA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5667, 15, 'CIUDAD DE JUNIN', '5573', 0);
INSERT INTO ubicacion_localidades VALUES (5668, 15, 'CIUDAD OESTE', '5634', 0);
INSERT INTO ubicacion_localidades VALUES (5669, 15, 'CLARENTINO ROLDAN', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5670, 15, 'CLODOMIRO RETA', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5671, 15, 'COCHICO', '5621', 0);
INSERT INTO ubicacion_localidades VALUES (5672, 15, 'COIHUECO', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (5673, 15, 'COIHUECO NORTE', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (5674, 15, 'COLA MORA', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (5675, 15, 'COLON SANDALHO', '5545', 0);
INSERT INTO ubicacion_localidades VALUES (5676, 15, 'COLONIA 3 DE MAYO', '5543', 0);
INSERT INTO ubicacion_localidades VALUES (5677, 15, 'COLONIA ALAZANES', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5678, 15, 'COLONIA ALEMANA', '5543', 0);
INSERT INTO ubicacion_localidades VALUES (5679, 15, 'COLONIA ALVEAR', '5632', 0);
INSERT INTO ubicacion_localidades VALUES (5680, 15, 'COLONIA ALVEAR OESTE', '5632', 0);
INSERT INTO ubicacion_localidades VALUES (5681, 15, 'COLONIA ANDRE', '5535', 0);
INSERT INTO ubicacion_localidades VALUES (5682, 15, 'COLONIA ASPERA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5683, 15, 'COLONIA ATUEL', '5623', 0);
INSERT INTO ubicacion_localidades VALUES (5684, 15, 'COLONIA ATUEL NORTE', '5605', 0);
INSERT INTO ubicacion_localidades VALUES (5685, 15, 'COLONIA BARRAQUERO', '5509', 0);
INSERT INTO ubicacion_localidades VALUES (5686, 15, 'COLONIA BOMBAL', '5529', 0);
INSERT INTO ubicacion_localidades VALUES (5687, 15, 'COLONIA BOMBAL Y TABANERA', '5607', 0);
INSERT INTO ubicacion_localidades VALUES (5688, 15, 'COLONIA BOUQUET', '5632', 0);
INSERT INTO ubicacion_localidades VALUES (5689, 15, 'COLONIA CANO', '5507', 0);
INSERT INTO ubicacion_localidades VALUES (5690, 15, 'COLONIA CHALET', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5691, 15, 'COLONIA CHATO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5692, 15, 'COLONIA COLINA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5693, 15, 'COLONIA COLOMER', '5603', 0);
INSERT INTO ubicacion_localidades VALUES (5694, 15, 'COLONIA CURNIÃAN', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5695, 15, 'COLONIA DE LAS MULAS', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (5696, 15, 'COLONIA DE LOS GUANAQUEROS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5697, 15, 'COLONIA DEL CARMEN', '5535', 0);
INSERT INTO ubicacion_localidades VALUES (5698, 15, 'COLONIA DEL DIABLO', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (5699, 15, 'COLONIA DEL LEON', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5700, 15, 'COLONIA DELFINO', '5573', 0);
INSERT INTO ubicacion_localidades VALUES (5701, 15, 'COLONIA DIVISADERO DEL CARDAL', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5702, 15, 'COLONIA DIVISADERO NEGRO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5703, 15, 'COLONIA DURAZNO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5704, 15, 'COLONIA EL CAMPANARIO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5705, 15, 'COLONIA EL REGADIO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5706, 15, 'COLONIA ELENA', '5603', 0);
INSERT INTO ubicacion_localidades VALUES (5707, 15, 'COLONIA ESPAÃOLA', '5607', 0);
INSERT INTO ubicacion_localidades VALUES (5708, 15, 'COLONIA ESTRELLA', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (5709, 15, 'COLONIA FARO', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (5710, 15, 'COLONIA FUNES', '5509', 0);
INSERT INTO ubicacion_localidades VALUES (5711, 15, 'COLONIA GUADAL', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5712, 15, 'COLONIA GUANACO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5713, 15, 'COLONIA ITALIANA', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (5714, 15, 'COLONIA JARA', '5529', 0);
INSERT INTO ubicacion_localidades VALUES (5715, 15, 'COLONIA JAUREGUI', '5622', 0);
INSERT INTO ubicacion_localidades VALUES (5716, 15, 'COLONIA LA ESCONDIDA', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (5717, 15, 'COLONIA LA LLAVE', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (5718, 15, 'COLONIA LA TORRECILLA', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (5719, 15, 'COLONIA LAMBARE', '5571', 0);
INSERT INTO ubicacion_localidades VALUES (5720, 15, 'COLONIA LAS ROSAS', '5565', 0);
INSERT INTO ubicacion_localidades VALUES (5721, 15, 'COLONIA LOLA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5722, 15, 'COLONIA LOPEZ', '5622', 0);
INSERT INTO ubicacion_localidades VALUES (5723, 15, 'COLONIA LOS HUEVOS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5724, 15, 'COLONIA LOS OSCUROS', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (5725, 15, 'COLONIA LOS TAPONES', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (5726, 15, 'COLONIA MIRADOR', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5727, 15, 'COLONIA MONTE CASEROS', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (5728, 15, 'COLONIA MURALLA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5729, 15, 'COLONIA NACIONAL DE LOS INDIOS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5730, 15, 'COLONIA NEGRO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5731, 15, 'COLONIA OSAMENTA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5732, 15, 'COLONIA PAPAL', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5733, 15, 'COLONIA PASCUAL IACARINI', '5615', 0);
INSERT INTO ubicacion_localidades VALUES (5734, 15, 'COLONIA PEDERNALES', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5735, 15, 'COLONIA PENCAL', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5736, 15, 'COLONIA PICO COLORADO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5737, 15, 'COLONIA REINA', '5584', 0);
INSERT INTO ubicacion_localidades VALUES (5738, 15, 'COLONIA RUSA', '5603', 0);
INSERT INTO ubicacion_localidades VALUES (5739, 15, 'COLONIA SAN AGUSTIN', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5740, 15, 'COLONIA SAN FRANCISCO', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (5741, 15, 'COLONIA SAN JORGE', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5742, 15, 'COLONIA SANTA TERESA', '5527', 0);
INSERT INTO ubicacion_localidades VALUES (5743, 15, 'COLONIA SEGOVIA', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (5744, 15, 'COLONIA SOITUE', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (5745, 15, 'COLONIA TABANERA', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (5746, 15, 'COLONIA TORRECILLAS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5747, 15, 'COLONIA TORRECITO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5748, 15, 'COLONIA TRES ALTITOS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5749, 15, 'COM NAC DE ENERGIA ATOMICA', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (5750, 15, 'COMANDANTE SALAS', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5751, 15, 'COMISION NAC DE EMERGENCIA', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (5752, 15, 'COMPUERTAS NEGRAS', '5632', 0);
INSERT INTO ubicacion_localidades VALUES (5753, 15, 'CONCHA SUBERCASEAUX', '5549', 0);
INSERT INTO ubicacion_localidades VALUES (5754, 15, 'CONTROL YPF', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5755, 15, 'COQUIMBITO', '5513', 0);
INSERT INTO ubicacion_localidades VALUES (5756, 15, 'CORDON DE PLATA', '5561', 0);
INSERT INTO ubicacion_localidades VALUES (5757, 15, 'CORONEL BELTRAN', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5758, 15, 'CORONEL DORREGO', '5519', 0);
INSERT INTO ubicacion_localidades VALUES (5759, 15, 'CORRAL DE CUERO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5760, 15, 'CORRAL DE LORCA', '5637', 0);
INSERT INTO ubicacion_localidades VALUES (5761, 15, 'CORRAL DEL MOLLE', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5762, 15, 'COSTA DE ARAUJO', '5535', 0);
INSERT INTO ubicacion_localidades VALUES (5763, 15, 'COSTA DEL DIAMANTE', '5637', 0);
INSERT INTO ubicacion_localidades VALUES (5764, 15, 'CRISTO REDENTOR', '5557', 0);
INSERT INTO ubicacion_localidades VALUES (5765, 15, 'CRISTOBAL LOPEZ', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5766, 15, 'CRUZ BLANCA', '5537', 0);
INSERT INTO ubicacion_localidades VALUES (5767, 15, 'CRUZ DE PIEDRA', '5517', 0);
INSERT INTO ubicacion_localidades VALUES (5768, 15, 'CRUZ DE PIEDRA PTO GENDARMERIA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5769, 15, 'CRUZ DEL YUGO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5770, 15, 'CRUZ LEDESMA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5771, 15, 'CTO DEL TIGRE', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5772, 15, 'CUADRO BENEGAS', '5603', 0);
INSERT INTO ubicacion_localidades VALUES (5773, 15, 'CUADRO BOMBAL', '5607', 0);
INSERT INTO ubicacion_localidades VALUES (5774, 15, 'CUADRO NACIONAL', '5607', 0);
INSERT INTO ubicacion_localidades VALUES (5775, 15, 'CUESTA DE LOS TERNEROS', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (5776, 15, 'CUPILES', '5595', 0);
INSERT INTO ubicacion_localidades VALUES (5777, 15, 'D LOPEZ', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5778, 15, 'DALMIRO ZAPATA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5779, 15, 'DANIEL LUCERO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5780, 15, 'DANIEL MORGAN', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5781, 15, 'DELGADILLO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5782, 15, 'DESAGUADERO', '5598', 0);
INSERT INTO ubicacion_localidades VALUES (5783, 15, 'DIONISIO ORDOÃEZ', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5784, 15, 'DIONISIO ORTUBIA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5785, 15, 'DIQUE DEL VALLE DE UCO', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (5786, 15, 'DIQUE RIO MENDOZA', '5507', 0);
INSERT INTO ubicacion_localidades VALUES (5787, 15, 'DISTRITO COMPUERTA', '5507', 0);
INSERT INTO ubicacion_localidades VALUES (5788, 15, 'DIVISADERO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5789, 15, 'DIVISADERO COLORADO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5790, 15, 'DIVISADERO NEGRO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5791, 15, 'DOCTOR ANTONIO SOOMAS', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (5792, 15, 'DOMINGO GIMENEZ', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5793, 15, 'DOMINGO LARA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5794, 15, 'DOMINGO OGA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5795, 15, 'DOMINGO REAL', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5796, 15, 'DON MARTIN', '5537', 0);
INSERT INTO ubicacion_localidades VALUES (5797, 15, 'DONATO OJEDA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5798, 15, 'DOROTEO ORDOÃEZ', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5799, 15, 'DULCE', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5800, 15, 'E ROSALES', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5801, 15, 'EL 15', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (5802, 15, 'EL ÃANGO', '5589', 0);
INSERT INTO ubicacion_localidades VALUES (5803, 15, 'EL AGUILA', '5636', 0);
INSERT INTO ubicacion_localidades VALUES (5804, 15, 'EL ALAMBRADO', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (5805, 15, 'EL ALAMO', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5806, 15, 'EL ALGARROBAL', '5541', 0);
INSERT INTO ubicacion_localidades VALUES (5807, 15, 'EL ALGARROBAL', '5607', 0);
INSERT INTO ubicacion_localidades VALUES (5808, 15, 'EL ALGARROBO', '5595', 0);
INSERT INTO ubicacion_localidades VALUES (5809, 15, 'EL ALGARROBO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5810, 15, 'EL ALPERO', '5535', 0);
INSERT INTO ubicacion_localidades VALUES (5811, 15, 'EL ALTILLO', '5549', 0);
INSERT INTO ubicacion_localidades VALUES (5812, 15, 'EL ALTILLO', '5531', 0);
INSERT INTO ubicacion_localidades VALUES (5813, 15, 'EL ALTO', '5577', 0);
INSERT INTO ubicacion_localidades VALUES (5814, 15, 'EL ALTO SALVADOR', '5571', 0);
INSERT INTO ubicacion_localidades VALUES (5815, 15, 'EL ARBOLITO', '5636', 0);
INSERT INTO ubicacion_localidades VALUES (5816, 15, 'EL AZUFRE', '5621', 0);
INSERT INTO ubicacion_localidades VALUES (5817, 15, 'EL BALSADERO', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (5818, 15, 'EL BANDERON', '5634', 0);
INSERT INTO ubicacion_localidades VALUES (5819, 15, 'EL BONITO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5820, 15, 'EL BORBOLLON', '5541', 0);
INSERT INTO ubicacion_localidades VALUES (5821, 15, 'EL BUEN PASTOR', '5634', 0);
INSERT INTO ubicacion_localidades VALUES (5822, 15, 'EL CAÃITO', '5543', 0);
INSERT INTO ubicacion_localidades VALUES (5823, 15, 'EL CABAO VIEJO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5824, 15, 'EL CAJON', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5825, 15, 'EL CALVADITO', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (5826, 15, 'EL CAMPAMENTO', '5609', 0);
INSERT INTO ubicacion_localidades VALUES (5827, 15, 'EL CAPACHO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5828, 15, 'EL CARANCHITO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5829, 15, 'EL CARBALINO', '5592', 0);
INSERT INTO ubicacion_localidades VALUES (5830, 15, 'EL CARMEN', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5831, 15, 'EL CARRIZAL', '5509', 0);
INSERT INTO ubicacion_localidades VALUES (5832, 15, 'EL CARRIZAL DE ABAJO', '5509', 0);
INSERT INTO ubicacion_localidades VALUES (5833, 15, 'EL CAVADITO', '5537', 0);
INSERT INTO ubicacion_localidades VALUES (5834, 15, 'EL CAVADO CHICO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5835, 15, 'EL CEIBO', '5621', 0);
INSERT INTO ubicacion_localidades VALUES (5836, 15, 'EL CENIZO', '5645', 0);
INSERT INTO ubicacion_localidades VALUES (5837, 15, 'EL CENTRAL', '5589', 0);
INSERT INTO ubicacion_localidades VALUES (5838, 15, 'EL CEPILLO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5839, 15, 'EL CERCADO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5840, 15, 'EL CERRITO', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (5841, 15, 'EL CHAÃAR', '5435', 0);
INSERT INTO ubicacion_localidades VALUES (5842, 15, 'EL CHACALLAL', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5843, 15, 'EL CHACAY', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5844, 15, 'EL CHALET', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5845, 15, 'EL CHALLAO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5846, 15, 'EL CHILCAL', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (5847, 15, 'EL CHIRCAL', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (5848, 15, 'EL CHOIQUE', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5849, 15, 'EL CIENAGO', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (5850, 15, 'EL CIPRES', '5585', 0);
INSERT INTO ubicacion_localidades VALUES (5851, 15, 'EL COLON', '5535', 0);
INSERT INTO ubicacion_localidades VALUES (5852, 15, 'EL COLORADO', '5595', 0);
INSERT INTO ubicacion_localidades VALUES (5853, 15, 'EL COLORADO', '5592', 0);
INSERT INTO ubicacion_localidades VALUES (5854, 15, 'EL CONSUELO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5855, 15, 'EL CORBALNO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5856, 15, 'EL DESVIO', '5621', 0);
INSERT INTO ubicacion_localidades VALUES (5857, 15, 'EL DIVISADERO', '5589', 0);
INSERT INTO ubicacion_localidades VALUES (5858, 15, 'EL DIVISADERO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5859, 15, 'EL DURAZNO', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (5860, 15, 'EL ESCONDIDO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5861, 15, 'EL GIGANTILLO', '5591', 0);
INSERT INTO ubicacion_localidades VALUES (5862, 15, 'EL GONZANO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5863, 15, 'EL GORGINO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5864, 15, 'EL GUADAL DE CAMPOS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5865, 15, 'EL GUANACO', '5537', 0);
INSERT INTO ubicacion_localidades VALUES (5866, 15, 'EL GUANACO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5867, 15, 'EL GUERRINO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5868, 15, 'EL INFIERNILLO', '5509', 0);
INSERT INTO ubicacion_localidades VALUES (5869, 15, 'EL INFIERNO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5870, 15, 'EL JILGUERO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5871, 15, 'EL JUME', '5572', 0);
INSERT INTO ubicacion_localidades VALUES (5872, 15, 'EL JUNCALITO', '5620', 0);
INSERT INTO ubicacion_localidades VALUES (5873, 15, 'EL LECHUCITO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5874, 15, 'EL LECHUCITO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5875, 15, 'EL LEMINO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5876, 15, 'EL MANZANO', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (5877, 15, 'EL MANZANO HISTORICO', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (5878, 15, 'EL MARCADO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5879, 15, 'EL MARUCHO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5880, 15, 'EL MAURINO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5881, 15, 'EL MIRADOR', '5579', 0);
INSERT INTO ubicacion_localidades VALUES (5882, 15, 'EL MOLINO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5883, 15, 'EL MOLLAR', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (5884, 15, 'EL MOYANO', '5573', 0);
INSERT INTO ubicacion_localidades VALUES (5885, 15, 'EL NEVADO', '5620', 0);
INSERT INTO ubicacion_localidades VALUES (5886, 15, 'EL NIHUIL', '5605', 0);
INSERT INTO ubicacion_localidades VALUES (5887, 15, 'EL PARAISO', '5531', 0);
INSERT INTO ubicacion_localidades VALUES (5888, 15, 'EL PARRAL', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5889, 15, 'EL PASCAL', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (5890, 15, 'EL PASTAL', '5541', 0);
INSERT INTO ubicacion_localidades VALUES (5891, 15, 'EL PAYEN', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5892, 15, 'EL PERAL', '5561', 0);
INSERT INTO ubicacion_localidades VALUES (5893, 15, 'EL PERINO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5894, 15, 'EL PICONA', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (5895, 15, 'EL PLATEADO', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (5896, 15, 'EL PLUMERILLO', '5541', 0);
INSERT INTO ubicacion_localidades VALUES (5897, 15, 'EL PLUMERO', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (5898, 15, 'EL PLUMERO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5899, 15, 'EL PORTILLO', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (5900, 15, 'EL PORVENIR', '5603', 0);
INSERT INTO ubicacion_localidades VALUES (5901, 15, 'EL PORVENIR', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5902, 15, 'EL PUESTITO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5903, 15, 'EL PUESTITO', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5904, 15, 'EL PUMA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5905, 15, 'EL PUNTIAGUDO', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (5906, 15, 'EL REFUGIO', '5634', 0);
INSERT INTO ubicacion_localidades VALUES (5907, 15, 'EL REGADIO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5908, 15, 'EL RESGUARDO', '5543', 0);
INSERT INTO ubicacion_localidades VALUES (5909, 15, 'EL RETAMO', '5537', 0);
INSERT INTO ubicacion_localidades VALUES (5910, 15, 'EL RETAMOSO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5911, 15, 'EL RETIRO', '5632', 0);
INSERT INTO ubicacion_localidades VALUES (5912, 15, 'EL RETIRO', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (5913, 15, 'EL RETIRO', '5579', 0);
INSERT INTO ubicacion_localidades VALUES (5914, 15, 'EL RETIRO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5915, 15, 'EL RETIRO', '5632', 0);
INSERT INTO ubicacion_localidades VALUES (5916, 15, 'EL RINCON', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5917, 15, 'EL ROSARIO', '5535', 0);
INSERT INTO ubicacion_localidades VALUES (5918, 15, 'EL SALADO', '5563', 0);
INSERT INTO ubicacion_localidades VALUES (5919, 15, 'EL SALTO', '5549', 0);
INSERT INTO ubicacion_localidades VALUES (5920, 15, 'EL SAUCE', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (5921, 15, 'EL SOSNEADO', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (5922, 15, 'EL TAMARINDO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5923, 15, 'EL TAPON', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (5924, 15, 'EL TOPON', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (5925, 15, 'EL TOSCAL', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (5926, 15, 'EL TROPEZON', '5603', 0);
INSERT INTO ubicacion_localidades VALUES (5927, 15, 'EL USILLAL', '5601', 0);
INSERT INTO ubicacion_localidades VALUES (5928, 15, 'EL VALLE', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5929, 15, 'EL VAQUERO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5930, 15, 'EL VATRO', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5931, 15, 'EL VENTARRON', '5621', 0);
INSERT INTO ubicacion_localidades VALUES (5932, 15, 'EL VERGEL', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (5933, 15, 'EL VILLEGUINO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5934, 15, 'EL VILTEGUINO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5935, 15, 'EL ZAMPAL', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5936, 15, 'EL ZAMPAL', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (5937, 15, 'EL ZAMPAL', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5938, 15, 'EL ZAMPAL', '5561', 0);
INSERT INTO ubicacion_localidades VALUES (5939, 15, 'EL ZAPATINO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5940, 15, 'EL ZORZAL', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5941, 15, 'ELOY FUNES', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5942, 15, 'EMBALSE VALLE GRANDE', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (5943, 15, 'EMILIANO LUCERO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5944, 15, 'EMILIO NIETA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5945, 15, 'EMPALME FRONTERA', '5553', 0);
INSERT INTO ubicacion_localidades VALUES (5946, 15, 'EMPALME RESGUARDO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5947, 15, 'EPIFANIO FERNANDEZ', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5948, 15, 'ERNESTO ALCARAZ', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5949, 15, 'ERNESTO LOPEZ', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5950, 15, 'ESCUDERO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5951, 15, 'ESPEJO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5952, 15, 'ESPEJO RESGUARDADO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5953, 15, 'ESPINO', '5571', 0);
INSERT INTO ubicacion_localidades VALUES (5954, 15, 'ESTACION USPALLATA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5955, 15, 'ESTANCIA AGUANDA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5956, 15, 'ESTANCIA ARROYO HONDO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5957, 15, 'ESTANCIA AVEIRO', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (5958, 15, 'ESTANCIA BELLA VISTA', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (5959, 15, 'ESTANCIA CASA DE PIEDRA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5960, 15, 'ESTANCIA CASAS VIEJAS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5961, 15, 'ESTANCIA CHACAICO', '5621', 0);
INSERT INTO ubicacion_localidades VALUES (5962, 15, 'ESTANCIA CORREA', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (5963, 15, 'ESTANCIA CUEVA DEL TORO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5964, 15, 'ESTANCIA EL BALDERON', '5634', 0);
INSERT INTO ubicacion_localidades VALUES (5965, 15, 'ESTANCIA EL BONITO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5966, 15, 'ESTANCIA EL CAMPAMENTO', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (5967, 15, 'ESTANCIA EL CARRIZAL', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5968, 15, 'ESTANCIA EL CARRIZALITO', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (5969, 15, 'ESTANCIA GIL', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5970, 15, 'ESTANCIA GUINAZU', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (5971, 15, 'ESTANCIA JOCOLI', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5972, 15, 'ESTANCIA LA ARGENTINA', '5577', 0);
INSERT INTO ubicacion_localidades VALUES (5973, 15, 'ESTANCIA LA CHUÃA', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (5974, 15, 'ESTANCIA LA CORTADERA', '5634', 0);
INSERT INTO ubicacion_localidades VALUES (5975, 15, 'ESTANCIA LA PAMPA', '5563', 0);
INSERT INTO ubicacion_localidades VALUES (5976, 15, 'ESTANCIA LA PUMA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5977, 15, 'ESTANCIA LA ROSA', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (5978, 15, 'ESTANCIA LA SALCEDINA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5979, 15, 'ESTANCIA LA SARITA', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (5980, 15, 'ESTANCIA LA TRINTRICA', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (5981, 15, 'ESTANCIA LA VARITA', '5634', 0);
INSERT INTO ubicacion_localidades VALUES (5982, 15, 'ESTANCIA LA VIZCACHERA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5983, 15, 'ESTANCIA LAS CHILCAS', '5595', 0);
INSERT INTO ubicacion_localidades VALUES (5984, 15, 'ESTANCIA LAS HIGUERAS', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (5985, 15, 'ESTANCIA LAS VIZCACHERAS', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (5986, 15, 'ESTANCIA LOS CHACAYES', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (5987, 15, 'ESTANCIA LOS HUAICOS', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (5988, 15, 'ESTANCIA LOS LEONES', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (5989, 15, 'ESTANCIA MALLEA', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (5990, 15, 'ESTANCIA SAN MARTIN', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5991, 15, 'ESTANCIA SILVA', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (5992, 15, 'ESTANCIA SOFIA RAQUEL', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (5993, 15, 'ESTANCIA TIERRAS BLANCAS', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (5994, 15, 'ESTANCIA USPALLATA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5995, 15, 'ESTANCIA VILLAVICENCIO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5996, 15, 'ESTANCIA VILUCO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5997, 15, 'ESTANCIA YALGUARAZ', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (5998, 15, 'ESTANCIA YAUCHA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (5999, 15, 'ESTANISLAO ORDOÃEZ', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6000, 15, 'EUGENIO BUSTOS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6001, 15, 'EUSEBIA VIUDA DE GOMEZ', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6002, 15, 'EVARISTO ACEVEDO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6003, 15, 'EVARISTO SALAS', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6004, 15, 'EX FORTIN MALARGUE', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (6005, 15, 'FABRICIANO ROJAS', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6006, 15, 'FELIPE GARRO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6007, 15, 'FELIPE PEREZ', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6008, 15, 'FERMIN PEREZ', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6009, 15, 'FINCA EL ARROZ', '5531', 0);
INSERT INTO ubicacion_localidades VALUES (6010, 15, 'FINCA LOPEZ', '5622', 0);
INSERT INTO ubicacion_localidades VALUES (6011, 15, 'FINCA LOS ALAMOS', '5531', 0);
INSERT INTO ubicacion_localidades VALUES (6012, 15, 'FLORENCIO GARRO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6013, 15, 'FLORENCIO MOLINA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6014, 15, 'FLORENCIO ORDOÃEZ', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6015, 15, 'FLORINDO FLORES', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (6016, 15, 'FORZUDO', '5537', 0);
INSERT INTO ubicacion_localidades VALUES (6017, 15, 'FRANCISCO MOLINA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6018, 15, 'FRANCISCO ROJAS', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6019, 15, 'FRAY LUIS BELTRAN', '5531', 0);
INSERT INTO ubicacion_localidades VALUES (6020, 15, 'FRUCTUOSO DIAZ', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6021, 15, 'G GIMENEZ', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6022, 15, 'GARGANTA DEL LEON', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6023, 15, 'GASPAR CAMPOS', '5609', 0);
INSERT INTO ubicacion_localidades VALUES (6024, 15, 'GENERAL ACHA', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (6025, 15, 'GENERAL ALVEAR', '5620', 0);
INSERT INTO ubicacion_localidades VALUES (6026, 15, 'GENERAL GUTIERREZ', '5511', 0);
INSERT INTO ubicacion_localidades VALUES (6027, 15, 'GENERAL ORTEGA', '5517', 0);
INSERT INTO ubicacion_localidades VALUES (6028, 15, 'GERMAN MATURANO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6029, 15, 'GERTRUDIS DE OJEDA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6030, 15, 'GILBERTO PEREZ', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6031, 15, 'GLACIARES DEL RIO BLANCO', '5549', 0);
INSERT INTO ubicacion_localidades VALUES (6032, 15, 'GOBERNADOR BENEGAS', '5544', 0);
INSERT INTO ubicacion_localidades VALUES (6033, 15, 'GOBERNADOR CIVIT', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6034, 15, 'GOBERNADOR LUIS MOLINA', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (6035, 15, 'GODOY CRUZ', '5501', 0);
INSERT INTO ubicacion_localidades VALUES (6036, 15, 'GOICO', '5609', 0);
INSERT INTO ubicacion_localidades VALUES (6037, 15, 'GOUDGE', '5603', 0);
INSERT INTO ubicacion_localidades VALUES (6038, 15, 'GREGORIO ZAPATA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6039, 15, 'GUADAL DE LOS PEREZ', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (6040, 15, 'GUADALES', '5609', 0);
INSERT INTO ubicacion_localidades VALUES (6041, 15, 'GUALTALLARY', '5561', 0);
INSERT INTO ubicacion_localidades VALUES (6042, 15, 'GUAYQUERIA COLORADA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6043, 15, 'GUIDO', '5549', 0);
INSERT INTO ubicacion_localidades VALUES (6044, 15, 'GUILLERMO DONAIRE', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6045, 15, 'GURRUCHAGA', '5584', 0);
INSERT INTO ubicacion_localidades VALUES (6046, 15, 'H GARZALA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6047, 15, 'HERMENEGILDO DIAZ', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6048, 15, 'HONORIO ZAPATA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6049, 15, 'HORNITO DEL GRINGO', '5543', 0);
INSERT INTO ubicacion_localidades VALUES (6050, 15, 'HORNOS DE MOYANO', '5543', 0);
INSERT INTO ubicacion_localidades VALUES (6051, 15, 'HOTEL PORTEZUELO DEL VIENTO', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6052, 15, 'HOTEL TERMAS DEL AZUFRE', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6053, 15, 'HOTEL TERMAS EL SOSNEADO', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6054, 15, 'HUAICOS DE RUFINO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6055, 15, 'HUAICOS DE RUFINO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6056, 15, 'HUAIQUERIA DE LA HORQUETA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6057, 15, 'HUAIQUERIA DE LOS BURROS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6058, 15, 'HUECOS DE LOS TORDILLOS', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6059, 15, 'IGNACIO VILLEGAS', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6060, 15, 'IGUAZU', '5601', 0);
INSERT INTO ubicacion_localidades VALUES (6061, 15, 'INGENIERO BALLOFFET', '5603', 0);
INSERT INTO ubicacion_localidades VALUES (6062, 15, 'INGENIERO GIAGNONI', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (6063, 15, 'INGENIERO GUSTAVO ANDRE', '5535', 0);
INSERT INTO ubicacion_localidades VALUES (6064, 15, 'IRINEO ZAPATA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6065, 15, 'ISABEL FLORES', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (6066, 15, 'ISLA CHICA', '5587', 0);
INSERT INTO ubicacion_localidades VALUES (6067, 15, 'ISLA DEL CUCHILLO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6068, 15, 'ISLA GRANDE', '5587', 0);
INSERT INTO ubicacion_localidades VALUES (6069, 15, 'ISLA RETAMITO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6070, 15, 'J CAMPOS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6071, 15, 'J ORTUBIA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6072, 15, 'J VERON', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6073, 15, 'JAGUEL', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (6074, 15, 'JAGUEL AMARILLO', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (6075, 15, 'JAGUEL DE LAS CHILCAS', '5509', 0);
INSERT INTO ubicacion_localidades VALUES (6076, 15, 'JAGUEL DE ROSAS', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (6077, 15, 'JAGUEL DEL CATALAN', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (6078, 15, 'JAGUEL DEL GAUCHO', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6079, 15, 'JAGUEL DEL GOBIERNO', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6080, 15, 'JAIME PRATS', '5623', 0);
INSERT INTO ubicacion_localidades VALUES (6081, 15, 'JARILLOSO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6082, 15, 'JAUCHA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6083, 15, 'JESUS NAZARENO', '5523', 0);
INSERT INTO ubicacion_localidades VALUES (6084, 15, 'JOCOLI', '5543', 0);
INSERT INTO ubicacion_localidades VALUES (6085, 15, 'JOCOLI VIEJO', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (6086, 15, 'JOFRE', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6087, 15, 'JORGE NEWBERY', '5585', 0);
INSERT INTO ubicacion_localidades VALUES (6088, 15, 'JOSE CAMPOS', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6089, 15, 'JOSE DIAZ', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6090, 15, 'JOSE FERNANDEZ', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6091, 15, 'JOSE LUCERO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6092, 15, 'JOSE R MOLINA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6093, 15, 'JOSE SOSA', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6094, 15, 'JOSE SUAREZ', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6095, 15, 'JUAN B DUFAU', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6096, 15, 'JUAN H ORTIZ', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6097, 15, 'JUAN MILLAN', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6098, 15, 'JUAN ZAPATA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6099, 15, 'JULIO COMEGLIO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6100, 15, 'JUNTA DE LA VAINA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6101, 15, 'JUNTA DE LOS RIOS', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6102, 15, 'KILOMETRO 1013', '5535', 0);
INSERT INTO ubicacion_localidades VALUES (6103, 15, 'KILOMETRO 1032', '5535', 0);
INSERT INTO ubicacion_localidades VALUES (6104, 15, 'KILOMETRO 1085', '5549', 0);
INSERT INTO ubicacion_localidades VALUES (6105, 15, 'KILOMETRO 43', '5535', 0);
INSERT INTO ubicacion_localidades VALUES (6106, 15, 'KILOMETRO 47', '5624', 0);
INSERT INTO ubicacion_localidades VALUES (6107, 15, 'KILOMETRO 55', '5549', 0);
INSERT INTO ubicacion_localidades VALUES (6108, 15, 'KILOMETRO 56', '5620', 0);
INSERT INTO ubicacion_localidades VALUES (6109, 15, 'KILOMETRO 84', '5634', 0);
INSERT INTO ubicacion_localidades VALUES (6110, 15, 'KILOMETRO 882', '5620', 0);
INSERT INTO ubicacion_localidades VALUES (6111, 15, 'KILOMETRO 884', '5634', 0);
INSERT INTO ubicacion_localidades VALUES (6112, 15, 'KILOMETRO 935 DESVIO FCGSM', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6113, 15, 'KILOMETRO 947', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6114, 15, 'L DEL AGUERO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6115, 15, 'L PEREZ', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6116, 15, 'L PRADO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6117, 15, 'LA ADELINA', '5634', 0);
INSERT INTO ubicacion_localidades VALUES (6118, 15, 'LA ANGELINA', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6119, 15, 'LA ANGOSTURA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6120, 15, 'LA ARBOLEDA', '5561', 0);
INSERT INTO ubicacion_localidades VALUES (6121, 15, 'LA ARGENTINA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6122, 15, 'LA ARGENTINA', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6123, 15, 'LA BAJADA', '5535', 0);
INSERT INTO ubicacion_localidades VALUES (6124, 15, 'LA BANDERA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6125, 15, 'LA BANDERA', '5592', 0);
INSERT INTO ubicacion_localidades VALUES (6126, 15, 'LA BARDA CORTADA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6127, 15, 'LA BATRA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6128, 15, 'LA BOVEDA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6129, 15, 'LA CAÃADA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6130, 15, 'LA CAÃADA', '5567', 0);
INSERT INTO ubicacion_localidades VALUES (6131, 15, 'LA CALDENADA', '5634', 0);
INSERT INTO ubicacion_localidades VALUES (6132, 15, 'LA CALIFORNIA', '5621', 0);
INSERT INTO ubicacion_localidades VALUES (6133, 15, 'LA CARRERA', '5561', 0);
INSERT INTO ubicacion_localidades VALUES (6134, 15, 'LA CARUSINA', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (6135, 15, 'LA CASA DEL TIGRE', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6136, 15, 'LA CAUTIVA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6137, 15, 'LA CELIA', '5535', 0);
INSERT INTO ubicacion_localidades VALUES (6138, 15, 'LA CENTRAL  RIVADAVIA', '5579', 0);
INSERT INTO ubicacion_localidades VALUES (6139, 15, 'LA CHAPINA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6140, 15, 'LA CHILCA', '5515', 0);
INSERT INTO ubicacion_localidades VALUES (6141, 15, 'LA CHIMBA', '5589', 0);
INSERT INTO ubicacion_localidades VALUES (6142, 15, 'LA CHUNA', '5595', 0);
INSERT INTO ubicacion_localidades VALUES (6143, 15, 'LA CIENAGUITA', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6144, 15, 'LA CLARITA', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6145, 15, 'LA COLA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6146, 15, 'LA COLONIA', '5572', 0);
INSERT INTO ubicacion_localidades VALUES (6147, 15, 'LA COLONIA SUD', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6148, 15, 'LA CONSULTA', '5567', 0);
INSERT INTO ubicacion_localidades VALUES (6149, 15, 'LA CORONA', '5634', 0);
INSERT INTO ubicacion_localidades VALUES (6150, 15, 'LA CORTADERA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6151, 15, 'LA CORTADERA', '5545', 0);
INSERT INTO ubicacion_localidades VALUES (6152, 15, 'LA CORTADERA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6153, 15, 'LA COSTA', '5596', 0);
INSERT INTO ubicacion_localidades VALUES (6154, 15, 'LA DIVISORIA', '5549', 0);
INSERT INTO ubicacion_localidades VALUES (6155, 15, 'LA DORMIDA', '5592', 0);
INSERT INTO ubicacion_localidades VALUES (6156, 15, 'LA ESCANDINAVA', '5634', 0);
INSERT INTO ubicacion_localidades VALUES (6157, 15, 'LA ESPERANZA', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (6158, 15, 'LA ESQUINA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6159, 15, 'LA ESTACADA', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (6160, 15, 'LA ESTANCIA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6161, 15, 'LA ESTRECHURA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6162, 15, 'LA EXCAVACION', '5537', 0);
INSERT INTO ubicacion_localidades VALUES (6163, 15, 'LA FAVORITA', '5591', 0);
INSERT INTO ubicacion_localidades VALUES (6164, 15, 'LA FLORIDA', '5579', 0);
INSERT INTO ubicacion_localidades VALUES (6165, 15, 'LA FLORIDA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6166, 15, 'LA FLORIDA', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6167, 15, 'LA FORTUNA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6168, 15, 'LA FORTUNA', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (6169, 15, 'LA FUNDICION', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6170, 15, 'LA GUEVARINA', '5622', 0);
INSERT INTO ubicacion_localidades VALUES (6171, 15, 'LA HOLANDA', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (6172, 15, 'LA HORQUETA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6173, 15, 'LA HULLERA', '5549', 0);
INSERT INTO ubicacion_localidades VALUES (6174, 15, 'LA ISLA', '5585', 0);
INSERT INTO ubicacion_localidades VALUES (6175, 15, 'LA ISLA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6176, 15, 'LA ISLA CHICA', '5585', 0);
INSERT INTO ubicacion_localidades VALUES (6177, 15, 'LA ISLA GRANDE', '5585', 0);
INSERT INTO ubicacion_localidades VALUES (6178, 15, 'LA JACINTITA', '5591', 0);
INSERT INTO ubicacion_localidades VALUES (6179, 15, 'LA JACINTO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6180, 15, 'LA JAULA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6181, 15, 'LA JAULA', '5517', 0);
INSERT INTO ubicacion_localidades VALUES (6182, 15, 'LA JAULA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6183, 15, 'LA JAULA', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (6184, 15, 'LA JUNTA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6185, 15, 'LA LAGUNITA', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6186, 15, 'LA LATA', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (6187, 15, 'LA LEONA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6188, 15, 'LA LIBERTAD', '5579', 0);
INSERT INTO ubicacion_localidades VALUES (6189, 15, 'LA LLAVE', '5603', 0);
INSERT INTO ubicacion_localidades VALUES (6190, 15, 'LA LLAVE NUEVA', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (6191, 15, 'LA LLAVE VIEJA', '5603', 0);
INSERT INTO ubicacion_localidades VALUES (6192, 15, 'LA LUNINA', '5634', 0);
INSERT INTO ubicacion_localidades VALUES (6193, 15, 'LA MARZOLINA', '5632', 0);
INSERT INTO ubicacion_localidades VALUES (6194, 15, 'LA MINA DEL PECEÃO', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (6195, 15, 'LA MONTILLA', '5634', 0);
INSERT INTO ubicacion_localidades VALUES (6196, 15, 'LA MORA', '5636', 0);
INSERT INTO ubicacion_localidades VALUES (6197, 15, 'LA NEGRITA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6198, 15, 'LA PALMERA', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (6199, 15, 'LA PASTORAL', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (6200, 15, 'LA PAZ', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6201, 15, 'LA PEGA', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (6202, 15, 'LA PICARONA', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (6203, 15, 'LA PICASA', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (6204, 15, 'LA PICAZA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6205, 15, 'LA PICHANA', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (6206, 15, 'LA PICHANA', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (6207, 15, 'LA PIRATA', '5553', 0);
INSERT INTO ubicacion_localidades VALUES (6208, 15, 'LA POMONA', '5620', 0);
INSERT INTO ubicacion_localidades VALUES (6209, 15, 'LA PORTEÃA', '5591', 0);
INSERT INTO ubicacion_localidades VALUES (6210, 15, 'LA PORTEÃA', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6211, 15, 'LA PRIMAVERA', '5527', 0);
INSERT INTO ubicacion_localidades VALUES (6212, 15, 'LA PRIMAVERA', '5591', 0);
INSERT INTO ubicacion_localidades VALUES (6213, 15, 'LA PRIMAVERA', '5565', 0);
INSERT INTO ubicacion_localidades VALUES (6214, 15, 'LA QUEBRADA', '5622', 0);
INSERT INTO ubicacion_localidades VALUES (6215, 15, 'LA SALINILLA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6216, 15, 'LA SEÃA', '5634', 0);
INSERT INTO ubicacion_localidades VALUES (6217, 15, 'LA SIRENA', '5579', 0);
INSERT INTO ubicacion_localidades VALUES (6218, 15, 'LA SOMBRILLA', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6219, 15, 'LA TOMA', '5563', 0);
INSERT INTO ubicacion_localidades VALUES (6220, 15, 'LA TOSCA', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (6221, 15, 'LA VALENCIANA', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (6222, 15, 'LA VARITA', '5634', 0);
INSERT INTO ubicacion_localidades VALUES (6223, 15, 'LA VASCONIA', '5623', 0);
INSERT INTO ubicacion_localidades VALUES (6224, 15, 'LA VERDE', '5577', 0);
INSERT INTO ubicacion_localidades VALUES (6225, 15, 'LA VERDE', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (6226, 15, 'LA YESERA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6227, 15, 'LADISLAO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6228, 15, 'LADISLAO CAMPOS', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6229, 15, 'LAGUNA DE GUANACACHE', '5535', 0);
INSERT INTO ubicacion_localidades VALUES (6230, 15, 'LAGUNA DEL ROSARIO', '5535', 0);
INSERT INTO ubicacion_localidades VALUES (6231, 15, 'LAGUNA NEGRA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6232, 15, 'LAGUNA SALADA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6233, 15, 'LAGUNITA', '5527', 0);
INSERT INTO ubicacion_localidades VALUES (6234, 15, 'LAS ARABIAS', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6235, 15, 'LAS CANTERAS', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6236, 15, 'LAS CARDITAS', '5549', 0);
INSERT INTO ubicacion_localidades VALUES (6237, 15, 'LAS CATITAS', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6238, 15, 'LAS CHACRAS', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6239, 15, 'LAS CHACRITAS', '5549', 0);
INSERT INTO ubicacion_localidades VALUES (6240, 15, 'LAS COLONIAS', '5509', 0);
INSERT INTO ubicacion_localidades VALUES (6241, 15, 'LAS COLONIAS', '5571', 0);
INSERT INTO ubicacion_localidades VALUES (6242, 15, 'LAS COMPUERTAS', '5549', 0);
INSERT INTO ubicacion_localidades VALUES (6243, 15, 'LAS COMPUERTAS NEGRAS', '5632', 0);
INSERT INTO ubicacion_localidades VALUES (6244, 15, 'LAS CORTADERAS', '5545', 0);
INSERT INTO ubicacion_localidades VALUES (6245, 15, 'LAS CRUCES', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6246, 15, 'LAS CRUCES', '5537', 0);
INSERT INTO ubicacion_localidades VALUES (6247, 15, 'LAS CUEVAS', '5557', 0);
INSERT INTO ubicacion_localidades VALUES (6248, 15, 'LAS DELICIAS', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (6249, 15, 'LAS GATEADAS', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (6250, 15, 'LAS GATITAS', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6251, 15, 'LAS HERAS', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6252, 15, 'LAS JUNTAS', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6253, 15, 'LAS LOICAS', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6254, 15, 'LAS MALVINAS', '5605', 0);
INSERT INTO ubicacion_localidades VALUES (6255, 15, 'LAS MINAS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6256, 15, 'LAS PAREDES', '5601', 0);
INSERT INTO ubicacion_localidades VALUES (6257, 15, 'LAS PEÃAS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6258, 15, 'LAS PINTADAS', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (6259, 15, 'LAS RAJADURAS', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6260, 15, 'LAS ROSAS', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6261, 15, 'LAS ROSAS', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (6262, 15, 'LAS TAGUAS', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6263, 15, 'LAS TORRECITAS', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (6264, 15, 'LAS TORRECITAS', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (6265, 15, 'LAS TOTORITAS', '5591', 0);
INSERT INTO ubicacion_localidades VALUES (6266, 15, 'LAS VAYAS', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6267, 15, 'LAS VEGAS', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6268, 15, 'LAS VEGAS', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6269, 15, 'LAS VIOLETAS', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (6270, 15, 'LAS VIOLETAS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6271, 15, 'LAS VISCACHERAS', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6272, 15, 'LAS VISTAS', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6273, 15, 'LAS VIZCACHAS', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6274, 15, 'LAS YEGUAS', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6275, 15, 'LAVALLE VILLA TULUMAYA', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (6276, 15, 'LEZCANO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6277, 15, 'LIBERTADOR GRAL SAN MARTIN', '5570', 0);
INSERT INTO ubicacion_localidades VALUES (6278, 15, 'LIMON', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (6279, 15, 'LINO PEREZ', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6280, 15, 'LIRA', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6281, 15, 'LISANDRO ESCUDERO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6282, 15, 'LLANO BLANCO', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (6283, 15, 'LOMA ALTA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6284, 15, 'LOMA CHATA', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (6285, 15, 'LOMA COLORADA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6286, 15, 'LOMA DE LOS BURROS', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6287, 15, 'LOMA DEL CERRO ASPERO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6288, 15, 'LOMA DEL CHAÃAR', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6289, 15, 'LOMA DEL MEDIO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6290, 15, 'LOMA EXTENDIDA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6291, 15, 'LOMA JAGUEL DEL GAUCHO', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6292, 15, 'LOMA NEGRA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6293, 15, 'LOMA NEGRA GRANDE', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6294, 15, 'LOMA PELADA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6295, 15, 'LOMA SOLA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6296, 15, 'LOMAS ALTAS', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6297, 15, 'LOMAS BAYAS', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6298, 15, 'LOMAS BLANCAS', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6299, 15, 'LOMAS CHICAS', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6300, 15, 'LOMAS COLORADAS', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6301, 15, 'LOMITA LARGA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6302, 15, 'LOMITA MORADA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6303, 15, 'LONCO VACAS', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6304, 15, 'LOS AHUMADOS', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (6305, 15, 'LOS ALAMOS', '5531', 0);
INSERT INTO ubicacion_localidades VALUES (6306, 15, 'LOS ALAMOS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6307, 15, 'LOS ALGARROBOS', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6308, 15, 'LOS ALGODONES', '5537', 0);
INSERT INTO ubicacion_localidades VALUES (6309, 15, 'LOS ALTAMIQUES', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6310, 15, 'LOS AMARILLOS', '5634', 0);
INSERT INTO ubicacion_localidades VALUES (6311, 15, 'LOS ANGELES', '5634', 0);
INSERT INTO ubicacion_localidades VALUES (6312, 15, 'LOS ARBOLES', '5575', 0);
INSERT INTO ubicacion_localidades VALUES (6313, 15, 'LOS ARBOLES DE VILLEGAS', '5563', 0);
INSERT INTO ubicacion_localidades VALUES (6314, 15, 'LOS ARROYOS', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (6315, 15, 'LOS BALDES', '5537', 0);
INSERT INTO ubicacion_localidades VALUES (6316, 15, 'LOS BARRIALES', '5585', 0);
INSERT INTO ubicacion_localidades VALUES (6317, 15, 'LOS BARRIALES', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6318, 15, 'LOS BLANCOS', '5537', 0);
INSERT INTO ubicacion_localidades VALUES (6319, 15, 'LOS BRITOS', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (6320, 15, 'LOS BURGOS', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6321, 15, 'LOS CAMPAMENTOS', '5634', 0);
INSERT INTO ubicacion_localidades VALUES (6322, 15, 'LOS CAMPAMENTOS', '5577', 0);
INSERT INTO ubicacion_localidades VALUES (6323, 15, 'LOS CARRIZALES', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6324, 15, 'LOS CHACAYES', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6325, 15, 'LOS CLAVELES', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (6326, 15, 'LOS COLADOS', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6327, 15, 'LOS COLORADOS', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6328, 15, 'LOS COMETIERRAS', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (6329, 15, 'LOS COMPARTOS', '5621', 0);
INSERT INTO ubicacion_localidades VALUES (6330, 15, 'LOS CORRALES', '5621', 0);
INSERT INTO ubicacion_localidades VALUES (6331, 15, 'LOS CORRALITOS', '5527', 0);
INSERT INTO ubicacion_localidades VALUES (6332, 15, 'LOS CORREDORES', '5521', 0);
INSERT INTO ubicacion_localidades VALUES (6333, 15, 'LOS EMBARQUES', '5595', 0);
INSERT INTO ubicacion_localidades VALUES (6334, 15, 'LOS EUCALIPTOS', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (6335, 15, 'LOS FILTROS', '5505', 0);
INSERT INTO ubicacion_localidades VALUES (6336, 15, 'LOS HORCONCITOS', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6337, 15, 'LOS HUARPES', '5634', 0);
INSERT INTO ubicacion_localidades VALUES (6338, 15, 'LOS MEDANOS NEGROS', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6339, 15, 'LOS MOLLES', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (6340, 15, 'LOS MOROS', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6341, 15, 'LOS OLMOS', '5571', 0);
INSERT INTO ubicacion_localidades VALUES (6342, 15, 'LOS OTOYANES', '5579', 0);
INSERT INTO ubicacion_localidades VALUES (6343, 15, 'LOS PAPAGAYOS', '5549', 0);
INSERT INTO ubicacion_localidades VALUES (6344, 15, 'LOS PARAMILLOS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6345, 15, 'LOS PARLAMENTOS', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (6346, 15, 'LOS PATOS', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (6347, 15, 'LOS PEJECITOS', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (6348, 15, 'LOS PEJES', '5603', 0);
INSERT INTO ubicacion_localidades VALUES (6349, 15, 'LOS PENITENTES', '5553', 0);
INSERT INTO ubicacion_localidades VALUES (6350, 15, 'LOS POZOS', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6351, 15, 'LOS RALOS', '5537', 0);
INSERT INTO ubicacion_localidades VALUES (6352, 15, 'LOS RAMBLONES', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6353, 15, 'LOS RAMBLONES', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (6354, 15, 'LOS REPUNTES', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (6355, 15, 'LOS REYUNOS', '5615', 0);
INSERT INTO ubicacion_localidades VALUES (6356, 15, 'LOS ROSETI', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6357, 15, 'LOS SAUCES', '5537', 0);
INSERT INTO ubicacion_localidades VALUES (6358, 15, 'LOS SAUCES', '5563', 0);
INSERT INTO ubicacion_localidades VALUES (6359, 15, 'LOS SAUCES LAVALLE', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (6360, 15, 'LOS TAMARINDOS', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6361, 15, 'LOS TAMARINDOS', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6362, 15, 'LOS TERNEROS', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (6363, 15, 'LOS TOLDITOS', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (6364, 15, 'LOS TORDILLOS', '5621', 0);
INSERT INTO ubicacion_localidades VALUES (6365, 15, 'LOS TORDILLOS', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (6366, 15, 'LOS TOSCALES', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6367, 15, 'LOS VERDES', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6368, 15, 'LOS VERDES', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6369, 15, 'LOS VILLEGAS', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6370, 15, 'LOS YAULLINES', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (6371, 15, 'LOS YOLES', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6372, 15, 'LOTES DE GAVIOLA', '5507', 0);
INSERT INTO ubicacion_localidades VALUES (6373, 15, 'LUANCO', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (6374, 15, 'LUCAS DONAIRE', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6375, 15, 'LUGUINA', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6376, 15, 'LUIS MARQUEZ', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6377, 15, 'LUJAN DE CUYO', '5507', 0);
INSERT INTO ubicacion_localidades VALUES (6378, 15, 'LUNLUNTA', '5517', 0);
INSERT INTO ubicacion_localidades VALUES (6379, 15, 'LUZURIAGA', '5513', 0);
INSERT INTO ubicacion_localidades VALUES (6380, 15, 'M ESCUDERO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6381, 15, 'M QUIROGA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6382, 15, 'MAIPU', '5515', 0);
INSERT INTO ubicacion_localidades VALUES (6383, 15, 'MALAL DEL MEDIO', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (6384, 15, 'MALARGUE', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6385, 15, 'MALLIN QUEMADO', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6386, 15, 'MAQUINISTA LEVET', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6387, 15, 'MARAVILLA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6388, 15, 'MARIA GARCIA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6389, 15, 'MARIA LUISA', '5563', 0);
INSERT INTO ubicacion_localidades VALUES (6390, 15, 'MARIA VIUDA DE DONAIRE', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6391, 15, 'MARIO OLGUIN', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6392, 15, 'MARQUEZ ESCUELA117', '5517', 0);
INSERT INTO ubicacion_localidades VALUES (6393, 15, 'MASA', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (6394, 15, 'MATANCILLA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6395, 15, 'MATHIEU NORTE', '5543', 0);
INSERT INTO ubicacion_localidades VALUES (6396, 15, 'MATIAS GARRO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6397, 15, 'MATONCILLA', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (6398, 15, 'MATURANA', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6399, 15, 'MAURICIO CALDERON', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6400, 15, 'MAYOR DRUMMOND', '5507', 0);
INSERT INTO ubicacion_localidades VALUES (6401, 15, 'MAZA', '5517', 0);
INSERT INTO ubicacion_localidades VALUES (6402, 15, 'MECHANQUIL', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6403, 15, 'MECHENGUIL', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6404, 15, 'MEDANOS COLORADO', '5636', 0);
INSERT INTO ubicacion_localidades VALUES (6405, 15, 'MEDARDO MIRANDA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6406, 15, 'MEDIA LUNA', '6279', 0);
INSERT INTO ubicacion_localidades VALUES (6407, 15, 'MEDRANO', '5585', 0);
INSERT INTO ubicacion_localidades VALUES (6408, 15, 'MELITON CAMPOS', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6409, 15, 'MELOCOTON', '5565', 0);
INSERT INTO ubicacion_localidades VALUES (6410, 15, 'MENDOZA', '5500', 0);
INSERT INTO ubicacion_localidades VALUES (6411, 15, 'MESETA COLORADA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6412, 15, 'MIGUEZ', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6413, 15, 'MINA ARGENTINA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6414, 15, 'MINA ETHEL', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6415, 15, 'MINA HUEMUL', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6416, 15, 'MINA SANTA CRUZ', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6417, 15, 'MINA VOLCAN OVERO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6418, 15, 'MINACAR', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6419, 15, 'MINAS DE PETROLEO', '5509', 0);
INSERT INTO ubicacion_localidades VALUES (6420, 15, 'MINAS DEL NEVADO', '5605', 0);
INSERT INTO ubicacion_localidades VALUES (6421, 15, 'MINAS EL SOSNEADO', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (6422, 15, 'MINAS SALAGASTA', '5545', 0);
INSERT INTO ubicacion_localidades VALUES (6423, 15, 'MINELLI', '5579', 0);
INSERT INTO ubicacion_localidades VALUES (6424, 15, 'MOJON OCHO', '5636', 0);
INSERT INTO ubicacion_localidades VALUES (6425, 15, 'MOLINO', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6426, 15, 'MOLUCHES', '5535', 0);
INSERT INTO ubicacion_localidades VALUES (6427, 15, 'MONTE BAYO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6428, 15, 'MONTE CASEROS', '5571', 0);
INSERT INTO ubicacion_localidades VALUES (6429, 15, 'MONTE COMAN', '5609', 0);
INSERT INTO ubicacion_localidades VALUES (6430, 15, 'MONUMENTO AL EJERCITO DE LOS A', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6431, 15, 'MORON CHICO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6432, 15, 'MORON VIEJO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6433, 15, 'MORRO DEL CUERO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6434, 15, 'MOSMOTA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6435, 15, 'MOYANO', '5543', 0);
INSERT INTO ubicacion_localidades VALUES (6436, 15, 'MUNDO NUEVO', '5579', 0);
INSERT INTO ubicacion_localidades VALUES (6437, 15, 'N ZAPATA', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6438, 15, 'NATALIA DONAIRE', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6439, 15, 'NAVARRO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6440, 15, 'NECTO SOSA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6441, 15, 'NEGRO QUEMADO', '5623', 0);
INSERT INTO ubicacion_localidades VALUES (6442, 15, 'NESTOR AGUILERA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6443, 15, 'NICOLAS ORDOÃEZ', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6444, 15, 'NIHUIL', '5605', 0);
INSERT INTO ubicacion_localidades VALUES (6445, 15, 'NIRE CO', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (6446, 15, 'NUEVA CALIFORNIA', '5535', 0);
INSERT INTO ubicacion_localidades VALUES (6447, 15, 'NUEVA CIUDAD', '5519', 0);
INSERT INTO ubicacion_localidades VALUES (6448, 15, 'OCHENTA Y CUATRO', '5633', 0);
INSERT INTO ubicacion_localidades VALUES (6449, 15, 'OJO DE AGUA', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (6450, 15, 'ONOTRE PUEBLA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6451, 15, 'OVEJERIA', '5637', 0);
INSERT INTO ubicacion_localidades VALUES (6452, 15, 'P PLANCHON', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6453, 15, 'P ROSALES', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6454, 15, 'P SAN IGNACIO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6455, 15, 'PACHANGO', '5579', 0);
INSERT INTO ubicacion_localidades VALUES (6456, 15, 'PALERMO CHICO', '5624', 0);
INSERT INTO ubicacion_localidades VALUES (6457, 15, 'PALMIRA', '5584', 0);
INSERT INTO ubicacion_localidades VALUES (6458, 15, 'PAMPA AMARILLA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6459, 15, 'PAMPA DE LAS YARETAS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6460, 15, 'PAMPA DE LOS BAYOS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6461, 15, 'PAMPA DEL SALADO', '5535', 0);
INSERT INTO ubicacion_localidades VALUES (6462, 15, 'PAMPA DEL TIGRE', '5634', 0);
INSERT INTO ubicacion_localidades VALUES (6463, 15, 'PAMPA YALGUARAZ', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6464, 15, 'PAMPITA EMBARCADERO FCGSM', '5598', 0);
INSERT INTO ubicacion_localidades VALUES (6465, 15, 'PANQUEUA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6466, 15, 'PAPAGAYO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6467, 15, 'PARADERO LA SUPERIORA', '5525', 0);
INSERT INTO ubicacion_localidades VALUES (6468, 15, 'PARAMILLO', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (6469, 15, 'PARAMILLO DE LAS VACAS', '5500', 0);
INSERT INTO ubicacion_localidades VALUES (6470, 15, 'PAREDITAS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6471, 15, 'PASCUAL SOSA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6472, 15, 'PASO DE LAS CANOAS', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6473, 15, 'PASO DE LAS CARRETAS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6474, 15, 'PASO DE LOS ANDES', '5505', 0);
INSERT INTO ubicacion_localidades VALUES (6475, 15, 'PASO DE LOS GAUCHOS', '5636', 0);
INSERT INTO ubicacion_localidades VALUES (6476, 15, 'PASO DEL CISNE', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (6477, 15, 'PASO HONDO', '5541', 0);
INSERT INTO ubicacion_localidades VALUES (6478, 15, 'PASO LOS PALOS', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (6479, 15, 'PATA MORA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6480, 15, 'PATIMALAL', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6481, 15, 'PAULINO MATURA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6482, 15, 'PAYUN', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6483, 15, 'PEÃA', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6484, 15, 'PEDREGAL', '5529', 0);
INSERT INTO ubicacion_localidades VALUES (6485, 15, 'PEDRO CASTELU', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6486, 15, 'PEDRO PABLO PEREZ', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6487, 15, 'PEDRO VARGAS', '5603', 0);
INSERT INTO ubicacion_localidades VALUES (6488, 15, 'PERDRIEL', '5509', 0);
INSERT INTO ubicacion_localidades VALUES (6489, 15, 'PETROLEO', '5549', 0);
INSERT INTO ubicacion_localidades VALUES (6490, 15, 'PHILIPPS', '5579', 0);
INSERT INTO ubicacion_localidades VALUES (6491, 15, 'PICHI CIEGO ESTACION FCGSM', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6492, 15, 'PICOS BAYOS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6493, 15, 'PIEDRA DE AFILAR', '5615', 0);
INSERT INTO ubicacion_localidades VALUES (6494, 15, 'PIEDRAS BLANCAS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6495, 15, 'PIRCAS DE OSORIO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6496, 15, 'PIRQUITA EMBARCADERO FCGSM', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6497, 15, 'PLAZA DE MULAS', '5500', 0);
INSERT INTO ubicacion_localidades VALUES (6498, 15, 'PO ALVARADO NORTE', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6499, 15, 'PO ALVARADO SUR', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6500, 15, 'PO AMARILLO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6501, 15, 'PO DE CONTRABANDISTA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6502, 15, 'PO DE LA CUMBRE', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6503, 15, 'PO DE LA QUEBRADA HONDA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6504, 15, 'PO DE LOS ESCALONES', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6505, 15, 'PO DEL HUANACO', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6506, 15, 'PO DEL RUBIO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6507, 15, 'PO MAIPU', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6508, 15, 'PO MALLAN', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6509, 15, 'PO PEHUENCHE', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6510, 15, 'PO VALLE HERMOSO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6511, 15, 'POLVAREDA', '5551', 0);
INSERT INTO ubicacion_localidades VALUES (6512, 15, 'PONCE', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6513, 15, 'PORTEZUELO CHOIQUE', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6514, 15, 'PORTILLO AGUA DE TORO', '5545', 0);
INSERT INTO ubicacion_localidades VALUES (6515, 15, 'PORTILLO CANALES', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6516, 15, 'PORTILLO CRUZ DE PIEDRA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6517, 15, 'PORTILLO D QUEMADO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6518, 15, 'PORTILLO DE COLINA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6519, 15, 'PORTILLO DE INDIO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6520, 15, 'PORTILLO DE LA G  DEL CAMINO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6521, 15, 'PORTILLO DE LA LAGRIMA VIVA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6522, 15, 'PORTILLO DE LA YESERA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6523, 15, 'PORTILLO DE LAS CABEZAS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6524, 15, 'PORTILLO DE LAS VACAS', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6525, 15, 'PORTILLO DE LOMAS COLORADAS', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6526, 15, 'PORTILLO DE PINQUENES', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (6527, 15, 'PORTILLO DEL DIABLO', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (6528, 15, 'PORTILLO DEL MEDIO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6529, 15, 'PORTILLO DEL NORTE', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6530, 15, 'PORTILLO DEL PAPAL', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6531, 15, 'PORTILLO DEL TIGRE', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6532, 15, 'PORTILLO DEL VIENTO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6533, 15, 'PORTILLO LA PAMPA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6534, 15, 'PORTILLO OCCIDENTAL DEL BAYO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6535, 15, 'PORTILLO PEDERNALES', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6536, 15, 'POSTA DE HIERRO', '5621', 0);
INSERT INTO ubicacion_localidades VALUES (6537, 15, 'POSTE DE FIERRO', '5634', 0);
INSERT INTO ubicacion_localidades VALUES (6538, 15, 'POTRERILLOS', '5549', 0);
INSERT INTO ubicacion_localidades VALUES (6539, 15, 'POTRERO SAN PABLO', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (6540, 15, 'PRIMAVERA', '5525', 0);
INSERT INTO ubicacion_localidades VALUES (6541, 15, 'PROGRESO', '5535', 0);
INSERT INTO ubicacion_localidades VALUES (6542, 15, 'PTA DEL AGUA VIEJA', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (6543, 15, 'PTO LOS AMARILLOS', '5636', 0);
INSERT INTO ubicacion_localidades VALUES (6544, 15, 'PUEBLO DIAMANTE', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (6545, 15, 'PUEBLO ECHEVARRIETA', '5603', 0);
INSERT INTO ubicacion_localidades VALUES (6546, 15, 'PUEBLO LUNA', '5632', 0);
INSERT INTO ubicacion_localidades VALUES (6547, 15, 'PUEBLO SOTO', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (6548, 15, 'PUENTE DEL INCA', '5555', 0);
INSERT INTO ubicacion_localidades VALUES (6549, 15, 'PUENTE VIEJO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6550, 15, 'PUERTA DE LA ISLA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6551, 15, 'PUERTO HORTENSA', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (6552, 15, 'PUERTO RINCON ESCALONA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6553, 15, 'PUESTA EL CAVADO', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (6554, 15, 'PUESTO A MARTINEZ', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6555, 15, 'PUESTO AGUA AMARGA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6556, 15, 'PUESTO AGUA DE LA LIEBRE', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (6557, 15, 'PUESTO AGUA DE LA MERINA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6558, 15, 'PUESTO AGUA DE LA ZORRA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6559, 15, 'PUESTO AGUA DE ZANJON', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6560, 15, 'PUESTO AGUA DEL MEDANO', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (6561, 15, 'PUESTO ALFARFA', '5561', 0);
INSERT INTO ubicacion_localidades VALUES (6562, 15, 'PUESTO ALGARROBO GRANDE', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (6563, 15, 'PUESTO ATAMISQUI', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6564, 15, 'PUESTO CANALES', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6565, 15, 'PUESTO CARRIZALITO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6566, 15, 'PUESTO CHAMBON', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6567, 15, 'PUESTO DE GARRO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6568, 15, 'PUESTO DE LA CORONA', '5636', 0);
INSERT INTO ubicacion_localidades VALUES (6569, 15, 'PUESTO DE LA SALADA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6570, 15, 'PUESTO DE LAS CARRETAS', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6571, 15, 'PUESTO DE LAS TROPAS', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6572, 15, 'PUESTO DE OLGUIN', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6573, 15, 'PUESTO DE OROZCO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6574, 15, 'PUESTO DE PETRA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6575, 15, 'PUESTO DE SOSA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6576, 15, 'PUESTO DEL BUEN PASTOR', '5636', 0);
INSERT INTO ubicacion_localidades VALUES (6577, 15, 'PUESTO DEL CHAÃACAL', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6578, 15, 'PUESTO EL CARRIZALITO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6579, 15, 'PUESTO EL JAGUAL', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6580, 15, 'PUESTO EL JILGUERO', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (6581, 15, 'PUESTO EL MANZANO', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (6582, 15, 'PUESTO EL PERAL', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6583, 15, 'PUESTO EL PICHON', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (6584, 15, 'PUESTO EL RETAMITO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6585, 15, 'PUESTO EL TOTORAL', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6586, 15, 'PUESTO EL TRUENO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6587, 15, 'PUESTO ESCONDIDO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6588, 15, 'PUESTO F TELLO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6589, 15, 'PUESTO GARCIA', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6590, 15, 'PUESTO GENDARMERIA NACIONAL PO', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6591, 15, 'PUESTO GENTILE', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (6592, 15, 'PUESTO GUAMPARITO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6593, 15, 'PUESTO HORQUETA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6594, 15, 'PUESTO ISLA CHAÃAR', '5435', 0);
INSERT INTO ubicacion_localidades VALUES (6595, 15, 'PUESTO J ALVAREZ', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6596, 15, 'PUESTO J CASTRO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6597, 15, 'PUESTO LA CACHACA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6598, 15, 'PUESTO LA CALDENADA', '5621', 0);
INSERT INTO ubicacion_localidades VALUES (6599, 15, 'PUESTO LA COSTA', '5583', 0);
INSERT INTO ubicacion_localidades VALUES (6600, 15, 'PUESTO LA FLORIDA', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (6601, 15, 'PUESTO LA GRUTA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6602, 15, 'PUESTO LA HORTENSIA', '5537', 0);
INSERT INTO ubicacion_localidades VALUES (6603, 15, 'PUESTO LA INVERNADA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6604, 15, 'PUESTO LA JARILLA', '5507', 0);
INSERT INTO ubicacion_localidades VALUES (6605, 15, 'PUESTO LA JERILLA', '5561', 0);
INSERT INTO ubicacion_localidades VALUES (6606, 15, 'PUESTO LA MOJADA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6607, 15, 'PUESTO LA NEGRITA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6608, 15, 'PUESTO LA NIEBLA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6609, 15, 'PUESTO LA PORTEÃA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6610, 15, 'PUESTO LA SEÃA', '5636', 0);
INSERT INTO ubicacion_localidades VALUES (6611, 15, 'PUESTO LA SUIZA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6612, 15, 'PUESTO LA TOSCA', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (6613, 15, 'PUESTO LA VENTANA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6614, 15, 'PUESTO LAS AGUADAS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6615, 15, 'PUESTO LAS CORTADERAS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6616, 15, 'PUESTO LAS HIGUERAS', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6617, 15, 'PUESTO LAS JUNTITAS', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (6618, 15, 'PUESTO LAS PICHANAS', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (6619, 15, 'PUESTO LAS PUNTANAS', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (6620, 15, 'PUESTO LAS VIBORAS', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (6621, 15, 'PUESTO LORCA', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6622, 15, 'PUESTO LORETO', '5621', 0);
INSERT INTO ubicacion_localidades VALUES (6623, 15, 'PUESTO LOS ALOJAMIENTOS', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6624, 15, 'PUESTO LOS CAUSES', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (6625, 15, 'PUESTO LOS CHAÃARES', '5435', 0);
INSERT INTO ubicacion_localidades VALUES (6626, 15, 'PUESTO LOS GAUCHOS', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (6627, 15, 'PUESTO LOS PAJARITOS', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6628, 15, 'PUESTO LOS RAMBLONES', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6629, 15, 'PUESTO LUFFI', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6630, 15, 'PUESTO LUNA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6631, 15, 'PUESTO LUNINA', '5621', 0);
INSERT INTO ubicacion_localidades VALUES (6632, 15, 'PUESTO MALLIN', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6633, 15, 'PUESTO MALO', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6634, 15, 'PUESTO MANGA DE ARRIBA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6635, 15, 'PUESTO MANZANITO', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (6636, 15, 'PUESTO MARFIL', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (6637, 15, 'PUESTO MIRONDA', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (6638, 15, 'PUESTO NIEVES NEGRAS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6639, 15, 'PUESTO NUERAS', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6640, 15, 'PUESTO OJO DE AGUA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6641, 15, 'PUESTO P MIRANDA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6642, 15, 'PUESTO P MONTRIEL', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6643, 15, 'PUESTO PUNTA DEL AGUA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6644, 15, 'PUESTO QUEMADO', '5435', 0);
INSERT INTO ubicacion_localidades VALUES (6645, 15, 'PUESTO QUIROGA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6646, 15, 'PUESTO RANCHO DE LA PAMPA', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (6647, 15, 'PUESTO RINCON DE LA PAMPA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6648, 15, 'PUESTO RINCON DEL SAUCE', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6649, 15, 'PUESTO RINCON ESCALONA', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (6650, 15, 'PUESTO RIQUITIPANCHE', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6651, 15, 'PUESTO S PEREZ', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6652, 15, 'PUESTO SAN JOSE', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (6653, 15, 'PUESTO SAN MIGUEL', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (6654, 15, 'PUESTO SAN VICENTE', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6655, 15, 'PUESTO SANTA CLARA DE ARRIBA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6656, 15, 'PUESTO SANTA MARIA', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (6657, 15, 'PUESTO SANTA MARIA', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (6658, 15, 'PUESTO SECO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6659, 15, 'PUESTO SOSA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6660, 15, 'PUESTO ULTIMA AGUADA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6661, 15, 'PUESTO VEGA', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (6662, 15, 'PUESTO VIUDA DE ESTRELLA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6663, 15, 'PUESTO VUELTA DEL ZANJON', '5636', 0);
INSERT INTO ubicacion_localidades VALUES (6664, 15, 'PUESTO ZAMPAL', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6665, 15, 'PUNTA DE VACAS', '5553', 0);
INSERT INTO ubicacion_localidades VALUES (6666, 15, 'PUNTA DEL AGUA', '5621', 0);
INSERT INTO ubicacion_localidades VALUES (6667, 15, 'PUNTA DEL CANAL', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6668, 15, 'PUNTOS DE AGUA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6669, 15, 'QUIRCACO', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6670, 15, 'R BARRI', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6671, 15, 'R BEBEDERA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6672, 15, 'R BUSTOS', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6673, 15, 'RAMA CAIDA', '5603', 0);
INSERT INTO ubicacion_localidades VALUES (6674, 15, 'RAMBLON', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (6675, 15, 'RAMBLON', '5435', 0);
INSERT INTO ubicacion_localidades VALUES (6676, 15, 'RAMBLON DE LA PAMPA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6677, 15, 'RAMBLON DE LOS CHILENOS', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (6678, 15, 'RAMBLON GRANDE', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6679, 15, 'RAMON DONAIRE', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6680, 15, 'RAMON GIMENEZ', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6681, 15, 'RANCHITOS', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6682, 15, 'RANQUIL NORTE', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (6683, 15, 'RANQUILCO POZOS PETROLIFEROS', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6684, 15, 'REAL BAYO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6685, 15, 'REAL DE MOYANO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6686, 15, 'REAL DEL COLORADO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6687, 15, 'REAL DEL LEON', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6688, 15, 'REAL DEL PADRE', '5624', 0);
INSERT INTO ubicacion_localidades VALUES (6689, 15, 'REAL DEL PELAMBRE', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6690, 15, 'REAL ESCONDIDO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6691, 15, 'REAL LOMA BLANCA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6692, 15, 'REAL PIEDRA HORADADA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6693, 15, 'REAL PRIMER RIO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6694, 15, 'REAL RINCON DE LAS OVEJAS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6695, 15, 'RECOARO', '5596', 0);
INSERT INTO ubicacion_localidades VALUES (6696, 15, 'REDUCCION', '5579', 0);
INSERT INTO ubicacion_localidades VALUES (6697, 15, 'REFUGIO LA FAJA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6698, 15, 'REFUGIO MILITAR GRAL ALVARADO', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6699, 15, 'REFUGIO VIALIDAD', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6700, 15, 'REGINO OJEDA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6701, 15, 'REINA', '5584', 0);
INSERT INTO ubicacion_localidades VALUES (6702, 15, 'RESOLANA', '5601', 0);
INSERT INTO ubicacion_localidades VALUES (6703, 15, 'RESURRECCION', '5535', 0);
INSERT INTO ubicacion_localidades VALUES (6704, 15, 'RETAMO', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (6705, 15, 'RETAMO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6706, 15, 'RETAMO PARTIDO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6707, 15, 'REYES', '5589', 0);
INSERT INTO ubicacion_localidades VALUES (6708, 15, 'RICARDO VIDELA', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (6709, 15, 'RINCON CHICO', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6710, 15, 'RINCON DE CORREA', '5611', 0);
INSERT INTO ubicacion_localidades VALUES (6711, 15, 'RINCON DE LA RAMADA CHATO', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6712, 15, 'RINCON DEL ATUEL', '5603', 0);
INSERT INTO ubicacion_localidades VALUES (6713, 15, 'RINCON ESCONDIDO', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6714, 15, 'RINCON HUAIQUERIAS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6715, 15, 'RIO BARRANCAS', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6716, 15, 'RIO BLANCO', '5553', 0);
INSERT INTO ubicacion_localidades VALUES (6717, 15, 'RIO CHICO', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6718, 15, 'RIO GRANDE', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6719, 15, 'RIVADAVIA', '5577', 0);
INSERT INTO ubicacion_localidades VALUES (6720, 15, 'RIVAS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6721, 15, 'ROBERTS', '5572', 0);
INSERT INTO ubicacion_localidades VALUES (6722, 15, 'RODEO DE LA CRUZ', '5525', 0);
INSERT INTO ubicacion_localidades VALUES (6723, 15, 'RODEO DEL MEDIO', '5529', 0);
INSERT INTO ubicacion_localidades VALUES (6724, 15, 'RODEO GRANDE', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6725, 15, 'RODOLFO ISELIN', '5603', 0);
INSERT INTO ubicacion_localidades VALUES (6726, 15, 'RODRIGUEZ', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6727, 15, 'RODRIGUEZ PEÃA', '5585', 0);
INSERT INTO ubicacion_localidades VALUES (6728, 15, 'ROSARIO GATICA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6729, 15, 'RUFINO GOMEZ', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6730, 15, 'RUIZ HUIDOBRO', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (6731, 15, 'RUSSELL', '5517', 0);
INSERT INTO ubicacion_localidades VALUES (6732, 15, 'RUTA 7 KILOMETRO 1014', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (6733, 15, 'S CORTIS', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6734, 15, 'S ESTRELLA', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6735, 15, 'SALINAS EL DIAMANTE', '5605', 0);
INSERT INTO ubicacion_localidades VALUES (6736, 15, 'SALTO DE LAS ROSAS', '5603', 0);
INSERT INTO ubicacion_localidades VALUES (6737, 15, 'SAN ALBERTO', '5545', 0);
INSERT INTO ubicacion_localidades VALUES (6738, 15, 'SAN ANTONIO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6739, 15, 'SAN CARLOS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6740, 15, 'SAN FRANCISCO DEL MONTE', '5503', 0);
INSERT INTO ubicacion_localidades VALUES (6741, 15, 'SAN IGNACIO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6742, 15, 'SAN IGNACIO', '5549', 0);
INSERT INTO ubicacion_localidades VALUES (6743, 15, 'SAN JOSE', '5535', 0);
INSERT INTO ubicacion_localidades VALUES (6744, 15, 'SAN JOSE', '5519', 0);
INSERT INTO ubicacion_localidades VALUES (6745, 15, 'SAN JOSE DE TUPUNGATO', '5561', 0);
INSERT INTO ubicacion_localidades VALUES (6746, 15, 'SAN MIGUEL', '5537', 0);
INSERT INTO ubicacion_localidades VALUES (6747, 15, 'SAN PABLO', '5563', 0);
INSERT INTO ubicacion_localidades VALUES (6748, 15, 'SAN PEDRO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6749, 15, 'SAN PEDRO', '5537', 0);
INSERT INTO ubicacion_localidades VALUES (6750, 15, 'SAN PEDRO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6751, 15, 'SAN PEDRO DE ATUEL', '5621', 0);
INSERT INTO ubicacion_localidades VALUES (6752, 15, 'SAN RAFAEL', '5600', 0);
INSERT INTO ubicacion_localidades VALUES (6753, 15, 'SAN ROQUE', '5587', 0);
INSERT INTO ubicacion_localidades VALUES (6754, 15, 'SANCHEZ DE BUSTAMANTE', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6755, 15, 'SANTA ANA', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6756, 15, 'SANTA BLANCA', '5531', 0);
INSERT INTO ubicacion_localidades VALUES (6757, 15, 'SANTA CLARA', '5561', 0);
INSERT INTO ubicacion_localidades VALUES (6758, 15, 'SANTA ELENA', '5634', 0);
INSERT INTO ubicacion_localidades VALUES (6759, 15, 'SANTA ELENA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6760, 15, 'SANTA MARIA', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6761, 15, 'SANTA MARIA DE ORO', '5579', 0);
INSERT INTO ubicacion_localidades VALUES (6762, 15, 'SANTA MARTA', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (6763, 15, 'SANTA ROSA', '5596', 0);
INSERT INTO ubicacion_localidades VALUES (6764, 15, 'SANTA TERESA', '5605', 0);
INSERT INTO ubicacion_localidades VALUES (6765, 15, 'SANTIAGO ROMERO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6766, 15, 'SANTO DOMINGO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6767, 15, 'SARMIENTO', '5513', 0);
INSERT INTO ubicacion_localidades VALUES (6768, 15, 'SATURNINO ROMERO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6769, 15, 'SERVILIANO OJEDA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6770, 15, 'SIERRA ANSILTA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6771, 15, 'SIERRA DE LAS HIGUERAS', '5545', 0);
INSERT INTO ubicacion_localidades VALUES (6772, 15, 'SIERRA DEL TONTAL', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6773, 15, 'SIXTO LEDESMA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6774, 15, 'SOITUE', '5623', 0);
INSERT INTO ubicacion_localidades VALUES (6775, 15, 'SOPANTA', '5591', 0);
INSERT INTO ubicacion_localidades VALUES (6776, 15, 'T OROZCO', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6777, 15, 'TABANERA', '5607', 0);
INSERT INTO ubicacion_localidades VALUES (6778, 15, 'TALQUENCA', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6779, 15, 'TAMBITO', '5621', 0);
INSERT INTO ubicacion_localidades VALUES (6780, 15, 'TAPERA DE LOS VIEJOS', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6781, 15, 'TAPERA NEGRA', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (6782, 15, 'TAPON', '5598', 0);
INSERT INTO ubicacion_localidades VALUES (6783, 15, 'TEODORO GARRO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6784, 15, 'TEODORO VILLARUEL', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6785, 15, 'TEOFILA ACEVEDO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6786, 15, 'TEOFILO RUBEN', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6787, 15, 'TEOFILO ZAPATA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6788, 15, 'TERESA B DE TITTARELLI', '5589', 0);
INSERT INTO ubicacion_localidades VALUES (6789, 15, 'TERMAS VILLAVICENCIO', '5545', 0);
INSERT INTO ubicacion_localidades VALUES (6790, 15, 'TIERRAS BLANCAS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6791, 15, 'TILA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6792, 15, 'TILIO ALCARAZ', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6793, 15, 'TOMAS MERCADO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6794, 15, 'TOSCAL DEL TORO', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6795, 15, 'TOTORAL', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (6796, 15, 'TRAVESIA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6797, 15, 'TRES ACEQUIAS', '5585', 0);
INSERT INTO ubicacion_localidades VALUES (6798, 15, 'TRES BANDERAS', '5517', 0);
INSERT INTO ubicacion_localidades VALUES (6799, 15, 'TRES ESQUINAS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6800, 15, 'TRES PORTEÃAS', '5589', 0);
INSERT INTO ubicacion_localidades VALUES (6801, 15, 'TRINO ROSALESO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6802, 15, 'TROPERO SOSA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6803, 15, 'TTE BENJAMIN MATIENZO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6804, 15, 'TULUMAYA', '5533', 0);
INSERT INTO ubicacion_localidades VALUES (6805, 15, 'TUNUYAN', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (6806, 15, 'TUPUNGATO', '5561', 0);
INSERT INTO ubicacion_localidades VALUES (6807, 15, 'UGARTECHE', '5509', 0);
INSERT INTO ubicacion_localidades VALUES (6808, 15, 'URISA', '5594', 0);
INSERT INTO ubicacion_localidades VALUES (6809, 15, 'USPALLATA', '5545', 0);
INSERT INTO ubicacion_localidades VALUES (6810, 15, 'V HIPODROMO', '5547', 0);
INSERT INTO ubicacion_localidades VALUES (6811, 15, 'V N DE COCHIQUITA', '5613', 0);
INSERT INTO ubicacion_localidades VALUES (6812, 15, 'VALLE DE LAS LEÃAS', '5612', 0);
INSERT INTO ubicacion_localidades VALUES (6813, 15, 'VALLE DE USPALLATA', '5545', 0);
INSERT INTO ubicacion_localidades VALUES (6814, 15, 'VALLE HERMOSO', '5587', 0);
INSERT INTO ubicacion_localidades VALUES (6815, 15, 'VEGA DE LOS BURROS', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6816, 15, 'VEGA DE PRASO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6817, 15, 'VEGA FERRAINA', '5549', 0);
INSERT INTO ubicacion_localidades VALUES (6818, 15, 'VEGAS DE LAS OVEJAS', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6819, 15, 'VEGAS DE LOS CORRALES DE ARAYA', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6820, 15, 'VERGEL', '5527', 0);
INSERT INTO ubicacion_localidades VALUES (6821, 15, 'VICENTE MUÃOZ', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6822, 15, 'VICENTE PELETAY', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6823, 15, 'VILLA ANTIGUA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6824, 15, 'VILLA ATUEL', '5622', 0);
INSERT INTO ubicacion_localidades VALUES (6825, 15, 'VILLA BASTIAS', '5561', 0);
INSERT INTO ubicacion_localidades VALUES (6826, 15, 'VILLA CATALA', '5596', 0);
INSERT INTO ubicacion_localidades VALUES (6827, 15, 'VILLA CENTENARIO', '5570', 0);
INSERT INTO ubicacion_localidades VALUES (6828, 15, 'VILLA COMPARTO', '5620', 0);
INSERT INTO ubicacion_localidades VALUES (6829, 15, 'VILLA DEL CARMEN', '5570', 0);
INSERT INTO ubicacion_localidades VALUES (6830, 15, 'VILLA GAVIOLA', '5507', 0);
INSERT INTO ubicacion_localidades VALUES (6831, 15, 'VILLA HIPODROMO', '5547', 0);
INSERT INTO ubicacion_localidades VALUES (6832, 15, 'VILLA LA PAZ', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6833, 15, 'VILLA LOS CORRALITOS', '5527', 0);
INSERT INTO ubicacion_localidades VALUES (6834, 15, 'VILLA MOLINO ORFILA', '5571', 0);
INSERT INTO ubicacion_localidades VALUES (6835, 15, 'VILLA NUEVA DE GUAYMALLEN', '5521', 0);
INSERT INTO ubicacion_localidades VALUES (6836, 15, 'VILLA PRIMAVERA', '5525', 0);
INSERT INTO ubicacion_localidades VALUES (6837, 15, 'VILLA RIVADAVIA', '5577', 0);
INSERT INTO ubicacion_localidades VALUES (6838, 15, 'VILLA SAN ISIDRO', '5577', 0);
INSERT INTO ubicacion_localidades VALUES (6839, 15, 'VILLA SECA', '5517', 0);
INSERT INTO ubicacion_localidades VALUES (6840, 15, 'VILLA SECA DE TUNUYAN', '5563', 0);
INSERT INTO ubicacion_localidades VALUES (6841, 15, 'VILLA SUAVA', '5523', 0);
INSERT INTO ubicacion_localidades VALUES (6842, 15, 'VILLA VIEJA', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6843, 15, 'VILLAS UNIDAS 25 DE MAYO', '5519', 0);
INSERT INTO ubicacion_localidades VALUES (6844, 15, 'VILLAVICENCIO', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6845, 15, 'VISTA FLORES', '5565', 0);
INSERT INTO ubicacion_localidades VALUES (6846, 15, 'VISTALBA', '5509', 0);
INSERT INTO ubicacion_localidades VALUES (6847, 15, 'VIUDA DE OROZCO', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6848, 15, 'VIUDA DE SATELO', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6849, 15, 'VOLCAN MAIPU', '5569', 0);
INSERT INTO ubicacion_localidades VALUES (6850, 15, 'VRA DE LAS VACAS', '5539', 0);
INSERT INTO ubicacion_localidades VALUES (6851, 15, 'VUELTA DEL ZANJON', '5634', 0);
INSERT INTO ubicacion_localidades VALUES (6852, 15, 'ZANJON AMARILLO', '5553', 0);
INSERT INTO ubicacion_localidades VALUES (6853, 15, 'ZANON CANAL', '5590', 0);
INSERT INTO ubicacion_localidades VALUES (6854, 15, 'ZAPATA', '5560', 0);
INSERT INTO ubicacion_localidades VALUES (6855, 16, 'ÃIRECO SUD', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (6856, 16, 'ÃORQUIN', '8349', 0);
INSERT INTO ubicacion_localidades VALUES (6857, 16, 'AÃELO', '8305', 0);
INSERT INTO ubicacion_localidades VALUES (6858, 16, 'ACHICO', '8315', 0);
INSERT INTO ubicacion_localidades VALUES (6859, 16, 'AGRIO BALSA', '8351', 0);
INSERT INTO ubicacion_localidades VALUES (6860, 16, 'AGUADA CHACAY CO', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6861, 16, 'AGUADA FLORENCIO', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (6862, 16, 'AGUAS DE LAS MULAS', '8349', 0);
INSERT INTO ubicacion_localidades VALUES (6863, 16, 'ALIANZA', '8379', 0);
INSERT INTO ubicacion_localidades VALUES (6864, 16, 'ALICURA', '8403', 0);
INSERT INTO ubicacion_localidades VALUES (6865, 16, 'ALUMINE', '8345', 0);
INSERT INTO ubicacion_localidades VALUES (6866, 16, 'ANDACOLLO', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6867, 16, 'ANQUINCO', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6868, 16, 'ARROYITO', '8313', 0);
INSERT INTO ubicacion_localidades VALUES (6869, 16, 'ARROYITO CHALLACO', '8313', 0);
INSERT INTO ubicacion_localidades VALUES (6870, 16, 'ARROYO BLANCO', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6871, 16, 'ARROYO CAHUNCO', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (6872, 16, 'ARROYO QUILLEN', '8341', 0);
INSERT INTO ubicacion_localidades VALUES (6873, 16, 'ARROYO RANQUILCO', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (6874, 16, 'AUCA MAHUIDA', '8305', 0);
INSERT INTO ubicacion_localidades VALUES (6875, 16, 'AUCA PAN', '8373', 0);
INSERT INTO ubicacion_localidades VALUES (6876, 16, 'BAJADA COLORADA', '8315', 0);
INSERT INTO ubicacion_localidades VALUES (6877, 16, 'BAJADA DE LOS MOLLES', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (6878, 16, 'BAJADA DEL AGRIO', '8351', 0);
INSERT INTO ubicacion_localidades VALUES (6879, 16, 'BAJADA DEL MARUCHO', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (6880, 16, 'BALNEARIO DEL RIO AGRIO', '8351', 0);
INSERT INTO ubicacion_localidades VALUES (6881, 16, 'BALSA DEL RIO AGRIO', '8351', 0);
INSERT INTO ubicacion_localidades VALUES (6882, 16, 'BALSA SENILLOSA', '8316', 0);
INSERT INTO ubicacion_localidades VALUES (6883, 16, 'BARDA ANGUIL', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (6884, 16, 'BARDAS NEGRAS', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (6885, 16, 'BARRANCAS', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6886, 16, 'BARRIO PELIGROSO', '8322', 0);
INSERT INTO ubicacion_localidades VALUES (6887, 16, 'BATRE LAUQUEN', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6888, 16, 'BELLA VISTA', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6889, 16, 'BUENA ESPERANZA', '8370', 0);
INSERT INTO ubicacion_localidades VALUES (6890, 16, 'BUTA CO', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6891, 16, 'BUTA MALLIN', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6892, 16, 'BUTA RANQUIL', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6893, 16, 'BUTALON', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6894, 16, 'CAÃADA SECA', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6895, 16, 'CAÃADON DE LOS INDIOS', '8375', 0);
INSERT INTO ubicacion_localidades VALUES (6896, 16, 'CAÃADON DEL INDIO', '8375', 0);
INSERT INTO ubicacion_localidades VALUES (6897, 16, 'CAEPE MALAL', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6898, 16, 'CAICHIHUE', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (6899, 16, 'CAJON DE ALMAZA', '8349', 0);
INSERT INTO ubicacion_localidades VALUES (6900, 16, 'CAJON DE CURI LEUVU', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6901, 16, 'CAJON DE LOS PATOS', '8349', 0);
INSERT INTO ubicacion_localidades VALUES (6902, 16, 'CAJON DE MANZANO', '8349', 0);
INSERT INTO ubicacion_localidades VALUES (6903, 16, 'CAJON DEL TORO', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (6904, 16, 'CAJON GRANDE', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6905, 16, 'CALEOFU', '8373', 0);
INSERT INTO ubicacion_localidades VALUES (6906, 16, 'CALIHUE', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (6907, 16, 'CAMALEONES', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6908, 16, 'CAMINERA', '8370', 0);
INSERT INTO ubicacion_localidades VALUES (6909, 16, 'CAMINERA TRAFUL', '8403', 0);
INSERT INTO ubicacion_localidades VALUES (6910, 16, 'CAMPAMENTO SOL', '8319', 0);
INSERT INTO ubicacion_localidades VALUES (6911, 16, 'CANCHA HUIGANCO', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6912, 16, 'CARRAN CARA', '8315', 0);
INSERT INTO ubicacion_localidades VALUES (6913, 16, 'CARRERI', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (6914, 16, 'CARRI LAUQUEN', '8315', 0);
INSERT INTO ubicacion_localidades VALUES (6915, 16, 'CARRI LIL', '8345', 0);
INSERT INTO ubicacion_localidades VALUES (6916, 16, 'CASA DE PIEDRA', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6917, 16, 'CATAN LIL', '8341', 0);
INSERT INTO ubicacion_localidades VALUES (6918, 16, 'CAVIAHUE', '8349', 0);
INSERT INTO ubicacion_localidades VALUES (6919, 16, 'CAYANTA', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6920, 16, 'CENTENARIO', '8309', 0);
INSERT INTO ubicacion_localidades VALUES (6921, 16, 'CERRO COLORADO', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (6922, 16, 'CERRO DE LA GRASA', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (6923, 16, 'CERRO DE LA PARVA', '8349', 0);
INSERT INTO ubicacion_localidades VALUES (6924, 16, 'CERRO DE LOS PINOS', '8379', 0);
INSERT INTO ubicacion_localidades VALUES (6925, 16, 'CERRO DEL LEON', '8313', 0);
INSERT INTO ubicacion_localidades VALUES (6926, 16, 'CERRO GATO', '8375', 0);
INSERT INTO ubicacion_localidades VALUES (6927, 16, 'CERRO NEGRO CHAPUA', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6928, 16, 'CERRO NEGRO TRICAO', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6929, 16, 'CHACABUCO', '8379', 0);
INSERT INTO ubicacion_localidades VALUES (6930, 16, 'CHACAY', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (6931, 16, 'CHACAY CO', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6932, 16, 'CHACAY MELEHUE', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6933, 16, 'CHACAYAL', '8373', 0);
INSERT INTO ubicacion_localidades VALUES (6934, 16, 'CHACAYCO', '8341', 0);
INSERT INTO ubicacion_localidades VALUES (6935, 16, 'CHALLACO', '8318', 0);
INSERT INTO ubicacion_localidades VALUES (6936, 16, 'CHAPELCO', '8370', 0);
INSERT INTO ubicacion_localidades VALUES (6937, 16, 'CHAPUA', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6938, 16, 'CHAPUA ABAJO', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6939, 16, 'CHARAHUILLA', '8341', 0);
INSERT INTO ubicacion_localidades VALUES (6940, 16, 'CHENQUECURA', '8349', 0);
INSERT INTO ubicacion_localidades VALUES (6941, 16, 'CHICHIGUAY', '8373', 0);
INSERT INTO ubicacion_localidades VALUES (6942, 16, 'CHIMEHUIN', '8373', 0);
INSERT INTO ubicacion_localidades VALUES (6943, 16, 'CHINA MUERTA', '8316', 0);
INSERT INTO ubicacion_localidades VALUES (6944, 16, 'CHINCHINA', '8379', 0);
INSERT INTO ubicacion_localidades VALUES (6945, 16, 'CHIQUILLIHUIN', '8371', 0);
INSERT INTO ubicacion_localidades VALUES (6946, 16, 'CHOCHOY MALLIN', '8349', 0);
INSERT INTO ubicacion_localidades VALUES (6947, 16, 'CHOS MALAL', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6948, 16, 'CHURRIACA', '8351', 0);
INSERT INTO ubicacion_localidades VALUES (6949, 16, 'COCHICO', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (6950, 16, 'CODIHUE', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (6951, 16, 'COIHUECO', '8351', 0);
INSERT INTO ubicacion_localidades VALUES (6952, 16, 'COLI MALAL', '8351', 0);
INSERT INTO ubicacion_localidades VALUES (6953, 16, 'COLIPILI', '8349', 0);
INSERT INTO ubicacion_localidades VALUES (6954, 16, 'COLLUN CO', '8371', 0);
INSERT INTO ubicacion_localidades VALUES (6955, 16, 'COLONIA VALENTINA', '8301', 0);
INSERT INTO ubicacion_localidades VALUES (6956, 16, 'COLONIA VALENTINA SUR', '8301', 0);
INSERT INTO ubicacion_localidades VALUES (6957, 16, 'CONFLUENCIA DEL AGUIJON', '8351', 0);
INSERT INTO ubicacion_localidades VALUES (6958, 16, 'COPAHUE', '8349', 0);
INSERT INTO ubicacion_localidades VALUES (6959, 16, 'CORRAL DE PIEDRA', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (6960, 16, 'CORRENTOSO', '8407', 0);
INSERT INTO ubicacion_localidades VALUES (6961, 16, 'COSTA DEL ARROYO SALADO', '8351', 0);
INSERT INTO ubicacion_localidades VALUES (6962, 16, 'COSTA LIMAY', '8315', 0);
INSERT INTO ubicacion_localidades VALUES (6963, 16, 'COVUNCO', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (6964, 16, 'COVUNCO ABAJO', '8351', 0);
INSERT INTO ubicacion_localidades VALUES (6965, 16, 'COVUNCO ARRIBA', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (6966, 16, 'COVUNCO CENTRO', '8351', 0);
INSERT INTO ubicacion_localidades VALUES (6967, 16, 'COYUCO', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6968, 16, 'CUCHILLO CURA', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (6969, 16, 'CULLIN MANZANO', '8401', 0);
INSERT INTO ubicacion_localidades VALUES (6970, 16, 'CURU  LEUVU', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6971, 16, 'CUTRAL CO', '8322', 0);
INSERT INTO ubicacion_localidades VALUES (6972, 16, 'EL  TROPEZON', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (6973, 16, 'EL ALAMITO', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6974, 16, 'EL ARBOLITO', '8407', 0);
INSERT INTO ubicacion_localidades VALUES (6975, 16, 'EL ATRAVESADO', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (6976, 16, 'EL BOSQUE', '8349', 0);
INSERT INTO ubicacion_localidades VALUES (6977, 16, 'EL CERRITO', '8370', 0);
INSERT INTO ubicacion_localidades VALUES (6978, 16, 'EL CHINGUE', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6979, 16, 'EL CHOLAR', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6980, 16, 'EL CURILEO', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6981, 16, 'EL DORMIDO', '8341', 0);
INSERT INTO ubicacion_localidades VALUES (6982, 16, 'EL DURAZNO', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6983, 16, 'EL ESCORIAL', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (6984, 16, 'EL GATO', '8341', 0);
INSERT INTO ubicacion_localidades VALUES (6985, 16, 'EL HUECU', '8349', 0);
INSERT INTO ubicacion_localidades VALUES (6986, 16, 'EL MACHETE', '8407', 0);
INSERT INTO ubicacion_localidades VALUES (6987, 16, 'EL MANZANO', '8403', 0);
INSERT INTO ubicacion_localidades VALUES (6988, 16, 'EL OASIS', '8370', 0);
INSERT INTO ubicacion_localidades VALUES (6989, 16, 'EL OVERO', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (6990, 16, 'EL PALAO', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (6991, 16, 'EL PERALITO', '8351', 0);
INSERT INTO ubicacion_localidades VALUES (6992, 16, 'EL PINO ANDINO', '8349', 0);
INSERT INTO ubicacion_localidades VALUES (6993, 16, 'EL PORTON', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6994, 16, 'EL PORVENIR', '8370', 0);
INSERT INTO ubicacion_localidades VALUES (6995, 16, 'EL SALADO', '8351', 0);
INSERT INTO ubicacion_localidades VALUES (6996, 16, 'EL SALITRAL', '8375', 0);
INSERT INTO ubicacion_localidades VALUES (6997, 16, 'EL SAUCE', '8313', 0);
INSERT INTO ubicacion_localidades VALUES (6998, 16, 'EL TROMEN', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (6999, 16, 'EL TROMEN', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7000, 16, 'ESPINAZO DEL ZORRO', '8341', 0);
INSERT INTO ubicacion_localidades VALUES (7001, 16, 'ESTANCIA LA PRIMAVERA', '8403', 0);
INSERT INTO ubicacion_localidades VALUES (7002, 16, 'ESTANCIA NEWBERY', '8401', 0);
INSERT INTO ubicacion_localidades VALUES (7003, 16, 'ESTANCIA TEQUEL MALAL', '8401', 0);
INSERT INTO ubicacion_localidades VALUES (7004, 16, 'FILMATUE', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7005, 16, 'FILO HUA HUM', '8370', 0);
INSERT INTO ubicacion_localidades VALUES (7006, 16, 'FLORES', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7007, 16, 'FORTIN 1 DE MAYO', '8341', 0);
INSERT INTO ubicacion_localidades VALUES (7008, 16, 'FORTIN GUAÃACOS', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7009, 16, 'FRANHUCURA', '8351', 0);
INSERT INTO ubicacion_localidades VALUES (7010, 16, 'GENTE GRANDE', '8379', 0);
INSERT INTO ubicacion_localidades VALUES (7011, 16, 'GOÃI CO', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (7012, 16, 'GUAÃACOS', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7013, 16, 'HAICHOL', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (7014, 16, 'HARAS PATRIA', '8345', 0);
INSERT INTO ubicacion_localidades VALUES (7015, 16, 'HAYCU', '8349', 0);
INSERT INTO ubicacion_localidades VALUES (7016, 16, 'HUA HUM', '8370', 0);
INSERT INTO ubicacion_localidades VALUES (7017, 16, 'HUALCUPEN', '8349', 0);
INSERT INTO ubicacion_localidades VALUES (7018, 16, 'HUARACO', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7019, 16, 'HUARINCHENQUE', '8349', 0);
INSERT INTO ubicacion_localidades VALUES (7020, 16, 'HUECHAHUE', '8375', 0);
INSERT INTO ubicacion_localidades VALUES (7021, 16, 'HUECHULAFQUEN', '8371', 0);
INSERT INTO ubicacion_localidades VALUES (7022, 16, 'HUILLILON', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (7023, 16, 'HUINCA LU', '8403', 0);
INSERT INTO ubicacion_localidades VALUES (7024, 16, 'HUINGANCO', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7025, 16, 'HUITRIN', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7026, 16, 'HUMIGAMIO', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7027, 16, 'HUNCAL', '8351', 0);
INSERT INTO ubicacion_localidades VALUES (7028, 16, 'INVERNADA VIEJA', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7029, 16, 'ISLA VICTORIA', '8400', 0);
INSERT INTO ubicacion_localidades VALUES (7030, 16, 'JECANASCO', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7031, 16, 'JUARANCO', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7032, 16, 'JUNIN DE LOS ANDES', '8371', 0);
INSERT INTO ubicacion_localidades VALUES (7033, 16, 'KILCA CASA', '8345', 0);
INSERT INTO ubicacion_localidades VALUES (7034, 16, 'LA  POCHOLA', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (7035, 16, 'LA ANGOSTURA DE ICALMA', '8345', 0);
INSERT INTO ubicacion_localidades VALUES (7036, 16, 'LA ARAUCARIA', '8401', 0);
INSERT INTO ubicacion_localidades VALUES (7037, 16, 'LA ARBOLEDA', '8341', 0);
INSERT INTO ubicacion_localidades VALUES (7038, 16, 'LA ARGENTINA', '8349', 0);
INSERT INTO ubicacion_localidades VALUES (7039, 16, 'LA ATALAYA', '8371', 0);
INSERT INTO ubicacion_localidades VALUES (7040, 16, 'LA BUITRERA', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (7041, 16, 'LA CIENAGA', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7042, 16, 'LA CIENEGUITA', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7043, 16, 'LA ESTACADA', '8401', 0);
INSERT INTO ubicacion_localidades VALUES (7044, 16, 'LA FORTUNA', '8370', 0);
INSERT INTO ubicacion_localidades VALUES (7045, 16, 'LA FRIA', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (7046, 16, 'LA ISABEL', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (7047, 16, 'LA LIPELA', '8401', 0);
INSERT INTO ubicacion_localidades VALUES (7048, 16, 'LA NEGRA', '8375', 0);
INSERT INTO ubicacion_localidades VALUES (7049, 16, 'LA OFELIA', '8341', 0);
INSERT INTO ubicacion_localidades VALUES (7050, 16, 'LA PATAGONIA', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (7051, 16, 'LA PATRIA', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (7052, 16, 'LA PINTADA', '8315', 0);
INSERT INTO ubicacion_localidades VALUES (7053, 16, 'LA PORTEÃA', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (7054, 16, 'LA PRIMAVERA', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7055, 16, 'LA RINCONADA', '8375', 0);
INSERT INTO ubicacion_localidades VALUES (7056, 16, 'LA SALADA', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7057, 16, 'LA SUSANA', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (7058, 16, 'LA TERESA', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (7059, 16, 'LA TERESA PIEDRA DEL AGUILA', '8315', 0);
INSERT INTO ubicacion_localidades VALUES (7060, 16, 'LA UNION', '8371', 0);
INSERT INTO ubicacion_localidades VALUES (7061, 16, 'LA VERDAD', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (7062, 16, 'LAGO LOG LOG', '8370', 0);
INSERT INTO ubicacion_localidades VALUES (7063, 16, 'LAGOTERA', '8345', 0);
INSERT INTO ubicacion_localidades VALUES (7064, 16, 'LAGUNA BLANCA', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (7065, 16, 'LAGUNA MIRANDA', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (7066, 16, 'LAJA', '8341', 0);
INSERT INTO ubicacion_localidades VALUES (7067, 16, 'LAPA', '8341', 0);
INSERT INTO ubicacion_localidades VALUES (7068, 16, 'LAPACHAL', '8341', 0);
INSERT INTO ubicacion_localidades VALUES (7069, 16, 'LAS ABEJAS', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7070, 16, 'LAS BANDURRIAS', '8370', 0);
INSERT INTO ubicacion_localidades VALUES (7071, 16, 'LAS BARDITAS', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (7072, 16, 'LAS CHACRAS', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7073, 16, 'LAS COLORADAS', '8341', 0);
INSERT INTO ubicacion_localidades VALUES (7074, 16, 'LAS CORTADERAS', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7075, 16, 'LAS CORTADERAS', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7076, 16, 'LAS CORTADERAS', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (7077, 16, 'LAS LAGUNAS', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7078, 16, 'LAS LAJAS', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (7079, 16, 'LAS LAJITAS', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (7080, 16, 'LAS MERCEDES', '8373', 0);
INSERT INTO ubicacion_localidades VALUES (7081, 16, 'LAS OVEJAS', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7082, 16, 'LAS PERLAS', '8300', 0);
INSERT INTO ubicacion_localidades VALUES (7083, 16, 'LAS SALADAS', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7084, 16, 'LAS TOSCAS', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (7085, 16, 'LAS TRES LAGUNAS', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (7086, 16, 'LASCAR', '8370', 0);
INSERT INTO ubicacion_localidades VALUES (7087, 16, 'LEUTO CABALLO', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7088, 16, 'LILEO', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7089, 16, 'LIMAY CENTRO', '8313', 0);
INSERT INTO ubicacion_localidades VALUES (7090, 16, 'LITRAN', '8345', 0);
INSERT INTO ubicacion_localidades VALUES (7091, 16, 'LIU CULLIN', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (7092, 16, 'LLAMUCO', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (7093, 16, 'LOG LOG', '8370', 0);
INSERT INTO ubicacion_localidades VALUES (7094, 16, 'LOMA DE LA LATA', '8300', 0);
INSERT INTO ubicacion_localidades VALUES (7095, 16, 'LONCO LUAN', '8345', 0);
INSERT INTO ubicacion_localidades VALUES (7096, 16, 'LONCO MULA', '8345', 0);
INSERT INTO ubicacion_localidades VALUES (7097, 16, 'LONCOPUE', '8349', 0);
INSERT INTO ubicacion_localidades VALUES (7098, 16, 'LOS BOLILLOS', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7099, 16, 'LOS CARRIZOS', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7100, 16, 'LOS CATUTOS', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (7101, 16, 'LOS CHACALES', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7102, 16, 'LOS CHIHUIDOS', '8305', 0);
INSERT INTO ubicacion_localidades VALUES (7103, 16, 'LOS CHINITOS', '8305', 0);
INSERT INTO ubicacion_localidades VALUES (7104, 16, 'LOS CISNES', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7105, 16, 'LOS ENTIERROS', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7106, 16, 'LOS GALPONES', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (7107, 16, 'LOS MENUCOS', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7108, 16, 'LOS MICHES', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7109, 16, 'LOS MOLLES', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7110, 16, 'LOS MUCHACHOS', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (7111, 16, 'LOS RODILLOS', '8341', 0);
INSERT INTO ubicacion_localidades VALUES (7112, 16, 'LOS SAUCES', '8313', 0);
INSERT INTO ubicacion_localidades VALUES (7113, 16, 'LOS TRES CHORROS', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7114, 16, 'LUBECA', '8371', 0);
INSERT INTO ubicacion_localidades VALUES (7115, 16, 'LUICOCO', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7116, 16, 'LUIN COCO', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7117, 16, 'MACHICO', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7118, 16, 'MALAICO', '8341', 0);
INSERT INTO ubicacion_localidades VALUES (7119, 16, 'MALLIN BLANCO', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (7120, 16, 'MALLIN CHILENO', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (7121, 16, 'MALLIN DE LA CUEVA', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (7122, 16, 'MALLIN DE LAS YEGUAS', '8341', 0);
INSERT INTO ubicacion_localidades VALUES (7123, 16, 'MALLIN DE MENA', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (7124, 16, 'MALLIN DEL RUBIO', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (7125, 16, 'MALLIN DEL TORO', '8349', 0);
INSERT INTO ubicacion_localidades VALUES (7126, 16, 'MALLIN QUEMADO', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (7127, 16, 'MAMUL MALAL', '8371', 0);
INSERT INTO ubicacion_localidades VALUES (7128, 16, 'MANZANO AMARGO', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7129, 16, 'MARI MENUCO', '8300', 0);
INSERT INTO ubicacion_localidades VALUES (7130, 16, 'MARIANO MORENO', '8351', 0);
INSERT INTO ubicacion_localidades VALUES (7131, 16, 'MAYAN MAHUIDA', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7132, 16, 'MELIQUINA', '8370', 0);
INSERT INTO ubicacion_localidades VALUES (7133, 16, 'MILLA', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7134, 16, 'MINA CARRASCOSA', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7135, 16, 'MINA LILEO', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7136, 16, 'MOQUEHUE', '8345', 0);
INSERT INTO ubicacion_localidades VALUES (7137, 16, 'MULICHINCO', '8349', 0);
INSERT INTO ubicacion_localidades VALUES (7138, 16, 'NAHUEL HUAPI', '8401', 0);
INSERT INTO ubicacion_localidades VALUES (7139, 16, 'NAHUEL MAPE', '8371', 0);
INSERT INTO ubicacion_localidades VALUES (7140, 16, 'NAHUEVE', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7141, 16, 'NALAY CULLIN', '8349', 0);
INSERT INTO ubicacion_localidades VALUES (7142, 16, 'NAU NAUCO', '8351', 0);
INSERT INTO ubicacion_localidades VALUES (7143, 16, 'NERECO NORTE', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7144, 16, 'NEUQUEN', '8300', 0);
INSERT INTO ubicacion_localidades VALUES (7145, 16, 'NIRECO', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7146, 16, 'NOGUEIRA', '8315', 0);
INSERT INTO ubicacion_localidades VALUES (7147, 16, 'OJO DE AGUA', '8341', 0);
INSERT INTO ubicacion_localidades VALUES (7148, 16, 'OJO DE AGUA', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (7149, 16, 'PALAU', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7150, 16, 'PALITUE', '8371', 0);
INSERT INTO ubicacion_localidades VALUES (7151, 16, 'PAMPA COLLON CURA', '8375', 0);
INSERT INTO ubicacion_localidades VALUES (7152, 16, 'PAMPA DE LONCO LUAN', '8345', 0);
INSERT INTO ubicacion_localidades VALUES (7153, 16, 'PAMPA DE TRIL', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7154, 16, 'PAMPA DEL MALLEO', '8373', 0);
INSERT INTO ubicacion_localidades VALUES (7155, 16, 'PAMPA DEL SALADO', '8351', 0);
INSERT INTO ubicacion_localidades VALUES (7156, 16, 'PAMPA FERREIRA', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7157, 16, 'PANTANITOS', '8313', 0);
INSERT INTO ubicacion_localidades VALUES (7158, 16, 'PASO AGUERRE', '8313', 0);
INSERT INTO ubicacion_localidades VALUES (7159, 16, 'PASO ANCHO', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (7160, 16, 'PASO BARDA', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7161, 16, 'PASO CATA TUN', '8341', 0);
INSERT INTO ubicacion_localidades VALUES (7162, 16, 'PASO COIHUE', '8401', 0);
INSERT INTO ubicacion_localidades VALUES (7163, 16, 'PASO DE LOS INDIOS', '8318', 0);
INSERT INTO ubicacion_localidades VALUES (7164, 16, 'PASO DE SAN IGNACIO', '8375', 0);
INSERT INTO ubicacion_localidades VALUES (7165, 16, 'PEÃA COLORADA', '8373', 0);
INSERT INTO ubicacion_localidades VALUES (7166, 16, 'PENINSULA HUEMUL', '8400', 0);
INSERT INTO ubicacion_localidades VALUES (7167, 16, 'PICHAIHUE', '8351', 0);
INSERT INTO ubicacion_localidades VALUES (7168, 16, 'PICHE PONON', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (7169, 16, 'PICHI NEUQUEN', '8351', 0);
INSERT INTO ubicacion_localidades VALUES (7170, 16, 'PICUN LEUFU', '8313', 0);
INSERT INTO ubicacion_localidades VALUES (7171, 16, 'PIEDRA DEL AGUILA', '8315', 0);
INSERT INTO ubicacion_localidades VALUES (7172, 16, 'PIEDRA MALA', '8371', 0);
INSERT INTO ubicacion_localidades VALUES (7173, 16, 'PIEDRA PINTADA', '8315', 0);
INSERT INTO ubicacion_localidades VALUES (7174, 16, 'PIEDRAS BAYAS', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (7175, 16, 'PILMATUE', '8351', 0);
INSERT INTO ubicacion_localidades VALUES (7176, 16, 'PILO LIL', '8373', 0);
INSERT INTO ubicacion_localidades VALUES (7177, 16, 'PINO HACHADO', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (7178, 16, 'PINO SOLO', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (7179, 16, 'PLANICIE BANDERITA', '8301', 0);
INSERT INTO ubicacion_localidades VALUES (7180, 16, 'PLAZA HUINCUL', '8318', 0);
INSERT INTO ubicacion_localidades VALUES (7181, 16, 'PLOTTIER', '8316', 0);
INSERT INTO ubicacion_localidades VALUES (7182, 16, 'PORTADA COVUNCO', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (7183, 16, 'PORTEZUELO GRANDE', '8300', 0);
INSERT INTO ubicacion_localidades VALUES (7184, 16, 'POZO HUALICHES', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (7185, 16, 'PRIMEROS PINOS', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (7186, 16, 'PUEBLO NUEVO', '8322', 0);
INSERT INTO ubicacion_localidades VALUES (7187, 16, 'PUENTE PICUN LEUFU', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (7188, 16, 'PUERTO ANCHORENA', '8400', 0);
INSERT INTO ubicacion_localidades VALUES (7189, 16, 'PUERTO MANZANO', '8407', 0);
INSERT INTO ubicacion_localidades VALUES (7190, 16, 'PUESTO HERNANDEZ  BATERIAS', '8319', 0);
INSERT INTO ubicacion_localidades VALUES (7191, 16, 'PUNTA DE SIERRA', '8305', 0);
INSERT INTO ubicacion_localidades VALUES (7192, 16, 'QUEBRADA HONDA', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (7193, 16, 'QUEMPU LEUFU', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7194, 16, 'QUENTRENQUEN', '8373', 0);
INSERT INTO ubicacion_localidades VALUES (7195, 16, 'QUILA CHANQUIL', '8345', 0);
INSERT INTO ubicacion_localidades VALUES (7196, 16, 'QUILA QUEHUE', '8371', 0);
INSERT INTO ubicacion_localidades VALUES (7197, 16, 'QUILA QUINA', '8370', 0);
INSERT INTO ubicacion_localidades VALUES (7198, 16, 'QUILCA', '8345', 0);
INSERT INTO ubicacion_localidades VALUES (7199, 16, 'QUILI MALAL', '8351', 0);
INSERT INTO ubicacion_localidades VALUES (7200, 16, 'QUILLEN', '8341', 0);
INSERT INTO ubicacion_localidades VALUES (7201, 16, 'QUILQUIHUE', '8371', 0);
INSERT INTO ubicacion_localidades VALUES (7202, 16, 'QUININELIU', '8341', 0);
INSERT INTO ubicacion_localidades VALUES (7203, 16, 'QUINQUIMITREO', '8373', 0);
INSERT INTO ubicacion_localidades VALUES (7204, 16, 'QUINTUCO', '8351', 0);
INSERT INTO ubicacion_localidades VALUES (7205, 16, 'QUITA QUINA', '8370', 0);
INSERT INTO ubicacion_localidades VALUES (7206, 16, 'RAHUE', '8341', 0);
INSERT INTO ubicacion_localidades VALUES (7207, 16, 'RAMICHAL', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (7208, 16, 'RANQUELES', '8349', 0);
INSERT INTO ubicacion_localidades VALUES (7209, 16, 'RANQUIL VEGA', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7210, 16, 'RANQUILCO', '8349', 0);
INSERT INTO ubicacion_localidades VALUES (7211, 16, 'RANQUILON', '8349', 0);
INSERT INTO ubicacion_localidades VALUES (7212, 16, 'REÃILEO', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7213, 16, 'RICHOIQUE', '8349', 0);
INSERT INTO ubicacion_localidades VALUES (7214, 16, 'RINCON CHICO', '8401', 0);
INSERT INTO ubicacion_localidades VALUES (7215, 16, 'RINCON DE EMILIO', '8300', 0);
INSERT INTO ubicacion_localidades VALUES (7216, 16, 'RINCON DE LOS SAUCES', '8319', 0);
INSERT INTO ubicacion_localidades VALUES (7217, 16, 'RINCON GRANDE', '8401', 0);
INSERT INTO ubicacion_localidades VALUES (7218, 16, 'RIO AGRIO', '8351', 0);
INSERT INTO ubicacion_localidades VALUES (7219, 16, 'RIO BARRANCAS', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7220, 16, 'RUCA CHORROY ARRIBA', '8345', 0);
INSERT INTO ubicacion_localidades VALUES (7221, 16, 'SAÃICO', '8315', 0);
INSERT INTO ubicacion_localidades VALUES (7222, 16, 'SAINUCO', '8345', 0);
INSERT INTO ubicacion_localidades VALUES (7223, 16, 'SALQUICO', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (7224, 16, 'SAN BERNARDO', '8315', 0);
INSERT INTO ubicacion_localidades VALUES (7225, 16, 'SAN DEMETRIO', '8347', 0);
INSERT INTO ubicacion_localidades VALUES (7226, 16, 'SAN EDUARDO', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7227, 16, 'SAN IGNACIO', '8375', 0);
INSERT INTO ubicacion_localidades VALUES (7228, 16, 'SAN JUAN JUNIN DE LOS ANDES', '8371', 0);
INSERT INTO ubicacion_localidades VALUES (7229, 16, 'SAN JUAN RAHUE', '8341', 0);
INSERT INTO ubicacion_localidades VALUES (7230, 16, 'SAN MARTIN DE LOS ANDES', '8370', 0);
INSERT INTO ubicacion_localidades VALUES (7231, 16, 'SAN PATRICIO DEL CHAÃAR', '8305', 0);
INSERT INTO ubicacion_localidades VALUES (7232, 16, 'SANTA ISABEL', '8341', 0);
INSERT INTO ubicacion_localidades VALUES (7233, 16, 'SANTA ISABEL', '8315', 0);
INSERT INTO ubicacion_localidades VALUES (7234, 16, 'SANTA ISABEL', '8349', 0);
INSERT INTO ubicacion_localidades VALUES (7235, 16, 'SANTA MARIA', '8401', 0);
INSERT INTO ubicacion_localidades VALUES (7236, 16, 'SANTO DOMINGO', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (7237, 16, 'SANTO TOMAS', '8315', 0);
INSERT INTO ubicacion_localidades VALUES (7238, 16, 'SAUZAL BONITO', '8319', 0);
INSERT INTO ubicacion_localidades VALUES (7239, 16, 'SENILLOSA', '8316', 0);
INSERT INTO ubicacion_localidades VALUES (7240, 16, 'TAQUI NILEU', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (7241, 16, 'TAQUIMILAN', '8351', 0);
INSERT INTO ubicacion_localidades VALUES (7242, 16, 'TAQUIMILLAN ABAJO', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7243, 16, 'TIERRAS BLANCAS', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7244, 16, 'TIHUE', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7245, 16, 'TIPILIUKE', '8373', 0);
INSERT INTO ubicacion_localidades VALUES (7246, 16, 'TRAHUNCURA', '8351', 0);
INSERT INTO ubicacion_localidades VALUES (7247, 16, 'TRALATUE', '8349', 0);
INSERT INTO ubicacion_localidades VALUES (7248, 16, 'TRATAYEN', '8305', 0);
INSERT INTO ubicacion_localidades VALUES (7249, 16, 'TRES CHORROS', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7250, 16, 'TRES PICOS', '8371', 0);
INSERT INTO ubicacion_localidades VALUES (7251, 16, 'TRES PIEDRAS', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (7252, 16, 'TRICAO MALAL', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7253, 16, 'TRILI', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7254, 16, 'TROMEN', '8371', 0);
INSERT INTO ubicacion_localidades VALUES (7255, 16, 'TROMPUL', '8370', 0);
INSERT INTO ubicacion_localidades VALUES (7256, 16, 'VACA MUERTA', '8351', 0);
INSERT INTO ubicacion_localidades VALUES (7257, 16, 'VARVARCO', '8353', 0);
INSERT INTO ubicacion_localidades VALUES (7258, 16, 'VILLA EL CHOCON', '8311', 0);
INSERT INTO ubicacion_localidades VALUES (7259, 16, 'VILLA LA ANGOSTURA', '8407', 0);
INSERT INTO ubicacion_localidades VALUES (7260, 16, 'VILLA MALLIN', '8349', 0);
INSERT INTO ubicacion_localidades VALUES (7261, 16, 'VILLA PEHUENIA', '8345', 0);
INSERT INTO ubicacion_localidades VALUES (7262, 16, 'VILLA RAUR', '8370', 0);
INSERT INTO ubicacion_localidades VALUES (7263, 16, 'VILLA TRAFUL', '8403', 0);
INSERT INTO ubicacion_localidades VALUES (7264, 16, 'VISTA ALEGRE NORTE', '8309', 0);
INSERT INTO ubicacion_localidades VALUES (7265, 16, 'VISTA ALEGRE SUR', '8309', 0);
INSERT INTO ubicacion_localidades VALUES (7266, 16, 'ZAINA YEGUA', '8315', 0);
INSERT INTO ubicacion_localidades VALUES (7267, 16, 'ZAPALA', '8340', 0);
INSERT INTO ubicacion_localidades VALUES (7268, 16, 'ZINGONE Y CIA M', '8375', 0);
INSERT INTO ubicacion_localidades VALUES (7269, 16, 'ZULEMITA', '8375', 0);
INSERT INTO ubicacion_localidades VALUES (7270, 17, '6 DE OCTUBRE', '8134', 0);
INSERT INTO ubicacion_localidades VALUES (7271, 17, 'ÃIRIHUAO ESTACION FCGR', '8400', 0);
INSERT INTO ubicacion_localidades VALUES (7272, 17, 'ÃORQUINCO', '8415', 0);
INSERT INTO ubicacion_localidades VALUES (7273, 17, 'AGUADA CECILIO', '8534', 0);
INSERT INTO ubicacion_localidades VALUES (7274, 17, 'AGUADA DE GUERRA', '8424', 0);
INSERT INTO ubicacion_localidades VALUES (7275, 17, 'AGUADA DE LOS PAJARITOS', '8301', 0);
INSERT INTO ubicacion_localidades VALUES (7276, 17, 'AGUADA DE PIEDRA', '8422', 0);
INSERT INTO ubicacion_localidades VALUES (7277, 17, 'AGUADA DEL LORO', '8520', 0);
INSERT INTO ubicacion_localidades VALUES (7278, 17, 'AGUADA GUZMAN', '8333', 0);
INSERT INTO ubicacion_localidades VALUES (7279, 17, 'AGUARA', '8307', 0);
INSERT INTO ubicacion_localidades VALUES (7280, 17, 'ALANITOS', '8301', 0);
INSERT INTO ubicacion_localidades VALUES (7281, 17, 'ALLEN', '8328', 0);
INSERT INTO ubicacion_localidades VALUES (7282, 17, 'ANECON CHICO', '8418', 0);
INSERT INTO ubicacion_localidades VALUES (7283, 17, 'ANECON GRANDE', '8416', 0);
INSERT INTO ubicacion_localidades VALUES (7284, 17, 'ARROYO BLANCO', '8403', 0);
INSERT INTO ubicacion_localidades VALUES (7285, 17, 'ARROYO CHACAY', '8401', 0);
INSERT INTO ubicacion_localidades VALUES (7286, 17, 'ARROYO LA VENTANA', '8521', 0);
INSERT INTO ubicacion_localidades VALUES (7287, 17, 'ARROYO LAS MINAS', '8415', 0);
INSERT INTO ubicacion_localidades VALUES (7288, 17, 'ARROYO LOS BERROS', '8521', 0);
INSERT INTO ubicacion_localidades VALUES (7289, 17, 'ARROYO SALADO', '8532', 0);
INSERT INTO ubicacion_localidades VALUES (7290, 17, 'ARROYO TEMBRADO', '8521', 0);
INSERT INTO ubicacion_localidades VALUES (7291, 17, 'ARROYO VERDE', '8521', 0);
INSERT INTO ubicacion_localidades VALUES (7292, 17, 'ATRAICO', '8418', 0);
INSERT INTO ubicacion_localidades VALUES (7293, 17, 'BAJO DEL GUALICHO', '8520', 0);
INSERT INTO ubicacion_localidades VALUES (7294, 17, 'BAJO RICO', '8360', 0);
INSERT INTO ubicacion_localidades VALUES (7295, 17, 'BALNEARIO EL CONDOR', '8501', 0);
INSERT INTO ubicacion_localidades VALUES (7296, 17, 'BALNEARIO LAS GRUTAS', '8521', 0);
INSERT INTO ubicacion_localidades VALUES (7297, 17, 'BARDA COLORADA', '8333', 0);
INSERT INTO ubicacion_localidades VALUES (7298, 17, 'BARDA DEL MEDIO', '8305', 0);
INSERT INTO ubicacion_localidades VALUES (7299, 17, 'BARILOCHE', '8400', 0);
INSERT INTO ubicacion_localidades VALUES (7300, 17, 'BARRIL NIYEO', '8422', 0);
INSERT INTO ubicacion_localidades VALUES (7301, 17, 'BARRIO LAGUNA', '8520', 0);
INSERT INTO ubicacion_localidades VALUES (7302, 17, 'BARRIO NORTE', '8328', 0);
INSERT INTO ubicacion_localidades VALUES (7303, 17, 'BENJAMIN ZORRILLA', '8360', 0);
INSERT INTO ubicacion_localidades VALUES (7304, 17, 'BOCA DE LA TRAVESIA', '8505', 0);
INSERT INTO ubicacion_localidades VALUES (7305, 17, 'BUENA PARADA', '8138', 0);
INSERT INTO ubicacion_localidades VALUES (7306, 17, 'CAÃADON CAMALLO', '8416', 0);
INSERT INTO ubicacion_localidades VALUES (7307, 17, 'CAÃADON CHILENO', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7308, 17, 'CAÃADON DEL CORRAL', '8412', 0);
INSERT INTO ubicacion_localidades VALUES (7309, 17, 'CALTRAUNA', '8424', 0);
INSERT INTO ubicacion_localidades VALUES (7310, 17, 'CAMPANA MAHUIDA', '8532', 0);
INSERT INTO ubicacion_localidades VALUES (7311, 17, 'CANLLEQUIN', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7312, 17, 'CARHUE', '8412', 0);
INSERT INTO ubicacion_localidades VALUES (7313, 17, 'CARRI YEGUA', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7314, 17, 'CARRILAUQUEN', '8418', 0);
INSERT INTO ubicacion_localidades VALUES (7315, 17, 'CASA QUEMADA', '8412', 0);
INSERT INTO ubicacion_localidades VALUES (7316, 17, 'CASCADA LOS CANTAROS', '8411', 0);
INSERT INTO ubicacion_localidades VALUES (7317, 17, 'CATRIEL', '8307', 0);
INSERT INTO ubicacion_localidades VALUES (7318, 17, 'CERRO ABANICO', '8424', 0);
INSERT INTO ubicacion_localidades VALUES (7319, 17, 'CERRO ALTO', '8403', 0);
INSERT INTO ubicacion_localidades VALUES (7320, 17, 'CERRO DE POLICIA', '8333', 0);
INSERT INTO ubicacion_localidades VALUES (7321, 17, 'CERRO MESA', '8415', 0);
INSERT INTO ubicacion_localidades VALUES (7322, 17, 'CERVANTES', '8326', 0);
INSERT INTO ubicacion_localidades VALUES (7323, 17, 'CHACALHUA RUCA', '8415', 0);
INSERT INTO ubicacion_localidades VALUES (7324, 17, 'CHACAY HUARRUCA', '8415', 0);
INSERT INTO ubicacion_localidades VALUES (7325, 17, 'CHACRAS DE ALLEN', '8328', 0);
INSERT INTO ubicacion_localidades VALUES (7326, 17, 'CHAIFUL', '8418', 0);
INSERT INTO ubicacion_localidades VALUES (7327, 17, 'CHASICO', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7328, 17, 'CHELFORO', '8366', 0);
INSERT INTO ubicacion_localidades VALUES (7329, 17, 'CHENQUENIYEU', '8412', 0);
INSERT INTO ubicacion_localidades VALUES (7330, 17, 'CHICHIHUAO', '8422', 0);
INSERT INTO ubicacion_localidades VALUES (7331, 17, 'CHICHINALES', '8336', 0);
INSERT INTO ubicacion_localidades VALUES (7332, 17, 'CHIMPAY', '8364', 0);
INSERT INTO ubicacion_localidades VALUES (7333, 17, 'CHINA MUERTA', '8505', 0);
INSERT INTO ubicacion_localidades VALUES (7334, 17, 'CHIPAUQUIL', '8536', 0);
INSERT INTO ubicacion_localidades VALUES (7335, 17, 'CHOCORI', '8503', 0);
INSERT INTO ubicacion_localidades VALUES (7336, 17, 'CHOELE CHOEL', '8360', 0);
INSERT INTO ubicacion_localidades VALUES (7337, 17, 'CHURQUIÃEO', '8412', 0);
INSERT INTO ubicacion_localidades VALUES (7338, 17, 'CINCO CHAÃARES', '8520', 0);
INSERT INTO ubicacion_localidades VALUES (7339, 17, 'CINCO SALTOS', '8303', 0);
INSERT INTO ubicacion_localidades VALUES (7340, 17, 'CIPOLLETTI', '8324', 0);
INSERT INTO ubicacion_localidades VALUES (7341, 17, 'CLEMENTE ONELLI', '8416', 0);
INSERT INTO ubicacion_localidades VALUES (7342, 17, 'COLI TORO', '8418', 0);
INSERT INTO ubicacion_localidades VALUES (7343, 17, 'COLONIA ALTE GUERRICO', '8307', 0);
INSERT INTO ubicacion_localidades VALUES (7344, 17, 'COLONIA EL MANZANO', '8305', 0);
INSERT INTO ubicacion_localidades VALUES (7345, 17, 'COLONIA GENERAL FRIAS', '8501', 0);
INSERT INTO ubicacion_localidades VALUES (7346, 17, 'COLONIA JOSEFA', '8363', 0);
INSERT INTO ubicacion_localidades VALUES (7347, 17, 'COLONIA JULIA Y ECHARREN', '8138', 0);
INSERT INTO ubicacion_localidades VALUES (7348, 17, 'COLONIA LA LUISA', '8503', 0);
INSERT INTO ubicacion_localidades VALUES (7349, 17, 'COLONIA REGINA', '8336', 0);
INSERT INTO ubicacion_localidades VALUES (7350, 17, 'COLONIA RUSA', '8332', 0);
INSERT INTO ubicacion_localidades VALUES (7351, 17, 'COLONIA SAN JUAN', '8503', 0);
INSERT INTO ubicacion_localidades VALUES (7352, 17, 'COMALLO', '8416', 0);
INSERT INTO ubicacion_localidades VALUES (7353, 17, 'COMALLO ABAJO', '8416', 0);
INSERT INTO ubicacion_localidades VALUES (7354, 17, 'COMI C', '8424', 0);
INSERT INTO ubicacion_localidades VALUES (7355, 17, 'CONA NIYEU', '8521', 0);
INSERT INTO ubicacion_localidades VALUES (7356, 17, 'CONTRALMIRANTE CORDERO', '8301', 0);
INSERT INTO ubicacion_localidades VALUES (7357, 17, 'CONTRALMIRANTE M GUERRICO', '8328', 0);
INSERT INTO ubicacion_localidades VALUES (7358, 17, 'COQUELEN', '8416', 0);
INSERT INTO ubicacion_localidades VALUES (7359, 17, 'CORONEL BELISLE', '8364', 0);
INSERT INTO ubicacion_localidades VALUES (7360, 17, 'CORONEL EUGENIO DEL BUSTO', '8138', 0);
INSERT INTO ubicacion_localidades VALUES (7361, 17, 'CORONEL FRANCISCO SOSA', '8503', 0);
INSERT INTO ubicacion_localidades VALUES (7362, 17, 'CORONEL JUAN JOSE GOMEZ', '8333', 0);
INSERT INTO ubicacion_localidades VALUES (7363, 17, 'CORONEL VIDAL', '8305', 0);
INSERT INTO ubicacion_localidades VALUES (7364, 17, 'CORRAL DE LOS PINOS', '8412', 0);
INSERT INTO ubicacion_localidades VALUES (7365, 17, 'CORRALITO', '8403', 0);
INSERT INTO ubicacion_localidades VALUES (7366, 17, 'COS ZAURES', '8307', 0);
INSERT INTO ubicacion_localidades VALUES (7367, 17, 'COSTA DEL RIO AZUL', '8430', 0);
INSERT INTO ubicacion_localidades VALUES (7368, 17, 'COSTAS DEL PICHI LEUFU', '8412', 0);
INSERT INTO ubicacion_localidades VALUES (7369, 17, 'CUATRO ESQUINAS', '8324', 0);
INSERT INTO ubicacion_localidades VALUES (7370, 17, 'CUENCA VIDAL', '8301', 0);
INSERT INTO ubicacion_localidades VALUES (7371, 17, 'CUESTA DEL TERNERO', '9210', 0);
INSERT INTO ubicacion_localidades VALUES (7372, 17, 'CURA LAUQUEN', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7373, 17, 'DARWIN', '8364', 0);
INSERT INTO ubicacion_localidades VALUES (7374, 17, 'DINA HUAPI', '8402', 0);
INSERT INTO ubicacion_localidades VALUES (7375, 17, 'EL BOLSON', '8430', 0);
INSERT INTO ubicacion_localidades VALUES (7376, 17, 'EL CACIQUE', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7377, 17, 'EL CAIN', '8422', 0);
INSERT INTO ubicacion_localidades VALUES (7378, 17, 'EL CAMARURO', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7379, 17, 'EL CHEIFUL', '8418', 0);
INSERT INTO ubicacion_localidades VALUES (7380, 17, 'EL CONDOR ESTANCIA', '8400', 0);
INSERT INTO ubicacion_localidades VALUES (7381, 17, 'EL CUY', '8333', 0);
INSERT INTO ubicacion_localidades VALUES (7382, 17, 'EL DIQUE', '8500', 0);
INSERT INTO ubicacion_localidades VALUES (7383, 17, 'EL FOYEL', '8401', 0);
INSERT INTO ubicacion_localidades VALUES (7384, 17, 'EL GAUCHO POBRE', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7385, 17, 'EL JARDINERO', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7386, 17, 'EL MANSO', '8430', 0);
INSERT INTO ubicacion_localidades VALUES (7387, 17, 'EL MANZANO', '8326', 0);
INSERT INTO ubicacion_localidades VALUES (7388, 17, 'EL MOLIGUE', '8418', 0);
INSERT INTO ubicacion_localidades VALUES (7389, 17, 'EL PANTANOSO', '8412', 0);
INSERT INTO ubicacion_localidades VALUES (7390, 17, 'EL PORVENIR', '8503', 0);
INSERT INTO ubicacion_localidades VALUES (7391, 17, 'EL SALADO', '8536', 0);
INSERT INTO ubicacion_localidades VALUES (7392, 17, 'EMPALME KILOMETRO 648', '8418', 0);
INSERT INTO ubicacion_localidades VALUES (7393, 17, 'ESTANCIA LAS JULIAS', '8363', 0);
INSERT INTO ubicacion_localidades VALUES (7394, 17, 'FALCKNER', '8534', 0);
INSERT INTO ubicacion_localidades VALUES (7395, 17, 'FERRI', '8301', 0);
INSERT INTO ubicacion_localidades VALUES (7396, 17, 'FITALANCAO', '8415', 0);
INSERT INTO ubicacion_localidades VALUES (7397, 17, 'FITAMICHE', '8415', 0);
INSERT INTO ubicacion_localidades VALUES (7398, 17, 'FITATIMEN', '8415', 0);
INSERT INTO ubicacion_localidades VALUES (7399, 17, 'FORTIN UNO', '8360', 0);
INSERT INTO ubicacion_localidades VALUES (7400, 17, 'FUTA RUIN', '8418', 0);
INSERT INTO ubicacion_localidades VALUES (7401, 17, 'GANZU LAUQUEN', '8424', 0);
INSERT INTO ubicacion_localidades VALUES (7402, 17, 'GENERAL CONESA', '8503', 0);
INSERT INTO ubicacion_localidades VALUES (7403, 17, 'GENERAL ENRIQUE GODOY', '8336', 0);
INSERT INTO ubicacion_localidades VALUES (7404, 17, 'GENERAL FERNANDEZ ORO', '8324', 0);
INSERT INTO ubicacion_localidades VALUES (7405, 17, 'GENERAL LIBORIO BERNAL', '8500', 0);
INSERT INTO ubicacion_localidades VALUES (7406, 17, 'GENERAL ROCA', '8332', 0);
INSERT INTO ubicacion_localidades VALUES (7407, 17, 'GUARDIA MITRE', '8505', 0);
INSERT INTO ubicacion_localidades VALUES (7408, 17, 'HUA MICHE', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7409, 17, 'HUAN LUAN', '8418', 0);
INSERT INTO ubicacion_localidades VALUES (7410, 17, 'INGENIERO HUERGO', '8334', 0);
INSERT INTO ubicacion_localidades VALUES (7411, 17, 'INGENIERO JACOBACCI', '8418', 0);
INSERT INTO ubicacion_localidades VALUES (7412, 17, 'INGENIERO ZIMMERMANN RESTA', '8416', 0);
INSERT INTO ubicacion_localidades VALUES (7413, 17, 'INGENIO SAN LORENZO', '8503', 0);
INSERT INTO ubicacion_localidades VALUES (7414, 17, 'IRIS', '8324', 0);
INSERT INTO ubicacion_localidades VALUES (7415, 17, 'ISLA CHICA', '8363', 0);
INSERT INTO ubicacion_localidades VALUES (7416, 17, 'ISLA GRANDE', '8361', 0);
INSERT INTO ubicacion_localidades VALUES (7417, 17, 'JAG_EL CAMPO MONTE', '8520', 0);
INSERT INTO ubicacion_localidades VALUES (7418, 17, 'JANINUE', '8534', 0);
INSERT INTO ubicacion_localidades VALUES (7419, 17, 'JITA RUSIA', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7420, 17, 'JUAN DE GARAY', '8134', 0);
INSERT INTO ubicacion_localidades VALUES (7421, 17, 'KILI MALAL', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7422, 17, 'KILOMETRO 1218', '8305', 0);
INSERT INTO ubicacion_localidades VALUES (7423, 17, 'LA ADELA', '8138', 0);
INSERT INTO ubicacion_localidades VALUES (7424, 17, 'LA ALIANZA', '8324', 0);
INSERT INTO ubicacion_localidades VALUES (7425, 17, 'LA ANGOSTURA', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7426, 17, 'LA BOMBILLA', '8520', 0);
INSERT INTO ubicacion_localidades VALUES (7427, 17, 'LA CAROLINA', '8503', 0);
INSERT INTO ubicacion_localidades VALUES (7428, 17, 'LA CHILENA', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7429, 17, 'LA CRIOLLITA', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7430, 17, 'LA ELVIRA', '8360', 0);
INSERT INTO ubicacion_localidades VALUES (7431, 17, 'LA EMILIA', '8324', 0);
INSERT INTO ubicacion_localidades VALUES (7432, 17, 'LA ESMERALDA', '8324', 0);
INSERT INTO ubicacion_localidades VALUES (7433, 17, 'LA ESPERANZA', '8534', 0);
INSERT INTO ubicacion_localidades VALUES (7434, 17, 'LA ESTANCIA VIEJA', '8324', 0);
INSERT INTO ubicacion_localidades VALUES (7435, 17, 'LA ESTRELLA', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7436, 17, 'LA EXCURRA', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7437, 17, 'LA FLECHA', '8503', 0);
INSERT INTO ubicacion_localidades VALUES (7438, 17, 'LA GRANJA', '8501', 0);
INSERT INTO ubicacion_localidades VALUES (7439, 17, 'LA JULIA', '8363', 0);
INSERT INTO ubicacion_localidades VALUES (7440, 17, 'LA LOBERIA', '8501', 0);
INSERT INTO ubicacion_localidades VALUES (7441, 17, 'LA LUCINDA', '8324', 0);
INSERT INTO ubicacion_localidades VALUES (7442, 17, 'LA MARIA INES', '8138', 0);
INSERT INTO ubicacion_localidades VALUES (7443, 17, 'LA MESETA', '8500', 0);
INSERT INTO ubicacion_localidades VALUES (7444, 17, 'LA MIMOSA', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7445, 17, 'LA PORTEÃA', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7446, 17, 'LA PRIMAVERA', '8520', 0);
INSERT INTO ubicacion_localidades VALUES (7447, 17, 'LA QUEBRADA', '8412', 0);
INSERT INTO ubicacion_localidades VALUES (7448, 17, 'LA RINCONADA', '8424', 0);
INSERT INTO ubicacion_localidades VALUES (7449, 17, 'LA RUBIA', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7450, 17, 'LA SARA', '8360', 0);
INSERT INTO ubicacion_localidades VALUES (7451, 17, 'LA VENCEDORA', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7452, 17, 'LAGO PELLEGRINI', '8305', 0);
INSERT INTO ubicacion_localidades VALUES (7453, 17, 'LAGUNA CORTES', '8520', 0);
INSERT INTO ubicacion_localidades VALUES (7454, 17, 'LAGUNA DE LA PRUEBA', '8520', 0);
INSERT INTO ubicacion_localidades VALUES (7455, 17, 'LAGUNA DEL BARRO', '8514', 0);
INSERT INTO ubicacion_localidades VALUES (7456, 17, 'LAGUNA DEL MONTE', '8514', 0);
INSERT INTO ubicacion_localidades VALUES (7457, 17, 'LAGUNA FRIAS', '8411', 0);
INSERT INTO ubicacion_localidades VALUES (7458, 17, 'LAGUNA LOS JUNCOS', '8400', 0);
INSERT INTO ubicacion_localidades VALUES (7459, 17, 'LAGUNITA', '8424', 0);
INSERT INTO ubicacion_localidades VALUES (7460, 17, 'LAMARQUE', '8363', 0);
INSERT INTO ubicacion_localidades VALUES (7461, 17, 'LANQUIÃEO', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7462, 17, 'LAS BAYAS', '8412', 0);
INSERT INTO ubicacion_localidades VALUES (7463, 17, 'LAS MAQUINAS', '8520', 0);
INSERT INTO ubicacion_localidades VALUES (7464, 17, 'LAS MELLIZAS', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7465, 17, 'LAS PIEDRAS', '8134', 0);
INSERT INTO ubicacion_localidades VALUES (7466, 17, 'LENZANIYEN', '8424', 0);
INSERT INTO ubicacion_localidades VALUES (7467, 17, 'LOMA BLANCA', '8424', 0);
INSERT INTO ubicacion_localidades VALUES (7468, 17, 'LONCO VACA', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7469, 17, 'LOS COSTEROS', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7470, 17, 'LOS JUNCOS', '8422', 0);
INSERT INTO ubicacion_localidades VALUES (7471, 17, 'LOS MANANTIALES', '8422', 0);
INSERT INTO ubicacion_localidades VALUES (7472, 17, 'LOS MENUCOS', '8424', 0);
INSERT INTO ubicacion_localidades VALUES (7473, 17, 'LOS MOLINOS', '8360', 0);
INSERT INTO ubicacion_localidades VALUES (7474, 17, 'LOS PIRINEOS', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7475, 17, 'LOS QUEBRACHOS', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7476, 17, 'LOS REPOLLOS', '8430', 0);
INSERT INTO ubicacion_localidades VALUES (7477, 17, 'LOS SAUCES', '8307', 0);
INSERT INTO ubicacion_localidades VALUES (7478, 17, 'LUIS BELTRAN', '8361', 0);
INSERT INTO ubicacion_localidades VALUES (7479, 17, 'LUIS M ZAGAGLIA', '8503', 0);
INSERT INTO ubicacion_localidades VALUES (7480, 17, 'MAINQUE', '8326', 0);
INSERT INTO ubicacion_localidades VALUES (7481, 17, 'MALLIN AHOGADO', '8430', 0);
INSERT INTO ubicacion_localidades VALUES (7482, 17, 'MAMUEL CHOIQUE', '8415', 0);
INSERT INTO ubicacion_localidades VALUES (7483, 17, 'MANCHA BLANCA', '8520', 0);
INSERT INTO ubicacion_localidades VALUES (7484, 17, 'MANCULLIQUE', '8422', 0);
INSERT INTO ubicacion_localidades VALUES (7485, 17, 'MAQUINCHAO', '8422', 0);
INSERT INTO ubicacion_localidades VALUES (7486, 17, 'MARIA CRISTINA', '8360', 0);
INSERT INTO ubicacion_localidades VALUES (7487, 17, 'MATA NEGRA', '8500', 0);
INSERT INTO ubicacion_localidades VALUES (7488, 17, 'MEDANOS NEGROS', '8134', 0);
INSERT INTO ubicacion_localidades VALUES (7489, 17, 'MENCUE', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7490, 17, 'MENUCO VACA MUERTA', '8412', 0);
INSERT INTO ubicacion_localidades VALUES (7491, 17, 'MICHI HONOCA', '8301', 0);
INSERT INTO ubicacion_localidades VALUES (7492, 17, 'MICHIHUAO', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7493, 17, 'MINISTRO RAMOS MEXIA', '8534', 0);
INSERT INTO ubicacion_localidades VALUES (7494, 17, 'MULANILLO', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7495, 17, 'MUSTERS', '8536', 0);
INSERT INTO ubicacion_localidades VALUES (7496, 17, 'NAHUEL NIYEU', '8534', 0);
INSERT INTO ubicacion_localidades VALUES (7497, 17, 'NAUPA HUEN', '8313', 0);
INSERT INTO ubicacion_localidades VALUES (7498, 17, 'NEGRO MUERTO', '8360', 0);
INSERT INTO ubicacion_localidades VALUES (7499, 17, 'NENEO RUCA', '8416', 0);
INSERT INTO ubicacion_localidades VALUES (7500, 17, 'NILUAN', '8422', 0);
INSERT INTO ubicacion_localidades VALUES (7501, 17, 'NUEVA CAROLINA', '8503', 0);
INSERT INTO ubicacion_localidades VALUES (7502, 17, 'NUEVO LEON', '8514', 0);
INSERT INTO ubicacion_localidades VALUES (7503, 17, 'OJO DE AGUA EMBARCADERO FCGB', '8418', 0);
INSERT INTO ubicacion_localidades VALUES (7504, 17, 'PADRE ALEJANDRO STEFANELLI', '8333', 0);
INSERT INTO ubicacion_localidades VALUES (7505, 17, 'PAJALTA', '8536', 0);
INSERT INTO ubicacion_localidades VALUES (7506, 17, 'PALENQUE NIYEU', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7507, 17, 'PANQUEHUAO', '8412', 0);
INSERT INTO ubicacion_localidades VALUES (7508, 17, 'PASO CHACABUCO', '8401', 0);
INSERT INTO ubicacion_localidades VALUES (7509, 17, 'PASO CORDOVA', '8333', 0);
INSERT INTO ubicacion_localidades VALUES (7510, 17, 'PASO DE LOS MOLLES', '8412', 0);
INSERT INTO ubicacion_localidades VALUES (7511, 17, 'PASO DEL LIMAY', '8403', 0);
INSERT INTO ubicacion_localidades VALUES (7512, 17, 'PASO FLORES', '8403', 0);
INSERT INTO ubicacion_localidades VALUES (7513, 17, 'PASO LEZCANO', '8361', 0);
INSERT INTO ubicacion_localidades VALUES (7514, 17, 'PASO MIRANDA', '8403', 0);
INSERT INTO ubicacion_localidades VALUES (7515, 17, 'PASO PIEDRA', '8360', 0);
INSERT INTO ubicacion_localidades VALUES (7516, 17, 'PEÃAS BLANCAS', '8307', 0);
INSERT INTO ubicacion_localidades VALUES (7517, 17, 'PERCY H SCOTT', '8520', 0);
INSERT INTO ubicacion_localidades VALUES (7518, 17, 'PERITO MORENO', '8416', 0);
INSERT INTO ubicacion_localidades VALUES (7519, 17, 'PERITO MORENO ESTACION FCGR', '8400', 0);
INSERT INTO ubicacion_localidades VALUES (7520, 17, 'PICHI LEUFU', '8412', 0);
INSERT INTO ubicacion_localidades VALUES (7521, 17, 'PICHI LEUFU ABAJO', '8412', 0);
INSERT INTO ubicacion_localidades VALUES (7522, 17, 'PICHI LEUFU ARRIBA', '8412', 0);
INSERT INTO ubicacion_localidades VALUES (7523, 17, 'PICHI MAHUIDA', '8138', 0);
INSERT INTO ubicacion_localidades VALUES (7524, 17, 'PILAHUE', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7525, 17, 'PILCANIYEU', '8412', 0);
INSERT INTO ubicacion_localidades VALUES (7526, 17, 'PILCANIYEU VIEJO', '8412', 0);
INSERT INTO ubicacion_localidades VALUES (7527, 17, 'PILQUI NIYEU', '8416', 0);
INSERT INTO ubicacion_localidades VALUES (7528, 17, 'PLANICIE DE JAGUELITO', '8333', 0);
INSERT INTO ubicacion_localidades VALUES (7529, 17, 'PLAYA BONITA', '8501', 0);
INSERT INTO ubicacion_localidades VALUES (7530, 17, 'POMONA', '8363', 0);
INSERT INTO ubicacion_localidades VALUES (7531, 17, 'PORTEZUELO', '8415', 0);
INSERT INTO ubicacion_localidades VALUES (7532, 17, 'POZO MORO', '8520', 0);
INSERT INTO ubicacion_localidades VALUES (7533, 17, 'POZO SALADO', '8514', 0);
INSERT INTO ubicacion_localidades VALUES (7534, 17, 'PRAHUANIYEU', '8424', 0);
INSERT INTO ubicacion_localidades VALUES (7535, 17, 'PRIMERA ANGOSTURA', '8505', 0);
INSERT INTO ubicacion_localidades VALUES (7536, 17, 'PUERTO BLEST', '8411', 0);
INSERT INTO ubicacion_localidades VALUES (7537, 17, 'PUERTO OJO DE AGUA', '8401', 0);
INSERT INTO ubicacion_localidades VALUES (7538, 17, 'PUERTO SAN ANTONIO ESTE', '8521', 0);
INSERT INTO ubicacion_localidades VALUES (7539, 17, 'PUERTO TIGRE ISLA VICTORIA', '8400', 0);
INSERT INTO ubicacion_localidades VALUES (7540, 17, 'PUESTO FARIA', '8360', 0);
INSERT INTO ubicacion_localidades VALUES (7541, 17, 'PUESTO GAVIÃA', '8503', 0);
INSERT INTO ubicacion_localidades VALUES (7542, 17, 'PUNTA DE AGUA', '8536', 0);
INSERT INTO ubicacion_localidades VALUES (7543, 17, 'QUEMPU NIYEU', '8333', 0);
INSERT INTO ubicacion_localidades VALUES (7544, 17, 'QUETREQUILE', '8418', 0);
INSERT INTO ubicacion_localidades VALUES (7545, 17, 'QUINTA PANAL', '8416', 0);
INSERT INTO ubicacion_localidades VALUES (7546, 17, 'RAYHUAO', '8412', 0);
INSERT INTO ubicacion_localidades VALUES (7547, 17, 'REPOLLOS', '8415', 0);
INSERT INTO ubicacion_localidades VALUES (7548, 17, 'RINCON DE GASTRE', '8503', 0);
INSERT INTO ubicacion_localidades VALUES (7549, 17, 'RINCONADA', '8360', 0);
INSERT INTO ubicacion_localidades VALUES (7550, 17, 'RIO CHICO', '8415', 0);
INSERT INTO ubicacion_localidades VALUES (7551, 17, 'RIO COLORADO', '8138', 0);
INSERT INTO ubicacion_localidades VALUES (7552, 17, 'RIO SALADO', '8134', 0);
INSERT INTO ubicacion_localidades VALUES (7553, 17, 'RIO VILLEGAS', '8401', 0);
INSERT INTO ubicacion_localidades VALUES (7554, 17, 'RUCU LUAN', '8422', 0);
INSERT INTO ubicacion_localidades VALUES (7555, 17, 'SACO VIEJO', '8514', 0);
INSERT INTO ubicacion_localidades VALUES (7556, 17, 'SALITRAL NEGRO', '8363', 0);
INSERT INTO ubicacion_localidades VALUES (7557, 17, 'SAN ANTONIO OESTE', '8520', 0);
INSERT INTO ubicacion_localidades VALUES (7558, 17, 'SAN JAVIER', '8501', 0);
INSERT INTO ubicacion_localidades VALUES (7559, 17, 'SAN JORGE', '8324', 0);
INSERT INTO ubicacion_localidades VALUES (7560, 17, 'SAN JUAN', '8503', 0);
INSERT INTO ubicacion_localidades VALUES (7561, 17, 'SAN LEON', '8138', 0);
INSERT INTO ubicacion_localidades VALUES (7562, 17, 'SAN LORENZO', '8503', 0);
INSERT INTO ubicacion_localidades VALUES (7563, 17, 'SAN PEDRO', '8412', 0);
INSERT INTO ubicacion_localidades VALUES (7564, 17, 'SAN RAMON', '8416', 0);
INSERT INTO ubicacion_localidades VALUES (7565, 17, 'SAN SIMON', '8503', 0);
INSERT INTO ubicacion_localidades VALUES (7566, 17, 'SANTA ELENA', '8417', 0);
INSERT INTO ubicacion_localidades VALUES (7567, 17, 'SANTA GENOVEVA', '8363', 0);
INSERT INTO ubicacion_localidades VALUES (7568, 17, 'SANTA GREGORIA', '8364', 0);
INSERT INTO ubicacion_localidades VALUES (7569, 17, 'SANTA NICOLASA', '8364', 0);
INSERT INTO ubicacion_localidades VALUES (7570, 17, 'SARGENTO VIDAL', '8305', 0);
INSERT INTO ubicacion_localidades VALUES (7571, 17, 'SAUCE BLANCO', '8505', 0);
INSERT INTO ubicacion_localidades VALUES (7572, 17, 'SAUCE BLANCO', '8360', 0);
INSERT INTO ubicacion_localidades VALUES (7573, 17, 'SEGUNDA ANGOSTURA', '8501', 0);
INSERT INTO ubicacion_localidades VALUES (7574, 17, 'SIERRA COLORADA', '8534', 0);
INSERT INTO ubicacion_localidades VALUES (7575, 17, 'SIERRA DE LA VENTANA', '8521', 0);
INSERT INTO ubicacion_localidades VALUES (7576, 17, 'SIERRA GRANDE', '8532', 0);
INSERT INTO ubicacion_localidades VALUES (7577, 17, 'SIERRA PAILEMAN', '8521', 0);
INSERT INTO ubicacion_localidades VALUES (7578, 17, 'TENIENTE MAZA ESTACION FCGR', '8534', 0);
INSERT INTO ubicacion_localidades VALUES (7579, 17, 'TERCERA ZONA', '8336', 0);
INSERT INTO ubicacion_localidades VALUES (7580, 17, 'TRAVESIA CASTRO', '8503', 0);
INSERT INTO ubicacion_localidades VALUES (7581, 17, 'TRENETA', '8534', 0);
INSERT INTO ubicacion_localidades VALUES (7582, 17, 'TRES OJOS DE AGUAS', '8416', 0);
INSERT INTO ubicacion_localidades VALUES (7583, 17, 'TRICACO', '8332', 0);
INSERT INTO ubicacion_localidades VALUES (7584, 17, 'TROMENIYEU', '8422', 0);
INSERT INTO ubicacion_localidades VALUES (7585, 17, 'TTE GRAL EUSTOQUIO FRIAS', '8501', 0);
INSERT INTO ubicacion_localidades VALUES (7586, 17, 'TUNQUELEN', '8409', 0);
INSERT INTO ubicacion_localidades VALUES (7587, 17, 'VACA LAUQUEN', '8422', 0);
INSERT INTO ubicacion_localidades VALUES (7588, 17, 'VALCHETA', '8536', 0);
INSERT INTO ubicacion_localidades VALUES (7589, 17, 'VALLE AZUL', '8336', 0);
INSERT INTO ubicacion_localidades VALUES (7590, 17, 'VALLE DE LOS ALAMOS', '8307', 0);
INSERT INTO ubicacion_localidades VALUES (7591, 17, 'VIEDMA', '8500', 0);
INSERT INTO ubicacion_localidades VALUES (7592, 17, 'VILLA ALBERDI', '8336', 0);
INSERT INTO ubicacion_localidades VALUES (7593, 17, 'VILLA LLANQUIN', '8401', 0);
INSERT INTO ubicacion_localidades VALUES (7594, 17, 'VILLA MANZANO', '8308', 0);
INSERT INTO ubicacion_localidades VALUES (7595, 17, 'VILLA MASCARDI', '8401', 0);
INSERT INTO ubicacion_localidades VALUES (7596, 17, 'VILLA REGINA', '8336', 0);
INSERT INTO ubicacion_localidades VALUES (7597, 17, 'VILLA TURISMO', '8430', 0);
INSERT INTO ubicacion_localidades VALUES (7598, 17, 'YUQUINCHE', '8418', 0);
INSERT INTO ubicacion_localidades VALUES (7599, 12, 'ÃORQUINCO SUD', '8415', 0);
INSERT INTO ubicacion_localidades VALUES (7600, 12, 'AGUADA DE LAS TEJAS', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7601, 12, 'AGUADA DEL PITO', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7602, 12, 'ALDEA APELEG', '9033', 0);
INSERT INTO ubicacion_localidades VALUES (7603, 12, 'ALDEA BELEIRO', '9037', 0);
INSERT INTO ubicacion_localidades VALUES (7604, 12, 'ALDEA ESCOLAR', '9203', 0);
INSERT INTO ubicacion_localidades VALUES (7605, 12, 'ALTO DE LAS PLUMAS', '9101', 0);
INSERT INTO ubicacion_localidades VALUES (7606, 12, 'ALTO RIO MAYO', '9037', 0);
INSERT INTO ubicacion_localidades VALUES (7607, 12, 'ALTO RIO PICO', '9225', 0);
INSERT INTO ubicacion_localidades VALUES (7608, 12, 'ALTO RIO SENGUER', '9033', 0);
INSERT INTO ubicacion_localidades VALUES (7609, 12, 'ANGOSTURA', '9105', 0);
INSERT INTO ubicacion_localidades VALUES (7610, 12, 'ANGOSTURA SEGUNDA', '9105', 0);
INSERT INTO ubicacion_localidades VALUES (7611, 12, 'ARENOSO', '9225', 0);
INSERT INTO ubicacion_localidades VALUES (7612, 12, 'ARROYO CHALIA', '9035', 0);
INSERT INTO ubicacion_localidades VALUES (7613, 12, 'ARROYO GATO', '9033', 0);
INSERT INTO ubicacion_localidades VALUES (7614, 12, 'ARROYO GUILAIA', '9207', 0);
INSERT INTO ubicacion_localidades VALUES (7615, 12, 'ARROYO PERCY', '9203', 0);
INSERT INTO ubicacion_localidades VALUES (7616, 12, 'ARROYO PESCADO', '9200', 0);
INSERT INTO ubicacion_localidades VALUES (7617, 12, 'ARROYO QUILLA', '9020', 0);
INSERT INTO ubicacion_localidades VALUES (7618, 12, 'ARROYO VERDE', '8532', 0);
INSERT INTO ubicacion_localidades VALUES (7619, 12, 'BAHIA BUSTAMANTE', '9111', 0);
INSERT INTO ubicacion_localidades VALUES (7620, 12, 'BAHIA CRACHER', '9120', 0);
INSERT INTO ubicacion_localidades VALUES (7621, 12, 'BAHIA SOLANO', '9003', 0);
INSERT INTO ubicacion_localidades VALUES (7622, 12, 'BAJADA DEL DIABLO', '9101', 0);
INSERT INTO ubicacion_localidades VALUES (7623, 12, 'BAJADA MORENO', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7624, 12, 'BAJO BARTOLO', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7625, 12, 'BAJO DE LOS HUESOS', '9103', 0);
INSERT INTO ubicacion_localidades VALUES (7626, 12, 'BAJO DEL GUALICHO', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7627, 12, 'BAJO LA CANCHA', '9031', 0);
INSERT INTO ubicacion_localidades VALUES (7628, 12, 'BAJO LAS DAMAJUANAS', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7629, 12, 'BARRIO ASTRA', '9003', 0);
INSERT INTO ubicacion_localidades VALUES (7630, 12, 'BASE AERONAVAL ALMIRANTE ZAR', '9101', 0);
INSERT INTO ubicacion_localidades VALUES (7631, 12, 'BETESTA', '9105', 0);
INSERT INTO ubicacion_localidades VALUES (7632, 12, 'BOCA DE LA ZANJA', '9107', 0);
INSERT INTO ubicacion_localidades VALUES (7633, 12, 'BOCA ZANJA SUD', '9107', 0);
INSERT INTO ubicacion_localidades VALUES (7634, 12, 'BRYN BROWN', '9105', 0);
INSERT INTO ubicacion_localidades VALUES (7635, 12, 'BRYN GWYN', '9105', 0);
INSERT INTO ubicacion_localidades VALUES (7636, 12, 'BUEN PASTO', '9023', 0);
INSERT INTO ubicacion_localidades VALUES (7637, 12, 'BUENOS AIRES CHICO', '9210', 0);
INSERT INTO ubicacion_localidades VALUES (7638, 12, 'CAÃADA BAGUAL', '9207', 0);
INSERT INTO ubicacion_localidades VALUES (7639, 12, 'CAÃADON BAGUAL', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7640, 12, 'CAÃADON BLANCO', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7641, 12, 'CAÃADON CALIENTE', '9217', 0);
INSERT INTO ubicacion_localidades VALUES (7642, 12, 'CAÃADON CARRIL', '9020', 0);
INSERT INTO ubicacion_localidades VALUES (7643, 12, 'CAÃADON CHACAY', '9223', 0);
INSERT INTO ubicacion_localidades VALUES (7644, 12, 'CAÃADON CHILENO', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7645, 12, 'CAÃADON FERRAIS', '9001', 0);
INSERT INTO ubicacion_localidades VALUES (7646, 12, 'CAÃADON GRANDE', '9217', 0);
INSERT INTO ubicacion_localidades VALUES (7647, 12, 'CAÃADON LA MADERA', '9220', 0);
INSERT INTO ubicacion_localidades VALUES (7648, 12, 'CAÃADON LAGARTO', '9009', 0);
INSERT INTO ubicacion_localidades VALUES (7649, 12, 'CAÃADON LOPEZ', '9009', 0);
INSERT INTO ubicacion_localidades VALUES (7650, 12, 'CAÃADON PEDRO EX VALLE HERMOSO', '9009', 0);
INSERT INTO ubicacion_localidades VALUES (7651, 12, 'CAÃADON TACHO', '9020', 0);
INSERT INTO ubicacion_localidades VALUES (7652, 12, 'CABAÃA DEL VALLE', '9105', 0);
INSERT INTO ubicacion_localidades VALUES (7653, 12, 'CABEZA DE BUEY', '9101', 0);
INSERT INTO ubicacion_localidades VALUES (7654, 12, 'CABO RASO', '9111', 0);
INSERT INTO ubicacion_localidades VALUES (7655, 12, 'CACHEL', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7656, 12, 'CAJON DE GINEBRA CHICO', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7657, 12, 'CAJON DE GINEBRA GRANDE', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7658, 12, 'CALETA CORDOVA', '9003', 0);
INSERT INTO ubicacion_localidades VALUES (7659, 12, 'CALETA VALDEZ', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7660, 12, 'CAMARONES', '9111', 0);
INSERT INTO ubicacion_localidades VALUES (7661, 12, 'CAMPAMENTO VILLEGAS', '9107', 0);
INSERT INTO ubicacion_localidades VALUES (7662, 12, 'CARHUE NIYEO', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7663, 12, 'CARRENLEUFU', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7664, 12, 'CASA BLANCA', '9103', 0);
INSERT INTO ubicacion_localidades VALUES (7665, 12, 'CASA BLANCA', '9220', 0);
INSERT INTO ubicacion_localidades VALUES (7666, 12, 'CATAICO', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7667, 12, 'CERRO CENTINELA', '9203', 0);
INSERT INTO ubicacion_localidades VALUES (7668, 12, 'CERRO CONDOR', '9207', 0);
INSERT INTO ubicacion_localidades VALUES (7669, 12, 'CERRO FOFOCAHUEL', '9213', 0);
INSERT INTO ubicacion_localidades VALUES (7670, 12, 'CERRO LONCO TRAPIAL', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7671, 12, 'CERRO MALLACO', '9200', 0);
INSERT INTO ubicacion_localidades VALUES (7672, 12, 'CERRO NEGRO', '9220', 0);
INSERT INTO ubicacion_localidades VALUES (7673, 12, 'CERRO PICHALAO', '9120', 0);
INSERT INTO ubicacion_localidades VALUES (7674, 12, 'CERRO RADAL', '9431', 0);
INSERT INTO ubicacion_localidades VALUES (7675, 12, 'CERRO SANTA ANA', '9100', 0);
INSERT INTO ubicacion_localidades VALUES (7676, 12, 'CHACAY ESTE', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7677, 12, 'CHACAY OESTE', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7678, 12, 'CHACRA DE AUSTIN', '9200', 0);
INSERT INTO ubicacion_localidades VALUES (7679, 12, 'CHARQUE CHICO', '9103', 0);
INSERT INTO ubicacion_localidades VALUES (7680, 12, 'CHASICO', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7681, 12, 'CHOLILA', '9217', 0);
INSERT INTO ubicacion_localidades VALUES (7682, 12, 'COLANCONHUE', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7683, 12, 'COLELACHE', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7684, 12, 'COLHUE HUAPI', '9021', 0);
INSERT INTO ubicacion_localidades VALUES (7685, 12, 'COLONIA 16 DE OCTUBRE', '9200', 0);
INSERT INTO ubicacion_localidades VALUES (7686, 12, 'COLONIA CUSHAMEN', '9213', 0);
INSERT INTO ubicacion_localidades VALUES (7687, 12, 'COLONIA EPULIEF', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7688, 12, 'COLONIA GERMANIA', '9020', 0);
INSERT INTO ubicacion_localidades VALUES (7689, 12, 'COMODORO RIVADAVIA', '9000', 0);
INSERT INTO ubicacion_localidades VALUES (7690, 12, 'CORCOVADO', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7691, 12, 'CORRALITOS', '9220', 0);
INSERT INTO ubicacion_localidades VALUES (7692, 12, 'COSTA CHUBUT', '9210', 0);
INSERT INTO ubicacion_localidades VALUES (7693, 12, 'COSTA DEL LEPA', '9210', 0);
INSERT INTO ubicacion_localidades VALUES (7694, 12, 'COSTA RIO CHICO', '9020', 0);
INSERT INTO ubicacion_localidades VALUES (7695, 12, 'CUSHAMEN', '9211', 0);
INSERT INTO ubicacion_localidades VALUES (7696, 12, 'DIQUE FLORENTINO AMEGHINO', '9101', 0);
INSERT INTO ubicacion_localidades VALUES (7697, 12, 'DOCTOR RICARDO ROJAS', '9035', 0);
INSERT INTO ubicacion_localidades VALUES (7698, 12, 'DOLAVON', '9107', 0);
INSERT INTO ubicacion_localidades VALUES (7699, 12, 'DOS POZOS', '9100', 0);
INSERT INTO ubicacion_localidades VALUES (7700, 12, 'EBENECER', '9107', 0);
INSERT INTO ubicacion_localidades VALUES (7701, 12, 'EL ALAMO', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7702, 12, 'EL ARGENTINO', '9105', 0);
INSERT INTO ubicacion_localidades VALUES (7703, 12, 'EL BOQUETE', '9210', 0);
INSERT INTO ubicacion_localidades VALUES (7704, 12, 'EL CAJON', '9217', 0);
INSERT INTO ubicacion_localidades VALUES (7705, 12, 'EL CALAFATE', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7706, 12, 'EL CANQUEL', '9207', 0);
INSERT INTO ubicacion_localidades VALUES (7707, 12, 'EL CHACAY  DPTO GASTRE', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7708, 12, 'EL CHALET', '9207', 0);
INSERT INTO ubicacion_localidades VALUES (7709, 12, 'EL CHERQUE', '9223', 0);
INSERT INTO ubicacion_localidades VALUES (7710, 12, 'EL COIHUE', '9211', 0);
INSERT INTO ubicacion_localidades VALUES (7711, 12, 'EL COYTE', '9033', 0);
INSERT INTO ubicacion_localidades VALUES (7712, 12, 'EL CRONOMETRO', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7713, 12, 'EL CUCHE', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7714, 12, 'EL DESEMPEÃO', '9120', 0);
INSERT INTO ubicacion_localidades VALUES (7715, 12, 'EL ESCORIAL', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7716, 12, 'EL HOYO', '9211', 0);
INSERT INTO ubicacion_localidades VALUES (7717, 12, 'EL JAGUEL', '9111', 0);
INSERT INTO ubicacion_localidades VALUES (7718, 12, 'EL KAQUEL', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7719, 12, 'EL MAITEN', '9210', 0);
INSERT INTO ubicacion_localidades VALUES (7720, 12, 'EL MIRADOR', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7721, 12, 'EL MIRASOL', '9101', 0);
INSERT INTO ubicacion_localidades VALUES (7722, 12, 'EL MOLLE', '9220', 0);
INSERT INTO ubicacion_localidades VALUES (7723, 12, 'EL PAJARITO', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7724, 12, 'EL PASTIZAL', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7725, 12, 'EL PIQUILLIN', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7726, 12, 'EL PORTEZUELO', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7727, 12, 'EL PORVENIR', '9033', 0);
INSERT INTO ubicacion_localidades VALUES (7728, 12, 'EL POYO', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7729, 12, 'EL QUILIMUAY', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7730, 12, 'EL RUANO', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7731, 12, 'EL SALITRAL', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7732, 12, 'EL SHAMAN', '9223', 0);
INSERT INTO ubicacion_localidades VALUES (7733, 12, 'EL SOMBRERO', '9207', 0);
INSERT INTO ubicacion_localidades VALUES (7734, 12, 'EL TRIANA', '9037', 0);
INSERT INTO ubicacion_localidades VALUES (7735, 12, 'EL TROPEZON', '9220', 0);
INSERT INTO ubicacion_localidades VALUES (7736, 12, 'EMPALME A ASTRA', '9003', 0);
INSERT INTO ubicacion_localidades VALUES (7737, 12, 'EMPALME PUERTO LOBOS', '8532', 0);
INSERT INTO ubicacion_localidades VALUES (7738, 12, 'ENRIQUE HERMITTE', '9020', 0);
INSERT INTO ubicacion_localidades VALUES (7739, 12, 'EPUYEN', '9211', 0);
INSERT INTO ubicacion_localidades VALUES (7740, 12, 'ESQUEL', '9200', 0);
INSERT INTO ubicacion_localidades VALUES (7741, 12, 'ESTANCIA EL MORO', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7742, 12, 'ESTANCIA LA MIMOSA', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7743, 12, 'ESTANCIA PAMPA CHICA', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7744, 12, 'FACUNDO', '9031', 0);
INSERT INTO ubicacion_localidades VALUES (7745, 12, 'FITHEN VERIN', '9210', 0);
INSERT INTO ubicacion_localidades VALUES (7746, 12, 'FITIRHUIN', '9210', 0);
INSERT INTO ubicacion_localidades VALUES (7747, 12, 'FLORENTINO AMEGHINO', '9100', 0);
INSERT INTO ubicacion_localidades VALUES (7748, 12, 'FOTOCAHUEL', '9213', 0);
INSERT INTO ubicacion_localidades VALUES (7749, 12, 'FRONTERA DE RIO PICO', '9225', 0);
INSERT INTO ubicacion_localidades VALUES (7750, 12, 'FUTALEUFU', '9203', 0);
INSERT INTO ubicacion_localidades VALUES (7751, 12, 'GAIMAN', '9105', 0);
INSERT INTO ubicacion_localidades VALUES (7752, 12, 'GAN GAN', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7753, 12, 'GARAYALDE', '9111', 0);
INSERT INTO ubicacion_localidades VALUES (7754, 12, 'GASTRE', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7755, 12, 'GLASFRYN', '9105', 0);
INSERT INTO ubicacion_localidades VALUES (7756, 12, 'GOBERNADOR COSTA', '9223', 0);
INSERT INTO ubicacion_localidades VALUES (7757, 12, 'GUALJAINA', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7758, 12, 'GUALJAINA', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7759, 12, 'HITO 43', '9225', 0);
INSERT INTO ubicacion_localidades VALUES (7760, 12, 'HITO 45', '9039', 0);
INSERT INTO ubicacion_localidades VALUES (7761, 12, 'HITO 50', '9039', 0);
INSERT INTO ubicacion_localidades VALUES (7762, 12, 'HOLDICH', '9009', 0);
INSERT INTO ubicacion_localidades VALUES (7763, 12, 'ING BRUNO J THOMAE', '9210', 0);
INSERT INTO ubicacion_localidades VALUES (7764, 12, 'JOSE DE SAN MARTIN', '9220', 0);
INSERT INTO ubicacion_localidades VALUES (7765, 12, 'KILOMETRO 11', '9000', 0);
INSERT INTO ubicacion_localidades VALUES (7766, 12, 'KILOMETRO 191', '9020', 0);
INSERT INTO ubicacion_localidades VALUES (7767, 12, 'LA BOMBILLA', '9207', 0);
INSERT INTO ubicacion_localidades VALUES (7768, 12, 'LA CANCHA', '9200', 0);
INSERT INTO ubicacion_localidades VALUES (7769, 12, 'LA CANCHA', '9039', 0);
INSERT INTO ubicacion_localidades VALUES (7770, 12, 'LA CASTELLANA', '9111', 0);
INSERT INTO ubicacion_localidades VALUES (7771, 12, 'LA CASTELLANA', '9111', 0);
INSERT INTO ubicacion_localidades VALUES (7772, 12, 'LA CORONA', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7773, 12, 'LA LANCHA', '9200', 0);
INSERT INTO ubicacion_localidades VALUES (7774, 12, 'LA LAURITA', '9033', 0);
INSERT INTO ubicacion_localidades VALUES (7775, 12, 'LA NICOLASA', '9039', 0);
INSERT INTO ubicacion_localidades VALUES (7776, 12, 'LA PEPITA', '9033', 0);
INSERT INTO ubicacion_localidades VALUES (7777, 12, 'LA PRIMAVERA', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7778, 12, 'LA ROSILLA', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7779, 12, 'LA SALAMANCA', '9007', 0);
INSERT INTO ubicacion_localidades VALUES (7780, 12, 'LA SIBERIA', '9039', 0);
INSERT INTO ubicacion_localidades VALUES (7781, 12, 'LAGO BLANCO', '9039', 0);
INSERT INTO ubicacion_localidades VALUES (7782, 12, 'LAGO CARLOS PELLEGRINI', '9217', 0);
INSERT INTO ubicacion_localidades VALUES (7783, 12, 'LAGO FONTANA', '9033', 0);
INSERT INTO ubicacion_localidades VALUES (7784, 12, 'LAGO LEZAMA', '9217', 0);
INSERT INTO ubicacion_localidades VALUES (7785, 12, 'LAGO MUSTERS', '9023', 0);
INSERT INTO ubicacion_localidades VALUES (7786, 12, 'LAGO PAZ', '9225', 0);
INSERT INTO ubicacion_localidades VALUES (7787, 12, 'LAGO PUELO', '9211', 0);
INSERT INTO ubicacion_localidades VALUES (7788, 12, 'LAGO RIVADAVIA', '9217', 0);
INSERT INTO ubicacion_localidades VALUES (7789, 12, 'LAGO ROSARIO', '9203', 0);
INSERT INTO ubicacion_localidades VALUES (7790, 12, 'LAGO VERDE', '9225', 0);
INSERT INTO ubicacion_localidades VALUES (7791, 12, 'LAGO VINTTER', '9225', 0);
INSERT INTO ubicacion_localidades VALUES (7792, 12, 'LAGUNA BLANCA', '9220', 0);
INSERT INTO ubicacion_localidades VALUES (7793, 12, 'LAGUNA DE VACAS', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7794, 12, 'LAGUNA DE VACAS', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7795, 12, 'LAGUNA DEL MATE', '9020', 0);
INSERT INTO ubicacion_localidades VALUES (7796, 12, 'LAGUNA FRIA', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7797, 12, 'LAGUNA GRANDE', '9107', 0);
INSERT INTO ubicacion_localidades VALUES (7798, 12, 'LAGUNA PALACIO', '9020', 0);
INSERT INTO ubicacion_localidades VALUES (7799, 12, 'LAGUNA RINCON DEL MORO', '9207', 0);
INSERT INTO ubicacion_localidades VALUES (7800, 12, 'LAGUNA TERRAPLEN', '9200', 0);
INSERT INTO ubicacion_localidades VALUES (7801, 12, 'LAGUNA VERDE', '9220', 0);
INSERT INTO ubicacion_localidades VALUES (7802, 12, 'LAGUNITA SALADA', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7803, 12, 'LANGUIÃEO', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7804, 12, 'LARRALDE', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7805, 12, 'LAS CHAPAS', '9107', 0);
INSERT INTO ubicacion_localidades VALUES (7806, 12, 'LAS CORTADERAS', '9207', 0);
INSERT INTO ubicacion_localidades VALUES (7807, 12, 'LAS GOLONDRINAS', '8431', 0);
INSERT INTO ubicacion_localidades VALUES (7808, 12, 'LAS HORQUETAS', '9207', 0);
INSERT INTO ubicacion_localidades VALUES (7809, 12, 'LAS MULAS', '9225', 0);
INSERT INTO ubicacion_localidades VALUES (7810, 12, 'LAS PAMPAS', '9225', 0);
INSERT INTO ubicacion_localidades VALUES (7811, 12, 'LAS PLUMAS', '9101', 0);
INSERT INTO ubicacion_localidades VALUES (7812, 12, 'LAS PULGAS', '9020', 0);
INSERT INTO ubicacion_localidades VALUES (7813, 12, 'LAS SALINAS', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7814, 12, 'LEGUA 24', '9203', 0);
INSERT INTO ubicacion_localidades VALUES (7815, 12, 'LELEQUE', '9217', 0);
INSERT INTO ubicacion_localidades VALUES (7816, 12, 'LENZANILLEO', '9220', 0);
INSERT INTO ubicacion_localidades VALUES (7817, 12, 'LOMA REDONDA', '9105', 0);
INSERT INTO ubicacion_localidades VALUES (7818, 12, 'LORETO', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7819, 12, 'LOS ALTARES', '9207', 0);
INSERT INTO ubicacion_localidades VALUES (7820, 12, 'LOS CIPRECES', '9203', 0);
INSERT INTO ubicacion_localidades VALUES (7821, 12, 'LOS CORRALITOS', '9220', 0);
INSERT INTO ubicacion_localidades VALUES (7822, 12, 'LOS MANANTIALES', '9207', 0);
INSERT INTO ubicacion_localidades VALUES (7823, 12, 'LOS TAMARISCOS', '9031', 0);
INSERT INTO ubicacion_localidades VALUES (7824, 12, 'MAESTEG', '9105', 0);
INSERT INTO ubicacion_localidades VALUES (7825, 12, 'MALASPINA', '9111', 0);
INSERT INTO ubicacion_localidades VALUES (7826, 12, 'MALLIN BLANCO', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7827, 12, 'MALLIN GRANDE', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7828, 12, 'MALLIN GRANDE CORCOBADO', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7829, 12, 'MANANTIAL GRANDE', '9020', 0);
INSERT INTO ubicacion_localidades VALUES (7830, 12, 'MATA GRANDE', '9220', 0);
INSERT INTO ubicacion_localidades VALUES (7831, 12, 'MATUCANA', '9200', 0);
INSERT INTO ubicacion_localidades VALUES (7832, 12, 'MAYOCO', '9200', 0);
INSERT INTO ubicacion_localidades VALUES (7833, 12, 'MEDANOS', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7834, 12, 'NAHUEL PAN ESTACION FCGR', '9200', 0);
INSERT INTO ubicacion_localidades VALUES (7835, 12, 'NIRIGUAO', '9223', 0);
INSERT INTO ubicacion_localidades VALUES (7836, 12, 'NIRIGUCE PAMPA', '9223', 0);
INSERT INTO ubicacion_localidades VALUES (7837, 12, 'NUEVA LUBECKA', '9227', 0);
INSERT INTO ubicacion_localidades VALUES (7838, 12, 'PAINALUF', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7839, 12, 'PAMPA DE AGNIA', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7840, 12, 'PAMPA DEL CASTILLO', '9000', 0);
INSERT INTO ubicacion_localidades VALUES (7841, 12, 'PAMPA PELADA', '9007', 0);
INSERT INTO ubicacion_localidades VALUES (7842, 12, 'PAMPA SALAMANCA', '9000', 0);
INSERT INTO ubicacion_localidades VALUES (7843, 12, 'PAMPA TEPUEL', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7844, 12, 'PARQUE NACIONAL LOS ALERCES', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7845, 12, 'PASO DE INDIOS', '9207', 0);
INSERT INTO ubicacion_localidades VALUES (7846, 12, 'PASO DE TORRES', '9020', 0);
INSERT INTO ubicacion_localidades VALUES (7847, 12, 'PASO DEL SAPO', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7848, 12, 'PASO MORENO', '9033', 0);
INSERT INTO ubicacion_localidades VALUES (7849, 12, 'PASTOS BLANCOS', '9033', 0);
INSERT INTO ubicacion_localidades VALUES (7850, 12, 'PICO SALAMANCA', '9000', 0);
INSERT INTO ubicacion_localidades VALUES (7851, 12, 'PIEDRA PARADA', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7852, 12, 'PIEDRA SHOTEL', '9227', 0);
INSERT INTO ubicacion_localidades VALUES (7853, 12, 'PIRRE MAHUIDA', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7854, 12, 'PLANCUNTRE', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7855, 12, 'PLAYA UNION', '9103', 0);
INSERT INTO ubicacion_localidades VALUES (7856, 12, 'POCITOS DE QUICHAURA', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7857, 12, 'PUENTE HENDRE', '9101', 0);
INSERT INTO ubicacion_localidades VALUES (7858, 12, 'PUERTO LOBOS', '8532', 0);
INSERT INTO ubicacion_localidades VALUES (7859, 12, 'PUERTO MADRYN', '9120', 0);
INSERT INTO ubicacion_localidades VALUES (7860, 12, 'PUERTO PIRAMIDES', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7861, 12, 'PUERTO SAN ROMAN', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7862, 12, 'PUNTA BAJOS', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7863, 12, 'PUNTA DELGADA', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7864, 12, 'PUNTA NINFAS', '9103', 0);
INSERT INTO ubicacion_localidades VALUES (7865, 12, 'PUNTA NORTE', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7866, 12, 'PUNTA QUIROGA', '9120', 0);
INSERT INTO ubicacion_localidades VALUES (7867, 12, 'PUTRACHOIQUE', '9223', 0);
INSERT INTO ubicacion_localidades VALUES (7868, 12, 'RADA TILLY', '9001', 0);
INSERT INTO ubicacion_localidades VALUES (7869, 12, 'RANQUIL HUAO', '9213', 0);
INSERT INTO ubicacion_localidades VALUES (7870, 12, 'RAWSON', '9103', 0);
INSERT INTO ubicacion_localidades VALUES (7871, 12, 'RIO CHICO', '9000', 0);
INSERT INTO ubicacion_localidades VALUES (7872, 12, 'RIO CORINTO', '9203', 0);
INSERT INTO ubicacion_localidades VALUES (7873, 12, 'RIO FRIAS', '9033', 0);
INSERT INTO ubicacion_localidades VALUES (7874, 12, 'RIO MAYO', '9030', 0);
INSERT INTO ubicacion_localidades VALUES (7875, 12, 'RIO PICO', '9225', 0);
INSERT INTO ubicacion_localidades VALUES (7876, 12, 'RUTA 3 KILOMETRO 1646', '9111', 0);
INSERT INTO ubicacion_localidades VALUES (7877, 12, 'RUTA 3 KILOMETRO 1711', '9111', 0);
INSERT INTO ubicacion_localidades VALUES (7878, 12, 'SACANANA', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7879, 12, 'SALINAS CHICAS', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7880, 12, 'SALINAS GRANDES', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7881, 12, 'SAN JOSE', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7882, 12, 'SARMIENTO', '9020', 0);
INSERT INTO ubicacion_localidades VALUES (7883, 12, 'SEPRUCAL', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7884, 12, 'SIEMPRE VIVA', '9213', 0);
INSERT INTO ubicacion_localidades VALUES (7885, 12, 'SIERRA CHATA', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7886, 12, 'SIERRA CHICA', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7887, 12, 'SIERRA COLORADA', '9111', 0);
INSERT INTO ubicacion_localidades VALUES (7888, 12, 'SIERRA CORRIENTES', '9020', 0);
INSERT INTO ubicacion_localidades VALUES (7889, 12, 'SIERRA CUADRADA', '9007', 0);
INSERT INTO ubicacion_localidades VALUES (7890, 12, 'SIERRA DE TECKA', '9200', 0);
INSERT INTO ubicacion_localidades VALUES (7891, 12, 'SIERRA NEVADA BUEN PASTO', '9023', 0);
INSERT INTO ubicacion_localidades VALUES (7892, 12, 'SIERRA NEVADA PASO DE INDIOS', '9207', 0);
INSERT INTO ubicacion_localidades VALUES (7893, 12, 'SIERRA OVERA CHICAS Y GRANDES', '9007', 0);
INSERT INTO ubicacion_localidades VALUES (7894, 12, 'SIERRA ROSADA', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7895, 12, 'SIERRA VICTORIA', '9020', 0);
INSERT INTO ubicacion_localidades VALUES (7896, 12, 'SOL DE MAYO', '9103', 0);
INSERT INTO ubicacion_localidades VALUES (7897, 12, 'SUNICA', '9200', 0);
INSERT INTO ubicacion_localidades VALUES (7898, 12, 'TALAGAPA', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7899, 12, 'TAQUETREN', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7900, 12, 'TATUEN', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7901, 12, 'TECKA', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7902, 12, 'TELSEN', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7903, 12, 'TOMA DE LOS CANALES', '9107', 0);
INSERT INTO ubicacion_localidades VALUES (7904, 12, 'TORO HOSCO', '9207', 0);
INSERT INTO ubicacion_localidades VALUES (7905, 12, 'TRELEW', '9100', 0);
INSERT INTO ubicacion_localidades VALUES (7906, 12, 'TREORKI', '9105', 0);
INSERT INTO ubicacion_localidades VALUES (7907, 12, 'TRES PICOS', '9220', 0);
INSERT INTO ubicacion_localidades VALUES (7908, 12, 'TREVELIN', '9203', 0);
INSERT INTO ubicacion_localidades VALUES (7909, 12, 'UZCUDUN', '9100', 0);
INSERT INTO ubicacion_localidades VALUES (7910, 12, 'VALLE DEL RIO CHUBUT', '9121', 0);
INSERT INTO ubicacion_localidades VALUES (7911, 12, 'VALLE DEL TECKA', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7912, 12, 'VALLE FRIO', '9203', 0);
INSERT INTO ubicacion_localidades VALUES (7913, 12, 'VALLE GARIN', '9201', 0);
INSERT INTO ubicacion_localidades VALUES (7914, 12, 'VALLE HERMOSO', '9020', 0);
INSERT INTO ubicacion_localidades VALUES (7915, 12, 'VALLE HONDO', '9221', 0);
INSERT INTO ubicacion_localidades VALUES (7916, 12, 'VALLE HUEMULES', '9039', 0);
INSERT INTO ubicacion_localidades VALUES (7917, 12, 'VALLE LOS MARTIRES', '9105', 0);
INSERT INTO ubicacion_localidades VALUES (7918, 12, 'VEINTIOCHO DE JULIO', '9107', 0);
INSERT INTO ubicacion_localidades VALUES (7919, 12, 'VILLA FUTALAUFQUEN', '9200', 0);
INSERT INTO ubicacion_localidades VALUES (7920, 12, 'VILLA INES', '9105', 0);
INSERT INTO ubicacion_localidades VALUES (7921, 22, 'ASERRADERO ARROYO', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7922, 22, 'BAHIA LAPATAIA', '9410', 0);
INSERT INTO ubicacion_localidades VALUES (7923, 22, 'BASE AEREA TENIENTE MATIENZO', '9411', 0);
INSERT INTO ubicacion_localidades VALUES (7924, 22, 'BASE AEREA VICECOMOD MARAMBIO', '9411', 0);
INSERT INTO ubicacion_localidades VALUES (7925, 22, 'BASE EJERCITO ESPERANZA', '9411', 0);
INSERT INTO ubicacion_localidades VALUES (7926, 22, 'BASE EJERCITO GRAL BELGRANO', '9411', 0);
INSERT INTO ubicacion_localidades VALUES (7927, 22, 'BASE EJERCITO GRAL BELGRANO 3', '9411', 0);
INSERT INTO ubicacion_localidades VALUES (7928, 22, 'BASE EJERCITO GRAL SAN MARTIN', '9411', 0);
INSERT INTO ubicacion_localidades VALUES (7929, 22, 'BASE EJERCITO PRIMAVERA', '9411', 0);
INSERT INTO ubicacion_localidades VALUES (7930, 22, 'BASE EJERCITO SOBRAL', '9411', 0);
INSERT INTO ubicacion_localidades VALUES (7931, 22, 'CABAÃA RUBY', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7932, 22, 'CABO SAN PABLO', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7933, 22, 'CAMPAMENTO CENTRAL Y P F', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7934, 22, 'CAMPAMENTO LOS CHORRILLOS Y P', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7935, 22, 'COMISARIA RADMAN', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7936, 22, 'DESTACAMENTO MELCHIOR', '9411', 0);
INSERT INTO ubicacion_localidades VALUES (7937, 22, 'EL PARAMO', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7938, 22, 'ESTACION AERONAVAL', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7939, 22, 'ESTACION CIENTIFICA ALTE BROWN', '9411', 0);
INSERT INTO ubicacion_localidades VALUES (7940, 22, 'ESTACION OSN', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7941, 22, 'ESTANCIA AURELIA', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7942, 22, 'ESTANCIA BUENOS AIRES', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7943, 22, 'ESTANCIA CARMEN', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7944, 22, 'ESTANCIA CAUCHICO', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7945, 22, 'ESTANCIA COSTANCIA', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7946, 22, 'ESTANCIA CULLEN', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7947, 22, 'ESTANCIA DESPEDIDA', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7948, 22, 'ESTANCIA DOS HEMANAS', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7949, 22, 'ESTANCIA EL ROBLE', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7950, 22, 'ESTANCIA EL RODEO', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7951, 22, 'ESTANCIA EL SALVADOR', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7952, 22, 'ESTANCIA GUAZU CUE', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7953, 22, 'ESTANCIA HARBERTON', '9410', 0);
INSERT INTO ubicacion_localidades VALUES (7954, 22, 'ESTANCIA HERMINITA', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7955, 22, 'ESTANCIA INES', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7956, 22, 'ESTANCIA JOSE MENENDEZ', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7957, 22, 'ESTANCIA LA CRIOLLA', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7958, 22, 'ESTANCIA LA FUEGUINA', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7959, 22, 'ESTANCIA LA INDIANA', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7960, 22, 'ESTANCIA LA PORTEÃA', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7961, 22, 'ESTANCIA LAS HIJAS', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7962, 22, 'ESTANCIA LAS VIOLETAS', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7963, 22, 'ESTANCIA LAURA', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7964, 22, 'ESTANCIA LIBERTAD', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7965, 22, 'ESTANCIA LOS CERROS', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7966, 22, 'ESTANCIA LOS FLAMENCOS', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7967, 22, 'ESTANCIA MARIA BEHETY', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7968, 22, 'ESTANCIA MARIA CRISTINA', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7969, 22, 'ESTANCIA MARIA LUISA', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7970, 22, 'ESTANCIA MARINA', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7971, 22, 'ESTANCIA MIRAMONTE', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7972, 22, 'ESTANCIA PIRINAICA', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7973, 22, 'ESTANCIA POLICARPO', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7974, 22, 'ESTANCIA RIO CLARO', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7975, 22, 'ESTANCIA RIO EWAN', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7976, 22, 'ESTANCIA RIO IRIGOYEN', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7977, 22, 'ESTANCIA RIVADAVIA', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7978, 22, 'ESTANCIA ROLITO', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7979, 22, 'ESTANCIA ROSITA', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7980, 22, 'ESTANCIA RUBY', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7981, 22, 'ESTANCIA SAN JOSE', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7982, 22, 'ESTANCIA SAN JULIO', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7983, 22, 'ESTANCIA SAN JUSTO', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7984, 22, 'ESTANCIA SAN MARTIN', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7985, 22, 'ESTANCIA SAN PABLO', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7986, 22, 'ESTANCIA SANTA ANA', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7987, 22, 'ESTANCIA SARA', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7988, 22, 'ESTANCIA TEPI', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7989, 22, 'ESTANCIA VIAMONTE', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7990, 22, 'FRIGORIFICO CAP', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (7991, 22, 'HOSTERIA KAIKEN', '9410', 0);
INSERT INTO ubicacion_localidades VALUES (7992, 22, 'ISLA DE LOS ESTADOS', '9410', 0);
INSERT INTO ubicacion_localidades VALUES (7993, 22, 'ISLA GRAN MALVINA', '9409', 0);
INSERT INTO ubicacion_localidades VALUES (7994, 22, 'ISLA JOINVILLE', '9411', 0);
INSERT INTO ubicacion_localidades VALUES (7995, 22, 'ISLA SHETLAND DEL SUR', '9411', 0);
INSERT INTO ubicacion_localidades VALUES (7996, 22, 'ISLA SOLEDAD', '9409', 0);
INSERT INTO ubicacion_localidades VALUES (7997, 22, 'ISLAS GEORGIAS', '9411', 0);
INSERT INTO ubicacion_localidades VALUES (7998, 22, 'ISLAS ORCADAS', '9411', 0);
INSERT INTO ubicacion_localidades VALUES (7999, 22, 'LAGO KHAMI', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (8000, 22, 'MISION SALESIANA MÃOR FAGNANO', '9410', 0);
INSERT INTO ubicacion_localidades VALUES (8001, 22, 'PUNTA MARIA', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (8002, 22, 'RIO GRANDE', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (8003, 22, 'SAN SEBASTIAN', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (8004, 22, 'SANTA INES', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (8005, 22, 'SECCION AVILES  ESTANCIA SAN J', '9420', 0);
INSERT INTO ubicacion_localidades VALUES (8006, 22, 'TOLHUIN', '9412', 0);
INSERT INTO ubicacion_localidades VALUES (8007, 22, 'USHUAIA', '9410', 0);
INSERT INTO ubicacion_localidades VALUES (8008, 19, '23 DE AGOSTO', '4503', 0);
INSERT INTO ubicacion_localidades VALUES (8009, 19, 'ABDON CASTRO TOLAY', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8010, 19, 'ABRA DE PEÃAS', '4650', 0);
INSERT INTO ubicacion_localidades VALUES (8011, 19, 'ABRA DE PIVES', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8012, 19, 'ABRA DEL TRIGO', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8013, 19, 'ABRA MAYO', '4622', 0);
INSERT INTO ubicacion_localidades VALUES (8014, 19, 'ABRA PAMPA', '4640', 0);
INSERT INTO ubicacion_localidades VALUES (8015, 19, 'ABRALAITE', '4632', 0);
INSERT INTO ubicacion_localidades VALUES (8016, 19, 'ACHACAMAYOC', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8017, 19, 'AGUA BENDITA', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8018, 19, 'AGUA CALIENTE', '4431', 0);
INSERT INTO ubicacion_localidades VALUES (8019, 19, 'AGUA CALIENTE', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8020, 19, 'AGUA CALIENTE DE LA PUNA', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8021, 19, 'AGUA CHICA', '4640', 0);
INSERT INTO ubicacion_localidades VALUES (8022, 19, 'AGUA CHICA', '4640', 0);
INSERT INTO ubicacion_localidades VALUES (8023, 19, 'AGUA DE CASTILLA', '4640', 0);
INSERT INTO ubicacion_localidades VALUES (8024, 19, 'AGUA DE CASTILLA', '4172', 0);
INSERT INTO ubicacion_localidades VALUES (8025, 19, 'AGUA NEGRA', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8026, 19, 'AGUA PALOMAR', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8027, 19, 'AGUAS BLANCAS', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8028, 19, 'AGUAS CALIENTES', '4431', 0);
INSERT INTO ubicacion_localidades VALUES (8029, 19, 'ALEGRIA', '4506', 0);
INSERT INTO ubicacion_localidades VALUES (8030, 19, 'ALFARCITO', '4624', 0);
INSERT INTO ubicacion_localidades VALUES (8031, 19, 'ALGARROBAL', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8032, 19, 'ALISOS  DE ABAJO', '4603', 0);
INSERT INTO ubicacion_localidades VALUES (8033, 19, 'ALISOS DE ARRIBA', '4603', 0);
INSERT INTO ubicacion_localidades VALUES (8034, 19, 'ALTO CALILEGUA', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8035, 19, 'ALTO COMEDERO', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8036, 19, 'ALTO DE CASA', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8037, 19, 'ALTO DE LOZANO', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8038, 19, 'ALTO DE MOJON', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8039, 19, 'ALTO DEL ANGOSTO', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8040, 19, 'ALTO DEL SALADILLO', '4500', 0);
INSERT INTO ubicacion_localidades VALUES (8041, 19, 'ALTO DEL SALADILLO', '4522', 0);
INSERT INTO ubicacion_localidades VALUES (8042, 19, 'ALTO HUANCAR', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8043, 19, 'ALTO MINERO', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8044, 19, 'ALTO POTRERILLO', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8045, 19, 'ALTO QUEMADO', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8046, 19, 'ALTO QUIRQUINCHO', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8047, 19, 'ALTO VERDE', '4606', 0);
INSERT INTO ubicacion_localidades VALUES (8048, 19, 'AMANCAYOC', '4513', 0);
INSERT INTO ubicacion_localidades VALUES (8049, 19, 'AMARGURAS', '4513', 0);
INSERT INTO ubicacion_localidades VALUES (8050, 19, 'ANGOSTO DEL PERCHEL', '4626', 0);
INSERT INTO ubicacion_localidades VALUES (8051, 19, 'ANIMAS', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8052, 19, 'ANTIGUO', '4631', 0);
INSERT INTO ubicacion_localidades VALUES (8053, 19, 'ANTIGUYOS', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8054, 19, 'ANTUMPA', '4632', 0);
INSERT INTO ubicacion_localidades VALUES (8055, 19, 'APAREJO', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8056, 19, 'APARZO', '4630', 0);
INSERT INTO ubicacion_localidades VALUES (8057, 19, 'ARBOLITO NUEVO', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8058, 19, 'ARENAL BARROSO', '4506', 0);
INSERT INTO ubicacion_localidades VALUES (8059, 19, 'ARRAYANAL', '4503', 0);
INSERT INTO ubicacion_localidades VALUES (8060, 19, 'ARROYO COLORADO', '4500', 0);
INSERT INTO ubicacion_localidades VALUES (8061, 19, 'ARROYO DEL MEDIO', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8062, 19, 'ATALAYA', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8063, 19, 'AZUL PAMPA', '4631', 0);
INSERT INTO ubicacion_localidades VALUES (8064, 19, 'BAJADA ALTA', '4506', 0);
INSERT INTO ubicacion_localidades VALUES (8065, 19, 'BALIAZO', '4631', 0);
INSERT INTO ubicacion_localidades VALUES (8066, 19, 'BALLIAZO', '4630', 0);
INSERT INTO ubicacion_localidades VALUES (8067, 19, 'BARCENA', '4616', 0);
INSERT INTO ubicacion_localidades VALUES (8068, 19, 'BARRANCAS', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8069, 19, 'BARRIO 9 DE JULIO', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8070, 19, 'BARRIO ALBERDI', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8071, 19, 'BARRIO ALTO LA LOMA', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8072, 19, 'BARRIO ALTO LA VIÃA', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8073, 19, 'BARRIO BAJO LA VIÃA', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8074, 19, 'BARRIO CHIJRA', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8075, 19, 'BARRIO CUYAYA', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8076, 19, 'BARRIO LA PROVIDENCIA', '4500', 0);
INSERT INTO ubicacion_localidades VALUES (8077, 19, 'BARRIO LA UNION', '4648', 0);
INSERT INTO ubicacion_localidades VALUES (8078, 19, 'BARRIO LUJAN', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8079, 19, 'BARRIO PARQUE 19 DE ABRIL', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8080, 19, 'BARRIO SANTA RITA', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8081, 19, 'BARRIOS', '4650', 0);
INSERT INTO ubicacion_localidades VALUES (8082, 19, 'BARRO NEGRO', '4522', 0);
INSERT INTO ubicacion_localidades VALUES (8083, 19, 'BATEAS', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8084, 19, 'BELLA VISTA', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8085, 19, 'BELLA VISTA', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8086, 19, 'BELLA VISTA', '4622', 0);
INSERT INTO ubicacion_localidades VALUES (8087, 19, 'BOLETERIA', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8088, 19, 'BOMBA', '4616', 0);
INSERT INTO ubicacion_localidades VALUES (8089, 19, 'BORDO LA ISLA', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8090, 19, 'CAÃAS', '4624', 0);
INSERT INTO ubicacion_localidades VALUES (8091, 19, 'CABRERIA', '4653', 0);
INSERT INTO ubicacion_localidades VALUES (8092, 19, 'CACHI PUNCO', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8093, 19, 'CACHIHUAICO', '4622', 0);
INSERT INTO ubicacion_localidades VALUES (8094, 19, 'CACHO', '4513', 0);
INSERT INTO ubicacion_localidades VALUES (8095, 19, 'CADILLA', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8096, 19, 'CAIMANCITO', '4516', 0);
INSERT INTO ubicacion_localidades VALUES (8097, 19, 'CAJAS', '4651', 0);
INSERT INTO ubicacion_localidades VALUES (8098, 19, 'CALAHOYO', '4655', 0);
INSERT INTO ubicacion_localidades VALUES (8099, 19, 'CALETE', '4630', 0);
INSERT INTO ubicacion_localidades VALUES (8100, 19, 'CALILEGUA', '4514', 0);
INSERT INTO ubicacion_localidades VALUES (8101, 19, 'CAMPO BAJO', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8102, 19, 'CAMPO COLORADO', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8103, 19, 'CAMPO LA TUNA', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8104, 19, 'CAMPO OCULTO', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8105, 19, 'CANCHAHUASI', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8106, 19, 'CANCHUELA', '4655', 0);
INSERT INTO ubicacion_localidades VALUES (8107, 19, 'CANDELARIA', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8108, 19, 'CANGREJILLOS', '4644', 0);
INSERT INTO ubicacion_localidades VALUES (8109, 19, 'CANGREJOS', '4644', 0);
INSERT INTO ubicacion_localidades VALUES (8110, 19, 'CAPACHACRA', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8111, 19, 'CAPLA', '4626', 0);
INSERT INTO ubicacion_localidades VALUES (8112, 19, 'CARACARA', '4644', 0);
INSERT INTO ubicacion_localidades VALUES (8113, 19, 'CARAHUASI', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8114, 19, 'CARAHUNCO', '4612', 0);
INSERT INTO ubicacion_localidades VALUES (8115, 19, 'CARAHUNCO', '4612', 0);
INSERT INTO ubicacion_localidades VALUES (8116, 19, 'CARAYOC', '4651', 0);
INSERT INTO ubicacion_localidades VALUES (8117, 19, 'CARCEL', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8118, 19, 'CASA COLORADA', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8119, 19, 'CASA GRANDE', '4634', 0);
INSERT INTO ubicacion_localidades VALUES (8120, 19, 'CASA NEGRA', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8121, 19, 'CASA VIEJA', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8122, 19, 'CASABINDO', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8123, 19, 'CASAYOCK', '4632', 0);
INSERT INTO ubicacion_localidades VALUES (8124, 19, 'CASILLA', '4632', 0);
INSERT INTO ubicacion_localidades VALUES (8125, 19, 'CASILLAS', '4632', 0);
INSERT INTO ubicacion_localidades VALUES (8126, 19, 'CASIRA', '4653', 0);
INSERT INTO ubicacion_localidades VALUES (8127, 19, 'CASPALA', '4631', 0);
INSERT INTO ubicacion_localidades VALUES (8128, 19, 'CASTI', '4650', 0);
INSERT INTO ubicacion_localidades VALUES (8129, 19, 'CATAMONTAÃA', '4603', 0);
INSERT INTO ubicacion_localidades VALUES (8130, 19, 'CATARI', '4640', 0);
INSERT INTO ubicacion_localidades VALUES (8131, 19, 'CATUA', '4413', 0);
INSERT INTO ubicacion_localidades VALUES (8132, 19, 'CAUCHARI', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8133, 19, 'CEIBAL', '4603', 0);
INSERT INTO ubicacion_localidades VALUES (8134, 19, 'CENTRO FORESTAL', '4612', 0);
INSERT INTO ubicacion_localidades VALUES (8135, 19, 'CENTRO FORESTAL', '4612', 0);
INSERT INTO ubicacion_localidades VALUES (8136, 19, 'CERRILLOS', '4653', 0);
INSERT INTO ubicacion_localidades VALUES (8137, 19, 'CERRITO', '4653', 0);
INSERT INTO ubicacion_localidades VALUES (8138, 19, 'CERRO CHICO', '4640', 0);
INSERT INTO ubicacion_localidades VALUES (8139, 19, 'CERRO NEGRO', '4605', 0);
INSERT INTO ubicacion_localidades VALUES (8140, 19, 'CERRO REDONDO', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8141, 19, 'CERROS ZAPLA', '4612', 0);
INSERT INTO ubicacion_localidades VALUES (8142, 19, 'CERROS ZAPLA', '4612', 0);
INSERT INTO ubicacion_localidades VALUES (8143, 19, 'CEVILAR', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8144, 19, 'CHAÃAR SOLO', '4516', 0);
INSERT INTO ubicacion_localidades VALUES (8145, 19, 'CHAÃARAL', '4516', 0);
INSERT INTO ubicacion_localidades VALUES (8146, 19, 'CHAÃARCITO', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8147, 19, 'CHAÃI', '4616', 0);
INSERT INTO ubicacion_localidades VALUES (8148, 19, 'CHAÃI CHICO', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8149, 19, 'CHALICAN', '4504', 0);
INSERT INTO ubicacion_localidades VALUES (8150, 19, 'CHAMICAL', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8151, 19, 'CHAUPI RODERO', '4640', 0);
INSERT INTO ubicacion_localidades VALUES (8152, 19, 'CHAUPI RODERO', '4632', 0);
INSERT INTO ubicacion_localidades VALUES (8153, 19, 'CHILCAR', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8154, 19, 'CHILCAR', '4631', 0);
INSERT INTO ubicacion_localidades VALUES (8155, 19, 'CHILCAYOC', '4616', 0);
INSERT INTO ubicacion_localidades VALUES (8156, 19, 'CHOCOITE', '4644', 0);
INSERT INTO ubicacion_localidades VALUES (8157, 19, 'CHORCAN', '4631', 0);
INSERT INTO ubicacion_localidades VALUES (8158, 19, 'CHOROJRA', '4640', 0);
INSERT INTO ubicacion_localidades VALUES (8159, 19, 'CHORRILLOS', '4630', 0);
INSERT INTO ubicacion_localidades VALUES (8160, 19, 'CHORRILLOS', '4626', 0);
INSERT INTO ubicacion_localidades VALUES (8161, 19, 'CHORRILLOS', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8162, 19, 'CHORRO', '4513', 0);
INSERT INTO ubicacion_localidades VALUES (8163, 19, 'CHUCALEZNA', '4626', 0);
INSERT INTO ubicacion_localidades VALUES (8164, 19, 'CHULIN O INCA NUEVA', '4640', 0);
INSERT INTO ubicacion_localidades VALUES (8165, 19, 'CHUQUINA', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8166, 19, 'CIANZO', '4631', 0);
INSERT INTO ubicacion_localidades VALUES (8167, 19, 'CIENAGA', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8168, 19, 'CIENAGA GRANDE', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8169, 19, 'CIENEGO GRANDE', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8170, 19, 'CIENEGUILLAS', '4653', 0);
INSERT INTO ubicacion_localidades VALUES (8171, 19, 'CINCEL', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8172, 19, 'COCHAGATE', '4640', 0);
INSERT INTO ubicacion_localidades VALUES (8173, 19, 'COCHINOCA', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8174, 19, 'COCTACA', '4631', 0);
INSERT INTO ubicacion_localidades VALUES (8175, 19, 'COIRURO', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8176, 19, 'COLONIA 8 DE SEPTIEMBRE', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8177, 19, 'COLONIA LOS LAPACHOS', '4606', 0);
INSERT INTO ubicacion_localidades VALUES (8178, 19, 'COLORADOS', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8179, 19, 'CONDOR', '4630', 0);
INSERT INTO ubicacion_localidades VALUES (8180, 19, 'CONDOR', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8181, 19, 'CONDOR', '4630', 0);
INSERT INTO ubicacion_localidades VALUES (8182, 19, 'CORANZULLI', '4633', 0);
INSERT INTO ubicacion_localidades VALUES (8183, 19, 'CORAYA', '4630', 0);
INSERT INTO ubicacion_localidades VALUES (8184, 19, 'CORONEL ARIAS', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8185, 19, 'CORRAL BLANCO', '4655', 0);
INSERT INTO ubicacion_localidades VALUES (8186, 19, 'CORRAL BLANCO', '4655', 0);
INSERT INTO ubicacion_localidades VALUES (8187, 19, 'CORRAL DE PIEDRAS', '4601', 0);
INSERT INTO ubicacion_localidades VALUES (8188, 19, 'CORRALITO', '4650', 0);
INSERT INTO ubicacion_localidades VALUES (8189, 19, 'CORTADERAS', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8190, 19, 'CORTADERAS', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8191, 19, 'CORTADERAS', '4631', 0);
INSERT INTO ubicacion_localidades VALUES (8192, 19, 'COSTILLAR', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8193, 19, 'COYAGUAIMA', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8194, 19, 'CRUZ NIDO', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8195, 19, 'CUSI CUSI', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8196, 19, 'DOGLONZO', '4631', 0);
INSERT INTO ubicacion_localidades VALUES (8197, 19, 'DON JORGE', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8198, 19, 'DONCELLAS', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8199, 19, 'DURAZNAL', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8200, 19, 'EL ACHERAL', '4500', 0);
INSERT INTO ubicacion_localidades VALUES (8201, 19, 'EL AGUILAR', '4634', 0);
INSERT INTO ubicacion_localidades VALUES (8202, 19, 'EL AIBAL', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8203, 19, 'EL ALGARROBAL', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8204, 19, 'EL ALGARROBAL', '4612', 0);
INSERT INTO ubicacion_localidades VALUES (8205, 19, 'EL AMANCAY', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8206, 19, 'EL ANGOSTO', '4655', 0);
INSERT INTO ubicacion_localidades VALUES (8207, 19, 'EL ARENAL', '4601', 0);
INSERT INTO ubicacion_localidades VALUES (8208, 19, 'EL ARENAL', '4500', 0);
INSERT INTO ubicacion_localidades VALUES (8209, 19, 'EL BANANAL', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8210, 19, 'EL BRETE', '4612', 0);
INSERT INTO ubicacion_localidades VALUES (8211, 19, 'EL BRETE', '4612', 0);
INSERT INTO ubicacion_localidades VALUES (8212, 19, 'EL CABRAL', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8213, 19, 'EL CADILLAL', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8214, 19, 'EL CALLEJON', '4622', 0);
INSERT INTO ubicacion_localidades VALUES (8215, 19, 'EL CARDONAL', '4506', 0);
INSERT INTO ubicacion_localidades VALUES (8216, 19, 'EL CARMEN PERICO DE', '4603', 0);
INSERT INTO ubicacion_localidades VALUES (8217, 19, 'EL CAULARIO', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8218, 19, 'EL CHUCUPAL', '4603', 0);
INSERT INTO ubicacion_localidades VALUES (8219, 19, 'EL COLORADO', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8220, 19, 'EL CONDOR', '4644', 0);
INSERT INTO ubicacion_localidades VALUES (8221, 19, 'EL CUCHO', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8222, 19, 'EL CUCHO', '4612', 0);
INSERT INTO ubicacion_localidades VALUES (8223, 19, 'EL DURAZNO', '4624', 0);
INSERT INTO ubicacion_localidades VALUES (8224, 19, 'EL FUERTE', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8225, 19, 'EL MANANTIAL', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8226, 19, 'EL MISTOL', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8227, 19, 'EL MOLINO', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8228, 19, 'EL MOLLAR', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8229, 19, 'EL MONUMENTO', '4650', 0);
INSERT INTO ubicacion_localidades VALUES (8230, 19, 'EL MORENO', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8231, 19, 'EL MORRO', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8232, 19, 'EL NARANJO', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8233, 19, 'EL OLLERO', '4603', 0);
INSERT INTO ubicacion_localidades VALUES (8234, 19, 'EL OLLERO', '4605', 0);
INSERT INTO ubicacion_localidades VALUES (8235, 19, 'EL OLVIDO', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8236, 19, 'EL PALMAR', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8237, 19, 'EL PALMAR DE SAN FRANCISCO', '4522', 0);
INSERT INTO ubicacion_localidades VALUES (8238, 19, 'EL PERCHEL', '4624', 0);
INSERT INTO ubicacion_localidades VALUES (8239, 19, 'EL PIQUETE', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8240, 19, 'EL PONGO', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8241, 19, 'EL PORVENIR', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8242, 19, 'EL POTRERO DE LA PUNA', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8243, 19, 'EL QUEMADO', '4504', 0);
INSERT INTO ubicacion_localidades VALUES (8244, 19, 'EL REMATE', '4612', 0);
INSERT INTO ubicacion_localidades VALUES (8245, 19, 'EL REMATE', '4612', 0);
INSERT INTO ubicacion_localidades VALUES (8246, 19, 'EL RIO NEGRO', '4504', 0);
INSERT INTO ubicacion_localidades VALUES (8247, 19, 'EL SALADILLO', '4500', 0);
INSERT INTO ubicacion_localidades VALUES (8248, 19, 'EL SAUCE', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8249, 19, 'EL SUNCHAL', '4603', 0);
INSERT INTO ubicacion_localidades VALUES (8250, 19, 'EL SUNCHAL', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8251, 19, 'EL TALAR', '4542', 0);
INSERT INTO ubicacion_localidades VALUES (8252, 19, 'EL TIPAL', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8253, 19, 'EL TOBA', '4606', 0);
INSERT INTO ubicacion_localidades VALUES (8254, 19, 'EL TORO', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8255, 19, 'ENCRUCIJADA', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8256, 19, 'ENSENADA', '4500', 0);
INSERT INTO ubicacion_localidades VALUES (8257, 19, 'ENTRE RIOS', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8258, 19, 'ESCAYA', '4644', 0);
INSERT INTO ubicacion_localidades VALUES (8259, 19, 'ESQUINA', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8260, 19, 'ESQUINA DE HUANCAR', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8261, 19, 'ESQUINA DE QUISTO', '4500', 0);
INSERT INTO ubicacion_localidades VALUES (8262, 19, 'ESQUINA GRANDE', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8263, 19, 'ESQUINAS BLANCAS', '4634', 0);
INSERT INTO ubicacion_localidades VALUES (8264, 19, 'ESTACION PERICO', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8265, 19, 'ESTACION ZOOTECNICA', '4640', 0);
INSERT INTO ubicacion_localidades VALUES (8266, 19, 'ESTANCIA GRANDE', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8267, 19, 'FALDA DEL QUEBRACHAL', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8268, 19, 'FALDA MOJON', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8269, 19, 'FALDA MONTOSA', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8270, 19, 'FARILLON', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8271, 19, 'FINCA AGUA SALADA', '4516', 0);
INSERT INTO ubicacion_localidades VALUES (8272, 19, 'FINCA AGUA TAPADA', '4516', 0);
INSERT INTO ubicacion_localidades VALUES (8273, 19, 'FINCA LA LUCRECIA', '4516', 0);
INSERT INTO ubicacion_localidades VALUES (8274, 19, 'FINCA LA REALIDAD', '4516', 0);
INSERT INTO ubicacion_localidades VALUES (8275, 19, 'FINCA LEACH', '4504', 0);
INSERT INTO ubicacion_localidades VALUES (8276, 19, 'FINCA SANTA CORNELIA', '4516', 0);
INSERT INTO ubicacion_localidades VALUES (8277, 19, 'FLORENCIA', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8278, 19, 'FRAILE PINTADO', '4506', 0);
INSERT INTO ubicacion_localidades VALUES (8279, 19, 'FUNDICIONES', '4513', 0);
INSERT INTO ubicacion_localidades VALUES (8280, 19, 'GALETA', '4630', 0);
INSERT INTO ubicacion_localidades VALUES (8281, 19, 'GENERAL MANUEL SAVIO', '4612', 0);
INSERT INTO ubicacion_localidades VALUES (8282, 19, 'GOBERNADOR OVEJERO', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8283, 19, 'GOBERNADOR TELLO', '4513', 0);
INSERT INTO ubicacion_localidades VALUES (8284, 19, 'GRAL MANUEL SAVIO', '4612', 0);
INSERT INTO ubicacion_localidades VALUES (8285, 19, 'GRANADAS', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8286, 19, 'GUACHAN', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8287, 19, 'GUAYACAN', '4506', 0);
INSERT INTO ubicacion_localidades VALUES (8288, 19, 'GUAYATAYOC', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8289, 19, 'GUAYATAYOC', '4653', 0);
INSERT INTO ubicacion_localidades VALUES (8290, 19, 'GUEMES', '4640', 0);
INSERT INTO ubicacion_localidades VALUES (8291, 19, 'GUERRERO', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8292, 19, 'GUERREROS', '4601', 0);
INSERT INTO ubicacion_localidades VALUES (8293, 19, 'HIGUERITAS', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8294, 19, 'HIGUERITAS', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8295, 19, 'HIPOLITO YRIGOYEN  EST ITURBE', '4632', 0);
INSERT INTO ubicacion_localidades VALUES (8296, 19, 'HORNADITAS', '4630', 0);
INSERT INTO ubicacion_localidades VALUES (8297, 19, 'HORNILLOS', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8298, 19, 'HORNILLOS', '4653', 0);
INSERT INTO ubicacion_localidades VALUES (8299, 19, 'HUACALERA', '4626', 0);
INSERT INTO ubicacion_localidades VALUES (8300, 19, 'HUACHICHOCANA', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8301, 19, 'HUAICO CHICO', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8302, 19, 'HUALLATAYOC', '4653', 0);
INSERT INTO ubicacion_localidades VALUES (8303, 19, 'HUANCAR', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8304, 19, 'HUANCAR', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8305, 19, 'HUICHAIRA', '4624', 0);
INSERT INTO ubicacion_localidades VALUES (8306, 19, 'HUMAHUACA', '4630', 0);
INSERT INTO ubicacion_localidades VALUES (8307, 19, 'INCA HUASI', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8308, 19, 'INGENIO LA ESPERANZA', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8309, 19, 'INGENIO LA ESPERANZA', '4542', 0);
INSERT INTO ubicacion_localidades VALUES (8310, 19, 'INGENIO LEDESMA', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8311, 19, 'INTICANCHO', '4651', 0);
INSERT INTO ubicacion_localidades VALUES (8312, 19, 'IRIARTE', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8313, 19, 'ISLA CHICA', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8314, 19, 'ISLA GRANDE', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8315, 19, 'ITUAICOCHICO', '4601', 0);
INSERT INTO ubicacion_localidades VALUES (8316, 19, 'ITURBE', '4632', 0);
INSERT INTO ubicacion_localidades VALUES (8317, 19, 'JARAMILLO', '4504', 0);
INSERT INTO ubicacion_localidades VALUES (8318, 19, 'JUAN GALAN', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8319, 19, 'JUAN MANUEL DE ROSAS', '4612', 0);
INSERT INTO ubicacion_localidades VALUES (8320, 19, 'JUELLA', '4624', 0);
INSERT INTO ubicacion_localidades VALUES (8321, 19, 'KILOMETRO 1129', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8322, 19, 'KILOMETRO 1183', '4616', 0);
INSERT INTO ubicacion_localidades VALUES (8323, 19, 'KILOMETRO 1289', '4630', 0);
INSERT INTO ubicacion_localidades VALUES (8324, 19, 'KILOMETRO 1321', '4634', 0);
INSERT INTO ubicacion_localidades VALUES (8325, 19, 'KILOMETRO 1369', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8326, 19, 'KILOMETRO 1397', '4644', 0);
INSERT INTO ubicacion_localidades VALUES (8327, 19, 'LA AGUADITA', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8328, 19, 'LA ALMONA', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8329, 19, 'LA BAJADA', '4506', 0);
INSERT INTO ubicacion_localidades VALUES (8330, 19, 'LA BANDA', '4640', 0);
INSERT INTO ubicacion_localidades VALUES (8331, 19, 'LA BANDA', '4624', 0);
INSERT INTO ubicacion_localidades VALUES (8332, 19, 'LA CABAÃA', '4605', 0);
INSERT INTO ubicacion_localidades VALUES (8333, 19, 'LA CALERA', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8334, 19, 'LA CHINACA', '4651', 0);
INSERT INTO ubicacion_localidades VALUES (8335, 19, 'LA CIENAGA', '4603', 0);
INSERT INTO ubicacion_localidades VALUES (8336, 19, 'LA CIENAGA', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8337, 19, 'LA CIENAGA', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8338, 19, 'LA CIENAGA', '4650', 0);
INSERT INTO ubicacion_localidades VALUES (8339, 19, 'LA CRUZ', '4655', 0);
INSERT INTO ubicacion_localidades VALUES (8340, 19, 'LA CUESTA', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8341, 19, 'LA CUESTA', '4612', 0);
INSERT INTO ubicacion_localidades VALUES (8342, 19, 'LA CUEVA', '4632', 0);
INSERT INTO ubicacion_localidades VALUES (8343, 19, 'LA CUEVA', '4632', 0);
INSERT INTO ubicacion_localidades VALUES (8344, 19, 'LA ESPERANZA', '4503', 0);
INSERT INTO ubicacion_localidades VALUES (8345, 19, 'LA FALDA', '4640', 0);
INSERT INTO ubicacion_localidades VALUES (8346, 19, 'LA INTERMEDIA', '4650', 0);
INSERT INTO ubicacion_localidades VALUES (8347, 19, 'LA MENDIETA', '4522', 0);
INSERT INTO ubicacion_localidades VALUES (8348, 19, 'LA OLLADA', '4603', 0);
INSERT INTO ubicacion_localidades VALUES (8349, 19, 'LA OLLADA', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8350, 19, 'LA OVEJERIA', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8351, 19, 'LA PUERTA', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8352, 19, 'LA PUERTA', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8353, 19, 'LA QUIACA', '4650', 0);
INSERT INTO ubicacion_localidades VALUES (8354, 19, 'LA QUINTA', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8355, 19, 'LA REDUCCION', '4506', 0);
INSERT INTO ubicacion_localidades VALUES (8356, 19, 'LA RONDA', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8357, 19, 'LA SANGA', '4500', 0);
INSERT INTO ubicacion_localidades VALUES (8358, 19, 'LA SANGA', '4522', 0);
INSERT INTO ubicacion_localidades VALUES (8359, 19, 'LA TOMA', '4605', 0);
INSERT INTO ubicacion_localidades VALUES (8360, 19, 'LA UNION', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8361, 19, 'LA VERTIENTE', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8362, 19, 'LA VETA', '4634', 0);
INSERT INTO ubicacion_localidades VALUES (8363, 19, 'LA VETA', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8364, 19, 'LAGUNA SAN MIGUEL', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8365, 19, 'LAGUNA TOTORILLAS', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8366, 19, 'LAGUNAS', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8367, 19, 'LAGUNAS DE YALA', '4616', 0);
INSERT INTO ubicacion_localidades VALUES (8368, 19, 'LAGUNAS DEYALA', '4616', 0);
INSERT INTO ubicacion_localidades VALUES (8369, 19, 'LAGUNILLA EL CARMEN', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8370, 19, 'LAGUNILLA LEDESMA', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8371, 19, 'LAGUNILLAS', '4640', 0);
INSERT INTO ubicacion_localidades VALUES (8372, 19, 'LAPACHAL LEDESMA', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8373, 19, 'LAPACHAL SANTA BARBARA', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8374, 19, 'LAS CAÃADAS', '4606', 0);
INSERT INTO ubicacion_localidades VALUES (8375, 19, 'LAS CAPILLAS', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8376, 19, 'LAS CAPILLAS', '4612', 0);
INSERT INTO ubicacion_localidades VALUES (8377, 19, 'LAS CHICAPENAS', '4622', 0);
INSERT INTO ubicacion_localidades VALUES (8378, 19, 'LAS ESCALERAS', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8379, 19, 'LAS ESCALERAS', '4612', 0);
INSERT INTO ubicacion_localidades VALUES (8380, 19, 'LAS HIGUERILLAS', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8381, 19, 'LAS HIGUERITAS', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8382, 19, 'LAS PAMPITAS', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8383, 19, 'LAS PICHANAS', '4606', 0);
INSERT INTO ubicacion_localidades VALUES (8384, 19, 'LAS PIRCAS', '4603', 0);
INSERT INTO ubicacion_localidades VALUES (8385, 19, 'LAS QUINTAS', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8386, 19, 'LAVAYEN', '4503', 0);
INSERT INTO ubicacion_localidades VALUES (8387, 19, 'LEACHS', '4504', 0);
INSERT INTO ubicacion_localidades VALUES (8388, 19, 'LECHERIA', '4514', 0);
INSERT INTO ubicacion_localidades VALUES (8389, 19, 'LEDESMA', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8390, 19, 'LEON', '4616', 0);
INSERT INTO ubicacion_localidades VALUES (8391, 19, 'LEON', '4616', 0);
INSERT INTO ubicacion_localidades VALUES (8392, 19, 'LIB GRAL SAN MARTIN', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8393, 19, 'LINDERO', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8394, 19, 'LIPAN', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8395, 19, 'LLAMERIA', '4644', 0);
INSERT INTO ubicacion_localidades VALUES (8396, 19, 'LLULLUCHAYOC', '4644', 0);
INSERT INTO ubicacion_localidades VALUES (8397, 19, 'LOMA BLANCA', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8398, 19, 'LOMA DEL MEDIO', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8399, 19, 'LOMA LARGA', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8400, 19, 'LOMA LARGA', '4513', 0);
INSERT INTO ubicacion_localidades VALUES (8401, 19, 'LOMA PELADA', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8402, 19, 'LOS ALISOS', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8403, 19, 'LOS ALISOS', '4605', 0);
INSERT INTO ubicacion_localidades VALUES (8404, 19, 'LOS BAÃOS TERMALES', '4516', 0);
INSERT INTO ubicacion_localidades VALUES (8405, 19, 'LOS BAYOS', '4503', 0);
INSERT INTO ubicacion_localidades VALUES (8406, 19, 'LOS BLANCOS', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8407, 19, 'LOS BLANCOS', '4612', 0);
INSERT INTO ubicacion_localidades VALUES (8408, 19, 'LOS CATRES', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8409, 19, 'LOS CEDROS', '4603', 0);
INSERT INTO ubicacion_localidades VALUES (8410, 19, 'LOS LAPACHOS', '4606', 0);
INSERT INTO ubicacion_localidades VALUES (8411, 19, 'LOS MANANTIALES', '4606', 0);
INSERT INTO ubicacion_localidades VALUES (8412, 19, 'LOS MATOS', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8413, 19, 'LOTE DON DAVID', '4522', 0);
INSERT INTO ubicacion_localidades VALUES (8414, 19, 'LOTE DON EMILIO', '4522', 0);
INSERT INTO ubicacion_localidades VALUES (8415, 19, 'LOTE EL PUESTO', '4503', 0);
INSERT INTO ubicacion_localidades VALUES (8416, 19, 'LOTE LA CIENAGA', '4503', 0);
INSERT INTO ubicacion_localidades VALUES (8417, 19, 'LOTE LA POSTA', '4503', 0);
INSERT INTO ubicacion_localidades VALUES (8418, 19, 'LOTE MAIZ NEGRO', '4506', 0);
INSERT INTO ubicacion_localidades VALUES (8419, 19, 'LOTE MIRAFLORES', '4503', 0);
INSERT INTO ubicacion_localidades VALUES (8420, 19, 'LOTE PALMERA', '4503', 0);
INSERT INTO ubicacion_localidades VALUES (8421, 19, 'LOTE PARAPETI', '4503', 0);
INSERT INTO ubicacion_localidades VALUES (8422, 19, 'LOTE PIEDRITAS', '4522', 0);
INSERT INTO ubicacion_localidades VALUES (8423, 19, 'LOTE PREDILIANA', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8424, 19, 'LOTE SAN ANTONIO', '4503', 0);
INSERT INTO ubicacion_localidades VALUES (8425, 19, 'LOTE SAN JUANCITO', '4522', 0);
INSERT INTO ubicacion_localidades VALUES (8426, 19, 'LOTE SAUZAL', '4522', 0);
INSERT INTO ubicacion_localidades VALUES (8427, 19, 'LOTE ZORA', '4504', 0);
INSERT INTO ubicacion_localidades VALUES (8428, 19, 'LOZANO', '4616', 0);
INSERT INTO ubicacion_localidades VALUES (8429, 19, 'LUMARA', '4640', 0);
INSERT INTO ubicacion_localidades VALUES (8430, 19, 'MAIMARA', '4622', 0);
INSERT INTO ubicacion_localidades VALUES (8431, 19, 'MAL PASO', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8432, 19, 'MAQUINISTA VERON', '4606', 0);
INSERT INTO ubicacion_localidades VALUES (8433, 19, 'MARTA', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8434, 19, 'MAYILTE', '4644', 0);
INSERT INTO ubicacion_localidades VALUES (8435, 19, 'MAYINTE', '4644', 0);
INSERT INTO ubicacion_localidades VALUES (8436, 19, 'MERCO', '4655', 0);
INSERT INTO ubicacion_localidades VALUES (8437, 19, 'MILAN', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8438, 19, 'MINA 9 DE OCTUBRE', '4612', 0);
INSERT INTO ubicacion_localidades VALUES (8439, 19, 'MINA 9 DE OCTUBRE', '4612', 0);
INSERT INTO ubicacion_localidades VALUES (8440, 19, 'MINA AJEDREZ', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8441, 19, 'MINA BELGICA', '4644', 0);
INSERT INTO ubicacion_localidades VALUES (8442, 19, 'MINA PIRQUITAS', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8443, 19, 'MINA PULPERA', '4644', 0);
INSERT INTO ubicacion_localidades VALUES (8444, 19, 'MINA SAN FRANCISCO', '4644', 0);
INSERT INTO ubicacion_localidades VALUES (8445, 19, 'MINA SOL DE MAYO', '4644', 0);
INSERT INTO ubicacion_localidades VALUES (8446, 19, 'MINAS AZULES', '4655', 0);
INSERT INTO ubicacion_localidades VALUES (8447, 19, 'MINAS DE BORATO', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8448, 19, 'MINIAIO', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8449, 19, 'MIRA FLORES', '4640', 0);
INSERT INTO ubicacion_localidades VALUES (8450, 19, 'MIRAFLORES DE LA CANDELARIA', '4640', 0);
INSERT INTO ubicacion_localidades VALUES (8451, 19, 'MIRES', '4655', 0);
INSERT INTO ubicacion_localidades VALUES (8452, 19, 'MIYUYOC', '4632', 0);
INSERT INTO ubicacion_localidades VALUES (8453, 19, 'MOCORAITE', '4644', 0);
INSERT INTO ubicacion_localidades VALUES (8454, 19, 'MOJON AZUCENA', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8455, 19, 'MOLINOS', '4616', 0);
INSERT INTO ubicacion_localidades VALUES (8456, 19, 'MOLINOS', '4616', 0);
INSERT INTO ubicacion_localidades VALUES (8457, 19, 'MOLLI PUNCO', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8458, 19, 'MOLULAR', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8459, 19, 'MOLULO', '4624', 0);
INSERT INTO ubicacion_localidades VALUES (8460, 19, 'MOLULO', '4624', 0);
INSERT INTO ubicacion_localidades VALUES (8461, 19, 'MONTE RICO', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8462, 19, 'MORALITO', '4500', 0);
INSERT INTO ubicacion_localidades VALUES (8463, 19, 'MORENO CHICO', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8464, 19, 'MORRITO', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8465, 19, 'MORRO', '4655', 0);
INSERT INTO ubicacion_localidades VALUES (8466, 19, 'MUÃAYOC', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8467, 19, 'MULLI PUNCO', '4650', 0);
INSERT INTO ubicacion_localidades VALUES (8468, 19, 'NARANJITO', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8469, 19, 'NAZARENO', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8470, 19, 'NOGAL', '4513', 0);
INSERT INTO ubicacion_localidades VALUES (8471, 19, 'NOGALES', '4513', 0);
INSERT INTO ubicacion_localidades VALUES (8472, 19, 'NOGALITO', '4513', 0);
INSERT INTO ubicacion_localidades VALUES (8473, 19, 'NORMENTA', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8474, 19, 'OBRAJE SAN JOSE', '4516', 0);
INSERT INTO ubicacion_localidades VALUES (8475, 19, 'OCLOYAS', '4601', 0);
INSERT INTO ubicacion_localidades VALUES (8476, 19, 'OCULTO', '4506', 0);
INSERT INTO ubicacion_localidades VALUES (8477, 19, 'OCUMAZO', '4630', 0);
INSERT INTO ubicacion_localidades VALUES (8478, 19, 'OJO DE AGUA', '4506', 0);
INSERT INTO ubicacion_localidades VALUES (8479, 19, 'OLACAPATO', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8480, 19, 'OLAROZ CHICO', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8481, 19, 'OLAROZ GRANDE', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8482, 19, 'ORATORIO', '4655', 0);
INSERT INTO ubicacion_localidades VALUES (8483, 19, 'ORNILLO', '4653', 0);
INSERT INTO ubicacion_localidades VALUES (8484, 19, 'OROS SAN JUAN', '4655', 0);
INSERT INTO ubicacion_localidades VALUES (8485, 19, 'OROSMAYO', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8486, 19, 'OVARA', '4630', 0);
INSERT INTO ubicacion_localidades VALUES (8487, 19, 'PAÃO', '4605', 0);
INSERT INTO ubicacion_localidades VALUES (8488, 19, 'PAICONE', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8489, 19, 'PAIRIQUE CHICO', '4644', 0);
INSERT INTO ubicacion_localidades VALUES (8490, 19, 'PAIRIQUE GRANDE', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8491, 19, 'PALCA DE APARZO', '4631', 0);
INSERT INTO ubicacion_localidades VALUES (8492, 19, 'PALMA SOLA', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8493, 19, 'PALO A PIQUE', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8494, 19, 'PALO BLANCO', '4522', 0);
INSERT INTO ubicacion_localidades VALUES (8495, 19, 'PALO SANTO', '4522', 0);
INSERT INTO ubicacion_localidades VALUES (8496, 19, 'PALPALA', '4612', 0);
INSERT INTO ubicacion_localidades VALUES (8497, 19, 'PALPALA', '4612', 0);
INSERT INTO ubicacion_localidades VALUES (8498, 19, 'PAMPA BLANCA', '4606', 0);
INSERT INTO ubicacion_localidades VALUES (8499, 19, 'PAMPA LARGA', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8500, 19, 'PAMPA VIEJA', '4606', 0);
INSERT INTO ubicacion_localidades VALUES (8501, 19, 'PAMPICHUELA', '4513', 0);
INSERT INTO ubicacion_localidades VALUES (8502, 19, 'PAN DE AZUCAR MINA', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8503, 19, 'PASAJES', '4653', 0);
INSERT INTO ubicacion_localidades VALUES (8504, 19, 'PASTO PAMPA', '4653', 0);
INSERT INTO ubicacion_localidades VALUES (8505, 19, 'PASTOS CHICOS', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8506, 19, 'PAULETE', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8507, 19, 'PAULINA', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8508, 19, 'PAYO', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8509, 19, 'PEÃA COLORADA', '4655', 0);
INSERT INTO ubicacion_localidades VALUES (8510, 19, 'PEÃAS BLANCAS', '4632', 0);
INSERT INTO ubicacion_localidades VALUES (8511, 19, 'PEÃAS BLANCAS', '4632', 0);
INSERT INTO ubicacion_localidades VALUES (8512, 19, 'PERICO', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8513, 19, 'PERICO DEL CARMEN', '4603', 0);
INSERT INTO ubicacion_localidades VALUES (8514, 19, 'PERICO SAN JUAN', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8515, 19, 'PICACHO', '4513', 0);
INSERT INTO ubicacion_localidades VALUES (8516, 19, 'PIE DE LA CUESTA', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8517, 19, 'PIEDRA BLANCA', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8518, 19, 'PIEDRA CHOTA', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8519, 19, 'PIEDRAS AMONTONADAS', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8520, 19, 'PIEDRAS BLANCAS', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8521, 19, 'PILCOMAYO', '4513', 0);
INSERT INTO ubicacion_localidades VALUES (8522, 19, 'PISCUNO', '4655', 0);
INSERT INTO ubicacion_localidades VALUES (8523, 19, 'PISCUNO', '4651', 0);
INSERT INTO ubicacion_localidades VALUES (8524, 19, 'PISUNGO', '4632', 0);
INSERT INTO ubicacion_localidades VALUES (8525, 19, 'PORVENIR', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8526, 19, 'POSTA DE HORNILLOS', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8527, 19, 'POSTA DE HORNILLOS', '4622', 0);
INSERT INTO ubicacion_localidades VALUES (8528, 19, 'POSTA GUARDAPARQUE NACIONAL CA', '4514', 0);
INSERT INTO ubicacion_localidades VALUES (8529, 19, 'POTRERILLO', '4631', 0);
INSERT INTO ubicacion_localidades VALUES (8530, 19, 'POTRERO', '4650', 0);
INSERT INTO ubicacion_localidades VALUES (8531, 19, 'POTRERO', '4640', 0);
INSERT INTO ubicacion_localidades VALUES (8532, 19, 'POTRERO', '4653', 0);
INSERT INTO ubicacion_localidades VALUES (8533, 19, 'POTRERO', '4616', 0);
INSERT INTO ubicacion_localidades VALUES (8534, 19, 'POTRERO ALEGRE', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8535, 19, 'POTRERO DE LA PUNA', '4640', 0);
INSERT INTO ubicacion_localidades VALUES (8536, 19, 'POZO BRAVO', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8537, 19, 'POZO CABADO', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8538, 19, 'POZO COLORADO', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8539, 19, 'POZO DE LAS AVISPAS', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8540, 19, 'POZO VERDE', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8541, 19, 'POZUELO', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8542, 19, 'PREDILIANA', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8543, 19, 'PUCARA', '4624', 0);
INSERT INTO ubicacion_localidades VALUES (8544, 19, 'PUEBLO', '4513', 0);
INSERT INTO ubicacion_localidades VALUES (8545, 19, 'PUEBLO LEDESMA', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8546, 19, 'PUEBLO NUEVO', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8547, 19, 'PUEBLO PLA', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8548, 19, 'PUEBLO VIEJO', '4632', 0);
INSERT INTO ubicacion_localidades VALUES (8549, 19, 'PUERTA DE COLORADOS', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8550, 19, 'PUERTA DE LIPAN', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8551, 19, 'PUERTA DE SALAS', '4612', 0);
INSERT INTO ubicacion_localidades VALUES (8552, 19, 'PUERTA PATACAL', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8553, 19, 'PUERTA POTRERO', '4640', 0);
INSERT INTO ubicacion_localidades VALUES (8554, 19, 'PUERTA VIEJA', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8555, 19, 'PUERTO TOLAVA', '4513', 0);
INSERT INTO ubicacion_localidades VALUES (8556, 19, 'PUESTO', '4624', 0);
INSERT INTO ubicacion_localidades VALUES (8557, 19, 'PUESTO CHICO', '4653', 0);
INSERT INTO ubicacion_localidades VALUES (8558, 19, 'PUESTO DEL MARQUEZ', '4644', 0);
INSERT INTO ubicacion_localidades VALUES (8559, 19, 'PUESTO GRANDE', '4653', 0);
INSERT INTO ubicacion_localidades VALUES (8560, 19, 'PUESTO NUEVO', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8561, 19, 'PUESTO VIEJO', '4606', 0);
INSERT INTO ubicacion_localidades VALUES (8562, 19, 'PUMAHUASI', '4644', 0);
INSERT INTO ubicacion_localidades VALUES (8563, 19, 'PUNA DE JUJUY', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8564, 19, 'PUNTA CANAL', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8565, 19, 'PUNTA CORRAL', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8566, 19, 'PUNTA DE AGUA', '4644', 0);
INSERT INTO ubicacion_localidades VALUES (8567, 19, 'PUNTA DEL AGUA', '4644', 0);
INSERT INTO ubicacion_localidades VALUES (8568, 19, 'PUNTA DEL CAMPO', '4622', 0);
INSERT INTO ubicacion_localidades VALUES (8569, 19, 'PUNTAS DE COLORADOS', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8570, 19, 'PURMAMARCA', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8571, 19, 'QUEÃOAL', '4513', 0);
INSERT INTO ubicacion_localidades VALUES (8572, 19, 'QUEBRADA HUASAMAYO', '4624', 0);
INSERT INTO ubicacion_localidades VALUES (8573, 19, 'QUEBRALEÃA', '4640', 0);
INSERT INTO ubicacion_localidades VALUES (8574, 19, 'QUENTI TACO', '4644', 0);
INSERT INTO ubicacion_localidades VALUES (8575, 19, 'QUERA', '4634', 0);
INSERT INTO ubicacion_localidades VALUES (8576, 19, 'QUERA', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8577, 19, 'QUETA', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8578, 19, 'QUICHAGUA', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8579, 19, 'QUIMAZO', '4630', 0);
INSERT INTO ubicacion_localidades VALUES (8580, 19, 'QUISQUINE', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8581, 19, 'RACHAITE', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8582, 19, 'RAMADA', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8583, 19, 'RAMADA', '4631', 0);
INSERT INTO ubicacion_localidades VALUES (8584, 19, 'RAMALLO', '4640', 0);
INSERT INTO ubicacion_localidades VALUES (8585, 19, 'RASTROJOS', '4504', 0);
INSERT INTO ubicacion_localidades VALUES (8586, 19, 'REAL DE LOS TOROS', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8587, 19, 'RECEPTORIA', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8588, 19, 'REDONDA', '4644', 0);
INSERT INTO ubicacion_localidades VALUES (8589, 19, 'REYES', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8590, 19, 'REYES VILLA JARDIN', '4601', 0);
INSERT INTO ubicacion_localidades VALUES (8591, 19, 'RINCONADA', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8592, 19, 'RINCONADILLAS', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8593, 19, 'RIO BLANCO', '4601', 0);
INSERT INTO ubicacion_localidades VALUES (8594, 19, 'RIO BLANCO', '4605', 0);
INSERT INTO ubicacion_localidades VALUES (8595, 19, 'RIO COLORADO', '4644', 0);
INSERT INTO ubicacion_localidades VALUES (8596, 19, 'RIO GRANDE', '4634', 0);
INSERT INTO ubicacion_localidades VALUES (8597, 19, 'RIO GRANDE', '4522', 0);
INSERT INTO ubicacion_localidades VALUES (8598, 19, 'RIO NEGRO', '4504', 0);
INSERT INTO ubicacion_localidades VALUES (8599, 19, 'RIO SECO', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8600, 19, 'RIVERITO', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8601, 19, 'RODEITOS', '4500', 0);
INSERT INTO ubicacion_localidades VALUES (8602, 19, 'RODEO', '4651', 0);
INSERT INTO ubicacion_localidades VALUES (8603, 19, 'RODEO CHICO', '4653', 0);
INSERT INTO ubicacion_localidades VALUES (8604, 19, 'RODERO', '4631', 0);
INSERT INTO ubicacion_localidades VALUES (8605, 19, 'RONQUE', '4631', 0);
INSERT INTO ubicacion_localidades VALUES (8606, 19, 'RONTUYOC', '4644', 0);
INSERT INTO ubicacion_localidades VALUES (8607, 19, 'RUMI CRUZ', '4640', 0);
INSERT INTO ubicacion_localidades VALUES (8608, 19, 'SALA', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8609, 19, 'SALADILLO LEDESMA', '4522', 0);
INSERT INTO ubicacion_localidades VALUES (8610, 19, 'SALADILLO LEDESMA', '4500', 0);
INSERT INTO ubicacion_localidades VALUES (8611, 19, 'SALADILLO SAN PEDRO', '4522', 0);
INSERT INTO ubicacion_localidades VALUES (8612, 19, 'SALITRE', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8613, 19, 'SAN ANDRES', '4630', 0);
INSERT INTO ubicacion_localidades VALUES (8614, 19, 'SAN ANTONIO', '4503', 0);
INSERT INTO ubicacion_localidades VALUES (8615, 19, 'SAN ANTONIO', '4513', 0);
INSERT INTO ubicacion_localidades VALUES (8616, 19, 'SAN ANTONIO PERICO DE', '4605', 0);
INSERT INTO ubicacion_localidades VALUES (8617, 19, 'SAN BERNARDO', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8618, 19, 'SAN FRANCISCO', '4655', 0);
INSERT INTO ubicacion_localidades VALUES (8619, 19, 'SAN FRANCISCO', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8620, 19, 'SAN FRANCISCO DE ALFARCITO', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8621, 19, 'SAN GABRIEL', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8622, 19, 'SAN JAVIER', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8623, 19, 'SAN JOSE', '4626', 0);
INSERT INTO ubicacion_localidades VALUES (8624, 19, 'SAN JOSE DE LA RINCONADA', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8625, 19, 'SAN JOSE DE MIRAFLORES', '4640', 0);
INSERT INTO ubicacion_localidades VALUES (8626, 19, 'SAN JOSE DEL BORDO', '4500', 0);
INSERT INTO ubicacion_localidades VALUES (8627, 19, 'SAN JOSE DEL CHAÃI', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8628, 19, 'SAN JUAN', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8629, 19, 'SAN JUAN', '4655', 0);
INSERT INTO ubicacion_localidades VALUES (8630, 19, 'SAN JUAN DE DIOS', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8631, 19, 'SAN JUAN DE OROS', '4655', 0);
INSERT INTO ubicacion_localidades VALUES (8632, 19, 'SAN JUAN DE QUILLAGUES', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8633, 19, 'SAN JUANCITO', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8634, 19, 'SAN LEON', '4655', 0);
INSERT INTO ubicacion_localidades VALUES (8635, 19, 'SAN LORENZO', '4514', 0);
INSERT INTO ubicacion_localidades VALUES (8636, 19, 'SAN LUCAS', '4500', 0);
INSERT INTO ubicacion_localidades VALUES (8637, 19, 'SAN LUCAS', '4513', 0);
INSERT INTO ubicacion_localidades VALUES (8638, 19, 'SAN PABLO DE REYES', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8639, 19, 'SAN PEDRITO', '4622', 0);
INSERT INTO ubicacion_localidades VALUES (8640, 19, 'SAN PEDRO', '4500', 0);
INSERT INTO ubicacion_localidades VALUES (8641, 19, 'SAN PEDRO DE IRUYA', '4633', 0);
INSERT INTO ubicacion_localidades VALUES (8642, 19, 'SAN RAFAEL', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8643, 19, 'SAN RAFAEL', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8644, 19, 'SAN ROQUE', '4630', 0);
INSERT INTO ubicacion_localidades VALUES (8645, 19, 'SAN SALVADOR DE JUJUY', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8646, 19, 'SAN VICENTE', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8647, 19, 'SANSANA', '4650', 0);
INSERT INTO ubicacion_localidades VALUES (8648, 19, 'SANTA ANA', '4631', 0);
INSERT INTO ubicacion_localidades VALUES (8649, 19, 'SANTA ANA DE LA PUNA', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8650, 19, 'SANTA BARBARA', '4513', 0);
INSERT INTO ubicacion_localidades VALUES (8651, 19, 'SANTA CATALINA', '4655', 0);
INSERT INTO ubicacion_localidades VALUES (8652, 19, 'SANTA CLARA', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8653, 19, 'SANTA CLARA', '4513', 0);
INSERT INTO ubicacion_localidades VALUES (8654, 19, 'SANTA RITA', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8655, 19, 'SANTA RITA', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8656, 19, 'SANTA ROSA TUMBAYA', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8657, 19, 'SANTA ROSA VALLE GRANDE', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8658, 19, 'SANTILLO', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8659, 19, 'SANTO DOMINGO', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8660, 19, 'SANTUARIO', '4640', 0);
INSERT INTO ubicacion_localidades VALUES (8661, 19, 'SANTUYOC', '4616', 0);
INSERT INTO ubicacion_localidades VALUES (8662, 19, 'SAUZAL', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8663, 19, 'SAUZALITO', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8664, 19, 'SAYATE', '4640', 0);
INSERT INTO ubicacion_localidades VALUES (8665, 19, 'SENADOR PEREZ', '4626', 0);
INSERT INTO ubicacion_localidades VALUES (8666, 19, 'SEPULTURA', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8667, 19, 'SEY', '4411', 0);
INSERT INTO ubicacion_localidades VALUES (8668, 19, 'SIBERIA', '4506', 0);
INSERT INTO ubicacion_localidades VALUES (8669, 19, 'SIETE AGUAS', '4501', 0);
INSERT INTO ubicacion_localidades VALUES (8670, 19, 'SIJES', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8671, 19, 'SOCABON', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8672, 19, 'SOLEDAD', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8673, 19, 'SOLEDAD', '4631', 0);
INSERT INTO ubicacion_localidades VALUES (8674, 19, 'SORCUYO', '4640', 0);
INSERT INTO ubicacion_localidades VALUES (8675, 19, 'SOYSOLAYTE', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8676, 19, 'SURIPUJIO', '4651', 0);
INSERT INTO ubicacion_localidades VALUES (8677, 19, 'SUSQUES', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8678, 19, 'SUSUYACO', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8679, 19, 'TABLADITAS', '4640', 0);
INSERT INTO ubicacion_localidades VALUES (8680, 19, 'TABLON', '4631', 0);
INSERT INTO ubicacion_localidades VALUES (8681, 19, 'TACTA', '4622', 0);
INSERT INTO ubicacion_localidades VALUES (8682, 19, 'TAFNA', '4650', 0);
INSERT INTO ubicacion_localidades VALUES (8683, 19, 'TALA GRUSA', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8684, 19, 'TALAR', '4513', 0);
INSERT INTO ubicacion_localidades VALUES (8685, 19, 'TAMBILLOS', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8686, 19, 'TANQUES', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8687, 19, 'TARIJITA', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8688, 19, 'TEJADAS', '4634', 0);
INSERT INTO ubicacion_localidades VALUES (8689, 19, 'TERMAS DE REYES', '4601', 0);
INSERT INTO ubicacion_localidades VALUES (8690, 19, 'TESORERO', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8691, 19, 'TESORERO', '4616', 0);
INSERT INTO ubicacion_localidades VALUES (8692, 19, 'TEUCO', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8693, 19, 'TILCARA', '4624', 0);
INSERT INTO ubicacion_localidades VALUES (8694, 19, 'TILQUIZA', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8695, 19, 'TIMON CRUZ', '4655', 0);
INSERT INTO ubicacion_localidades VALUES (8696, 19, 'TIO MAYO', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8697, 19, 'TIO MAYO', '4653', 0);
INSERT INTO ubicacion_localidades VALUES (8698, 19, 'TIRAXI', '4616', 0);
INSERT INTO ubicacion_localidades VALUES (8699, 19, 'TIRAXI CHICO', '4616', 0);
INSERT INTO ubicacion_localidades VALUES (8700, 19, 'TOBA', '4648', 0);
INSERT INTO ubicacion_localidades VALUES (8701, 19, 'TOBA', '4606', 0);
INSERT INTO ubicacion_localidades VALUES (8702, 19, 'TOCOL', '4643', 0);
INSERT INTO ubicacion_localidades VALUES (8703, 19, 'TOQUERO', '4653', 0);
INSERT INTO ubicacion_localidades VALUES (8704, 19, 'TOQUILLERA', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8705, 19, 'TORO MUERTO', '4631', 0);
INSERT INTO ubicacion_localidades VALUES (8706, 19, 'TOTORITO', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8707, 19, 'TRANCAS', '4631', 0);
INSERT INTO ubicacion_localidades VALUES (8708, 19, 'TREMENTINAL', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8709, 19, 'TRES CRUCES', '4634', 0);
INSERT INTO ubicacion_localidades VALUES (8710, 19, 'TRES MORROS', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8711, 19, 'TRIUNVIRATO', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8712, 19, 'TUCUMANCITO', '4512', 0);
INSERT INTO ubicacion_localidades VALUES (8713, 19, 'TUITE', '4644', 0);
INSERT INTO ubicacion_localidades VALUES (8714, 19, 'TUMBAYA', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8715, 19, 'TUMBAYA GRANDE', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8716, 19, 'TUNALITO', '4618', 0);
INSERT INTO ubicacion_localidades VALUES (8717, 19, 'TURILARI', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8718, 19, 'TURU TARI', '4640', 0);
INSERT INTO ubicacion_localidades VALUES (8719, 19, 'TURU TARI', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8720, 19, 'TUSAQUILLAS', '4641', 0);
INSERT INTO ubicacion_localidades VALUES (8721, 19, 'UQUIA', '4626', 0);
INSERT INTO ubicacion_localidades VALUES (8722, 19, 'VALLE COLORADO', '4631', 0);
INSERT INTO ubicacion_localidades VALUES (8723, 19, 'VALLE GRANDE', '4513', 0);
INSERT INTO ubicacion_localidades VALUES (8724, 19, 'VARAS', '4631', 0);
INSERT INTO ubicacion_localidades VALUES (8725, 19, 'VENECIAS ARGENTINAS', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8726, 19, 'VETA MINA AGUILAR', '4634', 0);
INSERT INTO ubicacion_localidades VALUES (8727, 19, 'VICUÃAYOC', '4634', 0);
INSERT INTO ubicacion_localidades VALUES (8728, 19, 'VILLA ARGENTINA', '4608', 0);
INSERT INTO ubicacion_localidades VALUES (8729, 19, 'VILLA CIUDAD DE NIEVA', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8730, 19, 'VILLA DEL PERCHEL', '4626', 0);
INSERT INTO ubicacion_localidades VALUES (8731, 19, 'VILLA GORRITI', '4600', 0);
INSERT INTO ubicacion_localidades VALUES (8732, 19, 'VINALITO', '4518', 0);
INSERT INTO ubicacion_localidades VALUES (8733, 19, 'VISCACHANI', '4651', 0);
INSERT INTO ubicacion_localidades VALUES (8734, 19, 'VIZACACHAL', '4631', 0);
INSERT INTO ubicacion_localidades VALUES (8735, 19, 'VOLCAN', '4616', 0);
INSERT INTO ubicacion_localidades VALUES (8736, 19, 'VOLCAN HIGUERA', '4633', 0);
INSERT INTO ubicacion_localidades VALUES (8737, 19, 'YACORAITE', '4634', 0);
INSERT INTO ubicacion_localidades VALUES (8738, 19, 'YALA', '4616', 0);
INSERT INTO ubicacion_localidades VALUES (8739, 19, 'YALA DE MONTE CARMELO', '4624', 0);
INSERT INTO ubicacion_localidades VALUES (8740, 19, 'YAVI', '4651', 0);
INSERT INTO ubicacion_localidades VALUES (8741, 19, 'YAVI CHICO', '4651', 0);
INSERT INTO ubicacion_localidades VALUES (8742, 19, 'YERBA BUENA LEDESMA', '4622', 0);
INSERT INTO ubicacion_localidades VALUES (8743, 19, 'YERBA BUENA TILCARA', '4622', 0);
INSERT INTO ubicacion_localidades VALUES (8744, 19, 'YOSCABA', '4653', 0);
INSERT INTO ubicacion_localidades VALUES (8745, 19, 'YUTO', '4518', 0);
INSERT INTO ubicacion_localidades VALUES (8746, 19, 'ZAPLA', '4612', 0);
INSERT INTO ubicacion_localidades VALUES (8747, 18, '25 DE MAYO', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (8748, 18, 'ABRAMO', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (8749, 18, 'ADOLFO VAN PRAET', '6212', 0);
INSERT INTO ubicacion_localidades VALUES (8750, 18, 'AGUA DE TORRE', '5621', 0);
INSERT INTO ubicacion_localidades VALUES (8751, 18, 'AGUAS BUENAS', '6228', 0);
INSERT INTO ubicacion_localidades VALUES (8752, 18, 'AGUSTONI', '6361', 0);
INSERT INTO ubicacion_localidades VALUES (8753, 18, 'ALFREDO PEÃA', '6333', 0);
INSERT INTO ubicacion_localidades VALUES (8754, 18, 'ALGARROBO DEL AGUILA', '6323', 0);
INSERT INTO ubicacion_localidades VALUES (8755, 18, 'ALPACHIRI', '6309', 0);
INSERT INTO ubicacion_localidades VALUES (8756, 18, 'ALTA ITALIA', '6207', 0);
INSERT INTO ubicacion_localidades VALUES (8757, 18, 'ANGUIL', '6326', 0);
INSERT INTO ubicacion_localidades VALUES (8758, 18, 'ANZOATEGUI', '8138', 0);
INSERT INTO ubicacion_localidades VALUES (8759, 18, 'ARATA', '6385', 0);
INSERT INTO ubicacion_localidades VALUES (8760, 18, 'ARBOL DE LA ESPERANZA', '6323', 0);
INSERT INTO ubicacion_localidades VALUES (8761, 18, 'ARBOL SOLO', '6323', 0);
INSERT INTO ubicacion_localidades VALUES (8762, 18, 'ARGENTINA BELVEDERE', '6367', 0);
INSERT INTO ubicacion_localidades VALUES (8763, 18, 'ARTURO ALMARAZ', '6330', 0);
INSERT INTO ubicacion_localidades VALUES (8764, 18, 'ATALIVA ROCA', '6301', 0);
INSERT INTO ubicacion_localidades VALUES (8765, 18, 'ATREUCO', '6305', 0);
INSERT INTO ubicacion_localidades VALUES (8766, 18, 'AZTEAZU', '6365', 0);
INSERT INTO ubicacion_localidades VALUES (8767, 18, 'BAJO DE LAS PALOMAS', '6313', 0);
INSERT INTO ubicacion_localidades VALUES (8768, 18, 'BALO LOS MORROS', '8138', 0);
INSERT INTO ubicacion_localidades VALUES (8769, 18, 'BARRANCAS COLORADAS', '6300', 0);
INSERT INTO ubicacion_localidades VALUES (8770, 18, 'BARRANCAS COLORADAS', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (8771, 18, 'BARRIO EL MOLINO', '6360', 0);
INSERT INTO ubicacion_localidades VALUES (8772, 18, 'BELGRANO', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (8773, 18, 'BELLA VISTA', '6305', 0);
INSERT INTO ubicacion_localidades VALUES (8774, 18, 'BERNARDO LARROUDE', '6220', 0);
INSERT INTO ubicacion_localidades VALUES (8775, 18, 'BERNASCONI', '8204', 0);
INSERT INTO ubicacion_localidades VALUES (8776, 18, 'BOEUF', '6380', 0);
INSERT INTO ubicacion_localidades VALUES (8777, 18, 'BOLICHE LA ARAÃA', '6301', 0);
INSERT INTO ubicacion_localidades VALUES (8778, 18, 'BUTALO', '6323', 0);
INSERT INTO ubicacion_localidades VALUES (8779, 18, 'CACHIRULO', '6303', 0);
INSERT INTO ubicacion_localidades VALUES (8780, 18, 'CAICHUE', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (8781, 18, 'CAIMI', '6361', 0);
INSERT INTO ubicacion_localidades VALUES (8782, 18, 'CALCHAHUE', '6303', 0);
INSERT INTO ubicacion_localidades VALUES (8783, 18, 'CALEU CALEU', '8138', 0);
INSERT INTO ubicacion_localidades VALUES (8784, 18, 'CALEUFU', '6387', 0);
INSERT INTO ubicacion_localidades VALUES (8785, 18, 'CAMPO CARETTO', '6381', 0);
INSERT INTO ubicacion_localidades VALUES (8786, 18, 'CAMPO CICARE', '8208', 0);
INSERT INTO ubicacion_localidades VALUES (8787, 18, 'CAMPO DE LOS TOROS', '6311', 0);
INSERT INTO ubicacion_localidades VALUES (8788, 18, 'CAMPO DE SALAS', '8208', 0);
INSERT INTO ubicacion_localidades VALUES (8789, 18, 'CAMPO LA FLORIDA', '6311', 0);
INSERT INTO ubicacion_localidades VALUES (8790, 18, 'CAMPO LUDUEÃA', '6330', 0);
INSERT INTO ubicacion_localidades VALUES (8791, 18, 'CAMPO MOISES SECCION 1A', '6383', 0);
INSERT INTO ubicacion_localidades VALUES (8792, 18, 'CAMPO NICHOLSON', '8208', 0);
INSERT INTO ubicacion_localidades VALUES (8793, 18, 'CAMPO PICO', '6381', 0);
INSERT INTO ubicacion_localidades VALUES (8794, 18, 'CAMPO SALUSSO', '6369', 0);
INSERT INTO ubicacion_localidades VALUES (8795, 18, 'CAMPO URDANIZ', '6309', 0);
INSERT INTO ubicacion_localidades VALUES (8796, 18, 'CARAMAN', '6387', 0);
INSERT INTO ubicacion_localidades VALUES (8797, 18, 'CARLOS BERG', '6360', 0);
INSERT INTO ubicacion_localidades VALUES (8798, 18, 'CARRO QUEMADO', '6319', 0);
INSERT INTO ubicacion_localidades VALUES (8799, 18, 'CATRILO', '6330', 0);
INSERT INTO ubicacion_localidades VALUES (8800, 18, 'CAYUPAN', '6330', 0);
INSERT INTO ubicacion_localidades VALUES (8801, 18, 'CEBALLOS', '6221', 0);
INSERT INTO ubicacion_localidades VALUES (8802, 18, 'CEREALES', '6301', 0);
INSERT INTO ubicacion_localidades VALUES (8803, 18, 'CERRO AZUL', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (8804, 18, 'CERRO BAYO', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (8805, 18, 'CERRO DEL AIGRE', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (8806, 18, 'CERRO LA BOTA', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (8807, 18, 'CHACHARRAMENDI', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (8808, 18, 'CHACRA 16', '6380', 0);
INSERT INTO ubicacion_localidades VALUES (8809, 18, 'CHACRA LA CASILDA', '6221', 0);
INSERT INTO ubicacion_localidades VALUES (8810, 18, 'CHACRA LA MAGDALENA', '6621', 0);
INSERT INTO ubicacion_localidades VALUES (8811, 18, 'CHACRAS DE VICTORICA', '6319', 0);
INSERT INTO ubicacion_localidades VALUES (8812, 18, 'CHACU', '6303', 0);
INSERT INTO ubicacion_localidades VALUES (8813, 18, 'CHAMAICO', '6214', 0);
INSERT INTO ubicacion_localidades VALUES (8814, 18, 'CHANILAO', '6201', 0);
INSERT INTO ubicacion_localidades VALUES (8815, 18, 'CHAPALCO', '6303', 0);
INSERT INTO ubicacion_localidades VALUES (8816, 18, 'CHICALCO', '6323', 0);
INSERT INTO ubicacion_localidades VALUES (8817, 18, 'COLONIA  LA INDIA', '6352', 0);
INSERT INTO ubicacion_localidades VALUES (8818, 18, 'COLONIA 17 DE AGOSTO', '8204', 0);
INSERT INTO ubicacion_localidades VALUES (8819, 18, 'COLONIA AGUIRRE', '6301', 0);
INSERT INTO ubicacion_localidades VALUES (8820, 18, 'COLONIA ANASAGASTI', '6309', 0);
INSERT INTO ubicacion_localidades VALUES (8821, 18, 'COLONIA BARON', '6315', 0);
INSERT INTO ubicacion_localidades VALUES (8822, 18, 'COLONIA BEATRIZ', '8208', 0);
INSERT INTO ubicacion_localidades VALUES (8823, 18, 'COLONIA BEAUFORT', '6331', 0);
INSERT INTO ubicacion_localidades VALUES (8824, 18, 'COLONIA CAZAUX', '8214', 0);
INSERT INTO ubicacion_localidades VALUES (8825, 18, 'COLONIA DENEVI', '6228', 0);
INSERT INTO ubicacion_localidades VALUES (8826, 18, 'COLONIA DEVOTO', '6325', 0);
INSERT INTO ubicacion_localidades VALUES (8827, 18, 'COLONIA ECHETA', '6301', 0);
INSERT INTO ubicacion_localidades VALUES (8828, 18, 'COLONIA EL DESTINO', '6381', 0);
INSERT INTO ubicacion_localidades VALUES (8829, 18, 'COLONIA EL PORVENIR', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (8830, 18, 'COLONIA EL TIGRE', '6385', 0);
INSERT INTO ubicacion_localidades VALUES (8831, 18, 'COLONIA ESPAÃA', '8206', 0);
INSERT INTO ubicacion_localidades VALUES (8832, 18, 'COLONIA ESPIGA DE ORO', '6313', 0);
INSERT INTO ubicacion_localidades VALUES (8833, 18, 'COLONIA FERRARO', '6303', 0);
INSERT INTO ubicacion_localidades VALUES (8834, 18, 'COLONIA GIUSTI', '6331', 0);
INSERT INTO ubicacion_localidades VALUES (8835, 18, 'COLONIA GOBERNADOR AYALA', '8307', 0);
INSERT INTO ubicacion_localidades VALUES (8836, 18, 'COLONIA GUIBURG N 2', '6305', 0);
INSERT INTO ubicacion_localidades VALUES (8837, 18, 'COLONIA HELVECIA', '8206', 0);
INSERT INTO ubicacion_localidades VALUES (8838, 18, 'COLONIA LA ABUNDANCIA', '6333', 0);
INSERT INTO ubicacion_localidades VALUES (8839, 18, 'COLONIA LA AMARGA', '6325', 0);
INSERT INTO ubicacion_localidades VALUES (8840, 18, 'COLONIA LA CARLOTA', '6315', 0);
INSERT INTO ubicacion_localidades VALUES (8841, 18, 'COLONIA LA CHISPA', '6309', 0);
INSERT INTO ubicacion_localidades VALUES (8842, 18, 'COLONIA LA ESPERANZA', '6311', 0);
INSERT INTO ubicacion_localidades VALUES (8843, 18, 'COLONIA LA GAVIOTA', '6354', 0);
INSERT INTO ubicacion_localidades VALUES (8844, 18, 'COLONIA LA MARGARITA', '6214', 0);
INSERT INTO ubicacion_localidades VALUES (8845, 18, 'COLONIA LA MUTUA', '8214', 0);
INSERT INTO ubicacion_localidades VALUES (8846, 18, 'COLONIA LA ORACION', '6307', 0);
INSERT INTO ubicacion_localidades VALUES (8847, 18, 'COLONIA LA PASTORIL', '6323', 0);
INSERT INTO ubicacion_localidades VALUES (8848, 18, 'COLONIA LA PAZ', '6313', 0);
INSERT INTO ubicacion_localidades VALUES (8849, 18, 'COLONIA LA SARA', '6333', 0);
INSERT INTO ubicacion_localidades VALUES (8850, 18, 'COLONIA LAGOS', '6300', 0);
INSERT INTO ubicacion_localidades VALUES (8851, 18, 'COLONIA LAS MERCEDES', '6221', 0);
INSERT INTO ubicacion_localidades VALUES (8852, 18, 'COLONIA LAS PIEDRITAS', '6385', 0);
INSERT INTO ubicacion_localidades VALUES (8853, 18, 'COLONIA LAS TRES PIEDRAS', '8204', 0);
INSERT INTO ubicacion_localidades VALUES (8854, 18, 'COLONIA LAS VIZCACHERAS', '6309', 0);
INSERT INTO ubicacion_localidades VALUES (8855, 18, 'COLONIA LIA Y ALLENDE', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (8856, 18, 'COLONIA LOS PIOJOS', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (8857, 18, 'COLONIA LOS TOROS', '6311', 0);
INSERT INTO ubicacion_localidades VALUES (8858, 18, 'COLONIA LUNA', '6311', 0);
INSERT INTO ubicacion_localidades VALUES (8859, 18, 'COLONIA MARIA LUISA', '6301', 0);
INSERT INTO ubicacion_localidades VALUES (8860, 18, 'COLONIA MEDANO COLORADO', '8214', 0);
INSERT INTO ubicacion_localidades VALUES (8861, 18, 'COLONIA MIGLIORI', '6367', 0);
INSERT INTO ubicacion_localidades VALUES (8862, 18, 'COLONIA MINISTRO LOBOS', '6325', 0);
INSERT INTO ubicacion_localidades VALUES (8863, 18, 'COLONIA RAMON QUINTAS', '6303', 0);
INSERT INTO ubicacion_localidades VALUES (8864, 18, 'COLONIA ROCA', '6303', 0);
INSERT INTO ubicacion_localidades VALUES (8865, 18, 'COLONIA SAN BASILIO', '6214', 0);
INSERT INTO ubicacion_localidades VALUES (8866, 18, 'COLONIA SAN FELIPE', '6313', 0);
INSERT INTO ubicacion_localidades VALUES (8867, 18, 'COLONIA SAN IGNACIO', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (8868, 18, 'COLONIA SAN JOSE', '6315', 0);
INSERT INTO ubicacion_localidades VALUES (8869, 18, 'COLONIA SAN JUAN', '6326', 0);
INSERT INTO ubicacion_localidades VALUES (8870, 18, 'COLONIA SAN LORENZO', '6380', 0);
INSERT INTO ubicacion_localidades VALUES (8871, 18, 'COLONIA SAN MIGUEL', '6352', 0);
INSERT INTO ubicacion_localidades VALUES (8872, 18, 'COLONIA SAN ROSARIO', '8208', 0);
INSERT INTO ubicacion_localidades VALUES (8873, 18, 'COLONIA SAN VICTORIO', '6301', 0);
INSERT INTO ubicacion_localidades VALUES (8874, 18, 'COLONIA SANTA ANA', '6309', 0);
INSERT INTO ubicacion_localidades VALUES (8875, 18, 'COLONIA SANTA CECILIA', '6333', 0);
INSERT INTO ubicacion_localidades VALUES (8876, 18, 'COLONIA SANTA CLARA', '8214', 0);
INSERT INTO ubicacion_localidades VALUES (8877, 18, 'COLONIA SANTA ELENA', '6313', 0);
INSERT INTO ubicacion_localidades VALUES (8878, 18, 'COLONIA SANTA ELVIRA', '6365', 0);
INSERT INTO ubicacion_localidades VALUES (8879, 18, 'COLONIA SANTA MARIA', '8214', 0);
INSERT INTO ubicacion_localidades VALUES (8880, 18, 'COLONIA SANTA TERESA', '6311', 0);
INSERT INTO ubicacion_localidades VALUES (8881, 18, 'COLONIA SOBADELL', '6301', 0);
INSERT INTO ubicacion_localidades VALUES (8882, 18, 'COLONIA TORELLO', '6326', 0);
INSERT INTO ubicacion_localidades VALUES (8883, 18, 'COLONIA TRENQUENDA', '6220', 0);
INSERT INTO ubicacion_localidades VALUES (8884, 18, 'COLONIA TREQUEN', '6220', 0);
INSERT INTO ubicacion_localidades VALUES (8885, 18, 'COLONIA VASCONGADA', '8208', 0);
INSERT INTO ubicacion_localidades VALUES (8886, 18, 'COLONIA VILLA ALBA', '8206', 0);
INSERT INTO ubicacion_localidades VALUES (8887, 18, 'COLONIAS DRYSDALE', '6381', 0);
INSERT INTO ubicacion_localidades VALUES (8888, 18, 'COLONIAS MURRAY', '6381', 0);
INSERT INTO ubicacion_localidades VALUES (8889, 18, 'CONA LAUQUEN', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (8890, 18, 'CONHELO', '6381', 0);
INSERT INTO ubicacion_localidades VALUES (8891, 18, 'CORONEL HILARIOS LAGOS', '6228', 0);
INSERT INTO ubicacion_localidades VALUES (8892, 18, 'COSTA DEL SALADO', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (8893, 18, 'COTITA', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (8894, 18, 'CUCHILLO  CO', '8214', 0);
INSERT INTO ubicacion_localidades VALUES (8895, 18, 'CURILCO', '6331', 0);
INSERT INTO ubicacion_localidades VALUES (8896, 18, 'CURRU MAHUIDA', '6323', 0);
INSERT INTO ubicacion_localidades VALUES (8897, 18, 'DOBLAS', '6305', 0);
INSERT INTO ubicacion_localidades VALUES (8898, 18, 'DORILA', '6365', 0);
INSERT INTO ubicacion_localidades VALUES (8899, 18, 'DOS AMIGOS', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (8900, 18, 'DOS AMIGOS', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (8901, 18, 'DOS CHAÃARES', '8204', 0);
INSERT INTO ubicacion_localidades VALUES (8902, 18, 'DOS DE IPIÃA', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (8903, 18, 'DOS VIOLETAS', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (8904, 18, 'EDUARDO CASTEX', '6380', 0);
INSERT INTO ubicacion_localidades VALUES (8905, 18, 'EL AGUILA', '8138', 0);
INSERT INTO ubicacion_localidades VALUES (8906, 18, 'EL ANTOJO', '6220', 0);
INSERT INTO ubicacion_localidades VALUES (8907, 18, 'EL BELGICA', '6331', 0);
INSERT INTO ubicacion_localidades VALUES (8908, 18, 'EL BOQUERON', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (8909, 18, 'EL BOQUERON', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (8910, 18, 'EL BRILLANTE', '6352', 0);
INSERT INTO ubicacion_localidades VALUES (8911, 18, 'EL CARANCHO', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (8912, 18, 'EL CENTENARIO', '6307', 0);
INSERT INTO ubicacion_localidades VALUES (8913, 18, 'EL CENTINELA', '6323', 0);
INSERT INTO ubicacion_localidades VALUES (8914, 18, 'EL CHILLEN', '6325', 0);
INSERT INTO ubicacion_localidades VALUES (8915, 18, 'EL CINCO', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (8916, 18, 'EL DESCANSO', '8214', 0);
INSERT INTO ubicacion_localidades VALUES (8917, 18, 'EL DESCANSO  LONQUIMAY', '6352', 0);
INSERT INTO ubicacion_localidades VALUES (8918, 18, 'EL DESLINDE', '6305', 0);
INSERT INTO ubicacion_localidades VALUES (8919, 18, 'EL DESTINO', '6313', 0);
INSERT INTO ubicacion_localidades VALUES (8920, 18, 'EL DESTINO', '6381', 0);
INSERT INTO ubicacion_localidades VALUES (8921, 18, 'EL DESTINO', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (8922, 18, 'EL DESTINO  ROLON', '6305', 0);
INSERT INTO ubicacion_localidades VALUES (8923, 18, 'EL DIEZ', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (8924, 18, 'EL DIEZ Y SIETE', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (8925, 18, 'EL DURAZNO', '6319', 0);
INSERT INTO ubicacion_localidades VALUES (8926, 18, 'EL ESCABEL', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (8927, 18, 'EL EUCALIPTO', '6331', 0);
INSERT INTO ubicacion_localidades VALUES (8928, 18, 'EL EUCALIPTO CARRO QUEMADO', '6319', 0);
INSERT INTO ubicacion_localidades VALUES (8929, 18, 'EL FURLONG', '6313', 0);
INSERT INTO ubicacion_localidades VALUES (8930, 18, 'EL GUAICURU', '6352', 0);
INSERT INTO ubicacion_localidades VALUES (8931, 18, 'EL GUANACO', '6380', 0);
INSERT INTO ubicacion_localidades VALUES (8932, 18, 'EL GUANACO', '6205', 0);
INSERT INTO ubicacion_localidades VALUES (8933, 18, 'EL GUANACO  WINIFREDA', '6313', 0);
INSERT INTO ubicacion_localidades VALUES (8934, 18, 'EL HUITRU', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (8935, 18, 'EL LUCERO', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (8936, 18, 'EL MADRIGAL', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (8937, 18, 'EL MALACATE', '6341', 0);
INSERT INTO ubicacion_localidades VALUES (8938, 18, 'EL MATE', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (8939, 18, 'EL MIRADOR', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (8940, 18, 'EL MIRADOR DE JUAREZ', '6300', 0);
INSERT INTO ubicacion_localidades VALUES (8941, 18, 'EL NUEVE', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (8942, 18, 'EL OASIS', '6300', 0);
INSERT INTO ubicacion_localidades VALUES (8943, 18, 'EL ODRE', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (8944, 18, 'EL OLIVO', '6203', 0);
INSERT INTO ubicacion_localidades VALUES (8945, 18, 'EL PALOMAR', '6305', 0);
INSERT INTO ubicacion_localidades VALUES (8946, 18, 'EL PARQUE', '6331', 0);
INSERT INTO ubicacion_localidades VALUES (8947, 18, 'EL PELUDO', '6381', 0);
INSERT INTO ubicacion_localidades VALUES (8948, 18, 'EL PIMIA', '8214', 0);
INSERT INTO ubicacion_localidades VALUES (8949, 18, 'EL PORVENIR', '6228', 0);
INSERT INTO ubicacion_localidades VALUES (8950, 18, 'EL PORVENIR', '8214', 0);
INSERT INTO ubicacion_localidades VALUES (8951, 18, 'EL PUMA', '8214', 0);
INSERT INTO ubicacion_localidades VALUES (8952, 18, 'EL RECREO', '6220', 0);
INSERT INTO ubicacion_localidades VALUES (8953, 18, 'EL REFUGIO', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (8954, 18, 'EL RETIRO', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (8955, 18, 'EL RUBI', '6352', 0);
INSERT INTO ubicacion_localidades VALUES (8956, 18, 'EL SALITRAL', '6352', 0);
INSERT INTO ubicacion_localidades VALUES (8957, 18, 'EL SAUCE', '6331', 0);
INSERT INTO ubicacion_localidades VALUES (8958, 18, 'EL SILENCIO', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (8959, 18, 'EL TAJAMAR', '6205', 0);
INSERT INTO ubicacion_localidades VALUES (8960, 18, 'EL TARTAGAL', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (8961, 18, 'EL TIGRE', '6203', 0);
INSERT INTO ubicacion_localidades VALUES (8962, 18, 'EL TORDILLO', '6212', 0);
INSERT INTO ubicacion_localidades VALUES (8963, 18, 'EL TRECE', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (8964, 18, 'EL TRIGO', '8206', 0);
INSERT INTO ubicacion_localidades VALUES (8965, 18, 'EL TRIUNFO', '6352', 0);
INSERT INTO ubicacion_localidades VALUES (8966, 18, 'EL UNO', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (8967, 18, 'EL VASQUITO', '8206', 0);
INSERT INTO ubicacion_localidades VALUES (8968, 18, 'EL VERANEO', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (8969, 18, 'EL VOLANTE', '6303', 0);
INSERT INTO ubicacion_localidades VALUES (8970, 18, 'EMBAJADOR MARTINI', '6203', 0);
INSERT INTO ubicacion_localidades VALUES (8971, 18, 'EMILIO MITRE', '6323', 0);
INSERT INTO ubicacion_localidades VALUES (8972, 18, 'EPU PEL', '8214', 0);
INSERT INTO ubicacion_localidades VALUES (8973, 18, 'ESTABLECIMIENTO EL CENTINELA', '6323', 0);
INSERT INTO ubicacion_localidades VALUES (8974, 18, 'ESTANCIA LA LUCHA', '6221', 0);
INSERT INTO ubicacion_localidades VALUES (8975, 18, 'ESTANCIA LA PAMPEANA', '6221', 0);
INSERT INTO ubicacion_localidades VALUES (8976, 18, 'ESTANCIA LA VOLUNTAD', '6221', 0);
INSERT INTO ubicacion_localidades VALUES (8977, 18, 'EUSKADI', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (8978, 18, 'EX ESCUELA HOGAR NRO 5', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (8979, 18, 'FALUCHO', '6212', 0);
INSERT INTO ubicacion_localidades VALUES (8980, 18, 'GALLINAO', '6228', 0);
INSERT INTO ubicacion_localidades VALUES (8981, 18, 'GAVIOTAS', '8138', 0);
INSERT INTO ubicacion_localidades VALUES (8982, 18, 'GENERAL ACHA', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (8983, 18, 'GENERAL PICO', '6360', 0);
INSERT INTO ubicacion_localidades VALUES (8984, 18, 'GENERAL SAN MARTIN', '8206', 0);
INSERT INTO ubicacion_localidades VALUES (8985, 18, 'GERVASIO ORTIZ DE ROSAS', '8204', 0);
INSERT INTO ubicacion_localidades VALUES (8986, 18, 'GOBERNADOR AYALA', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (8987, 18, 'GOBERNADOR DUVAL', '8336', 0);
INSERT INTO ubicacion_localidades VALUES (8988, 18, 'GRAL MANUEL CAMPOS', '6309', 0);
INSERT INTO ubicacion_localidades VALUES (8989, 18, 'GUADALOZA', '6319', 0);
INSERT INTO ubicacion_localidades VALUES (8990, 18, 'GUARACO', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (8991, 18, 'GUATRACHE', '6311', 0);
INSERT INTO ubicacion_localidades VALUES (8992, 18, 'HIDALGO', '6307', 0);
INSERT INTO ubicacion_localidades VALUES (8993, 18, 'HIPOLITO YRIGOYEN', '6305', 0);
INSERT INTO ubicacion_localidades VALUES (8994, 18, 'HUCAL', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (8995, 18, 'HUELEN', '6333', 0);
INSERT INTO ubicacion_localidades VALUES (8996, 18, 'INES Y CARLOTA', '6315', 0);
INSERT INTO ubicacion_localidades VALUES (8997, 18, 'INGENIERO FOSTER', '6385', 0);
INSERT INTO ubicacion_localidades VALUES (8998, 18, 'INGENIERO LUIGGI', '6205', 0);
INSERT INTO ubicacion_localidades VALUES (8999, 18, 'INTENDENTE ALVEAR', '6221', 0);
INSERT INTO ubicacion_localidades VALUES (9000, 18, 'IPIÃA', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (9001, 18, 'IVANOWSKY', '6330', 0);
INSERT INTO ubicacion_localidades VALUES (9002, 18, 'JACINTO ARAUZ', '8208', 0);
INSERT INTO ubicacion_localidades VALUES (9003, 18, 'JAGUEL DEL ESQUINERO', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9004, 18, 'JAGUEL DEL MONTE', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9005, 18, 'JARDON', '6214', 0);
INSERT INTO ubicacion_localidades VALUES (9006, 18, 'JULIAN A MANSILLA', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (9007, 18, 'JUZGADO VIEJO', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9008, 18, 'KILOMETRO 619', '6381', 0);
INSERT INTO ubicacion_localidades VALUES (9009, 18, 'LA ADELA', '8134', 0);
INSERT INTO ubicacion_localidades VALUES (9010, 18, 'LA ADMINISTRACION', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (9011, 18, 'LA ANTONIA', '6307', 0);
INSERT INTO ubicacion_localidades VALUES (9012, 18, 'LA ARAÃA', '6301', 0);
INSERT INTO ubicacion_localidades VALUES (9013, 18, 'LA ASTURIANA', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (9014, 18, 'LA ATALAYA', '6352', 0);
INSERT INTO ubicacion_localidades VALUES (9015, 18, 'LA AURORA', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (9016, 18, 'LA AVANZADA', '6325', 0);
INSERT INTO ubicacion_localidades VALUES (9017, 18, 'LA BANDERITA', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (9018, 18, 'LA BARRANCOSA', '6365', 0);
INSERT INTO ubicacion_localidades VALUES (9019, 18, 'LA BAYA', '6303', 0);
INSERT INTO ubicacion_localidades VALUES (9020, 18, 'LA BAYA MUERTA', '6303', 0);
INSERT INTO ubicacion_localidades VALUES (9021, 18, 'LA BILBAINA', '6345', 0);
INSERT INTO ubicacion_localidades VALUES (9022, 18, 'LA BLANCA', '6330', 0);
INSERT INTO ubicacion_localidades VALUES (9023, 18, 'LA BOTA', '8307', 0);
INSERT INTO ubicacion_localidades VALUES (9024, 18, 'LA CARMEN', '6354', 0);
INSERT INTO ubicacion_localidades VALUES (9025, 18, 'LA CAROLA', '6326', 0);
INSERT INTO ubicacion_localidades VALUES (9026, 18, 'LA CASILDA', '6228', 0);
INSERT INTO ubicacion_localidades VALUES (9027, 18, 'LA CATALINA', '6354', 0);
INSERT INTO ubicacion_localidades VALUES (9028, 18, 'LA CATALINA', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9029, 18, 'LA CATALINITA', '6305', 0);
INSERT INTO ubicacion_localidades VALUES (9030, 18, 'LA CATITA', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (9031, 18, 'LA CAUTIVA', '6333', 0);
INSERT INTO ubicacion_localidades VALUES (9032, 18, 'LA CELIA', '6352', 0);
INSERT INTO ubicacion_localidades VALUES (9033, 18, 'LA CELINA', '6333', 0);
INSERT INTO ubicacion_localidades VALUES (9034, 18, 'LA CELINA', '6303', 0);
INSERT INTO ubicacion_localidades VALUES (9035, 18, 'LA CELMIRA', '6303', 0);
INSERT INTO ubicacion_localidades VALUES (9036, 18, 'LA CHAPELLE', '6360', 0);
INSERT INTO ubicacion_localidades VALUES (9037, 18, 'LA CHIRLANDIA', '8203', 0);
INSERT INTO ubicacion_localidades VALUES (9038, 18, 'LA CHITA', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (9039, 18, 'LA CHITA PUELCHES', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (9040, 18, 'LA CIENAGA', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9041, 18, 'LA CLELIA', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (9042, 18, 'LA COLORADA CHICA', '8206', 0);
INSERT INTO ubicacion_localidades VALUES (9043, 18, 'LA COLORADA GRANDE', '8206', 0);
INSERT INTO ubicacion_localidades VALUES (9044, 18, 'LA CONSTANCIA', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (9045, 18, 'LA CONSTANCIA', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9046, 18, 'LA CONSTANCIA  ANGUIL', '6326', 0);
INSERT INTO ubicacion_localidades VALUES (9047, 18, 'LA COPELINA', '8307', 0);
INSERT INTO ubicacion_localidades VALUES (9048, 18, 'LA CUMBRE', '6354', 0);
INSERT INTO ubicacion_localidades VALUES (9049, 18, 'LA DELFINA', '6313', 0);
INSERT INTO ubicacion_localidades VALUES (9050, 18, 'LA DELICIA', '6333', 0);
INSERT INTO ubicacion_localidades VALUES (9051, 18, 'LA DOLORES', '6301', 0);
INSERT INTO ubicacion_localidades VALUES (9052, 18, 'LA ELENITA', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9053, 18, 'LA ELIA', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9054, 18, 'LA ELINA', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9055, 18, 'LA ELINA', '6203', 0);
INSERT INTO ubicacion_localidades VALUES (9056, 18, 'LA ELSA', '6331', 0);
INSERT INTO ubicacion_localidades VALUES (9057, 18, 'LA ELVA', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (9058, 18, 'LA ELVIRA', '6326', 0);
INSERT INTO ubicacion_localidades VALUES (9059, 18, 'LA ENERGIA', '6228', 0);
INSERT INTO ubicacion_localidades VALUES (9060, 18, 'LA ENRIQUETA', '6333', 0);
INSERT INTO ubicacion_localidades VALUES (9061, 18, 'LA ESCONDIDA', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (9062, 18, 'LA ESMERALDA', '6352', 0);
INSERT INTO ubicacion_localidades VALUES (9063, 18, 'LA ESMERALDA', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9064, 18, 'LA ESMERALDA  MACACHIN', '6307', 0);
INSERT INTO ubicacion_localidades VALUES (9065, 18, 'LA ESPERANZA', '6323', 0);
INSERT INTO ubicacion_localidades VALUES (9066, 18, 'LA ESPERANZA', '8204', 0);
INSERT INTO ubicacion_localidades VALUES (9067, 18, 'LA ESPERANZA  ANGUIL', '6326', 0);
INSERT INTO ubicacion_localidades VALUES (9068, 18, 'LA ESPERANZA  HIDALGO', '6305', 0);
INSERT INTO ubicacion_localidades VALUES (9069, 18, 'LA ESPERANZA  MACACHIN', '6307', 0);
INSERT INTO ubicacion_localidades VALUES (9070, 18, 'LA ESPERANZA VERTIZ', '6365', 0);
INSERT INTO ubicacion_localidades VALUES (9071, 18, 'LA ESTHER', '6325', 0);
INSERT INTO ubicacion_localidades VALUES (9072, 18, 'LA ESTRELLA', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9073, 18, 'LA ESTRELLA DEL SUD', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (9074, 18, 'LA EULOGIA', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9075, 18, 'LA FE', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9076, 18, 'LA FLORENCIA', '6317', 0);
INSERT INTO ubicacion_localidades VALUES (9077, 18, 'LA FLORIDA', '6326', 0);
INSERT INTO ubicacion_localidades VALUES (9078, 18, 'LA FORTUNA', '6300', 0);
INSERT INTO ubicacion_localidades VALUES (9079, 18, 'LA GAVENITA', '6361', 0);
INSERT INTO ubicacion_localidades VALUES (9080, 18, 'LA GAVIOTA', '6354', 0);
INSERT INTO ubicacion_localidades VALUES (9081, 18, 'LA GLORIA', '6348', 0);
INSERT INTO ubicacion_localidades VALUES (9082, 18, 'LA GUADALOSA', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9083, 18, 'LA GUEÃITA', '6360', 0);
INSERT INTO ubicacion_localidades VALUES (9084, 18, 'LA HUMADA', '6323', 0);
INSERT INTO ubicacion_localidades VALUES (9085, 18, 'LA IMARRA', '6323', 0);
INSERT INTO ubicacion_localidades VALUES (9086, 18, 'LA INVERNADA', '6228', 0);
INSERT INTO ubicacion_localidades VALUES (9087, 18, 'LA ISABEL', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (9088, 18, 'LA ISABEL', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9089, 18, 'LA JAPONESA', '8336', 0);
INSERT INTO ubicacion_localidades VALUES (9090, 18, 'LA JOSEFINA', '6307', 0);
INSERT INTO ubicacion_localidades VALUES (9091, 18, 'LA JUANITA', '6300', 0);
INSERT INTO ubicacion_localidades VALUES (9092, 18, 'LA JUANITA', '6208', 0);
INSERT INTO ubicacion_localidades VALUES (9093, 18, 'LA JUANITA', '8206', 0);
INSERT INTO ubicacion_localidades VALUES (9094, 18, 'LA LAURENTINA', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9095, 18, 'LA LEÃA', '6330', 0);
INSERT INTO ubicacion_localidades VALUES (9096, 18, 'LA LIMPIA', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (9097, 18, 'LA LONJA', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (9098, 18, 'LA LUCHA', '6228', 0);
INSERT INTO ubicacion_localidades VALUES (9099, 18, 'LA LUCHA LA REFORMA', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (9100, 18, 'LA LUISA', '6354', 0);
INSERT INTO ubicacion_localidades VALUES (9101, 18, 'LA LUZ', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9102, 18, 'LA MAGDALENA', '6228', 0);
INSERT INTO ubicacion_localidades VALUES (9103, 18, 'LA MAGDALENA', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (9104, 18, 'LA MALVINA', '6300', 0);
INSERT INTO ubicacion_localidades VALUES (9105, 18, 'LA MANUELITA', '6305', 0);
INSERT INTO ubicacion_localidades VALUES (9106, 18, 'LA MARCELA', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9107, 18, 'LA MARGARITA', '6214', 0);
INSERT INTO ubicacion_localidades VALUES (9108, 18, 'LA MARIA', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (9109, 18, 'LA MARIA', '6331', 0);
INSERT INTO ubicacion_localidades VALUES (9110, 18, 'LA MARIA ELENA', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (9111, 18, 'LA MARIA ELISA', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (9112, 18, 'LA MARIA ROSA', '6309', 0);
INSERT INTO ubicacion_localidades VALUES (9113, 18, 'LA MARIA VERTIZ', '6365', 0);
INSERT INTO ubicacion_localidades VALUES (9114, 18, 'LA MARIANITA', '6354', 0);
INSERT INTO ubicacion_localidades VALUES (9115, 18, 'LA MARUJA', '6385', 0);
INSERT INTO ubicacion_localidades VALUES (9116, 18, 'LA MATILDE', '6341', 0);
INSERT INTO ubicacion_localidades VALUES (9117, 18, 'LA MODERNA', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (9118, 18, 'LA MOROCHA', '6319', 0);
INSERT INTO ubicacion_localidades VALUES (9119, 18, 'LA NILDA', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (9120, 18, 'LA NUEVA', '6311', 0);
INSERT INTO ubicacion_localidades VALUES (9121, 18, 'LA NUEVA PROVINCIA', '6305', 0);
INSERT INTO ubicacion_localidades VALUES (9122, 18, 'LA OLLA', '6333', 0);
INSERT INTO ubicacion_localidades VALUES (9123, 18, 'LA ORACION', '6307', 0);
INSERT INTO ubicacion_localidades VALUES (9124, 18, 'LA PALOMA', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (9125, 18, 'LA PAMPEANA', '6228', 0);
INSERT INTO ubicacion_localidades VALUES (9126, 18, 'LA PAMPITA', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (9127, 18, 'LA PAMPITA  HIDALGO', '6305', 0);
INSERT INTO ubicacion_localidades VALUES (9128, 18, 'LA PASTORIL', '6323', 0);
INSERT INTO ubicacion_localidades VALUES (9129, 18, 'LA PAULINA', '6221', 0);
INSERT INTO ubicacion_localidades VALUES (9130, 18, 'LA PAZ', '6352', 0);
INSERT INTO ubicacion_localidades VALUES (9131, 18, 'LA PENCOSA', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9132, 18, 'LA PERLA', '6352', 0);
INSERT INTO ubicacion_localidades VALUES (9133, 18, 'LA PERLITA', '6352', 0);
INSERT INTO ubicacion_localidades VALUES (9134, 18, 'LA PIEDAD', '6311', 0);
INSERT INTO ubicacion_localidades VALUES (9135, 18, 'LA POMONA', '6214', 0);
INSERT INTO ubicacion_localidades VALUES (9136, 18, 'LA PORTEÃA', '8206', 0);
INSERT INTO ubicacion_localidades VALUES (9137, 18, 'LA PRADERA', '6228', 0);
INSERT INTO ubicacion_localidades VALUES (9138, 18, 'LA PRIMAVERA', '6323', 0);
INSERT INTO ubicacion_localidades VALUES (9139, 18, 'LA PRIMAVERA  MIGUEL RIGLOS', '6301', 0);
INSERT INTO ubicacion_localidades VALUES (9140, 18, 'LA PRIMAVERA  SANTA ROSA', '6300', 0);
INSERT INTO ubicacion_localidades VALUES (9141, 18, 'LA PRIMAVERA CHAMAICO', '6214', 0);
INSERT INTO ubicacion_localidades VALUES (9142, 18, 'LA PRIMERA', '8206', 0);
INSERT INTO ubicacion_localidades VALUES (9143, 18, 'LA PUÃALADA', '6323', 0);
INSERT INTO ubicacion_localidades VALUES (9144, 18, 'LA PUMA', '8206', 0);
INSERT INTO ubicacion_localidades VALUES (9145, 18, 'LA PUMA', '6360', 0);
INSERT INTO ubicacion_localidades VALUES (9146, 18, 'LA PUNA', '6330', 0);
INSERT INTO ubicacion_localidades VALUES (9147, 18, 'LA RAZON', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9148, 18, 'LA RAZON SANTA ISABEL', '6323', 0);
INSERT INTO ubicacion_localidades VALUES (9149, 18, 'LA REBECA', '6330', 0);
INSERT INTO ubicacion_localidades VALUES (9150, 18, 'LA REFORMA VIEJA', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (9151, 18, 'LA RESERVA  ANGUIL', '6326', 0);
INSERT INTO ubicacion_localidades VALUES (9152, 18, 'LA RESERVA IVANOWSKY', '6341', 0);
INSERT INTO ubicacion_localidades VALUES (9153, 18, 'LA ROSA', '8134', 0);
INSERT INTO ubicacion_localidades VALUES (9154, 18, 'LA SARITA', '6305', 0);
INSERT INTO ubicacion_localidades VALUES (9155, 18, 'LA SEGUNDA', '6352', 0);
INSERT INTO ubicacion_localidades VALUES (9156, 18, 'LA SIN NOMBRE', '8203', 0);
INSERT INTO ubicacion_localidades VALUES (9157, 18, 'LA SOLEDAD', '6323', 0);
INSERT INTO ubicacion_localidades VALUES (9158, 18, 'LA SORPRESA', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (9159, 18, 'LA SUERTE', '6354', 0);
INSERT INTO ubicacion_localidades VALUES (9160, 18, 'LA TERESITA', '6361', 0);
INSERT INTO ubicacion_localidades VALUES (9161, 18, 'LA TINAJERA', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9162, 18, 'LA TORERA', '8214', 0);
INSERT INTO ubicacion_localidades VALUES (9163, 18, 'LA TOSCA', '8134', 0);
INSERT INTO ubicacion_localidades VALUES (9164, 18, 'LA TRINIDAD', '6354', 0);
INSERT INTO ubicacion_localidades VALUES (9165, 18, 'LA UNIDA', '6330', 0);
INSERT INTO ubicacion_localidades VALUES (9166, 18, 'LA UNION', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (9167, 18, 'LA UNION', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9168, 18, 'LA VANGUARDIA', '6303', 0);
INSERT INTO ubicacion_localidades VALUES (9169, 18, 'LA VEINTITRES', '6323', 0);
INSERT INTO ubicacion_localidades VALUES (9170, 18, 'LA VERDE', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9171, 18, 'LA VERDE', '6331', 0);
INSERT INTO ubicacion_localidades VALUES (9172, 18, 'LA VERDE  ANGUIL', '6326', 0);
INSERT INTO ubicacion_localidades VALUES (9173, 18, 'LA VICTORIA', '6354', 0);
INSERT INTO ubicacion_localidades VALUES (9174, 18, 'LA VICTORIA', '6221', 0);
INSERT INTO ubicacion_localidades VALUES (9175, 18, 'LA VICTORIA', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (9176, 18, 'LA VIOLETA', '6352', 0);
INSERT INTO ubicacion_localidades VALUES (9177, 18, 'LA VOLUNTAD', '6228', 0);
INSERT INTO ubicacion_localidades VALUES (9178, 18, 'LA ZOTA', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9179, 18, 'LABAL', '6319', 0);
INSERT INTO ubicacion_localidades VALUES (9180, 18, 'LAS ACACIAS', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (9181, 18, 'LAS CHACRAS', '6381', 0);
INSERT INTO ubicacion_localidades VALUES (9182, 18, 'LAS DELICIAS', '6221', 0);
INSERT INTO ubicacion_localidades VALUES (9183, 18, 'LAS DELICIAS', '6214', 0);
INSERT INTO ubicacion_localidades VALUES (9184, 18, 'LAS DOS NACIONES', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (9185, 18, 'LAS FELICITAS', '6305', 0);
INSERT INTO ubicacion_localidades VALUES (9186, 18, 'LAS GAVIOTAS', '6354', 0);
INSERT INTO ubicacion_localidades VALUES (9187, 18, 'LAS MALVINAS', '6300', 0);
INSERT INTO ubicacion_localidades VALUES (9188, 18, 'LAS PIEDRITAS', '6387', 0);
INSERT INTO ubicacion_localidades VALUES (9189, 18, 'LAS QUINTAS', '6311', 0);
INSERT INTO ubicacion_localidades VALUES (9190, 18, 'LAS TRES HERMANAS', '6331', 0);
INSERT INTO ubicacion_localidades VALUES (9191, 18, 'LEGASA', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (9192, 18, 'LEONA REDONDA', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9193, 18, 'LIHUE CALEL', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (9194, 18, 'LIMAY MAHUIDA', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (9195, 18, 'LINDO VER', '6303', 0);
INSERT INTO ubicacion_localidades VALUES (9196, 18, 'LOMA REDONDA', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9197, 18, 'LOMAS DE GATICA', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9198, 18, 'LOMAS OMBU', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9199, 18, 'LONQUIMAY', '6352', 0);
INSERT INTO ubicacion_localidades VALUES (9200, 18, 'LOO CO', '6381', 0);
INSERT INTO ubicacion_localidades VALUES (9201, 18, 'LOS ALAMOS', '6325', 0);
INSERT INTO ubicacion_localidades VALUES (9202, 18, 'LOS ALGARROBOS', '6303', 0);
INSERT INTO ubicacion_localidades VALUES (9203, 18, 'LOS DOS HERMANOS', '6305', 0);
INSERT INTO ubicacion_localidades VALUES (9204, 18, 'LOS MANANTIALES', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9205, 18, 'LOS MORROS', '8134', 0);
INSERT INTO ubicacion_localidades VALUES (9206, 18, 'LOS NOGALES', '6300', 0);
INSERT INTO ubicacion_localidades VALUES (9207, 18, 'LOS OLIVOS', '8203', 0);
INSERT INTO ubicacion_localidades VALUES (9208, 18, 'LOS PIRINEOS', '6315', 0);
INSERT INTO ubicacion_localidades VALUES (9209, 18, 'LOS QUINIENTOS', '6301', 0);
INSERT INTO ubicacion_localidades VALUES (9210, 18, 'LOS TAJAMARES', '6323', 0);
INSERT INTO ubicacion_localidades VALUES (9211, 18, 'LOS TOROS', '6311', 0);
INSERT INTO ubicacion_localidades VALUES (9212, 18, 'LOS TRES POZOS', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9213, 18, 'LOS TURCOS', '6323', 0);
INSERT INTO ubicacion_localidades VALUES (9214, 18, 'LOTE 10', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (9215, 18, 'LOTE 11', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (9216, 18, 'LOTE 11 BERNASCONI', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (9217, 18, 'LOTE 11 ESCUELA 107', '6213', 0);
INSERT INTO ubicacion_localidades VALUES (9218, 18, 'LOTE 12', '6313', 0);
INSERT INTO ubicacion_localidades VALUES (9219, 18, 'LOTE 12', '8204', 0);
INSERT INTO ubicacion_localidades VALUES (9220, 18, 'LOTE 12', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (9221, 18, 'LOTE 13', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (9222, 18, 'LOTE 13 ESCUELA 173', '6313', 0);
INSERT INTO ubicacion_localidades VALUES (9223, 18, 'LOTE 14', '8134', 0);
INSERT INTO ubicacion_localidades VALUES (9224, 18, 'LOTE 15', '6213', 0);
INSERT INTO ubicacion_localidades VALUES (9225, 18, 'LOTE 15 ESCUELA 18', '6387', 0);
INSERT INTO ubicacion_localidades VALUES (9226, 18, 'LOTE 17', '8134', 0);
INSERT INTO ubicacion_localidades VALUES (9227, 18, 'LOTE 17 ESCUELA 121', '8206', 0);
INSERT INTO ubicacion_localidades VALUES (9228, 18, 'LOTE 17 ESCUELA 95', '6380', 0);
INSERT INTO ubicacion_localidades VALUES (9229, 18, 'LOTE 18', '8134', 0);
INSERT INTO ubicacion_localidades VALUES (9230, 18, 'LOTE 18', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (9231, 18, 'LOTE 18 ESCUELA 158', '8206', 0);
INSERT INTO ubicacion_localidades VALUES (9232, 18, 'LOTE 19', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (9233, 18, 'LOTE 19  COLONIA N LEVEN', '8134', 0);
INSERT INTO ubicacion_localidades VALUES (9234, 18, 'LOTE 2 ESCUELA 185', '6380', 0);
INSERT INTO ubicacion_localidades VALUES (9235, 18, 'LOTE 2 LA ELINA', '6203', 0);
INSERT INTO ubicacion_localidades VALUES (9236, 18, 'LOTE 20', '8204', 0);
INSERT INTO ubicacion_localidades VALUES (9237, 18, 'LOTE 20 LA CARLOTA', '6380', 0);
INSERT INTO ubicacion_localidades VALUES (9238, 18, 'LOTE 21', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (9239, 18, 'LOTE 21 COLONIA SANTA ELENA', '6313', 0);
INSERT INTO ubicacion_localidades VALUES (9240, 18, 'LOTE 22', '8134', 0);
INSERT INTO ubicacion_localidades VALUES (9241, 18, 'LOTE 22', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (9242, 18, 'LOTE 22 IPIÃA', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (9243, 18, 'LOTE 23', '8134', 0);
INSERT INTO ubicacion_localidades VALUES (9244, 18, 'LOTE 23 ESCUELA 221', '6313', 0);
INSERT INTO ubicacion_localidades VALUES (9245, 18, 'LOTE 23 ESCUELA 264', '8206', 0);
INSERT INTO ubicacion_localidades VALUES (9246, 18, 'LOTE 24', '8134', 0);
INSERT INTO ubicacion_localidades VALUES (9247, 18, 'LOTE 24 SECCION 1A', '6383', 0);
INSERT INTO ubicacion_localidades VALUES (9248, 18, 'LOTE 25 CONHELO', '6381', 0);
INSERT INTO ubicacion_localidades VALUES (9249, 18, 'LOTE 25 ESCUELA 146', '6381', 0);
INSERT INTO ubicacion_localidades VALUES (9250, 18, 'LOTE 25 ESCUELA 178', '6315', 0);
INSERT INTO ubicacion_localidades VALUES (9251, 18, 'LOTE 3', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (9252, 18, 'LOTE 4', '6387', 0);
INSERT INTO ubicacion_localidades VALUES (9253, 18, 'LOTE 5 CALEUFU  ESC 120', '6205', 0);
INSERT INTO ubicacion_localidades VALUES (9254, 18, 'LOTE 5 LUAN TORO', '6317', 0);
INSERT INTO ubicacion_localidades VALUES (9255, 18, 'LOTE 6', '8134', 0);
INSERT INTO ubicacion_localidades VALUES (9256, 18, 'LOTE 6 ESCUELA 171', '6315', 0);
INSERT INTO ubicacion_localidades VALUES (9257, 18, 'LOTE 7', '8134', 0);
INSERT INTO ubicacion_localidades VALUES (9258, 18, 'LOTE 7 ESCUELA 270', '8206', 0);
INSERT INTO ubicacion_localidades VALUES (9259, 18, 'LOTE 8', '8134', 0);
INSERT INTO ubicacion_localidades VALUES (9260, 18, 'LOTE 8', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (9261, 18, 'LOTE 8  ESCUELA 179', '8206', 0);
INSERT INTO ubicacion_localidades VALUES (9262, 18, 'LOTE 8 ESCUELA 141', '6369', 0);
INSERT INTO ubicacion_localidades VALUES (9263, 18, 'LOTE 8 ESCUELA 179', '6319', 0);
INSERT INTO ubicacion_localidades VALUES (9264, 18, 'LOTE 8 ESCUELA 184', '6380', 0);
INSERT INTO ubicacion_localidades VALUES (9265, 18, 'LOTE 9 ESCUELA 140', '6315', 0);
INSERT INTO ubicacion_localidades VALUES (9266, 18, 'LOVENTUEL', '6317', 0);
INSERT INTO ubicacion_localidades VALUES (9267, 18, 'LUAN TORO', '6317', 0);
INSERT INTO ubicacion_localidades VALUES (9268, 18, 'LUNA', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (9269, 18, 'MACACHIN', '6307', 0);
INSERT INTO ubicacion_localidades VALUES (9270, 18, 'MAISONNAVE', '6212', 0);
INSERT INTO ubicacion_localidades VALUES (9271, 18, 'MALVINAS ARGENTINAS', '6228', 0);
INSERT INTO ubicacion_localidades VALUES (9272, 18, 'MANANTIALES', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9273, 18, 'MARACO', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (9274, 18, 'MARACO CHICO', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (9275, 18, 'MARI MARI', '6333', 0);
INSERT INTO ubicacion_localidades VALUES (9276, 18, 'MARIA', '8204', 0);
INSERT INTO ubicacion_localidades VALUES (9277, 18, 'MARIANO MIRO', '6228', 0);
INSERT INTO ubicacion_localidades VALUES (9278, 18, 'MAURICIO MAYER', '6315', 0);
INSERT INTO ubicacion_localidades VALUES (9279, 18, 'MAYACO', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9280, 18, 'MEDANO BLANCO', '6300', 0);
INSERT INTO ubicacion_localidades VALUES (9281, 18, 'MEDANO COLORADO', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (9282, 18, 'MEDANOS NEGROS', '6323', 0);
INSERT INTO ubicacion_localidades VALUES (9283, 18, 'METILEO', '6367', 0);
INSERT INTO ubicacion_localidades VALUES (9284, 18, 'MIGUEL CANE', '6331', 0);
INSERT INTO ubicacion_localidades VALUES (9285, 18, 'MIGUEL RIGLOS', '6301', 0);
INSERT INTO ubicacion_localidades VALUES (9286, 18, 'MINAS DE SAL', '8206', 0);
INSERT INTO ubicacion_localidades VALUES (9287, 18, 'MINERALES DE LA PAMPA', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (9288, 18, 'MINISTRO ORLANDO', '6367', 0);
INSERT INTO ubicacion_localidades VALUES (9289, 18, 'MOCOVI', '6360', 0);
INSERT INTO ubicacion_localidades VALUES (9290, 18, 'MONTE NIEVAS', '6383', 0);
INSERT INTO ubicacion_localidades VALUES (9291, 18, 'MONTE RALO', '6309', 0);
INSERT INTO ubicacion_localidades VALUES (9292, 18, 'NAHUEL NAPA', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9293, 18, 'NAICO', '6325', 0);
INSERT INTO ubicacion_localidades VALUES (9294, 18, 'NANQUEL HUITRE', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9295, 18, 'NARCISO LEVEN', '8204', 0);
INSERT INTO ubicacion_localidades VALUES (9296, 18, 'NERRE CO', '6303', 0);
INSERT INTO ubicacion_localidades VALUES (9297, 18, 'NICOLAS VERA', '6380', 0);
INSERT INTO ubicacion_localidades VALUES (9298, 18, 'OFICIAL E SEGURA', '6303', 0);
INSERT INTO ubicacion_localidades VALUES (9299, 18, 'OJEDA', '6207', 0);
INSERT INTO ubicacion_localidades VALUES (9300, 18, 'OJO DE AGUA', '6305', 0);
INSERT INTO ubicacion_localidades VALUES (9301, 18, 'PARERA', '6213', 0);
INSERT INTO ubicacion_localidades VALUES (9302, 18, 'PARQUE LURO', '6325', 0);
INSERT INTO ubicacion_localidades VALUES (9303, 18, 'PASO DE LOS ALGARROBOS', '6323', 0);
INSERT INTO ubicacion_localidades VALUES (9304, 18, 'PASO DE LOS PUNTANOS', '6323', 0);
INSERT INTO ubicacion_localidades VALUES (9305, 18, 'PASO LA BALSA', '8307', 0);
INSERT INTO ubicacion_localidades VALUES (9306, 18, 'PASO LA RAZON', '6323', 0);
INSERT INTO ubicacion_localidades VALUES (9307, 18, 'PAVON', '6331', 0);
INSERT INTO ubicacion_localidades VALUES (9308, 18, 'PERU', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (9309, 18, 'PICHE CONA LAUQUEN', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (9310, 18, 'PICHI  HUILCO', '6303', 0);
INSERT INTO ubicacion_localidades VALUES (9311, 18, 'PICHI  HUINCA', '6385', 0);
INSERT INTO ubicacion_localidades VALUES (9312, 18, 'PICHI MERICO', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9313, 18, 'POITAGUE', '6319', 0);
INSERT INTO ubicacion_localidades VALUES (9314, 18, 'PUEBLO ALASSA', '6208', 0);
INSERT INTO ubicacion_localidades VALUES (9315, 18, 'PUEBLO QUINTANA', '6352', 0);
INSERT INTO ubicacion_localidades VALUES (9316, 18, 'PUELCHES', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (9317, 18, 'PUELEN', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (9318, 18, 'QUEHUE', '8203', 0);
INSERT INTO ubicacion_localidades VALUES (9319, 18, 'QUEMU QUEMU', '6333', 0);
INSERT INTO ubicacion_localidades VALUES (9320, 18, 'QUETREQUEN', '6212', 0);
INSERT INTO ubicacion_localidades VALUES (9321, 18, 'QUIÃI MALAL', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (9322, 18, 'QUINTANA', '6352', 0);
INSERT INTO ubicacion_localidades VALUES (9323, 18, 'RAMON QUINTAS', '6303', 0);
INSERT INTO ubicacion_localidades VALUES (9324, 18, 'RAMON SEGUNDO', '6228', 0);
INSERT INTO ubicacion_localidades VALUES (9325, 18, 'RANCUL', '6214', 0);
INSERT INTO ubicacion_localidades VALUES (9326, 18, 'REALICO', '6200', 0);
INSERT INTO ubicacion_localidades VALUES (9327, 18, 'RELMO', '6331', 0);
INSERT INTO ubicacion_localidades VALUES (9328, 18, 'REMECO', '6311', 0);
INSERT INTO ubicacion_localidades VALUES (9329, 18, 'REMECO', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (9330, 18, 'RICARDO LAVALLE', '5582', 0);
INSERT INTO ubicacion_localidades VALUES (9331, 18, 'ROLON', '6305', 0);
INSERT INTO ubicacion_localidades VALUES (9332, 18, 'RUCAHUE', '6331', 0);
INSERT INTO ubicacion_localidades VALUES (9333, 18, 'RUCANELO', '6381', 0);
INSERT INTO ubicacion_localidades VALUES (9334, 18, 'SALINAS', '8134', 0);
INSERT INTO ubicacion_localidades VALUES (9335, 18, 'SALINAS GRANDES  ANZOATEGUI', '8138', 0);
INSERT INTO ubicacion_localidades VALUES (9336, 18, 'SALINAS GRANDES HIDALGO', '6305', 0);
INSERT INTO ubicacion_localidades VALUES (9337, 18, 'SALINAS MARI MANUEL', '6309', 0);
INSERT INTO ubicacion_localidades VALUES (9338, 18, 'SAN ALBERTO', '6331', 0);
INSERT INTO ubicacion_localidades VALUES (9339, 18, 'SAN ANDRES', '6354', 0);
INSERT INTO ubicacion_localidades VALUES (9340, 18, 'SAN ANTONIO', '8203', 0);
INSERT INTO ubicacion_localidades VALUES (9341, 18, 'SAN AQUILINO', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (9342, 18, 'SAN BASILIO', '6214', 0);
INSERT INTO ubicacion_localidades VALUES (9343, 18, 'SAN BENITO', '6331', 0);
INSERT INTO ubicacion_localidades VALUES (9344, 18, 'SAN BERNARDO', '8204', 0);
INSERT INTO ubicacion_localidades VALUES (9345, 18, 'SAN CARLOS', '6326', 0);
INSERT INTO ubicacion_localidades VALUES (9346, 18, 'SAN DIEGO', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (9347, 18, 'SAN EDUARDO', '6330', 0);
INSERT INTO ubicacion_localidades VALUES (9348, 18, 'SAN EMILIO', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9349, 18, 'SAN ERNESTO', '8203', 0);
INSERT INTO ubicacion_localidades VALUES (9350, 18, 'SAN FELIPE', '6305', 0);
INSERT INTO ubicacion_localidades VALUES (9351, 18, 'SAN FERNANDO', '8204', 0);
INSERT INTO ubicacion_localidades VALUES (9352, 18, 'SAN FRANCISCO', '6319', 0);
INSERT INTO ubicacion_localidades VALUES (9353, 18, 'SAN FRANCISCO DE LA RAMADA', '6323', 0);
INSERT INTO ubicacion_localidades VALUES (9354, 18, 'SAN HILARIO', '6212', 0);
INSERT INTO ubicacion_localidades VALUES (9355, 18, 'SAN HUMBERTO', '6325', 0);
INSERT INTO ubicacion_localidades VALUES (9356, 18, 'SAN IGNACIO', '6360', 0);
INSERT INTO ubicacion_localidades VALUES (9357, 18, 'SAN ILDEFONSO', '6365', 0);
INSERT INTO ubicacion_localidades VALUES (9358, 18, 'SAN JOAQUIN', '6330', 0);
INSERT INTO ubicacion_localidades VALUES (9359, 18, 'SAN JOAQUIN', '6360', 0);
INSERT INTO ubicacion_localidades VALUES (9360, 18, 'SAN JOAQUIN METILEO', '6367', 0);
INSERT INTO ubicacion_localidades VALUES (9361, 18, 'SAN JOSE', '6354', 0);
INSERT INTO ubicacion_localidades VALUES (9362, 18, 'SAN JOSE', '6228', 0);
INSERT INTO ubicacion_localidades VALUES (9363, 18, 'SAN JOSE', '6313', 0);
INSERT INTO ubicacion_localidades VALUES (9364, 18, 'SAN JOSE', '8204', 0);
INSERT INTO ubicacion_localidades VALUES (9365, 18, 'SAN JOSE', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9366, 18, 'SAN JOSE', '6360', 0);
INSERT INTO ubicacion_localidades VALUES (9367, 18, 'SAN JOSE  ANGUIL', '6326', 0);
INSERT INTO ubicacion_localidades VALUES (9368, 18, 'SAN JUAN', '6354', 0);
INSERT INTO ubicacion_localidades VALUES (9369, 18, 'SAN JUAN', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (9370, 18, 'SAN JUAN SIMSON', '6212', 0);
INSERT INTO ubicacion_localidades VALUES (9371, 18, 'SAN JUSTO', '6330', 0);
INSERT INTO ubicacion_localidades VALUES (9372, 18, 'SAN MANUEL', '6352', 0);
INSERT INTO ubicacion_localidades VALUES (9373, 18, 'SAN MARCELO', '6214', 0);
INSERT INTO ubicacion_localidades VALUES (9374, 18, 'SAN MIGUEL', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (9375, 18, 'SAN MIGUEL', '6331', 0);
INSERT INTO ubicacion_localidades VALUES (9376, 18, 'SAN PEDRO', '6330', 0);
INSERT INTO ubicacion_localidades VALUES (9377, 18, 'SAN PEDRO  ROLON', '6305', 0);
INSERT INTO ubicacion_localidades VALUES (9378, 18, 'SAN RAMON', '6383', 0);
INSERT INTO ubicacion_localidades VALUES (9379, 18, 'SAN ROBERTO', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (9380, 18, 'SAN SALVADOR', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (9381, 18, 'SAN SIMON', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (9382, 18, 'SAN URBANO', '6228', 0);
INSERT INTO ubicacion_localidades VALUES (9383, 18, 'SANTA ANA', '6309', 0);
INSERT INTO ubicacion_localidades VALUES (9384, 18, 'SANTA AURELIA', '6239', 0);
INSERT INTO ubicacion_localidades VALUES (9385, 18, 'SANTA CATALINA', '6365', 0);
INSERT INTO ubicacion_localidades VALUES (9386, 18, 'SANTA CLARA', '8214', 0);
INSERT INTO ubicacion_localidades VALUES (9387, 18, 'SANTA ELENA', '6360', 0);
INSERT INTO ubicacion_localidades VALUES (9388, 18, 'SANTA ELENA', '8201', 0);
INSERT INTO ubicacion_localidades VALUES (9389, 18, 'SANTA ELVIRA', '6333', 0);
INSERT INTO ubicacion_localidades VALUES (9390, 18, 'SANTA FELICITAS', '6220', 0);
INSERT INTO ubicacion_localidades VALUES (9391, 18, 'SANTA GRACIA', '6212', 0);
INSERT INTO ubicacion_localidades VALUES (9392, 18, 'SANTA INES', '6360', 0);
INSERT INTO ubicacion_localidades VALUES (9393, 18, 'SANTA ISABEL', '6323', 0);
INSERT INTO ubicacion_localidades VALUES (9394, 18, 'SANTA MARIA', '8214', 0);
INSERT INTO ubicacion_localidades VALUES (9395, 18, 'SANTA ROSA', '6300', 0);
INSERT INTO ubicacion_localidades VALUES (9396, 18, 'SANTA STELLA', '6305', 0);
INSERT INTO ubicacion_localidades VALUES (9397, 18, 'SANTIAGO ORELLANO', '6325', 0);
INSERT INTO ubicacion_localidades VALUES (9398, 18, 'SANTO DOMINGO', '8203', 0);
INSERT INTO ubicacion_localidades VALUES (9399, 18, 'SANTO TOMAS', '6307', 0);
INSERT INTO ubicacion_localidades VALUES (9400, 18, 'SARAH', '6228', 0);
INSERT INTO ubicacion_localidades VALUES (9401, 18, 'SECCION PRIMERA CONHELLO', '6383', 0);
INSERT INTO ubicacion_localidades VALUES (9402, 18, 'SIERRAS DE LIHUEL CALEL', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (9403, 18, 'SOL DE MAYO', '6333', 0);
INSERT INTO ubicacion_localidades VALUES (9404, 18, 'SPELUZZI', '6365', 0);
INSERT INTO ubicacion_localidades VALUES (9405, 18, 'TA HUILCO', '6303', 0);
INSERT INTO ubicacion_localidades VALUES (9406, 18, 'TELEN', '6321', 0);
INSERT INTO ubicacion_localidades VALUES (9407, 18, 'TOAY', '6303', 0);
INSERT INTO ubicacion_localidades VALUES (9408, 18, 'TOMAS M DE ANCHORENA', '6301', 0);
INSERT INTO ubicacion_localidades VALUES (9409, 18, 'TRAICO', '8206', 0);
INSERT INTO ubicacion_localidades VALUES (9410, 18, 'TRAICO GRANDE', '8208', 0);
INSERT INTO ubicacion_localidades VALUES (9411, 18, 'TREBOLARES', '6361', 0);
INSERT INTO ubicacion_localidades VALUES (9412, 18, 'TRENEL', '6369', 0);
INSERT INTO ubicacion_localidades VALUES (9413, 18, 'TRES BOTONES', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (9414, 18, 'TRES HERMANOS  MACACHIN', '6307', 0);
INSERT INTO ubicacion_localidades VALUES (9415, 18, 'TRES HERMANOS QUETREQUEN', '6212', 0);
INSERT INTO ubicacion_localidades VALUES (9416, 18, 'TRES LAGUNAS', '6228', 0);
INSERT INTO ubicacion_localidades VALUES (9417, 18, 'TRES NACIONES', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (9418, 18, 'TRIBULUCI', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (9419, 18, 'TRILI', '6333', 0);
INSERT INTO ubicacion_localidades VALUES (9420, 18, 'TRUBULUCO', '8212', 0);
INSERT INTO ubicacion_localidades VALUES (9421, 18, 'TTE GRAL EMILIO MITRE', '6381', 0);
INSERT INTO ubicacion_localidades VALUES (9422, 18, 'UNANUE', '8214', 0);
INSERT INTO ubicacion_localidades VALUES (9423, 18, 'URIBURU', '6354', 0);
INSERT INTO ubicacion_localidades VALUES (9424, 18, 'UTRACAN', '8203', 0);
INSERT INTO ubicacion_localidades VALUES (9425, 18, 'VALLE ARGENTINO', '6307', 0);
INSERT INTO ubicacion_localidades VALUES (9426, 18, 'VALLE ARGENTINO', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (9427, 18, 'VALLE DAZA', '8200', 0);
INSERT INTO ubicacion_localidades VALUES (9428, 18, 'VERTIZ', '6365', 0);
INSERT INTO ubicacion_localidades VALUES (9429, 18, 'VICTORICA', '6319', 0);
INSERT INTO ubicacion_localidades VALUES (9430, 18, 'VILLA ALBA', '8206', 0);
INSERT INTO ubicacion_localidades VALUES (9431, 18, 'VILLA MENCUELLE', '8208', 0);
INSERT INTO ubicacion_localidades VALUES (9432, 18, 'VILLA MIRASOL', '6315', 0);
INSERT INTO ubicacion_localidades VALUES (9433, 18, 'VILLA SAN JOSE', '6315', 0);
INSERT INTO ubicacion_localidades VALUES (9434, 18, 'VISTA ALEGRE', '6323', 0);
INSERT INTO ubicacion_localidades VALUES (9435, 18, 'WINIFREDA', '6313', 0);
INSERT INTO ubicacion_localidades VALUES (9436, 18, 'ZONA RURAL', '6331', 0);
INSERT INTO ubicacion_localidades VALUES (9437, 18, 'ZONA RURAL', '6369', 0);
INSERT INTO ubicacion_localidades VALUES (9438, 18, 'ZONA RURAL  METILEO', '6380', 0);
INSERT INTO ubicacion_localidades VALUES (9439, 18, 'ZONA RURAL DE MIRASOL', '6315', 0);
INSERT INTO ubicacion_localidades VALUES (9440, 18, 'ZONA RURAL DE VERTIZ', '6365', 0);
INSERT INTO ubicacion_localidades VALUES (9441, 18, 'ZONA RURAL DORILA', '6365', 0);
INSERT INTO ubicacion_localidades VALUES (9442, 18, 'ZONA URBANA NORTE', '6380', 0);
INSERT INTO ubicacion_localidades VALUES (9443, 13, '9 DE JULIO', '5417', 0);
INSERT INTO ubicacion_localidades VALUES (9444, 13, 'ACERILLOS', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9445, 13, 'ADAN QUIROGA', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (9446, 13, 'AEROPUERTO SAN JUAN', '5417', 0);
INSERT INTO ubicacion_localidades VALUES (9447, 13, 'AGUA CERCADA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9448, 13, 'AGUA DE LA ZANJA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9449, 13, 'AGUA DE LA ZORRA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9450, 13, 'AGUA DE LOS CABALLOS', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9451, 13, 'AGUA ESCONDIDA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9452, 13, 'AGUA Y ENERGIA', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9453, 13, 'AGUADA DE LA PEÃA', '5461', 0);
INSERT INTO ubicacion_localidades VALUES (9454, 13, 'AGUADITAS', '5461', 0);
INSERT INTO ubicacion_localidades VALUES (9455, 13, 'AGUADITAS DEL RIO JACHAL', '5461', 0);
INSERT INTO ubicacion_localidades VALUES (9456, 13, 'AGUANGO', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (9457, 13, 'AGUAS DEL PAJARO', '5461', 0);
INSERT INTO ubicacion_localidades VALUES (9458, 13, 'ALAMITO', '5415', 0);
INSERT INTO ubicacion_localidades VALUES (9459, 13, 'ALBARRACIN', '5425', 0);
INSERT INTO ubicacion_localidades VALUES (9460, 13, 'ALCAUCHA', '5461', 0);
INSERT INTO ubicacion_localidades VALUES (9461, 13, 'ALGARROBO DEL CURA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9462, 13, 'ALGARROBO GRANDE', '5435', 0);
INSERT INTO ubicacion_localidades VALUES (9463, 13, 'ALGARROBO VERDE', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (9464, 13, 'ALTO DE SIERRA', '5438', 0);
INSERT INTO ubicacion_localidades VALUES (9465, 13, 'ALTO HUACO', '5463', 0);
INSERT INTO ubicacion_localidades VALUES (9466, 13, 'AMARFIL', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (9467, 13, 'AMBAS PUNTILLAS', '5442', 0);
INSERT INTO ubicacion_localidades VALUES (9468, 13, 'AMPACAMA', '5444', 0);
INSERT INTO ubicacion_localidades VALUES (9469, 13, 'ANGACO NORTE', '5415', 0);
INSERT INTO ubicacion_localidades VALUES (9470, 13, 'ANGACO SUD', '5417', 0);
INSERT INTO ubicacion_localidades VALUES (9471, 13, 'ANGUALASTO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9472, 13, 'APEADERO GUANACACHE', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (9473, 13, 'APEADERO LAS CHIMBAS', '5413', 0);
INSERT INTO ubicacion_localidades VALUES (9474, 13, 'APEADERO QUIROGA', '5427', 0);
INSERT INTO ubicacion_localidades VALUES (9475, 13, 'ARREQUINTIN', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9476, 13, 'ASCHICHUSCA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9477, 13, 'ASTICA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9478, 13, 'AURORA', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (9479, 13, 'AZUCARERA DE CUYO', '5435', 0);
INSERT INTO ubicacion_localidades VALUES (9480, 13, 'BAÃOS CENTENARIO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9481, 13, 'BAÃOS DE LA LAJA', '5419', 0);
INSERT INTO ubicacion_localidades VALUES (9482, 13, 'BAÃOS DE LOS DESPOBLADOS', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9483, 13, 'BAÃOS DE SAN CRISPIN', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9484, 13, 'BAÃOS DEL CERRO', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (9485, 13, 'BAÃOS DEL SALADO', '5419', 0);
INSERT INTO ubicacion_localidades VALUES (9486, 13, 'BAÃOS PISMANTA', '5465', 0);
INSERT INTO ubicacion_localidades VALUES (9487, 13, 'BALDE DE LEYES', '5446', 0);
INSERT INTO ubicacion_localidades VALUES (9488, 13, 'BALDE DEL LUCERO', '5446', 0);
INSERT INTO ubicacion_localidades VALUES (9489, 13, 'BALDE DEL NORTE', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (9490, 13, 'BALDE DEL ROSARIO', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (9491, 13, 'BALDE PLUMERITO', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9492, 13, 'BALDE SAN CARLOS', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9493, 13, 'BALDECITO', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (9494, 13, 'BALDECITO DEL MORADO', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (9495, 13, 'BALDES DE LA CHILCA', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (9496, 13, 'BALDES DEL SUD', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (9497, 13, 'BALDES DEL TARABAY', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9498, 13, 'BARRANCA COLORADA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9499, 13, 'BARRANCA DE LOS LOROS', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9500, 13, 'BARRANCAS BLANCAS', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9501, 13, 'BARREAL', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9502, 13, 'BARREALES', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9503, 13, 'BARREALITO', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9504, 13, 'BARRIALITOS', '5403', 0);
INSERT INTO ubicacion_localidades VALUES (9505, 13, 'BARRIO AGUA Y ENERGIA', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (9506, 13, 'BARRIO COLON', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (9507, 13, 'BARRIO GRAFFIGNA', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (9508, 13, 'BARRIO RAWSON', '5425', 0);
INSERT INTO ubicacion_localidades VALUES (9509, 13, 'BARRIO SANTA BARBARA', '5427', 0);
INSERT INTO ubicacion_localidades VALUES (9510, 13, 'BELGRANO', '5439', 0);
INSERT INTO ubicacion_localidades VALUES (9511, 13, 'BELLA VISTA', '5403', 0);
INSERT INTO ubicacion_localidades VALUES (9512, 13, 'BELLA VISTA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9513, 13, 'BELLA VISTA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9514, 13, 'BERMEJO', '5444', 0);
INSERT INTO ubicacion_localidades VALUES (9515, 13, 'BOCA DE LA QUEBRADA', '5461', 0);
INSERT INTO ubicacion_localidades VALUES (9516, 13, 'BODEGA GRAFFIGNA', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (9517, 13, 'BODEGA SAN ANTONIO', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (9518, 13, 'BUENA ESPERANZA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9519, 13, 'CAÃADA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9520, 13, 'CAÃADA DE LAGUNA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9521, 13, 'CAÃADA DEL POZO', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9522, 13, 'CAÃADA HONDA', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (9523, 13, 'CABECERA DEL BARRIAL', '5403', 0);
INSERT INTO ubicacion_localidades VALUES (9524, 13, 'CABEZA DEL TORO', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (9525, 13, 'CACHO ANCHO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9526, 13, 'CAJON DE LOS TAMBILLOS', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9527, 13, 'CALIBAR', '5442', 0);
INSERT INTO ubicacion_localidades VALUES (9528, 13, 'CALINGASTA', '5403', 0);
INSERT INTO ubicacion_localidades VALUES (9529, 13, 'CALLE AGUILERAS', '5415', 0);
INSERT INTO ubicacion_localidades VALUES (9530, 13, 'CALLE LARGA', '5439', 0);
INSERT INTO ubicacion_localidades VALUES (9531, 13, 'CALLE NACIONAL', '5415', 0);
INSERT INTO ubicacion_localidades VALUES (9532, 13, 'CALLECITA', '5439', 0);
INSERT INTO ubicacion_localidades VALUES (9533, 13, 'CALLECITA', '5438', 0);
INSERT INTO ubicacion_localidades VALUES (9534, 13, 'CAMP D P V LA CIENAGA', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (9535, 13, 'CAMPANARIO NUEVO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9536, 13, 'CAMPO AFUERA', '5419', 0);
INSERT INTO ubicacion_localidades VALUES (9537, 13, 'CAMPO DE BATALLA', '5435', 0);
INSERT INTO ubicacion_localidades VALUES (9538, 13, 'CAMPO DEL LEONCITO', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9539, 13, 'CAMPO LAS LIEBRES', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9540, 13, 'CAMPO LOS POZOS', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9541, 13, 'CAPITAN LAZO', '5423', 0);
INSERT INTO ubicacion_localidades VALUES (9542, 13, 'CARBOMETAL', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (9543, 13, 'CARPINTERIA', '5435', 0);
INSERT INTO ubicacion_localidades VALUES (9544, 13, 'CARRIZALITO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9545, 13, 'CASA DE JAVIER', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9546, 13, 'CASA VIEJA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9547, 13, 'CASTAÃO', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (9548, 13, 'CASTAÃO NUEVO', '5403', 0);
INSERT INTO ubicacion_localidades VALUES (9549, 13, 'CASTAÃO VIEJO', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9550, 13, 'CAUCETE', '5442', 0);
INSERT INTO ubicacion_localidades VALUES (9551, 13, 'CENTRO AVIACION CIVIL SAN JUAN', '5427', 0);
INSERT INTO ubicacion_localidades VALUES (9552, 13, 'CERRO  D  L  BAÃITOS', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9553, 13, 'CERRO AGUADITAS', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (9554, 13, 'CERRO AGUILA', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (9555, 13, 'CERRO ALTO DEL DESCUBRIMIENTO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9556, 13, 'CERRO AMARILLO', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9557, 13, 'CERRO AMARILLO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9558, 13, 'CERRO ASILAN', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9559, 13, 'CERRO ASPERO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9560, 13, 'CERRO BAYO', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9561, 13, 'CERRO BLANCO', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9562, 13, 'CERRO BLANCO', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (9563, 13, 'CERRO BOLA', '5419', 0);
INSERT INTO ubicacion_localidades VALUES (9564, 13, 'CERRO BOLEADORA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9565, 13, 'CERRO BONETE', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9566, 13, 'CERRO BRAMADERO', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9567, 13, 'CERRO BRAVO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9568, 13, 'CERRO CABALLO ANCA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9569, 13, 'CERRO CABALLO BAYO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9570, 13, 'CERRO CASA DE PIEDRA', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (9571, 13, 'CERRO CHIQUERO', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9572, 13, 'CERRO COLORADO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9573, 13, 'CERRO COLORADO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9574, 13, 'CERRO CORTADERA', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9575, 13, 'CERRO CORTADERA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9576, 13, 'CERRO DE CONCONTA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9577, 13, 'CERRO DE DOLORES', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9578, 13, 'CERRO DE LA CUESTA DEL VIENTO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9579, 13, 'CERRO DE LA SEPULTURA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9580, 13, 'CERRO DE LAS VACAS', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9581, 13, 'CERRO DE LOS BURROS', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9582, 13, 'CERRO DE LOS BURROS', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (9583, 13, 'CERRO DE LOS CABALLOS', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9584, 13, 'CERRO DE LOS POZOS', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9585, 13, 'CERRO DEL AGUA DE LAS VACAS', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9586, 13, 'CERRO DEL ALUMBRE', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9587, 13, 'CERRO DEL CACHIYUYAL', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9588, 13, 'CERRO DEL COQUIMBITO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9589, 13, 'CERRO DEL GUANACO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9590, 13, 'CERRO DEL MEDIO', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (9591, 13, 'CERRO DEL SALADO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9592, 13, 'CERRO DEL TOME', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9593, 13, 'CERRO DIVOSADERO', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (9594, 13, 'CERRO EL BRONCE', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9595, 13, 'CERRO EL CEPO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9596, 13, 'CERRO EL DURAZNO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9597, 13, 'CERRO EL FRANCES', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9598, 13, 'CERRO ESPANTAJO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9599, 13, 'CERRO GRANDE', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9600, 13, 'CERRO GUANAQUERO', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9601, 13, 'CERRO HEDIONDO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9602, 13, 'CERRO HEDIONDO', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (9603, 13, 'CERRO HORNITO', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9604, 13, 'CERRO IMAN', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9605, 13, 'CERRO IMAN', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9606, 13, 'CERRO INFIERNILLO', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (9607, 13, 'CERRO JAGUEL', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (9608, 13, 'CERRO JOAQUIN', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9609, 13, 'CERRO LA BOLSA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9610, 13, 'CERRO LA CAÃADA AMARILLA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9611, 13, 'CERRO LA CIENAGA', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (9612, 13, 'CERRO LA COLORADA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9613, 13, 'CERRO LA FLECHA', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (9614, 13, 'CERRO LA FORTUNA', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9615, 13, 'CERRO LA JARILLA', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (9616, 13, 'CERRO LA ORTIGA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9617, 13, 'CERRO LA RINCONADA', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (9618, 13, 'CERRO LA VENTANITA', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (9619, 13, 'CERRO LAGUNITA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9620, 13, 'CERRO LAJITAS', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9621, 13, 'CERRO LAS BARRANCAS', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (9622, 13, 'CERRO LAS MULAS', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9623, 13, 'CERRO LAS MULITAS', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9624, 13, 'CERRO LAS PLACETAS', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (9625, 13, 'CERRO LAS RAICES', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9626, 13, 'CERRO LAS YEGUAS', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9627, 13, 'CERRO LAVADEROS', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9628, 13, 'CERRO LOS MOGOTES', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9629, 13, 'CERRO LOS PATOS', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9630, 13, 'CERRO LOS POZOS', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9631, 13, 'CERRO LOS POZOS', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (9632, 13, 'CERRO MERCEDARIO', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9633, 13, 'CERRO MUDADERO', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9634, 13, 'CERRO NEGRO', '5465', 0);
INSERT INTO ubicacion_localidades VALUES (9635, 13, 'CERRO NEGRO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9636, 13, 'CERRO NEGRO', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (9637, 13, 'CERRO NEGRO DE CHITA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9638, 13, 'CERRO NEGRO DEL CORRAL', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9639, 13, 'CERRO NICO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9640, 13, 'CERRO OCUCAROS', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9641, 13, 'CERRO PACHACO', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (9642, 13, 'CERRO PANTEON', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9643, 13, 'CERRO PATA DE INDIO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9644, 13, 'CERRO PICHEREGUAS', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9645, 13, 'CERRO PINTADO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9646, 13, 'CERRO PIRCAS', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (9647, 13, 'CERRO POTRERITO DE AGUA BLANCO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9648, 13, 'CERRO POTRERO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9649, 13, 'CERRO PUNTUDO', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9650, 13, 'CERRO RIQUILIPONCHE', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (9651, 13, 'CERRO SANTA ROSA', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (9652, 13, 'CERRO SASITO', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (9653, 13, 'CERRO SENDA AZUL', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9654, 13, 'CERRO SILVIO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9655, 13, 'CERRO SILVO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9656, 13, 'CERRO TAMBOLAR', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (9657, 13, 'CERRO TAMBOLAR', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (9658, 13, 'CERRO TIGRE', '5442', 0);
INSERT INTO ubicacion_localidades VALUES (9659, 13, 'CERRO TRES MOGOTES', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (9660, 13, 'CERRO TRES PUNTAS', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9661, 13, 'CERRO VILLA LONCITO', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (9662, 13, 'CERRO VILLICUN', '5419', 0);
INSERT INTO ubicacion_localidades VALUES (9663, 13, 'CHAÃAR PINTADO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9664, 13, 'CHAÃAR SECO', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (9665, 13, 'CHAMPONES', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9666, 13, 'CHANCHOS', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9667, 13, 'CHAPARRO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9668, 13, 'CHEPICAL', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9669, 13, 'CHICA NEGRA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9670, 13, 'CHIGUA DE ABAJO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9671, 13, 'CHIMBAS', '5413', 0);
INSERT INTO ubicacion_localidades VALUES (9672, 13, 'CHISNASCO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9673, 13, 'CHUCUMA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9674, 13, 'CIENAGA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9675, 13, 'CIENAGUILLOS', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9676, 13, 'CIENAGUITA', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (9677, 13, 'CIENAGUITA', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (9678, 13, 'COCHAGUAL', '5435', 0);
INSERT INTO ubicacion_localidades VALUES (9679, 13, 'COLANGUIL', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9680, 13, 'COLANQUI', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9681, 13, 'COLL', '5438', 0);
INSERT INTO ubicacion_localidades VALUES (9682, 13, 'COLOLA', '5465', 0);
INSERT INTO ubicacion_localidades VALUES (9683, 13, 'COLON', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (9684, 13, 'COLONIA CANTONI', '5427', 0);
INSERT INTO ubicacion_localidades VALUES (9685, 13, 'COLONIA CASTRO PADIN', '5427', 0);
INSERT INTO ubicacion_localidades VALUES (9686, 13, 'COLONIA CENTENARIO', '5421', 0);
INSERT INTO ubicacion_localidades VALUES (9687, 13, 'COLONIA EL MOLINO', '5425', 0);
INSERT INTO ubicacion_localidades VALUES (9688, 13, 'COLONIA FIORITO', '5417', 0);
INSERT INTO ubicacion_localidades VALUES (9689, 13, 'COLONIA FIORITO', '5435', 0);
INSERT INTO ubicacion_localidades VALUES (9690, 13, 'COLONIA FISCAL SARMIENTO', '5435', 0);
INSERT INTO ubicacion_localidades VALUES (9691, 13, 'COLONIA FLORIDA', '5425', 0);
INSERT INTO ubicacion_localidades VALUES (9692, 13, 'COLONIA GUTIERREZ', '5438', 0);
INSERT INTO ubicacion_localidades VALUES (9693, 13, 'COLONIA JUAN SOLARI', '5425', 0);
INSERT INTO ubicacion_localidades VALUES (9694, 13, 'COLONIA MOYA', '5427', 0);
INSERT INTO ubicacion_localidades VALUES (9695, 13, 'COLONIA RICHET', '5438', 0);
INSERT INTO ubicacion_localidades VALUES (9696, 13, 'COLONIA ROCA', '5427', 0);
INSERT INTO ubicacion_localidades VALUES (9697, 13, 'COLONIA RODAS', '5421', 0);
INSERT INTO ubicacion_localidades VALUES (9698, 13, 'COLONIA RODRIGUEZ ZAVALLA', '5427', 0);
INSERT INTO ubicacion_localidades VALUES (9699, 13, 'COLONIA RODRIGUEZ ZAVALLA', '5425', 0);
INSERT INTO ubicacion_localidades VALUES (9700, 13, 'COLONIA SAN ANTONIO', '5435', 0);
INSERT INTO ubicacion_localidades VALUES (9701, 13, 'COLONIA YORNER', '5425', 0);
INSERT INTO ubicacion_localidades VALUES (9702, 13, 'COLONIA ZABALA', '5425', 0);
INSERT INTO ubicacion_localidades VALUES (9703, 13, 'COLONIA ZAPATA', '5436', 0);
INSERT INTO ubicacion_localidades VALUES (9704, 13, 'COLONIA ZAPATA', '5436', 0);
INSERT INTO ubicacion_localidades VALUES (9705, 13, 'CONCOTA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9706, 13, 'CONDOR MUERTO', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9707, 13, 'CONTEGRAND', '5421', 0);
INSERT INTO ubicacion_localidades VALUES (9708, 13, 'CORRAL DE PIRCA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9709, 13, 'COYON', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (9710, 13, 'CRUZ DE PIEDRA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9711, 13, 'CRUZ DE SAN PEDRO', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (9712, 13, 'CUATRO ESQUINAS', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (9713, 13, 'CUCHILLAZO', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9714, 13, 'CUESTA VIEJO', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9715, 13, 'CULEBRA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9716, 13, 'CUMIYANGO', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (9717, 13, 'CUYO', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (9718, 13, 'DESAMPARADOS', '5400', 0);
INSERT INTO ubicacion_localidades VALUES (9719, 13, 'DIAZ VELEZ', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (9720, 13, 'DIAZ VELEZ', '5400', 0);
INSERT INTO ubicacion_localidades VALUES (9721, 13, 'DIFUNTA CORREA', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (9722, 13, 'DIQUE CAUQUENES', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9723, 13, 'DIQUE LAS CRUCECITAS', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (9724, 13, 'DIQUE SOLDANO', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (9725, 13, 'DIQUE TOMA', '5407', 0);
INSERT INTO ubicacion_localidades VALUES (9726, 13, 'DIVISADERO', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (9727, 13, 'DIVISADERO DE LA MUJER HELADA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9728, 13, 'DIVISORIA', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (9729, 13, 'DOMINGO DE ORO', '5415', 0);
INSERT INTO ubicacion_localidades VALUES (9730, 13, 'DOS ACEQUIAS', '5439', 0);
INSERT INTO ubicacion_localidades VALUES (9731, 13, 'DOS MOJONES', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9732, 13, 'DOS PUENTES', '5419', 0);
INSERT INTO ubicacion_localidades VALUES (9733, 13, 'EL ABANICO', '5429', 0);
INSERT INTO ubicacion_localidades VALUES (9734, 13, 'EL BALDE', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (9735, 13, 'EL BARRIALITO', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9736, 13, 'EL BOSQUE', '5415', 0);
INSERT INTO ubicacion_localidades VALUES (9737, 13, 'EL BUEN RETIRO', '5461', 0);
INSERT INTO ubicacion_localidades VALUES (9738, 13, 'EL CARRIZAL', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9739, 13, 'EL CHAÃAR', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (9740, 13, 'EL CHACRERO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9741, 13, 'EL CHAMIZUDO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9742, 13, 'EL CHILOTE', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (9743, 13, 'EL CHINGUILLO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9744, 13, 'EL CHUPINO', '5446', 0);
INSERT INTO ubicacion_localidades VALUES (9745, 13, 'EL CORRALITO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9746, 13, 'EL FICAL', '5461', 0);
INSERT INTO ubicacion_localidades VALUES (9747, 13, 'EL FUERTE', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (9748, 13, 'EL GIGANTILLO', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9749, 13, 'EL INFIERNO', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (9750, 13, 'EL JABONCITO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9751, 13, 'EL LECHUZO', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9752, 13, 'EL LEONCITO', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9753, 13, 'EL MEDANITO', '5400', 0);
INSERT INTO ubicacion_localidades VALUES (9754, 13, 'EL MEDANO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9755, 13, 'EL MOGOTE CHIMBAS', '5413', 0);
INSERT INTO ubicacion_localidades VALUES (9756, 13, 'EL MOLINO', '5425', 0);
INSERT INTO ubicacion_localidades VALUES (9757, 13, 'EL PLUMERITO', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9758, 13, 'EL POZO DEL 20', '5442', 0);
INSERT INTO ubicacion_localidades VALUES (9759, 13, 'EL PUERTO', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (9760, 13, 'EL QUEMADO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9761, 13, 'EL RETIRO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9762, 13, 'EL RINCON', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (9763, 13, 'EL RINCON', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9764, 13, 'EL SALADO', '5419', 0);
INSERT INTO ubicacion_localidades VALUES (9765, 13, 'EL SALADO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9766, 13, 'EL SALITRE', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9767, 13, 'EL SALTO', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9768, 13, 'EL TAPON', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9769, 13, 'EL TREINTA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9770, 13, 'EL VOLCAN', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (9771, 13, 'ENCON', '5421', 0);
INSERT INTO ubicacion_localidades VALUES (9772, 13, 'ENTRE RIOS', '5461', 0);
INSERT INTO ubicacion_localidades VALUES (9773, 13, 'EST ALBARDON', '5419', 0);
INSERT INTO ubicacion_localidades VALUES (9774, 13, 'EST DE HERRERA VEGAS', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9775, 13, 'EST LA CIENAGA DE GUALILA', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (9776, 13, 'EST MARAYES', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9777, 13, 'EST NIQUIVIL', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9778, 13, 'ESTACION LA RINCONADA', '5433', 0);
INSERT INTO ubicacion_localidades VALUES (9779, 13, 'ESTANCIA ACEQUION', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (9780, 13, 'ESTANCIA BAJO DE LAS TUMANAS', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9781, 13, 'ESTANCIA CASA RIO BLANCO', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9782, 13, 'ESTANCIA EL CHAÃAR NUEVO', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9783, 13, 'ESTANCIA EL DURAZNO', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (9784, 13, 'ESTANCIA EL JUMEAL', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9785, 13, 'ESTANCIA EL MOLINO', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9786, 13, 'ESTANCIA EL POLEAR', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9787, 13, 'ESTANCIA EL TOTORAL', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9788, 13, 'ESTANCIA ELIZONDO', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9789, 13, 'ESTANCIA LA ESCALERA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9790, 13, 'ESTANCIA LA FLORIDA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9791, 13, 'ESTANCIA LA LATA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9792, 13, 'ESTANCIA LA POSTA', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (9793, 13, 'ESTANCIA LA PUNTILLA', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9794, 13, 'ESTANCIA LEONCITO', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9795, 13, 'ESTANCIA MARADONA', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (9796, 13, 'ESTANCIA QUIROGA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9797, 13, 'ESTANCIA RIO VERDE', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9798, 13, 'ESTANCIA SAN ROQUE', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9799, 13, 'FIERRO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9800, 13, 'FIERRO NUEVO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9801, 13, 'FIERRO VIEJO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9802, 13, 'FILO DEL MOCHO', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9803, 13, 'FINCA DE IZASA', '5442', 0);
INSERT INTO ubicacion_localidades VALUES (9804, 13, 'FINCA DEL JAPONES', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9805, 13, 'FINCA EL MOLINO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9806, 13, 'FINCA EL TORO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9807, 13, 'FINCA ZAPATA', '5417', 0);
INSERT INTO ubicacion_localidades VALUES (9808, 13, 'GENDARMERIA NACIONAL', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9809, 13, 'GERMANIA', '5425', 0);
INSERT INTO ubicacion_localidades VALUES (9810, 13, 'GRAN CHINA', '5461', 0);
INSERT INTO ubicacion_localidades VALUES (9811, 13, 'GUAÃIZUL', '5465', 0);
INSERT INTO ubicacion_localidades VALUES (9812, 13, 'GUACHIPAMPA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9813, 13, 'GUAJA', '5461', 0);
INSERT INTO ubicacion_localidades VALUES (9814, 13, 'GUANACACHE', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (9815, 13, 'GUAYAGUAS', '5442', 0);
INSERT INTO ubicacion_localidades VALUES (9816, 13, 'GUAYAMAS', '5444', 0);
INSERT INTO ubicacion_localidades VALUES (9817, 13, 'HACHANGO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9818, 13, 'HILARIO', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (9819, 13, 'HOSTERIA EL BALDE', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9820, 13, 'HUAÃIZUIL', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9821, 13, 'HUACO', '5463', 0);
INSERT INTO ubicacion_localidades VALUES (9822, 13, 'HUAICOS', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (9823, 13, 'HUERTA DE GUACHI', '5461', 0);
INSERT INTO ubicacion_localidades VALUES (9824, 13, 'HUESO QUEBRADO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9825, 13, 'ICHIGUALASTO', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9826, 13, 'IGLESIA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9827, 13, 'IMSA', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (9828, 13, 'INDIO MUERTO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9829, 13, 'INGENIERO MATIAS G SANCHEZ', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (9830, 13, 'ISCHIGUALASTO', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9831, 13, 'ISLA DEL SAUCE', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (9832, 13, 'JACHAL', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9833, 13, 'JAGUELITO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9834, 13, 'JARILLA CHICA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9835, 13, 'JARILLITO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9836, 13, 'JOSE MARTI', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (9837, 13, 'JUAN CELANI', '5427', 0);
INSERT INTO ubicacion_localidades VALUES (9838, 13, 'JUNTA DE SANTA ROSA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9839, 13, 'JUNTAS DE LA JARILLA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9840, 13, 'JUNTAS DE LA SAL', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9841, 13, 'JUNTAS DEL FRIO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9842, 13, 'JUNTAS DEL GUANDACOL', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9843, 13, 'KILOMETRO 10 650', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (9844, 13, 'KILOMETRO 810', '5442', 0);
INSERT INTO ubicacion_localidades VALUES (9845, 13, 'KILOMETRO 893', '5444', 0);
INSERT INTO ubicacion_localidades VALUES (9846, 13, 'KILOMETRO 895', '5442', 0);
INSERT INTO ubicacion_localidades VALUES (9847, 13, 'KILOMETRO 905', '5439', 0);
INSERT INTO ubicacion_localidades VALUES (9848, 13, 'KILOMETRO 910', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (9849, 13, 'KILOMETRO 936', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (9850, 13, 'LA ALUMBRERA', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9851, 13, 'LA ANGOSTURA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9852, 13, 'LA CAÃADA', '5419', 0);
INSERT INTO ubicacion_localidades VALUES (9853, 13, 'LA CAÃADA', '5415', 0);
INSERT INTO ubicacion_localidades VALUES (9854, 13, 'LA CAÃADA', '5465', 0);
INSERT INTO ubicacion_localidades VALUES (9855, 13, 'LA CALLECITA', '5421', 0);
INSERT INTO ubicacion_localidades VALUES (9856, 13, 'LA CAPILLA', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9857, 13, 'LA CARPA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9858, 13, 'LA CERCADA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9859, 13, 'LA CHIGUA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9860, 13, 'LA CHILCA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9861, 13, 'LA CHILCA', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (9862, 13, 'LA CHIMBERA', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (9863, 13, 'LA CIENAGA', '5463', 0);
INSERT INTO ubicacion_localidades VALUES (9864, 13, 'LA CIENAGA DE CUMILLANGO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9865, 13, 'LA CIENAGUITA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9866, 13, 'LA CIENEGUITA', '5435', 0);
INSERT INTO ubicacion_localidades VALUES (9867, 13, 'LA CIENEGUITA', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (9868, 13, 'LA COLONIA', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (9869, 13, 'LA COSECHERA', '5427', 0);
INSERT INTO ubicacion_localidades VALUES (9870, 13, 'LA CRUZ', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9871, 13, 'LA ESQUINA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9872, 13, 'LA ESQUINA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9873, 13, 'LA ESTACA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9874, 13, 'LA ESTRECHURA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9875, 13, 'LA FALDA', '5461', 0);
INSERT INTO ubicacion_localidades VALUES (9876, 13, 'LA FLORIDA', '5425', 0);
INSERT INTO ubicacion_localidades VALUES (9877, 13, 'LA GERMANIA', '5439', 0);
INSERT INTO ubicacion_localidades VALUES (9878, 13, 'LA HUERTA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9879, 13, 'LA ISLA', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (9880, 13, 'LA LEGUA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9881, 13, 'LA LEGUA', '5411', 0);
INSERT INTO ubicacion_localidades VALUES (9882, 13, 'LA MAJADITA', '5417', 0);
INSERT INTO ubicacion_localidades VALUES (9883, 13, 'LA MAJADITA', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (9884, 13, 'LA MARAL', '5465', 0);
INSERT INTO ubicacion_localidades VALUES (9885, 13, 'LA MESADA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9886, 13, 'LA MORAL', '5465', 0);
INSERT INTO ubicacion_localidades VALUES (9887, 13, 'LA ORILLA', '5425', 0);
INSERT INTO ubicacion_localidades VALUES (9888, 13, 'LA ORQUETA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9889, 13, 'LA OVERA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9890, 13, 'LA PENCA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9891, 13, 'LA PUNTILLA', '5442', 0);
INSERT INTO ubicacion_localidades VALUES (9892, 13, 'LA PUNTILLA', '5439', 0);
INSERT INTO ubicacion_localidades VALUES (9893, 13, 'LA RAMADA', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (9894, 13, 'LA RINCONADA', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (9895, 13, 'LA RIPIERA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9896, 13, 'LA ROSITA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9897, 13, 'LA SAL', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9898, 13, 'LA TOMA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9899, 13, 'LA VALENTINA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9900, 13, 'LAGUNA DEL ROSARIO', '5435', 0);
INSERT INTO ubicacion_localidades VALUES (9901, 13, 'LAGUNA SECA', '5444', 0);
INSERT INTO ubicacion_localidades VALUES (9902, 13, 'LAPRIDA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9903, 13, 'LAS AGUADITAS', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (9904, 13, 'LAS CASITAS', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9905, 13, 'LAS CASUARINAS', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (9906, 13, 'LAS CHACRAS', '5446', 0);
INSERT INTO ubicacion_localidades VALUES (9907, 13, 'LAS CHACRITAS', '5417', 0);
INSERT INTO ubicacion_localidades VALUES (9908, 13, 'LAS CHIMBAS', '5436', 0);
INSERT INTO ubicacion_localidades VALUES (9909, 13, 'LAS CIENAGAS VERDES', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9910, 13, 'LAS CUEVAS', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9911, 13, 'LAS DELICIAS', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9912, 13, 'LAS ESPINAS', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9913, 13, 'LAS FLORES', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9914, 13, 'LAS HERMANAS', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9915, 13, 'LAS HIGUERAS', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9916, 13, 'LAS HIGUERITAS', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (9917, 13, 'LAS HIGUERITAS', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (9918, 13, 'LAS HORNILLAS', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9919, 13, 'LAS JUNTAS', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9920, 13, 'LAS LAGUNAS', '5435', 0);
INSERT INTO ubicacion_localidades VALUES (9921, 13, 'LAS LAJAS', '5419', 0);
INSERT INTO ubicacion_localidades VALUES (9922, 13, 'LAS LIEBRES', '5442', 0);
INSERT INTO ubicacion_localidades VALUES (9923, 13, 'LAS LOMITAS', '5419', 0);
INSERT INTO ubicacion_localidades VALUES (9924, 13, 'LAS PEÃITAS', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9925, 13, 'LAS PIEDRITAS', '5419', 0);
INSERT INTO ubicacion_localidades VALUES (9926, 13, 'LAS PUESTAS', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9927, 13, 'LAS RAMADITAS', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9928, 13, 'LAS SALINAS', '5446', 0);
INSERT INTO ubicacion_localidades VALUES (9929, 13, 'LAS TAPIAS', '5419', 0);
INSERT INTO ubicacion_localidades VALUES (9930, 13, 'LAS TUMANAS', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9931, 13, 'LAS YEGUAS', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9932, 13, 'LLOVERAS', '5425', 0);
INSERT INTO ubicacion_localidades VALUES (9933, 13, 'LOMA ANCHA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9934, 13, 'LOMA DE COCHO', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9935, 13, 'LOMA LEONES', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9936, 13, 'LOMA NEGRA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9937, 13, 'LOMA NEGRA', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (9938, 13, 'LOMAS BLANCAS', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9939, 13, 'LOMAS BLANCAS', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (9940, 13, 'LOMAS DEL AGUADITAS', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (9941, 13, 'LOS ANGACOS', '5439', 0);
INSERT INTO ubicacion_localidades VALUES (9942, 13, 'LOS BALDECITOS', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (9943, 13, 'LOS BALDES', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9944, 13, 'LOS BALDES DE ASTICA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9945, 13, 'LOS BARRIALES', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (9946, 13, 'LOS BERROS', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (9947, 13, 'LOS CHAÃARES', '5435', 0);
INSERT INTO ubicacion_localidades VALUES (9948, 13, 'LOS CHAVES', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (9949, 13, 'LOS COGOTES', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9950, 13, 'LOS COMPARTOS', '5438', 0);
INSERT INTO ubicacion_localidades VALUES (9951, 13, 'LOS CORREDORES', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (9952, 13, 'LOS DIAGUITAS', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (9953, 13, 'LOS HORNOS', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9954, 13, 'LOS LAGARES', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9955, 13, 'LOS LOROS', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9956, 13, 'LOS MEDANOS', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (9957, 13, 'LOS MELLIZOS', '5442', 0);
INSERT INTO ubicacion_localidades VALUES (9958, 13, 'LOS MOLLES', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (9959, 13, 'LOS NOGALES', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (9960, 13, 'LOS PAPAGAYOS', '5446', 0);
INSERT INTO ubicacion_localidades VALUES (9961, 13, 'LOS PARAMILLOS', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (9962, 13, 'LOS PENITENTES', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9963, 13, 'LOS PORONGOS', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9964, 13, 'LOS PUESTOS', '5419', 0);
INSERT INTO ubicacion_localidades VALUES (9965, 13, 'LOS PUESTOS', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9966, 13, 'LOS QUILLAY', '5417', 0);
INSERT INTO ubicacion_localidades VALUES (9967, 13, 'LOS QUIMBALETES', '5461', 0);
INSERT INTO ubicacion_localidades VALUES (9968, 13, 'LOS RANCHOS', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9969, 13, 'LOS RINCONES', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (9970, 13, 'LOS SANCHEZ', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9971, 13, 'LOS SAPITOS', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9972, 13, 'LOS SOMBREROS', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (9973, 13, 'LOS TERREMOTOS', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (9974, 13, 'LOS VIÃEDOS', '5413', 0);
INSERT INTO ubicacion_localidades VALUES (9975, 13, 'LOS VIÃEDOS', '5438', 0);
INSERT INTO ubicacion_localidades VALUES (9976, 13, 'LOTE ALVARADO', '5435', 0);
INSERT INTO ubicacion_localidades VALUES (9977, 13, 'LOTES DE ALVAREZ', '5442', 0);
INSERT INTO ubicacion_localidades VALUES (9978, 13, 'LOTES DE CORIA', '5442', 0);
INSERT INTO ubicacion_localidades VALUES (9979, 13, 'LOTES DE URIBURU', '5442', 0);
INSERT INTO ubicacion_localidades VALUES (9980, 13, 'LOTES ESCUELA 138', '5442', 0);
INSERT INTO ubicacion_localidades VALUES (9981, 13, 'LOTES RIVERA', '5442', 0);
INSERT INTO ubicacion_localidades VALUES (9982, 13, 'LUZ DEL MUNDO', '5411', 0);
INSERT INTO ubicacion_localidades VALUES (9983, 13, 'MACLACASTO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9984, 13, 'MAIPIRINQUI', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9985, 13, 'MAJADITA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9986, 13, 'MALIMAN', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9987, 13, 'MALIMAN ARRIBA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9988, 13, 'MALIMAN DE ABAJO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9989, 13, 'MANANTIALES', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (9990, 13, 'MANANTIALES', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (9991, 13, 'MARAYES', '5446', 0);
INSERT INTO ubicacion_localidades VALUES (9992, 13, 'MARQUESADO', '5407', 0);
INSERT INTO ubicacion_localidades VALUES (9993, 13, 'MATAGUSANOS', '5419', 0);
INSERT INTO ubicacion_localidades VALUES (9994, 13, 'MEDANO COLORADO', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (9995, 13, 'MEDANO DE ORO', '5421', 0);
INSERT INTO ubicacion_localidades VALUES (9996, 13, 'MEDIA AGUA', '5435', 0);
INSERT INTO ubicacion_localidades VALUES (9997, 13, 'MESADA AGUADA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9998, 13, 'MICA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (9999, 13, 'MILAGRO', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10000, 13, 'MINA DE GUACHI', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10001, 13, 'MINA DE LAS CARACHAS', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10002, 13, 'MINA EL ALGARROBO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10003, 13, 'MINA EL PESCADO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10004, 13, 'MINA ESCONDIDA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10005, 13, 'MINA GENERAL BELGRANO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10006, 13, 'MINA GUALILAN', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (10007, 13, 'MINA LA ABUNDANCIA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10008, 13, 'MINA LA DELFINA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10009, 13, 'MINA LA ESPERANZA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10010, 13, 'MINA LA SALAMANTA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10011, 13, 'MINA LOS CABALLOS', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10012, 13, 'MINA MONTOSA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10013, 13, 'MINA SAN ANTONIO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10014, 13, 'MINA SAN JORGE', '5403', 0);
INSERT INTO ubicacion_localidades VALUES (10015, 13, 'MOGNA', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (10016, 13, 'MONDACA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10017, 13, 'MORTERITO', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10018, 13, 'NAQUERA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10019, 13, 'NIKISANGA', '5444', 0);
INSERT INTO ubicacion_localidades VALUES (10020, 13, 'NIQUIVIL', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (10021, 13, 'NIQUIVIL VIEJO', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (10022, 13, 'NUEVA CASTILLA', '5444', 0);
INSERT INTO ubicacion_localidades VALUES (10023, 13, 'NUEVA ESPAÃA', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (10024, 13, 'OJOS DE AGUA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10025, 13, 'OJOS DE AGUA', '5461', 0);
INSERT INTO ubicacion_localidades VALUES (10026, 13, 'OTRA BANDA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10027, 13, 'PACHACO', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (10028, 13, 'PAJAS BLANCAS', '5411', 0);
INSERT INTO ubicacion_localidades VALUES (10029, 13, 'PAMPA DE LOS CABALLOS', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10030, 13, 'PAMPA DEL CHAÃAR', '5461', 0);
INSERT INTO ubicacion_localidades VALUES (10031, 13, 'PAMPA GRANDE', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10032, 13, 'PAMPA VIEJA', '5461', 0);
INSERT INTO ubicacion_localidades VALUES (10033, 13, 'PANACAN', '5461', 0);
INSERT INTO ubicacion_localidades VALUES (10034, 13, 'PAPAGAYOS', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (10035, 13, 'PAQUITA', '5415', 0);
INSERT INTO ubicacion_localidades VALUES (10036, 13, 'PARAJE BEBIDA', '5407', 0);
INSERT INTO ubicacion_localidades VALUES (10037, 13, 'PASLEAM', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10038, 13, 'PASO DE FERREIRA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10039, 13, 'PASO DE LAMAS', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10040, 13, 'PASO DE OTAROLA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10041, 13, 'PASO DEL AGUA NEGRA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10042, 13, 'PASO DEL LAMAR', '5463', 0);
INSERT INTO ubicacion_localidades VALUES (10043, 13, 'PEÃASCO COLORADO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10044, 13, 'PEÃASQUITO', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (10045, 13, 'PEÃASQUITO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10046, 13, 'PEDERNAL', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (10047, 13, 'PEDRO ECHAGUE', '5436', 0);
INSERT INTO ubicacion_localidades VALUES (10048, 13, 'PICHAGUAL', '5415', 0);
INSERT INTO ubicacion_localidades VALUES (10049, 13, 'PIE DE PALO', '5444', 0);
INSERT INTO ubicacion_localidades VALUES (10050, 13, 'PIEDRA BLANCA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10051, 13, 'PIEDRA COLORADA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10052, 13, 'PIEDRA PARADA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10053, 13, 'PIEDRA RAJADA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10054, 13, 'PIEDRAS BLANCAS', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10055, 13, 'PILA DE MACHO', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10056, 13, 'PIMPA', '5461', 0);
INSERT INTO ubicacion_localidades VALUES (10057, 13, 'PIRCAS BLANCAS', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10058, 13, 'PIRCAS NEGRAS', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10059, 13, 'PISMANIA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10060, 13, 'PLUMERILLOS', '5415', 0);
INSERT INTO ubicacion_localidades VALUES (10061, 13, 'PO CAJON DE LA BREA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10062, 13, 'PO DE BARAHONA', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (10063, 13, 'PO DE LA GUARDIA', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (10064, 13, 'PO DE LAS LLARETAS', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (10065, 13, 'PO DE LAS OJOTAS', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (10066, 13, 'PO DE LOS PIUQUENES', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (10067, 13, 'PO DE LOS TEATINOS', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (10068, 13, 'PO DE USNO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10069, 13, 'PO DEL CHOLLAY', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10070, 13, 'PO DEL INCA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10071, 13, 'PO DEL PORTILLO', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (10072, 13, 'PO DEL TONTAL', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (10073, 13, 'PO LAS TORTOLAS', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10074, 13, 'POCITO', '5429', 0);
INSERT INTO ubicacion_localidades VALUES (10075, 13, 'PORT DE LA PUNILLA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10076, 13, 'PORT DE LONGOMICHE', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (10077, 13, 'PORT DE LOS SOMBREROS', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (10078, 13, 'PORT DEL COLORADO', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (10079, 13, 'PORT LAS CARACACHAS', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10080, 13, 'PORT LAS CHILCAS', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10081, 13, 'PORT LAS FRANCAS', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10082, 13, 'PORT SAN GUILLERMO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10083, 13, 'PORT SANTA ROSA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10084, 13, 'PORTON GRANDE', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10085, 13, 'POTRANCA', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (10086, 13, 'POTRERILLOS', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (10087, 13, 'POTREROS LOS AMADORES', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10088, 13, 'POZO DE AGUADITA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10089, 13, 'POZO DE LOS ALGARROBOS', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (10090, 13, 'POZO SALADO', '5442', 0);
INSERT INTO ubicacion_localidades VALUES (10091, 13, 'PRESBITERO FRANCISCO PEREZ HER', '5400', 0);
INSERT INTO ubicacion_localidades VALUES (10092, 13, 'PRIMER CUARTEL', '5425', 0);
INSERT INTO ubicacion_localidades VALUES (10093, 13, 'PTO AG DEL BURRO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10094, 13, 'PTO AGUADITA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10095, 13, 'PTO AGUADITA DE ABAJO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10096, 13, 'PTO ANJULIO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10097, 13, 'PTO CHANQUIA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10098, 13, 'PTO CHAVEZ', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10099, 13, 'PTO CORDOVA', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (10100, 13, 'PTO CUMILLANGO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10101, 13, 'PTO DEL AGUA DE PINTO', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (10102, 13, 'PTO DURAZNO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10103, 13, 'PTO EL ARBOL LIGUDO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10104, 13, 'PTO EL MOLLE', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (10105, 13, 'PTO EL SARCO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10106, 13, 'PTO EL TORO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10107, 13, 'PTO FIGUEROA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10108, 13, 'PTO GEN', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10109, 13, 'PTO GORDILLO', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10110, 13, 'PTO HUASI', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10111, 13, 'PTO LA CHILCA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10112, 13, 'PTO LA CORTADERA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10113, 13, 'PTO LA ESPINA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10114, 13, 'PTO LA REPRESA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10115, 13, 'PTO LA TUNA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10116, 13, 'PTO LA VIRGENCITA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10117, 13, 'PTO LAS CUEVAS', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (10118, 13, 'PTO LIMA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10119, 13, 'PTO LOS ALAMOS', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10120, 13, 'PTO LOS PAPAGALLOS', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (10121, 13, 'PTO LOS POZOS', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10122, 13, 'PTO MAJADITA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10123, 13, 'PTO PAJARITO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10124, 13, 'PTO PANTANITO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10125, 13, 'PTO PERICO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10126, 13, 'PTO PESCADO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10127, 13, 'PTO PIMPA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10128, 13, 'PTO PORTEZUELO HONDO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10129, 13, 'PTO POTRERILLO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10130, 13, 'PTO PUNILLA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10131, 13, 'PTO RECREO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10132, 13, 'PTO ROMERO', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10133, 13, 'PTO SABATO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10134, 13, 'PTO SAN ISIDRO', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10135, 13, 'PTO SANTA ROSA DE ABAJO', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (10136, 13, 'PTO SEGOVIA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10137, 13, 'PTO TRAPICHE', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10138, 13, 'PTO VALLECITO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10139, 13, 'PTO VAREJON', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10140, 13, 'PTO VEGA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10141, 13, 'PUCHUZUN', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (10142, 13, 'PUENTE NACIONAL', '5438', 0);
INSERT INTO ubicacion_localidades VALUES (10143, 13, 'PUENTE RIO SAN JUAN', '5438', 0);
INSERT INTO ubicacion_localidades VALUES (10144, 13, 'PUENTE RUFINO', '5438', 0);
INSERT INTO ubicacion_localidades VALUES (10145, 13, 'PUERTA DE LA CHILCA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10146, 13, 'PUERTA DEL INFIERNILLO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10147, 13, 'PUERTO ALEGRE', '5442', 0);
INSERT INTO ubicacion_localidades VALUES (10148, 13, 'PUERTO TAPONES DE MAYA', '5442', 0);
INSERT INTO ubicacion_localidades VALUES (10149, 13, 'PUESTO ANGOSTURA', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (10150, 13, 'PUESTO DE ARRIBA', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (10151, 13, 'PUESTO LA CHILCA DE ABAJO', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (10152, 13, 'PUESTO OLGUIN', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (10153, 13, 'PUESTO RETIRO', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (10154, 13, 'PUESTO SANTA ROSA', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (10155, 13, 'PUNTA BLANCA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10156, 13, 'PUNTA DE AGUA', '5463', 0);
INSERT INTO ubicacion_localidades VALUES (10157, 13, 'PUNTA DE LAGUNA', '5435', 0);
INSERT INTO ubicacion_localidades VALUES (10158, 13, 'PUNTA DEL MEDANO', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (10159, 13, 'PUNTA DEL MEDANO', '5435', 0);
INSERT INTO ubicacion_localidades VALUES (10160, 13, 'PUNTA DEL MONTE', '5415', 0);
INSERT INTO ubicacion_localidades VALUES (10161, 13, 'PUNTA NORTE', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10162, 13, 'PUNTILLA', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (10163, 13, 'PUNTILLA BLANCA', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (10164, 13, 'PUNTILLA BLANCA', '5439', 0);
INSERT INTO ubicacion_localidades VALUES (10165, 13, 'PUNTILLA NEGRA', '5439', 0);
INSERT INTO ubicacion_localidades VALUES (10166, 13, 'QUILINQUIL', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10167, 13, 'QUINTO CUARTEL', '5427', 0);
INSERT INTO ubicacion_localidades VALUES (10168, 13, 'QUIROGA', '5435', 0);
INSERT INTO ubicacion_localidades VALUES (10169, 13, 'RAMBLON', '5435', 0);
INSERT INTO ubicacion_localidades VALUES (10170, 13, 'RANCHOS DE FAMACOA', '5415', 0);
INSERT INTO ubicacion_localidades VALUES (10171, 13, 'REFUGIO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10172, 13, 'REFUGIO', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10173, 13, 'REFUGIO D P V', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (10174, 13, 'REFUGIO LOS GAUCHOS', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (10175, 13, 'RETAMITO', '5435', 0);
INSERT INTO ubicacion_localidades VALUES (10176, 13, 'RICHARD', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10177, 13, 'RINCON', '5442', 0);
INSERT INTO ubicacion_localidades VALUES (10178, 13, 'RINCON', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10179, 13, 'RINCON CHICO', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10180, 13, 'RINCON COLORADO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10181, 13, 'RINCON DE LA BREA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10182, 13, 'RINCON DE LA OLLITA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10183, 13, 'RINCON DE LOS CHINCHILLEROS', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10184, 13, 'RINCON DEL GATO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10185, 13, 'RINCON GRANDE', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10186, 13, 'RINCONADA', '5429', 0);
INSERT INTO ubicacion_localidades VALUES (10187, 13, 'RINCONES', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (10188, 13, 'RIO PALO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10189, 13, 'RIO SASO', '5407', 0);
INSERT INTO ubicacion_localidades VALUES (10190, 13, 'RIVADAVIA', '5400', 0);
INSERT INTO ubicacion_localidades VALUES (10191, 13, 'RODEO', '5465', 0);
INSERT INTO ubicacion_localidades VALUES (10192, 13, 'RUINAS INDIGENAS', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10193, 13, 'RUTA 20 KILOMETRO 114', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (10194, 13, 'SAN ANTONIO', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (10195, 13, 'SAN ANTONIO', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (10196, 13, 'SAN CARLOS', '5446', 0);
INSERT INTO ubicacion_localidades VALUES (10197, 13, 'SAN CARLOS', '5435', 0);
INSERT INTO ubicacion_localidades VALUES (10198, 13, 'SAN ISIDRO', '5461', 0);
INSERT INTO ubicacion_localidades VALUES (10199, 13, 'SAN ISIDRO', '5439', 0);
INSERT INTO ubicacion_localidades VALUES (10200, 13, 'SAN JUAN', '5400', 0);
INSERT INTO ubicacion_localidades VALUES (10201, 13, 'SAN JUAN BAUTISTA USNO', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (10202, 13, 'SAN MARTIN', '5439', 0);
INSERT INTO ubicacion_localidades VALUES (10203, 13, 'SAN ROQUE', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (10204, 13, 'SANCHEZ DE LORIA', '5427', 0);
INSERT INTO ubicacion_localidades VALUES (10205, 13, 'SANJUANINO', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10206, 13, 'SANTA BARBARA', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (10207, 13, 'SANTA CLARA', '5425', 0);
INSERT INTO ubicacion_localidades VALUES (10208, 13, 'SANTA CLARA', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (10209, 13, 'SANTA LUCIA', '5411', 0);
INSERT INTO ubicacion_localidades VALUES (10210, 13, 'SANTA MARIA DEL ROSARIO', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (10211, 13, 'SANTO DOMINGO', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10212, 13, 'SARMIENTO', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10213, 13, 'SEGUNDO CUARTEL', '5425', 0);
INSERT INTO ubicacion_localidades VALUES (10214, 13, 'SIERRA BILLICUM', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10215, 13, 'SIERRA DE CHAVES', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (10216, 13, 'SIERRA DE ELIZONDO', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10217, 13, 'SIERRA DE RIVERO', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (10218, 13, 'SOROCAYENSE', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (10219, 13, 'TALACASTO', '5400', 0);
INSERT INTO ubicacion_localidades VALUES (10220, 13, 'TAMBERIAS', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (10221, 13, 'TAMBERIAS', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10222, 13, 'TAMBERIAS', '5461', 0);
INSERT INTO ubicacion_localidades VALUES (10223, 13, 'TAMBERIAS', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10224, 13, 'TAP GALLARDO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10225, 13, 'TERMA LA LAJA', '5419', 0);
INSERT INTO ubicacion_localidades VALUES (10226, 13, 'TERMA PISMANTA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10227, 13, 'TERMAS AGUA HEDIONDA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10228, 13, 'TERMAS CENTENARIO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10229, 13, 'TERMAS DE AGUA NEGRA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10230, 13, 'TIERRA ADENTRO', '5417', 0);
INSERT INTO ubicacion_localidades VALUES (10231, 13, 'TIERRA ADENTRO', '5419', 0);
INSERT INTO ubicacion_localidades VALUES (10232, 13, 'TIRA LARGA', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (10233, 13, 'TOCOTA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10234, 13, 'TONTAL', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (10235, 13, 'TOTORALITO', '5465', 0);
INSERT INTO ubicacion_localidades VALUES (10236, 13, 'TRANCAS', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10237, 13, 'TRAVESIA DE MOGNA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10238, 13, 'TRES ESQUINAS', '5435', 0);
INSERT INTO ubicacion_localidades VALUES (10239, 13, 'TRES QUEBRADITAS', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10240, 13, 'TRINIDAD', '5400', 0);
INSERT INTO ubicacion_localidades VALUES (10241, 13, 'TUCUNUCO', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (10242, 13, 'TUDCUM', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10243, 13, 'TUMANAS', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10244, 13, 'TUMINICO', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10245, 13, 'TUPELI', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (10246, 13, 'TUTIANCA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10247, 13, 'ULLUM', '5409', 0);
INSERT INTO ubicacion_localidades VALUES (10248, 13, 'URIBURU', '5442', 0);
INSERT INTO ubicacion_localidades VALUES (10249, 13, 'USINA', '5438', 0);
INSERT INTO ubicacion_localidades VALUES (10250, 13, 'USNO', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (10251, 13, 'VALLE DEL CURA', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10252, 13, 'VALLE FERTIL', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (10253, 13, 'VALLECITO', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (10254, 13, 'VALLECITO', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (10255, 13, 'VENILLO', '5467', 0);
INSERT INTO ubicacion_localidades VALUES (10256, 13, 'VILLA', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10257, 13, 'VILLA  J  C  SARMIENTO', '5411', 0);
INSERT INTO ubicacion_localidades VALUES (10258, 13, 'VILLA  N  LARRAIN', '5411', 0);
INSERT INTO ubicacion_localidades VALUES (10259, 13, 'VILLA  P A D SARMIENTO', '5413', 0);
INSERT INTO ubicacion_localidades VALUES (10260, 13, 'VILLA 20 DE JUNIO', '5411', 0);
INSERT INTO ubicacion_localidades VALUES (10261, 13, 'VILLA ABERASTAIN', '5427', 0);
INSERT INTO ubicacion_localidades VALUES (10262, 13, 'VILLA ALEM', '5439', 0);
INSERT INTO ubicacion_localidades VALUES (10263, 13, 'VILLA BARBOZA', '5427', 0);
INSERT INTO ubicacion_localidades VALUES (10264, 13, 'VILLA BASILIO NIEVAS', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (10265, 13, 'VILLA BERMEJITO', '5411', 0);
INSERT INTO ubicacion_localidades VALUES (10266, 13, 'VILLA BORJAS', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (10267, 13, 'VILLA CARLOTA', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (10268, 13, 'VILLA CAROLINA', '5400', 0);
INSERT INTO ubicacion_localidades VALUES (10269, 13, 'VILLA COLON', '5442', 0);
INSERT INTO ubicacion_localidades VALUES (10270, 13, 'VILLA CORRAL', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (10271, 13, 'VILLA DEL CARMEN', '5400', 0);
INSERT INTO ubicacion_localidades VALUES (10272, 13, 'VILLA DEL SALVADOR', '5415', 0);
INSERT INTO ubicacion_localidades VALUES (10273, 13, 'VILLA DOMINGUITO', '5439', 0);
INSERT INTO ubicacion_localidades VALUES (10274, 13, 'VILLA ESTEVEZ', '5411', 0);
INSERT INTO ubicacion_localidades VALUES (10275, 13, 'VILLA FLEURY', '5425', 0);
INSERT INTO ubicacion_localidades VALUES (10276, 13, 'VILLA FRANCA', '5425', 0);
INSERT INTO ubicacion_localidades VALUES (10277, 13, 'VILLA GENERAL ACHA', '5415', 0);
INSERT INTO ubicacion_localidades VALUES (10278, 13, 'VILLA GENERAL ACHA', '5425', 0);
INSERT INTO ubicacion_localidades VALUES (10279, 13, 'VILLA GENERAL LAS HERAS', '5411', 0);
INSERT INTO ubicacion_localidades VALUES (10280, 13, 'VILLA GENERAL SAN MARTIN', '5419', 0);
INSERT INTO ubicacion_localidades VALUES (10281, 13, 'VILLA GENERAL SARMIENTO', '5431', 0);
INSERT INTO ubicacion_localidades VALUES (10282, 13, 'VILLA GOBERNADOR CHAVEZ', '5411', 0);
INSERT INTO ubicacion_localidades VALUES (10283, 13, 'VILLA HUASIHUL', '5400', 0);
INSERT INTO ubicacion_localidades VALUES (10284, 13, 'VILLA INDEPENDENCIA', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (10285, 13, 'VILLA JUAN XXIII', '5413', 0);
INSERT INTO ubicacion_localidades VALUES (10286, 13, 'VILLA KRAUSE', '5425', 0);
INSERT INTO ubicacion_localidades VALUES (10287, 13, 'VILLA LAPRIDA', '5425', 0);
INSERT INTO ubicacion_localidades VALUES (10288, 13, 'VILLA LERGA', '5425', 0);
INSERT INTO ubicacion_localidades VALUES (10289, 13, 'VILLA LUGANO', '5439', 0);
INSERT INTO ubicacion_localidades VALUES (10290, 13, 'VILLA LUZ DEL MUNDO', '5411', 0);
INSERT INTO ubicacion_localidades VALUES (10291, 13, 'VILLA MARINI', '5400', 0);
INSERT INTO ubicacion_localidades VALUES (10292, 13, 'VILLA MEDIA AGUA', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (10293, 13, 'VILLA MERCEDES', '5461', 0);
INSERT INTO ubicacion_localidades VALUES (10294, 13, 'VILLA MORRONES', '5413', 0);
INSERT INTO ubicacion_localidades VALUES (10295, 13, 'VILLA MUÃOZ', '5411', 0);
INSERT INTO ubicacion_localidades VALUES (10296, 13, 'VILLA NACUSI', '5427', 0);
INSERT INTO ubicacion_localidades VALUES (10297, 13, 'VILLA NUEVA', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (10298, 13, 'VILLA OBRERA', '5407', 0);
INSERT INTO ubicacion_localidades VALUES (10299, 13, 'VILLA PALERMO', '5400', 0);
INSERT INTO ubicacion_localidades VALUES (10300, 13, 'VILLA PATRICIAS SANJUANINAS', '5411', 0);
INSERT INTO ubicacion_localidades VALUES (10301, 13, 'VILLA PUEYRREDON', '5411', 0);
INSERT INTO ubicacion_localidades VALUES (10302, 13, 'VILLA RACHEL', '5425', 0);
INSERT INTO ubicacion_localidades VALUES (10303, 13, 'VILLA RIZZO', '5411', 0);
INSERT INTO ubicacion_localidades VALUES (10304, 13, 'VILLA RUFINO GOMEZ', '5411', 0);
INSERT INTO ubicacion_localidades VALUES (10305, 13, 'VILLA SAN ISIDRO', '5415', 0);
INSERT INTO ubicacion_localidades VALUES (10306, 13, 'VILLA SANTA ANITA', '5423', 0);
INSERT INTO ubicacion_localidades VALUES (10307, 13, 'VILLA SANTA PAULA', '5413', 0);
INSERT INTO ubicacion_localidades VALUES (10308, 13, 'VILLA SANTA ROSA', '5443', 0);
INSERT INTO ubicacion_localidades VALUES (10309, 13, 'VILLA SARGENTO CABRAL', '5411', 0);
INSERT INTO ubicacion_localidades VALUES (10310, 13, 'VOLCAN', '5460', 0);
INSERT INTO ubicacion_localidades VALUES (10311, 13, 'YACIMIENTO DE COBRE EL PACHON', '5405', 0);
INSERT INTO ubicacion_localidades VALUES (10312, 13, 'YERBA BUENA', '5447', 0);
INSERT INTO ubicacion_localidades VALUES (10313, 13, 'YOCA', '5449', 0);
INSERT INTO ubicacion_localidades VALUES (10314, 13, 'ZAVALLA', '5425', 0);
INSERT INTO ubicacion_localidades VALUES (10315, 13, 'ZONDA', '5401', 0);
INSERT INTO ubicacion_localidades VALUES (10316, 13, 'ZONDA', '5401', 0);

--
-- Data for Name: sucursales; Type: TABLE DATA; Schema: public; Owner: retail
--

INSERT INTO sucursales (id_sucursal, nombre_sucursal, id_pais, id_provincia, id_localidad, direccion, telefono_fijo, fecha_alta, activo, version) VALUES
(1,	'CASA CENTRAL',	1,	1,	1,	'--',	NULL,	'2014-01-01 00:00:00',	true,	0);

--
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: retail
--

INSERT INTO usuarios (id_usuario, nombre_usuario, login, password, fecha_alta, id_sucursal, punto_venta, version) VALUES
(1,	'Administrador',	'admin',	'jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=',	'2013-08-14 22:25:06.283546',	1,	'0001',	0);


--
-- Data for Name: usuarios_grupos; Type: TABLE DATA; Schema: public; Owner: retail
--

INSERT INTO usuarios_grupos (id_grupo, nombre_grupo) VALUES
(1,	'ADMINISTRADORES');




--
-- Data for Name: usuarios_gruposx; Type: TABLE DATA; Schema: public; Owner: retail
--

INSERT INTO usuarios_gruposx (id_usuario, id_grupo) values
(1,	1);


INSERT INTO ventas_estados (id_estado, nombre_estado, version) VALUES
(1,	'DISEÑO',	0);
INSERT INTO ventas_estados (id_estado, nombre_estado, version) VALUES
(2,	'ACEPTADA',	0);
INSERT INTO ventas_estados (id_estado, nombre_estado, version) VALUES
(3,	'FACTURADA',	0);
INSERT INTO ventas_estados (id_estado, nombre_estado, version) VALUES
(4,	'ANULADA',	0);

--
-- Name: bancos_cuenta_corriente_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY bancos_cuenta_corriente
    ADD CONSTRAINT bancos_cuenta_corriente_pkey PRIMARY KEY (id_movimiento);


--
-- Name: bancos_cuentas_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY bancos_cuentas
    ADD CONSTRAINT bancos_cuentas_pkey PRIMARY KEY (id_cuenta_banco);


--
-- Name: bancos_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY bancos
    ADD CONSTRAINT bancos_pkey PRIMARY KEY (id_banco);


--
-- Name: bancos_tipos_cuenta_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY bancos_tipos_cuenta
    ADD CONSTRAINT bancos_tipos_cuenta_pkey PRIMARY KEY (id_tipo_cuenta_banco);


--
-- Name: cajas_categorias_movimientos_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY cajas_categorias_movimientos
    ADD CONSTRAINT cajas_categorias_movimientos_pkey PRIMARY KEY (id_categoria_movimiento);


--
-- Name: cajas_movimientos_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY cajas_movimientos
    ADD CONSTRAINT cajas_movimientos_pkey PRIMARY KEY (id_movimiento_caja);


--
-- Name: cajas_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY cajas
    ADD CONSTRAINT cajas_pkey PRIMARY KEY (id_caja);


--
-- Name: contabilidad_libros_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY contabilidad_libros
    ADD CONSTRAINT contabilidad_libros_pkey PRIMARY KEY (id_libro);


--
-- Name: contabilidad_monedas_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY contabilidad_monedas
    ADD CONSTRAINT contabilidad_monedas_pkey PRIMARY KEY (id_moneda);


--
-- Name: contabilidad_periodos_contables_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY contabilidad_periodos_contables
    ADD CONSTRAINT contabilidad_periodos_contables_pkey PRIMARY KEY (id_periodo_contable);


--
-- Name: contabilidad_plan_cuentas_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY contabilidad_plan_cuentas
    ADD CONSTRAINT contabilidad_plan_cuentas_pkey PRIMARY KEY (id_cuenta);


--
-- Name: contabilidad_registro_contable_lineas_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY contabilidad_registro_contable_lineas
    ADD CONSTRAINT contabilidad_registro_contable_lineas_pkey PRIMARY KEY (id_linea_registro);


--
-- Name: contabilidad_registro_contable_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY contabilidad_registro_contable
    ADD CONSTRAINT contabilidad_registro_contable_pkey PRIMARY KEY (id_registro);


--
-- Name: contabilidad_tipos_comprobantes_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY contabilidad_tipos_comprobantes
    ADD CONSTRAINT contabilidad_tipos_comprobantes_pkey PRIMARY KEY (id_tipo_comprobante);


--
-- Name: contabilidad_tipos_cuenta_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY contabilidad_tipos_cuenta
    ADD CONSTRAINT contabilidad_tipos_cuenta_pkey PRIMARY KEY (id_tipo_cuenta);


--
-- Name: contabilidad_tipos_operacion_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY contabilidad_tipos_operacion
    ADD CONSTRAINT contabilidad_tipos_operacion_pkey PRIMARY KEY (id_tipo_operacion);


--
-- Name: depositos_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY depositos
    ADD CONSTRAINT depositos_pkey PRIMARY KEY (id_deposito);


--
-- Name: fiscal_alicuotas_iva_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY fiscal_alicuotas_iva
    ADD CONSTRAINT fiscal_alicuotas_iva_pkey PRIMARY KEY (id_alicuota_iva);


--
-- Name: fiscal_letras_comprobantes_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY fiscal_letras_comprobantes
    ADD CONSTRAINT fiscal_letras_comprobantes_pkey PRIMARY KEY (id_resoponsabildiad_iva_emisor, id_resoponsabildiad_iva_receptor);


--
-- Name: fiscal_libro_iva_ventas_lineas_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY fiscal_libro_iva_ventas_lineas
    ADD CONSTRAINT fiscal_libro_iva_ventas_lineas_pkey PRIMARY KEY (id_linea_libro);


--
-- Name: fiscal_libro_iva_ventas_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY fiscal_libro_iva_ventas
    ADD CONSTRAINT fiscal_libro_iva_ventas_pkey PRIMARY KEY (id_factura);


--
-- Name: fiscal_periodos_fiscales_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY fiscal_periodos_fiscales
    ADD CONSTRAINT fiscal_periodos_fiscales_pkey PRIMARY KEY (id_periodo_fiscal);


--
-- Name: fiscal_responsabilidades_iva_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY fiscal_responsabilidades_iva
    ADD CONSTRAINT fiscal_responsabilidades_iva_pkey PRIMARY KEY (id_resoponsabildiad_iva);


--
-- Name: legal_generos_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY legal_generos
    ADD CONSTRAINT legal_generos_pkey PRIMARY KEY (id_genero);


--
-- Name: legal_tipos_documento_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY legal_tipos_documento
    ADD CONSTRAINT legal_tipos_documento_pkey PRIMARY KEY (id_tipo_documento);


--
-- Name: legal_tipos_personeria_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY legal_tipos_personeria
    ADD CONSTRAINT legal_tipos_personeria_pkey PRIMARY KEY (id_tipo_personeria);


--
-- Name: parametros_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY parametros
    ADD CONSTRAINT parametros_pkey PRIMARY KEY (nombre_parametro);


--
-- Name: personas_cuenta_corriente_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY personas_cuenta_corriente
    ADD CONSTRAINT personas_cuenta_corriente_pkey PRIMARY KEY (id_movimiento);


--
-- Name: personas_imagenes_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY personas_imagenes
    ADD CONSTRAINT personas_imagenes_pkey PRIMARY KEY (id_imagen);


--
-- Name: personas_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY personas
    ADD CONSTRAINT personas_pkey PRIMARY KEY (id_persona);


--
-- Name: personas_telefonos_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY personas_telefonos
    ADD CONSTRAINT personas_telefonos_pkey PRIMARY KEY (id_telefono);


--
-- Name: personas_tipos_imagenes_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY personas_tipos_imagenes
    ADD CONSTRAINT personas_tipos_imagenes_pkey PRIMARY KEY (id_tipo_imagen);


--
-- Name: privilegios_gruposx_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY privilegios_gruposx
    ADD CONSTRAINT privilegios_gruposx_pkey PRIMARY KEY (id_privilegio, id_grupo);


--
-- Name: privilegios_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY privilegios
    ADD CONSTRAINT privilegios_pkey PRIMARY KEY (id_privilegio);


--
-- Name: productos_caracteristicas_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY productos_caracteristicas
    ADD CONSTRAINT productos_caracteristicas_pkey PRIMARY KEY (id_caracteristica);


--
-- Name: productos_imagenes_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY productos_imagenes
    ADD CONSTRAINT productos_imagenes_pkey PRIMARY KEY (id_imagen);


--
-- Name: productos_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY productos
    ADD CONSTRAINT productos_pkey PRIMARY KEY (id_producto);


--
-- Name: productos_porcentajes_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY productos_porcentajes
    ADD CONSTRAINT productos_porcentajes_pkey PRIMARY KEY (id_porcentaje);


--
-- Name: productos_rubros_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY productos_rubros
    ADD CONSTRAINT productos_rubros_pkey PRIMARY KEY (id_rubro);


--
-- Name: productos_sub_rubros_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY productos_sub_rubros
    ADD CONSTRAINT productos_sub_rubros_pkey PRIMARY KEY (id_sub_rubro);


--
-- Name: productos_tipos_proveeduria_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY productos_tipos_proveeduria
    ADD CONSTRAINT productos_tipos_proveeduria_pkey PRIMARY KEY (id_tipo_proveeduria);


--
-- Name: productos_tipos_unidades_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY productos_tipos_unidades
    ADD CONSTRAINT productos_tipos_unidades_pkey PRIMARY KEY (id_tipo_unidad);


--
-- Name: productos_x_caracteristicas_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY productos_x_caracteristicas
    ADD CONSTRAINT productos_x_caracteristicas_pkey PRIMARY KEY (id_caracteristica_x_producto);


--
-- Name: proveedores_ordenes_compra_lineas_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY proveedores_ordenes_compra_lineas
    ADD CONSTRAINT proveedores_ordenes_compra_lineas_pkey PRIMARY KEY (id_linea);


--
-- Name: proveedores_ordenes_compra_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY proveedores_ordenes_compra
    ADD CONSTRAINT proveedores_ordenes_compra_pkey PRIMARY KEY (id_orden_compra);


--
-- Name: stock_movimientos_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY stock_movimientos
    ADD CONSTRAINT stock_movimientos_pkey PRIMARY KEY (id_movimiento_stock);


--
-- Name: stock_movimientos_tipos_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY stock_movimientos_tipos
    ADD CONSTRAINT stock_movimientos_tipos_pkey PRIMARY KEY (id_tipo_movimiento);


--
-- Name: sucursales_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY sucursales
    ADD CONSTRAINT sucursales_pkey PRIMARY KEY (id_sucursal);


--
-- Name: ubicacion_localidades_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY ubicacion_localidades
    ADD CONSTRAINT ubicacion_localidades_pkey PRIMARY KEY (id_localidad);


--
-- Name: ubicacion_paises_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY ubicacion_paises
    ADD CONSTRAINT ubicacion_paises_pkey PRIMARY KEY (id_pais);


--
-- Name: ubicacion_provincias_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY ubicacion_provincias
    ADD CONSTRAINT ubicacion_provincias_pkey PRIMARY KEY (id_provincia);


--
-- Name: unique_productos_x_caracteristicas; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY productos_x_caracteristicas
    ADD CONSTRAINT unique_productos_x_caracteristicas UNIQUE (id_caracteristica, id_producto);


--
-- Name: usuarios_grupos_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY usuarios_grupos
    ADD CONSTRAINT usuarios_grupos_pkey PRIMARY KEY (id_grupo);


--
-- Name: usuarios_gruposx_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY usuarios_gruposx
    ADD CONSTRAINT usuarios_gruposx_pkey PRIMARY KEY (id_usuario, id_grupo);


--
-- Name: usuarios_login_key; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY usuarios
    ADD CONSTRAINT usuarios_login_key UNIQUE (login);


--
-- Name: usuarios_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (id_usuario);


--
-- Name: ventas_cargos_fijos_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY ventas_cargos_fijos
    ADD CONSTRAINT ventas_cargos_fijos_pkey PRIMARY KEY (id_cargo_fijo);


--
-- Name: ventas_condiciones_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY negocio_condiciones_operaciones
    ADD CONSTRAINT ventas_condiciones_pkey PRIMARY KEY (id_condicion);


--
-- Name: ventas_estados_historico_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY ventas_estados_historico
    ADD CONSTRAINT ventas_estados_historico_pkey PRIMARY KEY (id_estado_historico);


--
-- Name: ventas_estados_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY ventas_estados
    ADD CONSTRAINT ventas_estados_pkey PRIMARY KEY (id_estado);


--
-- Name: ventas_formas_pago_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY negocio_formas_pago
    ADD CONSTRAINT ventas_formas_pago_pkey PRIMARY KEY (id_forma_pago);


--
-- Name: ventas_lineas_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY ventas_lineas
    ADD CONSTRAINT ventas_lineas_pkey PRIMARY KEY (id_linea_venta);


--
-- Name: ventas_pagos_lineas_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY ventas_pagos_lineas
    ADD CONSTRAINT ventas_pagos_lineas_pkey PRIMARY KEY (id_linea_pago);


--
-- Name: ventas_pagos_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY ventas_pagos
    ADD CONSTRAINT ventas_pagos_pkey PRIMARY KEY (id_pago_venta);


--
-- Name: ventas_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY ventas
    ADD CONSTRAINT ventas_pkey PRIMARY KEY (id_venta);


--
-- Name: ventas_remitos_lineas_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY ventas_remitos_lineas
    ADD CONSTRAINT ventas_remitos_lineas_pkey PRIMARY KEY (id_linea_remito);


--
-- Name: ventas_remitos_pkey; Type: CONSTRAINT; Schema: public; Owner: retail; Tablespace: 
--

ALTER TABLE ONLY ventas_remitos
    ADD CONSTRAINT ventas_remitos_pkey PRIMARY KEY (id_remito);


--
-- Name: bancos_cuenta_corriente_id_cuenta_banco_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY bancos_cuenta_corriente
    ADD CONSTRAINT bancos_cuenta_corriente_id_cuenta_banco_fkey FOREIGN KEY (id_cuenta_banco) REFERENCES bancos_cuentas(id_cuenta_banco);


--
-- Name: bancos_cuentas_id_banco_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY bancos_cuentas
    ADD CONSTRAINT bancos_cuentas_id_banco_fkey FOREIGN KEY (id_banco) REFERENCES bancos(id_banco);


--
-- Name: bancos_cuentas_id_moneda_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY bancos_cuentas
    ADD CONSTRAINT bancos_cuentas_id_moneda_fkey FOREIGN KEY (id_moneda) REFERENCES contabilidad_monedas(id_moneda);


--
-- Name: bancos_cuentas_id_tipo_cuenta_banco_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY bancos_cuentas
    ADD CONSTRAINT bancos_cuentas_id_tipo_cuenta_banco_fkey FOREIGN KEY (id_tipo_cuenta_banco) REFERENCES bancos_tipos_cuenta(id_tipo_cuenta_banco);


--
-- Name: bancos_id_localidad_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY bancos
    ADD CONSTRAINT bancos_id_localidad_fkey FOREIGN KEY (id_localidad) REFERENCES ubicacion_localidades(id_localidad);


--
-- Name: bancos_id_pais_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY bancos
    ADD CONSTRAINT bancos_id_pais_fkey FOREIGN KEY (id_pais) REFERENCES ubicacion_paises(id_pais);


--
-- Name: bancos_id_provincia_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY bancos
    ADD CONSTRAINT bancos_id_provincia_fkey FOREIGN KEY (id_provincia) REFERENCES ubicacion_provincias(id_provincia);


--
-- Name: bancos_id_responsabilidad_iva_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY bancos
    ADD CONSTRAINT bancos_id_responsabilidad_iva_fkey FOREIGN KEY (id_responsabilidad_iva) REFERENCES fiscal_responsabilidades_iva(id_resoponsabildiad_iva);


--
-- Name: cajas_id_sucursal_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY cajas
    ADD CONSTRAINT cajas_id_sucursal_fkey FOREIGN KEY (id_sucursal) REFERENCES sucursales(id_sucursal);


--
-- Name: cajas_movimientos_id_caja_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY cajas_movimientos
    ADD CONSTRAINT cajas_movimientos_id_caja_fkey FOREIGN KEY (id_caja) REFERENCES cajas(id_caja);


--
-- Name: cajas_movimientos_id_categoria_movimiento_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY cajas_movimientos
    ADD CONSTRAINT cajas_movimientos_id_categoria_movimiento_fkey FOREIGN KEY (id_categoria_movimiento) REFERENCES cajas_categorias_movimientos(id_categoria_movimiento);


--
-- Name: cajas_movimientos_id_forma_pago_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY cajas_movimientos
    ADD CONSTRAINT cajas_movimientos_id_forma_pago_fkey FOREIGN KEY (id_forma_pago) REFERENCES negocio_formas_pago(id_forma_pago);


--
-- Name: cajas_movimientos_id_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY cajas_movimientos
    ADD CONSTRAINT cajas_movimientos_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario);


--
-- Name: contabilidad_plan_cuentas_id_cuenta_padre_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY contabilidad_plan_cuentas
    ADD CONSTRAINT contabilidad_plan_cuentas_id_cuenta_padre_fkey FOREIGN KEY (id_cuenta_padre) REFERENCES contabilidad_plan_cuentas(id_cuenta);


--
-- Name: contabilidad_plan_cuentas_id_tipo_cuenta_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY contabilidad_plan_cuentas
    ADD CONSTRAINT contabilidad_plan_cuentas_id_tipo_cuenta_fkey FOREIGN KEY (id_tipo_cuenta) REFERENCES contabilidad_tipos_cuenta(id_tipo_cuenta);


--
-- Name: contabilidad_registro_contable_id_libro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY contabilidad_registro_contable
    ADD CONSTRAINT contabilidad_registro_contable_id_libro_fkey FOREIGN KEY (id_libro) REFERENCES contabilidad_libros(id_libro);


--
-- Name: contabilidad_registro_contable_id_periodo_contable_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY contabilidad_registro_contable
    ADD CONSTRAINT contabilidad_registro_contable_id_periodo_contable_fkey FOREIGN KEY (id_periodo_contable) REFERENCES contabilidad_periodos_contables(id_periodo_contable);


--
-- Name: contabilidad_registro_contable_id_periodo_fiscal_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY contabilidad_registro_contable
    ADD CONSTRAINT contabilidad_registro_contable_id_periodo_fiscal_fkey FOREIGN KEY (id_periodo_fiscal) REFERENCES fiscal_periodos_fiscales(id_periodo_fiscal);


--
-- Name: contabilidad_registro_contable_id_tipo_comprobante_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY contabilidad_registro_contable
    ADD CONSTRAINT contabilidad_registro_contable_id_tipo_comprobante_fkey FOREIGN KEY (id_tipo_comprobante) REFERENCES contabilidad_tipos_comprobantes(id_tipo_comprobante);


--
-- Name: contabilidad_registro_contable_id_tipo_operacion_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY contabilidad_registro_contable
    ADD CONSTRAINT contabilidad_registro_contable_id_tipo_operacion_fkey FOREIGN KEY (id_tipo_operacion) REFERENCES contabilidad_tipos_operacion(id_tipo_operacion);


--
-- Name: contabilidad_registro_contable_lineas_id_cuenta_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY contabilidad_registro_contable_lineas
    ADD CONSTRAINT contabilidad_registro_contable_lineas_id_cuenta_fkey FOREIGN KEY (id_cuenta) REFERENCES contabilidad_plan_cuentas(id_cuenta);


--
-- Name: contabilidad_registro_contable_lineas_id_registro_contable_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY contabilidad_registro_contable_lineas
    ADD CONSTRAINT contabilidad_registro_contable_lineas_id_registro_contable_fkey FOREIGN KEY (id_registro_contable) REFERENCES contabilidad_registro_contable(id_registro) ON DELETE CASCADE;


--
-- Name: depositos_id_localidad_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY depositos
    ADD CONSTRAINT depositos_id_localidad_fkey FOREIGN KEY (id_localidad) REFERENCES ubicacion_localidades(id_localidad);


--
-- Name: depositos_id_pais_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY depositos
    ADD CONSTRAINT depositos_id_pais_fkey FOREIGN KEY (id_pais) REFERENCES ubicacion_paises(id_pais);


--
-- Name: depositos_id_provincia_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY depositos
    ADD CONSTRAINT depositos_id_provincia_fkey FOREIGN KEY (id_provincia) REFERENCES ubicacion_provincias(id_provincia);


--
-- Name: depositos_id_sucursal_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY depositos
    ADD CONSTRAINT depositos_id_sucursal_fkey FOREIGN KEY (id_sucursal) REFERENCES sucursales(id_sucursal);


--
-- Name: fiscal_letras_comprobantes_id_resoponsabildiad_iva_emisor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY fiscal_letras_comprobantes
    ADD CONSTRAINT fiscal_letras_comprobantes_id_resoponsabildiad_iva_emisor_fkey FOREIGN KEY (id_resoponsabildiad_iva_emisor) REFERENCES fiscal_responsabilidades_iva(id_resoponsabildiad_iva);


--
-- Name: fiscal_letras_comprobantes_id_resoponsabildiad_iva_recepto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY fiscal_letras_comprobantes
    ADD CONSTRAINT fiscal_letras_comprobantes_id_resoponsabildiad_iva_recepto_fkey FOREIGN KEY (id_resoponsabildiad_iva_receptor) REFERENCES fiscal_responsabilidades_iva(id_resoponsabildiad_iva);


--
-- Name: fiscal_libro_iva_ventas_id_periodo_fiscal_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY fiscal_libro_iva_ventas
    ADD CONSTRAINT fiscal_libro_iva_ventas_id_periodo_fiscal_fkey FOREIGN KEY (id_periodo_fiscal) REFERENCES fiscal_periodos_fiscales(id_periodo_fiscal);


--
-- Name: fiscal_libro_iva_ventas_id_persona_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY fiscal_libro_iva_ventas
    ADD CONSTRAINT fiscal_libro_iva_ventas_id_persona_fkey FOREIGN KEY (id_persona) REFERENCES personas(id_persona);


--
-- Name: fiscal_libro_iva_ventas_id_responsabilidad_iva_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY fiscal_libro_iva_ventas
    ADD CONSTRAINT fiscal_libro_iva_ventas_id_responsabilidad_iva_fkey FOREIGN KEY (id_responsabilidad_iva) REFERENCES fiscal_responsabilidades_iva(id_resoponsabildiad_iva);


--
-- Name: fiscal_libro_iva_ventas_lineas_id_alicuota_iva_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY fiscal_libro_iva_ventas_lineas
    ADD CONSTRAINT fiscal_libro_iva_ventas_lineas_id_alicuota_iva_fkey FOREIGN KEY (id_alicuota_iva) REFERENCES fiscal_alicuotas_iva(id_alicuota_iva);


--
-- Name: fiscal_libro_iva_ventas_lineas_id_factura_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY fiscal_libro_iva_ventas_lineas
    ADD CONSTRAINT fiscal_libro_iva_ventas_lineas_id_factura_fkey FOREIGN KEY (id_factura) REFERENCES fiscal_libro_iva_ventas(id_factura);


--
-- Name: fk_estado; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas
    ADD CONSTRAINT fk_estado FOREIGN KEY (id_estado) REFERENCES ventas_estados(id_estado);


--
-- Name: fk_id_movimiento_caja; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas_pagos_lineas
    ADD CONSTRAINT fk_id_movimiento_caja FOREIGN KEY (id_movimiento_caja) REFERENCES cajas_movimientos(id_movimiento_caja);


--
-- Name: fk_id_movimiento_caja; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas_pagos
    ADD CONSTRAINT fk_id_movimiento_caja FOREIGN KEY (id_movimiento_caja) REFERENCES cajas_movimientos(id_movimiento_caja);


--
-- Name: fk_id_registro_contable; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY fiscal_libro_iva_ventas
    ADD CONSTRAINT fk_id_registro_contable FOREIGN KEY (id_registro_contable) REFERENCES contabilidad_registro_contable(id_registro);


--
-- Name: fk_personas; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas_pagos
    ADD CONSTRAINT fk_personas FOREIGN KEY (id_persona) REFERENCES personas(id_persona);


--
-- Name: fk_registro_contable; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY personas_cuenta_corriente
    ADD CONSTRAINT fk_registro_contable FOREIGN KEY (id_registro_contable) REFERENCES contabilidad_registro_contable(id_registro);


--
-- Name: fk_sucursal; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY personas
    ADD CONSTRAINT fk_sucursal FOREIGN KEY (id_sucursal) REFERENCES sucursales(id_sucursal);


--
-- Name: fk_sucursal; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas_pagos
    ADD CONSTRAINT fk_sucursal FOREIGN KEY (id_sucursal) REFERENCES sucursales(id_sucursal);


--
-- Name: fk_usuarios; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas_pagos
    ADD CONSTRAINT fk_usuarios FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario);


--
-- Name: legal_generos_id_tipo_personeria_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY legal_generos
    ADD CONSTRAINT legal_generos_id_tipo_personeria_fkey FOREIGN KEY (id_tipo_personeria) REFERENCES legal_tipos_personeria(id_tipo_personeria);


--
-- Name: legal_tipos_documento_id_tipo_personeria_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY legal_tipos_documento
    ADD CONSTRAINT legal_tipos_documento_id_tipo_personeria_fkey FOREIGN KEY (id_tipo_personeria) REFERENCES legal_tipos_personeria(id_tipo_personeria);


--
-- Name: personas_cuenta_corriente_id_persona_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY personas_cuenta_corriente
    ADD CONSTRAINT personas_cuenta_corriente_id_persona_fkey FOREIGN KEY (id_persona) REFERENCES personas(id_persona);


--
-- Name: personas_id_genero_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY personas
    ADD CONSTRAINT personas_id_genero_fkey FOREIGN KEY (id_genero) REFERENCES legal_generos(id_genero);


--
-- Name: personas_id_localidad_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY personas
    ADD CONSTRAINT personas_id_localidad_fkey FOREIGN KEY (id_localidad) REFERENCES ubicacion_localidades(id_localidad);


--
-- Name: personas_id_pais_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY personas
    ADD CONSTRAINT personas_id_pais_fkey FOREIGN KEY (id_pais) REFERENCES ubicacion_paises(id_pais);


--
-- Name: personas_id_provincia_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY personas
    ADD CONSTRAINT personas_id_provincia_fkey FOREIGN KEY (id_provincia) REFERENCES ubicacion_provincias(id_provincia);


--
-- Name: personas_id_responsabilidad_iva_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY personas
    ADD CONSTRAINT personas_id_responsabilidad_iva_fkey FOREIGN KEY (id_responsabilidad_iva) REFERENCES fiscal_responsabilidades_iva(id_resoponsabildiad_iva);


--
-- Name: personas_id_tipo_documento_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY personas
    ADD CONSTRAINT personas_id_tipo_documento_fkey FOREIGN KEY (id_tipo_documento) REFERENCES legal_tipos_documento(id_tipo_documento);


--
-- Name: personas_id_tipo_personeria_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY personas
    ADD CONSTRAINT personas_id_tipo_personeria_fkey FOREIGN KEY (id_tipo_personeria) REFERENCES legal_tipos_personeria(id_tipo_personeria);


--
-- Name: personas_imagenes_id_persona_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY personas_imagenes
    ADD CONSTRAINT personas_imagenes_id_persona_fkey FOREIGN KEY (id_persona) REFERENCES personas(id_persona);


--
-- Name: personas_imagenes_id_tipo_imagen_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY personas_imagenes
    ADD CONSTRAINT personas_imagenes_id_tipo_imagen_fkey FOREIGN KEY (id_tipo_imagen) REFERENCES personas_tipos_imagenes(id_tipo_imagen);


--
-- Name: personas_telefonos_id_persona_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY personas_telefonos
    ADD CONSTRAINT personas_telefonos_id_persona_fkey FOREIGN KEY (id_persona) REFERENCES personas(id_persona) ON DELETE CASCADE;


--
-- Name: privilegios_gruposx_id_grupo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY privilegios_gruposx
    ADD CONSTRAINT privilegios_gruposx_id_grupo_fkey FOREIGN KEY (id_grupo) REFERENCES usuarios_grupos(id_grupo);


--
-- Name: privilegios_gruposx_id_privilegio_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY privilegios_gruposx
    ADD CONSTRAINT privilegios_gruposx_id_privilegio_fkey FOREIGN KEY (id_privilegio) REFERENCES privilegios(id_privilegio);


--
-- Name: productos_id_alicuota_iva_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY productos
    ADD CONSTRAINT productos_id_alicuota_iva_fkey FOREIGN KEY (id_alicuota_iva) REFERENCES fiscal_alicuotas_iva(id_alicuota_iva);


--
-- Name: productos_id_proveedor_habitual_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY productos
    ADD CONSTRAINT productos_id_proveedor_habitual_fkey FOREIGN KEY (id_proveedor_habitual) REFERENCES personas(id_persona);


--
-- Name: productos_id_rubro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY productos
    ADD CONSTRAINT productos_id_rubro_fkey FOREIGN KEY (id_rubro) REFERENCES productos_rubros(id_rubro);


--
-- Name: productos_id_sub_rubro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY productos
    ADD CONSTRAINT productos_id_sub_rubro_fkey FOREIGN KEY (id_sub_rubro) REFERENCES productos_sub_rubros(id_sub_rubro);


--
-- Name: productos_id_tipo_proveeduria_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY productos
    ADD CONSTRAINT productos_id_tipo_proveeduria_fkey FOREIGN KEY (id_tipo_proveeduria) REFERENCES productos_tipos_proveeduria(id_tipo_proveeduria);


--
-- Name: productos_id_tipo_unidad_compra_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY productos
    ADD CONSTRAINT productos_id_tipo_unidad_compra_fkey FOREIGN KEY (id_tipo_unidad_compra) REFERENCES productos_tipos_unidades(id_tipo_unidad);


--
-- Name: productos_id_tipo_unidad_venta_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY productos
    ADD CONSTRAINT productos_id_tipo_unidad_venta_fkey FOREIGN KEY (id_tipo_unidad_venta) REFERENCES productos_tipos_unidades(id_tipo_unidad);


--
-- Name: productos_imagenes_id_producto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY productos_imagenes
    ADD CONSTRAINT productos_imagenes_id_producto_fkey FOREIGN KEY (id_producto) REFERENCES productos(id_producto);


--
-- Name: productos_imagenes_id_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY productos_imagenes
    ADD CONSTRAINT productos_imagenes_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario);


--
-- Name: productos_porcentajes_id_producto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY productos_porcentajes
    ADD CONSTRAINT productos_porcentajes_id_producto_fkey FOREIGN KEY (id_producto) REFERENCES productos(id_producto);


--
-- Name: productos_sub_rubros_id_rubro_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY productos_sub_rubros
    ADD CONSTRAINT productos_sub_rubros_id_rubro_fkey FOREIGN KEY (id_rubro) REFERENCES productos_rubros(id_rubro);


--
-- Name: productos_x_caracteristicas_id_caracteristica_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY productos_x_caracteristicas
    ADD CONSTRAINT productos_x_caracteristicas_id_caracteristica_fkey FOREIGN KEY (id_caracteristica) REFERENCES productos_caracteristicas(id_caracteristica);


--
-- Name: productos_x_caracteristicas_id_producto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY productos_x_caracteristicas
    ADD CONSTRAINT productos_x_caracteristicas_id_producto_fkey FOREIGN KEY (id_producto) REFERENCES productos(id_producto);


--
-- Name: proveedores_ordenes_compra_id_condicion_compra_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY proveedores_ordenes_compra
    ADD CONSTRAINT proveedores_ordenes_compra_id_condicion_compra_fkey FOREIGN KEY (id_condicion_compra) REFERENCES negocio_condiciones_operaciones(id_condicion);


--
-- Name: proveedores_ordenes_compra_id_proveedor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY proveedores_ordenes_compra
    ADD CONSTRAINT proveedores_ordenes_compra_id_proveedor_fkey FOREIGN KEY (id_proveedor) REFERENCES personas(id_persona);


--
-- Name: proveedores_ordenes_compra_id_sucursal_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY proveedores_ordenes_compra
    ADD CONSTRAINT proveedores_ordenes_compra_id_sucursal_fkey FOREIGN KEY (id_sucursal) REFERENCES sucursales(id_sucursal);


--
-- Name: proveedores_ordenes_compra_id_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY proveedores_ordenes_compra
    ADD CONSTRAINT proveedores_ordenes_compra_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario);


--
-- Name: proveedores_ordenes_compra_lineas_id_orden_compra_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY proveedores_ordenes_compra_lineas
    ADD CONSTRAINT proveedores_ordenes_compra_lineas_id_orden_compra_fkey FOREIGN KEY (id_orden_compra) REFERENCES proveedores_ordenes_compra(id_orden_compra);


--
-- Name: proveedores_ordenes_compra_lineas_id_producto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY proveedores_ordenes_compra_lineas
    ADD CONSTRAINT proveedores_ordenes_compra_lineas_id_producto_fkey FOREIGN KEY (id_producto) REFERENCES productos(id_producto);


--
-- Name: stock_movimientos_id_deposito_movimiento_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY stock_movimientos
    ADD CONSTRAINT stock_movimientos_id_deposito_movimiento_fkey FOREIGN KEY (id_deposito_movimiento) REFERENCES depositos(id_deposito);


--
-- Name: stock_movimientos_id_producto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY stock_movimientos
    ADD CONSTRAINT stock_movimientos_id_producto_fkey FOREIGN KEY (id_producto) REFERENCES productos(id_producto);


--
-- Name: stock_movimientos_id_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY stock_movimientos
    ADD CONSTRAINT stock_movimientos_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario);


--
-- Name: sucursales_id_localidad_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY sucursales
    ADD CONSTRAINT sucursales_id_localidad_fkey FOREIGN KEY (id_localidad) REFERENCES ubicacion_localidades(id_localidad);


--
-- Name: sucursales_id_pais_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY sucursales
    ADD CONSTRAINT sucursales_id_pais_fkey FOREIGN KEY (id_pais) REFERENCES ubicacion_paises(id_pais);


--
-- Name: sucursales_id_provincia_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY sucursales
    ADD CONSTRAINT sucursales_id_provincia_fkey FOREIGN KEY (id_provincia) REFERENCES ubicacion_provincias(id_provincia);


--
-- Name: ubicacion_localidades_id_provincia_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ubicacion_localidades
    ADD CONSTRAINT ubicacion_localidades_id_provincia_fkey FOREIGN KEY (id_provincia) REFERENCES ubicacion_provincias(id_provincia);


--
-- Name: ubicacion_provincias_id_pais_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ubicacion_provincias
    ADD CONSTRAINT ubicacion_provincias_id_pais_fkey FOREIGN KEY (id_pais) REFERENCES ubicacion_paises(id_pais);


--
-- Name: usuarios_gruposx_id_grupo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY usuarios_gruposx
    ADD CONSTRAINT usuarios_gruposx_id_grupo_fkey FOREIGN KEY (id_grupo) REFERENCES usuarios_grupos(id_grupo);


--
-- Name: usuarios_gruposx_id_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY usuarios_gruposx
    ADD CONSTRAINT usuarios_gruposx_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario);


--
-- Name: usuarios_id_sucursal_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY usuarios
    ADD CONSTRAINT usuarios_id_sucursal_fkey FOREIGN KEY (id_sucursal) REFERENCES sucursales(id_sucursal);


--
-- Name: ventas_cargos_fijos_id_producto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas_cargos_fijos
    ADD CONSTRAINT ventas_cargos_fijos_id_producto_fkey FOREIGN KEY (id_producto) REFERENCES productos(id_producto);


--
-- Name: ventas_estados_historico_id_estado_actual_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas_estados_historico
    ADD CONSTRAINT ventas_estados_historico_id_estado_actual_fkey FOREIGN KEY (id_estado_actual) REFERENCES ventas_estados(id_estado);


--
-- Name: ventas_estados_historico_id_estado_anterior_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas_estados_historico
    ADD CONSTRAINT ventas_estados_historico_id_estado_anterior_fkey FOREIGN KEY (id_estado_anterior) REFERENCES ventas_estados(id_estado);


--
-- Name: ventas_estados_historico_id_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas_estados_historico
    ADD CONSTRAINT ventas_estados_historico_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario);


--
-- Name: ventas_estados_historico_id_venta_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas_estados_historico
    ADD CONSTRAINT ventas_estados_historico_id_venta_fkey FOREIGN KEY (id_venta) REFERENCES ventas(id_venta);


--
-- Name: ventas_id_condicion_venta_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas
    ADD CONSTRAINT ventas_id_condicion_venta_fkey FOREIGN KEY (id_condicion_venta) REFERENCES negocio_condiciones_operaciones(id_condicion);


--
-- Name: ventas_id_persona_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas
    ADD CONSTRAINT ventas_id_persona_fkey FOREIGN KEY (id_persona) REFERENCES personas(id_persona);


--
-- Name: ventas_id_registro_iva_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas
    ADD CONSTRAINT ventas_id_registro_iva_fkey FOREIGN KEY (id_registro_iva) REFERENCES fiscal_libro_iva_ventas(id_factura);


--
-- Name: ventas_id_sucursal_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas
    ADD CONSTRAINT ventas_id_sucursal_fkey FOREIGN KEY (id_sucursal) REFERENCES sucursales(id_sucursal);


--
-- Name: ventas_id_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas
    ADD CONSTRAINT ventas_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario);


--
-- Name: ventas_lineas_id_linea_venta_referencia_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas_lineas
    ADD CONSTRAINT ventas_lineas_id_linea_venta_referencia_fkey FOREIGN KEY (id_linea_venta_referencia) REFERENCES ventas_lineas(id_linea_venta);


--
-- Name: ventas_lineas_id_producto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas_lineas
    ADD CONSTRAINT ventas_lineas_id_producto_fkey FOREIGN KEY (id_producto) REFERENCES productos(id_producto);


--
-- Name: ventas_lineas_id_venta_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas_lineas
    ADD CONSTRAINT ventas_lineas_id_venta_fkey FOREIGN KEY (id_venta) REFERENCES ventas(id_venta);


--
-- Name: ventas_pago_lineas_id_pago_venta_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas_pagos_lineas
    ADD CONSTRAINT ventas_pago_lineas_id_pago_venta_fkey FOREIGN KEY (id_pago_venta) REFERENCES ventas_pagos(id_pago_venta);


--
-- Name: ventas_pago_lineas_id_venta_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas_pagos_lineas
    ADD CONSTRAINT ventas_pago_lineas_id_venta_fkey FOREIGN KEY (id_venta) REFERENCES ventas(id_venta);


--
-- Name: ventas_pagos_id_forma_pago_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas_pagos
    ADD CONSTRAINT ventas_pagos_id_forma_pago_fkey FOREIGN KEY (id_forma_pago) REFERENCES negocio_formas_pago(id_forma_pago);


--
-- Name: ventas_remitos_id_sucursal_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas_remitos
    ADD CONSTRAINT ventas_remitos_id_sucursal_fkey FOREIGN KEY (id_sucursal) REFERENCES sucursales(id_sucursal);


--
-- Name: ventas_remitos_id_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas_remitos
    ADD CONSTRAINT ventas_remitos_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario);


--
-- Name: ventas_remitos_id_venta_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas_remitos
    ADD CONSTRAINT ventas_remitos_id_venta_fkey FOREIGN KEY (id_venta) REFERENCES ventas(id_venta);


--
-- Name: ventas_remitos_lineas_id_producto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas_remitos_lineas
    ADD CONSTRAINT ventas_remitos_lineas_id_producto_fkey FOREIGN KEY (id_producto) REFERENCES productos(id_producto);


--
-- Name: ventas_remitos_lineas_id_remito_fkey; Type: FK CONSTRAINT; Schema: public; Owner: retail
--

ALTER TABLE ONLY ventas_remitos_lineas
    ADD CONSTRAINT ventas_remitos_lineas_id_remito_fkey FOREIGN KEY (id_remito) REFERENCES ventas_remitos(id_remito);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--


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

INSERT INTO ubicacion_provincias (id_provincia, id_pais, nombre_provincia, version) VALUES
(1,	1,	'CHACO',	0);


INSERT INTO ubicacion_localidades(id_localidad,id_provincia,nombre_localidad,codigo_postal,version) values
(1,1,'RESISTENCIA','3500',0);
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


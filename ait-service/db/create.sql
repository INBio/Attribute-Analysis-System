--
-- PostgreSQL database dump
--

-- Started on 2010-07-09 15:49:10 CST

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 7 (class 2615 OID 22697)
-- Name: ait; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA ait;


ALTER SCHEMA ait OWNER TO postgres;

SET search_path = ait, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1492 (class 1259 OID 22698)
-- Dependencies: 7
-- Name: darwin_core; Type: TABLE; Schema: ait; Owner: postgres; Tablespace:
--

CREATE TABLE darwin_core (
    globaluniqueidentifier character varying NOT NULL,
    datelastmodified timestamp without time zone NOT NULL,
    institutioncode character varying NOT NULL,
    collectioncode character varying NOT NULL,
    catalognumber character varying NOT NULL,
    catalognumbernumeric numeric,
    scientificname character varying,
    basisofrecord character varying NOT NULL,
    informationwithheld character varying,
    kingdomid numeric,
    phylum_id numeric,
    class_id numeric,
    orders_id numeric,
    family_id numeric,
    genus_id numeric,
    specificepithet_id numeric,
    infraspecificepithet_id numeric,
    highertaxon character varying,
    kingdom character varying,
    phylum character varying,
    class character varying,
    orders character varying,
    family character varying,
    genus character varying,
    specificepithet character varying,
    infraspecificepithet character varying,
    infraspecificrank character varying,
    authoryearofscientificname character varying,
    nomenclaturalcode character varying,
    identificationqualifier character varying,
    identifiedby character varying,
    dateidentified timestamp without time zone,
    typestatus character varying,
    collectingmethod character varying,
    validdistributionflag character varying,
    collectornumber character varying,
    fieldnumber character varying,
    collector character varying,
    earliestdatecollected timestamp without time zone,
    latestdatecollected timestamp without time zone,
    verbatimcollectingdate character varying,
    dayofyear numeric,
    fieldnotes character varying,
    highergeography character varying,
    continent character varying,
    waterbody character varying,
    islandgroup character varying,
    island character varying,
    country character varying,
    stateprovince character varying,
    county character varying,
    locality character varying,
    decimallongitude character varying,
    verbatimlongitude character varying,
    decimallatitude character varying,
    verbatimlatitude character varying,
    geodeticdatum character varying,
    verbatimcoordinatesystem character varying,
    georeferenceprotocol character varying,
    coordinateuncertaintyinmeters character varying,
    georeferenceremarks character varying,
    footprintwkt character varying,
    minimumelevationinmeters double precision,
    maximumelevationinmeters double precision,
    verbatimelevation double precision,
    minimumdepthinmeters double precision,
    maximumdepthinmeters double precision,
    sex character varying,
    lifestage character varying,
    preparations character varying,
    individualcount numeric,
    genbanknum character varying,
    othercatalognumbers character varying,
    relatedcatalogitems character varying,
    remarks character varying,
    attributes character varying,
    imageurl character varying,
    relatedinformation character varying,
    disposition character varying,
    pointradiusspatialfit numeric,
    footprintspatialfit numeric,
    verbatimcoordinates character varying,
    georeferencesources character varying,
    georeferenceverificationstatus character varying
);


ALTER TABLE ait.darwin_core OWNER TO postgres;

--
-- TOC entry 1493 (class 1259 OID 22704)
-- Dependencies: 7
-- Name: indicator; Type: TABLE; Schema: ait; Owner: postgres; Tablespace:
--

CREATE TABLE indicator (
    indicator_id numeric NOT NULL,
    indicator_name character varying(200) NOT NULL,
    indicator_description character varying(400),
    indicator_applies_to_part numeric,
    indicator_ancestor_id numeric,
    indicator_references character varying(500)
);


ALTER TABLE ait.indicator OWNER TO postgres;

--
-- TOC entry 1494 (class 1259 OID 22710)
-- Dependencies: 7
-- Name: plinian_core; Type: TABLE; Schema: ait; Owner: postgres; Tablespace:
--

CREATE TABLE plinian_core (
    globaluniqueidentifier character varying NOT NULL,
    scientificname character varying,
    institutioncode character varying,
    datelastmodified timestamp without time zone,
    taxonrecordid character varying,
    language character varying,
    creators character varying,
    distribution character varying,
    abstract character varying,
    kingdomtaxon character varying,
    phylumtaxon character varying,
    classtaxon character varying,
    ordertaxon character varying,
    familytaxon character varying,
    genustaxon character varying,
    synonyms character varying,
    authoryearofscientificname character varying,
    speciespublicationreference character varying,
    commonnames character varying,
    typification character varying,
    contributors character varying,
    datecreated timestamp without time zone,
    habit character varying,
    lifecycle character varying,
    reproduction character varying,
    annualcycle character varying,
    scientificdescription character varying,
    briefdescription character varying,
    feeding character varying,
    behavior character varying,
    interactions character varying,
    chromosomicnumbern character varying,
    moleculardata character varying,
    populationbiology character varying,
    threatstatus character varying,
    legislation character varying,
    habitat character varying,
    territory character varying,
    endemicity character varying,
    theuses character varying,
    themanagement character varying,
    folklore character varying,
    thereferences character varying,
    unstructureddocumentation character varying,
    otherinformationsources character varying,
    papers character varying,
    identificationkeys character varying,
    migratorydata character varying,
    ecologicalsignificance character varying,
    unstructurednaturalhistory character varying,
    invasivenessdata character varying,
    targetaudiences character varying,
    version character varying,
    urlimage1 character varying,
    captionimage1 character varying,
    urlimage2 character varying,
    captionimage2 character varying,
    urlimage3 character varying,
    captionimage3 character varying
);


ALTER TABLE ait.plinian_core OWNER TO postgres;

--
-- TOC entry 1495 (class 1259 OID 22716)
-- Dependencies: 7
-- Name: selected_layer_seq; Type: SEQUENCE; Schema: ait; Owner: postgres
--

CREATE SEQUENCE selected_layer_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ait.selected_layer_seq OWNER TO postgres;

--
-- TOC entry 1813 (class 0 OID 0)
-- Dependencies: 1495
-- Name: selected_layer_seq; Type: SEQUENCE SET; Schema: ait; Owner: postgres
--

SELECT pg_catalog.setval('selected_layer_seq', 5, true);


--
-- TOC entry 1496 (class 1259 OID 22718)
-- Dependencies: 1771 1772 7
-- Name: selected_layer; Type: TABLE; Schema: ait; Owner: postgres; Tablespace:
--

CREATE TABLE selected_layer (
    id numeric DEFAULT nextval('selected_layer_seq'::regclass) NOT NULL,
    name character varying(500) NOT NULL,
    base numeric DEFAULT 0 NOT NULL
);


ALTER TABLE ait.selected_layer OWNER TO postgres;

--
-- TOC entry 1497 (class 1259 OID 22726)
-- Dependencies: 7
-- Name: taxon_index_seq; Type: SEQUENCE; Schema: ait; Owner: postgres
--

CREATE SEQUENCE taxon_index_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ait.taxon_index_seq OWNER TO postgres;

--
-- TOC entry 1814 (class 0 OID 0)
-- Dependencies: 1497
-- Name: taxon_index_seq; Type: SEQUENCE SET; Schema: ait; Owner: postgres
--

SELECT pg_catalog.setval('taxon_index_seq', 65, true);


--
-- TOC entry 1498 (class 1259 OID 22728)
-- Dependencies: 1773 7
-- Name: taxon_index; Type: TABLE; Schema: ait; Owner: postgres; Tablespace:
--

CREATE TABLE taxon_index (
    taxon_id numeric DEFAULT nextval('taxon_index_seq'::regclass) NOT NULL,
    taxon_name character varying(200) NOT NULL,
    taxon_range numeric NOT NULL
);


ALTER TABLE ait.taxon_index OWNER TO postgres;

--
-- TOC entry 1499 (class 1259 OID 22735)
-- Dependencies: 7
-- Name: taxon_indicator_seq; Type: SEQUENCE; Schema: ait; Owner: postgres
--

CREATE SEQUENCE taxon_indicator_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ait.taxon_indicator_seq OWNER TO postgres;

--
-- TOC entry 1815 (class 0 OID 0)
-- Dependencies: 1499
-- Name: taxon_indicator_seq; Type: SEQUENCE SET; Schema: ait; Owner: postgres
--

SELECT pg_catalog.setval('taxon_indicator_seq', 1, true);


--
-- TOC entry 1500 (class 1259 OID 22737)
-- Dependencies: 1774 7
-- Name: taxon_indicator; Type: TABLE; Schema: ait; Owner: postgres; Tablespace:
--

CREATE TABLE taxon_indicator (
    taxon_indicator_id numeric DEFAULT nextval('taxon_indicator_seq'::regclass) NOT NULL,
    taxon_indicator_certainty_level character varying(200),
    taxon_indicator_evaluation_criteria character varying(200),
    taxon_indicator_regionality character varying(200),
    taxon_indicator_temporality character varying(200),
    taxon_indicator_references character varying(3000),
    taxon_indicator_notes character varying(4000),
    taxon_indicator_valuer_person character varying(200),
    taxon_scientific_name character varying(200) NOT NULL,
    indicator_id numeric NOT NULL,
    component_part character varying(200)
);


ALTER TABLE ait.taxon_indicator OWNER TO postgres;

--
-- TOC entry 1501 (class 1259 OID 22744)
-- Dependencies: 7
-- Name: taxon_indicator_plain; Type: TABLE; Schema: ait; Owner: postgres; Tablespace:
--

CREATE TABLE taxon_indicator_plain (
    indicator character varying(200) NOT NULL,
    scientific_name character varying(200) NOT NULL
);


ALTER TABLE ait.taxon_indicator_plain OWNER TO postgres;

--
-- TOC entry 1816 (class 0 OID 0)
-- Dependencies: 1501
-- Name: TABLE taxon_indicator_plain; Type: COMMENT; Schema: ait; Owner: postgres
--

COMMENT ON TABLE taxon_indicator_plain IS 'Tabla inicial de la cual se gererarán las tablas ait.indicator y ait.taxon_indicator a través de un proceso de indexaxión de información de indicadores taxonómicos.';


--
-- TOC entry 1502 (class 1259 OID 22747)
-- Dependencies: 7
-- Name: taxon_info_index_seq; Type: SEQUENCE; Schema: ait; Owner: postgres
--

CREATE SEQUENCE taxon_info_index_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE ait.taxon_info_index_seq OWNER TO postgres;

--
-- TOC entry 1817 (class 0 OID 0)
-- Dependencies: 1502
-- Name: taxon_info_index_seq; Type: SEQUENCE SET; Schema: ait; Owner: postgres
--

SELECT pg_catalog.setval('taxon_info_index_seq', 4269, true);


--
-- TOC entry 1503 (class 1259 OID 22749)
-- Dependencies: 1775 7
-- Name: taxon_info_index; Type: TABLE; Schema: ait; Owner: postgres; Tablespace:
--

CREATE TABLE taxon_info_index (
    globaluniqueidentifier character varying NOT NULL,
    kingdom_id numeric,
    phylum_id numeric,
    class_id numeric,
    order_id numeric,
    family_id numeric,
    genus_id numeric,
    specific_epithet_id numeric,
    scientific_name_id numeric,
    indicator_id numeric,
    polygom_id numeric,
    row_id numeric DEFAULT nextval('taxon_info_index_seq'::regclass) NOT NULL,
    layer_table character varying
);


ALTER TABLE ait.taxon_info_index OWNER TO postgres;

--
-- TOC entry 1504 (class 1259 OID 22756)
-- Dependencies: 1776 7
-- Name: users; Type: TABLE; Schema: ait; Owner: postgres; Tablespace:
--

CREATE TABLE users (
    user_id numeric NOT NULL,
    fullname character varying(500),
    username character varying(100) NOT NULL,
    password character varying(500) NOT NULL,
    enabled boolean DEFAULT true NOT NULL,
    roles character varying(500) NOT NULL
);


ALTER TABLE ait.users OWNER TO postgres;

--
-- TOC entry 1780 (class 2606 OID 22764)
-- Dependencies: 1493 1493
-- Name: indicator_PK; Type: CONSTRAINT; Schema: ait; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY indicator
    ADD CONSTRAINT "indicator_PK" PRIMARY KEY (indicator_id);


--
-- TOC entry 1784 (class 2606 OID 22766)
-- Dependencies: 1496 1496
-- Name: selected_layerPK; Type: CONSTRAINT; Schema: ait; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY selected_layer
    ADD CONSTRAINT "selected_layerPK" PRIMARY KEY (id);


--
-- TOC entry 1782 (class 2606 OID 22768)
-- Dependencies: 1494 1494
-- Name: species_pkey; Type: CONSTRAINT; Schema: ait; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY plinian_core
    ADD CONSTRAINT species_pkey PRIMARY KEY (globaluniqueidentifier);


--
-- TOC entry 1778 (class 2606 OID 22770)
-- Dependencies: 1492 1492
-- Name: specimen_pkey; Type: CONSTRAINT; Schema: ait; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY darwin_core
    ADD CONSTRAINT specimen_pkey PRIMARY KEY (globaluniqueidentifier);


--
-- TOC entry 1798 (class 2606 OID 22772)
-- Dependencies: 1504 1504
-- Name: system_user_pkey; Type: CONSTRAINT; Schema: ait; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY users
    ADD CONSTRAINT system_user_pkey PRIMARY KEY (user_id);


--
-- TOC entry 1800 (class 2606 OID 22774)
-- Dependencies: 1504 1504
-- Name: system_user_username_key; Type: CONSTRAINT; Schema: ait; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY users
    ADD CONSTRAINT system_user_username_key UNIQUE (username);


--
-- TOC entry 1788 (class 2606 OID 22776)
-- Dependencies: 1498 1498
-- Name: taxon_index_PK; Type: CONSTRAINT; Schema: ait; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY taxon_index
    ADD CONSTRAINT "taxon_index_PK" PRIMARY KEY (taxon_id);


--
-- TOC entry 1792 (class 2606 OID 22778)
-- Dependencies: 1500 1500
-- Name: taxon_indicator_pk; Type: CONSTRAINT; Schema: ait; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY taxon_indicator
    ADD CONSTRAINT taxon_indicator_pk PRIMARY KEY (taxon_indicator_id);


--
-- TOC entry 1794 (class 2606 OID 22780)
-- Dependencies: 1501 1501 1501
-- Name: taxon_indicator_plain_PK; Type: CONSTRAINT; Schema: ait; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY taxon_indicator_plain
    ADD CONSTRAINT "taxon_indicator_plain_PK" PRIMARY KEY (indicator, scientific_name);


--
-- TOC entry 1796 (class 2606 OID 22782)
-- Dependencies: 1503 1503
-- Name: taxon_info_incex_pk; Type: CONSTRAINT; Schema: ait; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY taxon_info_index
    ADD CONSTRAINT taxon_info_incex_pk PRIMARY KEY (row_id);


--
-- TOC entry 1786 (class 2606 OID 22784)
-- Dependencies: 1496 1496
-- Name: unique_name; Type: CONSTRAINT; Schema: ait; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY selected_layer
    ADD CONSTRAINT unique_name UNIQUE (name);


--
-- TOC entry 1790 (class 2606 OID 22786)
-- Dependencies: 1498 1498
-- Name: unique_taxon_name; Type: CONSTRAINT; Schema: ait; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY taxon_index
    ADD CONSTRAINT unique_taxon_name UNIQUE (taxon_name);


--
-- TOC entry 1801 (class 2606 OID 22787)
-- Dependencies: 1779 1493 1493
-- Name: indicator_ancestor_id_fk; Type: FK CONSTRAINT; Schema: ait; Owner: postgres
--

ALTER TABLE ONLY indicator
    ADD CONSTRAINT indicator_ancestor_id_fk FOREIGN KEY (indicator_ancestor_id) REFERENCES indicator(indicator_id);


INSERT INTO users (user_id, fullname, username, password, enabled, roles) VALUES (1, 'Administrador', 'admin', '5fd060ad4189a1ffaa659d8c14d145b2', true, 'ROLE_USER,ROLE_ADMIN');
INSERT INTO users (user_id, fullname, username, password, enabled, roles) VALUES (2, 'Usuario de prueba', 'beta', '987bcab01b929eb2c07877b224215c92', true, 'ROLE_USER');

-- Completed on 2010-07-09 15:49:11 CST

--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database dump
--

-- Started on 2010-06-17 08:56:09 CST

SET client_encoding = 'SQL_ASCII';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 7 (class 2615 OID 1917518)
-- Name: ait; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA ait;


ALTER SCHEMA ait OWNER TO postgres;

SET search_path = ait, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 2332 (class 1259 OID 1917519)
-- Dependencies: 7
-- Name: darwin_core; Type: TABLE; Schema: ait; Owner: postgres; Tablespace: 
--

CREATE TABLE darwin_core (
    globaluniqueidentifier character varying NOT NULL,
    datelastmodified timestamp without time zone,
    institutioncode character varying,
    collectioncode character varying,
    catalognumber character varying,
    catalognumbernumeric numeric,
    scientificname character varying,
    basisofrecord character varying,
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
-- TOC entry 2333 (class 1259 OID 1917525)
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
-- TOC entry 2334 (class 1259 OID 1917531)
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
-- TOC entry 2335 (class 1259 OID 1917537)
-- Dependencies: 7
-- Name: taxon_index; Type: TABLE; Schema: ait; Owner: postgres; Tablespace: 
--

CREATE TABLE taxon_index (
    taxon_id numeric NOT NULL,
    taxon_name character varying(200) NOT NULL,
    taxon_range numeric NOT NULL
);


ALTER TABLE ait.taxon_index OWNER TO postgres;

--
-- TOC entry 2336 (class 1259 OID 1917543)
-- Dependencies: 7
-- Name: taxon_indicator; Type: TABLE; Schema: ait; Owner: postgres; Tablespace: 
--

CREATE TABLE taxon_indicator (
    taxon_indicator_id numeric NOT NULL,
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
-- TOC entry 2337 (class 1259 OID 1917549)
-- Dependencies: 7
-- Name: taxon_indicator_plain; Type: TABLE; Schema: ait; Owner: postgres; Tablespace: 
--

CREATE TABLE taxon_indicator_plain (
    indicator character varying(200) NOT NULL,
    scientific_name character varying(200) NOT NULL
);


ALTER TABLE ait.taxon_indicator_plain OWNER TO postgres;

--
-- TOC entry 2641 (class 0 OID 0)
-- Dependencies: 2337
-- Name: TABLE taxon_indicator_plain; Type: COMMENT; Schema: ait; Owner: postgres
--

COMMENT ON TABLE taxon_indicator_plain IS 'Tabla inicial de la cual se gererarán las tablas ait.indicator y ait.taxon_indicator a través de un proceso de indexaxión de información de indicadores taxonómicos.';


--
-- TOC entry 2338 (class 1259 OID 1917552)
-- Dependencies: 7
-- Name: taxon_info_index; Type: TABLE; Schema: ait; Owner: postgres; Tablespace: 
--

CREATE TABLE taxon_info_index (
    globaluniqueidentifier character varying(70) NOT NULL,
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
    row_id numeric NOT NULL,
    layer_table character varying
);


ALTER TABLE ait.taxon_info_index OWNER TO postgres;

--
-- TOC entry 2339 (class 1259 OID 1918766)
-- Dependencies: 2619 7
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
-- TOC entry 2623 (class 2606 OID 1917559)
-- Dependencies: 2333 2333
-- Name: indicator_PK; Type: CONSTRAINT; Schema: ait; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY indicator
    ADD CONSTRAINT "indicator_PK" PRIMARY KEY (indicator_id);


--
-- TOC entry 2625 (class 2606 OID 1917561)
-- Dependencies: 2334 2334
-- Name: species_pkey; Type: CONSTRAINT; Schema: ait; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY plinian_core
    ADD CONSTRAINT species_pkey PRIMARY KEY (globaluniqueidentifier);


--
-- TOC entry 2621 (class 2606 OID 1917563)
-- Dependencies: 2332 2332
-- Name: specimen_pkey; Type: CONSTRAINT; Schema: ait; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY darwin_core
    ADD CONSTRAINT specimen_pkey PRIMARY KEY (globaluniqueidentifier);


--
-- TOC entry 2635 (class 2606 OID 1918774)
-- Dependencies: 2339 2339
-- Name: system_user_pkey; Type: CONSTRAINT; Schema: ait; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT system_user_pkey PRIMARY KEY (user_id);


--
-- TOC entry 2637 (class 2606 OID 1918776)
-- Dependencies: 2339 2339
-- Name: system_user_username_key; Type: CONSTRAINT; Schema: ait; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT system_user_username_key UNIQUE (username);


--
-- TOC entry 2627 (class 2606 OID 1917565)
-- Dependencies: 2335 2335
-- Name: taxon_index_PK; Type: CONSTRAINT; Schema: ait; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY taxon_index
    ADD CONSTRAINT "taxon_index_PK" PRIMARY KEY (taxon_id);


--
-- TOC entry 2629 (class 2606 OID 1917567)
-- Dependencies: 2336 2336
-- Name: taxon_indicator_pk; Type: CONSTRAINT; Schema: ait; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY taxon_indicator
    ADD CONSTRAINT taxon_indicator_pk PRIMARY KEY (taxon_indicator_id);


--
-- TOC entry 2631 (class 2606 OID 1917569)
-- Dependencies: 2337 2337 2337
-- Name: taxon_indicator_plain_PK; Type: CONSTRAINT; Schema: ait; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY taxon_indicator_plain
    ADD CONSTRAINT "taxon_indicator_plain_PK" PRIMARY KEY (indicator, scientific_name);


--
-- TOC entry 2633 (class 2606 OID 1917571)
-- Dependencies: 2338 2338
-- Name: taxon_info_incex_pk; Type: CONSTRAINT; Schema: ait; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY taxon_info_index
    ADD CONSTRAINT taxon_info_incex_pk PRIMARY KEY (row_id);


--
-- TOC entry 2638 (class 2606 OID 1917572)
-- Dependencies: 2622 2333 2333
-- Name: indicator_ancestor_id_fk; Type: FK CONSTRAINT; Schema: ait; Owner: postgres
--

ALTER TABLE ONLY indicator
    ADD CONSTRAINT indicator_ancestor_id_fk FOREIGN KEY (indicator_ancestor_id) REFERENCES indicator(indicator_id);


-- Completed on 2010-06-17 08:56:17 CST

--
-- PostgreSQL database dump complete
--


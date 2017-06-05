CREATE TABLE documento (
	id integer NOT NULL PRIMARY KEY,
	caminho text NOT NULL UNIQUE
);

CREATE SEQUENCE documento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE documento_id_seq OWNER TO usr_ori;
ALTER SEQUENCE documento_id_seq OWNED BY documento.id;
ALTER TABLE documento ALTER COLUMN id SET DEFAULT nextval('documento_id_seq');

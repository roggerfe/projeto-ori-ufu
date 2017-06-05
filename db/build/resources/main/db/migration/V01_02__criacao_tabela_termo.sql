CREATE TABLE termo (
	id integer NOT NULL PRIMARY KEY,
	descricao text NOT NULL unique
);

CREATE SEQUENCE termo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE termo_id_seq OWNER TO usr_ori;
ALTER SEQUENCE termo_id_seq OWNED BY termo.id;
ALTER TABLE termo ALTER COLUMN id SET DEFAULT nextval('termo_id_seq');
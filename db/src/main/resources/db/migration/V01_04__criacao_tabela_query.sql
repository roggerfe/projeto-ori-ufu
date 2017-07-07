CREATE TABLE query(
	id integer NOT NULL PRIMARY KEY,
	consulta text NOT NULL,
	termo_consulta text NOT NULL,
	frequencia integer NOT NULL DEFAULT 1,
	unique (consulta, termo_consulta)
);
CREATE SEQUENCE query_id_seq
	START WITH 1
	INCREMENT BY 1
	NO MINVALUE
	NO MAXVALUE
	CACHE 1;
ALTER TABLE query_id_seq OWNER TO usr_ori;
ALTER SEQUENCE query_id_seq OWNED BY query.id;
ALTER TABLE query ALTER COLUMN id SET DEFAULT nextval('query_id_seq');
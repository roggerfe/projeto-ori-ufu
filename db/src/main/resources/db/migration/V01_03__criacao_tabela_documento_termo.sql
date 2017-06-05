CREATE TABLE documento_termo (
	id_termo integer NOT NULL REFERENCES termo(id),
	id_documento integer NOT NULL REFERENCES documento(id),
	frequencia integer NOT NULL DEFAULT 1,
	PRIMARY KEY(id_termo, id_documento)
);

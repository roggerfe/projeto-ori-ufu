-- Rodar com usr_ori conectado no bando db_ori
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

CREATE TABLE documento_termo (
	id_termo integer NOT NULL REFERENCES termo(id),
	id_documento integer NOT NULL REFERENCES documento(id),
	frequencia integer NOT NULL DEFAULT 1,
	PRIMARY KEY(id_termo, id_documento)
);

CREATE OR REPLACE FUNCTION public.fn_calcula_idf()
 RETURNS TABLE(termo text, idf numeric)
 LANGUAGE plpgsql
AS $function$
	declare
		n integer;
	begin
		select count(*) into n from documento;
		raise notice 'N: %', n;
		return QUERY select t.descricao as termo, log(2.0,(n::float/count(dt.id_documento))::numeric)
					from documento_termo dt join documento d on dt.id_documento = d.id
					join termo t on dt.id_termo = t.id
					group by t.descricao;	
	end; 
$function$;

CREATE OR REPLACE FUNCTION public.fn_inserir_doc(caminho_arqv text, conteudo text)
 RETURNS void
 LANGUAGE plpgsql
AS $function$
	declare
		conteudo_array text array;
		palavra text;
	begin
		execute 'insert into documento(caminho)
					select ''' || caminho_arqv || '''
					where not exists(select id 
									from documento
									where caminho='''||caminho_arqv||''')';
		conteudo_array := regexp_split_to_array(conteudo, E'\\s+');
		FOREACH palavra in array conteudo_array
		loop
			execute 'insert into termo(descricao)
						select ''' || palavra || '''
						where not exists(
										select id from termo
										where descricao='''||palavra||''')';
			execute 'insert into documento_termo(id_termo, id_documento, frequencia)
						select t.id, d.id, 1
						from termo t
						cross join documento d
						where t.descricao = '''|| palavra ||'''
						and d.caminho = '''|| caminho_arqv ||'''
						on conflict (id_termo, id_documento) do update set frequencia = documento_termo.frequencia + 1;';
		end loop;
	end;
$function$;

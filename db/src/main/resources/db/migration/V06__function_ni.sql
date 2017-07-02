-- parecido com idf
CREATE OR REPLACE FUNCTION public.fn_calcula_ni(termo text)
  RETURNS integer
  LANGUAGE plpgsql
 AS $function$
 	declare
 		ni integer;
 	begin
        select count(dt.id_documento) into ni
        from documento_termo dt
        join termo t on dt.id_termo = t.id
        where t.descricao = termo;
        return ni;
 	end; 
 $function$;

CREATE OR REPLACE FUNCTION public.fn_termos_documento(documento_id integer)
  RETURNS TABLE(termo text)
  LANGUAGE plpgsql
 AS $function$
 	declare
 	begin
		return query select distinct t.descricao from documento_termo dt
		join termo t on dt.id_termo = t.id
		where id_documento = documento_id;
 	end; 
 $function$;
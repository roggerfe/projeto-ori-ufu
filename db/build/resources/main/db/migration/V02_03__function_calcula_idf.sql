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

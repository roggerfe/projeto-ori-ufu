-- parecido com idf
CREATE OR REPLACE FUNCTION public.fn_qtd_docs()
  RETURNS integer
  LANGUAGE plpgsql
 AS $function$
 	declare
 		n integer;
 	begin
 		select count(*) into n from documento;
        return n;
 	end; 
 $function$;

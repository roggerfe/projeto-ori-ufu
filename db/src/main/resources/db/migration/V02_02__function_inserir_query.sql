CREATE OR REPLACE FUNCTION public.fn_inserir_query(conteudo text)
 RETURNS void
 LANGUAGE plpgsql
AS $function$
	declare
		conteudo_array text array;
		palavra text;
	begin
		conteudo_array := regexp_split_to_array(conteudo, E'\\s+');
		FOREACH palavra in array conteudo_array
		loop
			execute 'insert into query(consulta, termo_consulta, frequencia)
						select '''||conteudo||''','''||palavra||''', 1
						on conflict(consulta, termo_consulta) do update set frequencia = query.frequencia + 1
						where query.consulta = '''||conteudo||''' and query.termo_consulta = '''||palavra||''';';
		end loop;
	end;
$function$;


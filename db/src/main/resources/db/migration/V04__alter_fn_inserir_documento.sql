DROP FUNCTION public.fn_inserir_doc(caminho_arqv text, conteudo text);
CREATE OR REPLACE FUNCTION public.fn_inserir_doc(caminho_arqv text, conteudo text)
 RETURNS void
 LANGUAGE plpgsql
AS $function$
	declare
		conteudo_array text array;
		palavra text;
	begin
		insert into documento(caminho)
					select caminho_arqv
					where not exists(select id 
									from documento
									where caminho=caminho_arqv);
		conteudo_array := regexp_split_to_array(conteudo, E'\\s+');
		FOREACH palavra in array conteudo_array
		loop
			 -- if length(palavra) >=3 then 
				insert into termo(descricao)
							select palavra
							where not exists(
											select id from termo
											where descricao=palavra);
				insert into documento_termo(id_termo, id_documento, frequencia)
							select t.id, d.id, 1
							from termo t
							cross join documento d
							where t.descricao = palavra
							and d.caminho = caminho_arqv
							on conflict (id_termo, id_documento) do update set frequencia = documento_termo.frequencia + 1;
			--	 end if;
		end loop;
	end;
$function$;
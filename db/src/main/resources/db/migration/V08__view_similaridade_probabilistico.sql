create or replace view public.similaridade_probabilistico as
select
    substring(d.caminho, '[^/]*$') as doc
    , q.consulta
    , sum(log(2.0, (fn_qtd_docs()::numeric + 0.5) / (fn_calcula_ni(t.descricao)::numeric + 0.5))) as simprob
from
    query q
    join termo t on q.termo_consulta = t.descricao
    join documento_termo dt on t.id = dt.id_termo
    join documento d on dt.id_documento = d.id
group by
    d.caminho, q.consulta
union 
select
    substring(d.caminho, '[^/]*$') as doc
    , q.consulta
    , 0 as simprob
from
    query q 
    join termo t on q.termo_consulta = t.descricao
    cross join documento_termo dt
    join documento d on dt.id_documento = d.id
    where (d.id, q.consulta) not in (
	    select distinct
	        d.id
	        , q.consulta
	    from
            query q
	        join termo t on q.termo_consulta = t.descricao
	        join documento_termo dt on t.id = dt.id_termo
	        join documento d on dt.id_documento = d.id
	    group by d.id, q.consulta
	    )
group by
    d.caminho, q.consulta

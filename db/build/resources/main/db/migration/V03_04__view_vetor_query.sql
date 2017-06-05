-- vetor queries
create or replace view public.vetor_query as
select tq.consulta,
	array_agg(tq.tfidf::numeric(6,4) order by t.id) as pesos,
	array_agg(power(tq.tfidf::numeric(6,4),2) order by t.id) as pesos_quadrado
from tfidf_query tq
join termo t on tq.termo_consulta = t.descricao
group by tq.consulta;
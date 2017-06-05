--  calcular tf, idf das queries
create or replace view public.tfidf_query as
select q.consulta,
	q.termo_consulta,
	q.frequencia as frequencia,
	1::numeric + log(2.0, q.frequencia::numeric) AS tf,
	fn_calcula_idf.idf as idf,
	(1::numeric + log(2.0, q.frequencia::numeric)) * fn_calcula_idf.idf AS tfidf
from query q
cross join fn_calcula_idf() fn_calcula_idf(termo, idf)
where fn_calcula_idf.termo = q.termo_consulta
	union
select q.consulta, t.descricao, 0 as frequencia, 0 as tf, idf, 0 as tfidf
from query q
cross join termo t
cross join fn_calcula_idf()
where (t.descricao) not in (select q.termo_consulta from query q join termo t on q.termo_consulta = t.descricao)
and termo = t.descricao;
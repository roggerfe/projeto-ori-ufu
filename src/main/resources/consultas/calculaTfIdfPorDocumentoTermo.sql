-- Query calcula frequencia, tf, idf, tfidf por documento termo 
select substring(d.caminho, '[^/]*$') as doc, t.descricao as termo, dt.frequencia as freq, (1 + log(2.0, dt.frequencia)) as tf ,idf, (1 + log(2.0, dt.frequencia)) * idf as tfidf
		from documento_termo dt
		join documento d on dt.id_documento = d.id
		join termo t on dt.id_termo = t.id
    cross join fn_calcula_idf()
    where termo = t.descricao
		union
select substring(d.caminho, '[^/]*$') as doc, t.descricao as termo, 0 as freq, 0 as tf, idf, 0 as tfidf
from documento d
cross join termo t
cross join fn_calcula_idf()
where (d.id, t.id) not in (select dt.id_documento, dt.id_termo from documento_termo dt)
and termo = t.descricao;

CREATE OR REPLACE VIEW public.similaridade_probabilistico AS
select d.caminho,q.consulta, log(2.0, (fn_qtd_docs() + 0.5)/fn_calcula_ni(t.descricao) + 0.5) as simProb
from query q
cross join documento_termo dt
join documento d on dt.id_documento = d.id
join termo t on dt.id_termo = t.id and q.termo_consulta = t.descricao
order by q.consulta;
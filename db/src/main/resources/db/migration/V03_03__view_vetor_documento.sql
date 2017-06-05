-- vetor documentos
create or replace view public.vetor_documento as
select tfidf_documento.doc, array_agg(tfidf_documento.tfidf::numeric(6,4) order by t.id) as pesos, array_agg(power(tfidf_documento.tfidf::numeric(6,4),2) order by t.id) as pesos_quadrado
from tfidf_documento 
join termo t on tfidf_documento.termo = t.descricao
group by tfidf_documento.doc;
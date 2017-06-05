-- norma documentos
create or replace view public.norma_documento as
select doc, (SELECT sqrt(sum(p)) FROM UNNEST(pesos_quadrado) p) as norma from vetor_documento;
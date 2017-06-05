
-- norma queries
create or replace view public.norma_query as
select consulta, (SELECT sqrt(sum(p)) FROM UNNEST(pesos_quadrado) p) as norma from vetor_query;
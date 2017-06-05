-- similaridade
create or replace view public.similaridade as
SELECT q.consulta, d.doc, qd.prod, array_sum(qd.prod) as sim
FROM   vetor_query          q
CROSS  JOIN vetor_documento d
CROSS  JOIN LATERAL (
   SELECT ARRAY(SELECT x*y FROM unnest(q.pesos, d.pesos) t(x, y)) AS prod
) qd;

CREATE OR REPLACE VIEW public.similaridade AS
 SELECT q.consulta,
    d.doc,
    qd.prod,
    array_sum(qd.prod)/(nq.norma*nd.norma) AS sim
   FROM vetor_query q
     CROSS JOIN vetor_documento d
     CROSS JOIN LATERAL ( SELECT ARRAY( SELECT t.x * t.y
                   FROM UNNEST(q.pesos, d.pesos) t(x, y)) AS prod) qd
     join norma_query nq on q.consulta = nq.consulta
     join norma_documento nd on nd.doc = d.doc
     where nq.consulta = q.consulta
     and nd.doc = d.doc;

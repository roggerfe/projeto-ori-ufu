-- function soma array
CREATE FUNCTION array_sum(NUMERIC[]) returns numeric AS 
$$
  SELECT sum(unnest) FROM (select unnest($1)) as foo;
$$
LANGUAGE sql;

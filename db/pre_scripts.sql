-- Rodar logado com usuario ADMIN (ex: postgres)
CREATE USER usr_ori WITH
	LOGIN
	NOSUPERUSER
	CREATEDB
	NOCREATEROLE
	INHERIT
	NOREPLICATION
	CONNECTION LIMIT -1
	PASSWORD 'usr_or1';

-- Rodar logado com o usuario usr_ori
CREATE DATABASE db_ori
WITH OWNER = usr_ori
ENCODING = 'UTF8'
CONNECTION LIMIT = -1;


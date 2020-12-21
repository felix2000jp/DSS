create schema armazem;

use armazem;

CREATE USER  'gestor'@'localhost'
	IDENTIFIED BY 'gestor';

GRANT ALL ON armazem.* TO 'gestor'@'localhost';
DROP TABLE paletes;
DROP TABLE prateleiras;
DROP TABLE localizacoes;
DROP TABLE robots;
DROP TABLE mapa;

select * from paletes;
select * from prateleiras;
select * from localizacoes;
select * from robots;
select * from mapa;
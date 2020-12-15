create schema armazem;

use armazem;

CREATE USER  'gestor'@'localhost'
	IDENTIFIED BY 'gestor';

GRANT ALL ON armazem.* TO 'gestor'@'localhost';
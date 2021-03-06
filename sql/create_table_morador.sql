CREATE TABLE morador (
	Id INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY
	,nome VARCHAR(50) NOT NULL
	,data_nascimento DATE
	,email VARCHAR(20) NOT NULL
	,celular BIGINT NOT NULL
	,telefone BIGINT
	,rg BIGINT
	,cpf BIGINT NOT NULL
	);

CREATE TABLE torre (
	Id INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY
	,descricao VARCHAR(10) UNIQUE NOT NULL
	);

CREATE TABLE apartamento (
	Id INT NOT NULL GENERATED ALWAYS AS IDENTITY
	,descricao VARCHAR(10) UNIQUE NOT NULL
	,numero INT NOT NULL
	,torre_id INT
	,morador_id INT
	,FOREIGN KEY (torre_id) REFERENCES torre(id)
	,FOREIGN KEY (morador_id) REFERENCES morador(id)
	);
	
CREATE TABLE foto_morador (
	Id INT NOT NULL GENERATED ALWAYS AS IDENTITY
	,foto BLOB
	,morador_id int
	,FOREIGN KEY (morador_id) REFERENCES morador(id)
); 	

CREATE TABLE veiculo (
	Id INT NOT NULL GENERATED ALWAYS AS IDENTITY
	,tipo INT
	,marca VARCHAR(20)
	,modelo VARCHAR(20)
	,cor VARCHAR(10)
	,placa VARCHAR(8)
	,morador_id int
	,FOREIGN KEY (morador_id) REFERENCES morador(id)
); 

CREATE TABLE identificacao (
	Id INT NOT NULL GENERATED ALWAYS AS IDENTITY
	,usuario VARCHAR(50) UNIQUE NOT NULL
	,senha VARCHAR(50) NOT NULL
); 	

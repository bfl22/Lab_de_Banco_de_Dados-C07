
DROP DATABASE IF EXISTS clinica;
CREATE DATABASE clinica;
USE clinica;
SET SQL_SAFE_UPDATES = 0;

-- Criação das Tabelas
CREATE TABLE paciente(
	id_paciente INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    data_nascimento DATE NOT NULL,
    telefone VARCHAR(15) NOT NULL,
    genero CHAR(1) NOT NULL
);

CREATE TABLE prontuario(
	id_prontuario INT PRIMARY KEY AUTO_INCREMENT,
    historico_geral TEXT NOT NULL,
    data_abertura DATE NOT NULL,
    id_paciente INT UNIQUE,

    FOREIGN KEY (id_paciente) REFERENCES paciente (id_paciente)
);

CREATE TABLE medico(
	id_medico INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    crm VARCHAR(20) UNIQUE NOT NULL,
    especialidade VARCHAR(50) NOT NULL,
    telefone VARCHAR(15) NOT NULL
);

CREATE TABLE consulta(
	id_consulta INT PRIMARY KEY AUTO_INCREMENT,
    data_consulta DATE NOT NULL,
    hora_consulta TIME NOT NULL,
    valor_pago DECIMAL(10,2) NOT NULL,
    queixa_principal VARCHAR(255) NOT NULL,
    status VARCHAR(20),
    id_paciente INT,
    id_medico INT,

    FOREIGN KEY (id_paciente) REFERENCES paciente (id_paciente),
    FOREIGN KEY (id_medico) REFERENCES medico (id_medico)
);

CREATE TABLE exame(
	id_exame INT PRIMARY KEY AUTO_INCREMENT,
    nome_exame VARCHAR(100) NOT NULL,
    descricao TEXT NOT NULL,
    resultado VARCHAR(255) NOT NULL
);

CREATE TABLE consulta_exame(
	id_consulta INT NOT NULL,
    id_exame INT NOT NULL,
    data_solicitacao DATE NOT NULL,
    
    PRIMARY KEY (id_consulta, id_exame),
    FOREIGN KEY (id_consulta) REFERENCES consulta (id_consulta),
    FOREIGN KEY (id_exame) REFERENCES exame (id_exame)
);

-- Inserção dos Registros
-- PACIENTES
INSERT INTO paciente(nome, cpf, data_nascimento, telefone, genero) VALUES('Meredith Grey', '123.456.789-10', '1983-09-27', '(11) 99999-1111', 'F');
INSERT INTO paciente(nome, cpf, data_nascimento, telefone, genero) VALUES('Gregory House', '987.654.321-00', '1960-05-15', '(21) 98888-2222', 'M');
INSERT INTO paciente(nome, cpf, data_nascimento, telefone, genero) VALUES('Eleven Hopper', '741.852.963-44', '2004-02-17', '(31) 97777-3333', 'F');
INSERT INTO paciente(nome, cpf, data_nascimento, telefone, genero) VALUES('Bruce Wayne', '555.444.333-22', '1975-02-19', '(41) 96666-4444', 'M');
INSERT INTO paciente(nome, cpf, data_nascimento, telefone, genero) VALUES('Wanda Maximoff', '222.333.444-55', '1989-06-13', '(51) 95555-5555', 'F');
INSERT INTO paciente(nome, cpf, data_nascimento, telefone, genero) VALUES('Daenerys Targaryen', '888.777.666-55', '1991-11-08', '(61) 94444-6666', 'F');

-- PRONTUÁRIOS
INSERT INTO prontuario(historico_geral, data_abertura, id_paciente) VALUES('Histórico de ansiedade e insônia', '2024-01-10', 1);
INSERT INTO prontuario(historico_geral, data_abertura, id_paciente) VALUES('Dor crônica na perna direita', '2024-02-14', 2);
INSERT INTO prontuario(historico_geral, data_abertura, id_paciente) VALUES('Episódios frequentes de sangramento nasal', '2024-03-05', 3);
INSERT INTO prontuario(historico_geral, data_abertura, id_paciente) VALUES('Histórico de traumatismos após acidentes', '2024-03-20', 4);
INSERT INTO prontuario(historico_geral, data_abertura, id_paciente) VALUES('Controle psicológico e estresse intenso', '2024-04-02', 5);
INSERT INTO prontuario(historico_geral, data_abertura, id_paciente) VALUES('Histórico de queimaduras e estresse pós-traumático', '2024-05-01', 6);

-- MÉDICOS
INSERT INTO medico(nome, crm, especialidade, telefone) VALUES('Stephen Strange', 'CRM/SP 102030', 'Neurocirurgia', '(11) 94444-1111');
INSERT INTO medico(nome, crm, especialidade, telefone) VALUES('Leonard McCoy', 'CRM/RJ 405060', 'Clínica Geral', '(21) 93333-2222');
INSERT INTO medico(nome, crm, especialidade, telefone) VALUES('Cristina Yang', 'CRM/MG 708090', 'Cardiologia', '(31) 92222-3333');
INSERT INTO medico(nome, crm, especialidade, telefone) VALUES('Hannibal Lecter', 'CRM/PR 111213', 'Psiquiatria', '(41) 91111-4444');
INSERT INTO medico(nome, crm, especialidade, telefone) VALUES('Beverly Crusher', 'CRM/RS 141516', 'Pediatria', '(51) 90000-5555');
INSERT INTO medico(nome, crm, especialidade, telefone) VALUES('John Watson', 'CRM/DF 171819', 'Ortopedia', '(61) 93333-7777');

-- CONSULTAS
INSERT INTO consulta(data_consulta, hora_consulta, valor_pago, queixa_principal, status, id_paciente, id_medico) 
VALUES('2026-05-10', '14:00:00', 350.00, 'Fortes dores de cabeça', 'Agendada', 1, 1);

INSERT INTO consulta(data_consulta, hora_consulta, valor_pago, queixa_principal, status, id_paciente, id_medico) 
VALUES('2026-05-11', '09:30:00', 500.00, 'Dor muscular intensa', 'Concluída', 2, 6);

INSERT INTO consulta(data_consulta, hora_consulta, valor_pago, queixa_principal, status, id_paciente, id_medico) 
VALUES('2026-05-12', '16:15:00', 220.00, 'Cansaço excessivo', 'Cancelada', 3, 2);

INSERT INTO consulta(data_consulta, hora_consulta, valor_pago, queixa_principal, status, id_paciente, id_medico) 
VALUES('2026-05-13', '11:00:00', 650.00, 'Crises de ansiedade', 'Concluída', 5, 4);

INSERT INTO consulta(data_consulta, hora_consulta, valor_pago, queixa_principal, status, id_paciente, id_medico) 
VALUES('2026-05-14', '08:45:00', 300.00, 'Alterações cardíacas', 'Agendada', 4, 3);

INSERT INTO consulta(data_consulta, hora_consulta, valor_pago, queixa_principal, status, id_paciente, id_medico) 
VALUES('2026-05-15', '15:30:00', 380.00, 'Dores lombares frequentes', 'Concluída', 6, 5);

-- EXAMES
INSERT INTO exame(nome_exame, descricao, resultado) VALUES('Ressonância Magnética', 'Exame cerebral completo', 'Sem alterações');
INSERT INTO exame(nome_exame, descricao, resultado) VALUES('Hemograma', 'Avaliação sanguínea geral', 'Anemia leve');
INSERT INTO exame(nome_exame, descricao, resultado) VALUES('Eletrocardiograma', 'Avaliação cardíaca', 'Arritmia leve');
INSERT INTO exame(nome_exame, descricao, resultado) VALUES('Raio-X', 'Exame ósseo da perna', 'Pequena fissura');
INSERT INTO exame(nome_exame, descricao, resultado) VALUES('Tomografia', 'Avaliação neurológica', 'Resultado normal');
INSERT INTO exame(nome_exame, descricao, resultado) VALUES('Ultrassonografia', 'Avaliação abdominal completa', 'Pequena inflamação detectada');

-- EXAMES DAS CONSULTAS
INSERT INTO consulta_exame(id_consulta, id_exame, data_solicitacao) VALUES(1, 1, '2026-05-10');
INSERT INTO consulta_exame(id_consulta, id_exame, data_solicitacao) VALUES(1, 2, '2026-05-10');
INSERT INTO consulta_exame(id_consulta, id_exame, data_solicitacao) VALUES(2, 4, '2026-05-11');
INSERT INTO consulta_exame(id_consulta, id_exame, data_solicitacao) VALUES(3, 3, '2026-05-12');
INSERT INTO consulta_exame(id_consulta, id_exame, data_solicitacao) VALUES(5, 5, '2026-05-14');
INSERT INTO consulta_exame(id_consulta, id_exame, data_solicitacao) VALUES(6, 6, '2026-05-15');

-- Para evitar alguns conflitos do MySQL que tivemos
DROP USER IF EXISTS 'Claire_Temple'@'%';
DROP USER IF EXISTS 'Peter_Petrelli'@'%';
DROP ROLE IF EXISTS 'enfermeiros';

-- Criar dois usuários arbitrários
CREATE USER 'Claire_Temple'@'%' IDENTIFIED BY '01234';
CREATE USER 'Peter_Petrelli'@'%' IDENTIFIED BY '56789';

-- Criar Role
CREATE ROLE 'enfermeiros';
GRANT SELECT, INSERT, UPDATE ON clinica.* TO 'enfermeiros';
GRANT 'enfermeiros' TO 'Claire_Temple'@'%';
GRANT 'enfermeiros' TO 'Peter_Petrelli'@'%';
SET DEFAULT ROLE 'enfermeiros' TO 'Claire_Temple'@'%';
SET DEFAULT ROLE 'enfermeiros' TO 'Peter_Petrelli'@'%';

-- 1: PROCEDURE para Atualizar o Status da Consulta
DELIMITER $$
DROP PROCEDURE IF EXISTS AtualizarStatus$$
CREATE PROCEDURE AtualizarStatus(IN id_da_consulta INT, IN novo_status VARCHAR(20))
BEGIN
	UPDATE consulta
    SET status = novo_status
    WHERE id_consulta = id_da_consulta;
    
    SELECT 'Status atualizado com sucesso!' AS Resultado;
END $$
DELIMITER ;

-- Uso da PROCEDURE
CALL AtualizarStatus(5, 'Concluída');

-- 2: FUNCTION para Verificar se Consulta Possui Exames
DELIMITER $$

DROP FUNCTION IF EXISTS consulta_possui_exames$$

CREATE FUNCTION consulta_possui_exames(id_da_consulta INT) RETURNS VARCHAR(50)
DETERMINISTIC
BEGIN
	DECLARE quantidade INT;
    
    SELECT COUNT(*) INTO quantidade
    FROM consulta_exame
    WHERE id_consulta = id_da_consulta;
    
    IF quantidade > 0 THEN
		RETURN CONCAT('Sim - ', quantidade, ' exame(s)');
	ELSE
		RETURN 'Não possui exames';
	END IF;
END $$

DELIMITER ;

-- Uso da Function
SELECT consulta_possui_exames(1);

-- 3: PROCEDURE para Atualizar a Categoria da Consulta
ALTER TABLE consulta ADD categoria_consulta VARCHAR(30) NOT NULL;

DELIMITER $$
DROP PROCEDURE IF EXISTS AtualizarCategoriaConsultas $$
CREATE PROCEDURE AtualizarCategoriaConsultas()
BEGIN
	UPDATE consulta
	SET categoria_consulta = 
		CASE
			WHEN valor_pago <= 300 THEN 'Consulta Comum'
			WHEN valor_pago <= 500 THEN 'Consulta Especializada'
			ELSE 'Consulta Premium'
		END;

	SELECT 'Categorias atualizadas com sucesso!' AS Resultado;
END $$
DELIMITER ;

CALL AtualizarCategoriaConsultas();

-- 4: TRIGGER para Classificar Consultas
DELIMITER $$
DROP TRIGGER IF EXISTS tr_categoria_consulta $$
CREATE TRIGGER tr_categoria_consulta BEFORE INSERT ON consulta
FOR EACH ROW
BEGIN

	IF NEW.valor_pago <= 300 THEN
		SET NEW.categoria_consulta = 'Consulta Comum';

	ELSEIF NEW.valor_pago > 300 AND NEW.valor_pago <= 500 THEN
		SET NEW.categoria_consulta = 'Consulta Especializada';

	ELSE
		SET NEW.categoria_consulta = 'Consulta Premium';

	END IF;

END $$
DELIMITER ;

-- Uso do TRIGGER
INSERT INTO consulta(data_consulta, hora_consulta, valor_pago, queixa_principal, status, id_paciente, id_medico)
VALUES('2026-05-20', '13:30:00', 280.00, 'Febre persistente e dor no corpo', 'Agendada', 2, 5);

INSERT INTO consulta(data_consulta, hora_consulta, valor_pago, queixa_principal, status, id_paciente, id_medico)
VALUES('2026-05-22', '10:15:00', 520.00, 'Falta de ar e tonturas frequentes', 'Concluída', 4, 3);

INSERT INTO consulta(data_consulta, hora_consulta, valor_pago, queixa_principal, status, id_paciente, id_medico)
VALUES('2026-05-25', '17:00:00', 150.00, 'Dor abdominal leve', 'Cancelada', 6, 2);

SELECT * FROM consulta;

-- 5: VIEW para Verificar as Consultas em Aberto
DROP VIEW IF EXISTS consultas_incompletas;
CREATE VIEW consultas_incompletas AS (
	SELECT 
		p.nome AS 'Paciente',
		m.nome AS 'Médico',
		m.especialidade AS 'Especialidade',
		c.data_consulta AS 'Data da Consulta',
		c.status AS 'Status da Consulta'
	FROM 
		consulta c
	JOIN 
		paciente p
	ON 
		c.id_paciente = p.id_paciente
	JOIN 
		medico m
	ON 
		c.id_medico = m.id_medico
	WHERE 
		c.status != 'Concluída'
);

-- Consultando a View
SELECT * FROM consultas_incompletas;
SELECT * FROM paciente;
SELECT * FROM medico;
SELECT * FROM prontuario;
SELECT * FROM exame;
SELECT * FROM consulta;
SELECT * FROM consulta_exame;
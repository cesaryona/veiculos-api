CREATE TABLE tb_veiculo (
	id varchar(255) NOT NULL,
    veiculo varchar(255) NULL,
    marca varchar(255) NULL,
	ano int4 NULL,
	cor varchar(255) NULL,
    descricao varchar(255) NULL,
	vendido bool NULL,
	criado_em timestamp(6) NULL,
	atualizado_em timestamp(6) NULL,
	CONSTRAINT tb_veiculo_pkey PRIMARY KEY (id)
);

INSERT INTO tb_veiculo (id, veiculo, marca, ano, cor, descricao, vendido, criado_em, atualizado_em) VALUES
(gen_random_uuid(), 'Gol', 'VOLKSWAGEN', 1990, 'AZUL', 'Veículo compacto', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Hilux', 'TOYOTA', 2019, 'VERDE', 'Veículo utilitário', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'X5', 'BMW', 2022, 'AMARELO', 'Veículo de luxo', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Mustang', 'FORD', 2021, 'PRETO', 'Veículo de alto desempenho', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Corolla', 'TOYOTA', 2018, 'AZUL', 'Veículo familiar', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Palio', 'FIAT', 2020, 'VERMELHO', 'Veículo esportivo de luxo', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'M3', 'BMW', 2023, 'PRETO', 'Veículo de luxo', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Fiesta', 'FORD', 2019, 'VERDE', 'Veículo para uso diário', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Polo', 'VOLKSWAGEN', 2021, 'AMARELO', 'Veículo esportivo', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

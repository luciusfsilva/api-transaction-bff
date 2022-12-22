CREATE TABLE limite_diario (
	id BIGINT NOT NULL AUTO_INCREMENT,
    agencia INT NOT NULL,
    conta INT NOT NULL,
    limitedata DATETIME NOT NULL,
    valor DECIMAL(8,2),
    PRIMARY KEY (id)
);
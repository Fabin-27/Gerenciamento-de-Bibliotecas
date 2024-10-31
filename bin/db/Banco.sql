CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    email VARCHAR(100),
    telefone VARCHAR(15),
    endereco VARCHAR(255),
    CONSTRAINT chk_cpf CHECK (LENGTH(cpf) = 11)
);
select * from Clientes

CREATE TABLE Livros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    isbn VARCHAR(20) UNIQUE NOT NULL,
    anoPublicacao VARCHAR(20) NOT NULL
);

select * from livros

CREATE TABLE Emprestimos (
    Id INT PRIMARY KEY AUTO_INCREMENT,
    LivroId INT,
    ClienteId INT,
    DataEmprestimo DATE,
    DataDevolucao DATE,
    Status VARCHAR(20),
    FOREIGN KEY (LivroId) REFERENCES Livros(Id),
    FOREIGN KEY (ClienteId) REFERENCES Clientes(Id)
);

CREATE TABLE Funcionarios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    cargo VARCHAR(50) NOT NULL,
    salario DECIMAL(10, 2) NOT NULL,
    email VARCHAR(100),
    telefone VARCHAR(15),
    dataContratacao DATE NOT NULL
);

CREATE TABLE Login (
    id INT PRIMARY KEY AUTO_INCREMENT,
    funcionario_id INT NOT NULL,
    login VARCHAR(50) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    FOREIGN KEY (funcionario_id) REFERENCES Funcionarios(id) ON DELETE CASCADE
);

INSERT INTO Login (funcionario_id, login, senha) VALUES (4, 'admin', '1q2w3e4r');

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

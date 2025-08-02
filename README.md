# CRUD em Java com PostgreSQL

Este projeto implementa um sistema de CRUD (Create, Read, Update, Delete) para gerenciamento de usuários, utilizando Java com conexão a banco de dados PostgreSQL.

## \:computer: Tecnologias Utilizadas

* Java (JDK 17 ou superior)
* PostgreSQL (15 ou superior)
* JDBC (Driver PostgreSQL)
* Git
* Terminal/Console

## \:floppy\_disk: Estrutura do Projeto

```
projeto-crud-java/
│
├── src/
│   ├── Main.java                // Classe principal com menu interativo
│   ├── Usuario.java             // Modelo de dados (POJO)
│   └── UsuarioDAO.java         // Classe DAO para acesso ao banco de dados
│
├── db/
│   └── schema.sql              // Script para criação da tabela no PostgreSQL
│
├── README.md
└── .gitignore
```

## \:hammer: Funcionalidades

* Inserir novo usuário com validações de nome, CPF, e-mail e senha
* Listar todos os usuários
* Atualizar dados de um usuário existente (por CPF)
* Remover um usuário do banco de dados (por CPF)

## \:page\_facing\_up: Modelo da Tabela no PostgreSQL

```sql
CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL
);
```

## \:electric\_plug: Configuração da Conexão (ConexaoPostgres.java)

```java
public class ConexaoPostgres {
    public static Connection conectar() throws Exception {
        String url = "jdbc:postgresql://localhost:5432/seu_banco";
        String usuario = "seu_usuario";
        String senha = "sua_senha";
        return DriverManager.getConnection(url, usuario, senha);
    }
}
```

> Certifique-se de ajustar os dados de conexão acima.

## \:rocket: Como Executar

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
```

2. Compile e execute:

```bash
javac Main.java
java Main
```

## \:key: Validações Implementadas

* **CPF:** Deve conter exatamente 11 dígitos
* **Email:** Deve conter "@" e terminar com ".com"
* **Senha:** No mínimo 6 caracteres e conter letras

## \:memo: Autor

Desenvolvido por \Mateus- 2025.

## \:warning: Licença

Este projeto é de uso livre para fins acadêmicos e educacionais.

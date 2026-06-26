# SQA Social Media - API

Backend da aplicação, desenvolvido com Spring Boot e Java 17.

## Tecnologias

- Java 17
- Spring Boot 3.4.4
- JPA / Hibernate
- MySQL (produção) / H2 (testes)
- Maven

As principais dependências do projeto (definidas no `pom.xml`):

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>

<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
</dependency>

<!-- Banco H2 em memória para testes -->
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>test</scope>
</dependency>

<!-- Framework de testes -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

## Banco de Dados

Crie um banco MySQL com o nome de sua preferência e configure as credenciais em `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/<SEU_BANCO_DE_DADOS>
spring.datasource.username=<SEU_USUARIO_DB>
spring.datasource.password=<SUA_SENHA_DB>
spring.jpa.hibernate.ddl-auto=update
```

## Como Rodar

```bash
cd api
./mvnw spring-boot:run
```

A API será iniciada em `http://localhost:8080`.

## Endpoints

### Autenticação

**Sign Up:**

```bash
curl -X POST http://localhost:8080/auth/signup \
  -H "Content-Type: application/json" \
  -d '{"email": "user@email.com", "password": "Password123!"}'
```

**Sign In:**

```bash
curl -X POST http://localhost:8080/auth/signin \
  -H "Content-Type: application/json" \
  -d '{"email": "user@email.com", "password": "Password123!"}'
```

**Reset Password:**

```bash
curl -X POST http://localhost:8080/auth/reset-password \
  -H "Content-Type: application/json" \
  -d '{"email": "user@email.com"}'
```

### Posts

**Listar posts:**

```bash
curl "http://localhost:8080/posts?userId=1&limit=10&skip=0"
```

**Curtir/descurtir:**

```bash
curl -X POST "http://localhost:8080/posts/1/like?userId=1"
```

**Posts curtidos:**

```bash
curl "http://localhost:8080/posts/liked?userId=1&limit=10&skip=0"
```

## Testes

Os testes da API usam H2 em memória por meio de `src/test/resources/application.properties`.

```bash
./mvnw test
```

Para rodar um teste específico:

```bash
./mvnw test -Dtest=UserServiceTests
```

## Estrutura do Projeto

```
api/
├── src/
│   ├── main/
│   │   ├── java/com/demoapp/demo/
│   │   │   ├── config/
│   │   │   │   └── AppConfig.java        # Configurações (RestTemplate)
│   │   │   ├── controller/
│   │   │   │   ├── AuthController.java   # Endpoints de autenticação
│   │   │   │   └── PostController.java   # Endpoints de posts
│   │   │   ├── dto/                      # Objetos de transferência (EmailDTO, ErrorResponse, UserDTO)
│   │   │   ├── model/
│   │   │   │   ├── User.java             # Entidade de usuário
│   │   │   │   └── UserPostReaction.java # Entidade de reação (curtida)
│   │   │   ├── repository/
│   │   │   │   ├── UserRepository.java
│   │   │   │   └── UserPostReactionRepository.java
│   │   │   └── service/
│   │   │       ├── UserService.java      # Lógica de usuários
│   │   │       └── PostService.java      # Lógica de posts (DummyJSON)
│   │   │   └── DemoApplication.java      # Classe principal
│   │   └── resources/
│   │       └── application.properties    # Configurações da aplicação
│   └── test/
│       └── java/com/demoapp/demo/
│           ├── service/                  # Testes dos Services
│           │   └── UserServiceTests.java
│           └── DemoApplicationTests.java # Testes gerais
├── pom.xml                               # Dependências Maven
└── README.md                             # Documentação
```

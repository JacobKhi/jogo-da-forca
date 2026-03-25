# Jogo da Forca

Implementação do jogo da forca em Java, desenvolvido como trabalho da disciplina Laboratório de Orientação a Objetos do Instituto Federal Fluminense.

## Descrição

O jogo consiste em adivinhar letras de uma ou mais palavras escondidas. A cada erro, uma parte do boneco é desenhada. O jogador pode errar no máximo 10 letras. É possível arriscar todas as palavras de uma vez, mas apenas uma única vez por rodada.

## Tecnologias

- Java 17
- Maven
- JUnit 5

## Estrutura do Projeto

```
src/
├── main/java/br/edu/iff/forca/
│   ├── domain/          - Classes de domínio (Tema, Jogador, Palavra, Item, Rodada)
│   ├── factory/         - Fábricas de entidades e elementos gráficos
│   ├── repository/      - Interfaces e implementações de repositório
│   ├── service/         - Serviços de aplicação
│   └── Aplicacao.java   - Classe central de configuração
└── test/java/br/edu/iff/forca/
    └── domain/          - Testes unitários
```

## Como Executar

Compile e execute com Maven:

```bash
mvn compile
mvn exec:java -Dexec.mainClass="br.edu.iff.forca.Main"
```

## Como Testar

```bash
mvn test
```

## Padrões de Projeto Utilizados

- Singleton e Parametrized Singleton
- Factory Method e Abstract Factory
- Flyweight
- Template Method
- Repository
- Facade

## Autor

Disciplina: Laboratorio de Orientacao a Objetos
Professor: Mark Douglas Jacyntho
Instituto Federal Fluminense
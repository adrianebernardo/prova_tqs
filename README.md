# BugBank - Automação Web (TQS)

Automação Selenium + JUnit 5 + Java 21 (ou superior) do site https://bugbank.netlify.app/

## Fluxo testado

1. Cadastrar duas contas com saldo (nomes e e-mails gerados pelo Faker): origem e destino
2. Acessar a conta de origem e transferir R$ 150,00 para a conta de destino, com a descrição "valor para ganhar A na aula"
3. Validar no extrato da conta de origem o saldo (R$ 850,00) e a transferência enviada
4. Acessar a conta de destino e validar no extrato o saldo (R$ 1.150,00) e o recibo da transferência recebida

## Estrutura

- `Framework`: `TestBase`, `DriverManager` e `TypeBrowser` (abertura e fechamento do navegador)
- `Framework/Browser/Waits`: esperas explícitas usadas pelos PageObjects e pelas validações
- `Framework/Utils/FakersGeneration`: geração de nomes e e-mails (datafaker)
- `Framework/Utils/FileOperations`: leitura do `src/main/resources/Properties/user.properties` (URL, navegador, senha, saldo, valor e descrição)
- `PageObjects`: mapeamento dos elementos de cada tela
- `Tasks`: ações do usuário em cada tela
- `Validacao`: asserts
- `TestCases`: `RealizarTransferenciaComSucessoTest`

## Como rodar

Pelo terminal, com o Maven instalado:

```
mvn test
```

Para escolher o navegador (CHROME, EDGE, FIREFOX ou HEADLESS_CHROME) sem mexer no código:

```
mvn test -Dbrowser=HEADLESS_CHROME
```

Ou pelo VS Code (Extension Pack for Java): abra a classe `RealizarTransferenciaComSucessoTest` e clique em **Run Test** ao lado do método `realizarTransferencia`.
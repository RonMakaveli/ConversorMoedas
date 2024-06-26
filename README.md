# Documentação - Conversor de moedas

## Introdução
Este é um sistema simples de conversão de moedas desenvolvido em Java. <br>
Ele utiliza a API [ExchangeRate-API](https://www.exchangerate-api.com/) para obter as taxas de câmbio em tempo real e realizar as conversões entre diferentes moedas.

## Requisitos
Para executar este sistema, você precisa ter o Java instalado em sua máquina.

## Como usar
1. Clone ou baixe este repositório em sua máquina.
2. Compile o código-fonte utilizando o comando `javac Main.java`.
3. Execute o programa compilado com o comando `java Main`.
4. Siga as instruções no console para selecionar a opção desejada:
    - Conversão de moedas
    - Histórico de conversões
    - Sair

### Conversão de moedas
- Escolha a moeda do país de origem e do país de destino a partir da lista disponível.
- Digite a quantia a ser convertida.
- O programa exibirá o resultado da conversão.

### Histórico de conversões
- Esta opção exibe o histórico de todas as conversões realizadas durante a execução do programa.

### Observações
- As moedas suportadas atualmente são: 
    - Real Brasileiro (BRL)
    - Dólar Americano (USD)
    - Peso Chileno (CLP)
    - Peso Argentino (ARS)
    - Boliviano da Bolívia (BOB)
    - e Peso Colombiano (COP)
- O sistema armazena o histórico de conversões durante a execução, mas não persiste os dados após o encerramento do programa.

## Autor
Este programa foi desenvolvido por mim como parte de um Challenge proposto pela Alura.

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Documentação - Currency Converter</title>
</head>
<body>
    <h1>Documentação - Currency Converter</h1>

    <h2>Introdução</h2>
    <p>Este é um sistema simples de conversão de moedas desenvolvido em Java. Ele utiliza a API <a href="https://www.exchangerate-api.com/">ExchangeRate-API</a> para obter as taxas de câmbio em tempo real e realizar as conversões entre diferentes moedas.</p>

    <h2>Requisitos</h2>
    <p>Para executar este sistema, você precisa ter o Java instalado em sua máquina.</p>

    <h2>Como usar</h2>
    <ol>
        <li>Clone ou baixe este repositório em sua máquina.</li>
        <li>Compile o código-fonte utilizando o comando <code>javac Main.java</code>.</li>
        <li>Execute o programa compilado com o comando <code>java Main</code>.</li>
        <li>Siga as instruções no console para selecionar a opção desejada:
            <ul>
                <li>Conversão de moedas</li>
                <li>Histórico de conversões</li>
                <li>Sair</li>
            </ul>
        </li>
    </ol>

    <h3>Conversão de moedas</h3>
    <p>Escolha a moeda do país de origem e do país de destino a partir da lista disponível. Digite a quantia a ser convertida. O programa exibirá o resultado da conversão.</p>

    <h3>Histórico de conversões</h3>
    <p>Esta opção exibe o histórico de todas as conversões realizadas durante a execução do programa.</p>

    <h3>Observações</h3>
    <ul>
        <li>As moedas suportadas atualmente são: Real Brasileiro (BRL), Dólar Americano (USD), Peso Chileno (CLP), Peso Argentino (ARS), Boliviano da Bolívia (BOB) e Peso Colombiano (COP).</li>
        <li>O sistema armazena o histórico de conversões durante a execução, mas não persiste os dados após o encerramento do programa.</li>
    </ul>

    <h2>Agradecimentos</h2>
    <p>Este programa foi desenvolvido utilizando a API ExchangeRate-API. Agradecemos pela disponibilização deste serviço para realização de conversões de moedas em tempo real.</p>

    <h2>Autor</h2>
    <p>Este programa foi desenvolvido por mim como parte do Challenge proposto pela Alura.</p>
</body>
</html>

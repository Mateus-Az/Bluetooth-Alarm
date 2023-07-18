# Bluetooth-Alarm
📡🚨

Este projeto tem como objetivo buscar os dispositivos bluetooth mais próximos realizando a sua descoberta, logo em seguida faz abrir uma conexão com o 
dispositivo. Caso tenha sucesso, ele executará um alarme definido no próprio projeto, o que deve refletir no dispositivo.

## Primeira Conexão
🔗📱

Para fazer a primeira conexão, é necessário escolher no console quais dos dispositivos próximos você deseja fazer o pareamento. Nessa etapa, aparecerá 
no seu sistema uma mensagem solicitando permissão e a conexão será estabelecida e possivelmente validada. Mas não se preocupe, após você selecionar, o sistema gravará essa escolha para utilizar no futuro.

<a href="https://imgur.com/hM2Qtir"><img src="https://i.imgur.com/hM2Qtir.png" title="source: imgur.com" /></a>

## Execução Automática
🔄⏰

Se você executar novamente, perceberá que ele faz tudo automaticamente. Você pode gerar um arquivo JAR deste projeto e escolher em qual dia e 
horário esse programa será executado. Claro, se o seu dispositivo estiver desligado, longe demais ou o Bluetooth local do seu dispositivo estiver 
desligado, não será possível estabelecer a comunicação.

<a href="https://imgur.com/RfqNxhY"><img src="https://i.imgur.com/RfqNxhY.png" title="source: imgur.com" /></a>

## Sobre Tecnologias
💻🔧

Para conseguir rodar esse programa, precisei utilizar uma biblioteca chamada "Bluecove". Sua última atualização foi em 2008 e foi a mais recente que 
encontrei. Pode ser que não rode em alguns programas por conta da arquitetura exigida. O salvamento e recuperação do arquivo foram feitos utilizando o 
BufferedWriter.

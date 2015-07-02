# Projeto Mars-Rover


# Regras estabelecidas:
  + a classe ApplicationMain é o ponto de partida para a execução da aplicação;
  + o tamanho mínimo para largura e altura de um planalto (Surface) é de 1 para ambas as medidas;
  + o tamanho máximo para largura e altura de um planalto (Surface) é de 50 para ambas as medidas;
  + a medida que os comandos são aplicados a uma sonda, esses já vão sendo executados;
  + a entrada de valores para um planalto deve ser dois inteiro com 1 ou 2 dígitos sepradaos por espaço;
  + a entrada de valores para uma sonda deve ser dois inteiros com 1 ou 2 dígitos e uma direção (N,S,W,E) separados por espaço;
  + a entrada de valores para os comandos deve conter qualquer quantidade de valores pré-definidos (L,R,M);
  + qualquer entrada inválida (que não siga as regras acima) acarretará na finalização da aplicação;
  + qualquer erro esperado (sonda fora do planalto, tamanho indevido de planalto, etc) acarretará na finalização da aplicação;


# Dependências
  + Java 8
  + Gradle 2.4

  
# Execução
  Na pasta do projeto:
  
  + gradle build; java -jar build/libs/mars-rover-0.0.1-SNAPSHOT.jar


# Logs

  + tail -f /export/logs/mars-rover/mars-rover.log.{now}


# Serviço Rest

	+ disponível através do resource /mars-rover
	+ aceita dois parâmetros (Surface, List<Rover>)

	+ execução: gradle run
	+ exemplo de chamada (via curl):

```js
curl -v -X POST 'http://localhost:8080/mars-rover' -H 'Content-Type: Application/JSON' -d '{ "surface": {"width":5, "height":5}, "rovers":[ {"position":{"x":1, "y":2}, "faceDirection": "N", "commands":["L", "M", "L", "M", "L", "M", "L", "M", "M"]}, {"position": {"x":3, "y":3}, "faceDirection": "E", "commands":["M", "M", "R", "M", "M", "R", "M", "R", "R", "M"]} ] }'
```

	+ exemplo de retorno:

```js
* upload completely sent off: 275 out of 275 bytes
< HTTP/1.1 200 OK
* Server Apache-Coyote/1.1 is not blacklisted
< Server: Apache-Coyote/1.1
< Content-Type: application/json;charset=UTF-8
< Transfer-Encoding: chunked
< Date: Thu, 02 Jul 2015 04:18:31 GMT
< 
* Connection #0 to host localhost left intact
[{"position":{"x":1,"y":3},"faceDirection":"N","id":1,"surface":{"height":5,"width":5},"commands":["L","M","L","M","L","M","L","M","M"]},{"position":{"x":5,"y":1},"faceDirection":"E","id":2,"surface":{"height":5,"width":5},"commands":["M","M","R","M","M","R","M","R","R","M"]}]
```
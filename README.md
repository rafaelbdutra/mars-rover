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
  
  + gradle run
  ou
  + gradle build; java -jar build/libs/mars-rover-0.0.1-SNAPSHOT.jar


# Logs

  + tail -f /export/logs/mars-rover/mars-rover.log.{now}

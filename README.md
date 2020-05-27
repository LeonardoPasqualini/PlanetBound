# PlanetBound implementação em Java
Projeto criado a fim de obter competências em linguagem Java.  
Neste projeto, foi implementado um jogo de tabuleiro chamado **PlanetBound** utilizando máquinas de estado.  
O jogo e as regras podem ser encontradas neste link (https://boardgamegeek.com/boardgame/298332/planet-bound)  
**todos os créditos ao criador do jogo Joseph Propati**  
Algumas regras foram alteradas a fim de facilitar a programação, visto que trata-se de um projeto universitário  
- Não há fase de scan do espaço;
- Não há obstáculos nos terrenos no estado de explorar planeta nem cartas de terreno;
- Na exploração espacial a nave sempre passa por estados de eventos e planetas intercaladas;
- Movimentações por buracos de minhoca são aleatórias com 1/8 de chances de ocorrerem;
- Número de eventos reduzido pela metade e sem repetições;
- O utilizador tem a opção  (para efeito de debug) qual evento pode acontecer ou escolha aleatória;
- Foi acrescentado logs para melhor acompanhar o andamento do jogo.

Esta parte está implementada com interface de texto. Futuramente pretende-se colocar uma interface gráfica em Java-FX.

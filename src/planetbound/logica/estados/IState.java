package planetbound.logica.estados;

public interface IState {
    
    
//estados
    public IState escolheExploratoria();
    public IState escolheMilitar();
    public IState nextTurn(); // comeca um novo turno (EsperaMover)
    public IState move(); // move (ponto vermelho ou planeta)
    public IState iniciaExploracao(); // explora planeta (ExploraPlaneta) 
    public IState fimExploracao(); // volta para a nave (OrbitaPlaneta)
    public IState entraEstacao(); // entra na estacao espacial (EstacaoEspacial) 
    public IState saiEstacao(); // sai da entacao espacial (OrbitaPlaneta ou EsperaMover cas NextTurn)
    public IState gameOver(); // estado de fim (teminaJogo ou recomecaJogo)
    
    public IState recomecaJogo();// volta ao inicio do jogo (escolheNave)
    public IState aplicaEvento(); // eventos no ponto vermelho (game over OU esperaMover)
    public IState aplicaEvento(int id);

// quando na parte de eventos
    //public IState morteTripulante(); // o proximo tripulante da fila morre
//    public IState saqueNave(); // recupera 1d6 de um recurso aleatorio
//    public IState perdeRecurso(); // perde 1d3 de um recurso aleatorio que possue
//    public IState perdeCombustivel(); // perde 1 celula de combustivel
//    public IState noEvent(); // nada acontece uffa
//    public IState recuperaTripulante(); // recupera o proximo tripulante da fila
      public String getNomeEstado();
// estacao espacial
    public IState nivelArmaUp();
    public IState nivelArmazemUp();
    public IState contrataTripulacao();
    public IState mudaRecurso(String rec1, String rec2);
    public IState completaEscudo();
    public IState trocaRecursoEscudo();
    public IState trocaRecursoMunicao();
    public IState trocaRecursoCombustivel();
    public IState compraDrone();
    
    public IState movimentacaNoMapa(int dir);
}

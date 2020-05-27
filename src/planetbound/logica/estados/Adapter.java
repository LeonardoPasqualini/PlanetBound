package planetbound.logica.estados;

import java.io.Serializable;
import planetbound.logica.dados.GameData;


public abstract class Adapter implements IState, Serializable {
    
    private GameData game;
    private String ultimoEstado;
    private IState lastState;
    
    public Adapter(GameData game) {
        this.game = game;
        ultimoEstado = "Escolhe Nave";
    }
    
    public GameData getGame() 
    {
        return game;
    }
    
    public void setGame(GameData game) 
    {
        this.game = game;
    }

    public String getUltimoEstado() {
        return ultimoEstado;
    }

    public void setUltimoEstado(String ultimoEstado) {
        this.ultimoEstado = ultimoEstado;
    }

    public IState getLastState() {
        return lastState;
    }

    public void setLastState(IState lastState) {
        this.lastState = lastState;
    }
    
    
    
    @Override
    public IState escolheExploratoria(){return this;}
    @Override
    public IState escolheMilitar(){return this;}
    @Override
    public IState nextTurn(){return this;}
    @Override
    public IState move(){return this;}
    @Override
    public IState iniciaExploracao(){return this;}
    @Override
    public IState fimExploracao(){return this;}
    @Override
    public IState entraEstacao(){return this;}
    @Override
    public IState saiEstacao(){return this;}
    @Override
    public IState gameOver(){return this;}
    @Override
    public IState recomecaJogo(){return this;}
    @Override
    public IState aplicaEvento(){return this;}
    @Override
    public IState aplicaEvento(int id){return this;}
   
// quando na parte de eventos
//    @Override
//    public IState morteTripulante(){return this;}
//    @Override
//    public IState saqueNave(){return this;}
//    @Override
//    public IState perdeRecurso(){return this;}
//    @Override
//    public IState perdeCombustivel(){return this;}
//    @Override
//    public IState noEvent(){return this;}
//    @Override
//    public IState recuperaTripulante(){return this;}
    
 // estacao espacial
    @Override
    public IState nivelArmaUp(){return this;}
    @Override
    public IState nivelArmazemUp(){return this;}
    @Override
    public IState contrataTripulacao(){return this;}
    @Override
    public IState mudaRecurso(String rec1, String rec2){return this;}
    @Override
    public IState completaEscudo(){return this;}
    @Override
    public IState trocaRecursoEscudo(){return this;}
    @Override
    public IState trocaRecursoMunicao(){return this;}
    @Override
    public IState trocaRecursoCombustivel(){return this;}
    @Override
    public IState compraDrone(){return this;}
    
    @Override
    public IState movimentacaNoMapa(int dir){return this;}
    
    @Override
    public String getNomeEstado(){return "";}
}

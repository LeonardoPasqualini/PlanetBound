
package planetbound.logica;

import java.io.Serializable;
import planetbound.logica.dados.GameData;
import planetbound.logica.dados.Nave;
import planetbound.logica.estados.EscolheNave;
import planetbound.logica.estados.IState;
import planetbound.logica.estados.OrbitaPlaneta;


public class PlanetBound implements Serializable{
    
    
    public GameData gameData;
    public IState estado;

    public PlanetBound() {
        gameData = new GameData();
        estado = new EscolheNave(gameData);
 
    }

    public GameData getGameData() {
        return gameData;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public IState getEstado() {
        return estado;
    }

    public void setEstado(IState estado) {
        this.estado = estado;
    }              
    
    public Nave getNave(){
        return gameData.getNave();
    }
    public String getNaveString(){
        return gameData.getNave().toString();
    }
    public String showGrid(){
       return gameData.getPlaneta().getGrid().toString();
    }

    public void escolheNaveMilitar(){
        estado = estado.escolheMilitar();
    }
    
    public void escolheNaveExploratoria(){
        estado = estado.escolheExploratoria();
    }
    
    public void mostraNave(){
        gameData.getNave().toString();
    }
    
    public String showLog(){
        String stringLog = gameData.logMsgString();

        gameData.logMsgAdd().clear();
        
        return stringLog;
    }
    
    public String showLogAll(){
        String stringLogAll = gameData.LogMsgAllString();
        
        gameData.LogMsgAllString();
        
        return stringLogAll;
    }
    
    public boolean temEstacao(){
        return gameData.getPlaneta().getTipoCirculo() == 1;
    }
    
    public boolean isAlienDerrota(){
        return gameData.isAlienDerrota();
    }
    
    public void nextTurn(){
        estado = estado.nextTurn();
    }
    public void move(){
        estado = estado.move();
    }
    public void iniciaExploracao(){
        estado = estado.iniciaExploracao();
    }
    public boolean fimExploracao(){
        if(estado.fimExploracao() instanceof OrbitaPlaneta){
            
            return true;
        }
        return false;
    }
    public void entraEstacao(){
        estado = estado.entraEstacao();
    }
    public void saiEstacao(){
        estado = estado.saiEstacao();
    }
    
    public void movimentacaNoMapa(int dir){
        estado = estado.movimentacaNoMapa(dir);
    }
   
    public void gameOver(){
        estado = estado.gameOver();
    }
    
    public void recomecaJogo(){
        estado = estado.recomecaJogo();
    }
    public void terminaJogo(){
        System.exit(0);
    }
    
    
// quando na parte de eventos
    public void aplicaEvento(){
        estado = estado.aplicaEvento();
    }
    public void aplicaEvento(int n){
        estado = estado.aplicaEvento(n);
    }
    

//quando da estacao
    public void nivelArmaUp(){
        estado = estado.nivelArmaUp();
    }
    public void nivelArmazemUp(){
        estado = estado.nivelArmazemUp();
    }        
    public void contrataTripulacao(){
        estado = estado.contrataTripulacao();
    }
    public void mudaRecurso(String rec1, String rec2){
        estado = estado.mudaRecurso(rec1, rec2);
    }
    public void completaEscudo(){
        estado = estado.completaEscudo();
    }
    public void trocaRecursoEscudo(){
        estado = estado.trocaRecursoEscudo();
    }
    public void trocaRecursoMunicao(){
        estado = estado.trocaRecursoMunicao();
    }
    public void trocaRecursoCombustivel(){
        estado = estado.trocaRecursoCombustivel();
    }
    public void compraDrone(){
        estado = estado.compraDrone();
    }
  
    /**
     * Começa o jogo
     * devolve o estado do começo do jogo
     */
    /*public void startGame()
    {
        state = state.startGame();
    }*/
}

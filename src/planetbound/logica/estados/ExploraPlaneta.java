
package planetbound.logica.estados;

import java.awt.Point;
import planetbound.logica.dados.GameData;
import planetbound.logica.dados.Utils;


public class ExploraPlaneta extends Adapter {
    private int tipoAlien;
    //private boolean alienDerrota;
    
    //trazer o tipo de recurso para dentro
    public ExploraPlaneta(GameData game) {
        super(game);
        tipoAlien = Utils.rollRange(4);
        getGame().logMsgAllAdd("\n EXPLORA PLANETA tipo alien: " + tipoAlien);
        
    }
    
    /**
     * Move o drone e alien enquanto nao haver encontro nas imediacoes
     * @param dir numero da direcao que o utilizador deseja tomar
     */
    @Override
    public IState movimentacaNoMapa(int dir){
        String msg = "--> NAO FOI POSSIVEL MOVER O DRONE POIS ESTA NO LIMITE DO MAPA";
        
        encontro();
        
        if(getGame().pegaRecurso()){
            getGame().logMsgAdd("Recurso [" 
            + getGame().getPlaneta().getGrid().getRecurso().toUpperCase() + "] coletado");
        }
        switch(dir){
            case 1:
                if(!getGame().moveDroneCima()) 
                    getGame().logMsgAdd(msg);
                break;
                
            case 2: 
                if(!getGame().moveDroneBaixo()) 
                    getGame().logMsgAdd(msg);
                break;
                
            case 3: 
                if(!getGame().moveDroneEsquerda()) 
                    getGame().logMsgAdd(msg);
                break;
                
            case 4: 
                if(!getGame().moveDroneDireita()) 
                    getGame().logMsgAdd(msg);
                break;
                
        }//fim switch
        if(!getGame().isAlienDerrota()){
            getGame().moveAlien();
        }
        
        return fimExploracao();
    }
    
    /**
     * Verifica se o alien esta na adjascencia do drone, caso esteja inicia a fase de ataques
     * @return verdadeiro de estive ou falso se nao estiver
     */
    public boolean encontro(){
        Point d = getGame().getPlaneta().getGrid().getD();
        Point a = getGame().getPlaneta().getGrid().getA();
        if(Utils.imediacao(d, a)){
            iniciaAtaque();
            return true;
        }
        return false;
    }
    /**
     * Efetua ataques até que o aliem seja eliminado ou o drone seja destruido
     */
    public void iniciaAtaque(){
        while(getGame().getNave().temDrone() && !getGame().isAlienDerrota()){
            alienAtaque();
            droneAtaque();
        }
    }
    
    /**
     * Rola 1d6 e verifica o acerto do alien baseado em seu tipo, o drone perde 1 de vida
     */
    public void alienAtaque(){
        int roll = Utils.rollD6();
        String msg = ("\nDrone perdeu 1 de vida: ");
        getGame().logMsgAllAdd("\n ROLL EXPLORA PLANETA alien atack: " + roll);
        getGame().logMsgAdd("\nAlien rolou: " + roll);
        switch(tipoAlien){
            case 1:
                if(roll == 1){
                    getGame().getNave().dronePerdeVida();
                    getGame().logMsgAdd(msg + getGame().getNave().getDrone().getVida());
                }else{
                    getGame().logMsgAdd(" aliem errou");
                }
                break;
            case 2:
                
                if( roll == 1 || roll == 2){
                    getGame().getNave().dronePerdeVida();
                    getGame().logMsgAdd(msg + getGame().getNave().getDrone().getVida());
                }else{
                    getGame().logMsgAdd(" aliem errou");
                }
                break;
            case 3:
                if(roll == 3 || roll == 5){
                    getGame().getNave().dronePerdeVida();
                    getGame().logMsgAdd(msg + getGame().getNave().getDrone().getVida());
                }else{
                    getGame().logMsgAdd(" aliem errou");
                }
                break;
            default:
                if(roll == 5 || roll == 6){
                    getGame().getNave().dronePerdeVida();
                    getGame().logMsgAdd(msg + getGame().getNave().getDrone().getVida());
                }else{
                    getGame().logMsgAdd(" aliem errou");
                }
                break;
        }
    }
    
    /**
     * Rola 1d6 e baseado no tipo de alien, verifica se for eliminado
     */
    public void droneAtaque(){
        int roll = Utils.rollD6();
        String msg = "\nDrone eleminuo a ameca alien";
        getGame().logMsgAllAdd("\n ROLL EXPLORA PLANETA drone atack: " + roll);
        getGame().logMsgAdd("\nDrone ataque: " + roll);
        switch(tipoAlien){
            case 1:
                if(roll == 5 || roll == 6){
                    getGame().logMsgAdd(msg);
                    getGame().setAlienDerrota(true);
                }else{
                    getGame().logMsgAdd(" drone errou");
                }
                break;
            case 2:
                if( roll == 4 || roll == 6){
                    getGame().logMsgAdd(msg);
                    getGame().setAlienDerrota(true);
                }else{
                    getGame().logMsgAdd(" drone errou");
                }
                break;
            case 3:
                if(roll == 3 || roll == 5){
                    getGame().logMsgAdd(msg);
                    getGame().setAlienDerrota(true);
                }else{
                    getGame().logMsgAdd(" drone errou");
                }
                break;
            default:
                if(roll == 1 || roll == 2){
                    getGame().logMsgAdd(msg);
                    getGame().setAlienDerrota(true);
                }else{
                    getGame().logMsgAdd(" drone errou");
                }
                break;
        }
    }
    
    @Override
    public IState fimExploracao(){
        if(getGame().extraiRecurso()){
            return super.getLastState();
        }
        return this;
    }
}

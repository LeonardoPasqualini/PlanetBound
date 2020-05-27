package planetbound.logica.estados;

import planetbound.logica.dados.GameData;
import static planetbound.logica.dados.Utils.rollRange;

public class EsperaMover extends Adapter{
    
    private final String nomeEstado = "Epera Mover";

    EsperaMover(GameData game) {
        super(game);
    }
    
    @Override
    public IState move(){
        if(!super.getGame().getNave().temCombustivel()){
            return new GameOver(getGame());
        }
        
        passaBuracoMinhoca();
        
        if(getGame().getUltimoEstado().equals("Orbita Planeta")){
            getGame().logMsgAdd().add("Entrando na zona de [EVENTOS]");
            return new Eventos(super.getGame());
        }else{
            getGame().logMsgAdd().add("Entrando na zona de [PLANETA]");
            getGame().geraNovoPlaneta();
            return new OrbitaPlaneta(super.getGame());
        }
    }
    // verifica se passou por um buraco de minhoca
    
    public void passaBuracoMinhoca(){
        
        int escudo = getGame().getNave().getEscudo();
        int combustivel = getGame().getNave().getEscudo();
        int roll = rollRange(8);
        
        if(roll == 1){ // rola 1d8 probabilidade 1/8
            getGame().logMsgAllAdd().add("\nROLL passaBuacoNegro: " + roll);
            getGame().logMsgAdd().add("\nOh não, passaste por um [BURACO DE MINHOCA]");
            if(getGame().getNave().checaTripulacao("Escudo")){ // verifica oficial de escudo
                if( escudo < 2){
                    getGame().getNave().perdeTripulacao();
                }else{
                    getGame().getNave().perdeEscudo(escudo-2);
                    getGame().logMsgAdd().add("\n[ESCUDO] -2 (" + getGame().getNave().getEscudo()+')');
                }
                getGame().getNave().perdeCombustive(combustivel-3);
                getGame().logMsgAdd().add("\n[COMBUSTIVEL] -3 (" + getGame().getNave().getCombustivel()+')');
            }else{// penalisa se não tiver oficial
                getGame().logMsgAdd().add("\nNão possui oficial de [ESCUDO]");
                getGame().getNave().perdeEscudo(escudo-4);
                getGame().getNave().perdeCombustive(combustivel-4);
                getGame().logMsgAdd().add("\nescudo -4 (" + getGame().getNave().getEscudo()+')');
                getGame().logMsgAdd().add("\ncombustivel -4 (" + getGame().getNave().getCombustivel()+')');
            }
        }
        getGame().logMsgAllAdd().add("\nROLL NAO passaBuacoNegro: " + roll);
    }
    
    @Override
    public String getNomeEstado() {
        return nomeEstado;
    }
}

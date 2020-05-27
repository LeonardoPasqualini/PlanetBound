package planetbound.logica.estados;

import planetbound.logica.dados.GameData;
import planetbound.logica.dados.Nave;
import planetbound.logica.dados.Planeta;
import planetbound.logica.dados.Utils;


public class OrbitaPlaneta extends Adapter {
    
    private String nomeEstado = "Orbita Planeta";
    

    public OrbitaPlaneta(GameData game) {
        super(game);
       
        getGame().geraNovoPlaneta();
        
    }
     
    @Override
    public IState entraEstacao(){
        if(getGame().getPlaneta().getTipoCirculo() == 1){
             EstacaoEspacial es = new EstacaoEspacial(getGame());
            es.setLastState(this);
            return es;
        }
        return this;
    }
    
    
    @Override
    public IState nextTurn(){
        if(getGame().getNave().temAterfatosMaximo()){
            getGame().logMsgAdd().add("\nPARABENS CONSEGUISTE JUNTAR TODOS OS ARTEFATOS E SALVAR TEU PLANETA");
            return new GameOver(getGame());
        }
        getGame().setUltimoEstado(nomeEstado);
        return new EsperaMover(getGame());
    }
    
    @Override
    public IState iniciaExploracao(){
        Nave nave = getGame().getNave();
        if(nave.temCombustivel() && nave.checaTripulacao("Explorador") 
                && nave.temDrone()){
            ExploraPlaneta exploraplaneta = new ExploraPlaneta(getGame());
            exploraplaneta.setLastState(this);
            return exploraplaneta;
        }
        return this;
    }
    
    @Override
    public String getNomeEstado() {
        return nomeEstado;
    }
    
}

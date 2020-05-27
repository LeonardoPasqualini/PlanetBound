package planetbound.logica.estados;

import planetbound.logica.dados.GameData;
import planetbound.logica.dados.Nave;
import planetbound.logica.dados.Utils;

public class Eventos extends Adapter{
    private final String nomeEstado = "Eventos";
    private Nave nave = getGame().getNave();
    
    public Eventos(GameData game) {
        super(game);
        getGame().setUltimoEstado(nomeEstado);
    }
    
    /**
     * Aplica um evento de forma aleatória
     * @return retorna ao estado da movimentacao ou game over ou o mesmo estado
     */
    @Override
    public IState aplicaEvento(){
        int roll = Utils.rollD6();
        getGame().logMsgAllAdd().add("\nROLL Evento: " + roll);
        return aplicaEvento(roll);    
    }
    /**
     * Aplica um evento especifico
     * @param id id do evento que deseja executar
     * @return retorna ao estado da movimentacao ou game over ou o mesmo estado
     */
    @Override
     public IState aplicaEvento(int id){
        
        switch(id){
             case 1:
                 if(!morteTripulante()){
                    getGame().logMsgAdd("\nNave sem [TRIPULANTE] GAME OVER");
                    return new GameOver(getGame());
                }
                getGame().logMsgAllAdd("\nEVENTO Morte Tripulante aplicado");
                return new EsperaMover(getGame());
             case 2:
                 saqueNave();
                 return new EsperaMover(getGame());
             case 3:
                 perdeRecurso();
                 return new EsperaMover(getGame());
             case 4:
                 if(perdeCombustivel()){
                     getGame().logMsgAllAdd("\nEVENTO perdeCombustivel (-1) aplicado");
                     return new EsperaMover(getGame());
                 }else{
                     return new GameOver(getGame());
                 }    
             case 5:
                 noEvent();
                 return new EsperaMover(getGame());
             case 6:
                 recuperaTripulante();
                 return new EsperaMover(getGame());
             default:
                 return this;
        }        
    }
     
    
    public boolean morteTripulante(){
        
        getGame().logMsgAdd("Oh nao, um [TRIPULANTE] sofreu um ferimento grave");
        getGame().logMsgAdd(" Perdeu 1 de [TRIPULANTE]");
        return getGame().navePerdeTripulante();
    }
    
    public String geraCor(){
        
        int chance = Utils.rollRange(4);
        String cor = "";
        switch(chance){
            case 1:
                cor = "Negro";
                break;
            case 2:
                cor = "Vermelho";
                break;
            case 3:
                cor = "Azul";
                break;
            default:
                cor = "Verde";
                break;
        }
        getGame().logMsgAllAdd("\nROLL Evento Gera cor: " + chance + " : " + cor);
        return cor;
    }
    
    
    public void saqueNave(){
        
        int qtd = Utils.rollD6();
        String cor = geraCor();
        getGame().logMsgAdd("Navegando pelo espacao encontraste um destroco de nave");
        getGame().logMsgAdd("\nColetou +" + qtd + " do recurso [" + cor.toUpperCase() + "].");
        
        getGame().coletaRecursos(qtd, cor);
        
        getGame().logMsgAllAdd("\nEVENTO saqueNave aplicado");
        getGame().logMsgAllAdd("\nROLL Evento saqueNave: " + qtd + " : " + cor);
        
    }
    
    public void perdeRecurso(){
        String cor = geraCor();
        int qtd = Utils.rollRange(3);
        
        getGame().logMsgAdd("Navegando pelo espacao acabou por passar em uma instabilidade do espaco-tempo");
        if(getGame().temEspacoArmazem(cor)){
            getGame().logMsgAdd("\nPerdeu -" + qtd + " do recurso [" + cor.toUpperCase() + "].");
            getGame().perdeRecursos(qtd, cor);
        }else{
            getGame().logMsgAdd("\nPor sorte a caixa do recurso ["+ cor.toUpperCase() + "] estava vazia e nada foi perdido");
        }
        getGame().logMsgAllAdd("\nEVENTO perdeRecurso aplicado");
        getGame().logMsgAllAdd("\nROLL Evento perdeRecurso: " + qtd + " : " + cor);
        
    }
    
    
    public boolean perdeCombustivel(){
        if(getGame().perdaCombustivel(1)){
            getGame().logMsgAdd("Vossa nave foi puxada por um forte campo gravitacional");
            getGame().logMsgAdd("\nPerdeu 1 de [COMBUSTIVEL] (" + getGame().getNave().getCombustivel() + ')');
            return true;
        }else{
            getGame().logMsgAdd("\nNave sem [COMBUSTIVEL] GAME OVER");
            return false;
        }         
            
    }

    public void noEvent(){
        getGame().logMsgAdd("Parece que alcansaste o espaco interestelar e nao ha nada por aqui");
        getGame().logMsgAllAdd("\nEVENTO noEvent aplicado");
        
        
    }
    public void recuperaTripulante(){
        getGame().logMsgAdd("Encontraste um nave em pane e nela um tripulante perdido");
        if(getGame().naveContrataTripulante()){
            getGame().logMsgAdd("Ganhou (1) de [TRIPULANTE]");
        }else{
            getGame().logMsgAdd("infelizmente ja esta com a nave cheia.");
            getGame().logMsgAdd("\nCom vossa ajuda a nave em pane voltou a funcionar e o tripulante perdido pode voltar para casa");
        }
        getGame().logMsgAllAdd("\nEVENTO recuperaTripulante aplicado");
        
        
    }  
    
    @Override
    public String getNomeEstado() {
        return nomeEstado;
    }
}

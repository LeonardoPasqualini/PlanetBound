/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.logica.estados;
import planetbound.logica.dados.GameData;
import planetbound.logica.dados.Nave;
import planetbound.logica.dados.NaveMilitar;

public class EstacaoEspacial extends Adapter {
    private String nomeEstado = "Estacao Espacial";
    private Nave nave = getGame().getNave();
    
    public EstacaoEspacial(GameData game) {
        super(game);
    }
    /**
     * Verifica se é uma nave do tipo militar e aumenta o nivel caso positivo
     * @return retorna o proprio estado
     */
    public IState nivelArmaUp(){
       if(!nave.armaNivelUp()){
           getGame().logMsgAdd().add("\nNao foi possivel aumentar o nivel da [ARMA]");
           if(!(nave instanceof NaveMilitar))
                getGame().logMsgAllAdd().add("\nESTACAO ESPACIAL nivelArmaUp nao aplicado: nao é do tipo militar");
           return this;
       }
       getGame().logMsgAllAdd().add("\nESTACAO ESPACIAL nivelArmaUp aplicado");
       return this;
    }
    
    
    public IState nivelArmazemUp(){ // up nivel armazem
       if(!nave.armazemNivelUp()){
           getGame().logMsgAdd().add("\nNao foi possivel aumentar o nivel do [ARMAZEM]");
           getGame().logMsgAllAdd().add("\nESTACAO ESPACIAL nivelArmazemUp nao aplicado");
           return this;
       }
       getGame().logMsgAllAdd().add("\nESTACAO ESPACIAL nivelArmazemUp aplicado");
       return this;
    }
    
    /**
     * Verifica se ja nao possui todos os tripulantes e possue os recursos necessarios
     * @return retorna o proprio estado
     */
    public IState contrataTripulacao(){ // contrata tripulacao
        
        if(nave.checaTripulacao("Armazem") && nave.pagaRecursos(1)){
            nave.contrataTripulacao();
            getGame().logMsgAdd().add("\n[TRIPULANTE] contratado com sucesso");
            getGame().logMsgAllAdd().add("\nESTACAO ESPACIAL contrataTripulacao aplicado");
            return this;
        }
        getGame().logMsgAdd().add("\nNao foi possivel comtratar o [TRIPULANTE]");
        getGame().logMsgAllAdd().add("\nESTACAO ESPACIAL contrataTripulacao nao aplicado");
        return this;
    }
    /**
     * Muda um recurso de um tipo por outro tipo
     * @param rec1 recurso que sera trocado
     * @param rec2 recurso que sera adquirido na troca
     * @return retorna o proprio estado
     */
    public IState mudaRecurso(String rec1, String rec2){
        if(nave.perdeRecurso(rec1) && nave.coletaPeca(rec2)){
            getGame().logMsgAdd().add("\nRecurso [" + rec1.toUpperCase() 
                    + "] trocado por recurso [" + rec2.toUpperCase() + ']');
            getGame().logMsgAllAdd().add("\nESTACAO ESPACIAL mudaRecurso aplicado");
            return this;
        }
        getGame().logMsgAdd().add("\nNao foi possivel trocar recursos");
        getGame().logMsgAllAdd().add("\nESTACAO ESPACIAL mudaRecurso nao aplicado");
        return this;
    }
    /**
     * verifica se possui o ofical de armazem e se tem condicoes de carregar o escudo total
     * @return retorna o proprio estado
     */
    public IState completaEscudo(){
        if(nave.checaTripulacao("Armazem") && nave.carregaEscudo()){
            getGame().logMsgAdd().add("\n[ESCUDO] recarregado");
            getGame().logMsgAllAdd().add("\nESTACAO ESPACIAL completaEscudo aplicado");
            return this;
        }
        getGame().logMsgAdd().add("\nNao foi possivel recarregar o [ESCUDO]");
        getGame().logMsgAllAdd().add("\nESTACAO ESPACIAL completaEscudo nao aplicado");
        return this;
    }
    
    /**
     * Troca os recursos necessarios por +1 de escudo
     * @return Retorna o proprio estado
     */
    public IState trocaRecursoEscudo(){
        if(nave.checaTripulacao("Armazem") && nave.trocaRecursoEscudo()){
            getGame().logMsgAdd().add("\n[ESCUDO] +1 (" + getGame().getNave().getEscudo() + ')');
            getGame().logMsgAllAdd().add("\nESTACAO ESPACIAL trocaRecursoEscudo aplicado");
            return this;
        }
        getGame().logMsgAdd().add("\nNao foi possivel recarregar o [ESCUDO]");
        getGame().logMsgAllAdd().add("\nESTACAO ESPACIAL trocaRecursoEscudo nao aplicado");
        return this;
    }
    
    /**
     * Troca os recursos necessario por municao
     * @return Retorna o proprio estado
     */
    public IState trocaRecursoMunicao(){
        if(nave.checaTripulacao("Armazem") && nave.trocaRecursoMunicao()){
            getGame().logMsgAdd().add("\n[MUNICAO] +1 (" + getGame().getNave().getMunicao()+ ')');
            return this;
        }
        getGame().logMsgAdd().add("\nNao foi possivel recarregar a [MUNICAO]");
        return this;
    }
    
    /**
     * Troca os recursos necessarios por combustivel
     * @return o proprio estado
     */
    public IState trocaRecursoCombustivel(){
        if(nave.checaTripulacao("Armazem") && nave.trocaRecursoCombustivel()){
            getGame().logMsgAdd().add("\n[COMBUSTIVEL] +1 (" + getGame().getNave().getMunicao()+ ')');
            getGame().logMsgAllAdd().add("\nESTACAO ESPACIAL trocaRecursoCombustivel aplicado");
            return new EstacaoEspacial(getGame());
        }
        getGame().logMsgAdd().add("\nNao foi possivel recarregar o [COMBUSTIVEL]");
        getGame().logMsgAllAdd().add("\nESTACAO ESPACIAL trocaRecursoCombustivel aplicado");
        return this;
    }
    
    /**
     * Verifica se a nave ja possui um drone e se tem os recusos necessarios
     * @return Retorna o proprio estado
     */
    public IState compraDrone(){
        if(nave.temDrone()){
           getGame().logMsgAdd().add("\nJa possui [DRONE]");
           getGame().logMsgAllAdd().add("\nESTACAO ESPACIAL compraDrone nao aplicado: Ja possui drone");
           return this; 
        }
        if(nave.compraDrone()){
            getGame().logMsgAdd().add("\n[DRONE] adquirido com sucesso");
            nave.getDrone().setVida(nave.getDrone().getMax_vida());
            getGame().logMsgAllAdd().add("\nESTACAO ESPACIAL compraDrone aplicado");
            return this;
        }
        getGame().logMsgAdd().add("\nNao foi possvel comprar [DRONE]");
        getGame().logMsgAllAdd().add("\nESTACAO ESPACIAL compraDrone nao aplicado");
        return this;
    }
    
    @Override
    public IState saiEstacao(){
        return super.getLastState();
    }
    @Override
    public String getNomeEstado() {
        return nomeEstado;
    }
}

package planetbound.logica.dados;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import planetbound.logica.estados.IState;

public class GameData implements Constantes, Serializable{
    private Nave nave;
    private Planeta planeta;
    private String ultimoEstado = "Escolhe Nave";
    
    private List<String> logMsg;
    private List<String> logMsgAll;
    
    public GameData() {
        logMsg = new ArrayList<>();
        logMsgAll = new ArrayList<>();
    }


    public List<String> logMsgAllAdd() {
        return logMsgAll;
    }
    
    public Nave getNave() {
        return nave;
    }

    public void setNave(Nave nave) {
        this.nave = nave;
    }

    public Planeta getPlaneta() {
        return planeta;
    }

    public void setPlaneta(Planeta planeta) {
        this.planeta = planeta;
    }
    /**
     * constroi um novo planeta com parametros aleatorios;
     */
    public void geraNovoPlaneta(){
        planeta = new Planeta();
        planeta.constroiPlaneta();
    }

    public String getUltimoEstado() {
        return ultimoEstado;
    }

    public void setUltimoEstado(String ultimoEstado) {
        this.ultimoEstado = ultimoEstado;
    }

    public List<String> logMsgAdd() {
        return logMsg;
    }
    
    public String logMsgString(){
        return logMsg.toString();
    }
    
    public void logMsgAdd(String s){
        logMsg.add(s);
    }
    
    public String LogMsgAllString(){
        return logMsgAll.toString();
    }
    
    public void logMsgAllAdd(String s){
        logMsgAll.add(s);
    }
    
    /**
     * Acrecenta os recursos coletados no armazem enquanto houver espaco para tal 
     * @param qtd quantidade de recursosr a coletar
     * @param cor cor do recurso coletado
     */
    public void coletaRecursos(int qtd, String cor){
        
        for(int i = 0; i < qtd; i++)
            if(nave.temEspacoArmazem(cor)){
                nave.coletaPeca(cor);
            }else{
                break;
            }
    }
    
    public void perdeRecursos(int qtd, String cor){
        for(int i = 0; i < qtd; i++)
            nave.perdeRecurso(cor);
    }
    
    public boolean temEspacoArmazem(String cor){
        if((int)nave.getArmazem().get(cor) != 0){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean perdaCombustivel(int qtd){
        return nave.perdeCombustive(qtd);
    }
    
    public boolean navePerdeTripulante(){
        if(nave.aindaTemTripulante()){
            nave.perdeTripulacao();
            return nave.aindaTemTripulante();
        }else{
            return false;
        }
    }
    
    public boolean naveContrataTripulante(){
        return nave.contrataTripulacao();
    }
    
    public boolean isAlienDerrota() {
        return planeta.getGrid().isAlienDerrota();
    }

    public void setAlienDerrota(boolean alienDerrota) {
        planeta.getGrid().setAlienDerrota(alienDerrota);
    }
    
    public boolean temTripulante(){
        return nave.aindaTemTripulante();
    }
    
    /**
     * verifica se o planeta aida possui recursos disponiveis para ser coletado
     * caso ainda não esteja exaurido
     * @return String do recurso aleatorio dentro dos limites, se não "planeta exaurido"
     */
    /*public String coletaRecursoPlaneta(){
        if(planeta.getQtdRecursos() > 0){
            planeta.setQtdRecursos(planeta.getQtdRecursos()-1);
            return planeta.escolheRecurso();
        }
        return "planeta exaurido";
    }*/
    
    /**
     * manipula a posicao do drone para que mova para cima (y-1)
     * @return verdadeiro se conseguiu mover o drone dentro dos limiter do mapa 
     * ou falso caso não coansiga
     */
    public boolean moveDroneCima(){
        Point aux = planeta.getGrid().getD();
        if (aux.y > 0){
            aux.y--;
            return true;
        } 
        return false;
    }
    /**
     * manipula a posicao do drone para que mova para baixo (y+1)
     * @return verdadeiro se conseguiu mover o drone dentro dos limiter do mapa 
     * ou falso caso não coansiga
     */
    public boolean moveDroneBaixo(){
        Point aux = planeta.getGrid().getD();
        if (aux.y < Constantes.DIM_TERRENO_Y-1){
            aux.y++;
            return true;
        } 
        return false;
    }
    /**
     * manipula a posicao do drone para que mova para direita (x+1)
     * @return verdadeiro se conseguiu mover o drone dentro dos limiter do mapa 
     * ou falso caso não coansiga
     */
    public boolean moveDroneDireita(){
        Point aux = planeta.getGrid().getD();
        if (aux.x < Constantes.DIM_TERRENO_X-1){
            aux.x++;
            return true;
        } 
        return false;
    }
    /**
     * manipula a posicao do drone para que mova para esquerda (x-1)
     * @return verdadeiro se conseguiu mover o drone dentro dos limiter do mapa 
     * ou falso caso não coansiga
     */
    public boolean moveDroneEsquerda(){
        Point aux = planeta.getGrid().getD();
        if (aux.x > 0){
            aux.x--;
            return true;
        } 
        return false;
    }
    /**
     * verifica se o drone esta na adjacencia do recurso e cloeta
     * @return verdadeiro se estiver perto ou falso caso nao esteja
     */
    public boolean pegaRecurso(){
        return planeta.getGrid().pegaRecurso();
    }
    /**
     * O drone volta para a nave com o recurso
     * @return retorna verdadeiro se estiver com o recurso e na mesma posicao da onde partiu 
     * ou falso caso contratio
     */
    public boolean extraiRecurso(){
        String rec = "";
        if(pegaRecurso() && planeta.getGrid().isPontoPartida()){
            rec = planeta.getGrid().getRecurso();
            nave.setRecurso(rec, 1);
            return true;
        }
        return false;
    }
    
    /**
     * Movimentacao de uma unidade do alien automatica em diracao do drone
     * 
     */
    public void moveAlien(){
        Point alien = planeta.getGrid().getA();
        Point drone = planeta.getGrid().getD();
        if(Utils.imediacao(drone, alien))
            return;
        if(alien.x < drone.x && alien.x < Constantes.DIM_TERRENO_X)
            alien.x++;
        else if(alien.x > drone.x && alien.x >= 0)
            alien.x--;
        else if(alien.y < drone.y  && alien.y < Constantes.DIM_TERRENO_Y)
            alien.y++;
        else if(alien.y > drone.y  && alien.x >= 0)
            alien.y--;
    }
}
package planetbound.logica.dados;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
/**
 * Classe que reprenta a nave do jogo
 * @author Leonardo
 */
public class Nave implements Constantes, Serializable {
    
    
//atributos da nave
    private int combustivel; //qtd atual de combustivel
    private int escudo; // qtd atual de escudo
    private int municao; // qtd atual de municao
    private int nivelArma = 1; // nivel atual a arma
    private int nivelArmazem = 0; // nivel atual do armazem
    private int artefactos; // qtd atual de artefatos
    //caracteristicas de cada nave
    private final int maxCombustivel; //qtd maxima de combustivel
    private final int maxEscudo; // qtd maxima de escudo
    private final int maxMunicao; // qtd maxima de municao
    private final int maxNivelArmazem; // qtd maxima do nivel da arma
    private final int maxNivelArma; // qtd maxima do nivel do armazem
    
    // Map da tripulacao, Key nome, value boolen se tem ou não 
    private final Map<String, Boolean> tripulacao;
    private final String[] recursos = {"Negro", "Vermelho", "Verde", "Azul"};
    private final String[] oficiais = {"Capitao", "Navegador", "Explorador", "Escudo", "Armas", "Armazem"};
    // Map do armzem, Key cor, value Integer com a qtd 
    private final Map<String, Integer> armazem;
    
    //drone
    Drone drone;
    
    public Nave(int combustivel, int escudo, int municao, int maxNivelArma, int maxNivelArmazem){
        // inicializa as variáveis da nave de acondo com o tipo de instancia
        this.combustivel = this.maxCombustivel = combustivel;
        this.escudo = this.maxEscudo = escudo;
        this.municao = this.maxMunicao = municao;
        this.maxNivelArmazem = maxNivelArmazem;
        this.maxNivelArma = maxNivelArma;
        this.artefactos = QTD_INICIAL_ART;
        
        
        //cria um novo drone
        this.drone = new Drone();
        // inicilaiza o Map da tripulação. Foi utilizado LinkedHashMap
        // para que as posicoes se mantenham como as do jogo
        this.tripulacao = new LinkedHashMap<>();
        for(String oficial: oficiais ){
            this.tripulacao.put(oficial, Boolean.TRUE);
        }
        // inicilaiza o Map do armazem. Foi utilizado LinkedHashMap
        // para que as posicoes se mantenham como as do jogo
        this.armazem = new LinkedHashMap<>();
        for(String recurso: recursos){
            this.armazem.put(recurso, 0);
        }
    }
    
    public int getCombustivel() {
        return combustivel;
    }

    public int getEscudo() {
        return escudo;
    }

    public int getMunicao() {
        return municao;
    }

    public int getNivelArma() {
        return nivelArma;
    }

    public Map getArmazem() {
        return armazem;
    }
    
    public String getArmazemString(){
        
        return armazem.toString();
    }
    
    public int getNivelArmazem() {
        return nivelArmazem;
    }

    public int getArtefactos() {
        return artefactos;
    }

    public Map<String, Boolean> getTripulacao() {
        return tripulacao;
    }
    
    public String getTripulacaoString() {
        return tripulacao.toString();
    }
    
    public void setMunicao(int qtd){
       this.municao = qtd;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }
    
    public int getMaxCombustivel() {
        return maxCombustivel;
    }
     
    public int getMaxEscudo() {
        return maxEscudo;
    }
    public int getMaxMunicao() {
        return maxMunicao;
    }
    public int getMaxNivelArmazem() {
        return maxNivelArmazem;
    }
    public int getMaxNivelArma() {
        return maxNivelArma;
    }
    public void setRecurso(String cor, int qtd){
        armazem.put(cor, qtd);
    }

    public String[] getRecursos() {
        return recursos;
    }

    public String[] getOficiais() {
        return oficiais;
    }
    
    public void setCombustivel(int combustivel) {
        this.combustivel = combustivel;
    }

    public void setEscudo(int escudo) {
        this.escudo = escudo;
    }

    public void setNivelArma(int nivelArma) {
        this.nivelArma = nivelArma;
    }

    public void setNivelArmazem(int nivelArmazem) {
        this.nivelArmazem = nivelArmazem;
    }

    public void setArtefactos(int artefactos) {
        this.artefactos = artefactos;
    }
// talvez vá no game? 
    /**
     * Verifica se há tripulantes faltando e acrescenta o proximo da lista
     * @return verdadeiro se foi possivel acrescentar o tripulante ou falso caso contraio
     */
    public boolean contrataTripulacao(){
        
        if(tripulacao.get("Armazem"))
            return false;
        
        Iterator<Map.Entry<String, Boolean>> it = tripulacao.entrySet().iterator();
        
        while(it.hasNext()){
            Map.Entry<String, Boolean> entry = it.next();
            if(!entry.getValue()){
                entry.setValue(Boolean.TRUE);
                break;
            }
        }
        return true;
    } 
    
    /**
     * Retira o proximo tripulante da lista
     */
    public void perdeTripulacao(){ 
        
        Iterator<Map.Entry<String, Boolean>> it = tripulacao.entrySet().iterator();
        String anterior = "";
        while(it.hasNext()){
            Map.Entry<String, Boolean> entry = it.next();
            if(!entry.getValue()){
                break;
            }else{
                anterior = entry.getKey();
            }
        }
        tripulacao.put(anterior, Boolean.FALSE);
    }
    
    /**
     * verifica se o tripulante está disponivel
     * @param nome do tripulante é pretendido verificar
     * @return verdadeiro se possuir ou falso caso contrario
     */
    public boolean checaTripulacao(String nome){
        return tripulacao.get(nome);
    }
    
    /**
     * verifica e incrementa o nível do armazem
     * @return verdadeiro se tiver o tripulante necessário e recursos suficientes ou falso caso contrario
     */
    public boolean armazemNivelUp(){ // aumenta o nivel do armazem
        if(this.checaTripulacao("Armazem")){
            if((nivelArmazem < maxNivelArmazem) && pagaRecursos(2)){
                nivelArmazem++;
                return true;
            }
        }
        return false;
    }
    /**
     * acrescenta no armazem a peca coletada caso ainda haja espaço para tal
     * @param cor da peca a ser ascrescentada
     * @return verdadeiro se a peca pode ser coletada ou falso caso contrario
     */
    public boolean coletaPeca(String cor){ // coloca peça coletada no armazem
        if(armazem.get(cor) < (nivelArmazem+1)*6){
            armazem.put(cor, armazem.get(cor)+1);
            return true;
        }
        return false;   
    }
    
    /**
     * acrescenta combunstivel na nave caso não estaja no limite do tanque
     * @return verdadeiro caso acrescente ou falso caso contrario
     */
    
    public boolean acrescentaCombustivel(){ // aumenta o nivel de combustivel
        if(this.combustivel < this.maxCombustivel){
            this.combustivel++;
            return true;
        }
        return true;
    }
    
    /**
     * perde uma quantidade (qtd) de combustivel até zero
     * @param qtd quantidade de combustivel perdida
     * @return verdadeiro se combustivel for maior que zero ou falso caso contrario
     */
    
    public boolean perdeCombustive(int qtd){ // diminui qtd o nivel de combustivel
        this.combustivel = Math.max(0, this.combustivel - qtd);
        return combustivel > 0;
    }
    
    /**
     * perde uma quantidade (qtd) de escudo até zero
     * @param qtd quantidade de escudo perdida
     * @return verdadeiro se o valor do escudo for maior que zero ou falso caso contrario
     */
    
    public boolean perdeEscudo(int qtd){ // perde a qtd de escudo
        this.escudo = Math.max(0, this.escudo - qtd);
        return escudo > 0;
    }
    
    /**
     * Perde um recurso (cor) específico que tenha valor > 0
     * @param cor recurso a qual sera feito o decrecimo
     * @return verdadeiro se o recurso pode ser decrementado ou falso caso contrario
     */
    public boolean perdeRecurso(String cor){
        if(armazem.get(cor) > 0){
            armazem.put(cor, armazem.get(cor)-1);
            return true;
        }
        return false;
    }
    
    /**
     * incremente a quantidade de escudo caso ja nao esteja no maximo
     * @return  verdadeiro se fori possivel incrementar ou falso caso contrario
     */
    
    public boolean acrescentaEscudo(){ // aumenta a qtd de escudo
        if(this.escudo < this.maxEscudo){
            this.escudo++;
            return true;
        }
        return false;
    }
    
    /**
     * Simula um tiro da nave decrementando a quantidade municao se tiver mais que 0
     * @return verdadeiro caso o descressimo foi possivel ou falso caso contrario
     */
   
    public boolean atira(){ // decrementa a qtd de municao disponivel na arma
        if(municao > 0){
            municao--;
            return true;
        }
        return false;
    }
    
    /**
     * acrescenta mucinaco caso nao estaja no maximo
     * @return verdadeiro caso seja possivel ou falso caso contrario
     */
    
    public boolean acrscentaMunicao(){
        if(municao < maxMunicao){
            municao++;
            return true;
        }
        return false;
    }
    
    /**
     * Verifica se é um nave militar e possui oficial de armas e se ja não esta no nivel maximo maximo
     * e se possui os recursos necessarios para tal
     * @return verdadeiro se a operacao foi bem sucessedida ou falso caso contrario
     */
    
    public boolean armaNivelUp(){ // aumenta o nivel da arma (só usado na nave militar)
        if(this instanceof NaveMilitar && this.checaTripulacao("Armas")){
            if((nivelArma < maxNivelArma) && pagaRecursos(3)){
                nivelArma++;
                return true;
            }
        }
        return false;
    }
    
    /**
     * Verifica se possui menos que a quantidade possivel de artefatos e acrescente se verdadeiro
     * @return verdadeiro se foi possivel incrementar ou falso caso contrario
     */
    
    public boolean aumentaArtefacto(){ // acrescenta um artefato
        if(artefactos < QTD_GANHA_ART){
            artefactos++;
            return true;
        }
        return false;
    }
    
    /**
     * Verifica se possui a quantidade (qtd) de todoso os recurso necessários, caso possua,
     * faz o decremento dos mesmos
     * @param qtd quantidade que se pretente verificar e decrementar
     * @return verdadeiro caso a operacao foi bem sucedida ou falso caso contrario
     */
    public boolean pagaRecursos(int qtd){
        
        for(String recurso: recursos){
            if((int)armazem.get(recurso) < qtd){
                return false;
            }
        }
        for(String recurso: recursos)
            armazem.put(recurso, armazem.get(recurso)-qtd);
        return true;
    }
    
    /**
     * Troca recursos necessários para colocar o escudo no máximo
     * @return verdadeiro caso a operaca o foi bem sucedida ou falso caso contrario
     */
    public boolean carregaEscudo(){
        if(pagaRecursos(1)){
            escudo = maxEscudo;
            return true;
        }
        return false;
    }
    
    public boolean trocaRecursoEscudo(){
        if(perdeRecurso("Negro") && perdeRecurso("Verde") && perdeRecurso("Azul")){
            escudo++;
            return true;
        }
        return false;
    }
    
    public boolean trocaRecursoMunicao(){
        if(perdeRecurso("Negro") && perdeRecurso("Azul")){
            municao++;
            return true;
        }
        return false;
    }
    
    public boolean trocaRecursoCombustivel(){
        if(perdeRecurso("Negro") && perdeRecurso("Vermelho") && perdeRecurso("Azul")){
            combustivel++;
            return true;
        }
        return false;
    }
   
    public boolean compraDrone(){
        for(String recurso: recursos){
            if(armazem.get(recurso) < 2)
                return false;
        }
        for(String recurso: recursos){
            armazem.put(recurso, armazem.get(recurso)-2);
        }
        return true;
    }
    
    public boolean aindaTemTripulante(){
        return tripulacao.get("Capitao");
    }
    /**
     * verifica se possui a quantidade de artefatos para ganhar o jogo
     * @return verdadeiro se tiver ou falso se não
     */
    public boolean temAterfatosMaximo(){
        return this.artefactos == QTD_GANHA_ART;
    }
    public boolean temCombustivel(){
        return this.combustivel > 0;
    }
    public boolean temMunicao(){
        return this.municao > 0;
    }
    public boolean temEscudo(){
        return this.escudo > 0;
    }
    public boolean temEspacoArmazem(String cor){
        return armazem.get(cor) < ((nivelArmazem+1)*6);
    }
    
    public boolean temDrone(){
        return this.drone.getVida() > 0;
    }
    public boolean dronePerdeVida(){
        if(temDrone()){
            drone.perdeVida();
            return true;
        }
        else return false;
    }
    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        
        s.append("NAVE " + getTipo() + System.lineSeparator());
        s.append("Tripulacao - " + getTripulacaoString() + System.lineSeparator());
        s.append("Artefato (" + this.artefactos + ')'+ System.lineSeparator());
        s.append("Nivel Armazem (" + this.nivelArmazem + ')' + System.lineSeparator());
        s.append("Armazem - " + getArmazemString() + System.lineSeparator());
        s.append("Combustivel (" + this.combustivel + ')' + System.lineSeparator());
        s.append("Escudo (" + this.escudo + ')' + System.lineSeparator());
        s.append("Nivel Arma (" + this.nivelArma + ')' + System.lineSeparator());
        s.append("Municao (" + this.municao + ')' + System.lineSeparator());
        s.append(drone.toString());
        
        return s.toString();
    }
    
    public String getTipo(){return "";}
    
}


package planetbound.logica.dados;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Planeta implements Serializable{
    private int tipoRecurso;
    private int qtdRecursos;
    private List<String> listaRecursos;
    private int tipoCirculo = 0; // 0 - branco 1 - vermelho
    private Grid grid;
    
    public Planeta(){
        listaRecursos = new ArrayList<>();
        tipoRecurso = Utils.rollRange(4);
        
        if(Utils.rollRange(10) <= 3){
            tipoCirculo = 1;
        }else{
            tipoCirculo = 0;
        }
    }
    /**
     * Controi os atributos de um planeta: quantos recursos tem e quais são eles, controi o grid
     */
    public void constroiPlaneta(){
        geraQtdRecurso();
        geraRecursos();
        grid = new Grid(escolheRecurso());
    } 
    /**
     * Se for do tipo 4 gera int 4 recurso, se diferente de 4 então são apenas int 2
     */
    private void geraQtdRecurso() {
        switch(tipoRecurso){
            case 4:
                qtdRecursos = 4;
                break;
            default:
                qtdRecursos = 2;
                break;
        }
    }
    /**
     * povoa a lista de recursos consoante a cada cor de planeta
     */
    private void geraRecursos() {
        switch(tipoRecurso){
            case 1:
                listaRecursos.add("Vermelho");
                listaRecursos.add("Verde");
                break;
            case 2:
                listaRecursos.add("Negro");
                listaRecursos.add("Azul");
                break;
            case 3:
                listaRecursos.add("Vermelho");
                listaRecursos.add("Azul");
                break;
            default:
                listaRecursos.add("Negro");
                listaRecursos.add("Verde");
                listaRecursos.add("Azul");
                listaRecursos.add("Artecfato");
                break;
        }
    }
    /**
     * Escolhe um recurso aleatorio da lista de recursos
     * @return String da cor do recurso
     */
    public String escolheRecurso(){
        if(tipoRecurso == 4){
            return listaRecursos.get(Utils.rollRange(4)-1);
        }
        return listaRecursos.get(Utils.rollRange(2)-1);
    }
    
    public int getTipoRecurso() {
        return tipoRecurso;
    }

    public int getQtdRecursos() {
        return qtdRecursos;
    }

    public void setQtdRecursos(int qtdRecursos) {
        this.qtdRecursos = qtdRecursos;
    }
    
    public List<String> getListaRecursos() {
        return listaRecursos;
    }

    public void setTipoRecurso(int tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public int getTipoCirculo() {
        return tipoCirculo;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }
    
    
}

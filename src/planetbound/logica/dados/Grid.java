/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.logica.dados;

import java.awt.Point;
import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * @author Leonardo
 */
public class Grid implements Serializable{
    private String[][] grid;
    private Point ptInicial; // ponto incial do drone
    private Point d; // drone
    private Point a; // alien
    private Point r; // recurso
    private String recurso;
    private boolean alienDerrota;
    
    public Grid(String recurso){
        this.recurso = recurso;
        grid = new String[Constantes.DIM_TERRENO_X][Constantes.DIM_TERRENO_Y];
        iniciaGrid();
        alienDerrota = false;
    }
    /**
     * gera posicionamento não encavalado do drone, alien e recurso num limite de 6 epaços
     * 
     */
    public void iniciaGrid(){
        d = new Point(Utils.rollD6()-1, Utils.rollD6()-1); // gera posicao drone
        ptInicial = new Point((int)d.getX(), (int)d.getY());
        do{
            a = new Point(Utils.rollD6()-1, Utils.rollD6()-1); // gera posicao alien
        }while(a.equals(d));
        do{
            r = new Point(Utils.rollD6()-1, Utils.rollD6()-1); // gera posicao recurso
        }while(r.equals(d) || r.equals(a)); 
    }
    
    /**
     * verifica se o drone esta na adjacencia do recurso e retira-o do grid
     * @return verdadeiro se estiver perto ou falso caso nao esteja
     */
    public boolean pegaRecurso(){
        if(r.x == -1)
            return true;
        if(Utils.imediacao(d, r)){
            r = new Point(-1,-1);
            return true;
        }
        return false;
    }

    public Point getD() {
        return d;
    }

    public void setD(Point d) {
        this.d = d;
    }

    public Point getA() {
        return a;
    }

    public void setA(Point a) {
        this.a = a;
    }

    public Point getR() {
        return r;
    }

    public void setR(Point r) {
        this.r = r;
    }

    public Point getPtInicial() {
        return ptInicial;
    }

    public void setPtInicial(Point ptInicial) {
        this.ptInicial = ptInicial;
    }
    
    public boolean isPontoPartida(){
        return d.equals(ptInicial);
    }

    public String getRecurso() {
        return recurso;
    }

    public boolean isAlienDerrota() {
        return alienDerrota;
    }

    public void setAlienDerrota(boolean alienDerrota) {
        this.alienDerrota = alienDerrota;
    }
  
    /**
     * Construcao do grid em formato texto
     * @return o grid com as posicoes das pecas no mapa
     */
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < Constantes.DIM_TERRENO_X; i++){
            Arrays.fill(grid[i], "-");
        }
        grid[ptInicial.y][ptInicial.x] = "X";
        grid[d.y][d.x] = "D";
        if(!alienDerrota)
            grid[a.y][a.x] = "A";
        if(!(r.x == -1))
            grid[r.y][r.x] = "R";
        for(int i = 0; i < 6; i++){
            s.append("\n");
            for(int j = 0; j < 6; j++){
                s.append(grid[i][j]);
            }
        }
        return s.toString();
    }
}

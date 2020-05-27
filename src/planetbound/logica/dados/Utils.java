
package planetbound.logica.dados;

import java.awt.Point;

public class Utils {
    
    /**
     * Gera numeros aleatorios entre 1 e o limeite superior
     * @param limSup limite superior que deseja ser gerado
     * @return interio do valor gerado
     */
    public static int rollRange(int limSup){
        return (int)((Math.random()*limSup)+1);
    }
    
    public static int rollD6(){
        return rollRange(6);
    }
    public static int rollD3(){
        return rollRange(3);
    }
    
    /**
     * calcula a distancia entre dois pontos, horizontal ou vertical
     * @param p1 primeiro ponto para calcular a distancia
     * @param p2 segunto ponto para calcular a distancia 
     * @return verdadeiro se estiver nas imediacao ou falso se não
     */
    public static boolean imediacao(Point p1, Point p2){
        return ((((int)Math.abs(p1.x - p2.x)) == 0 && ((int)Math.abs(p1.y - p2.y))==1) 
                || ((int)Math.abs(p1.x - p2.x)) == 1 && ((int)Math.abs(p1.y - p2.y))==0);
    }
}

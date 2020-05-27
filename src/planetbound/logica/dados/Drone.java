package planetbound.logica.dados;

import java.io.Serializable;
import static planetbound.logica.dados.Constantes.MAX_VIDA_DRONE;

public class Drone implements Serializable{
    
    
    private int vida;
    private int max_vida = MAX_VIDA_DRONE;
    
    public Drone(){
        vida = max_vida;
    }

    public int getMax_vida() {
        return max_vida;
    }
  
    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
    /**
     * O drone perde vida proveniente de algum ataque. 
     */
    public void perdeVida() {
        if(vida > 0){
            this.vida--;
        }
    }

    @Override
    public String toString() {
        return "Drone: " + "vida(" +  this.vida + ')' + System.lineSeparator();
    }
    
    
}

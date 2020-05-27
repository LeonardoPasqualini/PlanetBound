
package planetbound.logica.dados;

import java.io.Serializable;

public class NaveMilitar extends Nave implements Serializable{
    
   private String tipo = "Militar";
    
    public NaveMilitar(){
        super(MAX_COMBUSTIVEL_MILI, MAX_ESCUDO_MILI, MAX_MUNICAO_MILI,
               MAX_ARMA_NIVEL_MILI, MAX_ARMAZEM_NIVEL_MILI);
    }
    
    @Override
    public String getTipo() {
        return tipo;
    }
}

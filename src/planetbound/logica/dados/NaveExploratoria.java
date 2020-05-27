package planetbound.logica.dados;

import java.io.Serializable;

public class NaveExploratoria extends Nave implements Serializable{
    
    private String tipo = "Exploratoria";
    
    public NaveExploratoria() {
        super(MAX_COMBUSTIVEL_EXP, MAX_ESCUDO_EXP, MAX_MUNICAO_EXP,
               MAX_ARMA_NIVEL_EXP, MAX_ARMAZEM_NIVEL_EXP);
    }
    
    @Override
    public String getTipo() {
        return tipo;
    }
    
    
}

package planetbound.logica.estados;

import planetbound.logica.dados.GameData;
import planetbound.logica.dados.Nave;
import planetbound.logica.dados.NaveExploratoria;
import planetbound.logica.dados.NaveMilitar;

public class EscolheNave extends Adapter {
    
    private final String nomeEstado = "Escolhe Nave";

    public EscolheNave(GameData game) {
        super(game);
    }
    
    public IState escolheMilitar(){ // esolhe a nava militar (EsperaMover) 
        super.getGame().setNave(new NaveMilitar());
        getGame().logMsgAllAdd().add("Escolheu nave Militar");
        return new EsperaMover(super.getGame());
    }
    
    public IState escolheExploratoria(){  // escolhe a nave exploratoria (EsperaMover)
        super.getGame().setNave(new NaveExploratoria());
        getGame().logMsgAllAdd().add("Escolheu nave Exploratoria");
        return new EsperaMover(super.getGame());
    }

    @Override
    public String getNomeEstado() {
        return nomeEstado;
    }
    
}

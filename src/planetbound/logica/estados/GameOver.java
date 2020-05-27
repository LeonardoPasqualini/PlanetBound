/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.logica.estados;

import planetbound.logica.dados.GameData;

/**
 *
 * @author Leonardo
 */
public class GameOver extends Adapter {
    private final String nomeEstado = "Game Over";

    public GameOver(GameData game) {
        super(game);
    }
    
    public void terminaJogo(){
        System.exit(0); // para meta 2 plataform.exit
    }
    @Override
    public IState recomecaJogo(){
        return new EscolheNave(getGame());
    }
    
    @Override
    public String getNomeEstado() {
        return nomeEstado;
    }
}

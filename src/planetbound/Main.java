/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound;

import planetbound.iu.gui.TextUI;
import planetbound.logica.PlanetBound;

public class Main {
    
    public static void main(String[] args) {
        
       PlanetBound gameModel = new PlanetBound();
       TextUI textUI = new TextUI(gameModel);
       textUI.run();   
    }
}

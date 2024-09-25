package controller;

import model.InputMap;
 import model.SnakeGame;
import view.PanelSnakeGame;
import view.ViewSnakeGame;

public class ControllerSnakeGame extends AbstractController {
    private InputMap carte;
    private PanelSnakeGame panneau;
    private ViewSnakeGame vue;
    public ControllerSnakeGame(){
        
        try {
            carte=new InputMap("layouts/arenaNoWall.lay");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("fichier non trouvé");
        }
        super.game=new SnakeGame(500,carte);
        panneau=new PanelSnakeGame(carte.getSizeX(), carte.getSizeY(), carte.get_walls(),carte.getStart_snakes(),carte.getStart_items());
        vue=new ViewSnakeGame(panneau);
        vue.affiche();
    }
}

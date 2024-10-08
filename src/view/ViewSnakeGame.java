package view;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;

import model.SnakeGame;

@SuppressWarnings("deprecation")
public class ViewSnakeGame implements Observer {
    private PanelSnakeGame panneau;
    private JFrame jFrame;
    private SnakeGame snakeGame;
    public ViewSnakeGame(PanelSnakeGame panneau,SnakeGame snakeGame){
        this.snakeGame=snakeGame;
        this.panneau=panneau;
        this.jFrame=new JFrame("Jeu du Serpent");
        jFrame.setSize(new Dimension(panneau.getSizeX()*50,panneau.getSizeY()*50));
        Dimension windowSize = jFrame.getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int dx = centerPoint.x - windowSize.width / 2 ;
        int dy = centerPoint.y - windowSize.height / 2 - 350;
        jFrame.setLocation(dx, dy);

        jFrame.add(panneau);
    }
    

    public void affiche(){
        jFrame.setVisible(true);
    }
    
    public void update(Observable o,Object arg){
        System.out.println("update");
        panneau.updateInfoGame(snakeGame.getListSnakes(), snakeGame.getFeaturesItems());
        panneau.repaint();
       // panneau.update(ge());
    }
}

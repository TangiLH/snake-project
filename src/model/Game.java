package model;

import java.util.Observable;

/**
 * Classe abstraite servant de base pour modeliser un jeu
 */
@SuppressWarnings("deprecation")
public abstract class Game extends Observable implements Runnable {

    private int turn;
    private int maxTurn;
    private Boolean isRunning;
    private long time;

    private Thread thread;

    public Integer getTurn() {
        return this.turn;
    }

    public Integer getMaxTurn() {
        return this.maxTurn;
    }

    public Boolean isRunning() {
        return this.isRunning;
    }

    public void setTime(long time){
        this.time=time;
    }

    public long getTime(){
        return this.time;
    }

    /**
     * constructeur de la classe abstraite
     * @param maxTurn le nombre de tours maximal
     * @param time le temps entre chaque tour en millisecondes
     */
    public Game(int maxTurn,long time){
        this.maxTurn=maxTurn;
        this.time=time;
    }

    /**
     * constructeur de la classe abstraite avec time par defaut
     * @param maxTurn le nombre de tours maximal
     */
    public Game(int maxTurn){
        this.maxTurn=maxTurn;
        this.time=500;
    }

    /**
     * initalise la partie
     */
    public abstract void initializeGame();

    /**
     * effectue un tour
     */
    public abstract void takeTurn();

    /**
     * determine si la partie doit continuer
     * @return true si la partie continue, false sinon
     */
    public abstract Boolean gameContinue();

    /**
     * termine la partie
     */
    public abstract void gameOver();

    /**
     * initialise le jeu en mettant le compteur à zero et isRunning à vrai
     */
    public void init(){
        this.turn=0;
        this.isRunning=true;
        initializeGame();
        this.setChanged();
    }
    
    /**
     * incremente le compteur et effectue un tour
     */
    public void step(){
        turn++;
        this.setChanged();
        if(gameContinue()&&turn<maxTurn){
            takeTurn();
        }
        else{
            isRunning=false;
            gameOver();
        }
    }

    /**
     * met le jeu en pause
     */
    public void pause(){
        isRunning=false;
        this.setChanged();
    }

    /**
     * continue le jeu
     */
    public void play(){
        isRunning=true;
        this.setChanged();
    }

    /**
     * lance le jeu en pas à pas
     */
    public void run(){
        while(isRunning){
            step();
            this.notifyObservers();
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * lance le jeu en utilisant le thread
     */
    public void launch(){
        this.isRunning=true;
        this.thread=new Thread(this);
        thread.start();
    }
}
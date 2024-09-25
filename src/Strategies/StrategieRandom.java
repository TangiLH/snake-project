package Strategies;

import java.util.Random;

import utils.AgentAction;

public class StrategieRandom implements Strategie {
    private static Strategie cache;

    private StrategieRandom(){
        //singleton private constructor
    }

    /**
     * public method to get a StrategieRandom object using the Singleton design patern
     * @return a StrategieRandom object
     */
    public static Strategie getStrategieRandom(){
        if(cache==null){
            cache=new StrategieRandom();
        }
        return cache;
    }
    public AgentAction nextMove(){
        Random random=new Random();
        return AgentAction.values()[random.nextInt(4)];
    }
}

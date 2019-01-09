package uniupo.gaborgalazzo.rogue;

import java.util.ArrayList;

public class GameEngine {

    private Prison prison;
    private Player player;
    private ArrayList<Monster> monsters;
    private int time;

    public void genPrison(){

    }

    public void genPrison(String s){

    }

    public void tick(){
        time++;
        for (Monster m : monsters) {
           //TODO: move m
        }
        for (Monster m : monsters) {
            //TODO: if m.isDead() remove m
        }
        if(time%3==1);
            //TODO: increment player power
        //TODO: move player
    }

    public boolean isGameEnd(){
        if(player.isDead() || prison.getExit())
    }
}

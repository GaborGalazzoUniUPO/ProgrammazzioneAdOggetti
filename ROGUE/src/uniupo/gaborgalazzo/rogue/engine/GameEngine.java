package uniupo.gaborgalazzo.rogue.engine;

import uniupo.gaborgalazzo.rogue.model.elements.actors.Monster;
import uniupo.gaborgalazzo.rogue.model.elements.actors.Player;
import uniupo.gaborgalazzo.rogue.model.Prison;

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
            if(m.isDead())
                prison.removeElement(m.getRoom(), m.getX(), m.getY());
        }
        if(time%3==1);
            //TODO: increment player power
        //TODO: move player
    }

    public boolean isGameEnd(){
        //TODO: player.isDead() || player has reached exit
        return false;
    }
}

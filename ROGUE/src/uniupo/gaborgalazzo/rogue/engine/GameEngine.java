package uniupo.gaborgalazzo.rogue.engine;

import uniupo.gaborgalazzo.rogue.model.elements.actors.Monster;
import uniupo.gaborgalazzo.rogue.model.elements.actors.Player;
import uniupo.gaborgalazzo.rogue.model.Prison;

import java.util.ArrayList;

/**
 * The type Game engine.
 */
public class GameEngine {

    private Prison prison;
    private Player player;
    private ArrayList<Monster> monsters;
    private int time;

	/**
	 * Gen prison prison.
	 *
	 * @return the prison
	 */
	public static Prison genPrison(){
        return null;
    }

	/**
	 * Gen prison prison.
	 *
	 * @param s the s
	 * @return the prison
	 */
	public static Prison genPrison(String s){
        return null;
    }

	/**
	 * Tick.
	 */
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

	/**
	 * Is game end boolean.
	 *
	 * @return the boolean
	 */
	public boolean isGameEnd(){
        //TODO: player.isDead() || player has reached exit
        return false;
    }
}

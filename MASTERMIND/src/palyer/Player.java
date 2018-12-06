package palyer;

import palyer.Attempt;
import util.Judge;

import java.util.ArrayList;

public abstract class Player {
    private String target;
    private ArrayList<Attempt> attempts;

    public Player() {
        attempts = new ArrayList<>();
        init();
    }

    public String getTarget(){
        return target;
    }

    public boolean addAttempt(String guess, int maggot, int bulls){
        if(bulls+maggot>Judge.MAX_PEGS)
            return false;
        if(!Judge.validateString(guess))
            return false;
        return attempts.add(new Attempt(guess, maggot, bulls));
    }

    public String attemptToString(){
        return attempts.toString();
    }

    public void init(){
        target = Judge.genTarget();
        attempts.clear();
    }

    public abstract String genGuess();

    public abstract String genTarget();


}

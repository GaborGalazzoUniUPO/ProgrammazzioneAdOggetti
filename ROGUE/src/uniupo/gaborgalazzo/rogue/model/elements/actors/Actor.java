package uniupo.gaborgalazzo.rogue.model.elements.actors;

import uniupo.gaborgalazzo.rogue.model.Prison;
import uniupo.gaborgalazzo.rogue.model.elements.Element;
import uniupo.gaborgalazzo.rogue.model.elements.items.usables.Weapon;

public abstract class Actor extends Element {

    private int maxStrength;
    private int actualStrength;
    private Weapon weapon;

    public void decrementStrenght(int amount){
        actualStrength -= amount;
    }


    public void incrementStrenght(int amount){
        actualStrength += amount;
        if(actualStrength>maxStrength)
            actualStrength = maxStrength;
    }

    public boolean isDead(){
        return actualStrength < 1;
    }

}

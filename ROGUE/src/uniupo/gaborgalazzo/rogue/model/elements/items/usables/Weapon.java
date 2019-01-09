package uniupo.gaborgalazzo.rogue.model.elements.items.usables;

import com.sun.javaws.exceptions.InvalidArgumentException;
import uniupo.gaborgalazzo.rogue.model.elements.actors.Actor;

public class Weapon extends Usable {

    private double missProbability;
    private int damageValue;

    public Weapon(int damageValue) {
        setDamageValue(damageValue);
        setMissProbability(0.5);
    }

    public Weapon(int damageValue, int probability) {
        this(damageValue);
        setMissProbability(probability);
    }

    @Override
    public void use(Actor actor) {
        double prob = Math.random();
        if(prob > missProbability)
            actor.decrementStrenght(damageValue);
    }

    public double getMissProbability() {
        return missProbability;
    }

    public void setMissProbability(double missProbability) {
        this.missProbability = missProbability;
    }

    public int getDamageValue() {
        return damageValue;
    }

    public void setDamageValue(int damageValue) {
        this.damageValue = damageValue;
    }
}

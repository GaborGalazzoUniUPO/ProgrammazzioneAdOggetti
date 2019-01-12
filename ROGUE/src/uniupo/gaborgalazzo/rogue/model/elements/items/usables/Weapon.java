package uniupo.gaborgalazzo.rogue.model.elements.items.usables;

import com.sun.javaws.exceptions.InvalidArgumentException;
import uniupo.gaborgalazzo.rogue.model.elements.actors.Actor;

/**
 * The type Weapon.
 */
public class Weapon extends Usable {

    private double missProbability;
    private int damageValue;

	/**
	 * Instantiates a new Weapon.
	 *
	 * @param damageValue the damage value
	 */
	public Weapon(int damageValue) {
        setDamageValue(damageValue);
        setMissProbability(0.5);
    }

	/**
	 * Instantiates a new Weapon.
	 *
	 * @param damageValue the damage value
	 * @param probability the probability
	 */
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

	/**
	 * Gets miss probability.
	 *
	 * @return the miss probability
	 */
	public double getMissProbability() {
        return missProbability;
    }

	/**
	 * Sets miss probability.
	 *
	 * @param missProbability the miss probability
	 */
	public void setMissProbability(double missProbability) {
        this.missProbability = missProbability;
    }

	/**
	 * Gets damage value.
	 *
	 * @return the damage value
	 */
	public int getDamageValue() {
        return damageValue;
    }

	/**
	 * Sets damage value.
	 *
	 * @param damageValue the damage value
	 */
	public void setDamageValue(int damageValue) {
        this.damageValue = damageValue;
    }
}

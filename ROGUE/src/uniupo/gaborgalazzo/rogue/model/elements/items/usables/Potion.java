package uniupo.gaborgalazzo.rogue.model.elements.items.usables;

import uniupo.gaborgalazzo.rogue.model.elements.actors.Actor;

/**
 * The type Potion.
 */
public class Potion extends Usable {

    private int healValue;

	/**
	 * Instantiates a new Potion.
	 *
	 * @param healValue the heal value
	 */
	public Potion(int healValue) {
        this.healValue = healValue;
    }

	/**
	 * Gets heal value.
	 *
	 * @return the heal value
	 */
	public int getHealValue() {
        return healValue;
    }

    @Override
    public void use(Actor actor) {
        actor.incrementStrenght(healValue);
    }
}

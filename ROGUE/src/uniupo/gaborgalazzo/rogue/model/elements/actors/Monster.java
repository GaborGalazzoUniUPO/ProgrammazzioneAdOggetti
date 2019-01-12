package uniupo.gaborgalazzo.rogue.model.elements.actors;

import uniupo.gaborgalazzo.rogue.model.Prison;
import uniupo.gaborgalazzo.rogue.model.elements.items.usables.Weapon;

/**
 * The type Monster.
 */
public class Monster extends Actor {


	/**
	 * Instantiates a new Monster.
	 *
	 * @param room           the room
	 * @param x              the x
	 * @param y              the y
	 * @param maxStrength    the max strength
	 * @param actualStrength the actual strength
	 * @param weapon         the weapon
	 */
	public Monster(int room, int x, int y, int maxStrength, int actualStrength, Weapon weapon)
	{
		super(room, x, y, maxStrength, actualStrength, weapon);
	}
}

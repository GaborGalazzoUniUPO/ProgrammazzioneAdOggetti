package uniupo.gaborgalazzo.rogue.model.elements.actors;

import uniupo.gaborgalazzo.rogue.model.Prison;
import uniupo.gaborgalazzo.rogue.model.elements.items.usables.Weapon;

public class Monster extends Actor {


	public Monster(int room, int x, int y, int maxStrength, int actualStrength, Weapon weapon)
	{
		super(room, x, y, maxStrength, actualStrength, weapon);
	}
}

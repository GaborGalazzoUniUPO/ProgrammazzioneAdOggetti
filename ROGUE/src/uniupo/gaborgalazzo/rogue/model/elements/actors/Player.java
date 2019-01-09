package uniupo.gaborgalazzo.rogue.model.elements.actors;

import uniupo.gaborgalazzo.rogue.model.elements.items.usables.Weapon;

public class Player extends Actor {

	public Player(int room, int x, int y, int maxStrength, int actualStrength, Weapon weapon)
	{
		super(room, x, y, maxStrength, actualStrength, weapon);
	}
}

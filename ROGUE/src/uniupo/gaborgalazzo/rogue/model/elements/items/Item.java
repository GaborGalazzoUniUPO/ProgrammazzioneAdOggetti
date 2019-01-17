package uniupo.gaborgalazzo.rogue.model.elements.items;

import uniupo.gaborgalazzo.rogue.model.elements.Element;

/**
 * The type Item.
 */
public abstract class Item extends Element {
	/**
	 * Instantiates a new Element.
	 *
	 * @param room the room
	 * @param x    the x
	 * @param y    the y
	 */
	public Item(int room, int x, int y)
	{
		super(room, x, y);
	}
}

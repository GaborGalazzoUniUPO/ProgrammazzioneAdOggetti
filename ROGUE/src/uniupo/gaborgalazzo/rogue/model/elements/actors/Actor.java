package uniupo.gaborgalazzo.rogue.model.elements.actors;

import uniupo.gaborgalazzo.rogue.model.Prison;
import uniupo.gaborgalazzo.rogue.model.elements.Element;
import uniupo.gaborgalazzo.rogue.model.elements.items.usables.Weapon;

/**
 * The type Actor.
 */
public abstract class Actor extends Element {

    private int maxStrength;
    private int actualStrength;
    private Weapon weapon;

	/**
	 * Instantiates a new Actor.
	 *
	 * @param room           the room
	 * @param x              the x
	 * @param y              the y
	 * @param maxStrength    the max strength
	 * @param actualStrength the actual strength
	 * @param weapon         the weapon
	 */
	public Actor(int room, int x, int y, int maxStrength, int actualStrength, Weapon weapon)
    {
        super(room, x, y);
        setMaxStrength(maxStrength);
        setActualStrength(actualStrength);
        setWeapon(weapon);
    }

	/**
	 * Decrement strenght int.
	 *
	 * @param amount the amount
	 * @return the int
	 */
	public int decrementStrenght(int amount){
        actualStrength -= amount;
        if(actualStrength<0)
            actualStrength = 0;
        return actualStrength;
    }


	/**
	 * Increment strenght.
	 *
	 * @param amount the amount
	 */
	public void incrementStrenght(int amount){
        actualStrength += amount;
        if(actualStrength>maxStrength)
            actualStrength = maxStrength;
    }

	/**
	 * Is dead boolean.
	 *
	 * @return the boolean
	 */
	public boolean isDead(){
        return actualStrength < 1;
    }

	/**
	 * Get actual strength int.
	 *
	 * @return the int
	 */
	public int getActualStrength(){
        return actualStrength;
    }

	/**
	 * Get max strength int.
	 *
	 * @return the int
	 */
	public int getMaxStrength(){
        return maxStrength;
    }

	/**
	 * Sets max strength.
	 *
	 * @param maxStrength the max strength
	 */
	public void setMaxStrength(int maxStrength)
    {
        this.maxStrength = maxStrength;
    }

	/**
	 * Sets actual strength.
	 *
	 * @param actualStrength the actual strength
	 */
	public void setActualStrength(int actualStrength)
    {
        this.actualStrength = actualStrength;
    }

	/**
	 * Gets weapon.
	 *
	 * @return the weapon
	 */
	public Weapon getWeapon()
    {
        return weapon;
    }

	/**
	 * Sets weapon.
	 *
	 * @param weapon the weapon
	 */
	public void setWeapon(Weapon weapon)
    {
        this.weapon = weapon;
    }
}

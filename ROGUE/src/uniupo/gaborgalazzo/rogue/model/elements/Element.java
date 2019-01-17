package uniupo.gaborgalazzo.rogue.model.elements;

/**
 * The type Element.
 */
public abstract class Element {

    private int x;
    private int y;
    private int room;

	/**
	 * Instantiates a new Element.
	 *
	 * @param room the room
	 * @param x    the x
	 * @param y    the y
	 */
	public Element(int room, int x, int y){
        this.room = room;
        this.x = x;
        this.y = y;
    }

	/**
	 * Gets x.
	 *
	 * @return the x
	 */
	public int getX() {
        return x;
    }

	/**
	 * Sets x.
	 *
	 * @param x the x
	 */
	public void setX(int x) {
        this.x = x;
    }

	/**
	 * Gets y.
	 *
	 * @return the y
	 */
	public int getY() {
        return y;
    }

	/**
	 * Sets y.
	 *
	 * @param y the y
	 */
	public void setY(int y) {
        this.y = y;
    }

	/**
	 * Gets room.
	 *
	 * @return the room
	 */
	public int getRoom() {
        return room;
    }

	/**
	 * Sets room.
	 *
	 * @param room the room
	 */
	public void setRoom(int room) {
        this.room = room;
    }
}

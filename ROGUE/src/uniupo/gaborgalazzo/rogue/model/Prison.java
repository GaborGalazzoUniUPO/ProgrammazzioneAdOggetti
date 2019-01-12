package uniupo.gaborgalazzo.rogue.model;

import uniupo.gaborgalazzo.rogue.exception.PositionNotEmptyException;
import uniupo.gaborgalazzo.rogue.model.elements.Element;

/**
 * The type Prison.
 */
public class Prison {

    private int currentRoom;

    private Room[] rooms;

	/**
	 * Instantiates a new Prison.
	 *
	 * @param floors the floors
	 */
	public Prison(int floors) {
        currentRoom = 0;
        rooms = new Room[floors];
    }

	/**
	 * Place element.
	 *
	 * @param element the element
	 * @param room    the room
	 * @param x       the x
	 * @param y       the y
	 * @throws PositionNotEmptyException the position not empty exception
	 */
	public void placeElement(Element element, int room, int x, int y) throws PositionNotEmptyException {

        rooms[room].placeElement(element, x, y);
        element.setX(x);
        element.setY(y);
        element.setRoom(room);

    }

	/**
	 * Replace element element.
	 *
	 * @param element the element
	 * @param room    the room
	 * @param x       the x
	 * @param y       the y
	 * @return the element
	 */
	public Element replaceElement(Element element,int room,  int x, int y){

        Element e = rooms[room].replaceElement(element, x, y);
        element.setX(x);
        element.setY(y);
        element.setRoom(room);
        e.setX(-1);
        e.setY(-1);
        e.setRoom(-1);
        return e;
    }

	/**
	 * Remove element element.
	 *
	 * @param room the room
	 * @param x    the x
	 * @param y    the y
	 * @return the element
	 */
	public Element removeElement(int room,  int x, int y){
        return rooms[room].replaceElement(null, x, y);
    }

	/**
	 * Get room position content element.
	 *
	 * @param room the room
	 * @param x    the x
	 * @param y    the y
	 * @return the element
	 */
	public Element getRoomPositionContent(int room, int x, int y){ return rooms[room].getPositionContent(x,y); }

    private class Room {

        private Element[][] positions;

        private Room(int x, int y){
            positions = new Element[x][y];
        }

		/**
		 * Place element.
		 *
		 * @param element the element
		 * @param x       the x
		 * @param y       the y
		 * @throws PositionNotEmptyException the position not empty exception
		 */
		void placeElement(Element element, int x, int y) throws PositionNotEmptyException {
            if(getPositionContent(x,y)!=null)
                throw new PositionNotEmptyException(this, x, y);
            replaceElement(element, x, y);

        }

		/**
		 * Replace element element.
		 *
		 * @param element the element
		 * @param x       the x
		 * @param y       the y
		 * @return the element
		 */
		Element replaceElement(Element element, int x, int y){

            Element old = positions[x][y];
            positions[x][y] = element;
            return old;
        }

		/**
		 * Get position content element.
		 *
		 * @param x the x
		 * @param y the y
		 * @return the element
		 */
		Element getPositionContent(int x, int y){
            return positions[x][y];
        }
    }
}

package uniupo.gaborgalazzo.rogue.model;

import uniupo.gaborgalazzo.rogue.exception.PositionNotEmptyException;
import uniupo.gaborgalazzo.rogue.model.elements.Element;

public class Prison {

    private int currentFloor;

    private Room[] rooms;

    public Prison(int floors) {
        currentFloor = 0;
        rooms = new Room[floors];
    }

    public void placeElement(Element element, int room, int x, int y) throws PositionNotEmptyException {

        rooms[room].placeElement(element, x, y);
        element.setX(x);
        element.setY(y);
        element.setRoom(room);

    }

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

    public Element removeElement(int room,  int x, int y){
        return rooms[room].replaceElement(null, x, y);
    }

    public Element getRoomPositionContent(int room, int x, int y){ return rooms[room].getPositionContent(x,y); }

    private class Room {

        private Element[][] positions;

        private Room(int x, int y){
            positions = new Element[x][y];
        }

        void placeElement(Element element, int x, int y) throws PositionNotEmptyException {
            if(getPositionContent(x,y)!=null)
                throw new PositionNotEmptyException(this, x, y);
            replaceElement(element, x, y);

        }

        Element replaceElement(Element element, int x, int y){

            Element old = positions[x][y];
            positions[x][y] = element;
            return old;
        }

        Element getPositionContent(int x, int y){
            return positions[x][y];
        }
    }
}

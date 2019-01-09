package uniupo.gaborgalazzo.rogue.model;

import uniupo.gaborgalazzo.rogue.exception.PositionNotEmptyException;
import uniupo.gaborgalazzo.rogue.model.elements.Element;

public class Prison {

    private int currentFloor;

    private Room[] rooms;

    public Element placeElement(Element element, int room, int x, int y) throws PositionNotEmptyException {

        Element e = rooms[room].placeElement(element, x, y);
        e.setX(x);
        e.setY(y);
        e.setRoom(room);
        return e;

    }

    public Element replaceElement(Element element,int room,  int x, int y){

        Element e = rooms[room].replaceElement(element, x, y);
        element.setX(x);
        element.setY(y);
        element.setRoom(room);
        return e;
    }

    public Element removeElement(int room,  int x, int y){
        return rooms[room].replaceElement(null, x, y);
    }
}

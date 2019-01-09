package uniupo.gaborgalazzo.rogue.model;

import uniupo.gaborgalazzo.rogue.exception.PositionNotEmptyException;
import uniupo.gaborgalazzo.rogue.model.elements.Element;

public class Room {

    private Element[][] positions;

    public Element placeElement(Element element, int x, int y) throws PositionNotEmptyException {
        if(getPositionContent(x,y)!=null)
            throw new PositionNotEmptyException(this, x, y);
        return replaceElement(element, x, y);

    }

    public Element replaceElement(Element element, int x, int y){

        Element old = positions[x][y];
        positions[x][y] = element;
        return old;
    }

    public Element getPositionContent(int x, int y){
        return positions[x][y];
    }
}

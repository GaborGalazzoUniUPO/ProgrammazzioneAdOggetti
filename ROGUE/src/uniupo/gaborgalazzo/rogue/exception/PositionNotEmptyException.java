package uniupo.gaborgalazzo.rogue.exception;

import uniupo.gaborgalazzo.rogue.model.Prison;

public class PositionNotEmptyException extends Throwable {
    public PositionNotEmptyException(Prison.Room room, int x, int y) {

    }
}

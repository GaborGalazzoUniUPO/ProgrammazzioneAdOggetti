import jbook.util.Input;

import java.awt.*;

public class PointDistance {
    public static void main(String[] args) {
        System.out.println("Inserisci il primo putno:");
        Point p1 = requestPoint();
        System.out.println("Inserisci il secondo putno:");
        Point p2 = requestPoint();

        System.out.println("La distanza tra i due punti e': "+p1.distance(p2));
    }

    private static Point requestPoint() {
        return new Point(
                Input.readInt("Inserisci X\n> "),
                Input.readInt("Inserisci Y\n> ")
        );
    }
}

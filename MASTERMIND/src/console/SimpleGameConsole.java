package console;

import jbook.util.Input;
import util.Judge;

public class SimpleGameConsole {

    private static int LIMIT = 12;

    public static void main(String[] args) {
        int numGuesses = 0;
        String guess;
        String target = Judge.genTarget();
        while (true) {
            guess = Input.readString("Indovina la sequenza:");
            if (guess.equals("quit")){
                System.out.println("La risposta e':" + target +"");
                return;
            }
            if (guess.equals("Mi_arrendo")) {
                System.out.println("La risposta e':" + target +"");
            } else if (!(Judge.validateString(guess))) {
                System.out.println("Stringa non valida");
            } else {
                int bulls = Judge.numBulls(guess, target);
                int maggots = Judge.numMaggots(guess, target);
                if (bulls == 4) {
                    numGuesses++;
                    System.out.println(
                            "Hai indovinato in" + numGuesses + "tentativi.  Riprova!"
                    );
                    target = Judge.genTarget();
                    numGuesses = 0;
                } else {
                    numGuesses++;
                    System.out.println(bulls + "B  " + maggots + "M");
                }
            }
        }
    }
}

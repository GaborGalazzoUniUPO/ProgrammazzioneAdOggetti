package palyer;

import jbook.util.Input;
import util.Judge;

public class Human extends Player {
    @Override
    public String genGuess() {
        String guess = null;
        boolean isGuessValid = false;
        while (!isGuessValid){
            guess= Input.readString("Inserisci una stringa valida\n>");
            isGuessValid = Judge.validateString(guess);
        }
        return guess;
    }

    @Override
    public String genTarget() {
        String guess = null;
        boolean isGuessValid = false;
        while (!isGuessValid){
            guess= Input.readString("Inserisci una stringa valida\n>");
            isGuessValid = Judge.validateString(guess);
        }
        return guess;
    }
}

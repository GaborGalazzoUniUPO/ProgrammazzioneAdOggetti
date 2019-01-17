package palyer;

import util.Judge;

import java.util.ArrayList;

public class SmartComputer extends Computer {

    private ArrayList<String> possibleGuesses;

    @Override
    public String genGuess() {
        ArrayList<String> toRemove = new ArrayList<>();
        for(String s: possibleGuesses)
            if(!matchAll(s, attempts))
                toRemove.add(s);
        possibleGuesses.removeAll(toRemove);

        return possibleGuesses.get((int)(Math.random() * possibleGuesses.size()));
    }

    @Override
    public void init() {
        super.init();
        possibleGuesses = getValidCombos(genCombos(Judge.MAX_PEGS, Judge.PEGS));
    }

    public static boolean matchAll(String s, ArrayList<Attempt> attempts) {
        for(Attempt attempt: attempts)
            if(!match(s, attempt))
                return false;
        return true;
    }

    public static boolean match(String s, Attempt attempt) {
        int maggots = Judge.numMaggots(s, attempt.getGuess()) ;
        int bulls = Judge.numBulls(s, attempt.getGuess());
        return maggots == attempt.getMaggots() && bulls == attempt.getBulls();
    }

    @Override
    public String genTarget() {
        return super.genTarget();
    }

    public static ArrayList<String> getValidCombos(ArrayList<String> combos){
        ArrayList<String> validCombos = new ArrayList<>();
        for(String s: combos)
            if(Judge.validateString(s))
                validCombos.add(s);
        return validCombos;
    }

    public static ArrayList<String> genCombos(int length, char[] chars){
        ArrayList<String> combos = new ArrayList<>();
        if(length == 0) {
            combos.add("");
            return combos;
        }
        ArrayList<String> subCombos = genCombos(length-1, chars);
        for(int i = 0; i<chars.length; i++){
            for (String s :
                    subCombos) {
                combos.add(chars[i] + s);
            }
            
        }
        return combos;

    }
}

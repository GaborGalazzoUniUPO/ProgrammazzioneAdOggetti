package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Judge {

    public static final int MAX_PEGS = 4;
    public static final char PEGS[] = {'0','1','2','3','4','5','6','7','8','9'};

    /**
     * Validate a Character c
     * @param c
     * @return c is present in {@value PEGS}
     */
    private static boolean validatePeg(char c){
        for(int i = 0; i<PEGS.length; i++){
            if(c == PEGS[i])
                return true;
        }
        return false;
    }

    /**
     * Validate a string for the game
     * @param string
     * @return the string contains exactly {@value MAX_PEGS} {@value PEGS} not repeated
     */
    public static boolean validateString(String string){
        if(string.length()!= MAX_PEGS)
            return false;
        for(int i = 0; i<MAX_PEGS; i++){
            if(!validatePeg(string.charAt(i)))
                return false;
            if(string.indexOf(string.charAt(i)) != string.lastIndexOf(string.charAt(i)))
                return false;
        }
        return true;
    }

    /**
     * Check the bulls of guess based on target
     * @param guess
     * @param target
     * @return  -1: invalid guess. See: {@link #validateString(String)} <br/>
     *          -2: invalid target. See: {@link #validateString(String)}  <br/>
     *          n: Number of bulls in guess based on target
     */
    public static int numBulls(String guess, String target){
        if(!validateString(guess))
            return -1;
        if(!validateString(target))
            return -2;
        int c = 0;
        for(int i = 0; i< MAX_PEGS; i++){
            if(guess.charAt(i)== target.charAt(i))
                c++;
        }
        return c;
    }

    /**
     * Check maggots in guess based on target
     * @param guess
     * @param target
     * @return number of character of guess inside target
     */
    public static int numMaggots(String guess, String target){
        if(!validateString(guess))
            return -1;
        if(!validateString(target))
            return -2;
        int c = 0;
        for(int i = 0; i< MAX_PEGS; i++){
            if(target.indexOf(guess.charAt(i))>=0 && guess.charAt(i)!=target.charAt(i) )
                c++;
        }
        return c;
    }

    /**
     *
     * @return
     */
    public static String genTarget(){
        if(MAX_PEGS > PEGS.length/2)
            return null;
        StringBuilder s = new StringBuilder();
        char bucket[] = new char[PEGS.length];
        for(int i = 0; i<PEGS.length; i++){
            bucket[(int)(Math.random()*(PEGS.length-1))] = PEGS[i];
        }
        for(int i = 0; i<PEGS.length ; i++){
            if(bucket[i] != '\0')
                s.append(bucket[i]);
            if(s.length()==MAX_PEGS)
                return s.toString();
        }
        return genTarget();
    }

    /*
    public static String getTarget(){

        Collections.shuffle();
    }
    */
}

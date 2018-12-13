package palyer;

import org.junit.Before;
import org.junit.Test;
import util.Judge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class SmartComputerTest {

    private SmartComputer smartComputer;

    @Before
    public void setUp() throws Exception {
        smartComputer = new SmartComputer();
    }

    @Test
    public void genCombos() {
        ArrayList<String> combos = SmartComputer.genCombos(Judge.MAX_PEGS, Judge.PEGS);
        int pcombos = (int) Math.pow(Judge.PEGS.length, Judge.MAX_PEGS);
        assertEquals(pcombos, combos.size());
        Set<String> set = new HashSet<>(combos);
        assertEquals(combos.size(), set.size());
    }

    @Test
    public void getValidCombos() {
        ArrayList<String> validCombos = SmartComputer.getValidCombos(SmartComputer.genCombos(Judge.MAX_PEGS, Judge.PEGS));
        for(String s: validCombos){
            assertTrue(Judge.validateString(s));
        }
        Set<String> set = new HashSet<>(validCombos);
        assertEquals(validCombos.size(), set.size());

    }

    @Test
    public void matchAll() {
        ArrayList<Attempt> attempts = new ArrayList<>();
        attempts.add(new Attempt("1234", 1, 1));
        attempts.add(new Attempt("8259", 1, 0));
        assertTrue(SmartComputer.matchAll("4537", attempts));
        assertTrue(SmartComputer.matchAll("1873", attempts));

        assertFalse(SmartComputer.matchAll("1234", attempts));
        assertFalse(SmartComputer.matchAll("8524", attempts));
    }

    @Test
    public void match() {
        Attempt attempt = new Attempt("1234", 1, 1);
        assertTrue(SmartComputer.match("4537", attempt));
        assertFalse(SmartComputer.match("1234", attempt));
        assertTrue(SmartComputer.match("8524", attempt));
        attempt = new Attempt("8259", 1, 0);
        assertTrue(SmartComputer.match("4537", attempt));
        assertFalse(SmartComputer.match("1234", attempt));
        assertFalse(SmartComputer.match("8524", attempt));
    }

    @Test
    public void genGuess() {
        String target = smartComputer.getTarget();
        while(true) {
            String guess = smartComputer.genGuess();
            int bulls = Judge.numBulls(guess, target);
            if(bulls == 4)
                break;
            int maggots = Judge.numMaggots(guess, target);
            smartComputer.addAttempt(guess, maggots, bulls);
        }

    }
}
import org.junit.Before;
import org.junit.Test;
import palyer.Player;
import util.Judge;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player player;

    @Before
    public void setUp() throws Exception {
        player = new Player() {
            @Override
            public String genGuess() {
                return null;
            }

            @Override
            public String genTarget() {
                return null;
            }
        };
    }

    @Test
    public void getTarget() {
        String target = player.getTarget();
        assertTrue(Judge.validateString(target));
    }

    @Test
    public void addAttempt() {
        assertFalse(player.addAttempt("1111", 1, 1));
        assertFalse(player.addAttempt("1234", 4, 1));
        assertTrue(player.addAttempt(Judge.genTarget(), 4, 0));
        assertTrue(player.addAttempt(Judge.genTarget(), 1, 1));


    }

    @Test
    public void attemptToString() {
        String guess = Judge.genTarget();
        assertFalse(player.attemptToString().contains(guess));
        player.addAttempt(guess, 1, 1);
        assertTrue(player.attemptToString().contains(guess));
    }

    @Test
    public void init() {
        player.addAttempt(Judge.genTarget(), 1, 1);
        String oldAttempts= player.attemptToString();
        String target =  player.getTarget();
        player.init();
        assertNotEquals(oldAttempts, player.attemptToString());
        assertNotEquals(target, player.getTarget());
    }


}
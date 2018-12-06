import org.junit.Before;
import org.junit.Test;
import palyer.Computer;
import util.Judge;

import static org.junit.Assert.*;

public class ComputerTest {

    private Computer computer;

    @Before
    public void setUp() throws Exception {
        computer = new Computer();
    }

    @Test
    public void genGuess() {
        assertTrue(Judge.validateString(computer.genGuess()));
    }

    @Test
    public void genTarget() {
        assertTrue(Judge.validateString(computer.getTarget()));
    }
}
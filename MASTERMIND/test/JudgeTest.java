import org.junit.Test;
import util.Judge;

import static org.junit.Assert.*;

public class JudgeTest {

    @org.junit.Test
    public void validateString() {
        assertTrue(Judge.validateString("1456"));
        assertFalse(Judge.validateString("e6"));
        assertFalse(Judge.validateString(""));
        assertTrue(Judge.validateString("9564"));
        assertFalse(Judge.validateString("097523"));
        assertFalse(Judge.validateString("1111"));
    }

    @org.junit.Test
    public void numBulls() {

        assertEquals(Judge.numBulls("9876", "5573"),-2);
        assertEquals(Judge.numBulls("9877", "5573"),-1);

        assertEquals(Judge.numBulls("1234", "5678"),0);
        assertEquals(Judge.numBulls("1234", "1876"),1);
        assertEquals(Judge.numBulls("1234", "9874"),1);
        assertEquals(Judge.numBulls("1234", "9243"),1);
        assertEquals(Judge.numBulls("1234", "1256"),2);
        assertEquals(Judge.numBulls("1234", "1537"),2);
        assertEquals(Judge.numBulls("1234","1764"),2);
        assertEquals(Judge.numBulls("1234", "8237"),2);
        assertEquals(Judge.numBulls("1234", "9834"),2);
        assertEquals(Judge.numBulls("9264", "9261"),3);
        assertEquals(Judge.numBulls("9264", "9284"),3);
        assertEquals(Judge.numBulls("9264", "9764"),3);
        assertEquals(Judge.numBulls("9264", "5264"),3);
        assertEquals(Judge.numBulls("9264", "9264"),4);
        assertEquals(Judge.numBulls("8725", "8725"),4);
    }


    @org.junit.Test
    public void numMaggots() {
        assertEquals(Judge.numMaggots("9876", "5573"),-2);
        assertEquals(Judge.numMaggots("9877", "5573"),-1);

        assertEquals(Judge.numMaggots("1234", "1234"),0);
        assertEquals(Judge.numMaggots("1234", "4321"),4);
        assertEquals(Judge.numMaggots("8234", "3746"),2);
        assertEquals(Judge.numMaggots("8234", "9763"),1);
        assertEquals(Judge.numMaggots("1234", "2345"),3);
    }

    @Test
    public void getTarget() {
        for(int i = 0; i< 100000; i++)
            assertTrue(Judge.validateString(Judge.genTarget()));
    }
}
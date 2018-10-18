import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PhoneBookTest {

    @Before
    public void createTest() {
        PhoneBook.create();
        assertEquals(0, PhoneBook.size());
    }

    @Test
    public void testAdd() {
        assertEquals(1, PhoneBook.add("Giovanni=1233456"));
        assertEquals(1, PhoneBook.size());
    }

    @Test
    public void testMax() {
        for (int i = 0; i < PhoneBook.MAX_SIZE; i++) {
            int sizeOld = PhoneBook.size();
            assertEquals(1, PhoneBook.add(i + "=" + i));
            assertEquals(sizeOld+1, PhoneBook.size());
        }
        assertEquals(-1, PhoneBook.add(
                (PhoneBook.MAX_SIZE + 1) +
                "=" +
                (PhoneBook.MAX_SIZE + 1)
        ));
    }

    @Test
    public void testDup(){
        String dup = "Giovanni=1233456";
        assertEquals(1, PhoneBook.add(dup));
        int sizeOld = PhoneBook.size();
        assertEquals(0, PhoneBook.add(dup));
        assertEquals(sizeOld, PhoneBook.size());
    }

    @Test
    public void testFind(){
        //Fill the phonebook with generic elements
        assertEquals(1, PhoneBook.add("gabor=3390000000"));
        assertEquals(1, PhoneBook.add("mario=3310000000"));
        assertEquals(1, PhoneBook.add("mario=3660000000"));
        ArrayList<String> findMario = PhoneBook.find("mario");
        ArrayList<String> findGabor = PhoneBook.find("gabor");
        ArrayList<String> findMichela = PhoneBook.find("michela");
        assertEquals(2, findMario.size());
        assertEquals(1, findGabor.size());
        assertEquals(0, findMichela.size());
    }

    @Test
    public void testPosition(){
        assertEquals(1, PhoneBook.add("gabor=3390000000"));
        assertTrue(PhoneBook.isFirst("gabor=3390000000"));
        assertTrue(PhoneBook.isLast("gabor=3390000000"));

        assertEquals(1, PhoneBook.add("mario=3310000000"));
        assertTrue(PhoneBook.isFirst("gabor=3390000000"));
        assertFalse(PhoneBook.isLast("gabor=3390000000"));
        assertTrue(PhoneBook.isLast("mario=3310000000"));
        assertFalse(PhoneBook.isFirst("mario=3310000000"));

        assertEquals(1, PhoneBook.add("mario=3660000000"));
        assertTrue(PhoneBook.isFirst("gabor=3390000000"));
        assertFalse(PhoneBook.isLast("gabor=3390000000"));
        assertFalse(PhoneBook.isLast("mario=3310000000"));
        assertFalse(PhoneBook.isFirst("mario=3310000000"));
        assertTrue(PhoneBook.isLast("mario=3660000000"));
        assertFalse(PhoneBook.isFirst("mario=3660000000"));
    }

}
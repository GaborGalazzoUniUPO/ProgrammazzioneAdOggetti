
import gabor.utils.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PhoneBookManagerTest {

    @Before
    public void setUp() throws Exception {
        PhoneBookManager.init();
    }

    @After
    public void tearDown() throws Exception {
        PhoneBookManager.reset();
    }

    @Test
    public void create() {
        String name = TestUtils.randomString(10);
        assertEquals(-1, PhoneBookManager.size(name));
        assertEquals(1, PhoneBookManager.create(name, TestUtils.randomInteger(1,5)));
        assertEquals(-1, PhoneBookManager.create(name, TestUtils.randomInteger(1,5)));
        assertEquals(0, PhoneBookManager.size(name));

    }

    @Test
    public void delete() {
        String name = TestUtils.randomString(10);
        PhoneBookManager.create(name, TestUtils.randomInteger(1,5));
        assertEquals(1, PhoneBookManager.delete(name));
        assertEquals(-1, PhoneBookManager.size(name));
    }

    @Test
    public void add() {
        String name = TestUtils.randomString(10);
        String notName = name+TestUtils.randomString(5);
        String contact = TestUtils.randomString(5)+"="+TestUtils.randomString(5);
        PhoneBookManager.create(name, TestUtils.randomInteger(1,5));
        assertEquals(-3, PhoneBookManager.add(notName, contact));
        assertNotEquals(-3, PhoneBookManager.add(name, contact));
    }

    @Test
    public void find() {
        String name = TestUtils.randomString(10);
        String notName = name+TestUtils.randomString(5);
        String contact = TestUtils.randomString(5)+"="+TestUtils.randomString(5);
        PhoneBookManager.create(name, TestUtils.randomInteger(1,5));
        PhoneBookManager.add(name, contact);
        assertNull(PhoneBookManager.find(notName, contact));
        assertNotNull(PhoneBookManager.find(name, contact));
    }

    @Test
    public void toStringTest() {
        String name = TestUtils.randomString(10);
        String notName = name+TestUtils.randomString(5);
        String contact = TestUtils.randomString(5)+"="+TestUtils.randomString(5);
        PhoneBookManager.create(name, TestUtils.randomInteger(1,5));
        PhoneBookManager.add(name, contact);
        assertEquals("", PhoneBookManager.toString(notName));
        assertNotEquals("", PhoneBookManager.toString(name));
    }

    @Test
    public void size() {
        String name = TestUtils.randomString(10);
        assertEquals(-1, PhoneBookManager.size(name));
        PhoneBookManager.create(name, TestUtils.randomInteger(1,5));
        assertNotEquals(-1, PhoneBookManager.size(name));
    }
}
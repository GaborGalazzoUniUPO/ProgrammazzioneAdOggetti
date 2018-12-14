import gabor.utils.TestUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import throwables.InvalidEmailException;
import throwables.InvalidPhoneNumberException;

import static org.junit.Assert.*;

public class ContactTest {


    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void constructor1() throws InvalidEmailException, InvalidPhoneNumberException {
        String name = TestUtils.randomString(10);
        String email = name+"@mail.com";
        StringBuilder phones = new StringBuilder();
        for(int i = 0; i< 5; i++){
            phones.append(TestUtils.randomString(10)).append(",");
        }
        phones.append(TestUtils.randomString(10));
        Contact c = new Contact(name, email, phones.toString());
        assertEquals(name, c.name);
        assertEquals(email, c.getEmail());
        assertFalse(c.getPhones().hasNext());
    }

    @Test
    public void constructor2() throws InvalidEmailException, InvalidPhoneNumberException {
        String name = TestUtils.randomString(10);
        String email = name+"@mail.com";
        Contact c = new Contact(name, email);
        assertEquals(name, c.name);
        assertEquals(email, c.getEmail());
        assertFalse(c.getPhones().hasNext());
    }

    @Test
    public void constructor3() throws InvalidEmailException, InvalidPhoneNumberException {
        String name = TestUtils.randomString(10);
        Contact c = new Contact(name);
        assertEquals(name, c.name);
        assertEquals("", c.getEmail());
        assertFalse(c.getPhones().hasNext());
    }

    @Test
    public void getSetEmail() throws InvalidEmailException, InvalidPhoneNumberException {
        String name = TestUtils.randomString(10);
        Contact c = new Contact(name);
        String email = TestUtils.randomString(10)+"@mail.com";
        assertNotEquals(email, c.getEmail());
        c.setEmail(email);
        assertEquals(email, c.getEmail());
        c.setEmail(name);
        fail();
    }


}
import gabor.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PhoneBookTest {



    private PhoneBook phoneBook;



    @Before
    public void create() {
       phoneBook = new PhoneBook(3, "TEST");
    }


    @Test
    public void getNumPhoneBooks() {
        int old = PhoneBook.getNumPhoneBooks();
        new PhoneBook();
        assertEquals(old+1, PhoneBook.getNumPhoneBooks());

    }

    @Test
    public void add() {

        String n = TestUtils.randomString(10);
        Contact c = new Contact(n);
        assertEquals(1, phoneBook.add(c));
        assertEquals(0, phoneBook.add(c));
        for(int i = phoneBook.size(); i<phoneBook.MAX_SIZE; i++){
            n += n;
            phoneBook.add(new Contact(n));
        }
        n +=n;
        assertEquals(-1, phoneBook.add(n));
    }

    @Test
    public void add1() {
        String n = TestUtils.randomString(10);
        assertEquals(1, phoneBook.add(n));
        assertEquals(0, phoneBook.add(n));
        for(int i = phoneBook.size(); i<phoneBook.MAX_SIZE; i++){
            n += n;
            phoneBook.add(n);
        }
        n +=n;
        assertEquals(-1, phoneBook.add(n));

    }

    @Test
    public void add2() {
        String n = TestUtils.randomString(10);
        String email = n+"@gmail.com";
        assertEquals(1, phoneBook.add(n, email));
        assertEquals(0, phoneBook.add(n, email));
        for(int i = phoneBook.size(); i<phoneBook.MAX_SIZE; i++){
            n += n;
            phoneBook.add(n, email);
        }
        n +=n;
        assertEquals(-1, phoneBook.add(n, email));
    }

    @Test
    public void add3() {
        String n = TestUtils.randomString(10);
        String email = n+"@gmail.com";
        String phones = "123, 234,345,456";
        assertEquals(1, phoneBook.add(n, email, phones));
        assertEquals(0, phoneBook.add(n, email, phones));
        for(int i = phoneBook.size(); i<phoneBook.MAX_SIZE; i++){
            n += n;
            phoneBook.add(n, email, phones);
        }
        n +=n;
        assertEquals(-1, phoneBook.add(n, email, phones));
    }

    @Test
    public void findByName() {
        String n = TestUtils.randomString(10);
        String n1 = TestUtils.randomString(10);
        String n2 = TestUtils.randomString(10);
        phoneBook.add(n);
        phoneBook.add(n1);
        phoneBook.add(n2);
        List<Contact> l =  phoneBook.findByName(n.substring(0, TestUtils.randomInteger(1, n.length()-1)));
        assertFalse(l.isEmpty());
        assertEquals(1, l.size());
        assertEquals(n, l.get(0).name);
    }

    @Test
    public void findByEmail() {
        String n = TestUtils.randomString(10);
        String e = n+"@gmail.com";
        String n1 = TestUtils.randomString(10);
        String e1 = n+"@gmail.com";
        String n2 = TestUtils.randomString(10);
        String e2 = n2+"@gmail.com";
        phoneBook.add(n, e);
        phoneBook.add(n1, e1);
        phoneBook.add(n2, e2);
        List<Contact> l =  phoneBook.findByEmail(e.substring(0, TestUtils.randomInteger(1, n.length()-1)));
        assertFalse(l.isEmpty());
        assertEquals(2, l.size());
        assertEquals(n, l.get(0).name);
    }

    @Test
    public void deleteByName() {
        String n = TestUtils.randomString(10);
        phoneBook.add(n);
        List<Contact> l =  phoneBook.findByName(n);
        assertFalse(l.isEmpty());
        assertEquals(1, l.size());
        assertTrue(phoneBook.deleteByName(n));
        l =  phoneBook.findByName(n);
        assertTrue(l.isEmpty());
        assertFalse(phoneBook.deleteByName(n));


    }

    @Test
    public void deleteByEmail() {
        String n = TestUtils.randomString(10);
        String e = n+"@gmail.com";
        String n1 = TestUtils.randomString(10);
        String e1 = n+"@gmail.com";
        String n2 = TestUtils.randomString(10);
        String e2 = n2+"@gmail.com";
        phoneBook.add(n, e);
        phoneBook.add(n1, e1);
        phoneBook.add(n2, e2);
        List<Contact> l =  phoneBook.findByEmail(e);
        assertFalse(l.isEmpty());
        assertEquals(2, l.size());
        assertTrue(phoneBook.deleteByEmail(e));
        l =  phoneBook.findByEmail(e);
        assertTrue(l.isEmpty());
        assertFalse(phoneBook.deleteByEmail(e));
    }

    @Test
    public void size() {
        int old = PhoneBook.getNumPhoneBooks();
        new PhoneBook();
        assertEquals(old+1, PhoneBook.getNumPhoneBooks());
    }

    @Test
    public void getName() {
        String n = TestUtils.randomString(10);
        PhoneBook p = new PhoneBook(1, n);
        assertEquals(n, p.getName());
        p = new PhoneBook();
        assertEquals("Phonebook "+(PhoneBook.getNumPhoneBooks()-1), p.getName());
    }

    @Test
    public void multiPhoneBook(){
        String n = TestUtils.randomString(10);
        PhoneBook p = new PhoneBook();

        Contact c = new Contact(n);
        assertEquals(1,phoneBook.add(c));
        assertEquals(1, p.add(c));

        assertEquals(phoneBook.findByName(c.name).get(0).getEmail(), p.findByName(c.name).get(0).getEmail());
        c.setEmail(n+"@live.it");
        assertEquals(phoneBook.findByName(c.name).get(0).getEmail(), p.findByName(c.name).get(0).getEmail());
        assertEquals(c.getEmail(), phoneBook.findByName(c.name).get(0).getEmail());
        assertEquals(c.getEmail(), p.findByName(c.name).get(0).getEmail());
    }
}
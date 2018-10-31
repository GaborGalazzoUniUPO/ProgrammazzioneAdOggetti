import gabor.utils.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.Random;

import static org.junit.Assert.*;

public class PhoneBookTestGiannini {
    
    private PhoneBook phoneBook;



    @Before
    public void creazione() {
        int old = PhoneBook.getNumPhonebooks();
        String name = TestUtils.randomString();
        int maxDim = TestUtils.randomInteger();
        phoneBook = new PhoneBook(maxDim, name);
        assertEquals(old +1, phoneBook.getNumPhonebooks());
        assertEquals(maxDim, phoneBook.MAX_SIZE);
        assertEquals(name, phoneBook.getName());

    }

    @Test
    public void emptyConstructor(){
        int old = PhoneBook.getNumPhonebooks();
        PhoneBook phoneBook = new PhoneBook();
        assertEquals(old +1, PhoneBook.getNumPhonebooks());
        assertEquals(phoneBook.getName(), "Phonebook "+old);
        assertEquals(5, phoneBook.MAX_SIZE);
    }

    @After
    public void resetRubrica() {
    }

    @Test
    public void testCreazione() {
        assertEquals(0, phoneBook.size());
    }



    @Test
    public void testAggiungi() {
        for(int i = 0; i < TestUtils.randomInteger(1, phoneBook.MAX_SIZE-1); i++) {
            assertEquals(i, phoneBook.size());
            assertEquals(1, phoneBook.add(TestUtils.randomString()));
            assertEquals(i+1, phoneBook.size());
        }
    }

    @Test
    public void testAggiuntaMassima() {
        for(int i = 0; i < phoneBook.MAX_SIZE; i++) {
            assertEquals(i, phoneBook.size());
            assertEquals(1, phoneBook.add(TestUtils.randomString()));
            assertEquals(i+1, phoneBook.size());
        }
    }

    @Test
    public void testAggiuntaOltreLimite() {
        testAggiuntaMassima();
        assertEquals(-1, phoneBook.add(TestUtils.randomString()));
        assertEquals(phoneBook.MAX_SIZE, phoneBook.size());
    }

    @Test
    public void testAggiuntaGiaPresente() {
        testAggiungi();
        int oldSize = phoneBook.size();
        assertEquals(0, phoneBook.add(phoneBook.find("").get(0)));
        assertEquals(oldSize, phoneBook.size());
    }

    @Test
    public void cercaRubricaVuota() {
        assertEquals(0, phoneBook.find("").size());
        assertEquals(0, phoneBook.size()); // Non ci sono stringhe
    }

    @Test
    public void testRicerca() {
        addElementiRubricaStatica(); // Aggiunge 6 elementi alla rubrica
        assertEquals(1, phoneBook.find("Giov").size()); // Presente come primo e
        // ultimo
        assertEquals(1, phoneBook.find("Sara").size());
        assertEquals(0, phoneBook.find("Sat").size()); // Non ci sono stringhe
        // con questo prefisso
        assertEquals(0, phoneBook.find("123").size()); // ma ce ne sono che lo
        // contengono
        assertEquals(2, phoneBook.find("G").size());
        assertEquals(1, phoneBook.find("Giorgio=7689453201").size());
        assertEquals(0, phoneBook.find("Giorgio=7689453200").size()); // Confrontare
        // con
        // precedente
        assertEquals(5, phoneBook.find("").size()); // Tutte le stringhe inizano
        // con la stringa vuota
    }

    @Test
    public void testEliminaVuota() {
        assertEquals(0, phoneBook.size());
        assertFalse(phoneBook.delete(""));
        assertEquals(0, phoneBook.size());
    }

    @Test
    public void testElimina() {
        addElementiRubricaStatica(); // Aggiunge 6 elementi alla rubrica
        assertTrue(phoneBook.delete("")); // Li rimuove tutti
        assertEquals(0, phoneBook.size()); // Non ci sono piu' stringhe
        addElementiRubricaStatica(); // Ri-aggiunge 6 elementi alla rubrica
        assertTrue(phoneBook.delete("Giov"));
        assertEquals(4, phoneBook.size());
        assertFalse(phoneBook.delete("Sat"));
        assertEquals(4, phoneBook.size());
        assertFalse(phoneBook.delete("Giorgio=7689453200"));
        assertEquals(4, phoneBook.size());
        assertTrue(phoneBook.delete("Giorgio=7689453201"));
        assertEquals(3, phoneBook.size());
        assertTrue(phoneBook.delete("E"));
        assertTrue(phoneBook.delete("S"));
        assertTrue(phoneBook.delete("P"));
        assertEquals(0, phoneBook.size());
    }

    private void addElementiRubricaStatica() {
        phoneBook.add("Giovanni=0123456789");
        phoneBook.add("Enzo=4593782610");
        phoneBook.add("Paola=0123456789");
        phoneBook.add("Sara=0354768912");
        phoneBook.add("Giorgio=7689453201");
    }
}
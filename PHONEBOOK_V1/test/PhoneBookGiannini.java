import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PhoneBookGiannini {

    @Before
    public void creazione() {
//		System.out.println("BEFORE");
        PhoneBook.create();
    }

    @After
    public void resetRubrica() {
//		System.out.println("AFTER");
    }

    @Test
    public void testCreazione() {
//		System.out.println("CREAZIONE");
        assertTrue(PhoneBook.size() == 0);
    }

    @Test
    public void testAggiungi() {
//		System.out.println("AGGIUNGI");
        assertEquals(1, PhoneBook.add("Giovanni=123456"));
        assertEquals(1, PhoneBook.size());
        assertEquals(1, PhoneBook.add("Mattia = 345216789"));
        assertEquals(2, PhoneBook.size());
    }

    @Test
    public void testAggiuntaMassima() {
//		System.out.println("AGGIUNTA_MASSIMA");
        assertEquals(1, PhoneBook.add("Mattia = 345216789"));
        assertEquals(1, PhoneBook.size());
        assertEquals(1, PhoneBook.add("Roberta = 312789560"));
        assertEquals(2, PhoneBook.size());
        assertEquals(1, PhoneBook.add("Alice = 333214576"));
        assertEquals(3, PhoneBook.size());
        assertEquals(1, PhoneBook.add("Matteo = 312456712"));
        assertEquals(4, PhoneBook.size());
        assertEquals(1, PhoneBook.add("Enzo = 367891239"));
        assertEquals(5, PhoneBook.size());
    }

    @Test
    public void testAggiuntaOltreLimite() {
//		System.out.println("AGGIUNTA_OLTRE_LIMITE");
        assertEquals(1, PhoneBook.add("Martina = 346789123"));
        assertEquals(1, PhoneBook.size());
        assertEquals(1, PhoneBook.add("Sara = 331452673"));
        assertEquals(2, PhoneBook.size());
        assertEquals(1, PhoneBook.add("Lucia = 333123789"));
        assertEquals(3, PhoneBook.size());
        assertEquals(1, PhoneBook.add("Margherita = 334331245"));
        assertEquals(4, PhoneBook.size());
        assertEquals(1, PhoneBook.add("Noemi = 345123879"));
        assertEquals(5, PhoneBook.size());
        assertEquals(1, PhoneBook.add("Graziella = 331456234"));
        assertEquals(6, PhoneBook.size());
        assertEquals(-1, PhoneBook.add("Paolo = 331456234"));
        assertEquals(6, PhoneBook.size());
    }

    @Test
    public void testAggiuntaGiaPresente() {
//		System.out.println("AGGIUNTA_GIA_PRESENTE");
        PhoneBook.add("Martina = 346789123");
        assertEquals(1, PhoneBook.size());
        PhoneBook.add("Sara = 331452673");
        assertEquals(2, PhoneBook.size());
        PhoneBook.add("Marco = 345678123");
        assertEquals(3, PhoneBook.size());
        assertEquals(0, PhoneBook.add("Martina = 346789123"));
        assertEquals(3, PhoneBook.size());
        assertEquals(0, PhoneBook.add("Marco = 345678123"));
        assertEquals(3, PhoneBook.size());
    }

    @Test
    public void cercaRubricaVuota() {
//		System.out.println("CERCA_VUOTA");
        assertEquals(0, PhoneBook.find("Sat").size());
        assertEquals(0, PhoneBook.size()); // Non ci sono stringhe
    }

    @Test
    public void testRicerca() {
//		System.out.println("RICERCA");
        addElementiRubricaStatica(); // Aggiunge 6 elementi alla rubrica
        assertEquals(2, PhoneBook.find("Giov").size()); // Presente come primo e
        // ultimo
        assertEquals(1, PhoneBook.find("Sara").size());
        assertEquals(0, PhoneBook.find("Sat").size()); // Non ci sono stringhe
        // con questo prefisso
        assertEquals(0, PhoneBook.find("123").size()); // ma ce ne sono che lo
        // contengono
        assertEquals(3, PhoneBook.find("G").size());
        assertEquals(1, PhoneBook.find("Giorgio=7689453201").size());
        assertEquals(0, PhoneBook.find("Giorgio=7689453200").size()); // Confrontare
        // con
        // precedente
        assertEquals(6, PhoneBook.find("").size()); // Tutte le stringhe inizano
        // con la stringa vuota
    }

    @Test
    public void testEliminaVuota() {
//		System.out.println("ELIMINA_VUOTA");
        assertFalse(PhoneBook.delete("Giov"));
        assertEquals(0, PhoneBook.size()); // Non ci sono stringhe
        assertFalse(PhoneBook.delete("")); // Li rimuove tutti
        assertEquals(0, PhoneBook.size()); // Non ci sono piu' stringhe
    }

    @Test
    public void testElimina() {
//		System.out.println("ELIMINA");
        addElementiRubricaStatica(); // Aggiunge 6 elementi alla rubrica
        assertTrue(PhoneBook.delete("")); // Li rimuove tutti
        assertEquals(0, PhoneBook.size()); // Non ci sono piu' stringhe
        addElementiRubricaStatica(); // Ri-aggiunge 6 elementi alla rubrica
        assertTrue(PhoneBook.delete("Giov"));
        assertEquals(4, PhoneBook.size());
        assertFalse(PhoneBook.delete("Sat"));
        assertEquals(4, PhoneBook.size());
        assertFalse(PhoneBook.delete("Giorgio=7689453200"));
        assertEquals(4, PhoneBook.size());
        assertTrue(PhoneBook.delete("Giorgio=7689453201"));
        assertEquals(3, PhoneBook.size());
        assertTrue(PhoneBook.delete("E"));
        assertTrue(PhoneBook.delete("S"));
        assertTrue(PhoneBook.delete("P"));
        assertEquals(0, PhoneBook.size());
    }

    private void addElementiRubricaStatica() {
        PhoneBook.add("Giovanni=0123456789");
        PhoneBook.add("Enzo=4593782610");
        PhoneBook.add("Paola=0123456789");
        PhoneBook.add("Sara=0354768912");
        PhoneBook.add("Giorgio=7689453201");
        PhoneBook.add("Giovanni=0123456784");
    }
}
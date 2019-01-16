import java.util.ArrayList;
import java.util.Arrays;

public class PhoneBook {

    private static int numPhoneBooks = 0;

    public final int MAX_SIZE;
    private final ArrayList<Contact> phoneBook;
    private final String name;


    /**
     * Initialize this object with MAX_SIZE = 5 and name = "Phonebook " + numPhoneBooks
     */
    public PhoneBook() {
        this(5, "Phonebook " + numPhoneBooks);
    }

    /**
     * @param maxSize the max number of element to insert in the phoneBook
     * @param name the name of this phoneBook
     */
    public PhoneBook(int maxSize, String name) {
        numPhoneBooks++;
        MAX_SIZE = maxSize;
        this.name = name;
        phoneBook = new ArrayList<>();
    }

    /**
     * @return how much phoneBook were instantiated
     */
    public static int getNumPhoneBooks() {
        return numPhoneBooks;
    }

    /**
     * Add an element to <b>phoneBook</b>
     * @param c The element to add
     * @return 1: Added Correctly <br>
     * 0: Duplicate found: NOT ADDED <br>
     * -1: MAX_SIZE achieved: NOT ADDED <br>
     */
    public int add(Contact c) {
        if (phoneBook.size() == MAX_SIZE)
            return -1;
        if (phoneBook.contains(c))
            return 0;
        phoneBook.add(c);
        return 1;
    }


    /**
     * Create a contact ad add it to the phoneBook. See also {@link Contact#Contact(String, String, String)}
     * @param name contact's name
     * @param email contact's email
     * @param phones contact's phones
     * @return {@link #add(Contact)}
     */
    public  int add(String name, String email, String phones) {
        return add(new Contact(name, email, phones));
    }

    /**
     * Create a contact ad add it to the phoneBook. See also {@link Contact#Contact(String, String)}
     * @param name contact's name
     * @param email contact's email
     * @return {@link #add(Contact)}
     */
    public  int add(String name, String email) {
        return add(new Contact(name, email));
    }

    /**
     * Create a contact ad add it to the phoneBook. See also {@link Contact#Contact(String)}
     * @param name contact's name
     * @return {@link #add(Contact)}
     */
    public  int add(String name) {
        return add(new Contact(name));
    }
    /**
     * Find all contact in <b>phoneBook</b> with name <b>name</b>
     * @param name name to search
     * @return The list of found elements
     */
    public ArrayList<Contact> findByName(String name) {
        ArrayList<Contact> findResults = new ArrayList<>();
        for (Contact elem :
                phoneBook)
            if (elem.name.startsWith(name))
                findResults.add(elem);
        return findResults;
    }

    /**
     * Find all contact in <b>phoneBook</b> with email <b>email</b>
     * @param email email to search
     * @return The list of found elements
     */
    public ArrayList<Contact> findByEmail(String email) {
        ArrayList<Contact> findResults = new ArrayList<>();
        for (Contact elem :
                phoneBook)
            if (elem.getEmail().startsWith(email))
                findResults.add(elem);
        return findResults;
    }


    /**
     * Delete all contact in <b>phoneBook</b> with name <b>entry</b>
     * @param entry name to search
     * @return have deleted something
     */
    public boolean deleteByName(String entry) {
        ArrayList<Contact> toDelete = findByName(entry);
        return phoneBook.removeAll(toDelete);
    }

    /**
     * Delete all contact in <b>phoneBook</b> with email  <b>entry</b>
     * @param entry name to search
     * @return have deleted something
     */
    public boolean deleteByEmail(String entry) {
        ArrayList<Contact> toDelete = findByEmail(entry);
        return phoneBook.removeAll(toDelete);
    }

    /**
     * @return phoneBook's size
     */
    public int size() {
        return phoneBook.size();
    }

    /**
     * @return phoneBook's name
     */
    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "PhoneBook{" +
                "phoneBook=" + phoneBook +
                '}';
    }
}

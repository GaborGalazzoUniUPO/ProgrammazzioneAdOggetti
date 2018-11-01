import java.util.ArrayList;
import java.util.Arrays;

public class PhoneBook {

    private static int numPhoneBooks = 0;

    public final int MAX_SIZE;
    private final ArrayList<String> phoneBook;
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
     * @param s The element to add
     * @return 1: Added Correctly <br/>
     * 0: Duplicate found: NOT ADDED <br/>
     * -1: MAX_SIZE achieved: NOT ADDED <br/>
     * -2: Invalid Element: NOT ADDED
     */
    public int add(String s) {
        if (!isValid(s)) /** TEST relativi -> {@link PhoneBookTestGiannini.failAggiungi() failAggiungi} */
            return -2;
        if (phoneBook.size() == MAX_SIZE)
            return -1;
        if (phoneBook.contains(s))
            return 0;
        phoneBook.add(s);
        return 1;
    }

    /**
     * Check if the contact is valid
     * @param s FORMAT: "notBlank=notBlank"
     * @return is valid input
     */
    private boolean isValid(String s) {
        String split[] = s.split("=");
        return split.length == 2 &&
                !split[0].isEmpty() &&
                !split[1].isEmpty() &&
                !isBlank(split[0]) &&
                !isBlank(split[1]);
    }

    /**
     * Check if string is Blank
     * @param s a generic string
     * @return isBlank
     */
    private boolean isBlank(String s){
        return s.isEmpty() || s.replace(" ", "").isEmpty();
    }

    /**
     * Find all contact in <b>phoneBook</b> starting with <b>str</b>
     * @param str string to search
     * @return The list of found elements
     */
    public ArrayList<String> find(String str) {
        ArrayList<String> findResults = new ArrayList<>();
        for (String elem :
                phoneBook)
            if (elem.startsWith(str))
                findResults.add(elem);
        return findResults;
    }


    /**
     * Delete all contact in <b>phoneBook</b> starting with <b>entry</b>
     * @param entry string to search
     * @return have deleted something
     */
    public boolean delete(String entry) {
        ArrayList<String> toDelete = find(entry);
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
        // return phoneBook.toString();
        StringBuilder s = new StringBuilder();
        for (String c : phoneBook)
            s.append(c).append(",");
        s.deleteCharAt(s.length() - 1);
        return s.toString();
    }
}

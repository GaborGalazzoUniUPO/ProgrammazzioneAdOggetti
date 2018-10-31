import java.util.ArrayList;
import java.util.Arrays;

public class PhoneBook {

    private static int numPhonebooks = 0;

    public final int MAX_SIZE;
    private final ArrayList<String> phoneBook;
    private final String name;


    public PhoneBook(){
        this(5, "Phonebook "+numPhonebooks);
    }

    public PhoneBook(int maxSize, String name){
        numPhonebooks++;
        MAX_SIZE = maxSize;
        this.name = name;
        phoneBook = new ArrayList<>();
    }

    public static int getNumPhonebooks() {
        return numPhonebooks;
    }

    /**
     * Add an element to the PhoneBook
     * @param s The element to add
     * @return  1: Added Correctly <br/>
     *          0: Duplicate found: NOT ADDED <br/>
     *          -1: MAX_SIZE achieved: NOT ADDED
     */
    public int add(String s) {
        if(phoneBook.size() == MAX_SIZE)
            return -1;
        if(phoneBook.contains(s))
            return 0;
        phoneBook.add(s);
        return 1;
    }

    public ArrayList<String> find(String str) {
        ArrayList<String> findResults = new ArrayList<>();
        for (String elem :
                phoneBook)
            if (elem.startsWith(str))
                findResults.add(elem);
        return findResults;
    }


    public boolean delete(String entry) {
        ArrayList<String> toDelete = find(entry);
        return phoneBook.removeAll(toDelete);
    }

    public int size() {
        return phoneBook.size();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(String c: phoneBook)
            s.append(c).append(",");
        s.deleteCharAt(s.length()-1);
        return s.toString();
    }
}

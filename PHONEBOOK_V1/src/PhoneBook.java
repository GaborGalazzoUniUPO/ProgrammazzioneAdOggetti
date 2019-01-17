import java.lang.reflect.Array;
import java.util.ArrayList;

public class PhoneBook {

    public static final int MAX_SIZE = 6;


    private static ArrayList<String> phoneBook;


    public static void create() {
        phoneBook = new ArrayList<>();
    }

    public static int size() {
        return phoneBook.size();
    }

    /**
     * Add an element to the PhoneBook
     * @param s The element to add
     * @return  1: Added Correctly <br>
     *          0: Duplicate found: NOT ADDED <br>
     *          -1: MAX_SIZE achieved: NOT ADDED
     */
    public static int add(String s) {
        if(phoneBook.size() == MAX_SIZE)
            return -1;
        if(phoneBook.contains(s))
            return 0;
        phoneBook.add(s);
        return 1;
    }

    public static ArrayList<String> find(String mario) {
        ArrayList<String> findResults = new ArrayList<>();
        for (String elem :
                phoneBook)
            if (elem.startsWith(mario))
                findResults.add(elem);
        return findResults;
    }

    public static boolean isFirst(String s) {
        if(phoneBook == null  || phoneBook.size() == 0)
            return false;
        return phoneBook.get(0).equals(s);
    }

    public static boolean isLast(String s) {
        if(phoneBook == null || phoneBook.size() == 0)
            return false;
        return phoneBook.get(phoneBook.size()-1).equals(s);
    }

    public static boolean delete(String entry) {
        ArrayList<String> toDelete = new ArrayList<>();
        for(String contact : phoneBook)
            if(contact.startsWith(entry))
               toDelete.add(contact);
        return phoneBook.removeAll(toDelete);
    }
}

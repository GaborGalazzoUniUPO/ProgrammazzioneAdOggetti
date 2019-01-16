import java.util.ArrayList;
import java.util.HashMap;

public class PhoneBookManager {

    private static HashMap<String, PhoneBook> phoneBookList;

    /**
     * Initialize the hashMap
     */
    public static void init() {
        phoneBookList =new HashMap<String, PhoneBook>();
    }

    /**
     * Reset the hashMap
     */
    public static void reset() {
        phoneBookList =null;
    }

    /**
     * Create a PhoneBook and add it to the List
     * @param phoneBookName new phoneBook's name
     * @param dimension the phoneBook's max size
     * @return  -1: PhoneBook with that name exists<br>
     *          1: PhoneBook created and added
     */
    public static int create(String phoneBookName, int dimension){

        if(phoneBookList.containsKey(phoneBookName))
            return -1;
        PhoneBook newPhoneBook = new PhoneBook(dimension, phoneBookName);
        phoneBookList.put(phoneBookName, newPhoneBook);
        return 1;
    }

    /**
     * Remove a phoneBook from phoneBookList
     * @param phoneBookName name of the phoneBook to remove
     * @return  1: PhoneBook removed<br>
     *          -1: phoneBookName not found
     */
    public static int delete(String phoneBookName){
        return phoneBookList.remove(phoneBookName)==null?-1:1;
    }

    /**
     * Add contact to the phoneBook with phoneBookName name
     * @param phoneBookName name of the phoneBook
     * @param contact contact to add
     * @return  -3: phoneBookName not found<br>
     *          See also {@link PhoneBook#add(String)}
     *
     */
    public static int add(String phoneBookName, String contact){
        PhoneBook phoneBook = phoneBookList.get(phoneBookName);
        if(phoneBook == null)
            return -3;
        return phoneBook.add(contact);
    }

    /**
     * Find prefix in phoneBook with phoneBookName name
     * @param phoneBookName name of the phoneBook
     * @param prefix needle to find
     * @return  null: phoneBookName not found<br>
     *          See also {@link PhoneBook#findByName(String)}
     */
    public static ArrayList<Contact> find(String phoneBookName, String prefix){
        PhoneBook phoneBook = phoneBookList.get(phoneBookName);
        if(phoneBook == null)
            return null;
        return phoneBook.findByName(prefix);
    }

    /**
     * Stringify a phoneBook
     * @param phoneBookName name of the phoneBook
     * @return  "": phoneBookName not found<br>
     *          See also {@link PhoneBook#toString()}
     */
    public static String toString(String phoneBookName){
        PhoneBook phoneBook = phoneBookList.get(phoneBookName);
        if(phoneBook == null)
            return "";
        return phoneBook.toString();
    }

    /**
     * Return the phoneBook with phoneBookName name size
     * @param phoneBookName name of the phoneBook
     * @return  -1: phoneBookName not found<br>
     *          See also {@link PhoneBook#size()}
     */
    public static int size(String phoneBookName){
        PhoneBook phoneBook = phoneBookList.get(phoneBookName);
        if(phoneBook == null)
            return -1;
        return phoneBook.size();
    }
}

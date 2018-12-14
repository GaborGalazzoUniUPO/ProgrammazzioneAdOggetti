import throwables.InvalidEmailException;
import throwables.InvalidPhoneNumberException;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.naming.SizeLimitExceededException;
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
     */
    public static void create(String phoneBookName, int dimension) throws InstanceAlreadyExistsException {

        if(phoneBookList.containsKey(phoneBookName))
            throw new InstanceAlreadyExistsException(phoneBookName+" already exists in phoneBookList");
        PhoneBook newPhoneBook = new PhoneBook(dimension, phoneBookName);
        phoneBookList.put(phoneBookName, newPhoneBook);
    }

    /**
     * Remove a phoneBook from phoneBookList
     * @param phoneBookName name of the phoneBook to remove
     */
    public static void delete(String phoneBookName) throws InstanceNotFoundException {
        PhoneBook phoneBook = phoneBookList.get(phoneBookName);
        if(phoneBook == null)
            throw new InstanceNotFoundException(phoneBookName+" not found in phoneBookList");
        phoneBookList.remove(phoneBookName);
    }

    /**
     * Add contact to the phoneBook with phoneBookName name
     * @param phoneBookName name of the phoneBook
     * @param contact contact to add
     * @return  -3: phoneBookName not found<br/>
     *          See also {@link PhoneBook#add(String)}
     *
     */
    public static boolean add(String phoneBookName, String contact) throws SizeLimitExceededException, InvalidEmailException, InvalidPhoneNumberException, InstanceAlreadyExistsException, InstanceNotFoundException {
        PhoneBook phoneBook = phoneBookList.get(phoneBookName);
        if(phoneBook == null)
            throw new InstanceNotFoundException(phoneBookName+" not found in phoneBookList");
        return phoneBook.add(contact);
    }

    /**
     * Find prefix in phoneBook with phoneBookName name
     * @param phoneBookName name of the phoneBook
     * @param prefix needle to find
     * @return  null: phoneBookName not found<br/>
     *          See also {@link PhoneBook#findByName(String)}
     */
    public static ArrayList<Contact> find(String phoneBookName, String prefix) throws InstanceNotFoundException {
        PhoneBook phoneBook = phoneBookList.get(phoneBookName);
        if(phoneBook == null)
            throw new InstanceNotFoundException(phoneBookName+" not found in phoneBookList");
        return phoneBook.findByName(prefix);
    }

    /**
     * Stringify a phoneBook
     * @param phoneBookName name of the phoneBook
     * @return  "": phoneBookName not found<br/>
     *          See also {@link PhoneBook#toString()}
     */
    public static String toString(String phoneBookName) throws InstanceNotFoundException {
        PhoneBook phoneBook = phoneBookList.get(phoneBookName);
        if(phoneBook == null)
            throw new InstanceNotFoundException(phoneBookName+" not found in phoneBookList");
        return phoneBook.toString();
    }

    /**
     * Return the phoneBook with phoneBookName name size
     * @param phoneBookName name of the phoneBook
     * @return  -1: phoneBookName not found<br/>
     *          See also {@link PhoneBook#size()}
     */
    public static int size(String phoneBookName) throws InstanceNotFoundException {
        PhoneBook phoneBook = phoneBookList.get(phoneBookName);
        if(phoneBook == null)
            throw new InstanceNotFoundException(phoneBookName+" not found in phoneBookList");
        return phoneBook.size();
    }
}

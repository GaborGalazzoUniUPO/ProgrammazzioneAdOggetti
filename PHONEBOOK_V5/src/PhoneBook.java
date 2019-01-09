import throwables.InvalidEmailException;
import throwables.InvalidPhoneNumberException;

import javax.management.InstanceAlreadyExistsException;
import javax.naming.SizeLimitExceededException;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class PhoneBook implements Iterable<Contact>{

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
     * @return {@link ArrayList#add(Object)}
     */
    public boolean add(Contact c) throws InstanceAlreadyExistsException, SizeLimitExceededException {
        if (phoneBook.size() == MAX_SIZE)
            throw new SizeLimitExceededException();
        if (phoneBook.contains(c))
            throw new InstanceAlreadyExistsException();
        return phoneBook.add(c);
    }


    /**
     * Create a contact ad add it to the phoneBook. See also {@link Contact#Contact(String, String, String)}
     * @param name contact's name
     * @param email contact's email
     * @param phones contact's phones
     * @return {@link #add(Contact)}
     */
    public  boolean add(String name, String email, String phones) throws InvalidEmailException, InvalidPhoneNumberException, InstanceAlreadyExistsException, SizeLimitExceededException {
        return add(new Contact(name, email, phones));
    }

    /**
     * Create a contact ad add it to the phoneBook. See also {@link Contact#Contact(String, String)}
     * @param name contact's name
     * @param email contact's email
     * @return {@link #add(Contact)}
     */
    public  boolean add(String name, String email) throws InvalidEmailException, InvalidPhoneNumberException, InstanceAlreadyExistsException, SizeLimitExceededException {
        return add(new Contact(name, email));
    }

    /**
     * Create a contact ad add it to the phoneBook. See also {@link Contact#Contact(String)}
     * @param name contact's name
     * @return {@link #add(Contact)}
     */
    public  boolean add(String name) throws InvalidEmailException, InvalidPhoneNumberException, InstanceAlreadyExistsException, SizeLimitExceededException {
        return add(new Contact(name));
    }

    /**
     * Load from the file filename all contacts
     * @param filename a csv file in format {@link Contact#fromCsv(String)}
     */
    public void addFromFile(String filename) throws IOException, InstanceAlreadyExistsException, SizeLimitExceededException, InvalidEmailException, InvalidPhoneNumberException {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        while ((line = bufferedReader.readLine())!=null){
            Contact contact = Contact.fromCsv(line);
            add(contact);
        }
        bufferedReader.close();
        fileReader.close();
    }

    /**
     * Save all contact to file filename readable by {@link #addFromFile(String)}
     * @param filename filename for file
     */
    public void saveToFile(String filename) throws IOException{
        PrintWriter printWriter = new PrintWriter(filename);
        for(Contact c: phoneBook){
            printWriter.println(c);
        }
        printWriter.close();
    }

    /**
     * Check if there are two or more identical email between contacts in phonebook
     * @param phoneBook the phonebook to check
     * @return phoneBook.findByEmail(c.getEmail()).size()>1
     */
    public static boolean hasDoubleEmails(PhoneBook phoneBook){
        for(Contact c: phoneBook){
            if(phoneBook.findByEmail(c.getEmail()).size()>1)
                return true;
        }
        return false;
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

    @Override
    public Iterator<Contact> iterator() {
        return new ContactIterator();
    }

    private class ContactIterator implements Iterator<Contact>{

        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor<phoneBook.size();
        }

        @Override
        public Contact next() {
            return phoneBook.get(cursor++);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

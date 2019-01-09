import com.sun.javaws.exceptions.InvalidArgumentException;
import throwables.InvalidEmailException;
import throwables.InvalidPhoneNumberException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 *
 */
public class Contact {

    public final String name;
    private String email;
    private ArrayList<String> phones;

    /**
     * Construct a contact
     * @param name contact's name
     * @param email contact's email
     * @param phones contact's phones (num1,num2,...,numN)
     */
    public Contact(String name, String email, String phones) throws InvalidEmailException, InvalidPhoneNumberException {
        this.name = name;
        setEmail(email);
        this.phones = new ArrayList<String>();
        for(String s: phones.split(",")){
            if(!s.isEmpty())
                addPhone(s);
        }
    }


    /**
     * Construct a contact with empty phones
     * @param name contact's name
     * @param email contact's email
     */
    public Contact(String name, String email) throws InvalidEmailException, InvalidPhoneNumberException {
        this(name, email, "");
    }

    /**
     * Construct a contact with empty phones and empty email
     * @param name contact's name
     */
    public Contact(String name) throws InvalidEmailException, InvalidPhoneNumberException {
        // this(name, "", ""); -> FIX REDUNDANCY
        this(name, "");
    }

    /**
     *
     * @param line A csv line of a contact "name","email","phone1,phone2,phone3,...,phoneN"
     * @return a new Contact generated from csv DATA
     * @see Contact
     */
    public static Contact fromCsv(String line) throws InvalidEmailException, InvalidPhoneNumberException {
        String[] data = line.split("\",\"|^\"|\"$");

        return new Contact(data[1], data[2], data[3]);
    }

    /**
     * Add a phone number
     * @param phone number to add
     */
    public void addPhone(String phone) throws InvalidPhoneNumberException {
        if(phone.matches("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$"))
            phones.add(phone);
        else
            throw new InvalidPhoneNumberException("invalid phone: "+phone);
    }

    /**
     * Return an iterator of phones
     * @return iterator of String
     */
    public Iterator<String> getPhones(){

        return phones.iterator();
    }


    /**
     * Return contact's email
     * @return actual contact's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set contact's email
     * @param email new contact's email
     */
    public void setEmail(String email) throws InvalidEmailException {
        if(email.equals("") || email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"))
        this.email = email;
        else throw new InvalidEmailException("invalid email: "+email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return Objects.equals(name, contact.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\"" + name + "\",\"" + email + "\",\"");
        int i;
        for(i = 0; i<phones.size()-1; i++){
            s.append(phones.get(i)).append(",");
        }
        s.append(phones.get(i));
        s.append("\"");
        return s.toString();
    }
}

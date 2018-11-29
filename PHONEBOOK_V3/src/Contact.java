import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

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
    public Contact(String name, String email, String phones) {
        this.name = name;
        setEmail(email);
        this.phones = new ArrayList<String>();
        for(String s: phones.split(",")){
            addPhone(s);
        }
    }


    /**
     * Construct a contact with empty phones
     * @param name contact's name
     * @param email contact's email
     */
    public Contact(String name, String email) {
        this(name, email, "");
    }

    /**
     * Construct a contact with empty phones and empty email
     * @param name contact's name
     */
    public Contact(String name) {
        // this(name, "", ""); -> FIX REDUNDANCY
        this(name, "");
    }

    /**
     * Add a phone number
     * @param phone number to add
     */
    private void addPhone(String phone) {
        phones.add(phone);
    }

    /**
     * Return a string of all phones
     * @return coma separated string of all contact's phones (num1,num2,...,numN)
     */
    public String getPhones(){

        StringBuilder phones = new StringBuilder();
        int i = 0;
        for(; i< this.phones.size()-1; i++){
            phones.append(this.phones.get(i)+",");
        }
        phones.append(this.phones.get(i));
        return phones.toString();
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
    public void setEmail(String email) {
        this.email = email;
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
        return "Contact{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phones=" + phones +
                '}';
    }
}

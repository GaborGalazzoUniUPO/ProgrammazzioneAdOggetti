package throwables;

import com.sun.javaws.exceptions.InvalidArgumentException;

public class InvalidPhoneNumberException extends InvalidArgumentException {
    public InvalidPhoneNumberException(String string) {
        super(new String[]{string});
    }
}

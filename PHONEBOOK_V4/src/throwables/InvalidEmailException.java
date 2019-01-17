package throwables;

import com.sun.javaws.exceptions.InvalidArgumentException;

public class InvalidEmailException extends InvalidArgumentException {

    public InvalidEmailException(String string) {
        super(new String[]{string});
    }
}

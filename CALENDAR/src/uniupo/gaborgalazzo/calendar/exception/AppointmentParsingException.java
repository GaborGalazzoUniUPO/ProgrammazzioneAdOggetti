package uniupo.gaborgalazzo.calendar.exception;

import com.google.gson.JsonElement;

/**
 * Exception to handle errors while trying to create an appointment from any  type source
 *
 * @author Gabor Galazzo
 * @version 1.0.0
 */
public class AppointmentParsingException extends Exception{

    private final Object source;
    private final Exception exception;

    /**
     * Instantiates a new AppointmentParsingException.
     *
     * @param source the json element source
     * @param exception  the exception thrown
     */
    public AppointmentParsingException(Object source, Exception exception) {
        this.source = source;
        this.exception = exception;
    }

    public String getMessage(){
        return "Cannot load:\n"+source+"\nBecause:\n"+ exception;
    }

    /**
     * Gets source element.
     *
     * @return the source element
     */
    public Object getSource() {
        return source;
    }

    /**
     * Gets exception.
     *
     * @return the exception
     */
    public Exception getException() {
        return exception;
    }

}

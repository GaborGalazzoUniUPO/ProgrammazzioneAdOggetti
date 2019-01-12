package uniupo.gaborgalazzo.calendar.exception;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

/**
 * Exception to handle errors while trying to create an appointment from a json element
 *
 * @author Gabor Galazzo
 * @version 1.0.0
 */
public class AppointmentJsonParsingException extends Exception{

    private final JsonElement jsonElement;
    private final Exception exception;

    /**
     * Instantiates a new Appointment json parsing exception.
     *
     * @param jsonElement the json element source
     * @param exception           the error thrown
     */
    public AppointmentJsonParsingException(JsonElement jsonElement, Exception exception) {
        this.jsonElement = jsonElement;
        this.exception = exception;
    }

    public String getMessage(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return "Cannot load:\n"+gson.toJson(jsonElement)+"\nBecause:\n"+ exception.getMessage();
    }

    /**
     * Gets json element.
     *
     * @return the json element source
     */
    public JsonElement getJsonElement() {
        return jsonElement;
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

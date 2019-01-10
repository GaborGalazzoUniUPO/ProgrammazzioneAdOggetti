package uniupo.gaborgalazzo.calendar.exception;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ReadException extends Exception{

    private final JsonElement jsonElement;
    private final Exception e;

    public ReadException(JsonElement jsonElement, Exception e) {
        this.jsonElement = jsonElement;
        this.e = e;
    }

    public String getMessage(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return "Cannot load:\n"+gson.toJson(jsonElement)+"\nBecause:\n"+e.getMessage();
    }

    public JsonElement getJsonElement() {
        return jsonElement;
    }

    public Exception getE() {
        return e;
    }
}

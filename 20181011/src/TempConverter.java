import jbook.util.Input;

public class TempConverter {


    public static void main(String[] args) {
        float tf = Input.readFloat("Inserisci la temperatura in gradi Fahrenheit \n> ");

        System.out.println(String.format("La temperatura in gradi Celsius e': %.2f",toCelsius(tf)));
    }

    private static float toCelsius(float tf) {
        return (5*(tf-32))/9;
    }
}

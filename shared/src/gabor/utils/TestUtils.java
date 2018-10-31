package gabor.utils;

import java.nio.charset.Charset;
import java.util.Random;

public class TestUtils {

    public static String randomString(int numChars){
        byte[] array = new byte[numChars]; // length is bounded by 7
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }

    public static String randomString(){
        return randomString(24);
    }

    public static int randomInteger() {
        return randomInteger(0,1024);
    }

    public static int randomInteger(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}

package gabor.utils;

import java.nio.charset.Charset;
import java.util.Random;

public class TestUtils {

    public static String randomString(int numChars){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(numChars);
        for (int i = 0; i < numChars; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
       return buffer.toString();
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

package utility;

import java.util.Random;

public class Utils {

    public static String uRLify(String s) {
        s = s.trim();
        s = s.replaceAll(" ", "%20");

        return s;
    }

    public static String generateRandomAlphaNumericString () {

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 11;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}

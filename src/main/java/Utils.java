import org.apache.commons.lang3.RandomStringUtils;

public class Utils {

    public static String generateRandomString(){
        int length = 12;
        boolean useLetters = false;
        boolean useNumbers = true;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    public static int columnCounter(int quantity) {
        int result = quantity % 2;

        return 0;
    }
}

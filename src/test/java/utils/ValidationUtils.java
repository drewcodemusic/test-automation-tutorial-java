package utils;

import static org.testng.Assert.*;

public class ValidationUtils {

    /**
     * Utility method to validate a value starts with an expected prefix
     * @param value The value to validate
     * @param expectedPrefix The expected prefix
     */
    public static void validateValuePrefix(String value, String expectedPrefix) {
        assertTrue(value.startsWith(expectedPrefix), value + " does not start with " + expectedPrefix);
    }
}

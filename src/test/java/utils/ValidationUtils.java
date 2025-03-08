package utils;

import static org.testng.Assert.*;
import io.restassured.response.Response;

public class ValidationUtils {

    /**
     * Utility method to validate a value starts with an expected prefix
     * @param value The value to validate
     * @param expectedPrefix The expected prefix
     */
    public static void validateValuePrefix(String value, String expectedPrefix) {
        assertTrue(value.startsWith(expectedPrefix), value + " does not start with " + expectedPrefix);
    }

    public static void validateIdenticalValues(Response response1, Response response2, String jsonPath1, String jsonPath2) {
        String value1 = response1.jsonPath().getString(jsonPath1);
        String value2 = response2.jsonPath().getString(jsonPath2);
        assertEquals(value1, value2, "Values should be identical");
    }

    public static void validateNonIdenticalValues(Response response1, Response response2, String jsonPath1, String jsonPath2) {
        String value1 = response1.jsonPath().getString(jsonPath1);
        String value2 = response2.jsonPath().getString(jsonPath2);
        assertNotEquals(value1, value2, "Values should not be identical");
    }
}

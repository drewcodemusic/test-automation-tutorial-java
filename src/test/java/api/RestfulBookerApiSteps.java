package api;

import dto.TokenDto;
import dto.BookingDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import utils.ValidationUtils;

import static org.testng.Assert.*;

public class RestfulBookerApiSteps {

    private static final String BASE_URL = "https://restful-booker.herokuapp.com";
    private TokenDto tokenDto;
    private BookingDto bookingDto;
    private Response response;
    private Response originalResponse;

    public void createToken(String username, String password) {
        JSONObject authPayload = new JSONObject();
        authPayload.put("username", username);
        authPayload.put("password", password);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(authPayload.toString())
                .post(BASE_URL + "/auth");

//        assertEquals(response.getStatusCode(), 200, "Authentication failed");
        tokenDto = new TokenDto(response.jsonPath().getString("token"));
        //System.out.println(tokenDto.getToken());
    }

    public void createBooking(String firstname, String lastname, int totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds) {
        JSONObject bookingPayload = new JSONObject();
        bookingPayload.put("firstname", firstname);
        bookingPayload.put("lastname", lastname);
        bookingPayload.put("totalprice", totalprice);
        bookingPayload.put("depositpaid", depositpaid);

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", checkin);
        bookingDates.put("checkout", checkout);
        bookingPayload.put("bookingdates", bookingDates);

        bookingPayload.put("additionalneeds", additionalneeds);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(bookingPayload.toString())
                .post(BASE_URL + "/booking");

        bookingDto = new BookingDto(response.jsonPath().getInt("bookingid"));
        // System.out.println("check in date: " + response.jsonPath().getString("booking.bookingdates.checkin"));
        originalResponse = response;
    }

    public void updateBooking(String firstname, String lastname, int totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds) {
        JSONObject updatePayload = new JSONObject();
        updatePayload.put("firstname", firstname);
        updatePayload.put("lastname", lastname);
        updatePayload.put("totalprice", totalprice);
        updatePayload.put("depositpaid", depositpaid);

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", checkin);
        bookingDates.put("checkout", checkout);
        updatePayload.put("bookingdates", bookingDates);

        updatePayload.put("additionalneeds", additionalneeds);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("Cookie", "token=" + tokenDto.getToken()) // Accessing token from DTO
                .body(updatePayload.toString())
                .put(BASE_URL + "/booking/" + bookingDto.getBookingId()); // Accessing bookingId from DTO;
    }

    public void validateResponseStatus() {
        assertEquals(response.getStatusCode(), 200, "Status code error!");
        assertNotNull(response.getBody(), "Response body should not be null");
        System.out.println(response.getBody().asString());
    }

    public String getResponseBody() {
        return response.getBody().asString();
    }

    public TokenDto getTokenDto() {
        return tokenDto;
    }

    public BookingDto getBookingDto() {
        return bookingDto;
    }

    public void validateJsonPathValueIsUpdated(String jsonpath1, String jsonpath2) {
//        System.out.println(response.jsonPath().getString("$"));
//        System.out.println(originalResponse.jsonPath().getString("$"));
        ValidationUtils.validateNonIdenticalValues(response, originalResponse, jsonpath1, jsonpath2);
    }

    public void validateJsonPathValueIsSame(String jsonpath1, String jsonpath2) {
        ValidationUtils.validateIdenticalValues(response, originalResponse, jsonpath1, jsonpath2);
    }
}
package stepDefinitions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.json.JSONArray;
import static org.testng.Assert.*;
//import java.util.List;
//import java.util.Map;

public class ApiSteps_0221 {
    private static final String BASE_URL = "https://asia.creativecdn.com";
    private static final String ENDPOINT = "/tags/v2";
    private Response response;

    public void setupApiRequest() {
        JSONObject payload = createPayload();
        response = sendRequest(payload);
    }

    public void validateResponse() {
        assertResponseStatus();
//        validateResponseContent();    // For L3
    }

    private JSONObject createPayload() {
        JSONObject payload = new JSONObject();
        payload.put("v", "v0.1.9");
        payload.put("sr", "");
        payload.put("su", "https://24h.pchome.com.tw/?utm_source=google&utm_medium=cpc&utm_campaign=awo_mkt_conversion_allproduct_all_all_gg_sem_mix_brand&gad_source=1&gclid=CjwKCAiA5eC9BhAuEiwA3CKwQl6vlQ1_ajb5ZKne4yZ0WRfW1kKFXtN45AiES2Hpddka7GrD42ROChoCqoMQAvD_BwE");
        payload.put("th", "V8DOerCn5JrKlPuRawQ8");

        JSONArray tags = new JSONArray();
        tags.put(new JSONObject().put("eventType", "home"));
        tags.put(new JSONObject()
                .put("eventType", "uid")
                .put("id", "unknown")
                .put("expiryDate", "2026-02-21T09:11:58.490Z"));
        tags.put(new JSONObject()
                .put("eventType", "lid")
                .put("id", "NDW4ckIhPSIDSPYdKeok")
                .put("expiryDate", "2026-02-21T09:11:58.491Z"));
        payload.put("tags", tags);

        return payload;
    }
    // ctrl+shift+f
    private Response sendRequest(JSONObject payload) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .header("accept", "*/*")
                .header("accept-language", "zh-TW,zh;q=0.9,en-US;q=0.8,en;q=0.7")
                .header("origin", "https://24h.pchome.com.tw")
                .header("priority", "u=1, i")
                .header("referer", "https://24h.pchome.com.tw/?utm_source=google&utm_medium=cpc&utm_campaign=awo_mkt_conversion_allproduct_all_all_gg_sem_mix_brand&gad_source=1&gclid=CjwKCAiA5eC9BhAuEiwA3CKwQl6vlQ1_ajb5ZKne4yZ0WRfW1kKFXtN45AiES2Hpddka7GrD42ROChoCqoMQAvD_BwE")
                .header("sec-ch-ua", "\"Not(A:Brand\";v=\"99\", \"Google Chrome\";v=\"133\", \"Chromium\";v=\"133\"")
                .header("sec-ch-ua-mobile", "?0")
                .header("sec-ch-ua-platform", "\"Windows\"")
                .header("sec-fetch-dest", "empty")
                .header("sec-fetch-mode", "cors")
                .header("sec-fetch-site", "cross-site")
                .header("sec-fetch-storage-access", "active")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36")
                .cookie("g", "UdeqFUkydCyeLPbqaMf1_1737563732596")
                .cookie("c", "UdeqFUkydCyeLPbqaMf1_V8DOerCn5JrKlPuRawQ8_1740129113865")
                .cookie("ts", "1740129113")
                .cookie("ar_debug", "1")
                .cookie("receive-cookie-deprecation", "1")
                .body(payload.toString())
                .post(BASE_URL + ENDPOINT);
    }

    private void assertResponseStatus() {
        assertEquals(response.getStatusCode(), 200, "Status code error!");
        assertNotNull(response.getBody(), "Response body should not be null");
        System.out.println(response.getBody().asString());
//        System.out.println(response.getBody().prettyPrint());
    }

    /*
    private void validateResponseContent() {
        List<Map<String, String>> responseArray = response.jsonPath().getList("$");
        assertNotNull(responseArray, "Response array should not be null");
        assertFalse(responseArray.isEmpty(), "Response array should not be empty");
        assertEquals(responseArray.size(), 3, "Response should contain exactly 3 items");

        // Validate first item (DoubleClick pixel)
        Map<String, String> firstItem = responseArray.get(0);
        assertTrue(firstItem.get("url").contains("doubleclick.net/pixel"),
                "First URL should be DoubleClick pixel");
        assertEquals(firstItem.get("type"), "IMG", "First item should be of type IMG");

        // Validate second and third items (iframes)
        for (int i = 1; i < 3; i++) {
            Map<String, String> item = responseArray.get(i);
            assertTrue(item.get("url").contains("asia.creativecdn.com"),
                    "URL " + (i+1) + " should be from creativecdn.com");
            assertEquals(item.get("type"), "IFRAME",
                    "Item " + (i+1) + " should be of type IFRAME");
        }
    }*/
}
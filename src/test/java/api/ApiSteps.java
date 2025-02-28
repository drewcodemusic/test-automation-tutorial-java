package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.json.JSONArray;
import static org.testng.Assert.*;  // the reason for static: call utilities with ease
import utils.ApiEndpoints;

public class ApiSteps {
    private static final String BASE_URL = "https://asia.creativecdn.com";
    private static final String COLUMN_URL = "https://ecapi-cdn.pchome.com.tw/fsapi/cms/contents";
    private static final String ENDPOINT = "/tags/v2?type=json";
    private Response response;
//    private static final String BASE_URL = ApiEndpoints.BASE_URL.getUrl();
//    private static final String COLUMN_URL = ApiEndpoints.COLUMN_URL.getUrl();

    public void setupApiRequest() {
        JSONObject payload = createPayload();
        response = sendRequest(payload);
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

    public void validateResponseStatus() {
        assertEquals(response.getStatusCode(), 200, "Status code error!");
        assertNotNull(response.getBody(), "Response body should not be null");
        System.out.println(response.getBody().asString());
    }

    /**
     * Get the response body as string
     * @return Response body as string
     */
    public String getResponseBody() {
        return response.getBody().asString();
    }

    public void sendColumnPageRequest(String pageId) {
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .queryParam("pageType", "sign")
                .queryParam("pageId", pageId)
                .header("accept", "application/json, text/plain, */*")
                .header("accept-language", "en-US,en;q=0.9")
                .header("sec-ch-ua", "\"Google Chrome\";v=\"119\", \"Chromium\";v=\"119\", \"Not?A_Brand\";v=\"24\"")
                .header("sec-ch-ua-mobile", "?0")
                .header("sec-ch-ua-platform", "\"Windows\"")
                .header("sec-fetch-dest", "empty")
                .header("sec-fetch-mode", "cors")
                .header("sec-fetch-site", "same-site")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36")
                .get(COLUMN_URL);
    }
}
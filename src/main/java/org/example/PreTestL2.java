package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson; // Make sure to add Gson dependency

public class PreTestL2 {

    public static void main(String[] args) throws IOException, InterruptedException {

        String apiUrl = "https://ecapi-cdn.pchome.com.tw/fsapi/cms/contents?pageType=sign&pageId=h24/nb";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("accept", "application/json, text/plain, */*")
                .header("accept-language", "zh-TW,zh;q=0.9,en-US;q=0.8,en;q=0.7")
                .header("origin", "https://24h.pchome.com.tw")
                .header("priority", "u=1, i")
                .header("referer", "https://24h.pchome.com.tw/")
                .header("sec-ch-ua", "\"Not(A:Brand\";v=\"99\", \"Google Chrome\";v=\"133\", \"Chromium\";v=\"133\"")
                .header("sec-ch-ua-mobile", "?0")
                .header("sec-ch-ua-platform", "\"Windows\"")
                .header("sec-fetch-dest", "empty")
                .header("sec-fetch-mode", "cors")
                .header("sec-fetch-site", "same-site")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String responseBody = response.body();

            // Use Gson to parse the JSON
            Gson gson = new Gson();
            PChomeResponse pchomeResponse = gson.fromJson(responseBody, PChomeResponse.class);

            // Access the data
            if (pchomeResponse.AD1 != null && pchomeResponse.AD1.length > 0) {
                AD1 ad1 = pchomeResponse.AD1[0]; // Get the first AD1 object
                System.out.println("Ad Title: " + ad1.title);
                System.out.println("Ad ID: " + ad1.id);
                // ... access other fields as needed
                for(Creatives creative : ad1.creatives){
                    System.out.println("Creative URL: " + creative.url);
                    System.out.println("Creative Image: " + creative.imageSrc);
                }

            } else {
                System.out.println("No AD1 data found.");
            }



        } else {
            System.out.println("Request failed: " + response.statusCode());
            System.out.println("Response Body: " + response.body()); // Print the error response for debugging
        }
    }


    // Define classes to match the JSON structure (using Gson)
    private static class PChomeResponse {
        AD1[] AD1;
    }

    private static class AD1 {
        String id;
        String title;
        String type;
        Duration duration;
        String updatedAt;
        String createdAt;
        Campaign campaign;
        Creatives[] creatives;
        DisplayStrategy displayStrategy;

    }

    private static class Duration {
        boolean isPermanent;
        String startTime;
        String endTime;
    }

    private static class Campaign {
        String id;
        String title;
    }

    private static class Creatives {
        String id;
        String type;
        String imageSrc;
        String imageAlt;
        String imageFileName;
        String url;
    }

    private static class DisplayStrategy {
        String type;
        String value;
    }


}

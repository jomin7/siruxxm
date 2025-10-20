package com.siriusxm.example.cart;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PriceFetcher {

    private static final String BASE_URL = "https://raw.githubusercontent.com/mattjanks16/shopping-cart-test-data/main/";
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public PriceFetcher() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public BigDecimal fetchPrice(String productName) throws IOException, InterruptedException {
        String url = BASE_URL + productName.toLowerCase() + ".json";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            JsonNode jsonNode = objectMapper.readTree(response.body());
            return new BigDecimal(jsonNode.get("price").asText());
        } else {
            throw new IOException("Failed to fetch price for product: " + productName + ". Status code: " + response.statusCode());
        }
    }
}

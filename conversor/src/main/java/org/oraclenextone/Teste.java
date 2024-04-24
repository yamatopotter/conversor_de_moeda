package org.oraclenextone;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.oraclenextone.model.Currency;
import org.oraclenextone.model.CurrencyData;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Teste {
    public static void main(String[] args) {
        String apiKey = "10a5d1f31240f78d93470fd2";
        Map<String, Currency> currencyList = new HashMap<>();

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();

        HttpClient httpClient = HttpClient.newHttpClient();
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/codes";

        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build();

        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String json = httpResponse.body();
            System.out.println(json);
            CurrencyData currencyData = gson.fromJson(json, CurrencyData.class);
            List<String[]> supportedCode = currencyData.getSupportedCodes();

            for (String[] code : supportedCode) {
                currencyList.put(code[0], new Currency(code[0], code[1]));
            }

            Currency c1 = currencyList.get("USD");

            System.out.println(c1.getName());
        } catch (Exception e) {
            System.out.println("Houve um erro: " + e.getMessage());
        }
    }
}

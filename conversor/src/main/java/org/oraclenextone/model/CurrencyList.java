package org.oraclenextone.model;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyList {
    private Map<String, Currency> currencyList = new HashMap<>();

    public CurrencyList(String apiKey) {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();

        HttpClient httpClient = HttpClient.newHttpClient();
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/codes";

        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build();

        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String json = httpResponse.body();
            CurrencyData currencyData = gson.fromJson(json, CurrencyData.class);
            List<String[]> supportedCode = currencyData.getSupportedCodes();

            for (String[] code : supportedCode) {
                this.currencyList.put(code[0], new Currency(code[1], code[0]));
            }
        } catch (Exception e) {
            System.out.println("Houve um erro: " + e.getMessage());
        }
    }

    public Currency getCurrency(String code){
        return this.currencyList.get(code);
    }

    public Map<String, Currency> getCurrencyList() {
        return currencyList;
    }
}


package org.oraclenextone;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.oraclenextone.model.Currency;

import org.oraclenextone.model.CurrencyList;
import org.oraclenextone.model.ExchangeApiResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    static String apiKey = "10a5d1f31240f78d93470fd2";
    static CurrencyList currencyList = new CurrencyList(apiKey);

    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        Integer opcao = 0;
        Boolean loopMenu = true;

//        while(loopMenu){
//            showMenu();
//            System.out.println("Digite sua opção");
//            opcao = leitura.nextInt();
//            switch(opcao){
//                case 1:
//                    converterMoeda()
//                    break;
//                case 2:
//                    break;
//                case 3:
//                    break;
//                case 4:
//                    break;
//                case 5:
//                    break;
//                case 6:
//                    break;
//                case 7:
//                    break;
//                case 8:
//                    break;
//                case 9:
//                    break;
//                default:
//                    break;
//            }
//        }
    }

    private static Double converterMoeda(Currency currency1, Currency currency2){
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        HttpClient httpClient = HttpClient.newHttpClient();
        String url = "https://v6.exchangerate-api.com/v6/"
                +apiKey
                +"/pair/"
                +currency1.getCode()+"/"
                +currency2.getCode();

        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build();

        try{
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String json = httpResponse.body();
            ExchangeApiResponse exchangeResponse = gson.fromJson(json, ExchangeApiResponse.class);
        }
        catch(Exception e){
            System.out.println("Houve um erro: "+e.getMessage());
        }

        return 0.0;
    }

    private static void showMenu() {
        System.out.println("*******************************************************");
        System.out.println("*********         Conversor de Moedas         *********");
        System.out.println("");
        System.out.println("1) Dólar (USD) >> Peso Argentino (ARS)");
        System.out.println("2) Peso Argentino (ARS) >> Dólar (USD)");
        System.out.println("3) Dólar (USD) >> Real Brasileiro (BRL)");
        System.out.println("4) Real Brasileiro (BRL) >> Dólar (USD)");
        System.out.println("5) Dólar (USD) >> Peso Colombiano (COP)");
        System.out.println("6) Peso Colombiano (COP) >> Dólar (USD)");
        System.out.println("7) Converter para todas as moedas");
        System.out.println("8) Ver histórico da moeda");
        System.out.println("9) Sair");
        System.out.println("*******************************************************");
    }

    private static void getCurrencyData(){

    }
}
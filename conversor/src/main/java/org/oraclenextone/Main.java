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
        int opcao = 0;
        double valor = 0.0;
        Boolean loopMenu = true;

        while (loopMenu) {
            showMenu();
            System.out.println("Digite sua opção");
            opcao = leitura.nextInt();
            switch (opcao) {
                case 1:
                    System.out.println("Digite o valor a ser convertido");
                    valor = leitura.nextDouble();
                    converterMoeda(currencyList.getCurrency("USD"), currencyList.getCurrency("ARS"), valor);
                    break;
                case 2:
                    System.out.println("Digite o valor a ser convertido");
                    valor = leitura.nextDouble();
                    converterMoeda(currencyList.getCurrency("ARS"), currencyList.getCurrency("USD"), valor);
                    break;
                case 3:
                    System.out.println("Digite o valor a ser convertido");
                    valor = leitura.nextDouble();
                    converterMoeda(currencyList.getCurrency("USD"), currencyList.getCurrency("BRL"), valor);
                    break;
                case 4:
                    System.out.println("Digite o valor a ser convertido");
                    valor = leitura.nextDouble();
                    converterMoeda(currencyList.getCurrency("BRL"), currencyList.getCurrency("USD"), valor);
                    break;
                case 5:
                    System.out.println("Digite o valor a ser convertido");
                    valor = leitura.nextDouble();
                    converterMoeda(currencyList.getCurrency("USD"), currencyList.getCurrency("COP"), valor);
                    break;
                case 6:
                    System.out.println("Digite o valor a ser convertido");
                    valor = leitura.nextDouble();
                    converterMoeda(currencyList.getCurrency("COP"), currencyList.getCurrency("USD"), valor);
                    break;
                case 7:
                    System.out.println("Digite a moeda que deseja converter:");
                    String moeda1 = leitura.next().toUpperCase();
                    System.out.println("Digite o valor que deseja converter:");
                    valor = leitura.nextDouble();
                    System.out.println("Digite a moeda para qual deseja converter:");
                    String moeda2 = leitura.next().toUpperCase();

                    converterMoeda(currencyList.getCurrency(moeda1), currencyList.getCurrency(moeda2), valor);
                    break;
                case 8:
                    break;
                case 9:
                    break;
                default:
                    break;
            }
        }
    }

    private static void converterMoeda(Currency currency1, Currency currency2, Double valor) {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();

        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            String url = "https://v6.exchangerate-api.com/v6/"
                    + apiKey
                    + "/pair/"
                    + currency1.getCode() + "/"
                    + currency2.getCode() + "/"
                    + valor;

            HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String json = httpResponse.body();
            ExchangeApiResponse exchangeResponse = gson.fromJson(json, ExchangeApiResponse.class);

            System.out.println("*******************************************************");
            System.out.println("*********          Conversão da Moeda         *********");
            System.out.println("Moeda de base: " + exchangeResponse.base_code());
            System.out.println("Moeda convertida: " + exchangeResponse.target_code());
            System.out.println("Valor a converter: " + valor + " " + exchangeResponse.base_code());
            System.out.println("Valor convertido: " + exchangeResponse.conversion_result() + " " + exchangeResponse.target_code());
            System.out.println("Taxa de conversão: " + exchangeResponse.conversion_rate() + " " + exchangeResponse.target_code());
        }
        catch(NullPointerException e){
            System.out.println("[ERRO]: Uma ou as duas moedas fornecidas são inválidas, tente novamente.");
        }
        catch (Exception e) {
            System.out.println("Houve um erro: " + e.getMessage());
        }
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
        System.out.println("7) Converter para outra moeda");
        System.out.println("8) Ver histórico da moeda");
        System.out.println("9) Sair");
        System.out.println("*******************************************************");
    }
}
package org.oraclenextone.model;

import java.util.Map;

public record HistoryExchangeApiResponse (
        int month, int day, int year, int base_code, Map<String, Double> conversion_rates
){
}

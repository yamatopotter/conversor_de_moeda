package org.oraclenextone.model;

public record ExchangeApiResponse(String base_code, String target_code, Double conversion_rate, Double conversion_result) {

}

package org.oraclenextone.model;

public record ExchangeApiResponse(String locale, String twoLetterCode, String currencyName, String currencyNameShort,
                                  String displaySymbol, String flagUrl) {

}

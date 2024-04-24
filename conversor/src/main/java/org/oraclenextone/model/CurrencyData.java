package org.oraclenextone.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CurrencyData {
    @SerializedName("supported_codes")
    List<String[]> supportedCodes;

    public CurrencyData(List<String[]> supportedCodes) {
        this.supportedCodes = supportedCodes;
    }

    public List<String[]> getSupportedCodes() {
        return supportedCodes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(String[] item: supportedCodes){
            sb.append("Supported Codes:\n");
            for (String[] code : supportedCodes) {
                sb.append(item[0]).append(": ").append(item[1]).append("\n");
            }
        }

        return sb.toString();
    }
}

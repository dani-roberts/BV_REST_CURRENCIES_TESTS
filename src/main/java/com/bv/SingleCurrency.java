package com.bv;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleCurrency {

    Map<String, SingleCurrencyExchangeRate> singleCurrencyExchangeRate = new LinkedHashMap<>();

    @JsonAnySetter
    void setSingleCurrencyExchangeRate(String key, SingleCurrencyExchangeRate value) {
        singleCurrencyExchangeRate.put(key, value);
    }

    public Map<String, SingleCurrencyExchangeRate> getSingleCurrencyExchangeRate() {
        return singleCurrencyExchangeRate;
    }

    public void setSingleCurrencyExchangeRate(Map<String, SingleCurrencyExchangeRate> singleCurrencyExchangeRate) {
        this.singleCurrencyExchangeRate = singleCurrencyExchangeRate;
    }
}
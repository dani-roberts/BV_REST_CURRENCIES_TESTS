package com.bv;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.LinkedHashMap;
import java.util.Map;

public class SingleCurrencyExchangeRate {

    Map<String, Float> exchangeRate = new LinkedHashMap<>();

    @JsonAnySetter
    void setExchangeRate(String key, Float value) {
        exchangeRate.put(key, value);
    }

    public Map<String, Float> getExchangeRate() {
        return exchangeRate;
    }

    public void setCurrencyAbrv(Map<String, Float> exchangeRate) {
        this.exchangeRate = exchangeRate;
    }


}
package com.bv;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.LinkedHashMap;
import java.util.Map;

public class CurrencyAbbreviation {

        Map<String, String> currencyAbrv = new LinkedHashMap<>();

        @JsonAnySetter
        void setCur(String key, String value) {
            currencyAbrv.put(key, value);
        }

        public Map<String, String> getCurrencyAbrv() {
            return currencyAbrv;
        }

        public void setCurrencyAbrv(Map<String, String> cur) {
            this.currencyAbrv = cur;
        }
}

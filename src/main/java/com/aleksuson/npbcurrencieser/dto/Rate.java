
package com.aleksuson.npbcurrencieser.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Rate {

    private String currency;
    private String code;
    private BigDecimal mid;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getMid() {
        return mid;
    }

    public void setMid(BigDecimal mid) {
        this.mid = mid;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

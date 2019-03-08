package com.aleksuson.npbcurrencieser.domain;


public enum CurrencyCode {

    AUD("dolar australijski"),
    BAM("wymienialna marka (Bośnia i Hercegowina)"),
    BGN("lew (Bułgaria)"),
    CHF("frank szwajcarski"),
    BRL("real (Brazylia)"),
    CAD("dolar kanadyjski"),
    CLP("peso chilijskie"),
    CNY("yuan renminbi (Chiny)"),
    EUR("euro"),
    GBP("funt szterling"),
    CZK("korona czeska"),
    DKK("korona duńska"),
    HKD("dolar Hongkongu"),
    HRK("kuna (Chorwacja)"),
    HUF("forint (Węgry)"),
    IDR("rupia indonezyjska"),
    ILS("nowy izraelski szekel"),
    INR("rupia indyjska"),
    ISK("korona islandzka"),
    JPY("jen (Japonia)"),
    KRW("won południowokoreański"),
    MXN("peso meksykańskie"),
    MYR("ringgit (Malezja)"),
    NOK("korona norweska"),
    NZD("dolar nowozelandzki"),
    PHP("peso filipińskie"),
    RON("lej rumuński"),
    RUB("rubel rosyjski"),
    SEK("korona szwedzka"),
    SGD("dolar singapurski"),
    SHP("Ascension Island"),
    THB("Tajlandia"),
    TRY("lira turecka"),
    UAH("hrywna (Ukraina)"),
    USD("dolar amerykański"),
    XDR("SDR (MFW)"),
    ZAR("rand (Republika Południowej Afryki)");

    private String currencyName;

    private CurrencyCode(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyType() {
        return currencyName;
    }

}

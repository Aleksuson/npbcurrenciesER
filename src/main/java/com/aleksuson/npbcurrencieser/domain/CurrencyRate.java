package com.aleksuson.npbcurrencieser.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CurrencyRate {

    private String name;
    private String code;
    private LocalDate localDate;

}

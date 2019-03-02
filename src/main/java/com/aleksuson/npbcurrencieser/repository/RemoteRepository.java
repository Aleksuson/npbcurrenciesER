package com.aleksuson.npbcurrencieser.repository;

import com.aleksuson.npbcurrencieser.domain.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class RemoteRepository {

    private RestTemplate restTemplate;

    public RemoteRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> getTableFromDateToDate(LocalDate fromDate, LocalDate toDate, String TableType) {
        String startDate = formatDateToYYYYMMdd(fromDate);
        String endDate = formatDateToYYYYMMdd(toDate);
        ResponseEntity<List<User>> response = restTemplate.exchange(
                "http://api.nbp.pl/api/exchangerates/tables/"+TableType+"/"+startDate+"/"+endDate+"/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>(){});
        return response.getBody();
    }

    private static String formatDateToYYYYMMdd(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        return date.format(formatter);
    }

}

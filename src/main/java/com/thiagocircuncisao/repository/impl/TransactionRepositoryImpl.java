package com.thiagocircuncisao.repository.impl;

import com.thiagocircuncisao.presentation.*;
import com.thiagocircuncisao.repository.TransactionsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Properties;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TransactionRepositoryImpl implements TransactionsRepository {
    @Value("${transaction-service-url}")
    private final String serviceUrl;

    private HttpEntity<Object> getHeaders(Object request) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json;charset=utf-8");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(request, headers);
        return entity;
    }

    @Override
    public PurchaseResponse createTransaction(PurchaseRequest purchaseRequest) {
        RestTemplate restTemplate = new RestTemplate();

        String url = serviceUrl + "/create-purchase";
        return restTemplate.exchange(url, HttpMethod.POST, getHeaders(purchaseRequest), PurchaseResponse.class).getBody();
    }

    @Override
    public List<RetrievePurchaseResponse> getTransactionById(RetrievePurchaseRequest retrievePurchaseRequest) {
        RestTemplate restTemplate = new RestTemplate();

        String url = serviceUrl + "/retrieve-purchase/";
        return restTemplate.exchange(url, HttpMethod.POST, getHeaders(retrievePurchaseRequest), List.class).getBody();
    }

    @Override
    public List<CurrencyResponse> retrieveCurrencies() {
        RestTemplate restTemplate = new RestTemplate();

        String url = serviceUrl + "/retrieve-currencies";
        return restTemplate.exchange(url, HttpMethod.GET, getHeaders(null), List.class).getBody();
    }
}

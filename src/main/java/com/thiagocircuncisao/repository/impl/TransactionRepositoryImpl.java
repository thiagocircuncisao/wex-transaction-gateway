package com.thiagocircuncisao.repository.impl;

import com.thiagocircuncisao.presentation.*;
import com.thiagocircuncisao.repository.TransactionsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Properties;

@Repository
@Slf4j
public class TransactionRepositoryImpl implements TransactionsRepository {
    @Value("${transaction-service.url}")
    private String serviceUrl;

    private final RestTemplate restTemplate;

    public TransactionRepositoryImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private HttpEntity<Object> getHeaders(Object request) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json;charset=utf-8");
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(request, headers);
    }

    @Override
    public PurchaseResponse createTransaction(PurchaseRequest purchaseRequest) {
        String url = serviceUrl + "/create-purchase";
        ResponseEntity<PurchaseResponse> response = restTemplate.exchange(url, HttpMethod.POST,
                getHeaders(purchaseRequest), PurchaseResponse.class);
        return response.getBody();
    }

    @Override
    public List<RetrievePurchaseResponse> getTransactionById(RetrievePurchaseRequest retrievePurchaseRequest) {
        String url = serviceUrl + "/retrieve-purchase";

        ResponseEntity<List<RetrievePurchaseResponse>> response = restTemplate.exchange(url, HttpMethod.POST,
                getHeaders(retrievePurchaseRequest), new ParameterizedTypeReference<>() {});

        return response.getBody();
    }

    @Override
    public List<CurrencyResponse> retrieveCurrencies() {
        String url = serviceUrl + "/retrieve-currencies";

        ResponseEntity<List<CurrencyResponse>> response = restTemplate.exchange(url, HttpMethod.GET,
                getHeaders(null), new ParameterizedTypeReference<>() {});

        return response.getBody();
    }
}

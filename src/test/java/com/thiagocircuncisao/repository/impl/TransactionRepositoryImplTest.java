package com.thiagocircuncisao.repository.impl;

import com.thiagocircuncisao.presentation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class TransactionRepositoryImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TransactionRepositoryImpl transactionRepository;

    private static final String mockUrl = "http://mock-url";

    @BeforeEach
    void setUp() {
        openMocks(this);
        ReflectionTestUtils.setField(transactionRepository, "serviceUrl", mockUrl);
    }

    @Test
    void shouldCreateTransaction() {
        PurchaseRequest purchaseRequest = new PurchaseRequest();
        PurchaseResponse expectedResponse = new PurchaseResponse();
        when(restTemplate.exchange(eq(mockUrl + "/create-purchase"), eq(HttpMethod.POST),
                any(HttpEntity.class), eq(PurchaseResponse.class)))
                .thenReturn(ResponseEntity.ok(expectedResponse));

        PurchaseResponse response = transactionRepository.createTransaction(purchaseRequest);

        assertEquals(expectedResponse, response);
    }

    @Test
    void shouldGetTransactionById() {
        RetrievePurchaseRequest retrievePurchaseRequest = new RetrievePurchaseRequest();
        List<RetrievePurchaseResponse> expectedResponse = List.of(new RetrievePurchaseResponse());
        when(restTemplate.exchange(eq(mockUrl + "/retrieve-purchase"), eq(HttpMethod.POST),
                any(HttpEntity.class), any(ParameterizedTypeReference.class)))
                .thenReturn(ResponseEntity.ok(expectedResponse));

        List<RetrievePurchaseResponse> response = transactionRepository.getTransactionById(retrievePurchaseRequest);

        assertEquals(expectedResponse, response);
    }

    @Test
    void shouldRetrieveCurrencies() {
        List<CurrencyResponse> expectedResponse = List.of(new CurrencyResponse());
        when(restTemplate.exchange(eq(mockUrl + "/retrieve-currencies"), eq(HttpMethod.GET),
                any(HttpEntity.class), any(ParameterizedTypeReference.class)))
                .thenReturn(ResponseEntity.ok(expectedResponse));

        List<CurrencyResponse> response = transactionRepository.retrieveCurrencies();

        assertEquals(expectedResponse, response);
    }
}

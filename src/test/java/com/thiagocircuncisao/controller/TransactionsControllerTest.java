package com.thiagocircuncisao.controller;

import com.thiagocircuncisao.presentation.*;
import com.thiagocircuncisao.service.TransactionsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionsController.class)
public class TransactionsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionsService transactionsService;

    @Test
    void shouldCreatePurchase() throws Exception {
        PurchaseResponse purchaseResponse = new PurchaseResponse();
        purchaseResponse.setDescription("description test");
        when(transactionsService.createTransaction(any(PurchaseRequest.class))).thenReturn(purchaseResponse);

        mockMvc.perform(post("/transaction/create-purchase")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"description\": \"description test\",\n" +
                                "  \"transactionDate\": \"2024-05-12\",\n" +
                                "  \"amount\": 235\n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("description test"));
    }

    @Test
    void shouldRetrievePurchase() throws Exception {
        RetrievePurchaseResponse retrievePurchaseResponse = new RetrievePurchaseResponse();
        retrievePurchaseResponse.setExchangeRate(234.0);
        when(transactionsService.getTransactionById(any(RetrievePurchaseRequest.class)))
                .thenReturn(Collections.singletonList(retrievePurchaseResponse));

        mockMvc.perform(post("/transaction/retrieve-purchase")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"id\": \"1\",\n" +
                                "  \"country\": \"Argentina\",\n" +
                                "  \"currency\": \"Peso\"\n" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].exchangeRate").exists());
    }

    @Test
    void shouldRetrieveCurrencies() throws Exception {
        CurrencyResponse currencyResponse = new CurrencyResponse();
        currencyResponse.setCurrency("Peso");
        when(transactionsService.retrieveCurrencies()).thenReturn(Collections.singletonList(currencyResponse));

        mockMvc.perform(get("/transaction/retrieve-currencies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].currency").exists());
    }
}

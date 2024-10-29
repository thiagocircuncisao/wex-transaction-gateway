package com.thiagocircuncisao.service;

import com.thiagocircuncisao.presentation.*;
import com.thiagocircuncisao.repository.TransactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionsService {
    private final TransactionsRepository transactionsRepository;

    public PurchaseResponse createTransaction(PurchaseRequest purchaseRequest) {
        return transactionsRepository.createTransaction(purchaseRequest);
    }

    public List<RetrievePurchaseResponse> getTransactionById(RetrievePurchaseRequest retrievePurchaseRequest) {
        return transactionsRepository.getTransactionById(retrievePurchaseRequest);
    }

    public List<CurrencyResponse> retrieveCurrencies() {
        return transactionsRepository.retrieveCurrencies();
    }
}

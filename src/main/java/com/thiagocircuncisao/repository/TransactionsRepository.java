package com.thiagocircuncisao.repository;

import com.thiagocircuncisao.presentation.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository {
    PurchaseResponse createTransaction(PurchaseRequest purchaseRequest);
    List<RetrievePurchaseResponse> getTransactionById(RetrievePurchaseRequest retrievePurchaseRequest);
    List<CurrencyResponse> retrieveCurrencies();
}

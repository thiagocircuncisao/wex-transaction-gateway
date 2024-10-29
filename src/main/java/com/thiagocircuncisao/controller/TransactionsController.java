package com.thiagocircuncisao.controller;

import com.thiagocircuncisao.presentation.*;
import com.thiagocircuncisao.service.TransactionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/transaction")
public class TransactionsController {
    @Autowired
    private TransactionsService transactionsService;

    @RequestMapping(value = "/create-purchase", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<PurchaseResponse> purchase(@RequestBody PurchaseRequest purchaseRequest) {
        return ResponseEntity.ok(transactionsService.createTransaction(purchaseRequest));
    }

    @RequestMapping(value = "/retrieve-purchase", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<List<RetrievePurchaseResponse>> retrievePurchase(@RequestBody RetrievePurchaseRequest retrievePurchaseRequest) {
        return ResponseEntity.ok(transactionsService.getTransactionById(retrievePurchaseRequest));
    }

    @RequestMapping(value = "/retrieve-currencies", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<CurrencyResponse>> retrieveCurrencies() {
        return ResponseEntity.ok(transactionsService.retrieveCurrencies());
    }
}

package com.thiagocircuncisao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseTransaction {
    private Long id;
    private String description;
    private String transactionDate;
    private Double amount;
}

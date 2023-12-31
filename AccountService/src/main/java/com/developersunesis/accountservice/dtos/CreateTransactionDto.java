/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.accountservice.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class CreateTransactionDto {
    private String type;
    private String currency;
    private String accountNo;
    private String customerId;
    private BigDecimal amount;
    private String accountType;
}

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
    private String accountNo;
    private BigDecimal amount;
}

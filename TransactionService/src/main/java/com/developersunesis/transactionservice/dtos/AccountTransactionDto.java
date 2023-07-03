/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.transactionservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class AccountTransactionDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    @NotBlank
    private String type;
    @NotBlank
    private String accountNo;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String customerId;
    @DecimalMin("0")
    private BigDecimal amount;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updatedAt;
}

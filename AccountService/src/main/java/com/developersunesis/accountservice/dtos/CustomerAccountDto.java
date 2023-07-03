/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.accountservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;

import java.math.BigDecimal;

@Getter
@Setter
public class CustomerAccountDto {
    @UUID
    @NotBlank
    private String customerId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String firstName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String lastName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String accountNo;
    @NotBlank
    @Size(min = 3, max = 3)
    private String currency = "NGN";
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal balance;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal startingBalance;
    @DecimalMin("0")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private BigDecimal initialCredit = BigDecimal.ZERO;
}

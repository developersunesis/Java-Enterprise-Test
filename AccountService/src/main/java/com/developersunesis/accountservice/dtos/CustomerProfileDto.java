/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.accountservice.dtos;

import com.developersunesis.accountservice.services.entities.CustomerProfile;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerProfileDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    @NotBlank
    @Size(min = 2, max = 20)
    private String firstName;
    @NotBlank
    @Size(min = 2, max = 20)
    private String lastName;
    @NotBlank
    @Size(max = 500)
    private String address;
    @NotBlank
    @Size(min = 10, max = 10)
    private String bvn;

    public static CustomerProfileDto toDto(CustomerProfile customerProfile) {
        CustomerProfileDto customerProfileDto = new CustomerProfileDto();
        BeanUtils.copyProperties(customerProfile, customerProfileDto);
        return customerProfileDto;
    }
}

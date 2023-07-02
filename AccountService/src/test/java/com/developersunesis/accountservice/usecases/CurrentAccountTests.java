/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.accountservice.usecases;

import com.developersunesis.accountservice.dtos.AccountTransactionDto;
import com.developersunesis.accountservice.services.AccountTransactionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedHashMap;
import java.util.Optional;

import static com.developersunesis.accountservice.utils.GsonUtils.gson;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CurrentAccountTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AccountTransactionService accountTransactionService;

    @Test
    @DisplayName("Given customer's id and initialCredit is zero, " +
            "When customer already exists, Then create customer current account")
    public void shouldReturnNewCustomerCurrentAccount() throws Exception {
        LinkedHashMap<String, String> request = new LinkedHashMap<>();
        request.put("customerId", "b6d75818-2fbd-46f4-81cd-5c783a95a9b9");
        request.put("initialCredit", "0");

        this.mockMvc.perform(post("/api/currentAccounts").contentType(MediaType.APPLICATION_JSON)
                        .content(gson().toJson(request))).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.customerId").exists())
                .andExpect(jsonPath("$.data.customerId")
                        .value("b6d75818-2fbd-46f4-81cd-5c783a95a9b9"))
                .andExpect(jsonPath("$.data.accountNo").exists())
                .andExpect(jsonPath("$.data.firstName").value("Prince"))
                .andExpect(jsonPath("$.data.lastName").value("Adii"))
                .andExpect(jsonPath("$.data.balance").exists())
                .andExpect(jsonPath("$.data.balance").value("0"))
                .andExpect(jsonPath("$.data.startingBalance").exists())
                .andExpect(jsonPath("$.data.startingBalance").value("0"));
    }

    @Test
    @DisplayName("Given customer's id and initialCredit is greater than zero, " +
            "When customer already exists, Then create customer current account")
    public void shouldReturnNewCustomerCurrentAccountWithBalanceGreaterThanZero() throws Exception {
        LinkedHashMap<String, String> request = new LinkedHashMap<>();
        request.put("customerId", "ecda1a9a-eebe-4615-9aa8-db2c8e07d67f");
        request.put("initialCredit", "1000");

        // mock response supposed to be received from transaction service
        when(accountTransactionService.create(any())).thenReturn(Optional.of(new AccountTransactionDto()));

        this.mockMvc.perform(post("/api/currentAccounts").contentType(MediaType.APPLICATION_JSON)
                        .content(gson().toJson(request))).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.customerId").exists())
                .andExpect(jsonPath("$.data.customerId")
                        .value("ecda1a9a-eebe-4615-9aa8-db2c8e07d67f"))
                .andExpect(jsonPath("$.data.accountNo").exists())
                .andExpect(jsonPath("$.data.firstName").value("John"))
                .andExpect(jsonPath("$.data.lastName").value("Tella"))
                .andExpect(jsonPath("$.data.balance").exists())
                .andExpect(jsonPath("$.data.balance").value("1000"))
                .andExpect(jsonPath("$.data.startingBalance").exists())
                .andExpect(jsonPath("$.data.startingBalance").value("1000"));
    }

    @Test
    @DisplayName("Given customer's id and initialCredit is greater than zero, " +
            "When customer already exists and transaction service is unavailable, Then return error")
    public void shouldReturnFailureBadGatewayTransactionServiceNotAvailable() throws Exception {
        LinkedHashMap<String, String> request = new LinkedHashMap<>();
        request.put("customerId", "ecda1a9a-eebe-4615-9aa8-db2c8e07d67f");
        request.put("initialCredit", "1000");

        // mock response supposed to be received from transaction service
        when(accountTransactionService.create(any())).thenReturn(Optional.empty());

        this.mockMvc.perform(post("/api/currentAccounts").contentType(MediaType.APPLICATION_JSON)
                        .content(gson().toJson(request))).andDo(print())
                .andExpect(status().isBadGateway()).andExpect(jsonPath("$.message").exists());
    }

    @Test
    @DisplayName("Given customer's account number, " +
            "When customer and current account already exists, Then return customer current account information")
    public void shouldReturnCustomerCurrentAccountInfo() throws Exception {
        this.mockMvc.perform(get("/api/currentAccounts/0294839484").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.customerId").exists())
                .andExpect(jsonPath("$.data.customerId")
                        .value("03841a2e-c080-43b7-bb19-13769810ca57"))
                .andExpect(jsonPath("$.data.accountNo").exists())
                .andExpect(jsonPath("$.data.balance").exists())
                .andExpect(jsonPath("$.data.firstName").value("Zedd"))
                .andExpect(jsonPath("$.data.balance").value("0.0"))
                .andExpect(jsonPath("$.data.startingBalance").exists())
                .andExpect(jsonPath("$.data.startingBalance").value("0.0"));
    }
}

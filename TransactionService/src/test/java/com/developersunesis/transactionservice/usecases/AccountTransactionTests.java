/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.transactionservice.usecases;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedHashMap;

import static com.developersunesis.transactionservice.utils.GsonUtils.gson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountTransactionTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Given there's a mandate to create an account transaction, " +
            "When a CREDIT mandate is issued, Then create transaction record for specified account")
    public void shouldReturnNewCustomerProfile() throws Exception {
        LinkedHashMap<String, String> request = new LinkedHashMap<>();
        request.put("accountNo", "1234567890");
        request.put("accountType", "CURRENT");
        request.put("currency", "NGN");
        request.put("type", "CREDIT");
        request.put("amount", "1000");

        this.mockMvc.perform(post("/api/currentAccounts/03841a2e-c080-43b7-bb19-13769810ca57/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson().toJson(request))).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.id").exists())
                .andExpect(jsonPath("$.data.currency").exists())
                .andExpect(jsonPath("$.data.amount").value("1000"))
                .andExpect(jsonPath("$.data.createdAt").exists())
                .andExpect(jsonPath("$.data.updatedAt").exists())
                .andExpect(jsonPath("$.data.accountNo").value("1234567890"))
                .andExpect(jsonPath("$.data.customerId").value("03841a2e-c080-43b7-bb19-13769810ca57"))
                .andExpect(jsonPath("$.data.type").value("CREDIT"));
    }

    @Test
    @DisplayName("Given customer id and account number provided, " +
            "When account transactions is requested, Then return all transactions related to specified account")
    public void shouldReturnListOfTransactionsBelongingToAccountSpecified() throws Exception {
        this.mockMvc.perform(get("/api/currentAccounts/03841a2e-c080-43b7-bb19-13769810ca57/transactions" +
                        "?accountNo=1234567890&page=0&size=50").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.page").exists())
                .andExpect(jsonPath("$.size").exists())
                .andExpect(jsonPath("$.data[0].id").exists())
                .andExpect(jsonPath("$.data[0].amount").exists())
                .andExpect(jsonPath("$.data[0].currency").exists())
                .andExpect(jsonPath("$.data[0].accountNo").value("1234567890"))
                .andExpect(jsonPath("$.data[0].customerId").value("03841a2e-c080-43b7-bb19-13769810ca57"))
                .andExpect(jsonPath("$.data[0].type").exists());
    }
}

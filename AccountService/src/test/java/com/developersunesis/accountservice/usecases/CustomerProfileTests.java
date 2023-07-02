/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.accountservice.usecases;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedHashMap;

import static com.developersunesis.accountservice.utils.GsonUtils.gson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerProfileTests {

    @Autowired
    private MockMvc mockMvc;

    public static LinkedHashMap<String, String> profile(String bvn){
        LinkedHashMap<String, String> request = new LinkedHashMap<>();
        request.put("bvn", bvn);
        request.put("lastName", "Sunesis");
        request.put("firstName", "Emmanuel");
        request.put("address", "450 Axis Estate, Malebu, London");
        return request;
    }

    @Test
    @DisplayName("Given customer's information is provided, " +
            "When customer doesn't already exists, Then create customer profile")
    public void shouldReturnNewCustomerProfile() throws Exception {
        String bvn = "0000000000";
        this.mockMvc.perform(post("/api/profile").contentType(MediaType.APPLICATION_JSON)
                        .content(gson().toJson(profile(bvn)))).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.id").exists())
                .andExpect(jsonPath("$.data.bvn").value(bvn));
    }

    @Test
    @DisplayName("Given customer's information is provided, " +
            "When customer already exists, Then return an error")
    public void shouldReturnWithFailureExistingCustomerProfile() throws Exception {
        String bvn = "9737494739";
        this.mockMvc.perform(post("/api/profile").contentType(MediaType.APPLICATION_JSON)
                        .content(gson().toJson(profile(bvn)))).andDo(print())
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.message")
                        .value("Profile with similar bvn already exists"));
    }

    @Test
    @DisplayName("Given customer's id is provided, " +
            "When customer already exists, Then return customer profile")
    public void shouldReturnExistingCustomerProfile() throws Exception {
        this.mockMvc.perform(get("/api/profile/ab1f8512-b620-4e37-b7bb-c226b0c23671")
                        .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.id").value("ab1f8512-b620-4e37-b7bb-c226b0c23671"))
                .andExpect(jsonPath("$.data.address").value("5 NowayHome Street"))
                .andExpect(jsonPath("$.data.bvn").value("9737494739"));;
    }

    @Test
    @DisplayName("Given customer's id is provided, " +
            "When customer doesn't exist, Then return an error")
    public void shouldReturnWithFailureCustomerProfileNotFound() throws Exception {
        this.mockMvc.perform(get("/api/profile/97698d8c-09c3-432a-a772-656230e11b93")
                        .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isNotFound()).andExpect(jsonPath("$.message")
                        .value("Profile [97698d8c-09c3-432a-a772-656230e11b93] does not exist"));
    }
}

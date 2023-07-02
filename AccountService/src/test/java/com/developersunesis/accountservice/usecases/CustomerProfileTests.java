/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.accountservice.usecases;

import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.developersunesis.accountservice.utils.CustomerProfiles.profile;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerProfileTests {

    @Autowired
    private MockMvc mockMvc;
    private final Gson gson = new Gson();

    @Test
    @DisplayName("Given customer's information is provided, " +
            "When customer doesn't already exists, Then create customer profile")
    public void shouldReturnNewCustomerProfile() throws Exception {
        String bvn = "0000000000";
        this.mockMvc.perform(post("/api/profile").contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(profile(bvn)))).andDo(print())
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
        String bvn = "1111111111";
        // create an account with specified bvn above
        this.mockMvc.perform(post("/api/profile").contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(profile(bvn))));

        // attempt to create account with existing bvn again
        this.mockMvc.perform(post("/api/profile").contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(profile(bvn)))).andDo(print())
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.message")
                        .value("Profile with similar bvn already exists"));
    }
}

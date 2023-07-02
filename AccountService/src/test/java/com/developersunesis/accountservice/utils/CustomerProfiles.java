/*
 * Copyright (c) developersunesis 2023.
 */

package com.developersunesis.accountservice.utils;

import java.util.LinkedHashMap;

public class CustomerProfiles {

    public static LinkedHashMap<String, String> profile(String bvn){
        LinkedHashMap<String, String> request = new LinkedHashMap<>();
        request.put("bvn", bvn);
        request.put("lastName", "Sunesis");
        request.put("firstName", "Emmanuel");
        request.put("address", "450 Axis Estate, Malebu, London");
        return request;
    }
}

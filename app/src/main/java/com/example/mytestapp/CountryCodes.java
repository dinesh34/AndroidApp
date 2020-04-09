package com.example.mytestapp;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CountryCodes {

    private String country;
    private String code;

    public String getCountry() {
        return country;
    }

    public String getCode() {
        return code;
    }

    public CountryCodes(String country, String code) {
        this.country = country;
        this.code = code;
    }
}


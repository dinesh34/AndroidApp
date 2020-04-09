package com.example.mytestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CountryCodeView extends AppCompatActivity {

    private static final String url = "https://restcountries.eu/rest/v2/all";
    private CountryCodeAdapter adapter;
    private List<CountryCodes> Code_List;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_code_view);

        Code_List = new ArrayList<>();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setTitle("Country Codes");

        getJSONData();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CountryCodeAdapter(getApplicationContext(),Code_List);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

    }

    public void getJSONData(){

            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String code_x = "",country_x="";
                        for(int i=0;i<response.length();i++){
                            try {
                                JSONObject jObject = response.getJSONObject(i);
                                     country_x = jObject.getString("name");
                                JSONArray jArray = jObject.getJSONArray("callingCodes");
                                    for(int j=0;j<jArray.length();j++) {
                                        code_x = jArray.getString(j);
                                    }
                                CountryCodes data= new CountryCodes(country_x,code_x);
                                Code_List.add(data);
                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
}

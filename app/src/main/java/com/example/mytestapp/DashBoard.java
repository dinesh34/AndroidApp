package com.example.mytestapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class DashBoard extends AppCompatActivity {

    private ListView dash_list;
    private String[] dash_components_text ={"Country Codes","Dummy","Dummy","Dummy","Dummy","Dummy","Dummy"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setTitle("Dashboard");

        //Getting list view
        dash_list = findViewById(R.id.dashboard_list);
        //Getting the adapter object and set our intended values to given parameters
        MyAdapter adapter = new MyAdapter(this, dash_components_text);
        //Now need to set the adaptor
        dash_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    //Create an OnClick listener for list components
        dash_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    getCountryCodes();
//                    Toast.makeText(DashBoard.this, "Pressed "+ dash_components_text[0], Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void getCountryCodes() {

        Intent countryCodes = new Intent(this, CountryCodeView.class);
        startActivity(countryCodes);
    }

    class MyAdapter extends ArrayAdapter<String>{

        Context context;
        String dash_text[];

        MyAdapter(Context c, String texts[]){
            super(c, R.layout.dashboard_components, R.id.dashboard_text,texts);
            this.context = c;
            this.dash_text = texts;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            //Put a layout inflater
            LayoutInflater inflater  = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View dash_component = inflater.inflate(R.layout.dashboard_components,parent, false);
            TextView dashboard_text = dash_component.findViewById(R.id.dashboard_text);
            //Set the resources on view components
            dashboard_text.setText(dash_text[position]);

            //Return the view from adapter
            return dash_component;

        }
    }
}

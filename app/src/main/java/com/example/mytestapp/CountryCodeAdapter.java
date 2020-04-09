package com.example.mytestapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CountryCodeAdapter extends RecyclerView.Adapter<CountryCodeAdapter.MyViewHolder> {

    private List<CountryCodes> code_list;
    private LayoutInflater inflater;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView txtCountry, txtCode;

        public MyViewHolder( View itemView) {
            super(itemView);
            txtCountry = (TextView) itemView.findViewById(R.id.country);
            txtCode = (TextView) itemView.findViewById(R.id.country_code);
        }
    }

    public CountryCodeAdapter(Context context, List<CountryCodes> codeList){
        inflater = LayoutInflater.from(context);
        this.code_list = codeList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View list_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.code_list_component,parent,false);
        View view = inflater.inflate(R.layout.code_list_component, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CountryCodes code = code_list.get(position);
        holder.txtCode.setText(code.getCode());
        holder.txtCountry.setText(code.getCountry());
    }

    @Override
    public int getItemCount() {
       return code_list.size();
    }

}

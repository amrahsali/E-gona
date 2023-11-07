package com.example.e_gona;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Model.CalendarModel;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {

     ArrayList<CalendarModel> calendar;
    Context context;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,des,days;
        public ViewHolder(@NonNull View itemview){
            super(itemview);
            name = itemview.findViewById(R.id.calendar_name_item);
            des = itemview.findViewById(R.id.calendar_des_item);
            days = itemview.findViewById(R.id.calendar_dth_item);
        }

    }

    public CalendarAdapter(Context context, ArrayList<CalendarModel> calendar) {
        this.context = context;
        this.calendar = calendar;
    }

    public CalendarAdapter(){

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.calendar_row_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CalendarModel model = calendar.get(position);
        holder.name.setText(model.CropName);
        holder.des.setText(model.Description);
        holder.days.setText(model.CompletionDate);
    }

    @Override
    public int getItemCount() {
       return calendar.size();
    }
}

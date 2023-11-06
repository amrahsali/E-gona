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

    private ArrayList<CalendarModel> calendar;
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

    public void setCalendar(ArrayList<CalendarModel> calendar){
        this.calendar = calendar;
    }
    public CalendarAdapter(Context context){
        this.context = context;
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

        holder.name.setText(calendar.get(position).CropName);
        holder.des.setText(calendar.get(position).Description);
        holder.days.setText(calendar.get(position).CompletionDate);
    }

    @Override
    public int getItemCount() {
       return calendar.size();
    }
}

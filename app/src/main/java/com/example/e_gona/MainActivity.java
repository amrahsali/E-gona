package com.example.e_gona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Model.CalendarModel;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    ImageView profilebtn;
    FloatingActionButton fab;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profilebtn = (ImageView) findViewById(R.id.profile_item);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        recyclerView = (RecyclerView) findViewById(R.id.recylerview);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        CalendarAdapter adapter = new CalendarAdapter(MainActivity.this);
        ArrayList<CalendarModel> calendar = new ArrayList<>();
        CalendarModel c = new CalendarModel();
        c.Name = "Beans";
        c.Description = "Planted this to harvest within a short period of time";
        c.DatePlanted = "02/05/2021";
        c.HarvestDate = "16 days left";
        c.Status = "Not harvested";
        CalendarModel c1 = new CalendarModel();
        c1.Name = "PawPaw";
        c1.Description = "Saw an apportunity to not only enjoy the fruit myself but also to sell it when costly.";
        c1.DatePlanted = "02/05/2021";
        c1.HarvestDate = "5 days left";
        c1.Status = "planted";
        calendar.add(c);
        calendar.add(c1);
        adapter.setCalendar(calendar);
        recyclerView.setAdapter(adapter);

        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(i);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,AddCalenderActivity.class);
                startActivity(i);
            }
        });
    }
}
package com.example.e_gona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import Model.CalendarModel;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    ImageView profilebtn;
    FloatingActionButton fab;
    FirebaseFirestore db;
    ArrayList<CalendarModel> calendar;
    SharedPreferences sharedpreferences;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences = getSharedPreferences("myPref", Context.MODE_PRIVATE);

        profilebtn = (ImageView) findViewById(R.id.profile_item);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        recyclerView = (RecyclerView) findViewById(R.id.recylerview);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        CalendarAdapter adapter = new CalendarAdapter(MainActivity.this);
        calendar = new ArrayList<>();

        db = FirebaseFirestore.getInstance();

        getData();
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
    public void getData(){
        String email = sharedpreferences.getString("email_key", null);
        db.collection("Calendar")
                .whereEqualTo("UserId",email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                calendar.add(documentSnapshot.toObject(CalendarModel.class));
                                documentSnapshot.get("CompetionDate");
                            }
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Error Loading Data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}
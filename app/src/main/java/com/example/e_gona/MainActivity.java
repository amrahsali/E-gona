package com.example.e_gona;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Objects;

import Model.CalendarModel;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    ImageView profilebtn;
    CalendarAdapter adapter;
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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.hasFixedSize();
        db = FirebaseFirestore.getInstance();

        calendar = new ArrayList<CalendarModel>();

        adapter = new CalendarAdapter(MainActivity.this,calendar);

        recyclerView.setAdapter(adapter);

        EventChangeListener();
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
    private void EventChangeListener() {
        String email = sharedpreferences.getString("email_key", "");
        db.collection("Calendar")
                .whereEqualTo("UserId", email)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        for(DocumentChange dc:value.getDocumentChanges()){
                            if(dc.getType()==DocumentChange.Type.ADDED){
                                //calendar.add(dc.getDocument().toObject(CalendarModel.class));
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

    }
}
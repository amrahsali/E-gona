package com.example.e_gona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AddCalenderActivity extends AppCompatActivity {

    DatePicker picker;
    Button setCalendar;
    EditText name,desc;
    RadioButton harvest, plant;
    FirebaseFirestore db;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_calender);

        picker = (DatePicker) findViewById(R.id.Calendar_datepicker);
        setCalendar  = (Button) findViewById(R.id.set_calendar);
        name = (EditText) findViewById(R.id.editName);
        desc = (EditText) findViewById(R.id.editDesc);
        harvest = (RadioButton) findViewById(R.id.radioHarvesting);
        plant = (RadioButton) findViewById(R.id.radioPlanting);
        db = FirebaseFirestore.getInstance();

        sharedpreferences = getSharedPreferences("email", Context.MODE_PRIVATE);
        setCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(name.getText())&&TextUtils.isEmpty(desc.getText())){
                    Toast.makeText(AddCalenderActivity.this, "data empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    String email = sharedpreferences.getString("email_key","");
                    Map<String, Object> calendar = new HashMap<>();
                    calendar.put("UserId", email);
                    calendar.put("CropName", name.getText().toString());
                    calendar.put("Description", desc.getText().toString());
                    calendar.put("CompletionDate",picker.getDayOfMonth()+"-"+picker.getMonth()+"-"+picker.getYear());
                    calendar.put("Category",harvest.getText().toString());
                    if(harvest.isActivated()){
                        calendar.put("Category",harvest.getText().toString());
                    }
                    else {
                        calendar.put("Category",plant.getText().toString());
                    }

                    db.collection("Calendar").document()
                            .set(calendar)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(AddCalenderActivity.this,"Calendar Added",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(AddCalenderActivity.this,"Please try again",Toast.LENGTH_SHORT).show();
                                }
                            });


                }



            }
        });

    }

}
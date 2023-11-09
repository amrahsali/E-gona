package com.example.e_gona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {


    Button button;
    private EditText userNameEdt, passwordEdt;
    private TextView  signup;
    private FirebaseAuth mAuth;
    private ProgressBar loadingPB;
    SharedPreferences UserEmail;

    Context context;
    Resources resources;
    String[] languages = { "select language", "english", "hausa" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Spinner spin = findViewById(R.id.spinnerLang);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item, languages);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
        FirebaseApp.initializeApp(this);






        // initializing all our variables.
        userNameEdt = findViewById(R.id.email_login);
        passwordEdt = findViewById(R.id.login_password);
        mAuth = FirebaseAuth.getInstance();
        loadingPB = findViewById(R.id.idPBLoading);
        button = findViewById(R.id.login_btn);
        signup = findViewById(R.id.toSignup);

        UserEmail = getSharedPreferences("email", Context.MODE_PRIVATE);


        userNameEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer music = MediaPlayer.create(Login.this, R.raw.imel);
                music.start();
            }
        });

        passwordEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer music = MediaPlayer.create(Login.this, R.raw.sahannu);
                music.start();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer music = MediaPlayer.create(Login.this, R.raw.kirkira);
                music.start();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // hiding our progress bar.
                loadingPB.setVisibility(View.VISIBLE);
                // getting data from our edit text on below line.
                String email = userNameEdt.getText().toString();
                String password = passwordEdt.getText().toString();
                // on below line validating the text input.
                if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
                    Toast.makeText(Login.this, "Please enter your credentials..", Toast.LENGTH_SHORT).show();
                    return;
                }
                // on below line we are calling a sign in method and passing email and password to it.
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // on below line we are checking if the task is success or not.
                        if (task.isSuccessful()) {
                            // on below line we are hiding our progress bar.
                            loadingPB.setVisibility(View.GONE);
                            SharedPreferences.Editor editor = UserEmail.edit();
                            editor.putString("email_key", email).commit();
                            Toast.makeText(Login.this, "Login Successful..", Toast.LENGTH_SHORT).show();
                            // on below line we are o"pening our mainactivity.
                            Intent i = new Intent(Login.this, MainActivity.class);
                            startActivity(i);
                            // requireActivity().finish();
                        } else {
                            // hiding our progress bar and displaying a toast message.
                            loadingPB.setVisibility(View.GONE);
                            Toast.makeText(Login.this, "Please enter valid user credentials..", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

        @Override
        public void onStart() {
            super.onStart();
            // in on start method checking if
            // the user is already sign in.
            FirebaseUser user = mAuth.getCurrentUser();
            if (user != null) {
                // if the user is not null then we are
                // opening a main activity on below line.
                Intent i = new Intent(Login.this, MainActivity.class);
                startActivity(i);
                //requireActivity().finish();
            }



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });




}

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if (languages[position].equals("hausa")){
            context = LocaleHelper.setLocale(Login.this, "ha");
            resources = context.getResources();
            Intent refresh = new Intent(context, Login.class);
            Toast.makeText(getApplicationContext(),languages[position] , Toast.LENGTH_SHORT).show();
            finish();
            startActivity(refresh);
        }else if(languages[position].equals("english")){
            //Toast.makeText( LoginActivity.this, "checkbox is unchecked", Toast.LENGTH_SHORT).show();
            context = LocaleHelper.setLocale(Login.this, "en");
            resources = context.getResources();
            Intent refresh = new Intent(context, Login.class);
            Toast.makeText(getApplicationContext(),languages[position] , Toast.LENGTH_SHORT).show();
            finish();
            startActivity(refresh);
        }else {
            Toast.makeText(getApplicationContext(),"languages" , Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(getApplicationContext(),"languages" , Toast.LENGTH_SHORT).show();


    }


}
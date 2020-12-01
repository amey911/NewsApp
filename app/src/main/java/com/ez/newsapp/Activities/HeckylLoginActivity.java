package com.ez.newsapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ez.newsapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HeckylLoginActivity extends AppCompatActivity {

    EditText etEmailPhone, etPassword;

    Button loginButton;

    TextView signUp;

    ProgressBar progressBar;


    DatabaseReference loginRef;



    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heckyl_login);

        etEmailPhone = findViewById(R.id.login_et_email);
        etPassword = findViewById(R.id.login_et_password);
        signUp = findViewById(R.id.login_signup_btn);
        loginButton = findViewById(R.id.login_btn);
        progressBar = findViewById(R.id.login_prog_bar);

        loginRef = FirebaseDatabase.getInstance().getReference();


        mAuth = FirebaseAuth.getInstance();



        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(HeckylLoginActivity.this, HeckylRegisterActivity.class);
                startActivity(intent);

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SignIn();


            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() !=null) {
            finish();
            startActivity(new Intent(this, HeckylHome.class));
        }

    }



    private void SignIn() {




                String email = etEmailPhone.getText().toString();
                String password = etPassword.getText().toString();




                if (email.isEmpty()) {

                    etEmailPhone.setError("Email is Required");
                    etEmailPhone.requestFocus();
                    return;

                }


                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etEmailPhone.setError("Please enter valid email");
                    etEmailPhone.requestFocus();
                }



                if (password.isEmpty()) {

                    etPassword.setError("Password is required");
                    etPassword.requestFocus();
                    return;

                }


                if (password.length()<6){
                    etPassword.setError("minimum length should be 6");
                    etPassword.requestFocus();
                    return;
                }

        progressBar.setVisibility(View.VISIBLE);


        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {




            @Override


            public void onComplete(@NonNull Task<AuthResult> task) {

                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Log.d("auth", "sign in email success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    // login method or intent

                    Intent homeIntent = new Intent(HeckylLoginActivity.this , HeckylHome.class);
                    homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(homeIntent);
                    finish();



                } else
                    {


                        Toast.makeText(HeckylLoginActivity.this, "Login Failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }



            }
        });


    }




}
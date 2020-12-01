package com.ez.newsapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ez.newsapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class HeckylRegisterActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;

    EditText registerName, registerEmail, registerPassword;
    Button registerButton;

    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heckyl_register);


        registerButton = findViewById(R.id.register_btn);
        registerName = findViewById(R.id.et_name_register);
        registerEmail = findViewById(R.id.et_email_register);
        registerPassword = findViewById(R.id.et_password_register);

        progressBar = findViewById(R.id.register_prog_bar);


        mAuth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CreateAcc();

            }
        });



    }


    private void CreateAcc() {

        String email = registerEmail.getText().toString();
        String password = registerPassword.getText().toString();
        String name = registerName.getText().toString();



        if (email.isEmpty()) {

            registerEmail.setError("Email is Required");
            registerEmail.requestFocus();
            return;

        }


        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            registerEmail.setError("Please enter valid email");
            registerEmail.requestFocus();
        }



        if (password.isEmpty()) {

            registerPassword.setError("Password is required");
            registerPassword.requestFocus();
            return;

        }


        if (password.length()<6){
            registerPassword.setError("minimum length should be 6");
            registerPassword.requestFocus();
            return;
        }




        progressBar.setVisibility(View.VISIBLE);


        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {

                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(HeckylRegisterActivity.this, "success", Toast.LENGTH_SHORT).show();


                    Intent homeIntent = new Intent(HeckylRegisterActivity.this , HeckylHome.class);
                    homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(homeIntent);
                    finish();
                    //login and go to next activity

                } else {

                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(HeckylRegisterActivity.this, "User already exist.", Toast.LENGTH_SHORT).show();
                    }  else
                    {
                        Toast.makeText(HeckylRegisterActivity.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }


                    Toast.makeText(HeckylRegisterActivity.this, "error", Toast.LENGTH_SHORT).show();
                    //fail method

                }
            }


        });


    }

}
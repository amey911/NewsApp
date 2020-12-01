package com.ez.newsapp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ez.newsapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class HeckylProfileActivity extends AppCompatActivity {


    private static final int CHOOSE_IMAGE =101 ;
    ImageView profileImage;
    EditText profileName;
    Button profileSaveBtn;
    TextView verificationText;

    Uri uriImage;


    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heckyl_profile);

        profileImage = findViewById(R.id.profile_image);
        profileName = findViewById(R.id.profile_edit_name);
        profileSaveBtn = findViewById(R.id.profile_btn_save);

        verificationText = findViewById(R.id.profile_verification);



        mAuth = FirebaseAuth.getInstance();


        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageChooser();
            }
        });


        profileSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInfo();
            }
        });

        loadUserInformation();
    }

    private void saveUserInfo() {


        String displayName = profileName.getText().toString();

        if (displayName.isEmpty()) {
            profileName.setError("Name required.");
            profileName.requestFocus();
            return;

        }

        FirebaseUser user  = mAuth.getCurrentUser();

        if (user != null) {
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setDisplayName(displayName)
                    .setPhotoUri(Uri.parse(String.valueOf(uriImage)))
                    .build();

            user.updateProfile(profile)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(HeckylProfileActivity.this, "profile upddated", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }





    }


    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() ==null) {
            finish();
            startActivity(new Intent(this, HeckylLoginActivity.class));
        }
    }

    private void loadUserInformation() {

        final FirebaseUser user = mAuth.getCurrentUser();



        if (user.getPhotoUrl() != null) {
            String photoUrl = user.getPhotoUrl().toString();

            Glide.with(this)
                    .load(photoUrl)
                    .into(profileImage);

        }


        if (user.getDisplayName() != null){
            String displayName = user.getDisplayName();
            profileName.setText(displayName);
        }

        if (user.isEmailVerified()) {
            verificationText.setText("Email is Verified");

        } else {
            verificationText.setText("Email is not Verified (click to verify)");

            verificationText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(HeckylProfileActivity.this, "Verification Email is sent to " +user.getEmail(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data!= null && data.getData() != null)
        {
          uriImage =   data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriImage);

                profileImage.setImageBitmap(bitmap);

                uploadImageTofirebaseStorage();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

    private void uploadImageTofirebaseStorage() {

        StorageReference profileImageRef = FirebaseStorage.getInstance().getReference("profilePics/"+System.currentTimeMillis() + ".jpg");

        if (uriImage != null) {
            profileImageRef.putFile(uriImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(HeckylProfileActivity.this, "success", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }


    }





    private void showImageChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Profile Image"), CHOOSE_IMAGE);

    }




}
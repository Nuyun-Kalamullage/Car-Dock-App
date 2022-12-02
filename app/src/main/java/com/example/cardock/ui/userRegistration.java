package com.example.cardock.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cardock.R;
import com.example.cardock.ui.login.LoginActivity;
import com.example.cardock.backend.user;

import org.jetbrains.annotations.Nullable;

public class userRegistration extends AppCompatActivity {
    private static final int IMAGE_PICK_CODE = 100;
    ImageView profile;
    private String ImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        getSupportActionBar().setTitle("User Registration");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        profile = findViewById(R.id.imageProfile);
        profile.setImageResource(R.drawable.defultprofile);
        EditText nametext = findViewById(R.id.nameedittext);
        EditText emailtext = findViewById(R.id.priceText);
        EditText usernametext = findViewById(R.id.millageedittext);
        EditText passwordtext = findViewById(R.id.editTextTextPassword);
        EditText nictext = findViewById(R.id.nicedittext);
        EditText contactnumbertext = findViewById(R.id.contactedittext);


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile.destroyDrawingCache();
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, IMAGE_PICK_CODE);
            }

        });

        Button submitButton = findViewById(R.id.registerbutton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = nametext.getText().toString();
                String email = emailtext.getText().toString();
                String username = usernametext.getText().toString();
                int password = passwordtext.getText().toString().hashCode();
                String nic = nictext.getText().toString();
                String contactNumber = contactnumbertext.getText().toString();

                if(user.RegisterNewUser(fullname,email,username,password,nic,contactNumber,ImageUrl)){
                    Toast.makeText(userRegistration.this, "Registration Successful, SignIn Now..", Toast.LENGTH_SHORT).show();
                    Intent lunch = new Intent(userRegistration.this,LoginActivity.class);
                    startActivity(lunch);
                }else Toast.makeText(userRegistration.this, "Fill the all elements Correctly, Try again..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            ImageUrl=data.getDataString();
            profile.setImageURI(Uri.parse(ImageUrl));
        }
    }
}
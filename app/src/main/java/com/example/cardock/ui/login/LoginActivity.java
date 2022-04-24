package com.example.cardock.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cardock.R;
import com.example.cardock.backend.user;
import com.example.cardock.databinding.ActivityLoginBinding;
import com.example.cardock.ui.landingPage;
import com.example.cardock.ui.userRegistration;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    EditText usernameEditText;
    EditText passwordEditText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //For coding purpose.
        user.registeredUserArray.put("nuyun",new user("Nuyun Pabasara","nuyunp@sltc.ac.lk","nuyun","nuyun".hashCode(),"991781757V","0775225911",""));
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        usernameEditText = binding.username;
        passwordEditText = binding.password;
        final Button loginButton = binding.login;
        final ProgressBar loadingProgressBar = binding.loading;
        final Button signupButton = binding.signupButton;
        signupButton.setEnabled(true);
        loginButton.setEnabled(true);
        ImageView logo = (ImageView) findViewById(R.id.imageView2);
        logo.setImageResource(R.drawable.logocardock);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginAuthorize();
            }
        });

        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loadingProgressBar.setVisibility(View.VISIBLE);
                    loginAuthorize();
                }
                return false;
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                Intent lunch = new Intent(LoginActivity.this, userRegistration.class);
                startActivity(lunch);

            }
        });
        }
    private void loginAuthorize () {
        String userName = usernameEditText.getText().toString();
        int response = user.authoriseLogin(userName, passwordEditText.getText().toString().hashCode());
        if ( response== 1) {
            Toast.makeText(LoginActivity.this, "Welcome " + user.registeredUserArray.get(userName).getName() + "", Toast.LENGTH_SHORT).show();
            Intent lunch = new Intent(LoginActivity.this, landingPage.class);
            lunch.putExtra("userName", userName);
            startActivity(lunch);
        } else if (response == 0) {
            Toast.makeText(LoginActivity.this, "Entered Password was Wrong Try again :(", Toast.LENGTH_SHORT).show();
            setContentView(binding.getRoot());
        } else {
            if (userName.isEmpty()){
                Toast.makeText(LoginActivity.this, "Entered username & password to Continue :)", Toast.LENGTH_SHORT).show();
            }else Toast.makeText(LoginActivity.this, "Entered username not found. Registered to SignIn :)", Toast.LENGTH_SHORT).show();
            setContentView(binding.getRoot());
        }
    }

}
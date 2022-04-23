package com.example.cardock;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.cardock.backend.user;
import com.example.cardock.databinding.ActivityLandingPageBinding;
import com.google.android.material.navigation.NavigationView;

public class landingPage extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityLandingPageBinding binding;
    public static String logins ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logins = getIntent().getStringExtra("userName");
        System.out.println(logins);

        binding = ActivityLandingPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarLandingPage.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_seller, R.id.nav_logout)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_landing_page);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }
    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.landing_page, menu);
        ImageView prologo = (ImageView) findViewById(R.id.imageView);
        TextView name = findViewById(R.id.nameofuser);
        TextView email = findViewById(R.id.emailofuser);

        if (!user.registeredUserArray.get(logins).getImageUri().isEmpty())
            prologo.setImageURI(Uri.parse(user.registeredUserArray.get(logins).getImageUri()));
        System.out.println(user.registeredUserArray.get(logins).getImageUri());
        name.setText(user.registeredUserArray.get(logins).getName());
        email.setText(user.registeredUserArray.get(logins).getEmail());

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_landing_page);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
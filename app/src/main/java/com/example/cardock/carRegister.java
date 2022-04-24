package com.example.cardock;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cardock.backend.car;

import org.jetbrains.annotations.Nullable;

public class carRegister extends AppCompatActivity {
    public static final int PERMISSION_CODE = 101;
    public static final int IMAGE_PICK_CODE = 100;
    protected static ImageView setImage;
    public static String logins;
    ImageView frontView;
    ImageView rearView;
    ImageView interiorView;
    ImageView sideView;
    String frontUri = "";
    String rearUri = "";
    String interiorUri = "";
    String sideUri = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logins = landingPage.logins;
        setContentView(R.layout.activity_car_register);
        getSupportActionBar().setTitle("Car Registration");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        imageViewHandling();

        Button submit = findViewById(R.id.buttonSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitButtonAction();
            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


    private void imageViewHandling() {

         frontView=findViewById(R.id.imageView6);
         rearView=findViewById(R.id.imageView5);
         interiorView=findViewById(R.id.imageView4);
         sideView=findViewById(R.id.imageView3);

        Button addImgFront = findViewById(R.id.button6);
        Button addImgRear = findViewById(R.id.button4);
        Button addImgInterior = findViewById(R.id.button3);
        Button addImgSide = findViewById(R.id.button5);
        getImage(frontView,addImgFront);
        getImage(rearView,addImgRear);
        getImage(interiorView,addImgInterior);
        getImage(sideView,addImgSide);

    }
    private void submitButtonAction(){
        EditText modelText = findViewById(R.id.editTextTextPersonName);
        EditText manufactureYearText = findViewById(R.id.nameedittext);
        RadioGroup fuelTypeButtonGroup = findViewById(R.id.fuelTypeButtonGroup);
        EditText priceText = findViewById(R.id.priceText);
        RadioGroup financeType = findViewById(R.id.FinaceRadioButtonGroup);
        EditText millageText = findViewById(R.id.millageedittext);
        EditText descriptionText = findViewById(R.id.editTextTextMultiLine);
        String model = modelText.getText().toString();
        String year = manufactureYearText.getText().toString();
        int fuelType = -1;
        switch (fuelTypeButtonGroup.getCheckedRadioButtonId()){
            case R.id.Petrol:
                fuelType=0;
                break;
            case R.id.diesel:
                fuelType=1;
                break;
            case R.id.Electric:
                fuelType=2;
                break;
            default:
                Toast.makeText(carRegister.this, "Select the Fuel Type ", Toast.LENGTH_SHORT).show();
        }
        String price = priceText.getText().toString();
        Boolean finance = null;
        switch (financeType.getCheckedRadioButtonId()){
            case R.id.no:
                finance=false;
                break;
            case R.id.yes:
                finance=true;
                break;
            default:
                Toast.makeText(carRegister.this, "Select the Finance Status", Toast.LENGTH_SHORT).show();
        }
        String millage = millageText.getText().toString();
        String description = descriptionText.getText().toString();
        if (car.registeredCarsArray.containsKey(logins) || finance == null || fuelType == -1 || model.isEmpty() || year.isEmpty() || price.isEmpty() || millage.isEmpty() || description.isEmpty() || frontUri.isEmpty() || rearUri.isEmpty() || interiorUri.isEmpty() || sideUri.isEmpty()){
            if (car.registeredCarsArray.containsKey(logins) )
                Toast.makeText(carRegister.this, "this User already Register a car, ", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(carRegister.this, "Fill the form first, ", Toast.LENGTH_SHORT).show();

        }
        else if (car.RegisterNewCar(logins,frontUri,rearUri,interiorUri,sideUri,model,year,millage,price,description,fuelType,finance)){
            Toast.makeText(carRegister.this, "Car Registration Successful, ", Toast.LENGTH_SHORT).show();
            car.registeredCarsArray.get(logins).setHasAd(true);
            Intent lunch = new Intent(           carRegister.this, landingPage.class);
            lunch.putExtra("userName",logins);
            startActivity(lunch);
        }
    }

    private void getImage(ImageView imageView, Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImage = imageView;
                pickImageGallary();
                askGallaryPermission();
            }

        });
    }
    protected void pickImageGallary(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == PERMISSION_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                pickImageGallary();
            }else{
                Toast.makeText(this, "File manage permission need to go ahead", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            setImage.setImageURI(data.getData());
            if (setImage == frontView){
                frontUri =data.getData().toString();
            }else if (setImage == rearView){
                rearUri = data.getData().toString();
            }else if (setImage == interiorView){
                interiorUri = data.getData().toString();
            }else if (setImage == sideView){
                sideUri = data.getData().toString();
            }
        }else
            System.out.println("uuuuu");
    }

    protected void askGallaryPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkSelfPermission( Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permissions,PERMISSION_CODE);
            }
            else{
                pickImageGallary();
            }
        }else{
            pickImageGallary();
        }
    }

}
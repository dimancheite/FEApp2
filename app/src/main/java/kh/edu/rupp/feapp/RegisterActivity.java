package kh.edu.rupp.feapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class RegisterActivity extends AppCompatActivity {

    private final int REQUEST_CODE_GENDER = 1;

    private TextView txtGender;
    private EditText etxtAddress;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        txtGender = findViewById(R.id.txt_gender);
        etxtAddress = findViewById(R.id.etxt_address);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_GENDER && resultCode == RESULT_OK){
            String gender = data.getStringExtra("gender");
            txtGender.setText(gender);
        }
    }

    public void onSelectOnMapClick(View view){
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    public void onSelectGenderClick(View view){
        Intent intent = new Intent(this, SelectGenderActivity.class);
        if(txtGender.getText().equals("Male")){
            intent.putExtra("gender", "Male");
        } else if(txtGender.getText().equals("Female")){
            intent.putExtra("gender", "Female");
        }
        startActivityForResult(intent, REQUEST_CODE_GENDER);
    }



}

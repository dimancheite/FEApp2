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
    private final int REQUEST_CODE_LOCATION_PERMISSION = 2;

    private TextView txtGender;
    private EditText etxtAddress;

    private FusedLocationProviderClient locationProviderClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        txtGender = findViewById(R.id.txt_gender);
        etxtAddress = findViewById(R.id.etxt_address);

        loadUserCurrentLocation();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_GENDER && resultCode == RESULT_OK){
            String gender = data.getStringExtra("gender");
            txtGender.setText(gender);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            loadUserCurrentLocation();
        }
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

    private void loadUserCurrentLocation(){
        locationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_LOCATION_PERMISSION);
            return;
        }
        Task<Location> locationTask = locationProviderClient.getLastLocation();
        locationTask.addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if(task.isSuccessful()){
                    Location location = task.getResult();
                    if(location != null){
                        String locationString = location.getLatitude() + ", " + location.getLongitude();
                        etxtAddress.setText(locationString);
                    } else {
                        Toast.makeText(RegisterActivity.this, "Current location not found.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Error while getting current location.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}

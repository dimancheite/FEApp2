package kh.edu.rupp.feapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/**
 * FEApp
 * Created by leapkh on 4/23/18.
 */
public class ContactFragment extends Fragment {

    private final int REQUEST_CODE_LOCATION = 1;

    private TextView txtLocation;

    private FusedLocationProviderClient locationProviderClient;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_contact, container, false);
        return fragmentView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtLocation = view.findViewById(R.id.txt_location);

        // Load last known location
        loadUserLocation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if (requestCode == REQUEST_CODE_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(), "User allowed.", Toast.LENGTH_LONG).show();
                loadUserLocation();
            } else {
                Toast.makeText(getActivity(), "User denied.", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void loadUserLocation() {
        locationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
            requestPermissions(permissions, REQUEST_CODE_LOCATION);
            return;
        }
        Task<Location> locationTask = locationProviderClient.getLastLocation();
        locationTask.addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getActivity(), "Get user last know location success.", Toast.LENGTH_LONG).show();
                    Location lastKnownLocation = task.getResult();
                    if(lastKnownLocation != null) {
                        String locationString = "Location: lat = " + lastKnownLocation.getLatitude() + ", lng = " + lastKnownLocation.getLongitude();
                        txtLocation.setText(locationString);
                    }else{
                        txtLocation.setText("No last known location found.");
                    }
                } else {
                    Toast.makeText(getActivity(), "Get user last know location fail.", Toast.LENGTH_LONG).show();
                    Log.d("fe", "Get user last know location fail: " + task.getException());
                }
            }
        });
    }

}

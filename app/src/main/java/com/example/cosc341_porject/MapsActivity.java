package com.example.cosc341_porject;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.cosc341_porject.databinding.ActivityMapsBinding;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//         Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        String address_str = intent.getStringExtra("address");
        String name = intent.getStringExtra("name");
//        LatLng location = getLocationFromAddress(this,address_str);

//        mMap.addMarker(new MarkerOptions()
//                .position(location)
//                .title("name"));
    }

        @Override
        public void onMapReady (GoogleMap googleMap){
            LatLng kelowna = new LatLng(49.940147, -119.396516);
            googleMap.addMarker(new MarkerOptions()
                    .position(kelowna)
                    .title("Kelowna"));
        }


        public LatLng getLocationFromAddress (Context context, String strAddress){

            Geocoder coder = new Geocoder(context);
            List<Address> address;
            LatLng p1 = null;

            try {
                address = coder.getFromLocationName(strAddress, 5);
                if (address == null) {
                    return null;
                }
                Address location = address.get(0);
                location.getLatitude();
                location.getLongitude();

                p1 = new LatLng(location.getLatitude(), location.getLongitude());

            } catch (Exception ex) {

                ex.printStackTrace();
            }

            return p1;
        }
}
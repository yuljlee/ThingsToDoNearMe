package com.nanodegree.yj.thingstodonearme.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nanodegree.yj.thingstodonearme.R;
import com.nanodegree.yj.thingstodonearme.model.Event;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);

        Toolbar toolbar = findViewById(R.id.map_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //mMap.setMinZoomPreference(15);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        Intent intent = getIntent();
        //double[] location = intent.getDoubleArrayExtra("event_location");

        // Add a marker at the place and move the camera
        //LatLng place = new LatLng(location[0], location[1]);
        //LatLng sydney = new LatLng(37.8114102, -122.2665892);

        Event event = intent.getParcelableExtra("event_location");

        LatLng place = new LatLng(event.getLatitude(), event.getLongitude());
        final String eventName = event.getName();

        googleMap.addMarker(new MarkerOptions().position(place).title(""));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place, 14));

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                marker.setTitle(eventName);
                marker.showInfoWindow();

                return false;
            }
        });
    }
}

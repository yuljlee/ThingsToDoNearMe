package com.nanodegree.yj.thingstodonearme.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.nanodegree.yj.thingstodonearme.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemSelected;
import butterknife.Unbinder;

public class SearchActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.autoCompleteTextView) AutoCompleteTextView mAutoTextView;
    @BindView(R.id.textView_city1) TextView mCity1;
    @BindView(R.id.textView_city2) TextView mCity2;
    @BindView(R.id.textView_city3) TextView mCity3;
    @BindView(R.id.textView_city4) TextView mCity4;

    @BindView(R.id.current_location_textView) TextView mCurrentLocation;

    private LocationManager locationManager;
    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);


        final String[] us_city = getResources().getStringArray(R.array.us_city);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, us_city);

        mAutoTextView.setAdapter(arrayAdapter);
        mAutoTextView.requestFocus();

        mAutoTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
//                Toast.makeText(
//                        parent.getContext(),
//                        "Selected Value: " + parent.getItemAtPosition(position),
//                        Toast.LENGTH_LONG).show();

                String cityName = parent.getItemAtPosition(position).toString();
                Intent intent = new Intent();
                intent.putExtra("city_name", cityName);
                setResult(RESULT_OK, intent);

                finish();
            }
        });

        mCurrentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
                } else {
                    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    try {
                        String city = hereLocation(location.getLatitude(), location.getLongitude());
                        mAutoTextView.setText(city);
                        setLocation(city);
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(SearchActivity.this, "Not Found!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void setLocation(String city) {
        Intent intent = new Intent();
        intent.putExtra("city_name", city);
        setResult(RESULT_OK, intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1000: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    try {
                        String city = hereLocation(location.getLatitude(), location.getLongitude());
                        mAutoTextView.setText(city);
                        setLocation(city);
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(SearchActivity.this, "Not Found!", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private String hereLocation(double lat, double lon) {
        String cityName = "";

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocation(lat, lon, 10);
            if (addresses.size() > 0) {
                for (Address adr: addresses) {
                    if (adr.getLocality() != null && adr.getLocality().length() > 0) {
                        cityName = adr.getLocality();
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cityName;
    }

    @OnClick({R.id.textView_city1, R.id.textView_city2, R.id.textView_city3, R.id.textView_city4})
    public void onCityClick(TextView view) {
        String cityName = view.getText().toString();
        Intent intent=new Intent();
        intent.putExtra("city_name", cityName);
        setResult(RESULT_OK, intent);

        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // show keyboard
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
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
    protected void onStop() {
        super.onStop();
        // hide keyboard
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

}

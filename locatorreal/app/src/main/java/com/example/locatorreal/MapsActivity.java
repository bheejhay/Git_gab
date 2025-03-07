package com.example.locatorreal;

import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlarmManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationmanager;

    // declearing the latitude and longitude
    double latitude;
    double longitude;

    // declearing the address details
    String str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationmanager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        if (locationmanager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {

            locationmanager.requestLocationUpdates(locationmanager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                    // checking for the network provider

                    latitude = location.getLatitude();
                    longitude = location.getLongitude();

                    //instatiate latlng
                    LatLng latlng2 = new LatLng(latitude, longitude);

                    // instatiate geocoder
                    Geocoder geocoder = new Geocoder(getApplicationContext());

                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        str = addressList.get(0).getLocality() + ", ";
                        str += addressList.get(0).getCountryName();
                        mMap.addMarker(new MarkerOptions().position(latlng2).title(str));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng2, 10.2f));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }


                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
        } else if (locationmanager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
//                    AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                    // checking for the network provider

                    latitude = location.getLatitude();
                    longitude = location.getLongitude();

                    //instatiate latlng2
                    LatLng latlng2 = new LatLng(latitude, longitude);

                    // instatiate geocoder
                    Geocoder geocoder = new Geocoder(getApplicationContext());

                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        str = addressList.get(0).getLocality() + ", ";
                        str += addressList.get(0).getCountryName();
                        mMap.addMarker(new MarkerOptions().position(latlng2).title(str));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng2, 15.2f));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }


                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });

        }

    }
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera. In this case,
         * we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to install
         * it inside the SupportMapFragment. This method will only be triggered once the user has
         * installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady (GoogleMap googleMap){
            mMap = googleMap;

            // marker is to route vatebra tech
//        LatLng vatebraTech = new LatLng(6.4279737, 3.4284398);
//        mMap.addMarker(new MarkerOptions().position(vatebraTech).title("Currently in VatebraTech"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vatebraTech, 15.2f));


        }

}

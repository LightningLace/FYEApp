package com.stringclevername.fyeapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;

    // Define method to build API client
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Build API Client
        buildGoogleApiClient();
        // Connect to API client
        mGoogleApiClient.connect();

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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Map will show user's location
        // Check to see whether location permission has been granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            // Enable My Location layer, to show current location
            mMap.setMyLocationEnabled(true);
        } else {
            // TODO: Ask user to enable permission

        }


//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
        LatLng hicks = new LatLng(42.289, -85.600); // Hicks Center
        LatLng center = new LatLng(42.289714, -85.601228);  // ~center of campus


        // Define default (main) camera position
        CameraPosition main = new CameraPosition.Builder()
                .target(center) // sets location coordinates to KCollege center
                .zoom(18)       // sets zoom level
                .bearing(270)    // sets orientation (West at top)
                .build();        // creates a CameraPosition from the builder

//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.addMarker(new MarkerOptions().position(hicks).title("Hicks Center"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(hicks));
        //mMap.moveCamera(CameraUpdateFactory.newCameraPosition(main));


    }

    @Override
    public void onConnected(Bundle bundle) {

        // LatLng objects
        LatLng newCenter;

        // Get current location
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            // Store current location
            newCenter = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            // Debug Toast
            Toast success = Toast.makeText(this, "Debug: Success", Toast.LENGTH_SHORT);
            success.show();
        } else {
            // Store K College center
            newCenter = new LatLng(42.289714, -85.601228);  // ~center of campus
            // Debug Toast
            Toast error = Toast.makeText(this, "Debug: mLastLocation is null", Toast.LENGTH_SHORT);
            error.show();
        }
        // Create CameraPosition to center on
        CameraPosition main = new CameraPosition.Builder()
                .target(newCenter)  // sets location coordinates to either current
                                    // location or college center
                .zoom(18)           // sets reasonable zoom level
                .bearing(270)       // orient so West is up
                .build();             // creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(main));
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}

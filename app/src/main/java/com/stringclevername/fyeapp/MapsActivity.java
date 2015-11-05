package com.stringclevername.fyeapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        AdapterView.OnItemSelectedListener {

    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;

    // List of place markers
    List<Marker> markerList = new ArrayList<Marker>(29);


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


        // Spinner!
        Spinner spinner = (Spinner) findViewById(R.id.placesSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.places_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // Respond to spinner click events:
        // Specify the interface implementation
        // so the spinner knows which listener to use
        spinner.setOnItemSelectedListener(this);
        // The actual implementation methods are found
        // just below this onCreate() method

        // Build API Client
        buildGoogleApiClient();
        // Connect to API client
        mGoogleApiClient.connect();
    }

    // Respond to Spinner on-item-selected-events
    // The following two methods implement
    // the OnItemSelectedListener class
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item
        // using parent.getItemAtPosition(pos)
        Toast.makeText(this, "Item: " + pos, Toast.LENGTH_SHORT).show();
        if (pos != 0 && pos != 1 && pos != 8 && pos != 21) {
            Marker thisMarker = markerList.get(pos);
            mMap.animateCamera(CameraUpdateFactory.newLatLng(thisMarker.getPosition()));
            thisMarker.showInfoWindow();

        }
    }
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
        Toast.makeText(this, "Nothing selected", Toast.LENGTH_SHORT).show();
    }
    // Specify the interface implementation:
    // This needs to be done so the spinner knows which
    // listener to take instructions from.
    // (this is done in the onCreate method above)


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


        // Create LatLng objects, to be used
        // in markers for campus buildings
        LatLng center = new LatLng(42.289714, -85.601228);  // ~center of campus
        LatLng hicksLoc = new LatLng(42.289, -85.600); // Hicks Center


        // Define default (main) camera position
        CameraPosition main = new CameraPosition.Builder()
                .target(center) // sets location coordinates to KCollege center
                .zoom(18)       // sets zoom level
                .bearing(270)    // sets orientation (West at top)
                .build();        // creates a CameraPosition from the builder


        // Custom info window adapter implementation
        // Needed to allow multiple lines in snippets
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                Context context = getApplicationContext(); //or getActivity(), YourActivity.this, etc.

                LinearLayout info = new LinearLayout(context);
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(context);
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(context);
                snippet.setTextColor(Color.GRAY);
                snippet.setText(marker.getSnippet());

                info.addView(title);
                if (marker.getSnippet() != null)
                    info.addView(snippet);

                return info;
            }
        });

        // Add markers created above to the map
        Marker dewing = mMap.addMarker(new MarkerOptions().position(new LatLng(42.290109, -85.601887)).title("Dewing Hall")
                .snippet("Center for Career and Professional Development" + "\n" + "Center For Civic Engagement" + "\n" + "Center for International Programs" + "\n" + "First Year Experience" + "\n" + "Records Office/Registrar"));
        Marker dow = mMap.addMarker(new MarkerOptions().position(new LatLng(42.291777, -85.600535)).title("Dow Science Center"));
        Marker humphrey = mMap.addMarker(new MarkerOptions().position(new LatLng(42.290653, -85.599573)).title("Humphrey House"));
        Marker light = mMap.addMarker(new MarkerOptions().position(new LatLng(42.290746, -85.600704)).title("Light Fine Arts Building")
                .snippet("- Dalton Theater\n- Dungeon Theater\n- Recital Hall"));
        Marker ou = mMap.addMarker(new MarkerOptions().position(new LatLng(42.290058, -85.600210)).title("Olds-Upton Science Hall"));
        Marker upjohn = mMap.addMarker(new MarkerOptions().position(new LatLng(42.290709, -85.601424)).title("Upjohn Library Commons")
                .snippet("- Audio/Visual/Production Studios\n- Center for New Media\n- Information Services\n- The Book Club (Coffee Shop)\n- Writing Center"));

        Marker hicks = mMap.addMarker(new MarkerOptions().position(new LatLng(42.289220, -85.600666)).title("Hicks Center")
                .snippet("- Bookstore\n- Health Services\n- Mail Center\n- Security Office\n- Student Development\n- Student Union Desk\n- Welles Hall\n    - Stone Room\n    - Student Dining Hall"));
        Marker anderson = mMap.addMarker(new MarkerOptions().position(new LatLng(42.290094, -85.598527)).title("Anderson Athletic Center"));
        Marker fields = mMap.addMarker(new MarkerOptions().position(new LatLng(42.285666, -85.608086)).title("Athletic Field Complex")
                .snippet("- Angell Football Field/Fieldhouse\n- MacKenzie Soccer Field\n- Softball Field\n- Woodworth Baseball Field"));
        Marker markin = mMap.addMarker(new MarkerOptions().position(new LatLng(42.290889, -85.596703)).title("Markin Racquet Center"));
        Marker nat = mMap.addMarker(new MarkerOptions().position(new LatLng(42.290624, -85.598195)).title("Natatorium"));
        Marker nelda = mMap.addMarker(new MarkerOptions().position(new LatLng(42.291298, -85.600107)).title("Nelda K. Balch Festival Playhouse"));
        Marker stetson = mMap.addMarker(new MarkerOptions().position(new LatLng(42.289610, -85.601371)).title("Stetson Chapel"));
        Marker stowe = mMap.addMarker(new MarkerOptions().position(new LatLng(42.291432, -85.599527)).title("Stowe Tennis Stadium"));
        Marker arcus = mMap.addMarker(new MarkerOptions().position(new LatLng(42.290086, -85.603747)).title("Arcus Center")
                .snippet("Arcus Center for Social Justice Leadership"));
        Marker mandelle = mMap.addMarker(new MarkerOptions().position(new LatLng(42.290064, -85.600952)).title("Mandelle Hall")
                .snippet("- Admission & Financial Aid\n- Advancement Office\n- Business Office\n- Olmstead Room\n- President/Provost Ofice"));
        Marker facman = mMap.addMarker(new MarkerOptions().position(new LatLng(42.289359, -85.599068)).title("Facilities Management"));
        Marker hodge = mMap.addMarker(new MarkerOptions().position(new LatLng(42.291279, -85.601383)).title("Hodge House")
                .snippet("President's Residence"));
        Marker crissey = mMap.addMarker(new MarkerOptions().position(new LatLng(42.291188, -85.597946)).title("Crissey Residence Hall"));
        Marker dewaters = mMap.addMarker(new MarkerOptions().position(new LatLng(42.289323, -85.602391)).title("DeWaters Residence Hall"));
        Marker harmon = mMap.addMarker(new MarkerOptions().position(new LatLng(42.290085, -85.599494)).title("Harmon Residence Hall"));
        Marker hoben = mMap.addMarker(new MarkerOptions().position(new LatLng(42.289591, -85.599556)).title("Hoben Residence Hall"));
        Marker llh = mMap.addMarker(new MarkerOptions().position(new LatLng(42.289089, -85.603684)).title("Living/Learning Houses"));
        Marker sev = mMap.addMarker(new MarkerOptions().position(new LatLng(42.291482, -85.598426)).title("Severn Residence Hall"));
        Marker trow = mMap.addMarker(new MarkerOptions().position(new LatLng(42.289819, -85.602697)).title("Trowbridge Residence Hall"));

        // Add markers to List
        markerList.add(null);
        markerList.add(null);
        markerList.add(dewing);
        markerList.add(dow);
        markerList.add(humphrey);
        markerList.add(light);
        markerList.add(ou);
        markerList.add(upjohn);
        markerList.add(null);
        markerList.add(hicks);
        markerList.add(anderson);
        markerList.add(fields);
        markerList.add(markin);
        markerList.add(nat);
        markerList.add(nelda);
        markerList.add(stetson);
        markerList.add(stowe);
        markerList.add(arcus);
        markerList.add(mandelle);
        markerList.add(facman);
        markerList.add(hodge);
        markerList.add(null);
        markerList.add(crissey);
        markerList.add(dewaters);
        markerList.add(harmon);
        markerList.add(hoben);
        markerList.add(llh);
        markerList.add(sev);
        markerList.add(trow);

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(hicksLoc));
        //mMap.moveCamera(CameraUpdateFactory.newCameraPosition(main));


    }

    @Override
    public void onConnected(Bundle bundle) {

        // LatLng objects
        LatLng newCenter;

        // Get current location
        // If location unavailable, center of campus will be used
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

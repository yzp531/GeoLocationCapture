package com.example.android.geolocationcapture;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Step 1 : Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        /* Step 2 : this permission should be added in AndroidMainfest.xml <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"> </uses-permission> */
         String locationProvider = LocationManager.NETWORK_PROVIDER;
        //Tip: Or use LocationManager.GPS_PROVIDER
        Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
        makeUseOfNewLocation(lastKnownLocation); // Step 5
        Toast.makeText( getApplicationContext(), "Gps LAST KNOWN", Toast.LENGTH_SHORT).show(); //To ge the message

        // Step 3 : Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                Toast.makeText( getApplicationContext(), "Gps LOCATION CHANGED", Toast.LENGTH_SHORT).show(); //To ge the message
                // Called when a new location is found by the network location provider.
                makeUseOfNewLocation(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
                Toast.makeText( getApplicationContext(), "Gps STATUS CHANGED", Toast.LENGTH_SHORT).show();//To ge the message
            }

            public void onProviderEnabled(String provider) {
                Toast.makeText( getApplicationContext(), "Gps Enabled", Toast.LENGTH_SHORT).show(); //To ge the message
            }

            public void onProviderDisabled(String provider) {
                Toast.makeText( getApplicationContext(), "Gps DISABLED", Toast.LENGTH_SHORT).show(); //To ge the message
            }
        };

        // Step 4 : Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void makeUseOfNewLocation(Location loc)
    {   loc.getLatitude();
        loc.getLongitude();
        String Text = "My current location is: " +"Latitude = " + loc.getLatitude() +"Longitude = " + loc.getLongitude();
        Toast.makeText( getApplicationContext(), Text,Toast.LENGTH_SHORT).show();
    }
}

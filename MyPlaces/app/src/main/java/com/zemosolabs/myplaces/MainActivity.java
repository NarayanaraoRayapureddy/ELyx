package com.zemosolabs.myplaces;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements GoogleMap.OnMarkerClickListener,OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {

    GoogleMap mMap;
    public static ArrayAdapter<String> mPlacesAdapter;
    GoogleMap tMap;
    private String selectedItem;
    private static final float DEFAULT_ZOOM = 14;
    boolean markerClicked;
    public static ArrayList<String> myPlaces = new ArrayList<String>();
    public static String location;
    public ListView listView;
    LatLng latlng;
    String locality;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    private FusedLocationProviderApi fusedLocationProviderApi = LocationServices.FusedLocationApi;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        SharedPreference sharedprefs = new SharedPreference();
        myPlaces = sharedprefs.loadPlaces(this);

        mPlacesAdapter = new ArrayAdapter<String>(
                this,
                R.layout.list_item_places,
                R.id.list_item_places_textview,
                myPlaces);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mGoogleApiClient.connect();

    }

    @Override
    protected void onStart(){
        super.onStart();
        mGoogleApiClient.connect();
        Log.i("onMarkerClick","Connected");
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
        switch (id) {
            case R.id.action_addPlaces:

                setContentView(R.layout.map_fragment);
                Log.i("onMarkerClick","Executed0");
                SupportMapFragment newMapFragment;
                Log.i("onMarkerClick","Executed0,1");
                newMapFragment = SupportMapFragment.newInstance();
                Log.i("onMarkerClick","Executed1");
                newMapFragment.setArguments(getIntent().getExtras());
                Log.i("onMarkerClick", "Executed2");
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.Fmap, newMapFragment).commit();
                Log.i("onMarkerClick", "Executed3");
//                latlng = new LatLng(17.422245, 78.382000);
                location = "Current location";
                newMapFragment.getMapAsync(this);

            case R.id.action_deletePlaces:

                myPlaces.clear();
                Log.i("CurrentThread",Thread.currentThread().getName());
                mPlacesAdapter.notifyDataSetChanged();
                SharedPreference sharedPre = new SharedPreference();
                sharedPre.removeAll(this, myPlaces);
                Log.i("onMarkerClick", "Removing all");

            default:
                return super.onOptionsItemSelected(item);

        }

    }


    @Override
    public void onMapReady(GoogleMap map) {
        Log.i("onMarkerClick", "Executed13");
        Marker marker = map.addMarker(new MarkerOptions()
                .position(latlng)
                .title(location));
        marker.showInfoWindow();
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latlng, DEFAULT_ZOOM);
        Log.i("onMarkerClick", "Executed14");
        mMap=map;
        mMap.moveCamera(update);
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }

    private void gotoLocation(double lat, double lng, float zoom){
        Log.i("onMarkerClick", "Executed9");
        LatLng ll = new LatLng(lat,lng);
        Log.i("onMarkerClick", "Executed10");
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        Log.i("onMarkerClick", "Executed11");
        Log.i("onMarkerClick", "Executed12");
        mMap.moveCamera(update);
        Log.i("onMarkerClick", "Executed15");
        MarkerOptions options = new MarkerOptions()
                .title(location)
                .snippet(locality)
                .position(new LatLng(lat, lng));

        Marker mMapMarker = mMap.addMarker(options);
        mMapMarker.showInfoWindow();
        mMap.setOnMarkerClickListener(this);
    }



    public void geoLocate(View v) throws IOException {

        EditText et = (EditText) findViewById(R.id.editText1);
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
        Log.i("onMarkerClick", "Executed5");

        location = et.getText().toString();
        if (location.length() == 0) {
            Toast.makeText(this, "Please enter a valid location", Toast.LENGTH_LONG).show();
            return;
        }
        Log.i("onMarkerClick", "Executed6");
        Geocoder gc = new Geocoder(this);
        List<Address> list = gc.getFromLocationName(location, 1);
        Address add = list.get(0);
        locality = add.getLocality();
        Toast.makeText(this, locality, Toast.LENGTH_LONG).show();
        Log.i("onMarkerClick", "Executed7");

        double lat = add.getLatitude();
        double lng = add.getLongitude();
        Log.i("onMarkerClick", "Executed8");
        gotoLocation(lat, lng, DEFAULT_ZOOM);
        Log.i("onMarkerClick", "Executed9");

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu_items, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.action_view_maps:
                setContentView(R.layout.map_fragment);
                Log.i("onMarkerClick","Executed0");
                SupportMapFragment newMapFragment;
                Log.i("onMarkerClick","Executed0,1");
                newMapFragment = SupportMapFragment.newInstance();
                Log.i("onMarkerClick","Executed1");
                Log.i("onMarkerClick", "Executed1");
                newMapFragment.setArguments(getIntent().getExtras());
                Log.i("onMarkerClick", "Executed2");
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.Fmap, newMapFragment).commit();


                Log.i("onMarkerClick", "Executed3");
                location = mPlacesAdapter.getItem(info.position);

                Log.i("onMarkerClick", location);
                Geocoder gc = new Geocoder(this);
                List<Address> list = null;
                try {
                    list = gc.getFromLocationName(location, 3);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Address add = list.get(0);
                locality = add.getLocality();
                Toast.makeText(this, locality, Toast.LENGTH_LONG).show();
                Log.i("onMarkerClick", "Executed7_1");

                double lat1 = add.getLatitude();
                double lng1 = add.getLongitude();
                Log.i("onMarkerClick", "Executed8_1");
                latlng = new LatLng(lat1,lng1);
                newMapFragment.getMapAsync(this);
                Log.i("onMarkerClick", "Executed9_1");
                return true;
            case R.id.action_delete:
                mPlacesAdapter.remove(mPlacesAdapter.getItem(info.position));
                Log.i("CurrentThread",Thread.currentThread().getName());
                mPlacesAdapter.notifyDataSetChanged();
                SharedPreference sharedPre = new SharedPreference();
                sharedPre.removePlace(this, location);

                Log.i("onCreateViewPlaceHolderFrag","Deleted");
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public boolean onMarkerClick (Marker marker){
            PlaceholderFragment list1 = new PlaceholderFragment();

            myPlaces.add(location);
            SharedPreference sharedPre = new SharedPreference();
            sharedPre.addPlace(this,location);

            Log.i("onMarkerClick","Changed ArrayList");

            setContentView(R.layout.activity_main);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container,list1)
                    .addToBackStack(null)
                    .commit();

            return false;
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        Log.i("Connected","Connected");
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            double lat = mLastLocation.getLatitude();
            double lng = mLastLocation.getLongitude();
            latlng = new LatLng(lat,lng);
            Log.i("Connected","Current Location");
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}

package com.example.win81.maptest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements LocationListener,OnMapReadyCallback {

    static final int MIN_TIME = 5000;
    static final float MIN_DIST = 0;
    LocationManager mgr ;

    boolean isGPSEnabled;
    boolean isNetWorkEnabled;

    private GoogleMap map;
    LatLng currPoint;
    TextView txv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        txv = (TextView) findViewById(R.id.txv);
        mgr = (LocationManager)getSystemService(LOCATION_SERVICE);

        checkPermission();

        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }


    @Override
    public void onResume(){
        super.onResume();
        enableLocationUpdates(true);
    }

    @Override
    public void onPause(){
        super.onPause();
        enableLocationUpdates(false);
    }

    private void enableLocationUpdates(boolean isTurnOn){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(isTurnOn){
                isGPSEnabled = mgr.isProviderEnabled(LocationManager.GPS_PROVIDER);
                isNetWorkEnabled = mgr.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

                if(!isGPSEnabled && !isNetWorkEnabled) {
                    Toast.makeText(this,"請確認開啟定位!",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(this,"取得訂位中",Toast.LENGTH_LONG).show();

                    if (isGPSEnabled)
                        mgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, MIN_DIST, this);

                    if (isNetWorkEnabled)
                        mgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DIST, this);
                }
            }
            else{
                mgr.removeUpdates(this);
            }
        }
    }

    private  void checkPermission(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION )!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},200);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        if(requestCode == 200){
            if(grantResults.length >=1 &&  grantResults[0] != PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"需要權限",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public  void onLocationChanged(Location location){
        if(location != null){
            txv.setText(String.format("緯度%.4f,精度%.4f(%s 定位)",location.getLatitude(),location.getLongitude(),location.getProvider()));

            currPoint = new LatLng(location.getLatitude(),location.getLongitude());

            if(map != null){
                map.animateCamera(CameraUpdateFactory.newLatLng(currPoint));
                map.addMarker(new MarkerOptions().position(currPoint).title("目前位置"));
                Toast.makeText(this,"地圖可以顯示",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this,"地圖無法顯示",Toast.LENGTH_LONG).show();
            }
        }
        else{
            txv.setText("暫時無法取得定位資訊");
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

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

        switch(item.getItemId()) {
            case R.id.mark:
                map.clear();
                map.addMarker(new MarkerOptions().position(map.getCameraPosition().target).title("到此一遊"));
                break;
            case R.id.satellite:
                item.setChecked(!item.isChecked());
                if(item.isChecked())
                    map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                else
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case R.id.traffic:
                item.setChecked(!item.isChecked());
                map.setTrafficEnabled(item.isCheckable());
                break;
            case R.id.currLocation:
                map.animateCamera(CameraUpdateFactory.newLatLng(currPoint));
                break;
            case R.id.setGPS:
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
                break;
            case R.id.about:
                new AlertDialog.Builder(this)
                        .setTitle("關於我的地圖")
                        .setMessage("我的地圖體驗版 v1.0\nCopyrighht 2017 Flag Corp.")
                        .setPositiveButton("關閉",null)
                        .show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.moveCamera(CameraUpdateFactory.zoomTo(18));

    }
}

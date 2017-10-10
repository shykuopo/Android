package com.example.win81.location;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.audiofx.BassBoost;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener{

    static final int MIN_TIME = 5000;
    static final float MIN_DIST = 0;

    Location myLocation;
    Geocoder geocoder;
    EditText edtLat,edtLon;

    LocationManager mgr;
    TextView txvLoc,txvSetting;

    boolean isGPSEnabled,isNetworkEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txvLoc = (TextView)findViewById(R.id.txvLoc);
        txvSetting = (TextView)findViewById(R.id.txvSetting);
        edtLat = (EditText)findViewById(R.id.edtLat);
        edtLon = (EditText)findViewById(R.id.edtLon);

        geocoder = new Geocoder(this, Locale.getDefault());

        mgr = (LocationManager)getSystemService(LOCATION_SERVICE);
        checkPermission();
    }
    @Override
    protected void onResume(){
        super.onResume();

        txvLoc.setText("尚未取得定位資訊");
        enableLocationUpdates(true);

        String str = "GPS定位："+(isGPSEnabled?"開啟":"關閉");
        str += "\n網路定位:"+(isNetworkEnabled?"開啟":"關閉");
        txvSetting.setText(str);
    }
    @Override
    protected void onPause(){
        super.onPause();

        enableLocationUpdates(false);
    }

    @Override
    public void onLocationChanged(Location location) {

        myLocation = location;

        String str = "定位提供者:"+location.getProvider();
        str += String.format("\n緯度:%.5f\n精度:%.5f\n高度:%.2f公尺",location.getLatitude(),location.getLongitude(),location.getAltitude());

        txvLoc.setText(str);

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

    public void setup(View v){
        Intent it = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(it);
    }

    public void getLocation(View v){
        if (myLocation !=null){
            edtLat.setText(Double.toString(myLocation.getLatitude()));
            edtLon.setText(Double.toString(myLocation.getLongitude()));
        }
        else {
            txvLoc.setText("無法取得定位資料!");
        }
    }

    public void onQuery(View view){
        String strLat = edtLat.getText().toString();
        String strLon = edtLon.getText().toString();

        if(strLat.length() == 0 || strLon.length() == 0){
            return;
        }

        txvLoc.setText("讀取中...");
        double latitude = Double.parseDouble(strLat);
        double longitude = Double.parseDouble(strLon);

        String strAddr = "";
        try{

            List<Address>listAddr = geocoder.getFromLocation(latitude,longitude,1);

            if(listAddr == null || listAddr.size() == 0)
                strAddr += "無法取得地址資料";
            else{
                Address addr = listAddr.get(0);
                for (int i = 0; i<=addr.getMaxAddressLineIndex();i++)
                    strAddr += addr.getAddressLine(i) + "\n";
            }
        }catch (Exception ex){
            strAddr += "取得地址發生錯誤:"+ex.toString();
        }
        txvLoc.setText(strAddr);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
        if(requestCode == 200){
            if(grantResults.length >= 1 && grantResults[0] !=PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"程式需要定位權限才能運作",Toast.LENGTH_LONG).show();
            }
        }
    }

    private void checkPermission(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},200);
        }
    }

    private void enableLocationUpdates(boolean isTurnOn){
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(isTurnOn){
                isGPSEnabled = mgr.isProviderEnabled(LocationManager.GPS_PROVIDER);
                isNetworkEnabled = mgr.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

                if(!isGPSEnabled && !isNetworkEnabled){
                    Toast.makeText(this,"請確認已開啟定位功能!",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(this,"取得定位資訊中...",Toast.LENGTH_LONG).show();
                    if(isGPSEnabled)
                        mgr.requestLocationUpdates(LocationManager.GPS_PROVIDER,MIN_TIME,MIN_DIST,this);
                    if(isNetworkEnabled)
                        mgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,MIN_TIME,MIN_DIST,this);
                }
            }
            else{
                mgr.removeUpdates(this);
            }
        }
    }
}

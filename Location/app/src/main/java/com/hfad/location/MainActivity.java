package com.hfad.location;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.LocationServices;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.geometry.LatLngBounds;
import com.naver.maps.map.LocationSource;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.LocationOverlay;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.FusedLocationSource;

import java.security.Security;

import static com.naver.maps.map.LocationTrackingMode.NoFollow;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private FusedLocationSource locationSource;
    private final int MY_PERMISSIONS_REQUEST_CAMERA=1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);
        //위치정보 인스턴스 생성
         LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        int permissionCheck = ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)){

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
            }
            }

            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);







        MapFragment mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.map, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);


    }


    final LocationListener gpsLocationListener = new LocationListener() {

        public void onLocationChanged(Location location) {


                String provider = location.getProvider();
                double longitude = location.getLongitude();
                double latitude = location.getLatitude();
                double altitude = location.getAltitude();
            }
            public void onStatusChanged (String provider,int status, Bundle extras){

            }
            public void onProviderEnabled (String provider){
            }
            public void onProviderDisabled (String provider){
            }
        };
//현재 위치 정보 얻어오는 메소드

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (locationSource.onRequestPermissionsResult(
                requestCode, permissions, grantResults)) {
            return;
        }
        super.onRequestPermissionsResult(
                requestCode, permissions, grantResults
        );

    }

    public void onRequestPermissionResult(int requestCode, String permissions[],int [] grantResults)
    {
        switch(requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(this, "승인이 허가되어 있습니다", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this,"아직 승인받지 않았습니다",Toast.LENGTH_LONG).show();
                }
                return ;
            }
        }
    }
    @UiThread
    @Override


    public void onMapReady(@NonNull final NaverMap naverMap) {

        UiSettings uiSettings = naverMap.getUiSettings();

        naverMap.setMapType(NaverMap.MapType.Satellite);


        final LocationOverlay locationOverlay = naverMap.getLocationOverlay(); //오버레이 객체얻어오기
        locationOverlay.setVisible(true);


        naverMap.setLocationSource(locationSource);

        naverMap.setLocationTrackingMode(LocationTrackingMode.NoFollow);
        uiSettings.setLocationButtonEnabled(true);
        locationOverlay.setCircleRadius(200);
        locationOverlay.setCircleOutlineWidth(20);
        locationOverlay.setCircleOutlineColor(Color.GREEN);







        Button button1=findViewById(R.id.Home);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Marker homemarker=new Marker();
               homemarker.setPosition(new LatLng(37.1231231,126.2342341));
               homemarker.setMap(naverMap);








            }
        });
        Button button3=findViewById(R.id.Etc);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){


            }
        });






    }
}


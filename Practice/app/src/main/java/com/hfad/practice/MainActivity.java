package com.hfad.practice;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.graphics.Color;
import android.graphics.PointF;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.CircleOverlay;
import com.naver.maps.map.overlay.LocationOverlay;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.FusedLocationSource;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private FusedLocationSource locationSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);

        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment= (MapFragment)fm.findFragmentById(R.id.map_fragment);
        if(mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map_fragment, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);




    }
    @Override

    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,  @NonNull int[] grantResults) {
        if (locationSource.onRequestPermissionsResult(
                requestCode, permissions, grantResults)) {
            return;
        }
        super.onRequestPermissionsResult(
                requestCode, permissions, grantResults);
    }


    @UiThread
    @Override
    public void onMapReady(@NonNull final NaverMap navermap)
    {
        final Marker home_marker = new Marker();
        final Marker company_marker = new Marker();


        //마커 표시(xml에 위도,경도 딱맞춰주어야 출력됨)
        final TextView output=(TextView)findViewById(R.id.textview);
        Button Home=(Button)findViewById(R.id.Home);
        Button Company=(Button)findViewById(R.id.Company);
        final Button Etc=(Button)findViewById(R.id.Etc);
        final Button twenty=(Button)findViewById(R.id.twenty);
        final Button fourty=(Button)findViewById(R.id.fourty);
        final Button sixty=(Button)findViewById(R.id.sixty);

        //버튼 뷰








        final UiSettings uiSettings=navermap.getUiSettings();
        //현재위치찾기 버튼 쓰려면 객체를 불러와야함.


        navermap.setMapType(NaverMap.MapType.Satellite);

        // 지도옵션(스카이뷰)

        navermap.setLocationSource(locationSource);
        //위치를 받아옴

        LocationOverlay locationOverlay = navermap.getLocationOverlay();
        locationOverlay.setVisible(true);

        //위치오버레이




        locationOverlay.setPosition(new LatLng(36.763695,127.281796));
        //특정좌표에 위치오버레이 표기


        uiSettings.setLocationButtonEnabled(true);
        //현재위치찾기버튼 보이게하기

        navermap.addOnLocationChangeListener(new NaverMap.OnLocationChangeListener() {
            @Override
            public void onLocationChange(@NonNull final Location location) {

                final CircleOverlay circle = new CircleOverlay();
                circle.setCenter(new LatLng(location.getLatitude(),location.getLongitude()));
                circle.setRadius(0);
                circle.setMap(navermap);

                Etc.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        output.setText("latitude :  "+location.getLatitude() +"   Longitude : "+location.getLongitude());







                    }
                });

                twenty.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        circle.setRadius(40);
                        circle.setMap(navermap);






                    }
                });
                fourty.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view)
                    {
                        circle.setRadius(160);
                        circle.setMap(navermap);
                    }
                });
                sixty.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view)
                    {
                        circle.setRadius(360);
                        circle.setMap(navermap);
                    }
                });


            }
        });


        Home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(36.763695,127.281796));
                navermap.moveCamera(cameraUpdate);
                home_marker.setPosition(new LatLng(36.763695, 127.281796));
                home_marker.setMap(navermap);






            }
        });

        //Home 버튼 누르면 집의 좌표로 마커가 표기해주고 카메라도 집의 좌표로 이동
        Company.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(36.723420,127.234123));
                navermap.moveCamera(cameraUpdate);
                company_marker.setPosition(new LatLng(36.723420,127.234123));
                company_marker.setMap(navermap);




            }
        });
        //위와같은 원리(회사)











    }



    }


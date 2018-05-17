package com.example.lvqinglong.swpumap.service;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.util.concurrent.ScheduledExecutorService;


public class LocalServer extends Service {

    ScheduledExecutorService scheduledForLocationPull = null;


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onCreate() {
        super.onCreate();

        getLngAndLat(this);

//        scheduledForLocationPull = Executors.newSingleThreadScheduledExecutor();
//        scheduledForLocationPull.scheduleWithFixedDelay(new Runnable() {
//            public void run() {
//                try {
////                    pushLocationToServer(null);
//
//                } catch (Throwable e) {
//                    Log.v("Amence", "scheduledForLocationPull error" + e.getMessage());
//                }
//
//            }
//        }, Constant.DEFAULT_SCHEDULE_INITIAL_DELAY, Constant.DEFAULT_SCHEDULE_DELAY, TimeUnit.SECONDS);

    }

    private void pushLocationToServer(String lngAndLat) {

    }


    /**
     * 获取经纬度
     *
     * @param context
     * @return
     */
    public String getLngAndLat(Context context) {

        LocationManager locationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);//低精度，如果设置为高精度，依然获取不了location。
        criteria.setAltitudeRequired(false);//不要求海拔
        criteria.setBearingRequired(false);//不要求方位
        criteria.setCostAllowed(true);//允许有花费
        criteria.setPowerRequirement(Criteria.POWER_LOW);//低功耗

        //从可用的位置提供器中，匹配以上标准的最佳提供器
        String locationProvider = locationManager.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(context.getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context.getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("Amence", "onCreate: 没有权限 ");
            return null;
        }
        Location location = locationManager.getLastKnownLocation(locationProvider);
        if (location != null) {
            //不为空,显示地理位置经纬度
            showLocation(location);
        }
        //监视地理位置变化
        locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);

        return showLocation(location);
    }


    LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle arg2) {
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.d("Amence", "onProviderEnabled: " + provider + ".." + Thread.currentThread().getName());
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.d("Amence", "onProviderDisabled: " + provider + ".." + Thread.currentThread().getName());
        }

        @Override
        public void onLocationChanged(Location location) {
            Log.d("Amence", "onLocationChanged: " + ".." + Thread.currentThread().getName());
            //如果位置发生变化,重新显示
            Log.v("Amence","onLocationChanged:"+"Latitude="+location.getLatitude()+"Longitude="+location.getLongitude());

            pushLocationToServer(showLocation(location));
        }
    };

    public String showLocation(Location location) {
        return location.getLongitude()+","+location.getLatitude();
    }
}

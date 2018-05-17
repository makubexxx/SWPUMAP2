package com.example.lvqinglong.swpumap.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.DisplayMetrics;
import android.view.View;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Amence on 2016/11/4.
 */

public class Util {

    public static String cityName;  //城市名

    private static Geocoder geocoder;   //此对象能通过经纬度来获取相应的城市等信息

    /**
     * 通过地理坐标获取城市名  其中CN分别是city和name的首字母缩写
     *
     * @param context
     */
    public static String getCNBylocation(Context context) {

        geocoder = new Geocoder(context);
        //用于获取Location对象，以及其他
        LocationManager locationManager;
        String serviceName = Context.LOCATION_SERVICE;
        //实例化一个LocationManager对象
        locationManager = (LocationManager) context.getSystemService(serviceName);
        //provider的类型
        String provider = LocationManager.NETWORK_PROVIDER;

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);   //高精度
        criteria.setAltitudeRequired(false);    //不要求海拔
        criteria.setBearingRequired(false); //不要求方位
        criteria.setCostAllowed(false); //不允许有话费
        criteria.setPowerRequirement(Criteria.POWER_LOW);   //低功耗

        //通过最后一次的地理位置来获得Location对象
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return HanZi4UrlUnicode("北京");
        }
        Location location = locationManager.getLastKnownLocation(provider);

        String queryed_name = updateWithNewLocation(location);
        if ((queryed_name != null) && (0 != queryed_name.length())) {

            cityName = queryed_name;
        }

        /*
         * 第二个参数表示更新的周期，单位为毫秒；第三个参数的含义表示最小距离间隔，单位是米
         * 设定每30秒进行一次自动定位
         */
        locationManager.requestLocationUpdates(provider, 30000, 50,
                locationListener);
        //移除监听器，在只有一个widget的时候，这个还是适用的
        locationManager.removeUpdates(locationListener);

        return HanZi4UrlUnicode(cityName);
    }

    /**
     * 方位改变时触发，进行调用
     */
    private final static LocationListener locationListener = new LocationListener() {
        String tempCityName;

        public void onLocationChanged(Location location) {

            tempCityName = updateWithNewLocation(location);
            if ((tempCityName != null) && (tempCityName.length() != 0)) {

                cityName = tempCityName;
            }
        }

        public void onProviderDisabled(String provider) {
            tempCityName = updateWithNewLocation(null);
            if ((tempCityName != null) && (tempCityName.length() != 0)) {

                cityName = tempCityName;
            }
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };

    /**
     * 更新location
     *
     * @param location
     * @return cityName
     */
    private static String updateWithNewLocation(Location location) {
        String mcityName = "";
        double lat = 0;
        double lng = 0;
        List<Address> addList = null;
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
        } else {

            System.out.println("无法获取地理信息");
        }

        try {

            addList = geocoder.getFromLocation(lat, lng, 1);    //解析经纬度

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (addList != null && addList.size() > 0) {
            for (int i = 0; i < addList.size(); i++) {
                Address add = addList.get(i);
                mcityName += add.getLocality();
            }
        }
        if (mcityName.length() != 0) {

            return mcityName.substring(0, (mcityName.length() - 1));
        } else {
            return mcityName;
        }
    }


    /**
     * 汉字转换url
     *
     * @param str
     * @return
     */
    public static String HanZi4UrlUnicode(String str) {
        String urlUnicode = "";
        try {
            urlUnicode = URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return urlUnicode;
    }

    /**
     * url 转化汉字
     *
     * @param str
     * @return
     */
    public static String UrlUnicode4HanZi(String str) {
        String hanZi = "";
        try {
            hanZi = URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return hanZi;
    }

    public static SharedPreferences getSharePreference(Context context) {
        return context.getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);
    }




    /**
     * 存入String 类型的数据
     *
     * @param context
     * @param key
     * @param str
     */
    public static void saveString(Context context, String key, String str) {
        SharedPreferences sp = getSharePreference(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, str);
        editor.commit();
    }

    /**
     * 存入String
     *
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        SharedPreferences sp = getSharePreference(context);
        return sp.getString(key, null);
    }

    /**
     * 存入Boolean
     *
     * @param context
     * @param key
     * @param bol
     */
    public static void saveBoolean(Context context, String key, boolean bol) {
        SharedPreferences sp = getSharePreference(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, bol);
        editor.commit();

    }

    /**
     * 取出boolean
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = getSharePreference(context);
        return sp.getBoolean(key, true);
    }


    /**
     * make true current connect service is wifi
     *
     * @return
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    public static boolean isMOBILE(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }

    /**
     * 检测网络可用
     *
     * @param context
     * @return
     */
    public static boolean isNetWorkAvailable(Context context) {
        if (isWifi(context) || isMOBILE(context)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 设置全屏
     */
    public static void setFullScreen(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    /**
     * 判断是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;

    }

    /**
     * 获取当前时间 月/日
     *
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd");//设置日期格式
        String date = df.format(new Date());

        return date.substring(0,1) .equals("0") ? date.substring(1, date.length()) : date;
    }

    /**
     * 获取当前的月份
     *
     * @return
     */
    public static String getCurrentMonth() {
        return getCurrentTime().split("/")[0];
    }

    /**
     * * 获取当前的日
     *
     * @return
     */
    public static String getCurrentDay() {
        return getCurrentTime().split("/")[1];
    }


    public static <T> T parseGson(String jsonStr, T t) {
        Gson gson = new Gson();
        return (T) gson.fromJson(jsonStr, t.getClass());

    }

    public static int dip2px(int dip, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) (dip * displayMetrics.density + 0.5);

    }

    public static int px2dip(int px, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) (px / displayMetrics.density + 0.5f);

    }

    /**
     * 获取当前的版本号
     *
     * @param context
     * @return
     */
    public static String getCurrentVersion(Context context) {
        int version;
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            version = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return "1.0";
        }


        return version + "";
    }

}

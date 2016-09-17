package br.com.imarket.imarket.util;

import android.content.Context;
import android.location.LocationManager;
import android.util.Log;

public class LocationUtil {

    public static boolean isGPSEnabled(Context context) {
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGpsEnabled = false;

        try {
            isGpsEnabled = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {
            Log.e("SystemError", "Cannot retrieve gps information");
        }
        return isGpsEnabled;
    }
}

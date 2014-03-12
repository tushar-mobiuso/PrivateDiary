package com.mobiuso.mine.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

public class LocationTracker extends Service implements LocationListener{

	Context context;
	private double latitude;
	private double longitude;
	private Location location;
    protected LocationManager locationManager;
    
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public LocationTracker(Context context) {
        this.context = context;
        getLocation();
    }

	public Location getLocation(){
		try{
			locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
			isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		}catch (Exception e) {
			e.printStackTrace();
		}
		if(locationManager != null){
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
			location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			latitude = location.getLatitude();
			longitude = location.getLongitude();
		}
		return location;
	}
	
	public double getLatitude() {
		latitude = location.getLatitude();
		return latitude;
	}

	public double getLongitude() {
		longitude = location.getLongitude();
		return longitude;
	}
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
}

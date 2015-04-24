package com.example.alert;

import android.location.Location;

import com.google.android.gms.location.LocationListener;

public class MyLocationListener implements LocationListener {

	private double Latitude;
	private double Longitude;
	
	@Override
	public void onLocationChanged(Location location) {
		
		this.Latitude = location.getLatitude();
		this.Longitude = location.getLongitude();
		
	}
	public double getLatitude() {
		return Latitude;
	}
	
	public double getLongitude() {
		return Longitude;
	}
	

}

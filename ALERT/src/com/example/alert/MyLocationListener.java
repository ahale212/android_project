package com.example.alert;

import android.location.Location;

import com.google.android.gms.location.LocationListener;
/**
 * class used to implement the sending of the SMS, this adds location of user
 * @author adamhale
 *
 */
public class MyLocationListener implements LocationListener {
	//declare vars
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

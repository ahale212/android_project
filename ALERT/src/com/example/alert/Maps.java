/*package com.example.alert;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Maps extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.maps, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}*/

package com.example.alert;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Maps extends FragmentActivity {

	private static final int GPS_ERRORDIALOG_REQUEST = 9001;
	// setting the latlng for the location of Belfast City Hospital, taken from
	GoogleMap mmMap;
	Button CITY, ROYAL, ULSTER;
	
	
	private final LatLng LOCATION_CITY = new LatLng(54.587403, -5.941667);
	private final LatLng LOCATION_ROYAL = new LatLng(54.593553, -5.952457);
	private final LatLng LOCATION_ULSTER = new LatLng(54.596795, -5.811546);
	private static final double CITY_LAT = 54.587403, CITY_LNG = -5.941667,
			ROYAL_LAT = 54.593553, ROYAL_LNG = -5.952457,
			ULSTER_LAT = 54.596795, ULSTER_LNG = -5.811546,
			DEFAULT_LAT = 54.584409, DEFAULT_LNG = -5.934049;
	
	
	private static final float DEFAULT_ZOOM = 11;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (servicesOK()) {
			setContentView(R.layout.activity_map);

			if (iniMap()) {
				goToLocation(DEFAULT_LAT, DEFAULT_LNG, 10);
				Toast.makeText(this, "Ready to map", Toast.LENGTH_SHORT).show();
				mmMap.setMyLocationEnabled(true);
				
			} else {
				Toast.makeText(this, "Map not Available", Toast.LENGTH_SHORT)
						.show();
			}

		} else {
			setContentView(R.layout.activity_map_error);

		}

		Button CITY = (Button) findViewById(R.id.btnCity);
		CITY.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				goToLocation(CITY_LAT, CITY_LNG, DEFAULT_ZOOM);
				mmMap.addMarker(new MarkerOptions().position(LOCATION_CITY)
						.title("Belfast City Hospital"));

			}
		});

		Button ROYAL = (Button) findViewById(R.id.btnRoyal);
		ROYAL.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				goToLocation(ROYAL_LAT, ROYAL_LNG, DEFAULT_ZOOM);
				mmMap.addMarker(new MarkerOptions().position(LOCATION_ROYAL)
						.title("Belfast Royal Hospital"));
			}
		});

		Button ULSTER = (Button) findViewById(R.id.btnUlster);
		ULSTER.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				goToLocation(ULSTER_LAT, ULSTER_LNG, DEFAULT_ZOOM);
				mmMap.addMarker(new MarkerOptions().position(LOCATION_ULSTER)
						.title("Ulster Hospital"));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.maps, menu);
		return true;
	}// end of method

	// method used to allow the buttons to highlight where city location is

	public boolean servicesOK() {

		int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

		if (isAvailable == ConnectionResult.SUCCESS) {
			return true;
		} else if (GooglePlayServicesUtil.isUserRecoverableError(isAvailable)) {
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvailable,
					this, GPS_ERRORDIALOG_REQUEST);
			dialog.show();
		} else {
			Toast.makeText(this, "Can not connect", Toast.LENGTH_SHORT).show();
		}
		return false;
	}

	private boolean iniMap() {
		if (mmMap == null) {
			SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
			mmMap = mapFrag.getMap();

		}
		return (mmMap != null);
	}

	private void goToLocation(double lat, double lng, float defaultZoom) {
		LatLng ll = new LatLng(lat, lng);
		CameraUpdate update = CameraUpdateFactory
				.newLatLngZoom(ll, defaultZoom);
		mmMap.moveCamera(update);
	}

	@Override
	protected void onPause() {
		super.onPause();
	}
}


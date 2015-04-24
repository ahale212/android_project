package com.example.alert;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

public class Body_Model extends FragmentActivity {
	
	private ViewPager viewpager;
	private FragmentPageAdapter fpa;
	
	Intent intent;
	
	int patient_rec;
	
	private int counthead			= 1;
	private int countBackhead 		= 1;
	private int countneck 			= 1;
	private int countBackneck		= 1;
	private int countchest 			= 1;
	private int countback 			= 1;
	private int countgroin 			= 1;
	private int countbum 			= 1;
	private int countleftarm 		= 1;
	private int countBackleftarm 	= 1;
	private int countrightarm 		= 1;
	private int countBackrightarm 	= 1;
	private int countrightleg 		= 1;
	private int countBackrightleg 	= 1;
	private int countleftleg 		= 1;
	private int countBackleftleg 	= 1;
	
	public static final int NUMBER_OF_COLOURS = 3;
	
	public int getCounthead() {
		return counthead;
	}

	public void setCounthead(int counthead) {
		this.counthead = counthead;
	}

	public int getCountneck() {
		return countneck;
	}

	public void setCountneck(int countneck) {
		this.countneck = countneck;
	}

	public int getCountchest() {
		return countchest;
	}

	public void setCountchest(int countchest) {
		this.countchest = countchest;
	}

	public int getCountback() {
		return countback;
	}

	public void setCountback(int countback) {
		this.countback = countback;
	}

	public int getCountgroin() {
		return countgroin;
	}

	public void setCountgroin(int countgroin) {
		this.countgroin = countgroin;
	}

	public int getCountbum() {
		return countbum;
	}

	public void setCountbum(int countbum) {
		this.countbum = countbum;
	}

	public int getCountleftarm() {
		return countleftarm;
	}

	public void setCountleftarm(int countleftarm) {
		this.countleftarm = countleftarm;
	}

	public int getCountrightarm() {
		return countrightarm;
	}

	public void setCountrightarm(int countrightarm) {
		this.countrightarm = countrightarm;
	}

	public int getCountrightleg() {
		return countrightleg;
	}

	public void setCountrightleg(int countrightleg) {
		this.countrightleg = countrightleg;
	}

	public int getCountleftleg() {
		return countleftleg;
	}

	public void setCountleftleg(int countleftleg) {
		this.countleftleg = countleftleg;
	}

	public int getCountBackhead() {
		return countBackhead;
	}

	public void setCountBackhead(int countBackhead) {
		this.countBackhead = countBackhead;
	}

	public int getCountBackneck() {
		return countBackneck;
	}

	public void setCountBackneck(int countBackneck) {
		this.countBackneck = countBackneck;
	}

	public int getCountBackleftarm() {
		return countBackleftarm;
	}

	public void setCountBackleftarm(int countBackleftarm) {
		this.countBackleftarm = countBackleftarm;
	}

	public int getCountBackrightarm() {
		return countBackrightarm;
	}

	public void setCountBackrightarm(int countBackrightarm) {
		this.countBackrightarm = countBackrightarm;
	}

	public int getCountBackrightleg() {
		return countBackrightleg;
	}

	public void setCountBackrightleg(int countBackrightleg) {
		this.countBackrightleg = countBackrightleg;
	}

	public int getCountBackleftleg() {
		return countBackleftleg;
	}

	public void setCountBackleftleg(int countBackleftleg) {
		this.countBackleftleg = countBackleftleg;
	}
	
	public int getPatient_rec() {
		return patient_rec;
	}

	public void setPatient_rec(int patient_rec) {
		this.patient_rec = patient_rec;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_body_model);
		
		intent = getIntent();
		patient_rec = intent.getExtras().getInt("P_Id");
		
		if( savedInstanceState != null ) {
			
			counthead = savedInstanceState.getInt("counthead");
			countBackhead = savedInstanceState.getInt("countBackhead");
			countneck = savedInstanceState.getInt("countneck");
			countBackneck = savedInstanceState.getInt("countBackneck");
			countchest = savedInstanceState.getInt("countchest");
			countback = savedInstanceState.getInt("countback");
			countgroin = savedInstanceState.getInt("countgroin");
			countbum = savedInstanceState.getInt("countbum");
			countleftarm = savedInstanceState.getInt("countleftarm");
			countBackleftarm = savedInstanceState.getInt("countBackleftarm");
			countrightarm = savedInstanceState.getInt("countrightarm");
			countBackrightarm = savedInstanceState.getInt("countBackrightarm");
			countrightleg = savedInstanceState.getInt("countrightleg");
			countBackrightleg = savedInstanceState.getInt("countBackrightleg");
			countleftleg = savedInstanceState.getInt("countleftleg");
			countBackleftleg = savedInstanceState.getInt("countBackleftleg");
		}
		
		viewpager = (ViewPager)findViewById(R.id.pager);
	
		fpa = new FragmentPageAdapter(getSupportFragmentManager());
	
		viewpager.setAdapter(fpa);
		
		viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() 
		{
			
			@Override
			public void onPageSelected(int arg0) {
					
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
				
			}
		});
		
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
	   super.onSaveInstanceState(savedInstanceState);
	   
	   savedInstanceState.putInt("counthead", counthead);
	   savedInstanceState.putInt("countBackhead", countBackhead);
	   savedInstanceState.putInt("countneck", countneck);
	   savedInstanceState.putInt("countBackneck", countBackneck);
	   savedInstanceState.putInt("countchest", countchest);
	   savedInstanceState.putInt("countback", countback);
	   savedInstanceState.putInt("countgroin", countgroin);
	   savedInstanceState.putInt("countbum", countbum);
	   savedInstanceState.putInt("countleftarm", countleftarm);
	   savedInstanceState.putInt("countBackleftarm", countBackleftarm);
	   savedInstanceState.putInt("countrightarm", countrightarm);
	   savedInstanceState.putInt("countBackrightarm", countBackrightarm);
	   savedInstanceState.putInt("countrightleg", countrightleg);
	   savedInstanceState.putInt("countBackrightleg", countBackrightleg);
	   savedInstanceState.putInt("countleftleg", countleftleg);
	   savedInstanceState.putInt("countBackleftleg", countBackleftleg);
	   savedInstanceState.putInt("testValue", 5);
	   
	}
}

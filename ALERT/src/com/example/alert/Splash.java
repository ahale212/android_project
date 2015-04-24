package com.example.alert;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
/**
 * this is the class for the splash screen. Here, after the animation, the user will be taken to the login
 * @author adamhale
 *
 */
public class Splash extends Activity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//log messages for debugging
		Log.v("error", "before setting content view");
		//set the view
		setContentView(R.layout.splash);
		//log messages for debugging
		Log.v("error", "before setting content view");
		
		//make thread for how long before user is taken to login screen
		 	Thread timer = new Thread() {
			public void run() {
				try {
					sleep(8000);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					Intent openStartingPointActivity = new Intent("android.intent.action.STARTINGPOINT");
					startActivity(openStartingPointActivity);
				}
			}
		};
		//method called to start timer
		  timer.start();
		
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//finish();
	}

}
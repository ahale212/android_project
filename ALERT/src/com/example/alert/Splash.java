package com.example.alert;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

public class Splash extends Activity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Log.v("error", "before setting content view");
		
		setContentView(R.layout.splash);
		
		Log.v("error", "before setting content view");
		

		/*MediaPlayer siren = MediaPlayer.create(Splash.this, R.drawable.siren2);
	
		siren.start();*/
		
		
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
		  timer.start();
		
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//finish();
	}

}
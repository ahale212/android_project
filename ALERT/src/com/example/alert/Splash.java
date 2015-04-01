package com.example.alert;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		//MediaPlayer siren = MediaPlayer.create(Splash.this, R.drawable.siren);
		
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(2000);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					Intent openStartingPointActivity = new Intent("android.intent.action.STARTINGPOINT");
					startActivity(openStartingPointActivity);
				}
			}
		};
		timer.start();
		//siren.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
package com.example.alert;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

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
		
		ImageView img_animation = (ImageView) findViewById(R.id.imageViewAmbulance);
		 
		  TranslateAnimation animation = new TranslateAnimation(000.0f, 200.0f,
		    0.0f, 0.0f);
		  animation.setDuration(2000);
		  animation.setRepeatCount(5);
		  animation.setRepeatMode(2);
		  animation.setFillAfter(true);
		  img_animation.startAnimation(animation);
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
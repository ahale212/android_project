package com.example.alert;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class Background_view extends View{

	float left=0;
	float top=0;
	ImageAnimator background, ambulance, hospital;
	Bitmap Image;
	int count, count2=1;
	Float ratio;
	
	public Background_view(Context context) {
		super(context);

	}
	
	public Background_view(Context context, AttributeSet attrs) {
		super(context, attrs);

	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		
		background = new ImageAnimator(0, 0, 5000, 0, R.drawable.background, this);
		ambulance = new ImageAnimator(w/2, 0, 3500, 3500, R.drawable.ambulance_blue, this);
		hospital = new ImageAnimator(w, 0, 2000, 3000, R.drawable.hospital, this);
		
		ratio = (float)h/background.getHeight();
		
		background.scaleImage(ratio);
		ambulance.scaleImage(ratio/1.5f);
		hospital.scaleImage(ratio*1.3f);
		
		ambulance.setX(w/2-ambulance.getWidth());
		ambulance.setY(h * 0.95f - ambulance.getHeight());
		hospital.setY(h * 0.95f - hospital.getHeight());
		
		background.animate((int)-(background.getWidth() - w));
		ambulance.animate((int)(w-hospital.getWidth()));
		hospital.animate((int)(w-hospital.getWidth()));
		
		
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		if (count==30){
			count=0;
			changeLights();	
		}
		
		canvas.drawBitmap(background.Image, background.getX(), background.getY(), null);
		canvas.drawBitmap(ambulance.Image, ambulance.getX(), ambulance.getY(), null);
		canvas.drawBitmap(hospital.Image, hospital.getX(), hospital.getY(), null);
		count++;
	}
	
	private void changeLights(){
		
		if (count2%2==0){
			
		ambulance.Image = BitmapFactory.decodeResource(getResources(), R.drawable.ambulance_red);
		ambulance.scaleImage(ratio/1.5f);
		} else {
		ambulance.Image = BitmapFactory.decodeResource(getResources(), R.drawable.ambulance_blue);
		ambulance.scaleImage(ratio/1.5f);
		}
		count2++;
	}
	
}

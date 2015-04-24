package com.example.alert;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

public class ImageAnimator {
		
		private float X, Y, width, height;
		private int duration, wait, resourceId, setId;
		Bitmap Image;
		ValueAnimator animator;
		View view;
		
		public ImageAnimator(float x, float y, int duration, int wait, int resourceId, View view){
			this.X = x;
			this.Y = y;
			this.duration = duration;
			this.wait = wait;
			this.resourceId = resourceId;
			this.setId = resourceId;
			Image = BitmapFactory.decodeResource(view.getResources(), resourceId);
			height = Image.getHeight();
			width = Image.getWidth();
			
			this.view = view;
		}
		

		public float getX() {
			return X;
		}

		public float getY() {
			return Y;
		}

		public int getDuration() {
			return duration;
		}
		
		public int getWait() {
			return wait;
		}
		
		public int resourceId() {
			return resourceId;
		}

		public void setX(float X) {
			this.X = X;
		}

		public void setY(float Y) {
			this.Y = Y;
		}

		public void setDuration(int duration) {
			this.duration = duration;
		}
		
		public void setWait(int wait){
			this.wait = wait;
		}
		
		public void setResourceId(int resourceId){
			this.resourceId = resourceId;
		}

		public float getWidth() {
			return width;
		}


		public void setWidth(float width) {
			this.width = width;
		}


		public float getHeight() {
			return height;
		}


		public void setHeight(float height) {
			this.height = height;
		}
		
		public void scaleImage(float scale){
			
			Image = Bitmap.createScaledBitmap(Image, (int)(scale*Image.getWidth()), (int)(scale*Image.getHeight()), true);
			height = Image.getHeight();
			width = Image.getWidth();
		}
		
		public void animate(int endX){
			
			animator = new ValueAnimator();
			animator.setDuration(duration);
			animator.setFloatValues(X, endX);
			animator.addUpdateListener(new AnimatorUpdateListener() {
				
				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					X = (Float)animation.getAnimatedValue();
					view.invalidate();
				}
			});
			animator.setStartDelay(wait);
			animator.start();
		}
}

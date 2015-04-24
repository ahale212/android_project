package com.example.alert;

import android.telephony.SmsManager;


public class SMS {
	
	String phoneNumber, message; 
	
	public SMS(){
	   
	}
	
	public SMS(String phoneNumber , String message){
	   this.phoneNumber = phoneNumber;
	   this.message = message;
	}
	
	public boolean sendSMS() {
		
		MyLocationListener mll = new MyLocationListener();
		
    	boolean sent =false;
    	if(message!=null){
        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage(phoneNumber, null, "Emergency at :\nhttps://maps.google.com/maps?q=["+ mll.getLatitude() + "],[" + mll.getLongitude() + "]\n\n" + message , null, null);
        sent=true;
    	}
    	return sent;
    }

}

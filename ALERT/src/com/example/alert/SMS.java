package com.example.alert;

import android.telephony.SmsManager;
/**
 * class that will enable the APP to send SMS messages 
 * @author adamhale
 *
 */

public class SMS {
	//declare vars
	String phoneNumber, message; 
	/**
	 * default constructor
	 */
	public SMS(){
	   
	}
	/**
	 * constructor with args
	 * @param phoneNumber
	 * @param message
	 */
	public SMS(String phoneNumber , String message){
	   this.phoneNumber = phoneNumber;
	   this.message = message;
	}
	/**
	 * method to send the SMS
	 * @return
	 */
	public boolean sendSMS() {
		
		MyLocationListener mll = new MyLocationListener();
		
    	boolean sent =false;
    	if(message!=null){
        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage(phoneNumber, null, "Emergency at :\nhttps://maps.google.com/maps?q=["+ mll.getLatitude() + "],[" + mll.getLongitude() + "]\n\n" + message , null, null);
        sent=true;
    	}
    	//return the var
    	return sent;
    }

}

package com.example.alert;

import android.content.Intent;

public class Email {
	
	String message;
	String[] emailAddress;
	
	public Email(){		
	}
	
	public Email(String[] emailAddress, String message){
		this.emailAddress = emailAddress;
		this.message = message;
	}
	
	public Intent sendEmail(){
		
		if(message!=null){
			
			Intent email = new Intent(android.content.Intent.ACTION_SEND);
			
			email.putExtra(android.content.Intent.EXTRA_EMAIL, emailAddress);
			email.putExtra(android.content.Intent.EXTRA_SUBJECT, "Patients in transit");
			email.setType("plain/text");
			email.putExtra(android.content.Intent.EXTRA_TEXT, message);
			email.setType("text/xml");
			return email;
		}
		return null;
	}

}

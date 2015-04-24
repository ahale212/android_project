package com.example.alert;


import java.sql.SQLException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class DetailsFragment extends Fragment {

	RelativeLayout mRelativeLayout;
	
	Button update, view, QueryDB;
	EditText title, firstName, surname, dob, nhsNum, bloodGroup, allergies;

	Body_Model bm;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mRelativeLayout = (RelativeLayout) inflater.inflate(R.layout.activity_database_details, container, false);

		
		view = (Button) mRelativeLayout.findViewById(R.id.buttonView);
		QueryDB = (Button) mRelativeLayout.findViewById(R.id.QueryDB);
		
		title = (EditText) mRelativeLayout.findViewById(R.id.editTextTitle);
		firstName = (EditText) mRelativeLayout.findViewById(R.id.editTextFirstName);
		surname = (EditText) mRelativeLayout.findViewById(R.id.editTextSurname);
		dob = (EditText) mRelativeLayout.findViewById(R.id.editTextDob);
		nhsNum = (EditText) mRelativeLayout.findViewById(R.id.editTextNhsNum);
		bloodGroup = (EditText) mRelativeLayout.findViewById(R.id.editTextBloodGroup);
		allergies = (EditText) mRelativeLayout.findViewById(R.id.editTextAllergies);
		
		view.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				updateDatabase();
				
				try {
					Intent i = new Intent("android.intent.action.HomeScreen");
					i.putExtra("rowId", bm.getPatient_rec());
					startActivity(i);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Toast.makeText(getActivity(), "could not open SQL view page", Toast.LENGTH_SHORT).show();
				}	
				
			}
		});
		
		QueryDB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//if (nhsNum!=null && isNetworkAvailable()){
					
					JDBC jdbc = new JDBC(nhsNum.toString());
					
					if(jdbc.runDB()){
						
						Toast.makeText(bm,"Patient records available",Toast.LENGTH_SHORT ).show();
						
						title.setText(jdbc.getTitle());
						firstName.setText(jdbc.getFirstName());
						surname.setText(jdbc.getSurname());
						dob.setText(jdbc.getDob());
						bloodGroup.setText(jdbc.getblood_group());
						allergies.setText(jdbc.getAllergies());
					
					} else {
						
						Toast.makeText(bm,"Patient records not available",Toast.LENGTH_SHORT ).show();
						Log.v("message", "no patient");
					}
				//}
				
			}
		});
		return mRelativeLayout;
	}
	
	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);
		bm = (Body_Model) activity;
	}
	
	
	public void updateDatabase(){
		
		ArrayList<String> patientDetails = new ArrayList<String>();
		ArrayList<Integer> bodyPartCount = new ArrayList<Integer>();
		try {
			patientDetails.add(title.getText().toString());
			patientDetails.add(firstName.getText().toString());
			patientDetails.add(surname.getText().toString());
			patientDetails.add(dob.getText().toString());
			patientDetails.add(nhsNum.getText().toString());
			patientDetails.add(bloodGroup.getText().toString());
			patientDetails.add(allergies.getText().toString());	
			
			
			bodyPartCount.add(bm.getCounthead());
			bodyPartCount.add(bm.getCountBackhead());
			bodyPartCount.add(bm.getCountneck());
			bodyPartCount.add(bm.getCountBackneck());
			bodyPartCount.add(bm.getCountchest());
			bodyPartCount.add(bm.getCountback());
			bodyPartCount.add(bm.getCountgroin());
			bodyPartCount.add(bm.getCountbum());
			bodyPartCount.add(bm.getCountleftarm());
			bodyPartCount.add(bm.getCountBackleftarm());
			bodyPartCount.add(bm.getCountrightarm());
			bodyPartCount.add(bm.getCountBackrightarm());
			bodyPartCount.add(bm.getCountrightleg());
			bodyPartCount.add(bm.getCountBackrightleg());
			bodyPartCount.add(bm.getCountleftleg());
			bodyPartCount.add(bm.getCountBackleftleg());
			
			
			MainDetails entry = new MainDetails(bm);
			entry.open();
			entry.createEntry(patientDetails,bodyPartCount, bm.getPatient_rec());
			entry.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Toast.makeText(getActivity(), "error in writing to dataBase", Toast.LENGTH_SHORT).show();
		}
		
	}
}
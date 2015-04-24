package com.example.alert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class HomeScreen extends ActionBarActivity {

	int patient_rec;
	int TopId;
	boolean messageSent;
	private String HOSPITAL_NUMBER;
	private String [] EMAIL_ADDRESS;
	private int[] BUTTON_IDS = {R.id.BtAddPatient, R.id.BtClearPatient, R.id.BtAlertOnCall, R.id.BtAlertHospital};

	MainDetails dataBase;

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
	ArrayList<Integer> keys;
	ArrayList<String> Details;

	private Button addPatient, clearPatients, alertOnCall, alertHospital;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_patient);

		ExpandableListView();
		
		
		
		addPatient = (Button) findViewById(R.id.BtAddPatient);
		
	
		
		addPatient.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent openDetailsPage = new Intent(
						"android.intent.action.Body_Model");
				openDetailsPage.putExtra("P_Id", patient_rec);
				try {
					startActivity(openDetailsPage);
				} catch (Exception e) {
					Toast.makeText(getBaseContext(), "can't find class",
							Toast.LENGTH_SHORT).show();
				}

			}
		});

		clearPatients = (Button) findViewById(R.id.BtClearPatient);

		
		clearPatients.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if(ConfirmBox("Clear Patient Roster", "Are you sure you want to clear the active patient roster?")){
					ClearRoster();
					updateExpListView();
				};
			}
		});

		alertOnCall = (Button) findViewById(R.id.BtAlertOnCall);
		
		alertOnCall.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				SMS sms = new SMS(HOSPITAL_NUMBER, createMessage());
				
				if(sms.sendSMS()){
					Toast.makeText(getApplicationContext(), "Message sent" , Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(getApplicationContext(), "Message not sent" , Toast.LENGTH_LONG).show();
				}
				
				startActivity(openMaps());

			}
		});
		
		alertHospital = (Button) findViewById(R.id.BtAlertHospital);
		
		alertHospital.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(isNetworkAvailable()){
				Email email = new Email(EMAIL_ADDRESS, createMessage());
				
				startActivity(email.sendEmail());
				
				Toast.makeText(getApplicationContext(), "Message sent", Toast.LENGTH_LONG).show();
				} else {
					if(ConfirmBox("Message not sent","No internet connection available, proceed to maps anyway?")){
						openMaps();
					};
				}
			}
		});
		
		
		/*for(int IDs : BUTTON_IDS){
			buttonImgResize(IDs);
		}*/
		
	}

	
	private Intent openMaps(){
		
		Intent openMaps = new Intent(HomeScreen.this, Maps.class);
		return openMaps;
		
	}
	
	private void ExpandableListView() {

		int rowCount = getRowCount();

		if (rowCount < 1) {
			patient_rec = 1;
		} else {
			try {
				dataBase.open();
				TopId = dataBase.getTopId();
				dataBase.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			patient_rec = TopId + 1;
		}

		if (rowCount > 0) {

			updateExpListView();

		}
	}

	private int getRowCount() {
		int rowCount = 0;
		dataBase = new MainDetails(this);
		try {
			dataBase.open();

			rowCount = dataBase.getRowCount();

			dataBase.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowCount;
	}

	private void prepareListData() {

		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		MainDetails dataBase = new MainDetails(this);

		try {
			dataBase.open();

			keys = dataBase.getDatakeys();

			for (int i = 0; i < keys.size(); i++) {

				List<String> OtherDetails = new ArrayList<String>();

				Details = dataBase.getDataDetails(keys.get(i));

				listDataHeader.add(Details.get(2) + " " + Details.get(3));

				for (int j = 2; j < Details.size(); j++) {
					OtherDetails.add(Details.get(j));
				}

				listDataChild.put(listDataHeader.get(i), OtherDetails);
			}

		} catch (Exception e) {
			Log.v("DataBase Exception",
					"Error in dataBase connection at home screen");
		} finally {
			dataBase.close();
		}

	}

	private void updateExpListView() {

		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.expandablePatientList);

		if(getRowCount()<1){
			expListView.setVisibility(View.INVISIBLE);
		} else {
			expListView.setVisibility(View.VISIBLE);
		}
		
		// preparing list data
		prepareListData();

		listAdapter = new ExpandableListAdapter(this, listDataHeader,
				listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);

		expListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// TODO Auto-generated method stub
				return false;
			}
		});

		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				// TODO Auto-generated method stub

			}
		});

		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				// TODO Auto-generated method stub

			}
		});

		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				return false;
			}
		});
	}

	private boolean ConfirmBox(String title, String message) {
		
		
		 messageSent = false;
		
		new AlertDialog.Builder(this)
		.setIcon(android.R.drawable.ic_dialog_alert)
		.setTitle(title)
		.setMessage( message)
		.setPositiveButton("Yes",
				new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog,
					int which) {
				messageSent = true;
			}

		}).setNegativeButton("No", null).show();
		
		return messageSent;
	}
	
	private void ClearRoster() {

		MainDetails db = new MainDetails(this);
		try {
			db.open();
			db.deleteAll();
			db.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.v("message", "problem in home screen, clearRoster");
		}

	}
	
	
	private String createMessage() {
    	
    	String message="";
    	String[] fieldNames = {"Title : ", "Forename : ","Surname : ","DOB : ","NHS\nNumber : ","BloodType : ","Allergies : "};
    	
    	
    	for (int i = 0; i<keys.size();i++){
    		
    		message+="Patient " + (i+1) + "\n";
    		
    		for(int j = 1; j< Details.size();j++){
    			message+= "" + fieldNames[j-1] + Details.get(j) + "\n";
    		}
    		
    		message+="\n";
    	}
    	
    	return message;
    }

	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	
private void buttonImgResize(int resourceID){
		
		Bitmap originalBitmap = null;
		Bitmap scaledBitmap = null;
		
		switch(resourceID){
		
		case R.id.BtAddPatient : originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.top_right);
		scaledBitmap=Bitmap.createScaledBitmap(originalBitmap, addPatient.getLayoutParams().width, addPatient.getLayoutParams().height, true);
		addPatient.setBackgroundDrawable(new BitmapDrawable(scaledBitmap));
			break;
		case R.id.BtClearPatient : originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.top_left);
		originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.top_left);
		scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, clearPatients.getLayoutParams().width, clearPatients.getLayoutParams().height, true);
		clearPatients.setBackgroundDrawable(new BitmapDrawable(scaledBitmap));
			break;
		case R.id.BtAlertOnCall : originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bottom_right);
		originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bottom_right);
		scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, alertOnCall.getLayoutParams().width, alertOnCall.getLayoutParams().height, true);
		alertOnCall.setBackgroundDrawable(new BitmapDrawable(scaledBitmap));
			break;
		case R.id.BtAlertHospital : originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bottom_left);
		originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bottom_left);
		scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, alertHospital.getLayoutParams().width, alertHospital.getLayoutParams().height, true);
		alertHospital.setBackgroundDrawable(new BitmapDrawable(scaledBitmap));
		}
		
	}
		
	public String getHOSPITAL_NUMBER() {
		return HOSPITAL_NUMBER;
	}
	
	
	public void setHOSPITAL_NUMBER(String hOSPITAL_NUMBER) {
		HOSPITAL_NUMBER = hOSPITAL_NUMBER;
	}
	
	
	public String[] getEMAIL_ADDRESS() {
		//TODO get from database
		return EMAIL_ADDRESS;
	}
	
	
	public void setEMAIL_ADDRESS(String[] eMAIL_ADDRESS) {
		//TODO get from database
		EMAIL_ADDRESS = eMAIL_ADDRESS;
	}
}

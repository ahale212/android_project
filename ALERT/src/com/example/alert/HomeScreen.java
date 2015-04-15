package com.example.alert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

	MainDetails dataBase;

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;

	private Button addPatient, clearPatients;

	public HomeScreen() {

	}

	/*
	 * public HomeScreen(ArrayList<String> patient) { listDataHeader = new
	 * ArrayList<String>(); listDataHeader.add(patient.get(1)); }
	 */

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

				ConfirmBox();
			}
		});

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

		ArrayList<Integer> keys;
		ArrayList<String> Details;

		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		MainDetails dataBase = new MainDetails(this);

		try {
			dataBase.open();

			keys = dataBase.getDatakeys();

			for (int i = 0; i < keys.size(); i++) {

				List<String> OtherDetails = new ArrayList<String>();

				Details = dataBase.getDataDetails(keys.get(i));

				listDataHeader.add(Details.get(1) + " " + Details.get(2));

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

	private void ConfirmBox() {

		new AlertDialog.Builder(this)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("Clear Patient Roster")
				.setMessage(
						"Are you sure you want to clear the active patient roster?")
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								ClearRoster();
								updateExpListView();
							}

						}).setNegativeButton("No", null).show();
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
}

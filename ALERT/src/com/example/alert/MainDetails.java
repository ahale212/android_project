package com.example.alert;

import java.sql.SQLException;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class MainDetails {

	public static final String KEY_ROWID = "_id";
	public static final String KEY_TITLE = "title";
	public static final String KEY_FIRSTNAME = "firstName";
	public static final String KEY_SURNAME = "surname";
	public static final String KEY_DOB = "dob";
	public static final String KEY_NHSNUM = "nhsNum";
	public static final String KEY_MEDICALHISTORY = "medical_history";
	public static final String KEY_ALLERGIES = "allergies";
	
	public static final String KEY_counthead 			= "counthead";
	public static final String KEY_countBackhead 		= "countBackhead";
	public static final String KEY_countneck 			= "countneck";
	public static final String KEY_countBackneck 		= "countBackneck";
	public static final String KEY_countchest 			= "countchest";
	public static final String KEY_countback 			= "countback";
	public static final String KEY_countgroin 			= "countgroin";
	public static final String KEY_countbum 			= "countbum";
	public static final String KEY_countleftarm 		= "countleftarm";
	public static final String KEY_countBackleftarm 	= "countBackleftarm";
	public static final String KEY_countrightarm 		= "countrightarm";
	public static final String KEY_countBackrightarm	= "countBackrightarm";
	public static final String KEY_countrightleg		= "countrightleg";
	public static final String KEY_countBackrightleg 	= "countBackrightleg";
	public static final String KEY_countleftleg 		= "countleftleg";
	public static final String KEY_countBackleftleg 	= "countBackleftleg";

	private static final String DATABASE_NAME = "MainDetailsDb";
	private static final String DATABASE_TABLE = "peopleTable";
	private static final int DATABASE_VERSION = 1;
	
	private String[] keys_Details = {KEY_ROWID,KEY_TITLE,KEY_FIRSTNAME,KEY_SURNAME,KEY_DOB,KEY_NHSNUM,KEY_MEDICALHISTORY,KEY_ALLERGIES};
	private String[] keys_Body = {KEY_ROWID,KEY_counthead,KEY_countBackhead,KEY_countneck,KEY_countBackneck,KEY_countchest,KEY_countback,KEY_countgroin,
			KEY_countbum,KEY_countleftarm,KEY_countBackleftarm,KEY_countrightarm,KEY_countBackrightarm,KEY_countrightleg,KEY_countBackrightleg,
			KEY_countleftleg,KEY_countBackleftleg};
	
	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;

	private static class DbHelper extends SQLiteOpenHelper {

		
		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);

			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			// + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			
			try{
			//db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			db.execSQL("CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE + " ( "  
					
					+ KEY_ROWID 			+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ KEY_TITLE 			+ " TEXT NOT NULL, " 
					+ KEY_FIRSTNAME 		+ " TEXT NOT NULL, "
					+ KEY_SURNAME 			+ " TEXT NOT NULL, " 
					+ KEY_DOB 				+ " TEXT NOT NULL, " 
					+ KEY_NHSNUM 			+ " TEXT NOT NULL, "
					+ KEY_MEDICALHISTORY 	+ " TEXT NOT NULL, "
					+ KEY_ALLERGIES 		+ " TEXT NOT NULL, "
					
					+ KEY_counthead 		+ " INTEGER NOT NULL, "
					+ KEY_countBackhead 	+ " INTEGER NOT NULL, "
					+ KEY_countneck 		+ " INTEGER NOT NULL, "
					+ KEY_countBackneck 	+ " INTEGER NOT NULL, "
					+ KEY_countchest 		+ " INTEGER NOT NULL, "
					+ KEY_countback 		+ " INTEGER NOT NULL, "
					+ KEY_countgroin 		+ " INTEGER NOT NULL, "
					+ KEY_countbum 		    + " INTEGER NOT NULL, "
					+ KEY_countleftarm 	    + " INTEGER NOT NULL, "
					+ KEY_countBackleftarm  + " INTEGER NOT NULL, "
					+ KEY_countrightarm 	+ " INTEGER NOT NULL, "
					+ KEY_countBackrightarm + " INTEGER NOT NULL, "
					+ KEY_countrightleg	    + " INTEGER NOT NULL, "
					+ KEY_countBackrightleg + " INTEGER NOT NULL, "
					+ KEY_countleftleg 	    + " INTEGER NOT NULL, "
					+ KEY_countBackleftleg  + " INTEGER NOT NULL);"
					);
			} catch (Exception e){
				
				Log.v("MyApp", "exception in forming the database");
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
	}

	public MainDetails(Context c) {
		ourContext = c;
	}

	public MainDetails open() throws SQLException {
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		ourHelper.close();
	}
	
	public boolean deleteEntry(int key){
		String keyVal =""+key;
		
		return ourDatabase.delete(DATABASE_TABLE, KEY_ROWID +" = " + keyVal, null) > 0;
	}

	public long createEntry(ArrayList<String> patientDetails, ArrayList<Integer> bodyPartCounts, int key_ID) {
		
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		
		Boolean update = false;
		
		Cursor cursor = ourDatabase.query(DATABASE_TABLE, keys_Details, null, null,
				null, null, null);
		
		
			cv.put(KEY_ROWID, key_ID);
			
			for(int i = 0; i<patientDetails.size(); i++){
				cv.put(keys_Details[i+1], patientDetails.get(i));
			}
			
			for(int i = 0; i<bodyPartCounts.size(); i++){
				cv.put(keys_Body[i+1], bodyPartCounts.get(i));
			}
			
			try {
				for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
					
					if (cursor.getInt(cursor.getColumnIndex(KEY_ROWID)) == key_ID){
						update =true;
					} else {
						update = false;
					}
				}
			} catch (Exception e){
				Log.v("SQL PROBLEMS", "cursor in main details");
			}
		if (update){
			return ourDatabase.update(DATABASE_TABLE, cv, KEY_ROWID + " = " + key_ID, null );

		} else {
			return ourDatabase.insert(DATABASE_TABLE, null, cv);
		}
	}
	
	

	public ArrayList<String> getDataDetails(int key_ID) {
		
		ArrayList<String> result = new ArrayList<String>();
			
		// CHECK THIS FOR QUERY
		Cursor c = ourDatabase.query(DATABASE_TABLE, keys_Details, null, null,
				null, null, null);
		
		try {
			
			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				
				if (c.getInt(c.getColumnIndex(KEY_ROWID)) == key_ID){
				for (String data : keys_Details) {	
					
					@SuppressWarnings("unused")
					String var = c.getString(c.getColumnIndex(data));
					result.add(c.getString(c.getColumnIndex(data)));
					}
				}
			}
	
		} catch (Exception e) {

			Toast.makeText(ourContext, "there was a problem with creating details results array", Toast.LENGTH_SHORT).show();
		}
		return result;
	}

	public ArrayList<Integer> getDataBodyCounts(int key_ID) {
		
		ArrayList<Integer> result = new ArrayList<Integer>();
			
		// CHECK THIS FOR QUERY
		Cursor c = ourDatabase.query(DATABASE_TABLE, keys_Body, null, null,
				null, null, null);
		try {
			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

				if (c.getInt(c.getColumnIndex(KEY_ROWID)) == key_ID) {

					for (String data : keys_Body) {
						result.add(c.getInt(c.getColumnIndex(data)));
					}
				}
			}
		} catch (Exception e) {

			Toast.makeText(ourContext,"there was a problem with creating body count results array",Toast.LENGTH_SHORT).show();
		}
		return result;
	}
	
	public int getTopId() {
		
	    String countQuery = "SELECT MAX(" + KEY_ROWID +") FROM " + DATABASE_TABLE;
	    
	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    cursor.moveToFirst();
	    int maxId = cursor.getInt(0);
	    cursor.close();
	    return maxId;
	}

	public int getRowCount() {
		
	    String countQuery = "SELECT  * FROM " + DATABASE_TABLE;
	    
	    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
	    cursor.moveToFirst();
	    int count = cursor.getCount();
	    cursor.close();
	    return count;
	}
	
	public ArrayList<Integer> getDatakeys() {
		
		ArrayList<Integer> result = new ArrayList<Integer>();
			
		// CHECK THIS FOR QUERY
		Cursor c = ourDatabase.query(DATABASE_TABLE, keys_Body, null, null,
				null, null, null);
		try {
			
			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				
				result.add(c.getInt(c.getColumnIndex(KEY_ROWID)));
				
				}
		} catch (Exception e) {

			Toast.makeText(ourContext,"there was a problem with creating body count results array",Toast.LENGTH_SHORT).show();
		}
		return result;
	}


}

package com.example.alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import android.util.Log;



public class JDBC {
	/**
	 * main method to query for a database return
	 * 
	 * @param args
	 */
	
	private String title, firstName, surname,NHS_NUMBER, dob, blood_group, allergies;
	
	public JDBC(){
		
	}
	
	public JDBC(String NHS_NUMBER){
		this.NHS_NUMBER = NHS_NUMBER;
	}
	
	public boolean runDB() {

		boolean NHS_number_exists;
		NHS_number_exists=false;
		
		// establish connection to mySQl
		String url = "jdbc:mysql://web2.eeecs.qub.ac.uk/40058483";
		Connection con;
		PreparedStatement findPatients;
		// declare var
		String findPatientsString = "";

		// Loading the driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// catch exceptions
		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			// get error message
			System.err.println(e.getMessage());
		}

		try {
			// connection to database using login name and password
			con = DriverManager.getConnection(url, "40058483", "VPK7789");
			// initialise prepared statement
			findPatients = con.prepareStatement(findPatientsString);
			// execute query
			ResultSet rs = findPatients.executeQuery("SELECT title, first_name, last_name, date_of_birth, blood_group, known_allergies FROM patients WHERE NHS_number = 'NHS_NUMBER'");
			// display results method - this can be changed/removed as needed -
			// only used this for the purpose of testing it worked
			//displayResults(rs1);
			
			rs.last();
			
			if(rs.getRow()==0){
				NHS_number_exists = false;
				Log.v("message", "no results set");
			} else {
				
				Log.v("message", "results set present");
				NHS_number_exists = true;
				rs.beforeFirst();
				
				JDBC jdbc = new JDBC();
				
				jdbc.setTitle(rs.getString("title"));
				jdbc.setFirstName(rs.getNString("first_name"));
				jdbc.setSurname(rs.getNString("last_name"));
				jdbc.setDob(rs.getNString("date_of_birth"));
				jdbc.setblood_group(rs.getNString("blood_group"));
				jdbc.setAllergies(rs.getNString("known_allergies"));	
			}
			// start of catch block
		} catch (SQLException ex) {
			// print out the error
			System.err.println("SQLException: " + ex.getMessage());

		}// end of catch
		
		return NHS_number_exists;
	}// end of method
	//public static void main(String[] args) {
		//runDB();


	public String getNHS_NUMBER() {
		return NHS_NUMBER;
	}


	public void setNHS_NUMBER(String nHS_NUMBER) {
		NHS_NUMBER = nHS_NUMBER;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getblood_group() {
		return blood_group;
	}

	public void setblood_group(String blood_group) {
		this.blood_group = blood_group;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}
		
	//}
	

}// end of class


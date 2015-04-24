package com.example.alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import android.util.Log;

/**
 * JDBC class so the user can search via NHS number 
 * @author adamhale
 *
 */

public class JDBC {
	
	private static final String url = "jdbc:mysql://web2.eeecs.qub.ac.uk/40058483";
    private static final String user = "40058483";
    private static final String pass = "VPK7789";
Patients patients = new Patients();
String NHS_NUMBER = patients.getNHS_NUMBER();
	
	// establish connection to mySQl
	//String url = "jdbc:mysql://web2.eeecs.qub.ac.uk/40058483";
	Connection con;
	PreparedStatement findPatients, findPatientsWithNHSNum;
	// declare vars
	String findPatientsString = "";
	String findPatientWithNHSNumString = "";

	public void JDBC(String url, String user, String password)
			throws SQLException, ClassNotFoundException {
		// driver name
		Class.forName("com.mysql.jdbc.Driver");
		// connection to database using login name and password
		con = DriverManager.getConnection(url, "40058483", "VPK7789");

	}// end of DetailsFragment method

	/**
	 * method to shut down connection
	 * Throws exception
	 * @throws SQLException
	 */
	public void shutdown() throws SQLException {
		if (con != null) {
			con.close();
		}
	}//end of shutdown method
	
	 public void testDB() {
	    	//TextView tv = (TextView)this.findViewById(R.id.text_view);
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection con = DriverManager.getConnection(url, user, pass);
	            /* System.out.println("Databaseection success"); */

	            String result = "Database connection success\n";
	            java.sql.Statement st = con.createStatement();
	            ResultSet rs = st.executeQuery("SELECT * FROM patients WHERE NHS_number = '" + NHS_NUMBER + "'");
	            java.sql.ResultSetMetaData rsmd = rs.getMetaData();

	            while(rs.next()) {
	            	Patients patients = new Patients();
					
					patients.setTitle(rs.getString("title"));
					patients.setFirstName(rs.getString("first_name"));
					patients.setSurname(rs.getString("last_name"));
					patients.setDob(rs.getString("date_of_birth"));
					patients.setblood_group(rs.getString("blood_group"));
					patients.setAllergies(rs.getString("known_allergies"));
	            }
	            //tv.setText(result);
	        }
	        catch(Exception e) {
	            e.printStackTrace();
	        }
	            //tv.setText(e.toString());
	        }   
	/**
	 * method that when called will run the db query
	 * @return 
	 * @return
	 */
	
	
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
			ResultSet rs = findPatients.executeQuery("SELECT * FROM patients WHERE NHS_number = '" + NHS_NUMBER + "'");
			//set cursor to last position
			rs.last();
			
			if(rs.getRow()==0){
				NHS_number_exists = false;
				Log.v("ERROROROROEROEROERO", "no results set");
			} else {
				
				Log.v("EROORO2329239239239", "results set present");
				NHS_number_exists = true;
				rs.first();
				
				Patients patients = new Patients();
				
				patients.setTitle(rs.getString("title"));
				patients.setFirstName(rs.getString("first_name"));
				patients.setSurname(rs.getString("last_name"));
				patients.setDob(rs.getString("date_of_birth"));
				patients.setblood_group(rs.getString("blood_group"));
				patients.setAllergies(rs.getString("known_allergies"));	
			}
			// start of catch block
		} catch (SQLException ex) {
			// print out the error
			System.err.println("SQLException: " + ex.getMessage());

		}// end of catch
		
		return NHS_number_exists;
	}// end of method
	

		
	//}
	

}// end of class


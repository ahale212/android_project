package com.example.alert;
/**
 * class used for getter and setter details for patients
 * @author adamhale
 *
 */
public class Patients {
	private String title, firstName, surname, NHS_NUMBER, dob, blood_group, allergies;
	
	
	
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
	
	
	
}

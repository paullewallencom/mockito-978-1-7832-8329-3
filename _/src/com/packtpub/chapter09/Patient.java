package com.packtpub.chapter09;

import java.util.Date;
import java.util.List;

public class Patient  extends BaseObject{

	private static final long serialVersionUID = 1L;
	private String firstName,  middleName,  lastName;
    private Date dateOfBirth;
    private Sex sex;
	
	public Patient registerName(String firstName, String middleName, String lastName){
		this.firstName = firstName;
		this.middleName= middleName;
		this.lastName = lastName;
		return this;
	}

	
	public Patient registerDOB(Date dob){
		this.dateOfBirth= dob;
		return this;
	}
	
	public Patient registerSex(Sex sex){
		this.sex= sex;
		return this;
	}
	
	
	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public Patient(Long objectId) {
		super(objectId);
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public Sex getSex() {
		return sex;
	}
	
	public List<Encounter> getAllEncounters(){
		return DataAccessFacade.fecthEncountersFor(getObjectId());
	}
}

package com.packtpub.chapter09;

import java.util.Date;
import java.util.List;

public class Encounter extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Encounter(Long objectId) {
		super(objectId);
	}

	protected Encounter(Long objectId, boolean isInit ) {
		super(objectId, isInit);
	}
	
	private String encounterId;
	private Date startDate;
	private Date stopDate;
	private FinancialSummary financialSummary;
	private Patient patient;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public FinancialSummary getFinancialSummary() {
		return financialSummary;
	}

	public void setFinancialSummary(FinancialSummary financialSummary) {
		this.financialSummary = financialSummary;
	}

	public String getEncounterId() {
		return encounterId;
	}

	public void setEncounterId(String encounterId) {
		this.encounterId = encounterId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	public void addCharge(EncounterCharges aCharge){
		DataAccessFacade.addChargeFor(this,aCharge);
	}

	
	public List<EncounterCharges> getAllCharges(){
		return DataAccessFacade.getChargesFor(getObjectId());
	}
}

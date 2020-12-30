package com.packtpub.chapter09;

import java.util.Date;

public class FinancialSummary extends BaseObject {

	public FinancialSummary(Long objectId) {
		super(objectId);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Encounter encounter;
	private double amountDue;
	private Date lastPaymentDate;

	public Encounter getEncounter() {
		return encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

	public double getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
	}

	public Date getLastPaymentDate() {
		return lastPaymentDate;
	}

	public void setLastPaymentDate(Date lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}

}

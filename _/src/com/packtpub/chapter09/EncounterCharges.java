package com.packtpub.chapter09;

public class EncounterCharges extends BaseObject {

	public EncounterCharges(Long objectId) {
		super(objectId);
	}

	private Encounter encounter;
	private String description;
	private double charge;
	public Encounter getEncounter() {
		return encounter;
	}
	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getCharge() {
		return charge;
	}
	public void setCharge(double charge) {
		this.charge = charge;
	}
}

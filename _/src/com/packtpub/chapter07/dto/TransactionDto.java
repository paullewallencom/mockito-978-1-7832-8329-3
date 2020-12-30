package com.packtpub.chapter07.dto;

public class TransactionDto {

	private String targetId;
	private String targetPayPalId;
	private double amount;
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTargetId() {
		return targetId;
	}

	public String getTargetPayPalId() {
		return targetPayPalId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public void setTargetPayPalId(String targetPayPalId) {
		this.targetPayPalId = targetPayPalId;
	}

	

}

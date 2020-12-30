package com.packtpub.chapter07.dto;

public class PaymentAdviceDto {
	private final double amount;
	private final String targetPayPalId;
	private final String desc;

	public PaymentAdviceDto(double amount, String targetPayPalId, String desc) {
		super();
		this.amount = amount;
		this.targetPayPalId = targetPayPalId;
		this.desc = desc;
	}

	public double getAmount() {
		return amount;
	}

	public String getTargetPayPalId() {
		return targetPayPalId;
	}

	public String getDesc() {
		return desc;
	}

}

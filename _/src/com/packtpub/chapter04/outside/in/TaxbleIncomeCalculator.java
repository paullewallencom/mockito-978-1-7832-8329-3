package com.packtpub.chapter04.outside.in;

public interface TaxbleIncomeCalculator {
 double calculate(double totalIncome, double homeLoanInterest,
			double homeLoanPrincipal, double providentFundSavings,
			double lifeInsurancePremium);
}

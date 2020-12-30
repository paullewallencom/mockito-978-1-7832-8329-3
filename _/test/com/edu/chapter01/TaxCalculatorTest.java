package com.edu.chapter01;

import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

public class TaxCalculatorTest {

	
	@Test 
	public void when_income_less_than_5Lacs_then_deducts_10_percent_tax() {
		TaxCalculator taxCalculator = new TaxCalculator();
		double payableTax = taxCalculator.calculate(400000.00);
		assertTrue(40000 == payableTax);

		payableTax = taxCalculator.calculate(0);
		assertTrue(0 == payableTax);

		payableTax = taxCalculator.calculate(500000.00);
		assertTrue(50000 == payableTax);

	}

	
	@Test
	public void when_income_between_5lacs_and_10lacs_then_deducts_fifty_thousand_plus_20_percent_above_5lacs() {
		TaxCalculator taxCalculator = new TaxCalculator();
		double payableTax = taxCalculator.calculate(800000.00);
		double expectedTaxForFirstFiveHundredThousand = 50000;
		double expectedTaxForReminder = 60000;
		double expectedTotalTax = expectedTaxForFirstFiveHundredThousand
				+ expectedTaxForReminder;

		assertTrue(expectedTotalTax == payableTax);
	}

	@Test
	public void when_income_more_than_10lacs_then_deducts_150000_plus_30_percent_above_10lacs() {
		TaxCalculator taxCalculator = new TaxCalculator();
		double payableTax = taxCalculator.calculate(1200000.00);
		assertTrue((150000 + 60000) == payableTax);
	}
}

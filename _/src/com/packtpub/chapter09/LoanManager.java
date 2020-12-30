package com.packtpub.chapter09;

public class LoanManager {

   private final LoanCalculator loanCalculator;
   public LoanManager(){
	   loanCalculator = new LoanCalculator();
   }

   public LoanManager(LoanCalculator dependency){
	   loanCalculator = dependency;
   }
   
	public void calculateMaxLoan(Person person){
		loanCalculator.calculate(person);
	}
}

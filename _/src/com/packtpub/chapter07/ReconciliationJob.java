package com.packtpub.chapter07;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.packtpub.chapter07.dao.FinancialTransactionDAO;
import com.packtpub.chapter07.dao.MembershipDAO;
import com.packtpub.chapter07.dto.MembershipStatusDto;
import com.packtpub.chapter07.dto.PaymentAdviceDto;
import com.packtpub.chapter07.dto.TransactionDto;

public class ReconciliationJob {
	private final FinancialTransactionDAO financialTxDAO;
	private final MembershipDAO membershipDAO;
	private final PayPalFacade payPalFacade;

	public ReconciliationJob(FinancialTransactionDAO financialTxDAO,
			MembershipDAO membershipDAO, PayPalFacade payPalFacade) {
		super();
		this.financialTxDAO = financialTxDAO;
		this.membershipDAO = membershipDAO;
		this.payPalFacade = payPalFacade;
	}

	public int reconcile2() {
		List<TransactionDto> unSettledTxs = financialTxDAO
				.retrieveUnSettledTransactions();
		
		for (TransactionDto transactionDto : unSettledTxs) {
		   MembershipStatusDto membership = membershipDAO
				.getStatusFor(transactionDto.getTargetId());
		   payPalFacade.sendAdvice(new PaymentAdviceDto(0.00,
				   transactionDto.getTargetPayPalId(),"Post payment for developer "+ transactionDto.getTargetId()));
		}
		
		return unSettledTxs.size();
	}

	public int reconcile() {
		List<TransactionDto> unSettledTxs = financialTxDAO
				.retrieveUnSettledTransactions();
		Map<String, List<TransactionDto>> developerTxMap = new LinkedHashMap<String, List<TransactionDto>>();
		for (TransactionDto transactionDto : unSettledTxs) {
			List<TransactionDto> transactions = developerTxMap
					.get(transactionDto.getTargetId());
			if (transactions == null) {
				transactions = new ArrayList<TransactionDto>();
			}
			transactions.add(transactionDto);
			developerTxMap.put(transactionDto.getTargetId(), transactions);

		}
		for (String developerId : developerTxMap.keySet()) {
			MembershipStatusDto membership = membershipDAO
					.getStatusFor(developerId);
			String payPalId = null;
			double totalTxAmount = 0.00;
			for (TransactionDto tx : developerTxMap.get(developerId)) {
				totalTxAmount += tx.getAmount();
				payPalId = tx.getTargetPayPalId();
			}
			double payableAmount = totalTxAmount - totalTxAmount
					* membership.getDeductable();
			payPalFacade.sendAdvice(new PaymentAdviceDto(payableAmount,
					payPalId, null));
		}
		return unSettledTxs.size();
	}

}

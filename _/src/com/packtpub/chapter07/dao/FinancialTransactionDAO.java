package com.packtpub.chapter07.dao;

import java.util.List;

import com.packtpub.chapter07.dto.TransactionDto;

public interface FinancialTransactionDAO {

	List<TransactionDto> retrieveUnSettledTransactions();

}

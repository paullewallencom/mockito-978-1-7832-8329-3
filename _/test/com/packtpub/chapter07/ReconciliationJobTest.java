package com.packtpub.chapter07;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.Times;

import com.packtpub.chapter07.dao.FinancialTransactionDAO;
import com.packtpub.chapter07.dao.MembershipDAO;
import com.packtpub.chapter07.dto.MembershipStatusDto;
import com.packtpub.chapter07.dto.PaymentAdviceDto;
import com.packtpub.chapter07.dto.TransactionDto;

public class ReconciliationJobTest {

	ReconciliationJob job;
	@Mock
	FinancialTransactionDAO financialTransactionDAO;
	@Mock
	MembershipDAO membershipDAO;
	@Mock
	PayPalFacade payPalFacade;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		job = new ReconciliationJob(financialTransactionDAO, membershipDAO,
				payPalFacade);
		MembershipStatusDto basicMembership = new MembershipStatusDto();
		basicMembership.setDeductable(.30);
		when(membershipDAO.getStatusFor(anyString())).thenReturn(
				basicMembership);
	}

	@Test
	public void when_no_Transaction_To_Process_Job_RETURNS_Processing_Count_Zero()
			throws Exception {
		assertEquals(0, job.reconcile());
	}

	@Test
	public void reconcile_returns_Transaction_count() throws Exception {
		List<TransactionDto> singleTxList = new ArrayList<TransactionDto>();
		TransactionDto transactionDto = new TransactionDto();
		transactionDto.setAmount(100);
		singleTxList.add(transactionDto);
		when(financialTransactionDAO.retrieveUnSettledTransactions())
				.thenReturn(singleTxList);
		assertEquals(1, job.reconcile());
	}

	@Test
	public void when_transaction_exists_Then_membership_details_is_retrieved_for_the_developer()
			throws Exception {
		List<TransactionDto> singleTxList = new ArrayList<TransactionDto>();
		TransactionDto transactionDto = new TransactionDto();
		transactionDto.setTargetId("DEV001");
		singleTxList.add(transactionDto);
		when(financialTransactionDAO.retrieveUnSettledTransactions())
				.thenReturn(singleTxList);
		assertEquals(1, job.reconcile());
		verify(membershipDAO).getStatusFor(anyString());
	}

	@Test
	public void when_transactions_exist_then_membership_details_is_retrieved_for_each_developer()
			throws Exception {
		List<TransactionDto> multipleTxs = new ArrayList<TransactionDto>();
		TransactionDto johnsTransaction = new TransactionDto();
		String johnsDeveloperId = "john001";
		johnsTransaction.setTargetId(johnsDeveloperId);

		TransactionDto bobsTransaction = new TransactionDto();
		String bobsDeveloperId = "bob999";
		bobsTransaction.setTargetId(bobsDeveloperId);

		multipleTxs.add(johnsTransaction);
		multipleTxs.add(bobsTransaction);

		when(financialTransactionDAO.retrieveUnSettledTransactions())
				.thenReturn(multipleTxs);
		assertEquals(2, job.reconcile());
		ArgumentCaptor<String> argCaptor = ArgumentCaptor
				.forClass(String.class);

		verify(membershipDAO, new Times(2)).getStatusFor(argCaptor.capture());
		List<String> passedValues = argCaptor.getAllValues();
		assertEquals(johnsDeveloperId, passedValues.get(0));
		assertEquals(bobsDeveloperId, passedValues.get(1));

	}

	@Test
	public void when_transaction_exists_Then_sends_Payble_TO_PayPal()
			throws Exception {
		List<TransactionDto> davidsTransactionList = new ArrayList<TransactionDto>();

		String davidsDeveloperId = "dev999";
		String davidsPayPalId = "david@paypal.com";
		double davidsSuperMarioGamePrice = 100.00;

		davidsTransactionList.add(createTxDto(davidsDeveloperId,
				davidsPayPalId, davidsSuperMarioGamePrice));
		when(financialTransactionDAO.retrieveUnSettledTransactions())
				.thenReturn(davidsTransactionList);
		assertEquals(1, job.reconcile());
		verify(payPalFacade).sendAdvice(isA(PaymentAdviceDto.class));
	}

	@Test
	public void calculates_payable() throws Exception {
		List<TransactionDto> ronaldosTransactions = new ArrayList<TransactionDto>();
		String ronaldosDeveloperId = "ronaldo007";
		String ronaldosPayPalId = "Ronaldo@RealMdrid.com";
		double ronaldosSoccerFee = 100.00;
		
		ronaldosTransactions.add(createTxDto(ronaldosDeveloperId,
				ronaldosPayPalId, ronaldosSoccerFee));
		
		when(financialTransactionDAO.retrieveUnSettledTransactions())
				.thenReturn(ronaldosTransactions);

		assertEquals(1, job.reconcile());

		ArgumentCaptor<PaymentAdviceDto> calculatedAdvice = 
				ArgumentCaptor.forClass(PaymentAdviceDto.class);
		
		verify(payPalFacade).sendAdvice(calculatedAdvice.capture());
		
		assertTrue(70.00 == calculatedAdvice.getValue().getAmount());
	}

	@Test
	public void calculates_payable_with_multiple_Transaction() throws Exception {
		List<TransactionDto> transactionList = new ArrayList<TransactionDto>();
		String johnsDeveloperId = "john001";
		String johnsPayPalId = "john@gmail.com";
		double johnsGameFee = 200;
		
		transactionList.add(createTxDto(johnsDeveloperId, johnsPayPalId, johnsGameFee));
		String davesDeveloperId = "dave888";
		String davesPayPalId = "IamDave009@yahoo.co.uk";
		int davesGameFee = 150;
		
		transactionList.add(createTxDto(davesDeveloperId, davesPayPalId, davesGameFee));

		when(financialTransactionDAO.retrieveUnSettledTransactions())
				.thenReturn(transactionList);

		when(membershipDAO.getStatusFor(eq(johnsDeveloperId))).
		     thenReturn(memberShip(.15));
		when(membershipDAO.getStatusFor(eq(davesDeveloperId))).
		      thenReturn(memberShip(.10));

		assertEquals(2, job.reconcile());

		ArgumentCaptor<PaymentAdviceDto> calculatedAdvice = ArgumentCaptor
				.forClass(PaymentAdviceDto.class);
		verify(payPalFacade, new Times(2)).sendAdvice(
				calculatedAdvice.capture());

		assertTrue(170.00 == calculatedAdvice.getAllValues().get(0).getAmount());
		assertTrue(135.00 == calculatedAdvice.getAllValues().get(1).getAmount());
	}

	@Test
	public void calculates_payable_with_multiple_Transaction_For_same_developer()
			throws Exception {
		List<TransactionDto> janetsGameFees = new ArrayList<TransactionDto>();
		String janetsDeveloperId = "janet12567";
		String janetsPayPalId = "JanetTheJUnitGuru@gmail.com";
		double fishPondGameFee = 200;
		double ticTacToeGameFee = 100;
		
		janetsGameFees.add(createTxDto(janetsDeveloperId, janetsPayPalId, fishPondGameFee));
		janetsGameFees.add(createTxDto(janetsDeveloperId, janetsPayPalId, ticTacToeGameFee));

		when(financialTransactionDAO.retrieveUnSettledTransactions())
				.thenReturn(janetsGameFees);

		assertEquals(2, job.reconcile());

		ArgumentCaptor<PaymentAdviceDto> calculatedAdvice = ArgumentCaptor
				.forClass(PaymentAdviceDto.class);
		verify(payPalFacade, new Times(1)).sendAdvice(calculatedAdvice.capture());

		assertTrue(210.00 == calculatedAdvice.getValue().getAmount());

	}

	private TransactionDto createTxDto(String targetId, String payPalId,
			double amount) {
		TransactionDto transactionDto = new TransactionDto();
		transactionDto.setTargetId(targetId);
		transactionDto.setTargetPayPalId(payPalId);
		transactionDto.setAmount(amount);
		return transactionDto;
	}

	private MembershipStatusDto memberShip(double deductable) {
		MembershipStatusDto membershipStatusDto = new MembershipStatusDto();
		membershipStatusDto.setDeductable(deductable);
		return membershipStatusDto;
	}
}

package com.packtpub.chapter09;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PaymentHandlerTest {

	PaymentHandler handler;
	 @Mock Patient patient;
	 
	 List<Encounter> encounters;
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		encounters = new ArrayList<Encounter>();
		handler = new TestablePaymentHandler(1L);
		
	}
	
	@Test
	public void sanity() throws Exception {
		
	}
	
	class TestablePaymentHandler extends PaymentHandler{

		private static final long serialVersionUID = 1L;

		public TestablePaymentHandler(Long objectId) {
			super(objectId, false);
		}
		
		protected List<Encounter> getEncounters() {
			return encounters;
		}
	}
}

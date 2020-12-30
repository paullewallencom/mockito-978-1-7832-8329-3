package com.packtpub.chapter06;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;

public class doAnswerTest {
	@Mock
	StockBroker broker;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void doAnswer_void_methods() throws Exception {
		Stock myStock = new Stock("A2", 0.00);
		doAnswer(new Answer<Double>() {

			@Override
			public Double answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				Stock stock = (Stock)args[0];
				stock.changePrice(100.00);
				return null;
			}

		}).when(broker).buy(myStock, 10);
		
		assertTrue(0.00== myStock.boughtAt());
		
		broker.buy(myStock, 10);
		assertTrue(100.00== myStock.boughtAt());
	}
}

package com.packtpub.chapter06;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class StockListenerTest {
	StockListener listener;

	@Mock StockBroker stockBroker;
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		listener = new StockListener(stockBroker);
	}
	
	@Test
	public void sells_BlueChip_Stocks() throws Exception {
		when(stockBroker.getQoute(argThat(new BlueChipStockMatcher()))).thenReturn(1000.00);
		listener.takeAction(new Stock("SBI", 500.00));
		verify(stockBroker).sell(isA(Stock.class), anyInt());
	}
	
	@Test
	public void sells_BlueChip_Stocks_WITH_answer_object() throws Exception {
		when(stockBroker.getQoute(argThat(new BlueChipStockMatcher()))).
		  thenAnswer(new BlueChipShareRises());
		
		listener.takeAction(new Stock("SBI", 1000.00));
		
		verify(stockBroker).sell(isA(Stock.class), anyInt());
	}
	
	@Test
	public void buys_low_Stocks() throws Exception {
		when(stockBroker.getQoute(argThat(new BlueChipStockMatcher()))).thenReturn(1000.00);
		listener.takeAction(new Stock("XYZ", 500.00));
		verify(stockBroker).buy(isA(Stock.class), anyInt());
	}
	
	class BlueChipStockMatcher extends  ArgumentMatcher<Stock>{

		@Override
		public boolean matches(Object argument) {
			Stock myStock = (Stock)argument;
			return "SBI".equals(myStock.getId()) || "HDFC".equals(myStock.getId());
		}
		
	}
	
	class BlueChipShareRises implements Answer<Double> {

		@Override
		public Double answer(InvocationOnMock invocation) throws Throwable {
			Object[] args = invocation.getArguments();
            Stock stock = (Stock)args[0];
			return stock.boughtAt() + 1.00;
		}
	};
}

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
import static org.junit.Assert.*;
public class DoReturnTest {

	@Mock
	StockBroker broker;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	
	@Test
	public void doReturn_usage() throws Exception {
		List<String> list = new ArrayList<String>();
		List<String> spy = spy(list);
		
		// You have to use doReturn() for stubbing:
		doReturn("foo").when(spy).get(0);
		assertEquals("foo", spy.get(0));
	}

	// @Test
	public void doReturn_is_not_type_safe() throws Exception {
		when(broker.getQoute(isA(Stock.class))).thenReturn(5.00);
		doReturn("string").when(broker).getQoute(isA(Stock.class));
		broker.getQoute(new Stock("A1", 40.00));

	}

}

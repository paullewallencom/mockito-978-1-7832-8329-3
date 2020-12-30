package com.packtpub.chapter06;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

public class UtilityTest {

	@Test
	public void spyTest() throws Exception {
		Stock realStock = new Stock("ICICI", 30.00);
		Stock spy = spy(realStock);
		
		//call real method from  spy
		assertEquals("ICICI", spy.getId());
		
		//Changing value using spy
		spy.changePrice(100.00);
			
		//verify spy has the changed value
		assertTrue(100 == spy.boughtAt());
		
		//Stubbing method
		when(spy.boughtAt()).thenReturn(5.00);
		//Changing value using spy
		spy.changePrice(666.00);
		System.out.println(spy.getId());
		//Stubbed method value returned
		assertTrue(666 != spy.boughtAt());
		assertTrue(5.00 == spy.boughtAt());
		
	}
}


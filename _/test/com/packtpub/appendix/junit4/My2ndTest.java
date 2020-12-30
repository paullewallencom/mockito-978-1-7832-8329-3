package com.packtpub.appendix.junit4;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class My2ndTest {

	@Test
	public void testName() throws Exception {
		System.out.println("My2ndTest");
	}
	
	@Ignore("for now")
	@Test
	public void ignoreMe() {
		System.err.println("you should ignore me");
	}
	
	@Test
	public void assertMe() throws Exception {
		//This test will fail, to pass the test change the Adder's add method and return a+b, instead of a.
		int expected = 1+2;
		assertEquals(expected, new Adder().add(1, 2));
	}
	
	class Adder{
		public int add(int a, int b) {
			return a;			
		}
	}
}

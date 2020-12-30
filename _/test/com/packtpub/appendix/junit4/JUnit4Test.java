package com.packtpub.appendix.junit4;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JUnit4Test {
	
	public JUnit4Test() {
		System.out.println(" Constructor is invoked");
	}
	@BeforeClass
	public static void bootMe() {
		System.out.println("@BeforeClass is invoked once");
	}
	

	@Before
	public void init() {
		System.out.println("@Before is executed...");
	}
	
	@After
	public void clear() {
		System.out.println("@After is executed...");
	}
	

	@AfterClass
	public static void shuttingDown() {
		System.out.println("@AfterClass is invoked once");
	}
	
	@Test
	public void myFirstTest() {
		System.out.println("Executing myFirstTest");
	}

	@Test
	public void mySecondTest() {
		System.out.println("Executing mySecondTest");
	}
	
	@Test(expected=RuntimeException.class)
	public void exception() {
		throw new RuntimeException();
	}
	
}

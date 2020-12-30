package com.packtpub.chapter02;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class OnceYouBuyYouStartCryingTelephoneTest {

	OnceYouBuyYouStartCryingTelephone telephone = new OnceYouBuyYouStartCryingTelephone();
	PersonName name = null;
	
	@Before
	public void setUp() {
		name = new PersonName();
	}

	@Test(expected = RuntimeException.class)
	public void when_input_first_name_is_null_then_throws_exception() throws Exception {
		telephone.addConnection(name, null, null);
		fail("code should not reach here");
	}

	@Test(expected = RuntimeException.class)
	public void when_input_last_name_is_null_then_throws_exception() throws Exception {
		name.setFirstName("john");
		telephone.addConnection(name, null, null);
		fail("code should not reach here");
	}

	@Test(expected = RuntimeException.class)
	public void when_input_z_is_null_then_throws_exception() throws Exception {
		name.setFirstName("john");
		name.setLastName("doe");
		telephone.addConnection(name, null, null);
		fail("code should not reach here");
	}

	@Test(expected = RuntimeException.class)
	public void when_input_gen_is_null_then_throws_exception()
			throws Exception {
		name.setFirstName("john");
		name.setLastName("doe");
		telephone.addConnection(name, new Date(), null);
		fail("code should not reach here");
	}

	@Test
	public void when_valid_input_then_adds_inputs() throws Exception {
		name.setFirstName("john");
		name.setLastName("doe");
		assertNotNull(telephone.addConnection(name, new Date(),
				ConnectionType.THREE_G));

	}

	@Test
	public void when_all_name_attributes_are_passed_then_forms_the_name()
			throws Exception {
		String johnsFirstName = "john";
		String johnsLastName = "smith";
		String johnsMiddleName = "maddison";
		String johnsNamePrefix = "dr.";
		
		name.setFirstName(johnsFirstName);
		name.setLastName(johnsLastName);
		name.setMiddleName(johnsMiddleName);
		name.setPrefix(johnsNamePrefix);

		String number = telephone.addConnection(name, new Date(),
				ConnectionType.THREE_G);
		
		assertNotNull(number);
		String billDetails = telephone.bill(number);
		assertTrue(billDetails.contains(johnsNamePrefix));
		assertTrue(billDetails.contains(johnsLastName));
		assertTrue(billDetails.contains(johnsMiddleName));
		assertTrue(billDetails.contains(johnsFirstName));
	}
}

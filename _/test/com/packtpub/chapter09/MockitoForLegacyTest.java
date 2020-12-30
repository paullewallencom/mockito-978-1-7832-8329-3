package com.packtpub.chapter09;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MockitoForLegacyTest {

	@Mock
	Patient aPatient;
	@Mock EncounterCharges encounterCharges;

	@Test
	public void when_patient_is_required() throws Exception {
		when(aPatient.getAllEncounters())
				.thenReturn(new ArrayList<Encounter>());
		assertNotNull(aPatient.getAllEncounters());
	}

	@Test
	public void spying_an_encounter() throws Exception {
		Encounter anEnc = new Encounter(0L, false);
		//creating a spy, for real methods 
		Encounter anEncSpy = spy(anEnc);

		Date today = new Date();
		anEncSpy.setStartDate(today);
		assertEquals(today, anEncSpy.getStartDate());
		
		//mockout the addCharge method
		doNothing().when(anEncSpy).addCharge(isA(EncounterCharges.class));
		anEncSpy.addCharge(encounterCharges);
		
	}
}

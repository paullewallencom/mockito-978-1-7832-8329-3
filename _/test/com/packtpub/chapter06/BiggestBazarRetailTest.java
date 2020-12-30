package com.packtpub.chapter06;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Time;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.Times;

public class BiggestBazarRetailTest {
	@Mock
	PublicAddressSystem pas;
	@Mock
	Inventory inventory;

	BiggestBazarRetail bazar;
	ArrayList<Item> expiredList = new ArrayList<Item>();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		// Setup with mock objects
		bazar = new BiggestBazarRetail(inventory, pas);
	}
	
	
	@Test(expected=RuntimeException.class)
	public void voidMethod_to_throw_exception() throws Exception {
		doThrow(new RuntimeException()).when(pas).announce(isA(Offer.class));
		pas.announce(new Offer(null, 0));
		fail("Code should not reach here");
	}
	@Test
	public void consecutiveCalls() throws Exception {
		when(inventory.getItemsExpireInAMonth()).thenReturn(expiredList).thenReturn(null);
		assertEquals(expiredList, inventory.getItemsExpireInAMonth());
		assertEquals(null, inventory.getItemsExpireInAMonth());
	}
	
	@Test(expected=RuntimeException.class)
	public void inventory_access_raises_Error() {
		when(inventory.getItemsExpireInAMonth()).thenThrow(new RuntimeException("Databse Access fail"));
		bazar.issueDiscountForItemsExpireIn30Days(.30);
		fail("Code should not reach here");
	}

	@Test
	public void issues_discount() throws Exception {
		// Creating expected item list
		Item soap = new Item("123", "Luxury Soap", 100.00, 50.00);
		expiredList.add(soap);

		// Stubbing database call for getItemsExpireInAMonth
		when(inventory.getItemsExpireInAMonth()).thenReturn(expiredList);
		// Stubbing update count
		when(inventory.itemsUpdated()).thenReturn(1);
		
		// Test
		bazar.issueDiscountForItemsExpireIn30Days(.30);

		//Verify that inventory update and public announcement were invoked 
		verify(inventory).update(soap, 70.00);
		verify(pas).announce(isA(Offer.class));
	}

	@Test
	public void when_no_item_qulifies_then_doesNOT_issue_discount() throws Exception {
		// Creating expected item list
		Item soap = new Item("123", "Luxury Soap", 100.00, 90.00);
		expiredList.add(soap);

		// Stubbing database call for getItemsExpireInAMonth
		when(inventory.getItemsExpireInAMonth()).thenReturn(expiredList);
		// Stubbing update count
		when(inventory.itemsUpdated()).thenReturn(1);
		
		// Test
		bazar.issueDiscountForItemsExpireIn30Days(.30);

		//Verify that NO inventory update and public announcement were invoked 
		verify(inventory, new Times(0)).update(isA(Item.class), anyDouble());
		verify(pas,new Times(0)).announce(isA(Offer.class));
	}
	@Test
	public void mockAnnotation_creates_mock() throws Exception {
		assertNotNull("@Mock could not create inventory", inventory);
		assertNotNull("@Mock could not create  PublicAddressSystem ", pas);
	}

	@Test
	public void sanityCheck() throws Exception {
		PublicAddressSystem pas = mock(PublicAddressSystem.class);
		Inventory inventory = mock(Inventory.class);
		assertNotNull("Inventory object was null", inventory);
		assertNotNull("PublicAddressSystem object was null", pas);
	}
}

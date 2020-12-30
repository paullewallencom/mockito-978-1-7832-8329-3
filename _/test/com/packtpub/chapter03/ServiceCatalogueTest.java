package com.packtpub.chapter03;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class ServiceCatalogueTest {
	ServiceCatalogue catalogue;

	@Before
	public void setup() {
		catalogue = new ServiceCatalogue();
	}

	@Test
	public void user_can_add_a_service_to_catalogue() {
		Procedure proc = new Procedure("1234", "Basic Oxygen Setup");
		catalogue.add(proc, BigDecimal.TEN);
		assertNotNull(catalogue.find(proc.getId()));
		assertEquals(catalogue.find(proc.getId()), proc);
	}

	@Test
	public void catalogue_returns_null_for_an_unconfigured_procedure_id()
			throws Exception {
		addToCatalogue(new Proc("1234", "Basic Oxygen Setup", BigDecimal.TEN));
		assertNull(catalogue.find("4567"));
	}

	@Test
	public void catalogue_returns_procedure_and_price_for_a_configured_procedure() {
		addToCatalogue(new Proc("1234", "Basic Oxygen Setup", BigDecimal.TEN),
				new Proc("5678", "Basic Oxygen Setup", BigDecimal.ONE));
		procedureExists("1234", "5678");
		assertEquals(catalogue.findPriceBy("1234"), BigDecimal.TEN);
		assertEquals(catalogue.findPriceBy("5678"), BigDecimal.ONE);
	}

	private void addToCatalogue(Proc... procs) {
		for (Proc proc : procs) {
			catalogue.add(proc.procedure, proc.price);
		}
	}

	private void procedureExists(String... ids) {
		for (String id : ids) {
			assertNotNull("Expected a procedure", catalogue.find(id));
		}
	}

	class Proc {
		Procedure procedure;
		BigDecimal price;

		Proc(String id, String desc, BigDecimal price) {
			procedure = new Procedure(id, desc);
			this.price = price;
		}
	}
}

package com.packtpub.chapter03;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceCatalogue {
	private List<Procedure> procs = new ArrayList<Procedure>();
	private Map<String, BigDecimal> priceMap = new HashMap<String, BigDecimal>();
	
	public void add(Procedure proc, BigDecimal price) {
		procs.add(proc);
		priceMap.put(proc.getId(), price);
	}

	public Procedure find(String id) {
		for (Procedure proc : procs) {
			if (proc.getId().equals(id)) {
				return proc;
			}
		}
		return null;
	}

	public BigDecimal findPriceBy(String id) {
		return priceMap.get(id);
	}

}

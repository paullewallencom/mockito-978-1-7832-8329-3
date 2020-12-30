package com.packtpub.chapter06;

import java.util.List;

public class BiggestBazarRetail {

	public int issueDiscountForItemsExpireIn30Days(double discountRate) {
		List<Item> headingExpiryItems = inventory.getItemsExpireInAMonth();
		for (Item anItem : headingExpiryItems) {
			double newPrice = anItem.getPrice() - anItem.getPrice() * discountRate;
			if (newPrice > anItem.getBasePrice()) {
				inventory.update(anItem, newPrice);
				publicAddressSystem.announce(new Offer(anItem, newPrice));
			}
		}
		
		return inventory.itemsUpdated();
	}

	public BiggestBazarRetail(Inventory inventory,
			PublicAddressSystem publicAddressSystem) {
		this.inventory = inventory;
		this.publicAddressSystem = publicAddressSystem;
	}

	private Inventory inventory;
	private PublicAddressSystem publicAddressSystem;
}

class Item {
	private final String barCode;
	private final String name;
	private final Double cost;
	private final Double basePrice;

	public String getBarCode() {
		return barCode;
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return cost;
	}

	public Item(String barCode, String name, Double cost, Double basePrice) {
		super();
		this.barCode = barCode;
		this.name = name;
		this.cost = cost;
		this.basePrice = basePrice;
	}

	public Double getBasePrice() {
		return basePrice;
	}

}

class Inventory {
	public List<Item> getItemsExpireInAMonth() {
		return null;
	}

	public int itemsUpdated() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void update(Item anItem, double newCost) {
		// TODO Auto-generated method stub

	}
}

class PublicAddressSystem {

	public void announce(Offer offer) {
		// TODO Auto-generated method stub

	}

}

class Offer {

	public Offer(Item anItem, double newCost) {
		// TODO Auto-generated constructor stub
	}

}
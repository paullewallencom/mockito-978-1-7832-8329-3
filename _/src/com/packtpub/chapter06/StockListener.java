package com.packtpub.chapter06;

public class StockListener {
	private final StockBroker broker;
	
	public void takeAction(Stock stock){
		double currentPrice = broker.getQoute(stock);
		if(currentPrice <= stock.boughtAt()){
			broker.buy(stock, 100);
		}else{
			broker.sell(stock, 10);
		}
		
	}
	
	public StockListener(StockBroker broker) {
		super();
		this.broker = broker;
	}
}

class Stock{
	private String id;
	private Double lastValue;
	public Stock(String id, Double lastValue) {
		super();
		this.id = id;
		this.lastValue = lastValue;
	}
	public String getId() {
		return id;
	}
	public Double boughtAt() {
		return lastValue;
	}
	
	public void changePrice(Double newPrice){
		this.lastValue = newPrice;
	}
	
}


interface StockBroker{
	void buy(Stock stock, int quantity);
	void sell(Stock stock, int quantity);
	double getQoute(Stock stock);
}
package com.estockmarket.stocks.document;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class StockDetails {
	private Double stockPrice;
	private Date stockPriceDttm;
	
	private Double minPrice;
	private Double maxPrice;
	private Double avgPrice;
	
	private List<Stock> stockData;
	
	public List<Stock> getStockData() {
		return stockData;
	}
	public void setStockData(List<Stock> stockData) {
		this.stockData = stockData;
	}
	public Double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}
	public Double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}
	public Double getAvgPrice() {
		return avgPrice;
	}
	public void setAvgPrice(Double avgPrice) {
		this.avgPrice = avgPrice;
	}
	public Double getStockPrice() {
		return stockPrice;
	}
	public void setStockPrice(Double stockPrice) {
		this.stockPrice = stockPrice;
	}
	public Date getStockPriceDttm() {
		return stockPriceDttm;
	}
	public void setStockPriceDttm(Date stockPriceDttm) {
		this.stockPriceDttm = stockPriceDttm;
	}
	
	
}

package com.estockmarket.stocks.document;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Stock {

	@Id
	private Integer id;
	private Integer companyCode;
	private Double price;
	private Date stockPriceDttm;
	
	private Double minPrice;
	private Double maxPrice;
	private Double avgPrice;
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	//private List<StockDetails> stockDetails;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(Integer companyCode) {
		this.companyCode = companyCode;
	}
	/*
	 * public List<StockDetails> getStockDetails() { return stockDetails; } public
	 * void setStockDetails(List<StockDetails> stockDetails) { this.stockDetails =
	 * stockDetails; }
	 */
	public Date getStockPriceDttm() {
		return stockPriceDttm;
	}
	public void setStockPriceDttm(Date stockPriceDttm) {
		this.stockPriceDttm = stockPriceDttm;
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
	
}

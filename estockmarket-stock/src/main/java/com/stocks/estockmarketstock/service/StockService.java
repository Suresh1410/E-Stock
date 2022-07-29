package com.stocks.estockmarketstock.service;

import java.util.List;

import com.estockmarket.stocks.document.Stock;
import com.estockmarket.stocks.dto.StockDto;

public interface StockService {

	List<Stock> getStock(Integer companyCode);

	void deleteStock(Stock stock);

	List<Stock> getAllStock();

	void deleteAllStocks();
	
	void deleteAllCustomSequence();
	
	String addCompanyNewStock(Integer companyCode, Stock stock);
}

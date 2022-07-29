package com.stocks.estockmarketstock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.estockmarket.stocks.document.Stock;
import com.estockmarket.stocks.dto.StockDto;
import com.stocks.estockmarketstock.repository.CustomSequencesRepo;
import com.stocks.estockmarketstock.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	StockRepository stockRepository;
	
	@Autowired
	CustomSequencesRepo customSequencesRepo;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	@Autowired
	NextSequenceService nextSequenceService;
	
	@Override
	public String addCompanyNewStock(Integer companyCode, Stock stock) {
		stock.setCompanyCode(companyCode);
	    stockRepository.save(stock);
		return null;
	}


	@Override
	public List<Stock> getStock(Integer companyCode) {
		List<Stock> stocks=stockRepository.findByCompanyCode(companyCode);
		return stocks;
	}

	@Override
	public void deleteStock(Stock stock) {
		stockRepository.deleteById(stock.getId());
	}

	@Override
	public List<Stock> getAllStock() {
		List<Stock> stockList=stockRepository.findAll();	
	
		return stockList;
	}

	@Override
	public void deleteAllStocks() {
		stockRepository.deleteAll();
	}

	@Override
	public void deleteAllCustomSequence() {
		customSequencesRepo.deleteAll();
	}


}

package com.stocks.estockmarketstock.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.estockmarket.company.dto.CompanyDto;
import com.estockmarket.stocks.document.Stock;
import com.estockmarket.stocks.document.StockDetails;
import com.estockmarket.stocks.dto.StockDto;
import com.estockmarket.utils.Constants;
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
	public Stock getStock(Integer companyCode) {
		Stock stock=stockRepository.findByCompanyCode(companyCode);
		return stock;
	}

	@Override
	public void deleteStock(Stock stock) {
		stockRepository.deleteById(stock.getId());
	}

	@Override
	public List<StockDto> getAllStock() {
		List<Stock> stockList=stockRepository.findAll();
		List<StockDto> stockDtoList=new ArrayList<StockDto>();
		if(!stockList.isEmpty()) {
			stockList.stream().forEach(s -> {
				StockDto stockDto=new StockDto();
				BeanUtils.copyProperties(s, stockDto);
				stockDtoList.add(stockDto);
			});
		}
	
		return stockDtoList;
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

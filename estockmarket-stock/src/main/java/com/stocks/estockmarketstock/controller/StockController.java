package com.stocks.estockmarketstock.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estockmarket.stocks.document.Stock;
import com.estockmarket.stocks.document.StockDetails;
import com.estockmarket.utils.Constants;
import com.stocks.estockmarketstock.service.StockDetailsService;
import com.stocks.estockmarketstock.service.StockService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@CrossOrigin
@RequestMapping("/api/v1.0/market/stock")
public class StockController {

	private static final Logger logger = LoggerFactory.getLogger(StockController.class);

	@Autowired
	StockService stockService;

	@Autowired
	StockDetailsService stockDetailsService;

	@ApiOperation(value = "Add Stock details based on company code", response = ResponseEntity.class)
	@ApiResponse(code = 200, message = "successful", response = ResponseEntity.class)
	@PostMapping(value = "/add/{companyCode}")
	public ResponseEntity<String> addCompanyNewStock(@PathVariable Integer companyCode, @RequestBody Stock newStock) {
		stockService.addCompanyNewStock(companyCode, newStock);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "Get Stock details based on company code", response = ResponseEntity.class)
	@ApiResponse(code = 200, message = "successful", response = ResponseEntity.class)
	@PostMapping("get/{companyCode}/{startDate}/{endDate}")
	public ResponseEntity<StockDetails> getStockByDate(@PathVariable("companyCode") Integer companyCode,
			@PathVariable(name = "startDate") @DateTimeFormat(pattern = "yyyy-M-dd") Date startDate,
			@PathVariable(name = "endDate") @DateTimeFormat(pattern = "yyyy-M-dd") Date endDate) {
		ResponseEntity response = null;
		if (companyCode != null || companyCode > 0) {
			List<StockDetails> stockDetails = stockDetailsService.findByStockPriceDttm(companyCode, startDate, endDate);
			response = new ResponseEntity(stockDetails, HttpStatus.OK);
		}
		return response;
	}

	// @DateTimeFormat(pattern = "dd-M-yyyy hh:mm:ss") Date begin,

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/get/{companyCode}")
	public ResponseEntity<Stock> getStock(@PathVariable("companyCode") Integer companyCode) {
		ResponseEntity response = null;
		List<Stock> stocks;
		if (companyCode != null || companyCode > 0) {
			stocks = stockService.getStock(companyCode);
			response = new ResponseEntity(stocks, HttpStatus.OK);
		}
		return response;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })

	@GetMapping("/delete/{companyCode}")
	public ResponseEntity<String> deleteStock(@PathVariable("companyCode") Integer companyCode) {
		ResponseEntity response = null;
		List<Stock> stocks;
		try {	
			stocks = stockService.getStock(companyCode);
			if(stocks.size()>0) {
				for (Stock stock : stocks) {
					if (stock.getId() != null) {
						stockService.deleteStock(stock);
						response = new ResponseEntity(Constants.SUCCESS, HttpStatus.OK);
					} else {
						response = new ResponseEntity(Constants.STOCK_NOT_FOUND, HttpStatus.OK);
					}
				}
			}
			
		} catch (Exception e) {
			response = new ResponseEntity(Constants.FAILED, HttpStatus.OK);
		}
		return response;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/getAll")
	public ResponseEntity<Stock> getAllStock() {
		List<Stock> stockDto = stockService.getAllStock();
		ResponseEntity response = new ResponseEntity(stockDto, HttpStatus.OK);
		return response;
	}
}
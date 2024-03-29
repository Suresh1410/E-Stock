package com.stocks.estockmarketstock.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.estockmarket.stocks.document.Stock;


@Repository
public interface StockRepository extends MongoRepository<Stock, Integer> {

	List<Stock> findByCompanyCode(Integer companyCode);

	Stock findByCompanyCodeAndId(Integer companyCode, Integer companyCode2);

}

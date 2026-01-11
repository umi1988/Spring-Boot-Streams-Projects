package com.starttohkar.service;

import com.starttohkar.entity.Stock;
import com.starttohkar.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StockService {

    Logger log = LoggerFactory.getLogger(StockService.class);

    @Autowired
    private StockRepository stockRepository;

    public List<Stock> getAllStocks(){
        List<Stock> stockList = stockRepository.findAll();
        log.info("Retrieved {} stocks from the db.", stockList.size());
        return stockList;
    }
}

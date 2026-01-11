package com.starttohkar.service;

import com.starttohkar.entity.Stock;
import com.starttohkar.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
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

    public Flux<Stock> streamStocks(){
        return Flux.fromIterable(stockRepository.findAll())
                .delayElements(Duration.ofSeconds(1)); // this delay is to check only...not recommended for prod
    }
}

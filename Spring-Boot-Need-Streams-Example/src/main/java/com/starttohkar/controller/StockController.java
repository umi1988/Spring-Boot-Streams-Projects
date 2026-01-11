package com.starttohkar.controller;

import com.starttohkar.entity.Stock;
import com.starttohkar.service.StockDataGenerator;
import com.starttohkar.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private StockDataGenerator stockDataGenerator;

    @GetMapping
    public List<Stock> getAllStocks(){
        return stockService.getAllStocks();
    }

    // API to insert random data to Stock table
    @GetMapping("/insert/data/{count}")
    public ResponseEntity<String> generateInsertData(@PathVariable Integer count) throws Exception {
        //virtualReportService.generateReportForRegion(region);
        stockDataGenerator.finalExecution(count);
        return ResponseEntity.ok( "✅ Inserted data successfully.");
    }
}

package com.starttohkar.controller;

import com.starttohkar.entity.Stock;
import com.starttohkar.service.StockDataGenerator;
import com.starttohkar.service.StockService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import tools.jackson.databind.ObjectMapper;

import java.io.OutputStream;
import java.util.List;
import java.util.Random;

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

    @GetMapping("/stream")
    public StreamingResponseBody streamStocks(HttpServletResponse response){
        response.setContentType("text/event-stream");
        return outputStream -> {
            stockService.getAllStocks()
                    .forEach(stock -> {
                        try{
                            String json = new ObjectMapper().writeValueAsString(stock) + "\n";
                            outputStream.write(json.getBytes());
                            outputStream.flush();
                        }catch (Exception e){
                            throw new RuntimeException(e.getMessage());
                        }
                    });
        };
    }

    @GetMapping("/live")
    public StreamingResponseBody streamStockPrices(HttpServletResponse response) {
        response.setContentType("text/event-stream"); // so browser/clients treat it as a stream

        return (OutputStream outputStream) -> {
            try {
                for (int i = 0; i < 50; i++) { // simulate 20 updates
                    double price = 100 + new Random().nextDouble() * 10; // random stock price
                    String update = "Stock Price: " + price + "\n";

                    outputStream.write(update.getBytes());
                    outputStream.flush();

                    Thread.sleep(100); // every 1 second new update
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}

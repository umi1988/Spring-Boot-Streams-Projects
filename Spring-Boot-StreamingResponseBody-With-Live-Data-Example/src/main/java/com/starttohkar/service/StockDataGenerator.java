package com.starttohkar.service;

import com.starttohkar.entity.Stock;
import com.starttohkar.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class StockDataGenerator {

    @Autowired
    private StockRepository stockRepository;

    private static final Random random = new Random();

    // Base company names
    private static final String[] COMPANY_PREFIXES = {
            "Global", "Advanced", "Digital", "Smart", "Tech", "Green", "Blue", "Red",
            "Alpha", "Beta", "Gamma", "Delta", "Omega", "Prime", "Elite", "Supreme",
            "United", "Allied", "National", "International", "Continental", "Universal",
            "Modern", "Future", "Next", "Pro", "Max", "Ultra", "Mega", "Super"
    };

    private static final String[] COMPANY_SUFFIXES = {
            "Corp", "Inc", "Ltd", "Group", "Industries", "Systems", "Solutions",
            "Technologies", "Enterprises", "Holdings", "Partners", "Ventures",
            "Capital", "Services", "Networks", "Labs", "Dynamics", "Innovation"
    };

    private static final String[] SYMBOLS = {
            "YRD", "SSL", "FNB", "AMC", "IND", "SYS", "SOL",
            "TECH", "ENP", "KAL", "PART", "VEN",
            "GAL", "YAV", "NAC", "HAL", "SAL", "INV"
    };

    // Sectors and Industries
    private static final SectorIndustry[] SECTORS = {
            new SectorIndustry("Technology", new String[]{
                    "Software", "Hardware", "IT Services", "Semiconductors",
                    "Consumer Electronics", "Cloud Computing", "Cybersecurity",
                    "AI & Machine Learning", "Internet Services", "E-commerce"
            }),
            new SectorIndustry("Financial", new String[]{
                    "Banking", "Investment Banking", "Insurance", "Asset Management",
                    "Credit Services", "Payment Processing", "Fintech", "Wealth Management"
            }),
            new SectorIndustry("Healthcare", new String[]{
                    "Pharmaceuticals", "Biotechnology", "Medical Devices",
                    "Health Insurance", "Hospitals", "Diagnostics", "Healthcare IT"
            }),
            new SectorIndustry("Energy", new String[]{
                    "Oil & Gas", "Renewable Energy", "Solar Power", "Wind Energy",
                    "Energy Storage", "Utilities", "Coal & Mining"
            }),
            new SectorIndustry("Consumer Goods", new String[]{
                    "Beverages", "Food Products", "Personal Care", "Apparel",
                    "Restaurants", "Retail", "Household Products"
            }),
            new SectorIndustry("Automotive", new String[]{
                    "Electric Vehicles", "Automobiles", "Auto Parts",
                    "Autonomous Vehicles", "Commercial Vehicles"
            }),
            new SectorIndustry("Real Estate", new String[]{
                    "Commercial Real Estate", "Residential Real Estate", "REITs",
                    "Property Development", "Real Estate Services"
            }),
            new SectorIndustry("Telecommunications", new String[]{
                    "Wireless", "Broadband", "Fiber Optics", "Satellite", "5G Infrastructure"
            }),
            new SectorIndustry("Manufacturing", new String[]{
                    "Industrial Equipment", "Machinery", "Aerospace", "Defense",
                    "Chemical Manufacturing", "Construction Materials"
            }),
            new SectorIndustry("Retail", new String[]{
                    "E-commerce", "Discount Stores", "Luxury Retail",
                    "Home Improvement", "Specialty Retail", "Department Stores"
            })
    };

    public List<Stock> generateStocks(int count) {
        List<Stock> stocks = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Stock stock = new Stock();

            // Generate unique company name and symbol
            String companyName = generateCompanyName();
            String symbol = generateSymbols();

            // Select random sector and industry
            SectorIndustry sectorInfo = SECTORS[random.nextInt(SECTORS.length)];
            String industry = sectorInfo.industries[random.nextInt(sectorInfo.industries.length)];

            // Generate random stock price
            String price = generateRandomPrice();
            //stock.setId((long) i);
            stock.setName(companyName);
            stock.setSymbol(symbol);
            stock.setValue(price);
            stock.setSector(sectorInfo.sector);
            stock.setIndustry(industry);

            stocks.add(stock);
        }

        return stocks;
    }

    private String generateSymbols() {
        return SYMBOLS[random.nextInt(SYMBOLS.length)];
    }

    private String generateCompanyName() {
        String prefix = COMPANY_PREFIXES[random.nextInt(COMPANY_PREFIXES.length)];
        String suffix = COMPANY_SUFFIXES[random.nextInt(COMPANY_SUFFIXES.length)];

        // Add some variation
        if (random.nextDouble() > 0.5) {
            return prefix + " " + suffix;
        } else {
            String middle = COMPANY_PREFIXES[random.nextInt(COMPANY_PREFIXES.length)];
            return prefix + " " + middle + " " + suffix;
        }
    }

//    private String generateSymbol(int index) {
//        // Generate symbol based on index for uniqueness
//        // Format: ABC1234 or ABCD123
//        StringBuilder symbol = new StringBuilder();
//
//        // 3-4 random letters
//        int letterCount = random.nextInt(2) + 3; // 3 or 4 letters
//        for (int i = 0; i < letterCount; i++) {
//            symbol.append((char) ('A' + random.nextInt(26)));
//        }
//
//        // Add index to ensure uniqueness
//        symbol.append(index);
//
//        return symbol.toString();
//    }

    private String generateRandomPrice() {
        // Generate prices in different ranges for variety
        double price;
        int priceRange = random.nextInt(100);

        if (priceRange < 20) {
            // Penny stocks: $0.50 - $10
            price = 0.50 + (random.nextDouble() * 9.50);
        } else if (priceRange < 50) {
            // Low-priced stocks: $10 - $50
            price = 10 + (random.nextDouble() * 40);
        } else if (priceRange < 80) {
            // Mid-priced stocks: $50 - $200
            price = 50 + (random.nextDouble() * 150);
        } else if (priceRange < 95) {
            // High-priced stocks: $200 - $500
            price = 200 + (random.nextDouble() * 300);
        } else {
            // Premium stocks: $500 - $2000
            price = 500 + (random.nextDouble() * 1500);
        }

        BigDecimal bd = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP);
        return "$" + bd;
    }

    // Helper class for sector and industry data
    private static class SectorIndustry {
        String sector;
        String[] industries;

        SectorIndustry(String sector, String[] industries) {
            this.sector = sector;
            this.industries = industries;
        }
    }

    public void finalExecution(int count) throws Exception {
        System.out.println("Generating random stocks data...");
        List<Stock> stocks = generateStocks(count);
        stockRepository.saveAll(stocks);
        System.out.println("Successfully inserted " + stocks.size() + " stocks!");
    }
}











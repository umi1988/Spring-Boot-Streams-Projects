package com.starttohkar.entity;

import jakarta.persistence.*;

@Table(name="stocks")
@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String symbol;
    private String value;
    private String sector;
    private String industry;

    public Stock() {
    }

    public Stock(Long id, String name, String symbol, String value, String sector, String industry) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.value = value;
        this.sector = sector;
        this.industry = industry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", value='" + value + '\'' +
                ", sector='" + sector + '\'' +
                ", industry='" + industry + '\'' +
                '}';
    }
}
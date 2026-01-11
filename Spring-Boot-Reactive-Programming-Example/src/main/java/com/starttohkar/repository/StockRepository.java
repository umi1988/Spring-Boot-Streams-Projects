package com.starttohkar.repository;

import com.starttohkar.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository  extends JpaRepository<Stock, Long> {
}

package com.FinTradeX.Simulator.repository;

import com.FinTradeX.Simulator.entity.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepo extends JpaRepository<Stocks, Long> {
    Optional<Stocks> findBySymbol(String symbol);
}

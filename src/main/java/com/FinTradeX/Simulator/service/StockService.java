package com.FinTradeX.Simulator.service;

import com.FinTradeX.Simulator.dto.StockResponse;
import com.FinTradeX.Simulator.entity.Stocks;
import com.FinTradeX.Simulator.exception.ResourceNotFoundException;
import com.FinTradeX.Simulator.repository.StockRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {
    @Autowired
    private final StockRepo stockRepo;

    public List<StockResponse> getAllStocks() {
        return stockRepo.findAll()
                .stream()
                .map(stock -> new StockResponse(
                        stock.getSymbol(),
                        stock.getCompName(),
                        stock.getPrice()))
                .toList();
    }

    public StockResponse getStockBySymbol(String symbol){
        Stocks stock=stockRepo.findBySymbol(symbol)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found"));
        return new StockResponse(
                stock.getSymbol(),
                stock.getCompName(),
                stock.getPrice());
    }

    public void addStocks(StockResponse resp) {
        Stocks stock= Stocks.builder()
                .symbol(resp.getSymbol())
                .price(resp.getPrice())
                .compName(resp.getCompName())
                .lastUpdated(LocalDateTime.now())
                .build();
        stockRepo.save(stock);
    }
}

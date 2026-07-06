package com.FinTradeX.Simulator.controller;

import com.FinTradeX.Simulator.dto.StockResponse;
import com.FinTradeX.Simulator.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StockController {
    @Autowired
    private StockService stockServ;

    @GetMapping("/stocks")
    public List<StockResponse> getAllStocks(){
        return stockServ.getAllStocks();
    }

    @GetMapping("/stocks/{symbol}")
    public StockResponse getStockBySymbol(@PathVariable String symbol){
        return stockServ.getStockBySymbol(symbol);
    }

    @PostMapping("/stocks/add")
    public void addStocks(@RequestBody StockResponse resp){
        stockServ.addStocks(resp);
    }
}

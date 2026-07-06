package com.FinTradeX.Simulator.controller;

import com.FinTradeX.Simulator.service.MarketDataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
public class MarketController {
    private final MarketDataService marServ;

    @GetMapping("/price/{symbol}")
    public BigDecimal getCurrPrice(@PathVariable String symbol){
        return marServ.getCurrPrice(symbol);
    }

}

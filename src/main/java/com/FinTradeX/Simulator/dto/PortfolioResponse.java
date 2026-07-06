package com.FinTradeX.Simulator.dto;

import java.math.BigDecimal;


public record PortfolioResponse(
        String symbol,
        Integer quantity,
        BigDecimal avdBuyPrice,
        BigDecimal currentPrice,
        BigDecimal currentValue
) { }

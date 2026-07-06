package com.FinTradeX.Simulator.dto;

import java.math.BigDecimal;

public record PortfolioValueResponse(
        BigDecimal cash,
        BigDecimal stocks,
        BigDecimal total
) { }

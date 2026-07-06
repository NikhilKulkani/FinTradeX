package com.FinTradeX.Simulator.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record tradeResponse(
        String symbol,
        TradeType tradeType,
        Integer quantity,
        BigDecimal price,
        LocalDateTime time
) { }

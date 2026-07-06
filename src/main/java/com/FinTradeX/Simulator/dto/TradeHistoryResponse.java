package com.FinTradeX.Simulator.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TradeHistoryResponse(
        String symbol,
        TradeType tradeType,
        int quantity,
        BigDecimal exePrice,
        LocalDateTime time
) { }

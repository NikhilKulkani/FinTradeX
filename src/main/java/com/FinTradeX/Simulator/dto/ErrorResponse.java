package com.FinTradeX.Simulator.dto;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime time,
        int status,
        String error,
        String message
        ) { }

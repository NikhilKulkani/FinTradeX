package com.FinTradeX.Simulator.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeRequest {
    @NotBlank(message = "Symbol is required")
    private String symbol;

    @Min(value = 1, message = "Quantity must be greater than 0")
    private int quantity;
}

package com.FinTradeX.Simulator.entity;

import com.FinTradeX.Simulator.dto.TradeType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tradeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stockId")
    private Stocks stock;

    @Enumerated(EnumType.STRING)
    private TradeType tradetype;
    private int quantity;
    private BigDecimal exePrice;
    private LocalDateTime time;
}

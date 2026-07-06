package com.FinTradeX.Simulator.entity;

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
public class Stocks {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long stockId;
    @Column(unique=true)
    private String symbol;
    private String compName;
    private BigDecimal price;
    private LocalDateTime lastUpdated;

}

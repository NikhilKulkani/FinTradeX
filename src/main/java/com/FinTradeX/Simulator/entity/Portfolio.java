package com.FinTradeX.Simulator.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"userId","stockId"}))
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Portfolio {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long portId;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Users user;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "stockId")
    private Stocks stock;

    private Integer quantity;
    private BigDecimal avgBuyPrice;
}

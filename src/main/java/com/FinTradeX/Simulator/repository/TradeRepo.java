package com.FinTradeX.Simulator.repository;

import com.FinTradeX.Simulator.entity.Trade;
import com.FinTradeX.Simulator.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeRepo extends JpaRepository<Trade, Long> {
    List<Trade> findByUser(Users user);
}

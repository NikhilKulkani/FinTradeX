package com.FinTradeX.Simulator.repository;

import com.FinTradeX.Simulator.entity.Portfolio;
import com.FinTradeX.Simulator.entity.Stocks;
import com.FinTradeX.Simulator.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PortRepo extends JpaRepository<Portfolio, Long> {
    Optional<Portfolio> findByUserAndStock(
        Users user,
        Stocks stock
    );

    List<Portfolio> findByUser(Users user);
}

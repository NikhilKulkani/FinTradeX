package com.FinTradeX.Simulator.service;

import com.FinTradeX.Simulator.dto.PortfolioResponse;
import com.FinTradeX.Simulator.dto.PortfolioValueResponse;
import com.FinTradeX.Simulator.entity.Portfolio;
import com.FinTradeX.Simulator.entity.Users;
import com.FinTradeX.Simulator.exception.ResourceNotFoundException;
import com.FinTradeX.Simulator.repository.PortRepo;
import com.FinTradeX.Simulator.repository.UsersRepo;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Builder
public class PortfolioService {
    private final PortRepo portRepo;
    private final UsersRepo userRepo;


    public List<PortfolioResponse> getPortfolio(String email) {
        Users user=userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        List<Portfolio> ports=portRepo.findByUser(user);
        return ports.stream()
                .map(p -> {BigDecimal currentValue =
                            BigDecimal.valueOf(p.getQuantity()).multiply(p.getStock().getPrice());
                return new PortfolioResponse(
                        p.getStock().getSymbol(),
                        p.getQuantity(),
                        p.getAvgBuyPrice(),
                        p.getStock().getPrice(),
                        currentValue
                ); })
                .toList();
    }

    public PortfolioValueResponse getPortfolioValue(String email) {
        Users user= userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));
        List<Portfolio> ports=portRepo.findByUser(user);
        BigDecimal stockValue= BigDecimal.ZERO;
        for(Portfolio p : ports) {
            BigDecimal value= BigDecimal.valueOf(p.getQuantity())
                    .multiply(p.getStock().getPrice());
            stockValue = stockValue.add(value);
        }
        BigDecimal cash= user.getBalance();
        BigDecimal total= cash.add(stockValue);
        return new PortfolioValueResponse(stockValue,cash,total);
    }
}

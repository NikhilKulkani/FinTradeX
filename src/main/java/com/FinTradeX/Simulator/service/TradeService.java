package com.FinTradeX.Simulator.service;

import com.FinTradeX.Simulator.dto.TradeHistoryResponse;
import com.FinTradeX.Simulator.dto.TradeRequest;
import com.FinTradeX.Simulator.dto.TradeType;
import com.FinTradeX.Simulator.entity.Portfolio;
import com.FinTradeX.Simulator.entity.Stocks;
import com.FinTradeX.Simulator.entity.Trade;
import com.FinTradeX.Simulator.entity.Users;
import com.FinTradeX.Simulator.exception.InsufficientBalanceException;
import com.FinTradeX.Simulator.exception.InsufficientStocksException;
import com.FinTradeX.Simulator.exception.ResourceNotFoundException;
import com.FinTradeX.Simulator.repository.PortRepo;
import com.FinTradeX.Simulator.repository.StockRepo;
import com.FinTradeX.Simulator.repository.TradeRepo;
import com.FinTradeX.Simulator.repository.UsersRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TradeService {
    private final UsersRepo userRepo;
    private final StockRepo stockRepo;
    private final PortRepo portRepo;
    private final TradeRepo tradeRepo;

    @Transactional
    public void buyStock(String email, TradeRequest request) {
        Users user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Stocks stock = stockRepo.findBySymbol(request.getSymbol())
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found"));
        BigDecimal totalCost = stock.getPrice()
                .multiply(BigDecimal.valueOf(request.getQuantity()));
        if (user.getBalance().compareTo(totalCost) < 0) {
            throw new InsufficientBalanceException("Insufficient Balance");
        }
        user.setBalance(user.getBalance().subtract(totalCost));
        Optional<Portfolio> optional = portRepo.findByUserAndStock(user, stock);
        Portfolio port;
        if (optional.isPresent()) {
            port = optional.get();
            int newQty = port.getQuantity() + request.getQuantity();
            port.setQuantity(newQty);
        } else {
            port = Portfolio.builder()
                    .user(user)
                    .stock(stock)
                    .quantity(request.getQuantity())
                    .avgBuyPrice(stock.getPrice())
                    .build();
        }

        Trade trade = Trade.builder()
                .user(user)
                .stock(stock)
                .tradetype(TradeType.BUY)
                .quantity(request.getQuantity())
                .exePrice(stock.getPrice())
                .time(LocalDateTime.now())
                .build();
        userRepo.save(user);
        portRepo.save(port);
        tradeRepo.save(trade);
    }

    @Transactional
    public void sellStock(String email, TradeRequest req) {
        Users user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));
        Stocks stock = stockRepo.findBySymbol(req.getSymbol())
                .orElseThrow(() -> new ResourceNotFoundException("stock not found"));
        Portfolio port = portRepo.findByUserAndStock(user, stock)
                .orElseThrow(() -> new InsufficientStocksException("stock not owned"));
        if (port.getQuantity() < req.getQuantity()) {
            throw new InsufficientStocksException("Insufficient shares");
        }
        BigDecimal sellAmt = stock.getPrice()
                .multiply(BigDecimal.valueOf(req.getQuantity()));
        user.setBalance(user.getBalance().add(sellAmt));
        port.setQuantity(port.getQuantity() - req.getQuantity());
        if (port.getQuantity() == 0) {
            portRepo.delete(port);
        } else {
            portRepo.save(port);
        }
        Trade trade= Trade.builder()
                .user(user)
                .stock(stock)
                .tradetype(TradeType.SELL)
                .quantity(req.getQuantity())
                .exePrice(stock.getPrice())
                .time(LocalDateTime.now())
                .build();
        userRepo.save(user);
        tradeRepo.save(trade);
    }

    public List<TradeHistoryResponse> tradeHistory(String email) {
        Users user= userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));
        List<Trade> trades=tradeRepo.findByUser(user);
        return trades.stream()
                .map(t -> new TradeHistoryResponse(
                        t.getStock().getSymbol(),
                        t.getTradetype(),
                        t.getQuantity(),
                        t.getExePrice(),
                        t.getTime()
                ))
                .toList();
    }
}


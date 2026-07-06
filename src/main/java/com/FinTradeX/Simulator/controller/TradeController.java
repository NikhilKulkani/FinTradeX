package com.FinTradeX.Simulator.controller;

import com.FinTradeX.Simulator.dto.TradeHistoryResponse;
import com.FinTradeX.Simulator.dto.TradeRequest;
import com.FinTradeX.Simulator.service.TradeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class TradeController {
    @Autowired
    private final TradeService tradeServ;

    @PostMapping ("/stocks/buy")
    public ResponseEntity<String> buyStock(
            Authentication auth, @Valid @RequestBody TradeRequest req){
        tradeServ.buyStock(auth.getName(), req);
        return ResponseEntity.ok("stock purchased successfully");
    }

    @PostMapping("/stocks/sell")
    public ResponseEntity<String> sellStock(
            Authentication auth,@Valid @RequestBody TradeRequest req){
        System.out.println("Auth user=" + auth.getName());
        tradeServ.sellStock(auth.getName(),req);
        return ResponseEntity.ok("stock sold successfully");
    }

    @GetMapping("/trade/history")
    public ResponseEntity<List<TradeHistoryResponse>> getHistory(
            Authentication auth){
        return ResponseEntity.ok(tradeServ.tradeHistory(auth.getName()));
    }
}

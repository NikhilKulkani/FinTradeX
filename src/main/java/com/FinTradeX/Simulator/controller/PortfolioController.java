package com.FinTradeX.Simulator.controller;

import com.FinTradeX.Simulator.dto.PortfolioResponse;
import com.FinTradeX.Simulator.dto.PortfolioValueResponse;
import com.FinTradeX.Simulator.service.PortfolioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class PortfolioController {
    private final PortfolioService portServ;

    @GetMapping("/portfolio")
    public ResponseEntity<List<PortfolioResponse>> getPortfolio(
            Authentication auth) {
        return ResponseEntity.ok(portServ.getPortfolio(auth.getName()));
    }

    @GetMapping("/portfolio/value")
    public ResponseEntity<PortfolioValueResponse> getPortfolioValue(
            Authentication auth) {
        return ResponseEntity.ok(portServ.getPortfolioValue(auth.getName()));
    }
}

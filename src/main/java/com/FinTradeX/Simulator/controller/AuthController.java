package com.FinTradeX.Simulator.controller;

import com.FinTradeX.Simulator.dto.AuthResponse;
import com.FinTradeX.Simulator.dto.LoginRequest;
import com.FinTradeX.Simulator.dto.RegisterRequest;
import com.FinTradeX.Simulator.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private final AuthService authServ;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest req){
         authServ.register(req);
         return "Registered Successfully";
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest log){
        return ResponseEntity.ok(authServ.login(log));
    }
}

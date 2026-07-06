package com.FinTradeX.Simulator.service;

import com.FinTradeX.Simulator.dto.AuthResponse;
import com.FinTradeX.Simulator.dto.LoginRequest;
import com.FinTradeX.Simulator.dto.RegisterRequest;
import com.FinTradeX.Simulator.entity.Users;
import com.FinTradeX.Simulator.exception.ResourceNotFoundException;
import com.FinTradeX.Simulator.repository.UsersRepo;
import com.FinTradeX.Simulator.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    private final UsersRepo userRepo;
    private final PasswordEncoder encoder;
    @Autowired
    private final JwtUtil util;

    public void register(RegisterRequest req){
        Users user=Users.builder()
                .name(req.getName())
                .email(req.getEmail())
                .balance(BigDecimal.valueOf(100000))
                .createdAt(LocalDateTime.now())
                .password(encoder.encode(req.getPassword()))
                .build();
        userRepo.save(user);
    }

    public AuthResponse login(LoginRequest req){
        Users user=userRepo.findByEmail(req.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        if(!encoder.matches(req.getPassword(), user.getPassword()))
            throw new RuntimeException("Invalid Credentials");
        String token= util.generateToken(user.getEmail());
        return new AuthResponse (token);
    }

}

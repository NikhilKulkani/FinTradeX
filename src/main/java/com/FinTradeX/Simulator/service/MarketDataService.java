package com.FinTradeX.Simulator.service;

import com.FinTradeX.Simulator.dto.MarketPriceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class MarketDataService {
    @Value("${twelvedata.api.key}")
    private String apiKey;

    @Value("${twelvedata.base.url}")
    private String baseUrl;

    private final RestClient restClient;


    public BigDecimal getCurrPrice(String symbol){
        MarketPriceResponse resp =
                restClient.get()
                        .uri(baseUrl + "/price?symbol="
                        + symbol +  "&apikey="
                        + apiKey)
                        .retrieve()
                        .body(MarketPriceResponse.class);
        return new BigDecimal(resp.price());
    }



}

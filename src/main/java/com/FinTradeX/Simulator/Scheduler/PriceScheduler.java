//package com.FinTradeX.Simulator.Scheduler;
//
//import com.FinTradeX.Simulator.entity.Stocks;
//import com.FinTradeX.Simulator.repository.StockRepo;
//import com.FinTradeX.Simulator.service.MarketDataService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class PriceScheduler {
//    private final StockRepo stockRepo;
//    private final MarketDataService marServ;
//
//    @Scheduled(fixedRate = 300000)
//    public void updatePrices(){
//
//        List<Stocks> stocks=stockRepo.findAll();
//        for(Stocks st : stocks){
//            BigDecimal latest = marServ.getCurrPrice(st.getSymbol());
//            st.setPrice(latest);
//            st.setLastUpdated(LocalDateTime.now());
//            stockRepo.save(st);
//        }
//
//    }
//}

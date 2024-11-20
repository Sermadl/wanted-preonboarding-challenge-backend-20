package org.wantedmarket.trade.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.wantedmarket.global.util.CommonController;
import org.wantedmarket.trade.domain.dto.request.TradeAcceptRequest;
import org.wantedmarket.trade.domain.dto.request.TradeConfirmRequest;
import org.wantedmarket.trade.domain.dto.request.TradeCreateRequest;
import org.wantedmarket.trade.domain.dto.response.BuyHistoryResponse;
import org.wantedmarket.trade.domain.dto.response.SellHistoryResponse;
import org.wantedmarket.trade.service.TradeService;

@RestController
@RequestMapping("/trade")
@RequiredArgsConstructor
public class TradeController extends CommonController {
    private final TradeService tradeService;

    @PostMapping
    public void buyItem(@RequestBody TradeCreateRequest request, HttpServletRequest httpServletRequest) {
        tradeService.buyItem(request, getLoginMemberId(httpServletRequest));
    }

    @PostMapping("/accept")
    public void acceptTrade(@RequestBody TradeAcceptRequest request, HttpServletRequest httpServletRequest) {
        tradeService.acceptTrade(request, getLoginMemberId(httpServletRequest));
    }

    @PostMapping("/confirm")
    public void confirmPurchase(@RequestBody TradeConfirmRequest request, HttpServletRequest httpServletRequest) {
        tradeService.confirmPurchase(request, getLoginMemberId(httpServletRequest));
    }

    @GetMapping("/buy-history")
    public ResponseEntity<Page<BuyHistoryResponse>> showBuyHistory(Pageable pageable, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(tradeService.showBuyHistory(pageable, getLoginMemberId(httpServletRequest)));
    }

    @GetMapping("/sell-history")
    public ResponseEntity<Page<SellHistoryResponse>> showSellHistory(Pageable pageable, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(tradeService.showSellHistory(pageable, getLoginMemberId(httpServletRequest)));
    }
}

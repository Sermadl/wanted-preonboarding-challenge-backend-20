package org.wantedmarket.trade.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.wantedmarket.trade.domain.entity.TradeStatus;

@Getter
@AllArgsConstructor
public class BuyHistoryResponse {
    private Long tradeId;
    private int tradePrice;
    private TradeStatus tradeStatus;
    private Long itemId;
    private String itemName;
    private Long sellerId;
    private String sellerName;
}

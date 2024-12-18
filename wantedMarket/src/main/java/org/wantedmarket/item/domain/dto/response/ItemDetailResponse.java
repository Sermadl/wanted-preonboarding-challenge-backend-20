package org.wantedmarket.item.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.wantedmarket.item.domain.entity.ItemStatus;

@Getter
@AllArgsConstructor
public class ItemDetailResponse {
    private Long id;
    private String sellerName;
    private String name;
    private String description;
    private int price;
    private int quantity;
    private ItemStatus status;
}

package org.wantedmarket.item.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.wantedmarket.global.util.BaseEntity;
import org.wantedmarket.member.domain.entity.Member;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int price;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    @Setter
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public void decreaseQuantity() {
        this.quantity--;
    }

    public void increaseQuantity() {
        this.quantity++;
    }

    public void changeStatus(ItemStatus newStatus) {
        this.status = newStatus;
    }

    public void updateItem(String name, String description, int price, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
}

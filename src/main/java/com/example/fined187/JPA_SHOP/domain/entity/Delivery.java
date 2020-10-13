package com.example.fined187.JPA_SHOP.domain.entity;

import com.example.fined187.JPA_SHOP.domain.enums.DeliveryStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    @Setter
    private Order order;

    @Embedded
    private com.example.fined187.JPA_SHOP.domain.entity.Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @Builder
    public Delivery(Address address, DeliveryStatus deliveryStatus) {
        this.address = address;
        this.deliveryStatus = deliveryStatus;
    }
}

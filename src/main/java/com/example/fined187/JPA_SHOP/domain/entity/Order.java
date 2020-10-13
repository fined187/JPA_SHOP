package com.example.fined187.JPA_SHOP.domain.entity;

import com.example.fined187.JPA_SHOP.domain.enums.DeliveryStatus;
import com.example.fined187.JPA_SHOP.domain.enums.OrderStatus;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @OneToMany(mappedBy = "order")
    List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivet_id")
    private Delivery delivery;

    private LocalDateTime orderTime;
    private int orderPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    //  관계 편의 메서드.
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItem.setOrder(this);
        this.orderItems.add(orderItem);
    }

    @Builder
    public Order(LocalDateTime orderTime, OrderStatus orderStatus) {
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
    }

    //  도메인 로직.
    public static Order createOrder(Member member, Delivery delivery, List<OrderItem> orderItems) {
        Order order = Order
                .builder()
                .orderTime(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDERED)
                .build();

        order.setMember(member);
        order.setDelivery(delivery);

        for(OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        return order;
    }

    //  최종 가격
    public int totalPrice() {
        return this.getOrderItems().stream()
                .mapToInt(OrderItem::getOrderItemPrice)
                .sum();
    }

    //  주문 취소.
    public void orderCancel() {
        if(this.delivery.getDeliveryStatus() == DeliveryStatus.DELIVERYING ||
                this.delivery.getDeliveryStatus() == DeliveryStatus.DELIVERYING) {
            throw new IllegalStateException("배송중이거나 배송 완료된 상품은 주문 취소 할 수 없습니다.");
        }
        getOrderItems().forEach(OrderItem::cancel);
    }
}

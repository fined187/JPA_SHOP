package com.example.fined187.JPA_SHOP.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemDTO {
    private Long itemId;
    private int count;
    private int price;
}
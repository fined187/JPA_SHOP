package com.example.fined187.JPA_SHOP.domain.entity.item;

import com.example.fined187.JPA_SHOP.domain.dto.ItemDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("laptop")
@NoArgsConstructor
@Getter @Setter
public class Laptop extends Item {
    private String code;

    @Builder
    public Laptop(String itemName, int itemCount, int itemPrice, String name, String code) {
        super(itemName, itemCount, itemPrice);
        this.code = code;
    }


    @Override
    public void changeInfo(ItemDTO itemDTO) {
        super.changeInfo(itemDTO);
        this.code = itemDTO.getCode();
    }
}

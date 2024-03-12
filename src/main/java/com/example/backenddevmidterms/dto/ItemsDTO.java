package com.example.backenddevmidterms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemsDTO {
    private Long productId;
    private int quantity;
    public ItemsDTO() {
    }
    public ItemsDTO(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}

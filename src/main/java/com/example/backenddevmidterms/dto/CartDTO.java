package com.example.backenddevmidterms.dto;

import com.example.backenddevmidterms.Models.Items;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CartDTO {
    private Long id;
    private Long userId;
    private Date date;
    private List<Items> products;
}

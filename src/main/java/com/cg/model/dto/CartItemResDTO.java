package com.cg.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartItemResDTO {

    private Long id;
    private String title;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal amount;

}

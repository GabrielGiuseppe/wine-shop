package net.giuseppe.wine_shop.product.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer code;
    private String wineType;
    private Double price;
    private String harvest;
    private Integer purchaseYear;
}

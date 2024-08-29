package net.giuseppe.wine_shop.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    @JsonProperty("codigo")
    private Integer code;
    @JsonProperty("tipo_vinho")
    private String wineType;
    @JsonProperty("preco")
    private Double price;
    @JsonProperty("safra")
    private String harvest;
    @JsonProperty("ano_compra")
    private Integer purchaseYear;
}

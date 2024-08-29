package net.giuseppe.wine_shop.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    @JsonProperty("codigo")
    private String code;
    @JsonProperty("quantidade")
    private Integer quantity;
}

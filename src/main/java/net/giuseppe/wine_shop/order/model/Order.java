package net.giuseppe.wine_shop.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @JsonProperty("codigo")
    private String code;
    @JsonProperty("quantidade")
    private Integer quantity;
}

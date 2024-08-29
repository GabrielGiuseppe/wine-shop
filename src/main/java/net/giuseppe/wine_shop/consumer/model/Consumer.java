package net.giuseppe.wine_shop.consumer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import net.giuseppe.wine_shop.order.model.Order;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Consumer {
    @JsonProperty("nome")
    private String name;
    private String cpf;
    @JsonProperty("compras")
    private List<Order> order;
}

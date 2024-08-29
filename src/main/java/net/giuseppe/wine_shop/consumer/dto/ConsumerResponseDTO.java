package net.giuseppe.wine_shop.consumer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.giuseppe.wine_shop.order.dto.OrderResponseDTO;
import net.giuseppe.wine_shop.order.model.Order;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerResponseDTO {
    @JsonProperty("nome")
    private String name;
    private String cpf;
    @JsonProperty("compras")
    private List<OrderResponseDTO> order;
}

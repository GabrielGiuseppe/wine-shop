package net.giuseppe.wine_shop.consumer.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerResponse {

    @JsonProperty("nome")
    private String name;
    private String cpf;
    @JsonProperty("quantidade_compras")
    private Integer orderQuantity;
}

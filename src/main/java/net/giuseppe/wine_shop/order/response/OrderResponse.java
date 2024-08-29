package net.giuseppe.wine_shop.order.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    @JsonProperty("nome_consumidor")
    private String consumerName;
    @JsonProperty("cpf_consumidor")
    private String consumerCpf;
    @JsonProperty("quantidade_produto")
    private Integer productQuantity;
    @JsonProperty("preco_produto")
    private Double productPrice;
    @JsonProperty("safra_produto")
    private String productHarvest;
    @JsonProperty("ano_compra_produto")
    private Integer productPurchaseYear;
    @JsonProperty("tipo_vinho_produto")
    private String productWineType;
    @JsonProperty("quantidade_pedido")
    private Integer orderQuantity;
    @JsonProperty("preco_total_pedido")
    private Double orderTotalPrice;
}

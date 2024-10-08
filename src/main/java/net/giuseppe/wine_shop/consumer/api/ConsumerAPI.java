package net.giuseppe.wine_shop.consumer.api;

import feign.RequestLine;
import net.giuseppe.wine_shop.consumer.dto.ConsumerResponseDTO;

import java.util.List;

public interface ConsumerAPI {

    @RequestLine("GET /clientes-Vz1U6aR3GTsjb3W8BRJhcNKmA81pVh.json")
    List<ConsumerResponseDTO> getConsumerList();
}

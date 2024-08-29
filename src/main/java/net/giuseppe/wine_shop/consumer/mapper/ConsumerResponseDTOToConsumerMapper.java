package net.giuseppe.wine_shop.consumer.mapper;

import net.giuseppe.wine_shop.consumer.dto.ConsumerResponseDTO;
import net.giuseppe.wine_shop.consumer.model.Consumer;
import net.giuseppe.wine_shop.order.mapper.OrderResponseDTOToOrderMapper;

import java.util.List;
import java.util.stream.Collectors;

import static net.giuseppe.wine_shop.order.mapper.OrderResponseDTOToOrderMapper.*;

public abstract class ConsumerResponseDTOToConsumerMapper {

    public static Consumer mapToConsumer(ConsumerResponseDTO dto) {
        return Consumer.builder()
                .name(dto.getName())
                .cpf(dto.getCpf())
                .order(mapToOrder(dto.getOrder()))
                .build();
    }

    public static List<Consumer> mapToConsumer(List<ConsumerResponseDTO> dtoList) {
        return dtoList.stream()
                .map(ConsumerResponseDTOToConsumerMapper::mapToConsumer)
                .collect(Collectors.toList());
    }
}

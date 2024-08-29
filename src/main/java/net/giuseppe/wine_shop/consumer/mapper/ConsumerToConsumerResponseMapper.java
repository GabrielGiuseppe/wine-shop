package net.giuseppe.wine_shop.consumer.mapper;

import net.giuseppe.wine_shop.consumer.model.Consumer;
import net.giuseppe.wine_shop.consumer.response.ConsumerResponse;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ConsumerToConsumerResponseMapper {

    public static ConsumerResponse map(Consumer consumer) {
        return ConsumerResponse.builder()
                .name(consumer.getName())
                .cpf(consumer.getCpf())
                .orderQuantity(consumer.getOrder().size())
                .build();
    }

    public static List<ConsumerResponse> map(List<Consumer> consumers) {
        return consumers.stream()
                .map(ConsumerToConsumerResponseMapper::map)
                .collect(Collectors.toList());
    }
}

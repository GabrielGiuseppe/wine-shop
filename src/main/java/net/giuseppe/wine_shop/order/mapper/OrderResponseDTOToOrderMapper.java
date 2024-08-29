package net.giuseppe.wine_shop.order.mapper;

import net.giuseppe.wine_shop.order.dto.OrderResponseDTO;
import net.giuseppe.wine_shop.order.model.Order;

import java.util.List;
import java.util.stream.Collectors;

public abstract class OrderResponseDTOToOrderMapper {

    public static Order mapToOrder(OrderResponseDTO dto) {
        return Order.builder()
                .code(dto.getCode())
                .quantity(dto.getQuantity())
                .build();
    }

    public static List<Order> mapToOrder(List<OrderResponseDTO> dtoList) {
        return dtoList.stream()
                .map(OrderResponseDTOToOrderMapper::mapToOrder)
                .collect(Collectors.toList());
    }
}

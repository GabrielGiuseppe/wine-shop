package net.giuseppe.wine_shop.consumer.service.impl;

import net.giuseppe.wine_shop.common.exception.BaseException;
import net.giuseppe.wine_shop.common.response.BaseResponse;
import net.giuseppe.wine_shop.consumer.model.Consumer;
import net.giuseppe.wine_shop.consumer.service.ClientWineTypeRecomendationService;
import net.giuseppe.wine_shop.consumer.service.ConsumerService;
import net.giuseppe.wine_shop.order.response.OrderResponse;
import net.giuseppe.wine_shop.order.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static net.giuseppe.wine_shop.common.util.ExceptionConstants.NO_CONSUMER_FOUND;
import static net.giuseppe.wine_shop.common.util.StringUtil.removeUnderline;

@Service
public class ClientWineTypeRecomendationServiceImpl implements ClientWineTypeRecomendationService {

    @Autowired
    OrderService orderService;

    @Override
    public BaseResponse getWineTypeRecomendation(String consumerName) {
           var consumerOrders = getConsumerOrdersByName(consumerName);
           var wineTypeRecomendation = getWineTypeRecomendationByMostOrederedWineType(consumerOrders);
           return new BaseResponse<>(wineTypeRecomendation);
    }

    @Override
    public BaseResponse getGeneralWineTypeRecomendation() {
        var consumerOrders = getOrderList();
        var wineTypeRecomendation = getWineTypeRecomendationByMostOrederedWineType(consumerOrders);
        return new BaseResponse<>(wineTypeRecomendation);
    }

    private List<OrderResponse> getOrderList() {
        var orderData = orderService.getOrderList();
        if (orderData.data == null || !orderData.success) {
            throw new BaseException(HttpStatus.NOT_FOUND, NO_CONSUMER_FOUND);
        }
        return (List<OrderResponse>) orderData.data;
    }

    private String getWineTypeRecomendationByMostOrederedWineType(List<OrderResponse> consumerOrders) {
        var wineTypeMap = consumerOrders.stream()
                .collect(Collectors.groupingBy(OrderResponse::getProductWineType, Collectors.counting()));
        return wineTypeMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    private List<OrderResponse> getConsumerOrdersByName(String consumerName) {
        var consumerOrderData = orderService.getConsumerOrdersByName(removeUnderline(consumerName));
        if (consumerOrderData.data == null || !consumerOrderData.success) {
            throw new BaseException(HttpStatus.NOT_FOUND, NO_CONSUMER_FOUND);
        }
        return (List<OrderResponse>) consumerOrderData.data;
    }

}

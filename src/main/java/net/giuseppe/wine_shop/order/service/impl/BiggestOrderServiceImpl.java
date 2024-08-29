package net.giuseppe.wine_shop.order.service.impl;

import net.giuseppe.wine_shop.common.exception.BaseException;
import net.giuseppe.wine_shop.common.response.BaseResponse;
import net.giuseppe.wine_shop.order.response.OrderResponse;
import net.giuseppe.wine_shop.order.service.BiggestOrderService;
import net.giuseppe.wine_shop.order.service.OrderService;
import net.giuseppe.wine_shop.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static net.giuseppe.wine_shop.common.util.ExceptionConstants.NO_PRODUCT_FOUND;

@Service
public class BiggestOrderServiceImpl implements BiggestOrderService {

    @Autowired
    OrderService orderService;


    @Override
    public BaseResponse getBiggestOrderForYear(Integer year) {
        List<OrderResponse> orders = getOrderList();
        var biggestOrder = getBiggestOrder(year, orders);
        return new BaseResponse(biggestOrder);
    }

    private static OrderResponse getBiggestOrder(Integer year, List<OrderResponse> orders) {
        return orders.stream()
                .filter(order -> order.getProductPurchaseYear().equals(year))
                .max(Comparator.comparing(OrderResponse::getOrderTotalPrice))
                .orElse(null);
    }

    private List<OrderResponse> getOrderList() {
        var orderData = orderService.getOrderList();
        if (!orderData.success || orderData.data == null) {
            throw new BaseException(HttpStatus.NOT_FOUND, NO_PRODUCT_FOUND);
        }
        return (List<OrderResponse>) orderData.data;
    }
}

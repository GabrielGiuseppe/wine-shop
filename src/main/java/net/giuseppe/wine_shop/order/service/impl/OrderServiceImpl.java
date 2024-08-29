package net.giuseppe.wine_shop.order.service.impl;

import net.giuseppe.wine_shop.common.exception.BaseException;
import net.giuseppe.wine_shop.common.response.BaseResponse;
import net.giuseppe.wine_shop.consumer.model.Consumer;
import net.giuseppe.wine_shop.consumer.service.ConsumerService;
import net.giuseppe.wine_shop.order.model.Order;
import net.giuseppe.wine_shop.order.response.OrderResponse;
import net.giuseppe.wine_shop.order.service.OrderService;
import net.giuseppe.wine_shop.product.api.ProductAPI;
import net.giuseppe.wine_shop.product.model.Product;
import net.giuseppe.wine_shop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static net.giuseppe.wine_shop.common.util.ExceptionConstants.NO_CONSUMER_FOUND;
import static net.giuseppe.wine_shop.common.util.ExceptionConstants.NO_PRODUCT_FOUND;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    ConsumerService costumerService;
    @Autowired
    ProductService productService;

    @Override
    public BaseResponse getOrderList() {
        List<Consumer> consumerList = getConsumerList();
        List<Product> productList = getProductList();
        var orderList = fillOrderList(consumerList, productList);
        var sortedOrderList = sortList(orderList);
        return new BaseResponse<>(sortedOrderList);
    }

    @Override
    public BaseResponse getConsumerOrdersByName(String consumerName) {
        var orderData = getOrderList();
        if (orderData.data == null || !orderData.success) {
            throw new BaseException(HttpStatus.NOT_FOUND, NO_CONSUMER_FOUND);
        }
        var orderList = (List<OrderResponse>) orderData.data;
        return new BaseResponse<>(orderList.stream()
                .filter(order -> order.getConsumerName().equalsIgnoreCase(consumerName))
                .collect(Collectors.toList()));
    }

    private List<OrderResponse> sortList(List<OrderResponse> orderList) {
        orderList.sort((totalPrice1, totalPrice2) -> {
            if (totalPrice1.getOrderTotalPrice() > totalPrice2.getOrderTotalPrice()) {
                return -1;
            } else if (totalPrice1.getOrderTotalPrice() < totalPrice2.getOrderTotalPrice()) {
                return 1;
            }
            return 0;
        });
        return orderList;
    }

    private List<OrderResponse> fillOrderList(List<Consumer> consumerList, List<Product> productList) {
        var orderList = new ArrayList<OrderResponse>();
        consumerList.forEach(consumer -> {
            consumer.getOrder().forEach(order -> {
                var orderProduct = getProduct(productList, order);
                var orderResponse = OrderResponse.builder()
                        .consumerName(consumer.getName())
                        .consumerCpf(consumer.getCpf())
                        .productQuantity(order.getQuantity())
                        .productPrice(orderProduct.getPrice())
                        .productHarvest(orderProduct.getHarvest())
                        .productPurchaseYear(orderProduct.getPurchaseYear())
                        .productWineType(orderProduct.getWineType())
                        .orderQuantity(order.getQuantity())
                        .orderTotalPrice(order.getQuantity() * orderProduct.getPrice())
                        .build();
                orderList.add(orderResponse);
            });
        });
        return orderList;
    }

    private static Product getProduct(List<Product> productList, Order order) {
        return productList.stream()
                .filter(product -> product.getCode().toString().equals(order.getCode()))
                .findFirst()
                .orElse(null);
    }

    private List<Product> getProductList() {
        var productListData = productService.getProductList();
        if (!productListData.success || productListData.data == null) {
            throw new BaseException(HttpStatus.NOT_FOUND, NO_PRODUCT_FOUND);
        }
        return (List<Product>) productListData.data;
    }

    private List<Consumer> getConsumerList() {
        var consumerListData = costumerService.getconsumerList();
        if (!consumerListData.success || consumerListData.data == null) {
            throw new BaseException(HttpStatus.NOT_FOUND, NO_CONSUMER_FOUND);
        }
        return (List<Consumer>) consumerListData.data;
    }
}

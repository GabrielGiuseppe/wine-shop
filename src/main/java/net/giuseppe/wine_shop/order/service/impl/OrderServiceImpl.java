package net.giuseppe.wine_shop.order.service.impl;

import net.giuseppe.wine_shop.common.exception.BaseException;
import net.giuseppe.wine_shop.common.response.BaseResponse;
import net.giuseppe.wine_shop.consumer.service.ConsumerService;
import net.giuseppe.wine_shop.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static net.giuseppe.wine_shop.common.util.ExceptionConstants.NO_CONSUMER_FOUND;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    ConsumerService costumerService;

    @Override
    public BaseResponse getOrderList() {
        var consumerListData = costumerService.getcostumerList();
        if (!consumerListData.success || consumerListData.data == null) {
            throw new BaseException(HttpStatus.NOT_FOUND, NO_CONSUMER_FOUND);
        }
        var consumerList = consumerListData.data;
        return new BaseResponse<>(consumerList);
    }
}

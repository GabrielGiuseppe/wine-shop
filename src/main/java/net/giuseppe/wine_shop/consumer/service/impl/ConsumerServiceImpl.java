package net.giuseppe.wine_shop.consumer.service.impl;

import net.giuseppe.wine_shop.common.exception.BaseException;
import net.giuseppe.wine_shop.common.response.BaseResponse;
import net.giuseppe.wine_shop.consumer.api.ConsumerAPI;
import net.giuseppe.wine_shop.consumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static net.giuseppe.wine_shop.common.util.ExceptionConstants.NO_CONSUMER_FOUND;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    ConsumerAPI consumerAPI;

    @Override
    public BaseResponse getcostumerList() {
        var consumerList = consumerAPI.getConsumerList();
        if(consumerList == null || consumerList.isEmpty()) {
          throw new BaseException(HttpStatus.NOT_FOUND, NO_CONSUMER_FOUND);
        }
        return new BaseResponse(consumerList);
    }
}

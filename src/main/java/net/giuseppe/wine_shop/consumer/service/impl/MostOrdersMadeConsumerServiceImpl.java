package net.giuseppe.wine_shop.consumer.service.impl;

import net.giuseppe.wine_shop.common.exception.BaseException;
import net.giuseppe.wine_shop.common.response.BaseResponse;
import net.giuseppe.wine_shop.consumer.mapper.ConsumerToConsumerResponseMapper;
import net.giuseppe.wine_shop.consumer.model.Consumer;
import net.giuseppe.wine_shop.consumer.service.ConsumerService;
import net.giuseppe.wine_shop.consumer.service.MostOrdersMadeConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.giuseppe.wine_shop.common.util.ExceptionConstants.NO_CONSUMER_FOUND;

@Service
public class MostOrdersMadeConsumerServiceImpl implements MostOrdersMadeConsumerService {

    @Autowired
    ConsumerService consumerService;

    @Override
    public BaseResponse getTop3MostOrdersMadeConsumer() {
        var consumers = getConsumers();
        var top3Consumers = getTop3Consumers(consumers);
        var response = ConsumerToConsumerResponseMapper.map(top3Consumers);
        return new BaseResponse(response);
    }

    private List<Consumer> getTop3Consumers(List<Consumer> consumers) {
        consumers.sort((consumer1, consumer2) -> {
            if (consumer1.getOrder().size() > consumer2.getOrder().size()) {
                return -1;
            } else if (consumer1.getOrder().size() < consumer2.getOrder().size()) {
                return 1;
            }
            return 0;
        });
        return consumers.subList(0, 3);
    }

    private List<Consumer> getConsumers() {
        var consumerListData = consumerService.getconsumerList();
        if (!consumerListData.success || consumerListData.data == null) {
            throw new BaseException(HttpStatus.NOT_FOUND, NO_CONSUMER_FOUND);
        }
        return (List<Consumer>) consumerListData.data;
    }
}

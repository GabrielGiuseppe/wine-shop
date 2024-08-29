package net.giuseppe.wine_shop.consumer.service;

import net.giuseppe.wine_shop.common.response.BaseResponse;

public interface MostOrdersMadeConsumerService {
    BaseResponse getTop3MostOrdersMadeConsumer();
}

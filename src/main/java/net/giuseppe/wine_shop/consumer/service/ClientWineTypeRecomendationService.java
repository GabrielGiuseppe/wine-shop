package net.giuseppe.wine_shop.consumer.service;

import net.giuseppe.wine_shop.common.response.BaseResponse;

public interface ClientWineTypeRecomendationService {
    BaseResponse getWineTypeRecomendation(String consumerName);

    BaseResponse getGeneralWineTypeRecomendation();
}

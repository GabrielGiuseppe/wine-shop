package net.giuseppe.wine_shop.consumer.service.impl;

import net.giuseppe.wine_shop.common.exception.BaseException;
import net.giuseppe.wine_shop.common.response.BaseResponse;
import net.giuseppe.wine_shop.consumer.api.ConsumerAPI;
import net.giuseppe.wine_shop.consumer.dto.ConsumerResponseDTO;
import net.giuseppe.wine_shop.consumer.mapper.ConsumerResponseDTOToConsumerMapper;
import net.giuseppe.wine_shop.consumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.giuseppe.wine_shop.common.util.ExceptionConstants.NO_CONSUMER_FOUND;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    ConsumerAPI consumerAPI;

    @Override
    public BaseResponse getconsumerList() {
        var apiData = consumerAPI.getConsumerList();
        if(apiData == null || apiData.isEmpty()) {
          throw new BaseException(HttpStatus.NOT_FOUND, NO_CONSUMER_FOUND);
        }
        var consumerList = ConsumerResponseDTOToConsumerMapper.mapToConsumer(apiData);
        return new BaseResponse<>(consumerList);
    }

    private ConsumerResponseDTO filterConsumerByName(List<ConsumerResponseDTO> apiData, String consumerName) {
        return apiData.stream()
                .filter(consumer -> consumer.getName().equalsIgnoreCase(consumerName))
                .findFirst()
                .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND, NO_CONSUMER_FOUND));
    }
}

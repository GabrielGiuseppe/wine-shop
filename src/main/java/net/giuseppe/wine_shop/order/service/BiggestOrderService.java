package net.giuseppe.wine_shop.order.service;

import net.giuseppe.wine_shop.common.response.BaseResponse;

public interface BiggestOrderService {
    BaseResponse getBiggestOrderForYear(Integer year);
}

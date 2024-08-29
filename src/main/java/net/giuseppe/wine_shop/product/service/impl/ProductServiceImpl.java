package net.giuseppe.wine_shop.product.service.impl;

import net.giuseppe.wine_shop.common.exception.BaseException;
import net.giuseppe.wine_shop.common.response.BaseResponse;
import net.giuseppe.wine_shop.consumer.api.ConsumerAPI;
import net.giuseppe.wine_shop.product.api.ProductAPI;
import net.giuseppe.wine_shop.product.mapper.ProductResponseDTOToProductMapper;
import net.giuseppe.wine_shop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static net.giuseppe.wine_shop.common.util.ExceptionConstants.NO_CONSUMER_FOUND;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductAPI productAPI;

    @Override
    public BaseResponse getProductList() {
        var apiData = productAPI.getProductList();
        if(apiData == null || apiData.isEmpty()) {
            throw new BaseException(HttpStatus.NOT_FOUND, NO_CONSUMER_FOUND);
        }
        var productList = ProductResponseDTOToProductMapper.mapToProduct(apiData);
        return new BaseResponse<>(productList);
    }
}

package net.giuseppe.wine_shop.product.api;

import feign.RequestLine;
import net.giuseppe.wine_shop.product.dto.ProductResponseDTO;

import java.util.List;

public interface ProductAPI {

    @RequestLine("GET /produtos-mnboX5IPl6VgG390FECTKqHsD9SkLS.json")
    List<ProductResponseDTO> getProductList();
}

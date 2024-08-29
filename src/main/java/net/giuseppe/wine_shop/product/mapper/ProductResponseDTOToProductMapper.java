package net.giuseppe.wine_shop.product.mapper;

import net.giuseppe.wine_shop.product.dto.ProductResponseDTO;
import net.giuseppe.wine_shop.product.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ProductResponseDTOToProductMapper {

    public static List<Product> mapToProduct(List<ProductResponseDTO> productResponseDTOList) {
        return productResponseDTOList.stream()
                .map(ProductResponseDTOToProductMapper::mapToProduct)
                .collect(Collectors.toList());
    }

    public static Product mapToProduct(ProductResponseDTO dto) {
        return Product.builder()
                .code(dto.getCode())
                .wineType(dto.getWineType())
                .price(dto.getPrice())
                .harvest(dto.getHarvest())
                .purchaseYear(dto.getPurchaseYear())
                .build();
    }
}

package dev.ecagataydogan.productservice.mapper;

import dev.ecagataydogan.productservice.dto.response.ProductPurchaseResponse;
import dev.ecagataydogan.productservice.dto.response.ProductResponse;
import dev.ecagataydogan.productservice.entity.Product;

public class ProductMapper {

    private ProductMapper() {
    }

    public static ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .availableQuantity(product.getAvailableQuantity())
                .price(product.getPrice())
                .category(CategoryMapper.toResponse(product.getCategory()))
                .build();
    }

    public static ProductPurchaseResponse toproductPurchaseResponse(Product product, double quantity) {
        return ProductPurchaseResponse.builder()
                .productId(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(quantity)
                .build();
    }
}

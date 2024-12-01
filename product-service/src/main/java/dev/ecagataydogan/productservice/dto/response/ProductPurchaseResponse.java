package dev.ecagataydogan.productservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductPurchaseResponse {

    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private double quantity;
}

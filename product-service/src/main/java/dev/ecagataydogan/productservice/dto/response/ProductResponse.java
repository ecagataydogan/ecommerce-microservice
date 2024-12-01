package dev.ecagataydogan.productservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private double availableQuantity;
    private BigDecimal price;
    private CategoryResponse category;
}

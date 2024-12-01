package dev.ecagataydogan.productservice.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductPurchaseRequest {

    @NotNull
    private Long productId;

    @Positive
    double quantity;
}

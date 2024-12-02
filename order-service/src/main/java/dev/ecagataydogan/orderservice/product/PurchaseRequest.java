package dev.ecagataydogan.orderservice.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PurchaseRequest {

    @NotNull
    private Long productId;

    @Positive
    double quantity;
}

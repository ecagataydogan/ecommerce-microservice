package dev.ecagataydogan.orderservice.order.dto.request;

import dev.ecagataydogan.orderservice.order.entity.PaymentMethod;
import dev.ecagataydogan.orderservice.product.PurchaseRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {

    @NotNull(message = "Payment method should be precised")
    private PaymentMethod paymentMethod;

    @NotEmpty
    List<PurchaseRequest> products;
}

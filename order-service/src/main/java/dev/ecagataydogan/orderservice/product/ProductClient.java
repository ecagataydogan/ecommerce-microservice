package dev.ecagataydogan.orderservice.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(
        name = "product-service",
        url = "${application.config.product-url}"
)
public interface ProductClient {

    @PostMapping
    List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestBody);
}

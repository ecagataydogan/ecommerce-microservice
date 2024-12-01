package dev.ecagataydogan.productservice.controller;

import dev.ecagataydogan.productservice.dto.request.ProductPurchaseRequest;
import dev.ecagataydogan.productservice.dto.response.ProductPurchaseResponse;
import dev.ecagataydogan.productservice.dto.response.ProductResponse;
import dev.ecagataydogan.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductResponse> findAll() {
        return productService.findAll();
    }

    @PostMapping("/purchase")
    public List<ProductPurchaseResponse> purchaseProducts(@Valid @RequestBody List<ProductPurchaseRequest> productPurchaseRequests) {
        return productService.purchaseProducts(productPurchaseRequests);
    }
}

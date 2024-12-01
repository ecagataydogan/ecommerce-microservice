package dev.ecagataydogan.productservice.service;

import dev.ecagataydogan.productservice.dto.request.ProductPurchaseRequest;
import dev.ecagataydogan.productservice.dto.response.ProductPurchaseResponse;
import dev.ecagataydogan.productservice.dto.response.ProductResponse;
import dev.ecagataydogan.productservice.entity.Product;
import dev.ecagataydogan.productservice.exception.BusinessException;
import dev.ecagataydogan.productservice.exception.ErrorCode;
import dev.ecagataydogan.productservice.mapper.ProductMapper;
import dev.ecagataydogan.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream()
                .map(ProductMapper::toResponse).toList();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> productPurchaseRequests) {
        List<Long> productIds = productPurchaseRequests.stream()
                .map(ProductPurchaseRequest::getProductId)
                .toList();
        List<Product> storedProducts = productRepository.findAllByIdInOrderById(productIds);
        if (productIds.size() != storedProducts.size()) {
            throw new BusinessException(ErrorCode.not_found, "One or more products does not exist");
        }

        List<ProductPurchaseRequest> sortedRequests = productPurchaseRequests
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::getProductId))
                .toList();

        List<ProductPurchaseResponse> purchasedProducts = new ArrayList<>();
        for (int i = 0; i < storedProducts.size(); i++) {
            Product product = storedProducts.get(i);
            ProductPurchaseRequest productPurchaseRequest = sortedRequests.get(i);
            if (product.getAvailableQuantity() < productPurchaseRequest.getQuantity()) {
                throw new BusinessException(ErrorCode.conflict, "Insufficient stock quantity for product with ID:: " + productPurchaseRequest.getProductId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productPurchaseRequest.getQuantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(ProductMapper.toproductPurchaseResponse(product, productPurchaseRequest.getQuantity()));
        }
        return purchasedProducts;
    }
}

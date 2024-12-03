package dev.ecagataydogan.notificationservice.kafka.order;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PurchaseResponse {

    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private double quantity;
}

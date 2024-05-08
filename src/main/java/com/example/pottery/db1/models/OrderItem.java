package com.example.pottery.db1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_item")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @EmbeddedId
    private OrderItemId orderItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JsonIgnore
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    @Min(value = 1, message = "Quantity must be greater than 0")
    private int quantity;
    @Column(name = "list_price")
    private double listPrice;
}

package com.example.pottery.db1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "list_price")
    private double listPrice;
    private int quantity;

    @OneToMany(mappedBy = "product")
    private Set<OrderItem> orderItems;
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
}

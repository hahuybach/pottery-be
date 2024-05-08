package com.example.pottery.db1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {
    @EmbeddedId
    private AccountProduct accountProduct;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("productDetailId")
    @JoinColumn(name = "product_detail_id")
    @JsonIgnore
    private ProductDetail productDetail;
    private Integer quantity;

}

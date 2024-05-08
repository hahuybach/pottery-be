package com.example.pottery.db1.models;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class AccountProduct implements Serializable {
    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "product_detail_id")
    private Long productDetailId;

}

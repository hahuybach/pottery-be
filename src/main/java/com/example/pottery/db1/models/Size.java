package com.example.pottery.db1.models;

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
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "size_width")
    private Double sizeWidth;
    @Column(name = "size_height")
    private Double sizeHeight;
    @Column(name = "size_depth")
    private Double sizeDepth;
    @OneToMany(mappedBy = "size")
    private Set<Product> products;

}

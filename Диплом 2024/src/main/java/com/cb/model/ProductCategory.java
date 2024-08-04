package com.cb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productcategory")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product_category", nullable = false)
    private Integer id_product_category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_category")
    @JsonBackReference
    private CategoryProd id_category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_product")
    @JsonBackReference
    private Product id_product;
    @Column(name = "deleted", length = 50)
    private Boolean delete=false;

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id_product_category=" + id_product_category +
                ", id_category=" + id_category +
                ", id_product=" + id_product +
                ", delete=" + delete +
                '}';
    }
}

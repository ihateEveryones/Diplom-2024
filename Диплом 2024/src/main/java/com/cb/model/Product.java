package com.cb.model;

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
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product", nullable = false)
    private Integer id_product;

    @Column(name = "name_product", length = 45)
    private String name_product;
    @Column(name = "price", length = 45)
    private Double price;


    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "deleted", length = 50)
    private boolean delete=false;

    @Column(name = "productImage", nullable = false, length = 256)
    private String productImage;
    public Product(String name_product) {
        this.name_product = name_product;

    }

    @Override
    public String toString() {
        return "Product{" +
                "id_product=" + id_product +
                ", name_product='" + name_product + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", delete=" + delete +
                '}';
    }
}

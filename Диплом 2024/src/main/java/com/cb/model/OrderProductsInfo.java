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
@Table(name = "order_products_info")
public class OrderProductsInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_order", referencedColumnName = "id_orders")
    private Orders id_order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_product_category")
    private ProductCategory id_product_category;

    @Override
    public String toString() {
        return "OrderProductsInfo{" +
                "id=" + id +
                ", id_order=" + id_order +
                ", id_product_category=" + id_product_category +
                '}';
    }
}

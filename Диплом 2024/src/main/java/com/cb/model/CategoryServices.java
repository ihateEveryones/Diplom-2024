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
@Table(name = "categoryservices")
public class CategoryServices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category_services", nullable = false)
    private Integer id_category_services;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_category")
    @JsonBackReference
    private Category id_category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_service")
    @JsonBackReference
    private Services id_service;

    @Column(name = "deleted", columnDefinition = "BOOLEAN default 0")
    private boolean delete;

    @Override
    public String toString() {
        return "CategoryServices{" +
                "id_category_services=" + id_category_services +
//                ", id_category=" + id_category +
//                ", id_service=" + id_service +
//                ", delete=" + delete +
                '}';
    }
}

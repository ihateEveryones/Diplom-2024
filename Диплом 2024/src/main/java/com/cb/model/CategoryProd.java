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
@Table(name = "categoryprod")
public class CategoryProd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category", nullable = false)
    private Integer id_category;

    @Column(name = "name_category", nullable = false)
    private String name_category;
    @Column(name = "deleted", length = 50)
    private Boolean delete=false;




}

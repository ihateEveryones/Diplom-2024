package com.cb.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category", nullable = false)
    private Integer id_category;

    @Column(name = "name_category", length = 45)
    private String name_category;

    @Column(name = "deleted", columnDefinition = "BOOLEAN default 0")
    private boolean delete;



    @OneToMany(fetch = FetchType.EAGER ,mappedBy = "id_category")
    @JsonManagedReference
    private List<CategoryServices> categoryServices;

    public Category(String name_category, Integer id_category) {
        this.name_category = name_category;
        this.id_category = id_category;

    }

    @Override
    public String toString() {
        return "Category{" +
                "id_category=" + id_category +
                ", name_category='" + name_category + '\'' +
                ", delete=" + delete +
                ", categoryServices=" + categoryServices +
                '}';
    }
}

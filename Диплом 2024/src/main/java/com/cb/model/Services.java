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
@Table(name = "services")
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_service", nullable = false)
    private Integer id_service;

    @Column(name = "name_service", length = 250)
    private String name_service;
    @Column(name = "price", length = 45)
    private Double price;
    @Column(name = "deleted", columnDefinition = "BOOLEAN default 0")
    private boolean delete;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "duration")
    private LocalTime duration;

    @OneToMany(fetch = FetchType.EAGER ,mappedBy = "id_service")
    @JsonManagedReference
    private List<CategoryServices> service;
    public Services(String name_service) {
        this.name_service = name_service;

    }

    @Override
    public String toString() {
        return "Services{" +
                "id_service=" + id_service +
                ", name_service='" + name_service + '\'' +
                ", price=" + price +
                ", delete=" + delete +
                ", service=" + service +
                '}';
    }
}

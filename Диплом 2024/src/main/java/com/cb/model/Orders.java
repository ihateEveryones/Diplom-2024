package com.cb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orders", nullable = false)
    private Integer id_orders;
    


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User id_user;

    @ManyToOne
    @JoinColumn(name = "id_master", referencedColumnName = "id_user")
    private User id_master;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "id_category_services")
//    private CategoryServices id_category_services;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "id_product_category")
//    private ProductCategory id_product_category;
//
//    @OneToMany(mappedBy = "orders")
//    private List<OrderInfo> orderInfos;


//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "id_schedules")
//    private MastersWorkSchedules id_schedules;

    @Column(name = "date")
    private Date date;

    @Column(name = "start_time")
    private LocalTime start_time ;

    @Column(name = "end_time")
    private LocalTime end_time ;

    @Column(name = "total_price")
    private Double total_price ;

    public Orders(User id_user) {
        this.id_user = id_user;

    }

    @Override
    public String toString() {
        return "Orders{" +
                "id_orders=" + id_orders +
                ", id_user=" + id_user +
                ", id_master=" + id_master +
                ", date=" + date +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", total_price=" + total_price +
                '}';
    }
}

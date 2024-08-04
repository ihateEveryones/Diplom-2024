package com.cb.repository;

import com.cb.model.Orders;
import com.cb.model.User;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
    @Query("SELECT distinct o.date,o.start_time,o.end_time FROM Orders o ")
    List<Object> findByMasterId();

    @Query("SELECT distinct o.id_orders,o.id_master,o.date, o.start_time FROM Orders o WHERE o.id_user.id = :id_user")
    List<Object[]> findOrdersById(@Param("id_user") Integer id_user);

//    @Query("SELECT distinct  o.id_master, o.id_category_services, o.date, o.start_time , o.id_orders FROM Orders o WHERE o.id_user.id = :id_user")
//    List<Object[]> findOrdersByUserId(@Param("id_user") Integer id_user);

    @Query("SELECT distinct  o FROM Orders o WHERE o.id_user.id = :id_user")
    List<Orders> findOrdersByUserId(@Param("id_user") Integer id_user);

    @Query("SELECT distinct  o FROM Orders o WHERE o.id_master.id = :id_master")
    List<Orders> findOrdersByMasterId(@Param("id_master") Integer id_master);

    @Query("SELECT o FROM Orders o WHERE o.id_orders = :idOrders")
    List<Orders> findIdOrders(Integer idOrders);

    @Query("SELECT o FROM Orders o WHERE o.id_orders = :idOrders")
    List<Orders> findAllByIdOrders(Integer idOrders);

    @Query("SELECT m.name AS master_name, COUNT(o.id_orders) AS total_orders, SUM(o.total_price) AS total_sales " +
            "FROM Orders o " +
            "LEFT JOIN o.id_master m " +
            "GROUP BY m.name " +
            "ORDER BY total_orders DESC")
    List<Object[]> getMasterOrderStatistics();
//
//    @Query("SELECT cat.name_category AS categoryName, " +
//            "ser.name_service AS serviceName, " +
//            "COUNT(o.id_orders) AS totalOrders, " +
//            "SUM(o.total_price) AS totalSales " +
//            "FROM Orders o " +
//            "LEFT JOIN o.id_category_services cs " +
//            "LEFT JOIN cs.id_category cat " +
//            "LEFT JOIN cs.id_service ser " +
//            "GROUP BY categoryName, serviceName " +
//            "ORDER BY totalOrders DESC")
//    List<Object[]> getCategoryServiceStatistics();

    @Query("SELECT cat.name_category AS categoryName, " +
            "ser.name_service AS serviceName, " +
            "COUNT(o.id_orders) AS totalOrders, " +
            "SUM(o.total_price) AS totalSales " +
            "FROM Orders o " +
            "LEFT JOIN OrderServicesInfo osi ON o.id_orders = osi.id_order.id_orders " +
            "LEFT JOIN CategoryServices cs ON osi.id_category_services.id_category_services = cs.id_category_services " +
            "LEFT JOIN Category cat ON cs.id_category.id_category = cat.id_category " +
            "LEFT JOIN Services ser ON cs.id_service.id_service = ser.id_service " +
            "GROUP BY cat.name_category, ser.name_service " +
            "ORDER BY totalOrders DESC")
    List<Object[]> getCategoryServiceStatistics();

    @Query("SELECT u.id, u.name, u.email,u.image,u.description_master, COUNT(o.id_orders) AS totalOrders " +
            "FROM User u " +
            "LEFT JOIN Orders o ON u.id = o.id_master.id " +
            "WHERE u.id_roles.id_roles = 3 " +
            "GROUP BY u.id, u.name, u.email " +
            "ORDER BY totalOrders DESC")
    List<Object[]> findTop3MasterWithMostOrders();
//
    @Query("SELECT DATE(o.date) AS orderDate, " +
            "SUM(o.total_price) AS totalSales " +
            "FROM Orders o " +
            "WHERE o.date BETWEEN :startDate AND :endDate " +
            "GROUP BY DATE(o.date) " +
            "ORDER BY DATE(o.date)")
    List<Object[]> getDailySalesForPeriod(@Param("startDate") Date startDate, @Param("endDate") Date endDate);


}

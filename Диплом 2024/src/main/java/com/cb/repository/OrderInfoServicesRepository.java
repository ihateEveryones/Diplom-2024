package com.cb.repository;

import com.cb.model.OrderServicesInfo;
import com.cb.model.Orders;
import com.cb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderInfoServicesRepository extends JpaRepository<OrderServicesInfo, Integer> {

    @Query("SELECT op FROM OrderServicesInfo op WHERE op.id_order = :orderId")
    List<OrderServicesInfo> getOrderServicesListByOrderId(@Param("orderId") Orders orderId);

    @Query("SELECT op FROM OrderServicesInfo op WHERE op.id_order.id_user = :idUser")
    List<OrderServicesInfo> findOrderServicesListByUserId(@Param("idUser") User idUser);

    @Query("SELECT op FROM OrderServicesInfo op WHERE op.id_order.id_master = :idMaster")
    List<OrderServicesInfo> findOrderServicesListByMasterId(@Param("idMaster") User idMaster);



}

package com.cb.repository;

import com.cb.model.OrderProductsInfo;
import com.cb.model.OrderServicesInfo;
import com.cb.model.Orders;
import com.cb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderInfoProductsRepository extends JpaRepository<OrderProductsInfo, Integer> {

    @Query("SELECT op FROM OrderProductsInfo op WHERE op.id_order = :orderId")
    List<OrderProductsInfo> getOrderProductListByOrderId(@Param("orderId") Orders orderId);


    @Query("SELECT op FROM OrderProductsInfo op WHERE op.id_order.id_user = :idUser")
    List<OrderProductsInfo> findOrderProductListByUserId(@Param("idUser") User idUser);
    @Query("SELECT op FROM OrderProductsInfo op WHERE op.id_order.id_master = :idMaster")
    List<OrderProductsInfo> findOrderProductListByMasterId(@Param("idMaster") User idMaster);

}

package com.example.demo.repository;

import com.example.demo.entity.SysOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<SysOrder,Integer> {
    @Query("select sysOrder from SysOrder  sysOrder where sysOrder.userId = :userId and sysOrder.goodsId = :goodsId")
    SysOrder findByUidAndGid(String userId, String goodsId);
}

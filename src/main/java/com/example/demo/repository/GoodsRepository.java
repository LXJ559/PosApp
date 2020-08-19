package com.example.demo.repository;

import com.example.demo.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GoodsRepository extends JpaRepository<Goods,Integer> {

    @Query("select goods from Goods goods where goods.goodsName = :goodsName")
    Goods findByName(Object goodsName);

    @Query("select goods from Goods goods where goods.id = :goodsId")
    Goods findGoodsById(String goodsId);

    @Query("select goods from Goods goods where goods.goodsId = :goodsId")
    Goods findGoodsByGoodsId(Integer goodsId);

    @Modifying
    @Transactional
    @Query("delete from Goods goods where goods.goodsId = :goodsId")
    void deleteGoodsByGoodsId(Integer goodsId);


}

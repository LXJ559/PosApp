package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (Goods)实体类
 * @author makejava
 * @since 2020-05-15 11:59:30
 */

@Entity
@Data
@Table(name = "goods")
public class Goods implements Serializable {
    private static final long serialVersionUID = -91043201296548088L;

    @Id
    @Column(name = "id")
    private String id;


    @Column(name = "goods_id")
    private int goodsId;

    @Column(name = "goods_name")
    private String goodsName;


    @Column(name = "price")
    private int price;

    @Column(name = "goods_count")
    private int goodsCount;
}
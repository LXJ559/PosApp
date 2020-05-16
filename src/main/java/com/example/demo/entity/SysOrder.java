package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (Order)实体类
 * @author makejava
 * @since 2020-05-15 20:48:26
 */
@Data
@Entity
@Table(name = "order_table")
public class SysOrder implements Serializable {
    private static final long serialVersionUID = -85377600322053632L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "userID")
    private String userId;

    @Column(name = "goodsID")
    private String goodsId;

    @Column(name = "count")
    private int count;
}
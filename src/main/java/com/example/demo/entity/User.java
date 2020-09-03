package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (User)实体类
 * @author makejava
 * @since 2020-04-28 17:22:07
 */
@Entity
@Data
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = -67334256645128488L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;


}
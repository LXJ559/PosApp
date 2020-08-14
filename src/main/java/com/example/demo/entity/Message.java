package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "message")
public class Message implements Serializable {
    private static final long serialVersionUID = -9104379991296548088L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "type")
    private String type;

    @Column(name = "content")
    private String content;
}

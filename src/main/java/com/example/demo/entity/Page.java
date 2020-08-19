package com.example.demo.entity;

import lombok.Data;

import java.util.List;

@Data
public class Page<T>{
    private int number;
    private int totalPages;
    private List<T> list;
}

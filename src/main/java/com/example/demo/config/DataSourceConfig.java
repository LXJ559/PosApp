package com.example.demo.config;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(value = "master")
    @Qualifier("master")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource master(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(value = "slaver1")
    @Qualifier("slaver1")
    @ConfigurationProperties(prefix = "spring.datasource.slaver1")
    public DataSource slaver1(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(value = "dataSource")
    public DataSourceRoutingDataSource dataSource() {
        DataSourceRoutingDataSource dataSource = new DataSourceRoutingDataSource();
        // 数据源
        Map<Object, Object> dataSources = new HashMap<>();
        dataSources.put("master", master());
        dataSources.put("slaver1", slaver1());
        dataSource.setTargetDataSources(dataSources);
        dataSource.setDefaultTargetDataSource(master());

        // 设置主数据源的键值；
        Set<Object> masterKeys = new HashSet<>();
        masterKeys.add("master");
        dataSource.setMasterKeys(masterKeys);
        // 设置从数据源的键值；
        Set<Object> slaverKeys = new HashSet<>();
        slaverKeys.add("slaver1");
        dataSource.setSlaverKeys(slaverKeys);
        return dataSource;
    }

}

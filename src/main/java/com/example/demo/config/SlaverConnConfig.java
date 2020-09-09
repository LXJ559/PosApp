/*
package com.example.demo.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactorySlaver",
        transactionManagerRef="transactionManagerSlaver",
        basePackages= { "com.example.demo.repositorybackup" }) //设置Repository所在位置
public class SlaverConnConfig {

    @Autowired
    @Qualifier("slaver1")
    private DataSource slaverDataSource;

    @Autowired
    private JpaProperties jpaProperties;
    @Autowired
    private HibernateProperties hibernateProperties;

    private Map<String, Object> getVendorProperties() {
        return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
    }


    @Bean(name = "entityManagerSlaver")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactorySlaver(builder).getObject().createEntityManager();
    }


    @Bean(name = "entityManagerFactorySlaver")
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySlaver (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(slaverDataSource)
                .packages("com.example.demo.entity") //设置实体类所在位置
                .persistenceUnit("slaverPersistenceUnit")
                .properties(getVendorProperties())
                .build();
    }


    @Bean(name = "transactionManagerSlaver")
    public PlatformTransactionManager transactionManagerSlaver(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactorySlaver(builder).getObject());
    }
}
*/

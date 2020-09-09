package com.example.demo.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactoryMasterSlaver",
        transactionManagerRef="transactionManagerMasterSlaver",
        basePackages= { "com.example.demo.repository" }) //设置Repository所在位置
public class DataSourceConnConfig {

    @Resource(name = "dataSource")
    private DataSource masterSlaverDataSource;

    @Autowired
    private JpaProperties jpaProperties;
    @Autowired
    private HibernateProperties hibernateProperties;

    private Map<String, Object> getVendorProperties() {
        return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
    }

    @Primary
    @Bean(name = "entityManagerMasterSlaver")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryMasterSlaver(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "entityManagerFactoryMasterSlaver")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryMasterSlaver (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(masterSlaverDataSource)
                .packages("com.example.demo.entity") //设置实体类所在位置
                .persistenceUnit("masterslaverPersistenceUnit")
                .properties(getVendorProperties())
                .build();
    }

    @Primary
    @Bean(name = "transactionManagerMasterSlaver")
    public PlatformTransactionManager transactionManagerMasterSlaver(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryMasterSlaver(builder).getObject());
    }
}

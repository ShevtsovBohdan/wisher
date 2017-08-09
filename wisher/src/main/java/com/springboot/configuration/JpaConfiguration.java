package com.springboot.configuration;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.springboot")
public class JpaConfiguration {

    @Bean
    public DriverManagerDataSource conferenceDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/db");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(conferenceDataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.connection.characterEncoding", "utf8");
        jpaProperties.setProperty("hibernate.connection.useUnicode", "true");
        jpaProperties.setProperty("hibernate.connection.charSet", "UTF-8");
        jpaProperties.setProperty("show_sql", "true");
        jpaProperties.setProperty("format_sql", "false");

        entityManagerFactoryBean.setPackagesToScan("domain");

        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }
}

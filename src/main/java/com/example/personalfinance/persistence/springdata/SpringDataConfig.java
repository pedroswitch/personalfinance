package com.example.personalfinance.persistence.springdata;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.personalfinance.persistence.springdata")
@ComponentScan("com.example.personalfinance.persistence.springdata")
public class SpringDataConfig
{
    LocalContainerEntityManagerFactoryBean entityManager;

    public LocalContainerEntityManagerFactoryBean entityManagerFactory()
    {
        entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
        entityManager.setPersistenceUnitName("personalfinance");
        entityManager.setPackagesToScan("com.example.personalfinance.persistence.datamodel");
        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return entityManager;
    }

    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory)
    {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}

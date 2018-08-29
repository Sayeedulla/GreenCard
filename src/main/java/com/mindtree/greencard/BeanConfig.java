package com.mindtree.greencard;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages="com.mindtree.greencard")
@EnableTransactionManagement
public class BeanConfig {
	
	
	@Bean
	public DataSource getDataSource() {
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/ayushi");
		dataSource.setUsername("root");
		dataSource.setPassword("Welcome123");
		return dataSource;
	}
	

	@Bean
	public LocalSessionFactoryBean getSessionFactoryBean() {
		
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();	
		sessionFactoryBean.setDataSource(getDataSource());
		Properties props = new Properties();
		props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.hbm2ddl.auto", "create");
		sessionFactoryBean.setHibernateProperties(props);
		sessionFactoryBean.setPackagesToScan(new String[] {"com.mindtree.greencard.model"});
		return sessionFactoryBean;
	}
	
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory s) {
		
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(s);
		return transactionManager;
		
	}
}

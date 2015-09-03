package com.sci.user.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.sci.user.dao.UserDAO;
import com.sci.user.dao.UserDAOImpl;
import com.sci.user.model.User;

@EnableWebMvc
@Configuration
@ComponentScan("com.sci.user")
@EnableTransactionManagement

public class ApplicationContextConfig extends WebMvcConfigurerAdapter {
	private static final Logger LOGGER = Logger.getLogger(ApplicationContextConfig.class);

    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
		LOGGER.info("Start ApplicationContextConfig : getViewResolver");
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
		LOGGER.info("End ApplicationContextConfig : getViewResolver");
        return viewResolver;
    }
     
    public @Value("${jdbc.url}") String dbUrl;
    public @Value("${jdbc.username}") String dbUserName;
    public @Value("${jdbc.password}") String dbPassword;
    public @Value ("${app.name}") String appName;
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        String propertiesFilename = "env.properties";

        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new ClassPathResource(propertiesFilename));

        return configurer;
    }
    
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
		LOGGER.info("Start ApplicationContextConfig : getDataSource");
    	BasicDataSource dataSource = new BasicDataSource();
    	dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    	dataSource.setUrl(dbUrl);
    	dataSource.setUsername(dbUserName);
    	dataSource.setPassword(dbPassword);
    	LOGGER.info("End ApplicationContextConfig : getDataSource");
    	return dataSource;
    }
    
    
    private Properties getHibernateProperties() {
		LOGGER.info("Start ApplicationContextConfig : getHibernateProperties");
    	Properties properties = new Properties();
    	properties.put("hibernate.show_sql", "true");
    	properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    	LOGGER.info("End ApplicationContextConfig : getHibernateProperties");
    	return properties;
    }

    
    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
		LOGGER.info("Start ApplicationContextConfig : getSessionFactory");
    	LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
    	sessionBuilder.addProperties(getHibernateProperties());
    	sessionBuilder.addAnnotatedClasses(User.class);
    	LOGGER.info("End ApplicationContextConfig : getSessionFactory");
    	return sessionBuilder.buildSessionFactory();
    }
    
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
			SessionFactory sessionFactory) {
		LOGGER.info("Start ApplicationContextConfig : getTransactionManager");
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(
				sessionFactory);
		LOGGER.info("End ApplicationContextConfig : getTransactionManager");
		return transactionManager;
	}
    
    @Autowired
    @Bean(name = "userDao")
    public UserDAO getUserDao(SessionFactory sessionFactory) {
		LOGGER.info("Start ApplicationContextConfig : getUserDao");
    	return new UserDAOImpl(sessionFactory);
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations(
				"/resources/");
	}

}

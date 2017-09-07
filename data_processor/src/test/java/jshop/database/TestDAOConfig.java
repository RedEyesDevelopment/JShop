package jshop.database;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import jshop.services.UserServiceImpl;
import jshop.storage.UserCache;
import net.sf.ehcache.CacheManager;
import net.sf.log4jdbc.Log4jdbcProxyDataSource;
import net.sf.log4jdbc.tools.Log4JdbcCustomFormatter;
import net.sf.log4jdbc.tools.LoggingType;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.*;
import java.util.Locale;
import java.util.Properties;

/**
 * Created by Gvozd on 06.01.2017.
 */
@ContextConfiguration
@EnableTransactionManagement
public class TestDAOConfig implements TransactionManagementConfigurer {

    private static final Logger LOGGER = Logger.getLogger(TestDAOConfig.class);

    private String driver;
    private String url;
    private String username;
    private String password;
    private String modelPackage;
    private String dialect;

    public TestDAOConfig() {

        driver = "org.postgresql.Driver";
        url = "jdbc:postgresql://192.168.99.100:5432/dbadmin";
        username = "dbadmin";
        password = "jEvPjsm";
        modelPackage = "jshop.model";
        dialect = "org.hibernate.dialect.PostgreSQLDialect";
    }

    @Bean
    public DataSource dataSource() {
        Log4jdbcProxyDataSource dataSource = new Log4jdbcProxyDataSource(realDataSource());
        Log4JdbcCustomFormatter log4JdbcCustomFormatter = new Log4JdbcCustomFormatter();
        log4JdbcCustomFormatter.setLoggingType(LoggingType.SINGLE_LINE);
        log4JdbcCustomFormatter.setSqlPrefix("SQL:::");
        dataSource.setLogFormatter(log4JdbcCustomFormatter);
        return dataSource;
    }

    @Bean
    public DataSource realDataSource() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            LOGGER.error(e);
        }
        try {
            comboPooledDataSource.setDriverClass(driver);
        } catch (PropertyVetoException e) {
            LOGGER.error(e);
        }
        comboPooledDataSource.setJdbcUrl(url);
        comboPooledDataSource.setUser(username);
        comboPooledDataSource.setPassword(password);
        comboPooledDataSource.setAutoCommitOnClose(false);

        //минимальный размер пула
        comboPooledDataSource.setMinPoolSize(5);
        //максимальный размер пула
        comboPooledDataSource.setMaxPoolSize(40);
        //начальный размер пула
        comboPooledDataSource.setInitialPoolSize(10);
        //сколько пулов разрешено взять поверх максимального числа
        comboPooledDataSource.setAcquireIncrement(10);
        //максимальное время получения содеинения под запрос
        comboPooledDataSource.setMaxIdleTime(300);
        //максимальное время жизни запроса
        comboPooledDataSource.setMaxConnectionAge(1200);
        //время простоя соединения, после которого оно уничтожается, пул сжимается до минимума
        comboPooledDataSource.setMaxIdleTimeExcessConnections(120);
        //время между повторами запроса на соединение
        comboPooledDataSource.setAcquireRetryDelay(1500);
        //размер кэша под preparestatements
        comboPooledDataSource.setMaxStatements(500);
        //размер кэша для одного соединения под preparestatements
        comboPooledDataSource.setMaxStatementsPerConnection(14);
        //время через которое проверяется соединение на состояние
        comboPooledDataSource.setIdleConnectionTestPeriod(300);
        //имя специальной таблицы для тестирования соединения с БД
        comboPooledDataSource.setAutomaticTestTable("c3p0DatabaseTestTable");
        comboPooledDataSource.setForceIgnoreUnresolvedTransactions(false);
        comboPooledDataSource.setAutoCommitOnClose(false);
        //c3p0 helper threads - специальные треды что чистят пулы и смотрят за ними
        comboPooledDataSource.setNumHelperThreads(10);

        return comboPooledDataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(modelPackage);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put(org.hibernate.cfg.Environment.DIALECT, dialect);
//        jpaProperties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "false");
//        jpaProperties.put("hibernate.cache.region.factory_class",org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory.class);
//        jpaProperties.put("hibernate.cache.use_second_level_cache", true);
//        jpaProperties.put("hibernate.cache.use_query_cache", true);
//        jpaProperties.put("net.sf.ehcache.configurationResourceName","/ehcache.xml");
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        entityManagerFactoryBean.setDataSource(dataSource());
        return entityManagerFactoryBean;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new JpaTransactionManager();
    }

    @Bean
    CacheManager cacheManager() {
        return new CacheManager();
    }

    @Bean
    UserServiceImpl userServiceImpl() {
        UserServiceImpl userService = new UserServiceImpl(userCache());
        userService.addNewTelemetryService(new PrintTelemetryService());
        return userService;
    }

    @Bean
    UserCache userCache() {
        return new UserCache();
    }
}

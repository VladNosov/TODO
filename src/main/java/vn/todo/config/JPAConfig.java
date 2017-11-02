package vn.todo.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan
@PropertySource({
        "classpath:db/postgres.properties"
})
public class JPAConfig {

    @Autowired
    Environment env;

    @Autowired
    private DataSource dataSource;

    private HibernateJpaVendorAdapter vendorAdapter;

    @Autowired
    public void initVendorAdapter() {
        vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(Boolean.valueOf(env.getProperty("jpa.showSql")));
    }

    @Bean("dataSource")
    public DataSource herokuDataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setUrl(env.getProperty("database.url"));
        dataSource.setUsername(env.getProperty("database.username"));
        dataSource.setPassword(env.getProperty("database.password"));
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setMaxActive(10);
        dataSource.setMinIdle(2);
        dataSource.setMaxWait(20000);
        dataSource.setInitialSize(2);
        dataSource.setMaxIdle(5);
        dataSource.setTestOnBorrow(true);
        dataSource.setRemoveAbandoned(true);
        dataSource.setTestOnConnect(true);
        dataSource.setTestWhileIdle(true);
        return dataSource;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(new ClassPathResource("db/populateDB.sql"));
        dataSourceInitializer.setDatabasePopulator(databasePopulator);
        dataSourceInitializer.setEnabled(Boolean.parseBoolean(env.getProperty("database.init")));
        return dataSourceInitializer;
    }

    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean managerFactory = new LocalContainerEntityManagerFactoryBean();
        managerFactory.setDataSource(dataSource);
        managerFactory.setPackagesToScan("vn.todo.domain");
        managerFactory.setJpaPropertyMap(jpaPropertyMap());
        managerFactory.setJpaVendorAdapter(vendorAdapter);
        return managerFactory;
    }

    private Map<String, Object> jpaPropertyMap() {
        Map<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        propertyMap.put("hibernate.use_sql_comments", env.getProperty("hibernate.use_sql_comments"));
        propertyMap.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory");
        propertyMap.put("hibernate.cache.use_second_level_cache", "true");
        propertyMap.put("hibernate.cache.use_query_cache", "false");
        propertyMap.put("net.sf.ehcache.configurationResourceName", "cache/ehcache.xml");
        propertyMap.put("javax.persistence.validation.group.pre-persist", "vn.todo.View$Persist");
        propertyMap.put("javax.persistence.validation.group.pre-update", "vn.todo.View$Persist");
        return propertyMap;
    }
}
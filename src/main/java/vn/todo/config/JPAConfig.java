package vn.todo.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan
@PropertySource("classpath:db/${spring.profiles.active}.properties")
public class JPAConfig {

    @Autowired
    Environment env;

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
}
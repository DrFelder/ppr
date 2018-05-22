package is.surreal.ppr.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Created by nydiarra on 06/05/17.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "is.surreal.ppr.repository")
public class DatasourceConfig {

    @Value("${ppr.database.username}")
    private String databaseUsername;

    @Value("${ppr.database.password}")
    private String databasePassword;

    @Value("${ppr.database.url}")
    private String databaseUrl;

    @Value("${ppr.database.driverClass}")
    private String databaseDriver;

    @Bean
    public DataSource datasource() throws PropertyVetoException {
        return DataSourceBuilder
                .create()
                .username(databaseUsername)
                .password(databasePassword)
                .url(databaseUrl)
                .driverClassName(databaseDriver)
                .build();

    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("datasource") DataSource ds) throws PropertyVetoException {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(ds);
        entityManagerFactory.setPackagesToScan("is.surreal.ppr.model");
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
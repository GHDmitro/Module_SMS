package modulesms.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(value = "modulesms",
        entityManagerFactoryRef = "smsEntityManagerFactory",
        transactionManagerRef = "smsTransactionManager")
@PropertySource("classpath:smsdb.properties")
public class SmsDatabaseConfig {
    @Value("${sms.jdbc.driver}")
    private String jdbcDriver;
    @Value("${sms.jdbc.url}")
    private String jdbcURL;
    @Value("${sms.jdbc.username}")
    private String jdbcUsername;
    @Value("${sms.jdbc.password}")
    private String jdbcPassword;
    @Value("${sms.hibernate.dialect}")
    private String sqlDialect;
    @Value("${sms.hibernate.connection.characterEncoding}")
    private String characterEncoding;
    @Value("${sms.hibernate.hbm2ddl.auto}")
    private String ddlAutoValue;
    @Value("${sms.hibernate.connection.CharSet}")
    private String charSet;
    @Value("${sms.hibernate.connection.useUnicode}")
    private String useUnicode;

    @Bean
    @Autowired
    public LocalContainerEntityManagerFactoryBean smsEntityManagerFactory(
            @Qualifier("smsDataSource") DataSource dataSource,
            @Qualifier("smsJpaVendorAdapter") JpaVendorAdapter jpaVendorAdapter
    ) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactory.setPackagesToScan("modulesms");
        entityManagerFactory.setJpaProperties(additionalProperties());
        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager smsTransactionManager(
            @Qualifier("smsEntityManagerFactory") EntityManagerFactory entityManagerFactory)
    {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public JpaVendorAdapter smsJpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
//        adapter.setGenerateDdl(true);
        return adapter;
    }

    @Bean
    public DataSource smsDataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(jdbcDriver);
        dataSource.setUrl(jdbcURL);
        dataSource.setUsername(jdbcUsername);
        dataSource.setPassword(jdbcPassword);
//        DataSourceBuilder
//                .create()
//                .url(jdbcURL)
//                .username(jdbcUsername)
//                .password(jdbcPassword)
//                .driverClassName(jdbcDriver)
//                .build()

        return dataSource;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", ddlAutoValue);
        properties.setProperty("hibernate.dialect", sqlDialect);
        properties.setProperty("hibernate.connection.CharSet", charSet);
        properties.setProperty("hibernate.connection.characterEncoding", characterEncoding);
        properties.setProperty("hibernate.connection.useUnicode", useUnicode);
//        System.out.println("____________________________________________________________________________________");
//        System.out.println(ddlAutoValue+"  "+sqlDialect+"  "+charSet+"  "+characterEncoding+"  "+useUnicode);
//
//        System.out.println("____________________________________________________________________________________");

        return properties;
    }

//    private void setAlters(DataSource dataSource) throws SQLException {
//        Connection connection = dataSource.getConnection();
////        String sql = "ALTER TABLE SMS SET NAMES UTF8";
////        String sql1 = "ALTER TABLE SMS SET timezone"
//    }
}




//securitydb?useUnicode=yes&characterEncoding=UTF-8











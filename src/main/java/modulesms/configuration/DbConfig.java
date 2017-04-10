package modulesms.configuration;

/**
 * Created by Dmytro Tymoshenko on 10.04.17.
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DbConfig {
    @Bean(name="chainedTransactionManager")
    public PlatformTransactionManager chainedTransactionManager(PlatformTransactionManager... managers) {
        return new ChainedTransactionManager(managers);
    }

    public static DataSource createDataSource(String driver, String url, String username, String password) {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        Properties properties = new Properties();
        properties.setProperty("useUnicode", "true");
        properties.setProperty("characterEncoding", "UTF-8");
        properties.setProperty("COLLATE", "utf8_unicode_ci");
        properties.setProperty("characterSet", "UTF-8");
        ds.setConnectionProperties(properties);

        try {
            Connection connection = ds.getConnection();
            Statement stat = connection.createStatement();

            String query = "set names utf8";
            stat.execute(query);
        }catch (SQLException s){
            s.printStackTrace();
        }
        return ds;
    }
}
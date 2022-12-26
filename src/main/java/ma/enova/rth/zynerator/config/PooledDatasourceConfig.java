//package ma.enova.rth.common.config;
//
//import java.sql.SQLException;
//
//import javax.sql.DataSource;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//
//@Configuration
//@Profile({ "dev", "test", "prod" })
//@ConfigurationProperties(prefix = "spring.datasource")
//public class PooledDatasourceConfig extends HikariConfig {
//
//	@Bean
//	public DataSource dataSource() throws SQLException {
//		this.setMaximumPoolSize(20);
//		this.setMinimumIdle(10);
//		return new HikariDataSource(this);
//	}
//
//}
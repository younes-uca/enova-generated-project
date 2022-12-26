package ma.enova.rth.zynerator.config;

import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.sql.DataSource;

/*@Configuration
@Profile({ "dev", "test", "prod" , "recette" , "preprod" })
@ConfigurationProperties(prefix = "spring.datasource")*/
public class JNDIDataSourceConfig {

    //@Value("${spring.datasource.jndi-name}")
    private String JNDI_NAME;

    //@Bean
    public DataSource dataSource() {
        JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
        return dataSourceLookup.getDataSource(JNDI_NAME);
    }
}

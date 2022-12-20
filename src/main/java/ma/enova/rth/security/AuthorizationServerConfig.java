package ma.enova.rth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private JwtAccessTokenConverter accessTokenConverter;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Value("${security.client-id}")
	private String client_id;

	@Value("${security.client-secret}")
	private String client_secret;

	@Value("${security.access-token-validity}")
	private String access_token_validity_seconds;

	@Value("${security.access-token-validity}")
	private String refresh_token_validity_seconds;

	final String GRANT_TYPE_PASSWORD = "password";
	final String AUTHORIZATION_CODE = "authorization_code";
	final String REFRESH_TOKEN = "refresh_token";
	final String IMPLICIT = "implicit";
	final String SCOPE_READ = "read";
	final String SCOPE_WRITE = "write";
	final String TRUST = "trust";

	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

		// configurer.jdbc(dataSource);
		configurer.inMemory().withClient(client_id).secret(passwordEncoder.encode(client_secret)).authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, IMPLICIT).scopes(SCOPE_READ, SCOPE_WRITE, TRUST).accessTokenValiditySeconds(Integer.valueOf(
				access_token_validity_seconds)).refreshTokenValiditySeconds(Integer.valueOf(refresh_token_validity_seconds));
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore).accessTokenConverter(accessTokenConverter).authenticationManager(authenticationManager).userDetailsService(userDetailsService);

	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

}

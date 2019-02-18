package egr401.prototype.app.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.config.BeanIds
import java.util.stream.Collectors
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import com.sun.deploy.util.SearchPath.findOne
import org.apache.tomcat.util.net.openssl.ciphers.Authentication
import javax.naming.AuthenticationException


/**
 * Configures the authorization server.
 * The @EnableAuthorizationServer annotation is used to configure the OAuth 2.0 Authorization Server mechanism,
 * together with any @Beans that implement AuthorizationServerConfigurer (there is a handy adapter implementation with empty methods).
 */
@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig : AuthorizationServerConfigurerAdapter() {

    @Autowired
    private val authenticationManager: AuthenticationManager? = null

    @Autowired
    private val passwordEncoder: PasswordEncoder? = null

    /**
     * Setting up the endpointsconfigurer authentication manager.
     * The AuthorizationServerEndpointsConfigurer defines the authorization and token endpoints and the token services.
     * @param endpoints
     * @throws Exception
     */

    @Throws(Exception::class)

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints
                .authenticationManager(authenticationManager)
    }

    /**
     * Setting up the clients with a clientId, a clientSecret, a scope, the grant types and the authorities.
     * @param clients
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients
                .inMemory()
                .withClient("my-trusted-client")
                .authorizedGrantTypes("client_credentials", "password")
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
                .scopes("read", "write", "trust")
                .resourceIds("oauth2-resource")
                .accessTokenValiditySeconds(5000)
                .secret(passwordEncoder!!.encode("secret"))
    }

    /**
     * We here defines the security constraints on the token endpoint.
     * We set it up to isAuthenticated, which returns true if the user is not anonymous
     * @param security the AuthorizationServerSecurityConfigurer.
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun configure(security: AuthorizationServerSecurityConfigurer) {
        security
                .checkTokenAccess("isAuthenticated()")
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }



}



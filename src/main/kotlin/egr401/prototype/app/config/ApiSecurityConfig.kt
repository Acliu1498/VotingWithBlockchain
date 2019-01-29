//package egr401.prototype.app.config
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.http.HttpMethod
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
//import org.springframework.security.core.userdetails.User
//import org.springframework.security.core.userdetails.UserDetails
//import org.springframework.security.core.userdetails.UserDetailsService
//import org.springframework.security.provisioning.InMemoryUserDetailsManager
//
//@Configuration
//@EnableWebSecurity
//class ApiSecurityConfig : WebSecurityConfigurerAdapter() {
//    @Throws(Exception::class)
//    override fun configure(http: HttpSecurity) {
//        http
//                .authorizeRequests()
//                .anyRequest()
//                .permitAll();
//    }
//
//    @Bean
//    public override fun userDetailsService(): UserDetailsService {
//        val user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build()
//
//        return InMemoryUserDetailsManager(user)
//    }
//
//}

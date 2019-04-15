package egr401.prototype.app.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter


@Configuration
class MvcConfig : WebMvcConfigurer {

    @Bean
    fun corsConfigurer(): WebMvcConfigurerAdapter =  object : WebMvcConfigurerAdapter(){

            override fun addCorsMappings(registry: CorsRegistry ) {
                registry.addMapping("/**")
            }

    }

}
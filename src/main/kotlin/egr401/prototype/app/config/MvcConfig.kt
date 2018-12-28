package egr401.prototype.app.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class MvcConfig : WebMvcConfigurer {

    override fun addViewControllers(registry: ViewControllerRegistry) {

        registry.addViewController("/home").setViewName("home")
        registry.addViewController("/").setViewName("home")
        registry.addViewController("/student").setViewName("student")
        registry.addViewController("/admin").setViewName("admin")
        registry.addViewController("/vote").setViewName("vote")
    }

}
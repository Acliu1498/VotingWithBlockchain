package egr401.prototype.impl.presistence.daos

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@ComponentScan(basePackages = arrayOf("egr401.prototype.impl"))
@EnableTransactionManagement
class DataSourceConfig {

}
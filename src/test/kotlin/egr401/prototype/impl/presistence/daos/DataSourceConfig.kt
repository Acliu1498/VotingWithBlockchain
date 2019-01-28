package egr401.prototype.impl.presistence.daos

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableJpaRepositories(basePackages = arrayOf("egr401.prototype.impl.persistence.daos"))
@PropertySource("applicationTest.properties")
@EnableTransactionManagement
class DataSourceConfig {

}
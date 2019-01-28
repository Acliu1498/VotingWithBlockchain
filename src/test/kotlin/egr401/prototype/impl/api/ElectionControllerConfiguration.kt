package egr401.prototype.impl.api

import egr401.prototype.impl.persistence.daos.ElectionDAO
import org.mockito.Mockito
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile

@Profile("test")
@Configuration
class ElectionDaoConfiguration{
    @Bean
    @Primary
    fun electionDaoTest(): ElectionDAO{
        return Mockito.mock(ElectionDAO::class.java)
    }
}
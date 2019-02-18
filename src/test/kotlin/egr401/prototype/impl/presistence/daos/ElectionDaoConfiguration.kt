package egr401.prototype.impl.presistence.daos

import egr401.prototype.impl.persistence.daos.ElectionDao
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@Profile("test")
@Configuration
class ElectionDaoConfiguration{
    @Bean
    @Primary
    fun electionDaoTest(): ElectionDao {
        return Mockito.mock(ElectionDao::class.java)
    }
}
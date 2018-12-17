package egr401.prototype

import egr401.prototype.app.PrototypeApplication
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(PrototypeApplication::class))
class PrototypeApplicationTests {

    @Test
    fun contextLoads() {
    }

}

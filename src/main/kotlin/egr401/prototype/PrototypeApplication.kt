package egr401.prototype

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = arrayOf(SecurityAutoConfiguration::class))
class PrototypeApplication

fun main(args: Array<String>) {
    runApplication<PrototypeApplication>(*args)
}

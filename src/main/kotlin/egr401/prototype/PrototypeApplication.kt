package egr401.prototype

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.AuthenticationManager
import javax.annotation.Resource
import javax.naming.AuthenticationException


@SpringBootApplication(exclude = arrayOf(SecurityAutoConfiguration::class))
class PrototypeApplication

fun main(args: Array<String>) {
    runApplication<PrototypeApplication>(*args)
}

@Resource(name = "authenticationManager")
private val authenticationManager: AuthenticationManager? = null // specific for Spring Security

fun login(username: String, password: String): Boolean {
    try {
        val authenticate = authenticationManager!!.authenticate(UsernamePasswordAuthenticationToken(username, password))
        if (authenticate.isAuthenticated) {
            SecurityContextHolder.getContext().authentication = authenticate
            return true
        }
    } catch (e: AuthenticationException) {
    }

    return false
}
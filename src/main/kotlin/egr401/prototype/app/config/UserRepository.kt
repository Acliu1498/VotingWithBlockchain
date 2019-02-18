package egr401.prototype.app.config


import egr401.prototype.data.model.model.enums.User
import java.util.Optional
import org.springframework.data.jpa.repository.JpaRepository

/**
 * User repository for CRUD operations.
 */
interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): Optional<User>
}
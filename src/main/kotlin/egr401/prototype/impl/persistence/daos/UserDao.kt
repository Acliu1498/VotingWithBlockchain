package egr401.prototype.impl.persistence.daos

import egr401.prototype.data.model.User
import egr401.prototype.inter.persistence.daos.Dao
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class UserDao : Dao<User> {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun insert(obj: User) {
        entityManager.persist(obj)
    }

    override fun getById(id: Int): User {
        return entityManager.find(User::class.java, id)
    }

    override fun update(obj: User): User {
        return entityManager.merge(obj)
    }

    override fun delete(obj: User) {
        entityManager.remove(obj)
    }

}

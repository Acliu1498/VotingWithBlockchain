package egr401.prototype.impl.persistence.daos

import egr401.prototype.data.model.Greeting
import egr401.prototype.inter.persistence.daos.Dao
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

class GreetingDao: Dao<Greeting> {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun insert(obj: Greeting){
        entityManager.persist(obj)
    }

    override fun getById(id: Int): Greeting {
        return entityManager.find(Greeting::class.java, id)
    }

    override fun update(obj: Greeting): Greeting {
        return entityManager.merge(obj)
    }

    override fun delete(obj: Greeting){
        entityManager.remove(obj)
    }


}
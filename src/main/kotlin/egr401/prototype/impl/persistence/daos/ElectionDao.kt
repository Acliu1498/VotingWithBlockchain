package egr401.prototype.impl.persistence.daos

import egr401.prototype.data.model.Election
import egr401.prototype.inter.persistence.daos.Dao
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
@Transactional
class ElectionDao: Dao<Election> {
    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun insert(obj: Election){
        entityManager.persist(obj)
    }

    override fun getById(id: Int): Election {
        return entityManager.find(Election::class.java, id)
    }

    override fun update(obj: Election): Election {
        return entityManager.merge(obj)
    }

    override fun delete(obj: Election){
        entityManager.remove(obj)
    }

    fun getCurrentElections(): List<Election>{
        return entityManager
            .createQuery("SELECT e FROM Election e WHERE e.endDate > \'${LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE)}\'")
            .resultList as List<Election>

    }

    fun getPastElections(): List<Election>{
        return entityManager
            .createQuery("SELECT e FROM Election e WHERE e.endDate <= \'${LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE)}\'")
            .resultList as List<Election>
    }
}
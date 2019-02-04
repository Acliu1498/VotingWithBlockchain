package egr401.prototype.impl.persistence.daos

import egr401.prototype.data.model.Candidate
import egr401.prototype.inter.persistence.daos.Dao
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Repository
@Transactional
class CandidateDao : Dao<Candidate> {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun insert(obj: Candidate) {
        entityManager.persist(obj)
    }

    override fun getById(id: Int): Candidate {
        return entityManager.find(Candidate::class.java, id)
    }

    override fun update(obj: Candidate): Candidate {
        return entityManager.merge(obj)
    }

    override fun delete(obj: Candidate) {
        entityManager.remove(obj)
    }


}
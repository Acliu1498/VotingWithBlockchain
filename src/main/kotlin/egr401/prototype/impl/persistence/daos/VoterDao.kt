package egr401.prototype.impl.persistence.daos

import egr401.prototype.data.model.Election
import egr401.prototype.data.model.Voter
import egr401.prototype.data.model.model.enums.Housing
import egr401.prototype.data.model.model.enums.Residency
import egr401.prototype.data.model.model.enums.Year
import egr401.prototype.inter.persistence.daos.Dao
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional


@Repository
@Transactional
class VoterDao : Dao<Voter> {


    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun insert(obj: Voter) {
        entityManager.persist(obj)
    }

    override fun getById(id: Int): Voter {
        return entityManager.find(Voter::class.java, id)
    }

    override fun update(obj: Voter): Voter {
        return entityManager.merge(obj)
    }

    override fun delete(obj: Voter) {
        entityManager.remove(obj)
    }

    fun getElectionsByStudentId(id: Int): List<Election> {
        return entityManager
                .createQuery("select e from Election e where e.id == $id")
                .resultList as List<Election>

    }

}
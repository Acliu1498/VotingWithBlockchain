package egr401.prototype.impl.persistence.daos

import egr401.prototype.data.model.Voter
import egr401.prototype.data.model.model.enums.Housing
import egr401.prototype.data.model.model.enums.Residency
import egr401.prototype.data.model.model.enums.Year
import egr401.prototype.inter.persistence.daos.Dao
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


@Repository
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

    fun getVoters(): List<Voter> {
        return entityManager
                .createQuery("select e from Voter e where e.id >" + "0")
                .resultList as List<Voter>

    }

    fun getVotersByHousing(housing: Housing): List<Voter> {
        return entityManager
                .createQuery("SELECT e FROM Voter e WHERE e.housing.text ==" + housing)
                .resultList as List<Voter>
    }

    fun getVotersByYear(year: Year): List<Voter> {
        return entityManager
                .createQuery("select e from Voter where e.year.text ==" + year)
                .resultList as List<Voter>
    }

    fun getVotersByResidency(residency: Residency): List<Voter> {
        return entityManager
                .createQuery("select e from Voter where e.residency.text ==" + residency)
                .resultList as List<Voter>
    }


}
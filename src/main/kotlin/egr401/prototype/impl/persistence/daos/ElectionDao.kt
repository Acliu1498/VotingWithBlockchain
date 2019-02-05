package egr401.prototype.impl.persistence.daos

import egr401.prototype.data.model.Candidate
import egr401.prototype.data.model.Election
import egr401.prototype.data.model.Vote
import egr401.prototype.data.model.Voter
import egr401.prototype.inter.persistence.daos.Dao
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
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
        val election = entityManager.find(Election::class.java, id)
        if(election == null){
            throw IllegalArgumentException("Election with id: $id does not exist")
        } else {
            return election
        }
    }

    override fun update(obj: Election): Election {
        return entityManager.merge(obj)
    }

    override fun delete(obj: Election){
        entityManager.remove(obj)
    }



    fun getAllVotes(): List<Vote> {
        val votes = entityManager.createQuery("SELECT v FROM Vote v").resultList as List<Vote>
        for (vote in votes){
            entityManager.remove(vote)
        }

        return votes
    }

    fun getCurrentElections(): List<Election>{
        return entityManager
            .createQuery("SELECT e FROM Election e WHERE e.endDate > " + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE))
            .resultList as List<Election>

    }

    fun getPastElections(): List<Election>{
        return entityManager
            .createQuery("SELECT e FROM Election e WHERE e.endDate <= " + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE))
            .resultList as List<Election>
    }

    fun getCandidatesForElection(id: Int): List<Candidate> {
        return entityManager
                .createQuery("select e from Candidate e join e.election_id elec where elec = :id ")
                .setParameter("id", id)
                .resultList as List<Candidate>


    }


    fun getVotersForElection(id: Int): List<Voter> {
        return entityManager
                .createQuery("select e from Voter e where e.election_id = :id")
                .setParameter("id", id)
                .resultList as List<Voter>

    }


}
package egr401.prototype.impl.persistence.daos

import egr401.prototype.data.model.*
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
        // queries database for elections that have not yet past their end dates
        return entityManager
            .createQuery("SELECT e FROM Election e WHERE e.endDate > " + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE))
            .resultList as List<Election>

    }

    fun getPastElections(): List<Election>{
        // queries database for elections that have past their end date
        return entityManager
            .createQuery("SELECT e FROM Election e WHERE e.endDate <= " + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE))
            .resultList as List<Election>
    }

    fun getCandidatesForElection(id: Int): List<Candidate> {
        // queries database for candidates who are associate to specified candidate
        val candidates = mutableListOf<Candidate>()

            (entityManager
            .createQuery("SELECT ce FROM CandidateElection ce WHERE ce.election.id = :eId")
            .setParameter("eId", id)
            .resultList as List<CandidateElection>).forEach {
                candidates.add(it.candidate)
            }

        return candidates

    }

    fun getVotersForElection(id: Int): List<Voter> {
        // queries database for voters who are associated with the given election
        return entityManager
                .createQuery("select v from Voter v where v.election.id = :id")
                .setParameter("id", id)
                .resultList as List<Voter>

    }

    fun getResults(id:Int): List<Result>{
        val ces: List<CandidateElection>  = entityManager
            .createQuery("SELECT ce from CandidateElection ce where ce.election.id = :id")
            .setParameter("id", id)
            .resultList as List<CandidateElection>
        val results: MutableList<Result> = mutableListOf()
        for (ce in ces){
            val result: Result = Result(ce.candidate.id, ce.votes)
            results.add(result)
        }

        return results
    }


}
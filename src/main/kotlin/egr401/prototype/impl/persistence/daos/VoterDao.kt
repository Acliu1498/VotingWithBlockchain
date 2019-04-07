package egr401.prototype.impl.persistence.daos

import egr401.prototype.data.model.Election
import egr401.prototype.data.model.Vote
import egr401.prototype.data.model.Voter
import egr401.prototype.data.model.model.enums.Housing
import egr401.prototype.data.model.model.enums.Residency
import egr401.prototype.data.model.model.enums.Year
import egr401.prototype.inter.persistence.daos.Dao
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException
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

    @Deprecated("Cannot ensure what election voter is apart of, use get voter")
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
        return (entityManager
                .createQuery("select v from Voter v where v.voterId = :id")
                .setParameter("id", id)
                .resultList as List<Voter>).map {
                    it.election
                }

    }

    // adds a temporary vote to the database
    fun addVote(vote: Vote) {
        val voter = getVoter(vote.voterId, vote.electionId)
        if (voter.hasVoted){
            throw IllegalArgumentException("Voter has already voted")
        }
        entityManager.persist(vote)
        voter.hasVoted = true
        entityManager.merge(voter)

    }

    fun getVoter(voterId: Int, electionId: Int): Voter{
        try {
            val voter: Voter = entityManager
                .createQuery("SELECT v FROM Voter v WHERE v.voterId = :vId and v.election.id = :eId")
                .setParameter("vId", voterId)
                .setParameter("eId", electionId)
                .resultList.first() as Voter

            return voter
        } catch (e: Exception) {
            throw IllegalArgumentException("bad voter")
        }
    }

}
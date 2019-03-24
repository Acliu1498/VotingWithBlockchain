package egr401.prototype.impl.persistence.daos

import egr401.prototype.data.model.Election
import egr401.prototype.data.model.Vote
import egr401.prototype.data.model.Voter
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
        val voter: Voter = getById(id)
        return entityManager
            .createQuery("SELECT e FROM Election e WHERE :housing MEMBER e.housings and :year MEMBER e.year")
            .setParameter("housing", voter.housing)
            .setParameter("year", voter.year)
            .resultList as List<Election>
    }

    fun addVote(vote: Vote) {

        val electionVote: Vote? = entityManager
            .createQuery("SELECT v FROM Vote v WHERE :voterId = v.voterId and :electionId = v.electionId")
            .setParameter("voterId", vote.voterId)
            .setParameter("electionId", vote.electionId)
            .resultList.first() as Vote?

        if (electionVote == null){
            throw IllegalArgumentException("Voter has already voted")
        }
        entityManager.persist(vote)

    }

    fun getVoter(voterId: Int): Voter{
        try {
            val voter: Voter = entityManager
                .createQuery("SELECT v FROM Voter v WHERE v.voterId = :vId")
                .setParameter("vId", voterId)
                .resultList.first() as Voter

            return voter
        } catch (e: Exception) {
            throw IllegalArgumentException("bad voter")
        }
    }
}
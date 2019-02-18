package egr401.prototype.impl.persistence.daos

import egr401.prototype.data.model.Candidate
import egr401.prototype.data.model.CandidateElection
import egr401.prototype.data.model.Election
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
        val candidate =  entityManager.find(Candidate::class.java, id)
        // if the candidate does not exist throw an error
        if(candidate == null){
            throw IllegalArgumentException("Candidate with id: $id does not exist")
        } else {
            return candidate
        }
    }

    override fun update(obj: Candidate): Candidate {
        return entityManager.merge(obj)
    }

    override fun delete(obj: Candidate) {
        entityManager.remove(obj)
    }

    fun addCandidateToElection(candidate: Candidate, election: Election){
        val ce = CandidateElection(candidate, election)
        entityManager.persist(ce)
    }

    fun isCandidateElection(candidateId: Int, electionId: Int): Boolean{
        // returns if the candidate is a part of the election
        return !entityManager
            .createQuery("SELECT ce FROM CandidateElection ce WHERE ce.candidate.id = :cId AND ce.election.id = :eId")
            .setParameter("cId", candidateId)
            .setParameter("eId", electionId)
            .resultList.isEmpty()
    }

    fun addVotesForCandidate(candidate: Candidate, election: Election, votes: Int){
        val newCandidateElection = CandidateElection(candidate, election, votes)
        entityManager.merge(newCandidateElection)
    }


}
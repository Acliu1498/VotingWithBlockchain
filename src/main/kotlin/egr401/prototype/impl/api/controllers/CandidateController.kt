package egr401.prototype.impl.api.controllers

import egr401.prototype.data.model.Candidate
import egr401.prototype.data.model.Election
import egr401.prototype.impl.persistence.daos.CandidateDao
import egr401.prototype.inter.persistence.daos.Dao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class CandidateController @Autowired constructor(
    private val electionDAO: Dao<Election>,
    private val candidateDao: Dao<Candidate>
){
    @RequestMapping(value = "/Candidate/addCandidate/{electionId}", method = arrayOf(RequestMethod.POST))
    fun addCandidate(@PathVariable electionId: Int, @RequestBody candidate: Candidate): Candidate {
        val election = electionDAO.getById(electionId)
        try {
            candidateDao.getById(candidate.id)
        } catch (e: Exception){
            candidateDao.insert(candidate)
        }
        (candidateDao as CandidateDao).addCandidateToElection(candidate, election)

        return candidate
    }

    @RequestMapping(value = "/Candidate/getCandidate/{id}", method = arrayOf(RequestMethod.GET))
    fun getCandidate(@PathVariable id: Int): Candidate {
        return candidateDao.getById(id)
    }
}
package egr401.prototype.impl.api.controllers

import egr401.prototype.data.model.*
import egr401.prototype.impl.persistence.daos.CandidateDao
import egr401.prototype.impl.persistence.daos.ElectionDao
import egr401.prototype.inter.persistence.daos.Dao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.lang.IllegalArgumentException
import java.time.LocalDate

@RestController
class ElectionController @Autowired constructor(
        private val electionDAO: Dao<Election>,
        private val candidateDao: Dao<Candidate>
) {

    @RequestMapping(value = "/electionController/addElection", method = arrayOf(RequestMethod.POST))
    fun addElection(@RequestBody election: Election): Election {
        electionDAO.insert(election)
        return election

    }

    @RequestMapping(value = "/electionController/getById/{id}", method = arrayOf(RequestMethod.GET))
    fun getElection(@PathVariable id: Int): Election {
        return electionDAO.getById(id)
    }

    @RequestMapping(value = "/electionController/getCurrentElections", method = arrayOf(RequestMethod.GET))
    fun getCurrentElections(): List<Election> {
         return (electionDAO as ElectionDao).getCurrentElections()
    }

    @RequestMapping(value = "/electionController/getPastElections", method = arrayOf(RequestMethod.GET))
    fun getPastElections(): List<Election> {
        return (electionDAO as ElectionDao).getPastElections()
    }

    @RequestMapping(value = "/electionController/getCandidatesForElection/{id}", method = arrayOf(RequestMethod.GET))
    fun getCandidatesForElection(@PathVariable id: Int): List<Candidate> {
        return (electionDAO as ElectionDao).getCandidatesForElection(id)
    }


    @RequestMapping(value = "/electionController/getVotersForElection/{id}", method = arrayOf(RequestMethod.GET))
    fun getVotersForElection(@PathVariable id: Int): List<Voter>{
        return (electionDAO as ElectionDao).getVotersForElection(id)
    }

    @RequestMapping(value = "/electionController/addCandidate/{electionId}", method = arrayOf(RequestMethod.POST))
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

    @RequestMapping(value = "/electionController/getCandidate/{id}", method = arrayOf(RequestMethod.GET))
    fun getCandidate(@PathVariable id: Int): Candidate {
        return candidateDao.getById(id)
    }

    @RequestMapping(value = "/electionController/postResults/{id}", method = arrayOf(RequestMethod.POST))
    fun postResults(@PathVariable id: Int, @RequestBody candidateResults: List<Result>){
        val election = electionDAO.getById(id)
        // if it is the day the election ends, add dates
        if(election.endDate.isBefore(LocalDate.now()) || election.endDate.equals(LocalDate.now())) {
            if (!election.finished) {
                for (candidatePair in candidateResults) {
                    val candidate = candidateDao.getById(candidatePair.candidateId)
                    (candidateDao as CandidateDao).addVotesForCandidate(candidate, election, candidatePair.votes)
                }
                electionDAO.update(Election(election.id, election.name, election.startDate, election.endDate, true))
            } else {
                throw IllegalArgumentException("Election has already been completed.")
            }
        } else {
            throw IllegalArgumentException("Election has not finished yet.")
        }
    }

    @RequestMapping(value = "/electionController/getResults/{id}", method = arrayOf(RequestMethod.GET))
    fun getResults(@PathVariable id: Int): List<Result>{
        return(electionDAO as ElectionDao).getResults(id)
    }

    @RequestMapping(value = "electionController/getVotes", method = arrayOf(RequestMethod.GET))
    fun getVotes(): List<Vote>{
        val votes: List<Vote> = (electionDAO as ElectionDao).getAllVotes()
        val retVotes: MutableList<Vote> = mutableListOf()
        for (vote in votes){
            if(!vote.stored){
                retVotes.add(vote)
            }
        }
        return retVotes
    }

    @RequestMapping(value = "electionController/checkVotes", method = arrayOf(RequestMethod.POST))
    fun checkRealtimeVotes(@PathVariable votes: List<Vote>) {
        for(vote in votes){
            
        }
    }


}

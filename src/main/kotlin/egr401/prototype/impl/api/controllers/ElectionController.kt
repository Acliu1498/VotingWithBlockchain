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

    @RequestMapping(value = "/Election/addElection", method = arrayOf(RequestMethod.POST))
    fun addElection(@RequestBody election: Election): Election {
        electionDAO.insert(election)
        return election
    }

    @RequestMapping(value = "/Election/getElection/{id}", method = arrayOf(RequestMethod.GET))
    fun getElection(@PathVariable id: Int): Election {
        return electionDAO.getById(id)
    }

    @RequestMapping(value = "/Election/getCurrentElections", method = arrayOf(RequestMethod.GET))
    fun getCurrentElections(): List<Election> {
         return (electionDAO as ElectionDao).getCurrentElections()
    }

    @RequestMapping(value = "/Election/getPastElections", method = arrayOf(RequestMethod.GET))
    fun getPastElections(): List<Election> {
        return (electionDAO as ElectionDao).getPastElections()
    }

    @RequestMapping(value = "/Election/getCandidatesForElection/{id}", method = arrayOf(RequestMethod.GET))
    fun getCandidatesForElection(@PathVariable id: Int): List<Candidate> {
        return (electionDAO as ElectionDao).getCandidatesForElection(id)
    }

    @RequestMapping(value = "/Election/getVotersForElection/{id}", method = arrayOf(RequestMethod.GET))
    fun getVotersForElection(@PathVariable id: Int): List<Voter>{
        return (electionDAO as ElectionDao).getVotersForElection(id)
    }

    @RequestMapping(value = "/Election/postResults/{id}", method = arrayOf(RequestMethod.POST))
    fun postResults(@PathVariable id: Int, @RequestBody candidateResults: List<Result>){
        val election = electionDAO.getById(id)
        // if it is the day the election ends, add dates
        if(election.endDate.isBefore(LocalDate.now()) || election.endDate.equals(LocalDate.now())) {
            if (!election.finished) {
                for (candidatePair in candidateResults) {
                    val candidate = candidateDao.getById(candidatePair.candidateId)
                    (candidateDao as CandidateDao).addVotesForCandidate(candidate, election, candidatePair.votes)
                }
                electionDAO.update(Election(election.id, election.name, election.startDate, election.endDate, true, election.housings, election.year))
            } else {
                throw IllegalArgumentException("Election has already been completed.")
            }
        } else {
            throw IllegalArgumentException("Election has not finished yet.")
        }
    }

    @RequestMapping(value = "/Election/getResults/{id}", method = arrayOf(RequestMethod.GET))
    fun getResults(@PathVariable id: Int): List<Result>{
        return(electionDAO as ElectionDao).getResults(id)
    }


    @RequestMapping(value = "Election/getVotes", method = arrayOf(RequestMethod.GET))
    fun getVotes(): List<Vote>{
        return (electionDAO as ElectionDao).getAllVotes()
    }

}

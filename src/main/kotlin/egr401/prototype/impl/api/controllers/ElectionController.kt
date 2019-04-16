package egr401.prototype.impl.api.controllers

import egr401.prototype.data.model.*
import egr401.prototype.impl.persistence.daos.CandidateDao
import egr401.prototype.impl.persistence.daos.ElectionDao
import egr401.prototype.inter.persistence.daos.Dao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.lang.IllegalArgumentException
import java.time.LocalDateTime

@RestController
class ElectionController @Autowired constructor(
        private val electionDAO: Dao<Election>,
        private val candidateDao: Dao<Candidate>
) {

    /**
     * adds a new election
     */
    @RequestMapping(value = "/electionController/addElection", method = arrayOf(RequestMethod.POST))
    fun addElection(@RequestBody election: Election): Election {
        electionDAO.insert(election)
        print(election)
        return election

    }

    /**
     * gets and already existing election
     */
    @RequestMapping(value = "/electionController/getById/{id}", method = arrayOf(RequestMethod.GET))
    fun getElection(@PathVariable id: Int): Election {
        return electionDAO.getById(id)
    }

    /**
     * gets the current running elections
     */
    @RequestMapping(value = "/electionController/getCurrentElections", method = arrayOf(RequestMethod.GET))
    fun getCurrentElections(): List<Election> {
         return (electionDAO as ElectionDao).getCurrentElections()
    }

    /**
     * gets any election that has already finished
     */
    @RequestMapping(value = "/electionController/getPastElections", method = arrayOf(RequestMethod.GET))
    fun getPastElections(): List<Election> {
        return (electionDAO as ElectionDao).getPastElections()
    }

    /**
     * gets the candidates for an election given its id
     */
    @RequestMapping(value = "/electionController/getCandidatesForElection/{id}", method = arrayOf(RequestMethod.GET))
    fun getCandidatesForElection(@PathVariable id: Int): List<Candidate> {
        return (electionDAO as ElectionDao).getCandidatesForElection(id)
    }

    /**
     * gets the voters for an election given the id
     */
    @RequestMapping(value = "/electionController/getVotersForElection/{id}", method = arrayOf(RequestMethod.GET))
    fun getVotersForElection(@PathVariable id: Int): List<Voter>{
        return (electionDAO as ElectionDao).getVotersForElection(id)
    }

    /**
     * adds a candidate to an existing election
     */
    @RequestMapping(value = "/electionController/addCandidate/{electionId}", method = arrayOf(RequestMethod.POST))
    fun addCandidate(@PathVariable electionId: Int, @RequestBody candidate: Candidate): Candidate {
        val election = electionDAO.getById(electionId)
        // if the candidate does not already exist add a new one
        try {
            candidateDao.getById(candidate.id)
        } catch (e: Exception){
            candidateDao.insert(candidate)
        }
        (candidateDao as CandidateDao).addCandidateToElection(candidate, election)

        return candidate
    }

    /**
     * gets and already existing candidate given their id
     */
    @RequestMapping(value = "/electionController/getCandidate/{id}", method = arrayOf(RequestMethod.GET))
    fun getCandidate(@PathVariable id: Int): Candidate {
        return candidateDao.getById(id)
    }

    /**
     *  Method to post the final results of an election
     *  The votes given to this function override the current votes
     */
    @RequestMapping(value = "/electionController/postResults/{id}", method = arrayOf(RequestMethod.POST))
    fun postResults(@PathVariable id: Int, @RequestBody candidateResults: List<Result>){
        val election = electionDAO.getById(id)
        // if the election is past or at its end time.
        if(election.endDateTime.isBefore(LocalDateTime.now()) || election.endDateTime.equals(LocalDateTime.now())) {
            // checks that the election has not already been finished.
            if (!election.finished) {
                // update each candidate with their number of votes.
                for (candidatePair in candidateResults) {
                    val candidate = candidateDao.getById(candidatePair.candidateId)
                    (candidateDao as CandidateDao).setVotesForCandidate(candidate, election, candidatePair.votes)
                }
                electionDAO.update(Election(election.id, election.name, election.startDateTime, election.endDateTime, true))
            } else {
                throw IllegalArgumentException("Election has already been completed.")
            }
        } else {
            throw IllegalArgumentException("Election has not finished yet.")
        }
    }

    /**
     * gets the results of an election
     */
    @RequestMapping(value = "/electionController/getResults/{id}", method = arrayOf(RequestMethod.GET))
    fun getResults(@PathVariable id: Int): List<Result>{
        return(electionDAO as ElectionDao).getResults(id)
    }

    /**
     * gets all existing votes
     */
    @RequestMapping(value = "electionController/getVotes", method = arrayOf(RequestMethod.GET))
    fun getVotes(): List<Vote>{
        val votes: List<Vote> = (electionDAO as ElectionDao).getAllVotes()
        val retVotes: MutableList<Vote> = mutableListOf()
        for (vote in votes){
            retVotes.add(vote)
        }
        return retVotes
    }

    /**
     * method to delete temporary votes
     */
    @RequestMapping(value = "electionController/deleteVotes", method = arrayOf(RequestMethod.POST))
    fun deleteVotes(@RequestBody votes: List<Vote>){
        for (vote in votes){
            (electionDAO as ElectionDao).deleteVote(vote)
        }
    }

    /**
     * method used for checking the realtime votes
     */
    @RequestMapping(value = "electionController/checkVotes/{electionId}", method = arrayOf(RequestMethod.GET))
    fun checkRealtimeVotes(@PathVariable electionId: Int): List<CandidateElection> {
        val candidates: List<Candidate> = (electionDAO as ElectionDao).getCandidatesForElection(electionId)
        val results: MutableList<CandidateElection> = mutableListOf()
        for(candidate in candidates){
            results.add((candidateDao as CandidateDao).getCandidateElection(candidate.id, electionId))
        }
        return results
    }
            

}

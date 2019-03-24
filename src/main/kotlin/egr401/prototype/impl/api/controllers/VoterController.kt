package egr401.prototype.impl.api.controllers

import egr401.prototype.data.model.*
import egr401.prototype.impl.persistence.daos.CandidateDao
import egr401.prototype.impl.persistence.daos.ElectionDao
import egr401.prototype.impl.persistence.daos.VoterDao
import egr401.prototype.inter.persistence.daos.Dao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
class VoterController @Autowired constructor(
    private val voterDao: Dao<Voter>,
    private val candidateDao: Dao<Candidate>,
    private val electionDao: Dao<Election>
) {

    @RequestMapping(value = "/Voter/addVoter", method = arrayOf(RequestMethod.POST))
    fun addVoter(@RequestBody voter: Voter): Voter{
        voterDao.insert(voter)
        return voter
    }

    @RequestMapping(value = "/Voter/getVoter/{voterId}", method = arrayOf(RequestMethod.GET))
    fun getVoter(@PathVariable voterId: Int): Voter {
        return (voterDao as VoterDao).getVoter(voterId)
    }

    @RequestMapping(value = "/Voter/getElectionsForVoter/{id}", method = arrayOf(RequestMethod.GET))
    fun getElectionsForVoter(@PathVariable id: Int): List<Election>{
        return (voterDao as VoterDao).getElectionsByStudentId(id)
    }

    @RequestMapping(value = "/Voter/voterController/vote", method = arrayOf(RequestMethod.POST))
    fun vote(@RequestBody vote: Vote): Vote{
        // checks if the candidate and election is a valid combination
        if((candidateDao as CandidateDao).isCandidateElection(vote.candidateId, vote.electionId)) {
            val election: Election = electionDao.getById(vote.electionId)
            val voter: Voter = voterDao.getById(vote.voterId)
            // checks that the voter is allowed to vote in the election
            if(election.housings.contains(voter.housing) && election.year.contains(voter.year)) {
                (voterDao as VoterDao).addVote(vote)
                return vote
            }
        }
        throw IllegalArgumentException("Invalid vote")

    }
}
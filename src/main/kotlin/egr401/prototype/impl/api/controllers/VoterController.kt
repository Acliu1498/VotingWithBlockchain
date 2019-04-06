package egr401.prototype.impl.api.controllers

import egr401.prototype.data.model.Candidate
import egr401.prototype.data.model.Election
import egr401.prototype.data.model.Vote
import egr401.prototype.data.model.Voter
import egr401.prototype.impl.persistence.daos.CandidateDao
import egr401.prototype.impl.persistence.daos.VoterDao
import egr401.prototype.inter.persistence.daos.Dao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.persistence.Id


@RestController
class VoterController @Autowired constructor(
        private val voterDao: Dao<Voter>,
        private val candidateDao: Dao<Candidate>
) {

    @RequestMapping(value = "/voterController/addVoter", method = arrayOf(RequestMethod.POST))
    fun addVoter(@RequestBody voter: Voter): Voter {
        voterDao.insert(voter)
        return voter
    }

    @RequestMapping(value = "/voterController/{voterId}/{electionId}", method = arrayOf(RequestMethod.GET))
    fun getVoter(@PathVariable voterId: Int, @PathVariable electionId: Int): Voter {
        return (voterDao as VoterDao).getVoter(voterId, electionId)
    }

    @RequestMapping(value = "/voterController/getElectionsForVoter/{id}", method = arrayOf(RequestMethod.GET))
    fun getElectionsForVoter(@PathVariable id: Int): List<Election>{
        return (voterDao as VoterDao).getElectionsByStudentId(id)
    }

    @RequestMapping(value = "/voterController/vote", method = arrayOf(RequestMethod.POST))
    fun vote(@RequestBody vote: Vote): Vote{
        val voter = (voterDao as VoterDao).getVoter(vote.voterId, vote.electionId)
        if((candidateDao as CandidateDao).isCandidateElection(vote.candidateId, vote.electionId) || !voter.hasVoted) {
            (voterDao as VoterDao).addVote(vote)
            voter.hasVoted = true
            voterDao.update(voter)
        } else {
            throw IllegalArgumentException("Invalid vote")
        }
        return vote
    }
}
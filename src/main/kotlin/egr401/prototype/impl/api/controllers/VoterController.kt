package egr401.prototype.impl.api.controllers

import egr401.prototype.data.model.Candidate
import egr401.prototype.data.model.Election
import egr401.prototype.data.model.Voter
import egr401.prototype.impl.persistence.daos.VoterDao
import egr401.prototype.inter.persistence.daos.Dao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


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

    @RequestMapping(value = "/voterController/{id}", method = arrayOf(RequestMethod.GET))
    fun getVoter(@PathVariable id: Int): Voter {
        return voterDao.getById(id)
    }

    @RequestMapping(value = "/voterController/getElectionsForVoter/{id}", method = arrayOf(RequestMethod.GET))
    fun getElectionsForVoter(@PathVariable id: Int): List<Election>{
        val voter = voterDao.getById(id)
        return (voterDao as VoterDao).getElectionsByStudentId(voter.studentId)
    }



}
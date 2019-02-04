package egr401.prototype.impl.api.controllers

import egr401.prototype.data.model.Candidate
import egr401.prototype.data.model.Election
import egr401.prototype.data.model.Voter
import egr401.prototype.impl.persistence.daos.ElectionDao
import egr401.prototype.inter.persistence.daos.Dao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

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

    @RequestMapping(value = "/electionController/addCandidate", method = arrayOf(RequestMethod.POST))
    fun addCandidate(@RequestBody candidate: Candidate): Candidate {
        // attempts to find candidate
//        var daoCandidate : Candidate? = candidateDao.getById(candidate.studentId)
//        // if candidate exists then update else insert new
//        if(daoCandidate == null){
            candidateDao.insert(candidate)
//            daoCandidate = candidate
//        } else {
//            daoCandidate.elections.addAll(candidate.elections)
//            candidateDao.update(daoCandidate)
//        }

        return candidate
    }

    @RequestMapping(value = "/electionController/getCandidate/{id}", method = arrayOf(RequestMethod.GET))
    fun getCandidate(@PathVariable id: Int): Candidate {
        return candidateDao.getById(id)
    }
}

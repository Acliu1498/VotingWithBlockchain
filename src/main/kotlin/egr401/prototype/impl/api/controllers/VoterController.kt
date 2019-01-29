package egr401.prototype.impl.api.controllers

import egr401.prototype.data.model.Voter
import egr401.prototype.data.model.model.enums.Housing
import egr401.prototype.data.model.model.enums.Residency
import egr401.prototype.data.model.model.enums.Year
import egr401.prototype.impl.persistence.daos.VoterDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
class VoterController @Autowired constructor(private val voterDao: VoterDao) {

    @RequestMapping(value = "/voterController/addVoter", method = arrayOf(RequestMethod.POST))
    fun addVoter(@RequestBody voter: Voter): Voter {
        try {
            voterDao.insert(voter)
        } catch (e: Exception) {

        }
        when (voterDao) {
            is VoterDao -> return voterDao.getVoters().last()
            else -> throw  IllegalArgumentException("Incorrect dao")
        }
    }

    @RequestMapping(value = "/voterController/{id}", method = arrayOf(RequestMethod.GET))
    fun getVoter(@PathVariable id: Int): Voter {
        return voterDao.getById(id)
    }

    @RequestMapping(value = "/voterController/getByHousing", method = arrayOf(RequestMethod.GET))
    fun getVotersByHousing(@PathVariable housing: Housing): List<Voter> {
        when (voterDao) {
            is VoterDao -> return voterDao.getVotersByHousing(housing)
            else -> throw IllegalArgumentException("Incorrect dao used for Voter use case")
        }
    }

    @RequestMapping(value = "/voterController/getByYear", method = arrayOf(RequestMethod.GET))
    fun getVotersByYear(@PathVariable year: Year): List<Voter> {
        when (voterDao) {
            is VoterDao -> return voterDao.getVotersByYear(year)
            else -> throw IllegalArgumentException("Incorrect dao used for Voter use case")
        }
    }

    @RequestMapping(value = "/voterController/getByResidency", method = arrayOf(RequestMethod.GET))
    fun getVotersByResidency(@PathVariable residency: Residency): List<Voter> {
        when (voterDao) {
            is VoterDao -> return voterDao.getVotersByResidency(residency)
            else -> throw IllegalArgumentException("Incorrect dao used for Voter use case")
        }
    }

}
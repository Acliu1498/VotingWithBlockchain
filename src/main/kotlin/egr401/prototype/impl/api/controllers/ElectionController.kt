package egr401.prototype.impl.api.controllers

import egr401.prototype.data.model.Election
import egr401.prototype.impl.persistence.daos.ElectionDAO
import egr401.prototype.inter.persistence.daos.Dao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.lang.Exception
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController



@RestController
class ElectionController @Autowired constructor(private val electionDAO: Dao<Election>) {

    @RequestMapping(value = "/electionController/addElection", method = arrayOf(RequestMethod.POST))
    fun addElection(@RequestBody election: Election): Election {
        try {
            electionDAO.insert(election)
        } catch (e: Exception) {

        }
        when (electionDAO) {
            is ElectionDAO -> return election
            else -> throw IllegalArgumentException("Incorrect dao")
        }
    }

    @RequestMapping(value = "/electionController/getById/{id}", method = arrayOf(RequestMethod.GET))
    fun getElection(@PathVariable id: Int): Election {
        return electionDAO.getById(id)
    }

    @RequestMapping(value = "/electionController/getCurrentElections", method = arrayOf(RequestMethod.GET))
    fun getCurrentElections(): List<Election> {
        // checks that the dao is an election dao
        when (electionDAO) {
            // returns the current elections
            is ElectionDAO -> return electionDAO.getCurrentElections()
            else -> throw IllegalArgumentException("Incorrect dao used for Election Use case")
        }
    }

    @RequestMapping(value = "/electionController/getPastElections", method = arrayOf(RequestMethod.GET))
    fun getPastElections(): List<Election> {
        // checks that the dao is an election dao
        when (electionDAO) {
            // returns the current elections
            is ElectionDAO -> return electionDAO.getPastElections()
            else -> throw IllegalArgumentException("Incorrect dao used for Election Use case")
        }
    }
}
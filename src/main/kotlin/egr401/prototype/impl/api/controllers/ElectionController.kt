package egr401.prototype.impl.api.controllers

import egr401.prototype.data.model.Election
import egr401.prototype.impl.persistence.daos.ElectionDAO
import egr401.prototype.inter.persistence.daos.Dao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.*

import sun.misc.Request
import java.lang.Exception
import java.sql.Date
import java.time.LocalDate


@RestController
//@RequestMapping("/electionController")
class ElectionController @Autowired constructor(private val electionDAO: Dao<Election>) {

    val date1 = Date.valueOf(LocalDate.now())
    val date2 = Date.valueOf(LocalDate.of(2019,1,25))

    @RequestMapping(value= "/electionControlle/addElection", method = arrayOf(RequestMethod.POST))
    fun addElection(@RequestBody election: Election): Election{
        try {
            electionDAO.insert(election)
        }catch (e: Exception){

        }
        return election
    }

    @RequestMapping(value = "/electionController/{name}", method = arrayOf(RequestMethod.GET))
    fun getElection(@PathVariable name:String): Election{
        return Election(1, name , date1,date2)
    }

    @RequestMapping(value = "/electionController/getCurrentElections", method = arrayOf(RequestMethod.GET))
    fun getCurrentElections(): List<Election>{
        // checks that the dao is an election dao
        when (electionDAO){
            // returns the current elections
            is ElectionDAO -> return electionDAO.getCurrentElections()
            else -> throw IllegalArgumentException("Incorrect dao used for Election Use case")
        }
    }

    @RequestMapping(value = "/electionController/getPastElections", method = arrayOf(RequestMethod.GET))
    fun getFinishedElections(): List<Election>{
        // checks that the dao is an election dao
        when (electionDAO){
            // returns the current elections
            is ElectionDAO-> return electionDAO.getPastElections()
            else -> throw IllegalArgumentException("Incorrect dao used for Election Use case")
        }
    }


}

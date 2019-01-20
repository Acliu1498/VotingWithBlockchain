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

    @RequestMapping(value= "/electionController/addElection", method = arrayOf(RequestMethod.POST))
    fun addElection(@RequestBody election: Election): Election{
        try {
            electionDAO.insert(election)
        }catch (e: Exception){

        }
        when (electionDAO) {
            is ElectionDAO -> return election
            else -> throw IllegalArgumentException("Incorrect dao")
        }
    }

    @RequestMapping(value = "/electionController/getById/{id}", method = arrayOf(RequestMethod.GET))
    fun getElection(@PathVariable id: Int): Election{
        return electionDAO.getById(id)
    }

    @RequestMapping(value = "/electionController/getCurrentElections", method = arrayOf(RequestMethod.GET))
    fun getCurrentElections(): List<Election>{
        // checks that the dao is an election dao
        when (electionDAO){
            // returns the current elections
            is ElectionDao -> return electionDAO.getCurrentElections()
            else -> throw IllegalArgumentException("Incorrect dao used for Election Use case")
        }
    }

    @RequestMapping(value = "/electionController/getPastElections", method = arrayOf(RequestMethod.GET))
    fun getPastElections(): List<Election>{
        // checks that the dao is an election dao
        when (electionDAO){
            // returns the current elections
            is ElectionDao-> return electionDAO.getPastElections()
            else -> throw IllegalArgumentException("Incorrect dao used for Election Use case")
        }
    }


=======
//@RequestMapping("/electionController")
class ElectionController {

    val date1 = Date.valueOf(LocalDate.now())
    val date2 = Date.valueOf(LocalDate.of(2019,1,25))

    @RequestMapping(value = "/electionController/{name}", method = arrayOf(RequestMethod.GET))
    fun getElection(@PathVariable name:String): Election{
        return Election(1, name , date1,date2)
    }
>>>>>>> Created election class, DAO, and Controller for the election objects of the application
}

package egr401.prototype.impl.api.controllers

import egr401.prototype.data.model.Election

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.sql.Date
import java.time.LocalDate


@RestController
//@RequestMapping("/electionController")
class ElectionController {

    val date1 = Date.valueOf(LocalDate.now())
    val date2 = Date.valueOf(LocalDate.of(2019,1,25))

    @RequestMapping(value = "/electionController/{name}", method = arrayOf(RequestMethod.GET))
    fun getElection(@PathVariable name:String): Election{
        return Election(1, name , date1,date2)
    }
}

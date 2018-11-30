package egr401.prototype.impl.api.controllers

import egr401.prototype.data.model.Greeting
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingController {

    @RequestMapping(value = "/getGreeting/{id}", method = arrayOf(RequestMethod.GET))
    fun getGreeting(@PathVariable id: Int): Greeting{
        return Greeting(id, "Hello World")
    }
}

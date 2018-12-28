package egr401.prototype.impl.api.controllers

import egr401.prototype.data.model.VoteSubmission
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping



@RestController
class VoteSubmission {

    @RequestMapping(value="/getGreeting/{id}", method=arrayOf(RequestMethod.GET))
    fun getGreeting(@PathVariable id:Int):VoteSubmission{
        return VoteSubmission("Hello world")
    }

    @PostMapping("/greeting")
    fun greetingSubmit(@ModelAttribute VoteSubmission: VoteSubmission): String {
        System.out.println(VoteSubmission);
        return "result"
    }
}
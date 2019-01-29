package egr401.prototype.data.model

import com.fasterxml.jackson.annotation.JsonFormat
import egr401.prototype.data.model.model.enums.Housing
import egr401.prototype.data.model.model.enums.Residency
import egr401.prototype.data.model.model.enums.Year
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Voter")
data class Voter(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        override val id: Int,
        override val studentId: Int,
        override val email: String,
        override val lastName: String,
        override val firstName: String,
        val housing: Housing,
        val year: Year,
        val residency: Residency,
        @JsonFormat(pattern = "yyyy")
        val yearEnrolled: Date
) : User(id, studentId, email, lastName, firstName) {


}

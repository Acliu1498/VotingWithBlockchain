package egr401.prototype.data.model

import javax.persistence.*

@Entity
@Table(name = "Candidate")
data class Candidate(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        override val id: Int,
        override val studentId: Int,
        override val email: String,
        override val lastName: String,
        override val firstName: String,
        val officePosition: String,
        val votes: Int
) : User(id, studentId, email, lastName, firstName) {

}

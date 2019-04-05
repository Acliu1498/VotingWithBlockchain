package egr401.prototype.data.model

import javax.persistence.*

@Entity
@Table(name = "Candidate")
data class Candidate(
        @Id
        val id: Int,
        val email: String,
        val lastName: String,
        val firstName: String,
        val resume: String = "",
        val image: String = ""
)

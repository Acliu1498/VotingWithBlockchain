package egr401.prototype.data.model

import javax.persistence.*

@Entity
@Table(name = "Candidate")
data class Candidate(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int,
        val studentId: Int,
        val email: String,
        val lastName: String,
        val firstName: String,

        @ManyToOne
        @JoinColumn
        val elections: Election

)

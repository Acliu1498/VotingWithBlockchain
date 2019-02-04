package egr401.prototype.data.model

import javax.persistence.*

@Entity
@Table(name = "Candidate")
data class Candidate(

        @Id
        val studentId: Int,
        val email: String,
        val lastName: String,
        val firstName: String,

        @ManyToMany
        @JoinTable(name = "candidate_election",
                joinColumns = arrayOf(JoinColumn(name = "candidate_id", referencedColumnName = "studentId")),
                inverseJoinColumns = arrayOf(JoinColumn(name = "election_id", referencedColumnName = "id")))
        val elections: MutableList<Election>

)

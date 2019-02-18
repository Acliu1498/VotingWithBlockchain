package egr401.prototype.data.model

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "CandidateElection")
data class CandidateElection(

    @Id
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    val candidate: Candidate,
    @Id
    @ManyToOne
    @JoinColumn(name = "election_id")
    val election: Election,
    val votes: Int = 0
) : Serializable


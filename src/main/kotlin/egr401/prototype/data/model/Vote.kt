package egr401.prototype.data.model


import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Vote")
data class Vote(
    @Id
    val voterId: Int,
    @Id
    val candidateId: Int,
    @Id
    val electionId: Int,
    var stored: Boolean = false
): Serializable
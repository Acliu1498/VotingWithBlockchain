package egr401.prototype.data.model

import com.fasterxml.jackson.annotation.JsonFormat
import egr401.prototype.data.model.model.enums.Housing
import egr401.prototype.data.model.model.enums.Residency
import egr401.prototype.data.model.model.enums.Year
import org.jetbrains.annotations.NotNull
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Voter")
data class Voter(

        @Id
        val voterId: Int,
        var hasVoted: Boolean = false,

        @Id
        @ManyToOne
        @NotNull
        @JoinColumn(name="election_id")
        val election: Election

): Serializable

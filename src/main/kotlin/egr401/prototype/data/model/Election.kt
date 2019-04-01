package egr401.prototype.data.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "Election")
data class Election(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int,
        val name: String,
        val startDate: LocalDateTime,
        val endDate: LocalDateTime,
        val finished: Boolean = false
)
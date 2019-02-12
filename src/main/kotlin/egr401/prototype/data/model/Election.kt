package egr401.prototype.data.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Election")
data class Election(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int,
        val name: String,

        @Temporal(TemporalType.DATE)
        val startDate: Date,
        @Temporal(TemporalType.DATE)
        val endDate: Date,
        val finished: Boolean = false
)
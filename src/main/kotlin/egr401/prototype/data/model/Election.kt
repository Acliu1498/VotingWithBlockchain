package egr401.prototype.data.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Election")
data class Election(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int,
        val name: String,
        val startDate: LocalDate,
        val endDate: LocalDate,
        val finished: Boolean = false
)
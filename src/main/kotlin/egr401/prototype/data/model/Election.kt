package egr401.prototype.data.model

import egr401.prototype.data.model.model.enums.Housing
import egr401.prototype.data.model.model.enums.Year
import java.time.LocalDate
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
        val finished: Boolean = false,
        val housings: List<Housing>,
        val year: List<Year>
)
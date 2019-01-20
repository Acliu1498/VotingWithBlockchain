package egr401.prototype.data.model

import java.util.*
import javax.annotation.Generated
import javax.persistence.*

@Entity
@Table(name = "election")
data class Election(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int,
        val name: String,
        val startDate: Date,
        val endDate: Date

)
package egr401.prototype.data.model

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.annotation.Generated
import javax.persistence.*

@Entity
@Table(name = "Election")
data class Election(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int,
        val name: String,
        @JsonFormat(pattern="yyyyMMdd")
        val startDate: Date,
        @JsonFormat(pattern="yyyyMMdd")
        val endDate: Date

)
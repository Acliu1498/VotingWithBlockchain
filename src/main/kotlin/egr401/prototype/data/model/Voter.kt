package egr401.prototype.data.model

import com.fasterxml.jackson.annotation.JsonFormat
import egr401.prototype.data.model.model.enums.Housing
import egr401.prototype.data.model.model.enums.Residency
import egr401.prototype.data.model.model.enums.Year
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "Voter")
data class Voter(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int,
        val studentId: Int,

        @ManyToOne
        @JoinColumn
        val elections: Election

){


}

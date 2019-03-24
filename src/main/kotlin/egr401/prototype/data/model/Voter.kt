package egr401.prototype.data.model

import egr401.prototype.data.model.model.enums.Housing
import egr401.prototype.data.model.model.enums.Year
import javax.persistence.*

@Entity
@Table(name = "Voter")
data class Voter(
    @Id
    val id: Int,

    @Enumerated(EnumType.STRING)
    val housing: Housing,
    @Enumerated(EnumType.STRING)
    val year: Year
)
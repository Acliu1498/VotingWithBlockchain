package egr401.prototype.data.model

import javax.annotation.Generated
import javax.persistence.*

@Entity
@Table(name = "greeting")
data class Greeting(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,
    val text: String
)
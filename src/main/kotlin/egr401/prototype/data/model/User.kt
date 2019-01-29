package egr401.prototype.data.model

import javax.persistence.*

@Entity
@Table(name = "User")
abstract class User(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        open val id: Int,
        open val studentId: Int,
        open val email: String,
        open val lastName: String,
        open val firstName: String


)


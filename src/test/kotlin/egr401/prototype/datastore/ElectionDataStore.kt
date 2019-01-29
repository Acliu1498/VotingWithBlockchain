package egr401.prototype.datastore

import egr401.prototype.data.model.Election
import java.util.*

data class ElectionDataStore(
        val elections: List<Election> = listOf(
                Election(0, "aaa", Date(2019, 2, 3), Date(2019, 2, 23)),
                Election(0, "aab", Date(2019, 3, 2), Date(2019, 3, 30)),
                Election(0, "aac", Date(2019, 4, 3), Date(2019, 4, 24)),
                Election(0, "baa", Date(2018, 2, 3), Date(2018, 2, 23)),
                Election(0, "bab", Date(2018, 3, 2), Date(2018, 3, 30)),
                Election(0, "bac", Date(2018, 4, 3), Date(2018, 4, 24))
        ),

        val currentElections: List<Election> = listOf(
                Election(1, "aaa", Date(2019, 2, 3), Date(2019, 2, 23)),
                Election(2, "aab", Date(2019, 3, 2), Date(2019, 3, 30)),
                Election(3, "aac", Date(2019, 4, 3), Date(2019, 4, 24))
        ),

        val pastElections: List<Election> = listOf(
                Election(4, "baa", Date(2018, 2, 3), Date(2018, 2, 23)),
                Election(5, "bab", Date(2018, 3, 2), Date(2018, 3, 30)),
                Election(6, "bac", Date(2018, 4, 3), Date(2018, 4, 24))
        )
)
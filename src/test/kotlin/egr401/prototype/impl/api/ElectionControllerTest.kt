package egr401.prototype.impl.api

import egr401.prototype.PrototypeApplication
import egr401.prototype.data.model.Election
import egr401.prototype.datastore.ElectionDataStore
import egr401.prototype.impl.api.controllers.ElectionController
import egr401.prototype.impl.persistence.daos.ElectionDAO
import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import javax.validation.constraints.AssertTrue

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = arrayOf(PrototypeApplication::class))
class ElectionControllerTest {


    @Autowired
    lateinit var electionDao: ElectionDAO

    @Autowired
    lateinit var electionController : ElectionController

    val electionDataStore = ElectionDataStore()

    @Test
    fun addElectionTest() {
        Mockito.`when`(electionDao.getCurrentElections()).thenReturn(electionDataStore.currentElections)
        Mockito.`when`(electionDao.getPastElections()).thenReturn(electionDataStore.pastElections)
        Assert.assertEquals(electionDataStore.currentElections[2], electionController.addElection(electionDataStore.currentElections[0]))
    }

    @Test
    fun


}
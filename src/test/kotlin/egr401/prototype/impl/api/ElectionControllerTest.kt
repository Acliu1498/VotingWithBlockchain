package egr401.prototype.impl.api

import egr401.prototype.PrototypeApplication
import egr401.prototype.datastore.ElectionDataStore
import egr401.prototype.impl.api.controllers.ElectionController
import egr401.prototype.impl.persistence.daos.ElectionDao
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = arrayOf(PrototypeApplication::class))
class ElectionControllerTest {


    @Autowired
    lateinit var electionDao: ElectionDao

    @Autowired
    lateinit var electionController : ElectionController

    val electionDataStore = ElectionDataStore()

    @Test
    fun addElection_GivenValidElection_ReturnsElection() {
        Mockito.`when`(electionDao.getCurrentElections()).thenReturn(electionDataStore.currentElections)
        Mockito.`when`(electionDao.getPastElections()).thenReturn(electionDataStore.pastElections)
        Assert.assertEquals(electionDataStore.currentElections[2], electionController.addElection(electionDataStore.currentElections[0]))
    }

    @Test
    fun getElection_GivenId_ReturnsElectionWithId() {
        var id: Int = 1
        Mockito.`when`(electionDao.getById(id)).thenReturn(electionDataStore.currentElections.filter { it.id == id }[0])
        Assert.assertEquals(electionController.getElection(id), electionDao.getCurrentElections()[0])
    }

    @Test
    fun getCurrentElection_ReturnsCurrentElections() {
        Mockito.`when`(electionDao.getCurrentElections()).thenReturn(electionDataStore.currentElections)
        Assert.assertEquals(electionDataStore.currentElections, electionController.getCurrentElections())
    }

    @Test
    fun getPastElection_ReturnsPastElections() {
        Mockito.`when`(electionDao.getPastElections()).thenReturn(electionDataStore.currentElections)
        Assert.assertEquals(electionDataStore.currentElections, electionController.getPastElections())
    }


}
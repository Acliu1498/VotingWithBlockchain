package egr401.prototype.impl.presistence.daos

import egr401.prototype.PrototypeApplication
import egr401.prototype.datastore.ElectionDataStore
import egr401.prototype.impl.persistence.daos.ElectionDao
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * Integration test with database
 */
@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(PrototypeApplication::class, DataSourceConfig::class))
class ElectionDaoIntegrationTest {

    @Autowired
    lateinit var electionDao: ElectionDao

    val electionDataStore: ElectionDataStore = ElectionDataStore()

    init {
        for(election in electionDataStore.elections){
            electionDao.insert(election)
        }
    }

    @Test
    fun getById_DBContainsElections_ReturnsCorrectElection(){
        for(election in electionDataStore.currentElections){
            Assert.assertEquals(electionDao.getById(election.id), election)
        }
    }

    @Test
    fun update_DBContainsElections_ReturnsUpdatedElection(){
        for(election in electionDataStore.currentElections){
            Assert.assertEquals(electionDao.getById(election.id), election)
        }
    }

    @Test
    fun getCurrentElections(){
        Assert.assertEquals(electionDataStore.currentElections, electionDao.getCurrentElections())
    }

    @Test
    fun getPastElections(){
        Assert.assertEquals(electionDataStore.pastElections, electionDao.getPastElections())
    }

    @Test
    fun delete(){
        for(election in electionDataStore.currentElections){
            electionDao.delete(election)
            Assert.assertNull(electionDao.getById(election.id))
        }

        for(election in electionDataStore.pastElections){
            electionDao.delete(election)
            Assert.assertNull(electionDao.getById(election.id))
        }
    }



}
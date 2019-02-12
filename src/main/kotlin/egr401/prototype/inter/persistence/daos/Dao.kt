package egr401.prototype.inter.persistence.daos


interface Dao<T> {
    fun insert(obj: T)
    fun getById(id: Int): T
    fun update(obj: T): T
    fun delete(obj: T)
}
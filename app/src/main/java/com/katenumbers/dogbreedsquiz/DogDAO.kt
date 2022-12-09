package com.katenumbers.dogbreedsquiz

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.katenumbers.dogbreedsquiz.models.Dog
import com.katenumbers.dogbreedsquiz.models.TableMade

@Dao
interface DogDAO {
    @Query("SELECT * FROM dog")
    suspend fun getAll(): List<Dog>

    @Query("SELECT * FROM dog WHERE name LIKE :name")
    suspend fun findByName(name: String): Dog

    @Query("SELECT DISTINCT * FROM dog ORDER BY name")
    suspend fun orderByName(): List<Dog>

    @Insert
    suspend fun createDog(dog: Dog)

    @Query("SELECT COUNT(*) FROM dog")
    suspend fun getLength(): Int

    @Insert
    suspend fun createLoaded(tableMade: TableMade)

    @Query("SELECT loaded FROM tablemade WHERE id = :id")
    suspend fun isLoaded(id: Int): List<String>

    @Update
    suspend fun updateLoaded(tableMade: TableMade)

    @Query("DELETE FROM tablemade WHERE id != :id")
    suspend fun deleteUnused(id: Int)

    @Query("SELECT * FROM dog ORDER BY RANDOM() LIMIT :n")
    suspend fun getRandom(n: Int): List<Dog>

    @Query("SELECT name FROM dog WHERE id = 1")
    suspend fun getFirstRow(): String

    @Query("DELETE FROM dog WHERE id NOT BETWEEN 1 AND 353")
    suspend fun deleteExtras()

    @Query("SELECT name FROM dog WHERE name != :name ORDER BY RANDOM() LIMIT 2")
    suspend fun getRandom3(name: String): List<String>
}

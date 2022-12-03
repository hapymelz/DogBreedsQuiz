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

    @Query("SELECT * FROM dog ORDER BY name")
    suspend fun orderByName(): List<Dog>

    @Insert
    suspend fun createDog(dog: Dog)

    @Query("SELECT COUNT(*) FROM dog")
    suspend fun getLength(): Int

    @Insert
    suspend fun createLoaded(tableMade: TableMade)

    @Query("SELECT loaded FROM tablemade WHERE id = :id")
    suspend fun isLoaded(id: Int): String

    @Update
    suspend fun updateLoaded(tableMade: TableMade)

    @Query("DELETE FROM tablemade WHERE id != :id")
    suspend fun deleteUnused(id: Int)

    @Query("SELECT * FROM DOG ORDER BY RANDOM() LIMIT :n")
    suspend fun getRandom(n: Int): List<Dog>
}
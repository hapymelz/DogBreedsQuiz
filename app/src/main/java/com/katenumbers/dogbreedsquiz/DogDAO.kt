package com.katenumbers.dogbreedsquiz

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.katenumbers.dogbreedsquiz.models.Dog

@Dao
interface DogDAO {
    @Query("SELECT * FROM dog")
    suspend fun getAll(): List<Dog>

    @Query("SELECT * FROM dog WHERE name LIKE :name")
    suspend fun findByName(name: String): Dog

    @Query("SELECT * FROM dog ORDER BY name")
    suspend fun orderByName(): List<Dog>

    @Insert
    fun createDog(dog: Dog)
}
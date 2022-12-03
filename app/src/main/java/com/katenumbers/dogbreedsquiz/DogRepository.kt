package com.katenumbers.dogbreedsquiz

import androidx.room.Room
import com.katenumbers.dogbreedsquiz.models.Dog
import com.katenumbers.dogbreedsquiz.models.TableMade

object DogRepository {
    private val db: AppDatabase

    init {
        db = Room.databaseBuilder(
            DogApplication.getInstance(),
            AppDatabase::class.java,
            "Dog Database"
        ).build()
    }

    suspend fun getAll(): List<Dog> {
        return db.dogDao().getAll()
    }

    suspend fun findByName(name: String): Dog {
        return  db.dogDao().findByName(name)
    }

    suspend fun orderByName(): List<Dog> {
        return db.dogDao().orderByName()
    }

    suspend fun createDog(dog: Dog) {
        db.dogDao().createDog(dog)
    }

    suspend fun getLength(): Int {
        return db.dogDao().getLength()
    }

    suspend fun createLoaded(tableMade: TableMade) {
        db.dogDao().createLoaded(tableMade)
    }

    suspend fun isLoaded(id: Int): String {
        return db.dogDao().isLoaded(id)
    }

    suspend fun updateLoaded(tableMade: TableMade) {
        db.dogDao().updateLoaded(tableMade)
    }

    suspend fun deleteUnused(id: Int) {
        db.dogDao().deleteUnused(id)
    }

    suspend fun getRandom(n: Int): List<Dog> {
        return db.dogDao().getRandom(n)
    }
}

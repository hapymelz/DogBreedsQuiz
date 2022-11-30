package com.katenumbers.dogbreedsquiz

import androidx.room.Room
import com.katenumbers.dogbreedsquiz.models.Dog

object DogRepository {
    private val db: AppDatabase

    init {
        db = Room.databaseBuilder(
            DogApplication.getInstance(),
            AppDatabase::class.java,
            "dog"
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

    fun createDog(dog: Dog): Unit {
        db.dogDao().createDog(dog)
    }

}

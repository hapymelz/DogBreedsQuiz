package com.katenumbers.dogbreedsquiz

import androidx.room.Database
import androidx.room.RoomDatabase
import com.katenumbers.dogbreedsquiz.models.Dog

@Database(entities = [Dog::class], exportSchema = false, version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dogDao(): DogDAO
}
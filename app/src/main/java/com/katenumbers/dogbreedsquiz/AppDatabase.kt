package com.katenumbers.dogbreedsquiz

import androidx.room.Database
import androidx.room.RoomDatabase
import com.katenumbers.dogbreedsquiz.models.Dog
import com.katenumbers.dogbreedsquiz.models.TableMade

@Database(entities = [Dog::class, TableMade::class], exportSchema = false, version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dogDao(): DogDAO
}
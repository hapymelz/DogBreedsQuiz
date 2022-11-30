package com.katenumbers.dogbreedsquiz.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dog (
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "name") var name : String,
    @ColumnInfo(name = "section") var section : String,
    @ColumnInfo(name = "group") var group : String,
    @ColumnInfo(name = "country") var country : String,
    @ColumnInfo(name = "image") var image: String,
    )
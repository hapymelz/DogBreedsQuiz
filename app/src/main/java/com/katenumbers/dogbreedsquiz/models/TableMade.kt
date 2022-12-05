package com.katenumbers.dogbreedsquiz.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TableMade(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo var loaded: String
)
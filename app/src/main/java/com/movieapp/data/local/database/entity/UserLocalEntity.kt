package com.movieapp.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
class UserLocalEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0,
    @ColumnInfo(name = "gender")
    var gender: String,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "region")
    var region: String,
    @ColumnInfo(name = "surname")
    var surname: String
)